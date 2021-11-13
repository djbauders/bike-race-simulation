/**
 * 
 */
package cmu_baude2d;

/**
 * @author baude2d, dunha2j
 *
 */
public class RaceCourse {

	public int lengthInMiles;
	public int checkPoints;
	public String terrain;
	public String hazard;
	public double slope;
//incline = +, decline = -, flat = 0
	public Weather weatherObj;
	
	public RaceCourse() {}
	
	public RaceCourse(int lengthInMiles, int checkPoints, String terrain, String hazard, Weather weatherObj) {
		this.lengthInMiles = lengthInMiles;
		this.terrain = terrain;
		this.hazard = hazard;
		this.weatherObj = weatherObj;
	}
	
	public int getLengthInMiles() {
		return lengthInMiles;
	}

	public void setLengthInMiles(int lengthInMiles) {
		this.lengthInMiles = lengthInMiles;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getHazard() {
		return hazard;
	}

	public void setHazard(String hazard) {
		this.hazard = hazard;
	}

	public Weather getWeatherObj() {
		return weatherObj;
	}

	public void setWeatherObj(Weather weatherObj) {
		this.weatherObj = weatherObj;
	}

	
	
}
