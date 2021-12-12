/**
 * 
 */
package Default;

/**
 * Object class that is used to represent a cyclist for the program.
 * 
 *  IMPORTANT VAIRABLES FOR THIS CLASS:
 *   - mass: mass of the cyclist, measured in kg. 
 *   - height: height of the cyclist, measured in cm.
 *   - effectiveDragArea: the amount of aerodynamic drag a cyclist must face. This includes the 
 *     bike that they are riding. 
 *   - airDragCoefficent: a number which represents how streamlined the cyclist is.  
 *   - FTP (Functional Threshold Power) : the maximum power a cyclist can output within 20 minutes. 
 *     This is commonly used as a measurement of fitness for the cyclist. 
 *     
 * @author baude2d, dunha2j
 */
public class Cyclist {
	
	public String name; 
	public double mass;
	public double height;
	public double effectiveDragArea;
	public double airDragCoefficent;
	public int FTP;
	public double currentPower;
	public String riderStyle;
	public Bike bikeObj;

	public Cyclist(String name, double mass, double height, double effectiveDragArea, double airDragCoefficent, int FTP, String riderStyle, Bike bikeObj) {
		this.name = name;
		this.mass = mass;
		this.height = height;
		this.effectiveDragArea = effectiveDragArea;
		this.airDragCoefficent = airDragCoefficent;
		this.FTP = FTP;
		this.riderStyle = riderStyle;
		this.bikeObj = bikeObj;
	}
	
	/**
	 * Determines random FTP for a cyclist based on their bodyweight. 
	 * Formula : FTP = Weight in Lbs * 2
	 * Additionally, this can fluctuate between -5 to 10%.
	 */
	public void determineRandomFTP() {
		double weightInLbs = this.mass * 2.2;
		int originalFTP = (int) (weightInLbs * 2);
		int adjustment = (int) (Math.random() * ((originalFTP * 0.1) + (originalFTP * 0.05)) - (originalFTP * 0.05));
		
		this.FTP = originalFTP + adjustment;
	}
	
	/**
	 * Asses what the current power is of a cyclist based on the duration of how long they have been racing. 
	 * @param time - The amount of time a cyclist has spent in the race. 
	 */
	public void adjustCurrentPower(double time, double slope) {
		double ftpPercentage;
		
		//If slope is 10% or greater, racers will coast
		if(slope >= 10) {
			this.currentPower = 0;
			return;
		}
		
		//Sweet Spot : Manageable up to 2hrs
		if(time <= 7200) { //88 to 95% of FTP
			ftpPercentage = (Math.random() * (0.95 - 0.88) + 0.88);
		//Tempo : Manageable up to 8hrs
		}else if(time <= 28800) { //75 to 88% of FTP
			ftpPercentage = (Math.random() * (0.88 - 0.75) + 0.75);
		//Endurance : Manageable indefinitely
		} else { //55 to 75% of FTP
			ftpPercentage = (Math.random() * (0.75 - 0.55) + 0.55);
		}
		
		this.currentPower = FTP * ftpPercentage;
	}
	
	public double getAirDragCoefficent() {
		return airDragCoefficent;
	}

	public void setAirDragCoefficent(double airDragCoefficent) {
		this.airDragCoefficent = airDragCoefficent;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Bike getBikeObj() {
		return bikeObj;
	}

	public void setBikeObj(Bike bikeObj) {
		this.bikeObj = bikeObj;
	}

	public Double getRiderMass() {
		return mass;
	}

	public void setRiderMass(Double riderMass) {
		this.mass = riderMass;
	}

	public int getRiderFTP() {
		return this.FTP;
	}

	public void setRiderFTP(int riderFTP) {
		this.FTP = riderFTP;
	}

	public String getRiderStyle() {
		return riderStyle;
	}

	public void setRiderStyle(String riderStyle) {
		this.riderStyle = riderStyle;
	}
	
	public double getEffectiveDragArea() {
		return effectiveDragArea;
	}

	public void setEffectiveDragArea(double effectiveDragArea) {
		this.effectiveDragArea = effectiveDragArea;
	}

	public double getTotalMass() {
		return bikeObj.getBikeMass() + this.mass;
	}
	
	public double getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(double currentPower) {
		this.currentPower = currentPower;
	}

	
}