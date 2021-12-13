package Default;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
//import java.util.Scanner;

@FunctionalInterface
interface Func {
	double apply(double w, double cRVN, double cD, double A, double AD, double fRG, double cM, double p);
}

/**
 * Simulation is used to calculate information for races and for holding default objects 
 * used by the program. 
 * 
 * IMPORTANT VARIABLES FOR THIS CLASS: 
 *  - GRAVITATIONALACCEL (M/S^2) : The free falling acceleration of an object
 *     due to earth's gravity. 
 *  - VDDROLLINGRESISTANCECOE : The velocity-dependent dynamic rolling resistance,
 *     approximated to 0.1.
 *	- POWERTRANSLOSSCOE : Cofficient for power transmission losses due to tire
 *	   slippage. 
 *  - AIRDENTSITYATSL : Air density at Sea Level, 0 Degreees Celsius (kg/m^3).
 *  - AIRPRESSUREATSL : Air Pressure at Sea Level, 0 Degrees Celsius (Pascals).
 * @author baude2d, dunha2j
 */
public class Simulation{ 

	static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	static ArrayList<Cyclist> cyclistList = new ArrayList<Cyclist>();
	static ArrayList<String> cyclistStyleList = new ArrayList<String>();
	static Map<String, Double> hazardMap = new LinkedHashMap<String, Double>();

	// final variables used to calculate velocity
	final static double VDDROLLINGRESISTANCECOE = 0.1;
	final static double POWERTRANSLOSSCOE = 1.03;
	final static double AIRDENSITYATSL = 1.293; 
	final static double AIRPRESSUREATSL = 101325; 
	final static double GRAVITYACCEL = 9.81; 
	

	// other variables for calculating velocity
	static double rollingResistanceCoe; // Bike tire on asphalt
	
	/**
	 * A simple method that adds the default values for several static lists that
	 * referenced by the program. 
	 * These include: 
	 * 	- bikeList : A list default Bike objects to choose from. 
	 * 	- cyclistList* : A list default Cyclist objects to choose form. A cyclists 
	 * 	  watts per kg is determined by, Functional Threshold Power(FTP)/Body Mass. 
	 * 	  Cylists have been categorized in a hierarchy based on how high their 
	 * 	  FTP/kg is. 
	 * 	- hazardMap : A map of hazards that could occur during a race. Key = Name 
	 * 	  and Value = The chance that hazard occurs if a hazard is triggered.
	 * 	- cyclistStyleList <CURRENTLY UNUTILIZED> 
	 * 	- weather <CURRENTLY UNUTILIZED>
	 * 
	 * *Cyclists are instantiated with a Bike Object to properly calculate the
	 * effectiveDragArea.
	 */
	//loadDefaultItems from simulation testing before GUI implementation
	public static void loadDefaultItems() {

		//// BIKES////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Bike: /make/model/year/bikeMass(kg)/rollingResistanceCoe/frameMaterial/classification
		bikeList.add(new Bike("Giant", "TCR Advanced Pro", 2021, 7.6, 0.00330, "Carbon", "Road Race"));
		bikeList.add(new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.00330, "Carbon", "Time Trail"));
		bikeList.add(new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race"));
		bikeList.add(new Bike("LiteSpeed", "Ultimate Gravel", 2021, 8.93, 0.00460, "Titanium", "Gravel"));
		bikeList.add(new Bike("Steel Bike", "Custom", 1998, 9.07185, 0.00330, "Steel", "Road Race"));

		//// CYCLISTS///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cyclist: /name/mass(kg)/height(cm)/effectiveDragArea(m2)/airDragCoefficent/FTP(W)/riderStyle/bikeObj
		// WORLD TOUR PROS
		cyclistList.add(new Cyclist("Eddy Merckx", 72, 170, 0.4889, 0.96759, 430, "Sprinter", bikeList.get(4)));
		// DOMESTIC PROS
		// CATEGORY 1 RACERS
		cyclistList.add(new Cyclist("Brandon", 70.76, 158, 0.5758, 0.96759, 345, "Climber", bikeList.get(3)));
		cyclistList.add(new Cyclist("Alec", 59.87, 150, 0.4397, 0.96759, 303, "Climber", bikeList.get(2))); 
		// CATEGORY 2 RACERS
		cyclistList.add(new Cyclist("Mason", 83.91, 179, 0.5270, 0.96759, 365, "Sprinter", bikeList.get(0))); 																									
		// CATGORY 3 RACERS
		cyclistList.add(new Cyclist("Josh", 106, 155, 0.5478, 0.96759, 414, "Sprinter", bikeList.get(2))); 																																																// 414
		cyclistList.add(new Cyclist("Jon", 90, 190, 0.5529, 0.96775, 400, "Sprinter", bikeList.get(0))); 

		//// CYCLIST STYLES////////////////////////////////////////////////////////////////////////////////////////////////////////
		cyclistStyleList.add("Climber"); // Bonus on Velocity when Climbing, Slower on Flat Grades and Descents up to -5%
		cyclistStyleList.add("Sprinter"); // Bonus on Velocity for Flat Grades and Descents up to -5%, Slower on climbs
		cyclistStyleList.add("All-Arounder"); // Neither Bonus nor Disadvantages
		cyclistStyleList.add("Time Trialist"); // Slight Bonus when riding alone
		cyclistStyleList.add("Descender"); // Can still output wattage at up to 15% grade

		//// HAZARDS///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		hazardMap.put("Mechanical : Popped Tire", 25.0);
		hazardMap.put("Mechanical : Snapped Chain", 15.0);
		hazardMap.put("Mechanical : Cracked Frame", 8.0);
		hazardMap.put("Crash : Tire Slipped on Turn", 25.0);
		hazardMap.put("Crash : Hit Team Car", 5.0);
		hazardMap.put("Crash : Rode into Pothole", 18.0);

		//// WEATHER///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

	/**
	 * Calculates the current velocity of a cyclist based on a number of variables. 
	 * Returned number is in meters per second. 
	 * 
	 * EQUATIONS ARE PROVIDED FROM: www.kreuzotter.de
	 * 
	 * Calculations are based on the following variables: 
	 * - Power (Wattage) 
	 * - Wind Speed (Meters per Second) 
	 * - Gravitational Acceleration (Meters per Second^2) 
	 * - Total Frontal Area (Meters^2) 
	 * - Coefficient for Velocity
	 * - Dependent dynamic rolling resistance 
	 * - Coefficient for the dynamic rolling resistance 
	 * - Coefficient for the power transmission losses due to tire slippage
	 * 
	 * - Rolling Friction: 
	 * 	- Inclination Angle 
	 * 	- Grade (% Grade) 
	 * 	- Mass of Bike (Kilograms) 
	 * 	- Mass of Rider (Kilograms) 
	 * 	- Rolling Resistance Coefficient
	 * 
	 * - AirDensity (Kilogram per Meter^3): 
	 * 	- Height Above Sea Level (Meters) 
	 * 	- Temperature (Degrees Kelvin) 
	 * 	- Air Density at Sea Level, 0 Degrees Celsius (Kilograms per Meter^3) 
	 * 	- Air Pressure at Sea Level, 0 Degrees Celsius (Pascals)
	 * 
	 * @param racer - The Cyclist object that is being assessed. 
	 * @param course - The Course that the Cyclist is traversing.
	 * @param weatherObj - The Weather that the Cyclist is experiencing. 
	 * @param time - The current time that the Cyclist has spent racing. 
	 * @return The velocity of the Cylist (m/s) 
	 */
	public static double calculateVelocity(Cyclist racer, RaceCourse course, double time) {
		double velocity = 0;
		
		racer.adjustCurrentPower(time, course.getSlope());
		double dynamicRollingResistanceNRN = VDDROLLINGRESISTANCECOE;
		double windSpeed = course.getWeatherObj().getWindSpeedMPS();
		double airDensity = AIRDENSITYATSL;
		double rollingFriction = calcRollingFriction(racer, course.getSlope());

		Func a = (double w, double cRVN, double cD, double A, double PAD, double fRG, double cM, double p) -> ((Math.pow(w, 3) - Math.pow(cRVN, 3)) / 27)
				- ((w * (5 * w * cRVN + ((8 * Math.pow(cRVN, 2)) / (A * PAD)) - (6 * fRG))) / (9 * A * PAD)) + ((2 * fRG * cRVN) / (3 * Math.pow((A * PAD), 2))) 
				+ (p / (cM * A * PAD));
		
		Func b = (double w, double cRVN, double cD, double A, double PAD, double fRG, double cM, double p) -> ((2) / (9 * A * PAD)) * (3 * fRG - (4 * w * cRVN) 
				- (Math.pow(w, 2) * A * (PAD / 2)) - ((2 * cRVN) / (A * PAD)));

		double aVal = a.apply(windSpeed, dynamicRollingResistanceNRN, racer.getAirDragCoefficent(), racer.getEffectiveDragArea(), airDensity, rollingFriction,
					POWERTRANSLOSSCOE, racer.getCurrentPower());
		double bVal = b.apply(windSpeed, dynamicRollingResistanceNRN, racer.getAirDragCoefficent(), racer.getEffectiveDragArea(), airDensity, rollingFriction, 
					POWERTRANSLOSSCOE, racer.getCurrentPower());

		// Velocity for pedaling on flat or inclined terrain
		if (Math.pow(aVal, 2) + Math.pow(bVal, 3) >= 0) { 
			velocity = Math.cbrt(aVal + Math.sqrt(Math.pow(aVal, 2) + Math.pow(bVal, 3))) + Math.cbrt(aVal - Math.sqrt(Math.pow(aVal, 2) + Math.pow(bVal, 3))) 
					   - ((2.0 / 3.0) * (windSpeed + (dynamicRollingResistanceNRN / (racer.getAirDragCoefficent() * racer.getEffectiveDragArea() * airDensity))));
			// Velocity for descending from a hill
		} else if (Math.pow(aVal, 2) + Math.pow(bVal, 3) < 0) {
			// Not necessarily accurate, but functions
			velocity = (2 * Math.sqrt(-bVal)) * Math.cos(Math.toRadians((1 / 3) * aVal / Math.sqrt(Math.pow(-bVal, 3)))) - (2 / 3) * 
					   (windSpeed + (dynamicRollingResistanceNRN / (racer.getAirDragCoefficent() * racer.getEffectiveDragArea() * airDensity)));
		}

		return velocity;
	}

	/**
	 * Calculates the rolling friction for a cyclist (fRG), based on the combined
	 * weight of them and their bike and the current grade of the race course.
	 * 
	 * @param racer - The racer object that is having their velocity calculated.
	 * @param grade - The current slope of the race course, in percent (%).
	 * @return - The current rolling friction value of the cyclist (fRG).
	 */
	public static double calcRollingFriction(Cyclist racer, double grade) {
		// B = ("Beta") Inclination Angle
		double B = Math.atan(grade / 100);
		double totalMass = racer.getTotalMass();
		// Rolling Resistance coefficient of bike tire on race course surface
		double rollingResistanceCoe = racer.bikeObj.getRollingResistanceCoe();

		return (GRAVITYACCEL * (totalMass)) * (rollingResistanceCoe * Math.cos(B) + Math.sin(B));
	}
	
	public static void main(String[] args) throws Exception {
		loadDefaultItems();
		
		Weather weatherObj = new Weather("Rain", 3, 0, "Foggy");
		RaceCourse course = new RaceCourse("Test", 100, 8, 1, weatherObj);
		Cyclist Brandon = new Cyclist("Brandon", 70.76, 158, 0.4738, 0.96759, 345, "Climber", new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race"));
		Cyclist Alec = new Cyclist("Alec", 59.87, 150, 0.4387, 0.96759, 303, "Climber",  new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race"));
		Cyclist Josh = new Cyclist("Josh", 106, 155, 0.4738, 0.96759, 414, "Sprinter", new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.00330, "Carbon", "Time Trail")); 
		
		System.out.println(calculateVelocity(Brandon, course, 1));

	}

	
	//Unutilized calcAirDensity method
		/*
		 * public static double calcAirDensity(double Temp, double heightAbvSL) { 
		 * return (AIRDENSITYATSL * (373/Temp) * (Math.exp(((-1 * AIRDENSITYATSL) * GRAVITYACCEL) * (heightAbvSL/AIRPRESSUREATSL)))); 
		 * }
		 */
}