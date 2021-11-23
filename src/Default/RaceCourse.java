/**
 * 
 */
package Default;

/**
 * @author baude2d, dunha2j
 *
 */
public class RaceCourse {

	public double lengthInMiles;
	public double checkPoints;
	public String terrain;
	public String hazard;
	public double slope;
//incline = +, decline = -, flat = 0
	public Weather weatherObj;
	
	public RaceCourse() {}
	
	public RaceCourse(double lengthInMiles, double checkPoints, double slope, String terrain, String hazard, Weather weatherObj) throws Exception {
		if(lengthInMiles <= 0) {
			throw new Exception("Length is less than or equal to 0");
		}
		if(checkPoints <= 0) {
			throw new Exception("Checkpoints less than or equal to 0");
		}
		if(terrain == "" || hazard == "") {
			throw new Exception("Strings are empty");
		}
		this.lengthInMiles = lengthInMiles;
		this.checkPoints = checkPoints;
		this.slope = slope;
		this.terrain = terrain;
		this.hazard = hazard;
		this.weatherObj = weatherObj;
	}
	
	public double getLengthInMiles() {
		return lengthInMiles;
	}

	public void setLengthInMiles(double lengthInMiles) {
		this.lengthInMiles = lengthInMiles;
	}
	
	public double getCheckPoints() {
		return checkPoints;
	}
	
	public void setCheckPoitns(int checkPoints) {
		this.checkPoints = checkPoints;
	}

	public double getSlope() {
		return slope;
	}
	
	public void setSlope(double slope) {
		this.slope = slope;
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
