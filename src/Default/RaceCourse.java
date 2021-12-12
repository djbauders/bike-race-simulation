/**
 * 
 */
package Default;

import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author baude2d
 *
 */
public class RaceCourse {

	public String name;
	public double lengthInKM;
	public double checkPoints;
	private Map<String, Double> hazards;
	public double elevation; // height above sea level (meters)
	public double maxElevation = 100;
	public double slope;

	public Weather weatherObj;
	
	public RaceCourse() throws Exception {}
	
	public RaceCourse(String name, double lengthInKM, double slope, double checkPoints, Map<String, Double> hazards, Weather weatherObj) throws Exception {
		if(lengthInKM <= 0) {
			throw new Exception("Length is less than or equal to 0");
		}
		if(checkPoints <= 0) {
			throw new Exception("Checkpoints less than or equal to 0");
		}
//		if(terrain == "" || hazard == "") {
//			throw new Exception("Strings are empty");
//		}
		this.name = name;
		this.lengthInKM = lengthInKM;
		this.checkPoints = checkPoints;
//		this.elevation = elevation;
		this.slope = slope;
//		this.terrain = terrain;
		this.hazards = hazards;
		this.weatherObj = weatherObj;
	}
	
	/**
	 * Is used to see if a hazard occurs to a Cyclist during a race. 
	 * Currently, if a hazard occurs, the Cyclist is removed from the 
	 * race.
	 * @return - The name of the hazard that occured.
	 */
	public String selectHazard() {
		String hazard = "";
		double sum = 0;

		// Selecting random hazard from the hazardMap.
		for (Map.Entry<String, Double> hazardEntry : hazards.entrySet()) {
			double randomPercent = (Math.random() * (100 + 1) - 1);
			sum += hazardEntry.getValue();
			if (sum > randomPercent) {
				hazard = hazardEntry.getKey();
				break;
			}
		}
		return hazard;
	}
	
	public String getCourseName() {
		return name;
	}
	
	public double getlengthInKM() {
		return lengthInKM;
	}

	public void setlengthInKM(double lengthInKM) {
		this.lengthInKM = lengthInKM;
	}
	
	public double getCheckPoints() {
		return checkPoints;
	}
	
	public void setCheckPoints(int checkPoints) {
		this.checkPoints = checkPoints;
	}
	
	public double getElevation() {
		return elevation;
	}
	
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
	public double getMaxElevation() {
		return maxElevation;
	}
	
	public void setMaxElevation(double maxElevation) {
		this.maxElevation = maxElevation;
	}

	public double getSlope() {
		return slope;
	}
	
	public void setSlope(double slope) {
		this.slope = slope;
	}

	public Map<String, Double> getHazards() {
		return hazards;
	}

	public void setHazards(Map<String,Double> hazards) {
		this.hazards = hazards;
	}

	public Weather getWeatherObj() {
		return weatherObj;
	}

	public void setWeatherObj(Weather weatherObj) {
		this.weatherObj = weatherObj;
	}

	/*
	public ArrayList<Integer> generateHills(double lengthInKM, double checkPoints) {
		//Creating hills and elevations
		  ArrayList<Integer> cPList = new ArrayList<Integer>();
		  ArrayList<Integer> peakList = new ArrayList<Integer>();
		  ArrayList<Integer> elevationList = new ArrayList<Integer>();
//		  Random r = new Random();
//		  int currME = 0;
		  int numHills = (int) ((Math.random() * ((checkPoints / 3) - 1)) + 1);
		  int ascend, descend;
		  
		  //if(x % 2 != 0) ODD; if(x % 2 == 0) Even
		  // (double) lengthInMiles, (double) numCP, (bool) hill, final (double) maxElevation
		  
		  //Fill checkpoints with indexes given numCP
		  for(int i = 0; i < checkPoints; i++) {
			  cPList.add( i);
		  }
		  
		  //Generate peak elevations for each hill
		  for(int i = 0; i < numHills; i++) {
			  peakList.add((int) ((Math.random() * ((maxElevation - 1)) + 1)));
			  System.out.println("Hill num: " + (i + 1)  + "\nPeak elevation: " + peakList.get(i));
		  }
		  
		  for(int i = 0; i < peakList.size(); i++) {
			  ascend = 0;
			  descend = 0;
			  for(int j = ascend; j < peakList.get(i); j++) {
				if(ascend >= peakList.get(i)) {
					
				} else {
				ascend += (int) ((Math.random() * ((10 - 1)) + 1));
				System.out.println("Rand ase: " + ascend + "Hill: " + i);
			  	elevationList.add(ascend);
				}
			  }
			  descend = ascend;
			  for(int k = descend; k > 0; k--) {
//				  if(descend < 0) {
//					  descend = 0;
//				  }
				  if(descend <= 0) {
				  
				  } else {
					  descend -= (int) ((Math.random() * ((10 - 1)) + 1));
					  System.out.println("Random des: " + descend + "Hill: " + i);
					  elevationList.add(descend);
				  }
			  } 
		  }
		
		return elevationList;
	}
	*/
	
	
}