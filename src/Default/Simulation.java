/**
 * 
 */
package Default;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@FunctionalInterface
interface Func {
	double apply(double w, double cRVN, double cD, double A, double AD, double fRG, double cM, double p);
	//double apply(double a, double b, double w, double cRVN, double cD, double A, double PAD);
}

/**
 * @author baude2d, dunha2j
 *
 */
public class Simulation extends Utility {
	static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	static ArrayList<Cyclist> cyclistList = new ArrayList<Cyclist>();
	static ArrayList<String> cyclistStyleList = new ArrayList<String>();

	// final variables used to calculate velocity
	final static double VDDROLLINGRESISTANCECOE = 0.1;
	final static double POWERTRANSLOSSCOE = 1.03;
	final static double AIRDENSITYATSL = 1.293; // kg/m^3
	final static double AIRPRESSUREATSL = 101325; // Pa
	final static double GRAVITYACCEL = 9.81; // m/s^2

	// other variables for calculating velocity
	static double rollingResistanceCoe; // Bike tire on asphalt
	static double totalFrontalArea; // m^2

	// Testing objects; No simulating yet
	public static void main(String[] args) throws Exception {
		loadDefaultObjects();
		double standardLength = 97;
		double checkPoints = createCheckPoints(standardLength);
		String terrain = checkTerrain(checkPoints);
		String hazards = checkHazards(checkPoints);
		Weather weatherObj = new Weather("Rain", 0, 0, "Foggy"); //Change to degrees celcius 
		// checkSlope(checkPoints)
		RaceCourse course = new RaceCourse(standardLength, checkPoints, 0, -3, terrain, hazards, weatherObj);

//		ArrayList<Integer> hillList = new ArrayList<Integer>();
//		hillList = course.generateHills(standardLength, checkPoints);
		
		
		Scanner sr = new Scanner(System.in);
		String curr;
		int min = 0;
		
		ArrayList<String> nameList = new ArrayList<String>();
		HashMap<Cyclist, Double> distanceMap = new HashMap<>();
	
		System.out.println("Ready, set... GO!");
		
		for (int i = 0; i < cyclistList.size(); i++) {
			distanceMap.put(cyclistList.get(i), 0.0);
			nameList.add(cyclistList.get(i).getName());
			System.out.printf("%s distance traveled: %.2f\n", nameList.get(i), distanceMap.get(cyclistList.get(i)));
		}
	
		System.out.println("Enter c to continue, enter e to end race early");
		int x = 1;
		while (x == 1) {
			curr = sr.next();

			for (int j = 0; j < 100; j++) {
				for (int i = 0; i < cyclistList.size(); i++) {
					double speed = calculateVelocity(cyclistList.get(i), course, weatherObj, j);
					distanceMap.put(cyclistList.get(i), distanceMap.get(cyclistList.get(i)) + speed);
					System.out.println(nameList.get(i) + " distance traveled: " + distanceMap.get(cyclistList.get(i)) + " current Power: " + cyclistList.get(i).getCurrentPower());
				}
				System.out.println();
			}
			
			// I changed my mind, instead of focusing on terminating manually I
			// am going to wait until the clock is up and running in the GUI
//			if(curr == "e") {
//				x = 0;
//			}

		}
		//sr.close();
		/*
		 * 
		 * 
		 * 
		 * System.out.println("Length: " + rc.getLengthInMiles());
		 * System.out.println("Num of check points: " + rc.getCheckPoints());
		 * System.out.println("Current slope: " + rc.getSlope());
		 * System.out.println("Terrain: " + rc.getTerrain());
		 * System.out.println("Hazards: " + rc.getHazard());
		 * System.out.println("Precipitation: " + weatherObj.getPrecipitation());
		 * System.out.println("Temperature: " + weatherObj.getTemperatureInF());
		 * System.out.println("Visibility: " + weatherObj.getVisibility());
		 */

	}

	public static void loadDefaultObjects() {
		
		////BIKES////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Bike: /make/model/year/bikeMass(kg)/rollingResistanceCoe/frameMaterial/classification
		bikeList.add(new Bike("Giant", "TCR Advanced Pro", 2021, 7.6, 0.00330, "Carbon", "Road Race"));
		bikeList.add(new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.00330, "Carbon", "Time Trail"));
		bikeList.add(new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race")); 
		bikeList.add(new Bike("LiteSpeed", "Ultimate Gravel", 2021, 8.93, 0.00460, "Titanium", "Gravel"));
		bikeList.add(new Bike("Steel Bike", "Custom", 1998, 9.07185, 0.00330, "Steel", "Road Race"));
		
		 
		////CYCLISTS///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		                           //name/mass(kg)/height(cm)/effectiveDragArea(m2)/airDragCoefficent/FTP(W)/riderStyle/bikeObj
		
		//WORLD TOUR PRO
		cyclistList.add(new Cyclist("Eddy Merckx", 72, 170, 0.4889, 0.96759,430,"Sprinter",bikeList.get(4)));
		//DOMESTIC PRO
		
		//CAT 1
		cyclistList.add(new Cyclist("Brandon", 70.76, 158, 0.5758, 0.96759, 345, "Climber", bikeList.get(3)));//4.89w/kg
		cyclistList.add(new Cyclist("Alec", 59.87, 150, 0.4397, 0.96759, 303, "Climber", bikeList.get(2))); //5.07 w/kg
		//CAT 2
		cyclistList.add(new Cyclist("Mason", 83.91, 179, 0.5270, 0.96759, 365, "Sprinter", bikeList.get(0))); //4.35 w/kg
		//CAT 3
		cyclistList.add(new Cyclist("Josh", 106, 155, 0.5478, 0.96759, 414, "Sprinter", bikeList.get(2))); //Cat 3 Racer, 3.91 w/kg, W: 414
		cyclistList.add(new Cyclist("Jon", 90, 190, 0.5529, 0.96775, 400, "Sprinter", bikeList.get(0))); //W:400

		////CYCLIST STYLES////////////////////////////////////////////////////////////////////////////////////////////////////////
		cyclistStyleList.add("Climber"); // Bonus on Velocity when Climbing, Slower on Flat Grades and Descents up to -5%
		cyclistStyleList.add("Sprinter"); // Bonus on Velocity for Flat Grades and Descents up to -5%, Slower on climbs
		cyclistStyleList.add("All-Arounder"); // Neither Bonus nor Disadvantages
		cyclistStyleList.add("Time Trialist"); // Slight Bonus when riding alone 
		cyclistStyleList.add("Descender"); //Can still output wattage at up to 15% grade
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
		// Combined Mass of racer and bike
		double totalMass = racer.getTotalMass();
		// Rolling Resistance coefficient of bike tire on race course surface
		double rollingResistanceCoe = racer.bikeObj.getRollingResistanceCoe();

		return (GRAVITYACCEL * (totalMass)) * (rollingResistanceCoe * Math.cos(B) + Math.sin(B));
	}


	/**
	 * Calculates the current velocity of a cyclist based on the following
	 * variables:
	 * 	- Power (Wattage) 
	 * 	- Wind Speed (Meters per Second)
	 * 	- Gravitational Acceleration (Meters per Second^2) 
	 * 	- Total Frontal Area(Meter^2)
	 *  - Coefficient for Velocity-Dependent dynamic rolling resistance
	 *  - Coefficient for the dynamic rolling resistance
	 *  - Coefficient for the power transmission losses due to tire slippage
	 * 
	 * 	- Rolling Friction:
	 * 		- Inclination Angle
	 * 		- Grade (% Grade)
	 * 		- Mass of Bike (Kilograms)
	 * 		- Mass of Rider (Kilograms)
	 * 		- Rolling Resistance Coefficient
	 * 
	 * 	- AirDensity (Kilogram per Meter^3):
	 * 		- Height Above Sea Level (Meters)
	 * 		- Temperature (Degrees Kelvin)
	 * 		- Air Density at Sea Level, 0 Degrees Celsius (Kilograms per Meter^3)
	 * 		- Air Pressure at Sea Level, 0 Degrees Celsius (Pascals)
	 * 
	 * @param windSpeed - in m/s
	 * @return
	 */
	// Remove ws, g, t, hasl; add racecourse obj
	public static double calculateVelocity(Cyclist racer, RaceCourse course, Weather weatherObj, double time) {
		racer.adjustCurrentPower(time,course.getSlope());
		double velocity = 0;
		double dynamicRollingResistanceNRN = VDDROLLINGRESISTANCECOE;
		double windSpeed = weatherObj.getWindSpeedKPH(); //Use m/s
		double airDensity = AIRDENSITYATSL;
		double rollingFriction = calcRollingFriction(racer, course.getSlope());

		Func a = (double w, double cRVN, double cD, double A, double PAD, double fRG, double cM, double p) -> ((Math.pow(w, 3) - Math.pow(cRVN, 3)) / 27) - ((w * (5 * w * cRVN + ((8 * Math.pow(cRVN, 2)) / (A * PAD)) - (6 * fRG))) / (9 * A * PAD)) + ((2 * fRG * cRVN) / (3 * Math.pow((A * PAD), 2))) + (p / (cM * A * PAD));
		Func b = (double w, double cRVN, double cD, double A, double PAD, double fRG, double cM, double p) -> ((2) / (9 * A * PAD)) * (3 * fRG - (4 * w * cRVN) - (Math.pow(w, 2) * A * (PAD / 2)) - ((2 * cRVN) / (A * PAD)));
		
		double aVal = a.apply(windSpeed, dynamicRollingResistanceNRN, racer.getAirDragCoefficent(), racer.getEffectiveDragArea(),
				airDensity, rollingFriction, POWERTRANSLOSSCOE, racer.getRiderFTP());
		double bVal = b.apply(windSpeed, dynamicRollingResistanceNRN, racer.getAirDragCoefficent(), racer.getEffectiveDragArea(),
				airDensity, rollingFriction, POWERTRANSLOSSCOE, racer.getRiderFTP());

		//System.out.println("A: " + aVal);
		//System.out.println("B: " + bVal);
		
		//Velocity for pedaling on flat or inclined terrain
		if(Math.pow(aVal,2) + Math.pow(bVal, 3) >= 0) {
			velocity = Math.cbrt(aVal + Math.sqrt(Math.pow(aVal, 2) + Math.pow(bVal, 3))) + Math.cbrt(aVal - Math.sqrt(Math.pow(aVal, 2) + Math.pow(bVal, 3))) - ((2.0 / 3.0) * (windSpeed + (dynamicRollingResistanceNRN / (racer.getAirDragCoefficent() * racer.getEffectiveDragArea() * airDensity))));
		//Velocity for descending from a hill
		}else if(Math.pow(aVal,2) + Math.pow(bVal, 3) < 0) {
			//Not necessarily accurate, but functions
			velocity = (2 * Math.sqrt(-bVal)) * Math.cos(Math.toRadians((1/3) * aVal/Math.sqrt(Math.pow(-bVal,3)))) - (2/3) * (windSpeed + (dynamicRollingResistanceNRN/(racer.getAirDragCoefficent() * racer.getEffectiveDragArea() * airDensity)));
		}

		
		return velocity;
	}
	
	
	/**
	 * 
	 * @param Temp
	 * @param heightAbvSL
	 * @return
	 */
	/*
	public static double calcAirDensity(double Temp, double heightAbvSL) { 
		return (AIRDENSITYATSL * (373/Temp) * (Math.exp(((-1 * AIRDENSITYATSL) * GRAVITYACCEL) * (heightAbvSL/AIRPRESSUREATSL)))); 
	}
	*/
	
}