/**
 * 
 */
package cmu_baude2d;

/**
 * @author baude2d, dunha2j
 *
 */
public class Simulation extends Utility{
	//Testing objects; No simulating yet
	public static void main(String[] args) throws Exception {
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
	}
	
}
