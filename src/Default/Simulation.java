/**
 * 
 */
package Default;

import java.util.ArrayList;

/**
 * @author baude2d, dunha2j
 *
 */
public class Simulation extends Utility{
	static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	static ArrayList<Cyclist> cyclistList = new ArrayList<Cyclist>(); 
	
	//final variables used to calculate velocity
	final static double VDDROLLINGRESISTANCECOE = 0.1;
	final static double POWERTRANSLOSSCOE = 1.03;
	final static double AIRDRAGCOE = 0.88; //Bike coe(0.9) + Human coe (0.6)
	final static double AIRDENSITYATSL = 1.293; // kg/m^3
	final static double AIRPRESSUREATSL = 101325; // Pa
	final static double GRAVITYACCEL = 9.81; // m/s^2
	
	//other variables for calculating velocity
	static double rollingResistanceCoe; // Bike tire on asphalt
	static double totalFrontalArea; //m^2
	
	//Testing objects; No simulating yet
	public static void main(String[] args) throws Exception {
		loadDefaultObjects();
		
		/*
		double standardLength = 70.3;
		double checkPoints = createCheckPoints(standardLength);
		String terrain = checkTerrain(checkPoints);
		String hazards = checkHazards(checkPoints);
		Weather weatherObj = new Weather("Rain", 65, "Foggy");
		RaceCourse rc = new RaceCourse(standardLength, checkPoints, 
				checkSlope(checkPoints), terrain, hazards, weatherObj);
		System.out.println("Length: " + rc.getLengthInMiles());
		System.out.println("Num of check points: " + rc.getCheckPoints());
		System.out.println("Current slope: " + rc.getSlope());
		System.out.println("Terrain: " + rc.getTerrain());
		System.out.println("Hazards: " + rc.getHazard());
		System.out.println("Precipitation: " + weatherObj.getPrecipitation());
		System.out.println("Temperature: " + weatherObj.getTemperatureInF());
		System.out.println("Visibility: " + weatherObj.getVisibility());
		*/
	Cyclist testRacer = cyclistList.get(4);
	testRacer.setRiderFTP(300);
	calculateVeloctiy(testRacer,0,0,23.88,0);
	 
	}
	
	public static void loadDefaultObjects(){
		bikeList.add(new Bike("Giant", "TCR Advanced Pro", 2021, 7.6, 0.0330, "Carbon", "Road Race"));
		bikeList.add(new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.0330, "Carbon", "Time Trail"));
		bikeList.add(new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.0330, "Carbon", "Road Race"));
		bikeList.add(new Bike ("LiteSpeed", "Ultimate Gravel", 2021, 8.93, 0.00460, "Titanium", "Gravel"));
		
		cyclistList.add(new Cyclist(90, 190, 0.5529, bikeList.get(0)));
		cyclistList.add(new Cyclist(59.87, 150, 0.4387, bikeList.get(2)));
		cyclistList.add(new Cyclist(83.91, 179, 0.5270, bikeList.get(0)));
		cyclistList.add(new Cyclist(70.76, 158, 0.5758, bikeList.get(3)));
		cyclistList.add(new Cyclist(106, 155, 0.5478, bikeList.get(2)));	
	}
	
	/**
	 * Calculates the rolling friction for a cyclist (fRG), based on the combined weight of them
	 * and their bike and the current grade of the race course. 
	 * @param racer - The racer object that is having their velocity calculated.
	 * @param grade - The current slope of the race course, in percent (%).
	 * @return - The current rolling friction value of the cyclist (fRG).
	 */
	public static double calcRollingFriction(Cyclist racer, double grade) {
		// B = ("Beta") Inclination Angle
		double B = Math.atan(grade/100);
		// Combined Mass of racer and bike
		double totalMass = racer.getTotalMass();
		// Rolling Resistance coefficient of bike tire on race course surface 
		double rollingResistanceCoe = racer.bikeObj.getRollingResistanceCoe();
		
		return (GRAVITYACCEL * (racer.getTotalMass())) * (0.00432 * Math.cos(B) + Math.sin(B));
	}
	
	
	/**
	 * Calculates the current velocity of a cyclist based on the following variables: 
	 * @param windSpeed - in m/s
	 * @return
	 */
	public static double calculateVeloctiy(Cyclist racer, double windSpeed, double grade, double temp, double heightAbvSL) {
		//double inclinationAngle = Math.atan(grade/100);
		double dynamicRollingResistanceNRN = VDDROLLINGRESISTANCECOE; 
		double airDensity = AIRDENSITYATSL;
		double rollingFriction = calcRollingFriction(racer, grade);
		
		  Func a = (double w, double cRVN, double cD, double A, double PAD, double fRG, double cM, double p) -> ((Math.pow(w,3)-Math.pow(cRVN,3))/27) - ((w *(5 * w * cRVN + ((8 * Math.pow(cRVN, 2))/(cD * A * PAD))-(6 * fRG)))/(9 * cD * A * PAD)) + ((2 * fRG * cRVN)/(3 * Math.pow((cD * A * PAD),2))) + (p/(cM * cD * A *PAD));
		  Func b = (double w, double cRVN, double cD, double A, double PAD, double fRG, double cM, double p) -> ((2)/(9*cD*A*PAD)) * (3 * fRG -(4 * w * cRVN) - (Math.pow(w,2) * cD * A * (PAD/2)) -((2*cRVN)/(cD*A*PAD)));
		  
		  double aVal = a.apply(windSpeed, dynamicRollingResistanceNRN, AIRDRAGCOE, racer.getEffectiveDragArea(), airDensity, rollingFriction, POWERTRANSLOSSCOE, racer.getRiderFTP());
		  double bVal = b.apply(windSpeed, dynamicRollingResistanceNRN, AIRDRAGCOE, racer.getEffectiveDragArea(), airDensity, rollingFriction, POWERTRANSLOSSCOE, racer.getRiderFTP());
		  
		  double velocity = Math.cbrt(aVal + Math.sqrt(Math.pow(aVal,2) + Math.pow(bVal,3))) + Math.cbrt(aVal - Math.sqrt(Math.pow(aVal,2) + Math.pow(bVal,3))) - ((2.0/3.0) * (windSpeed + (dynamicRollingResistanceNRN / (AIRDRAGCOE * racer.getEffectiveDragArea() * airDensity))));
				  
		  System.out.println("V: " + velocity);
		  System.out.println("A: " + a.apply(windSpeed, dynamicRollingResistanceNRN, AIRDRAGCOE, racer.getEffectiveDragArea(), airDensity, rollingFriction, POWERTRANSLOSSCOE, racer.getRiderFTP()));
		  System.out.println("B: " + b.apply(windSpeed, dynamicRollingResistanceNRN, AIRDRAGCOE, racer.getEffectiveDragArea(), airDensity, rollingFriction, POWERTRANSLOSSCOE, racer.getRiderFTP()));
		  System.out.println("P: " + airDensity);
		  System.out.println("cRVN: " + dynamicRollingResistanceNRN);
		  
		return velocity;
	}
	
	/*
	public static double calcAirDensity(double Temp, double heightAbvSL) {
		return AIRDENSITYATSL * (373/Temp) * Math.exp(-AIRDENSITYATSL * GRAVITYACCEL * (heightAbvSL/AIRPRESSUREATSL));
	}
	*/
}
