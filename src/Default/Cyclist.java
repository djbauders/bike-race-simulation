/**
 * 
 */
package Default;

/**
 * @author baude2d, dunha2j
 *
 */
public class Cyclist {
	
	public String name; 
	public double mass;
	public double height;
	public double effectiveDragArea;
	public int FTP;
	public String riderStyle;
	public Bike bikeObj;

	public Cyclist() {
	}
	
	public Cyclist(double mass, double height, double effectiveDragArea, Bike bikeObj) {
		this.mass = mass;
		this.height = height;
		this.effectiveDragArea = effectiveDragArea;
		this.bikeObj = bikeObj;
	}

	public Cyclist(String name, double mass, double height, double effectiveDragArea, int FTP, String riderStyle, Bike bikeObj) {
		this.name = name;
		this.mass = mass;
		this.height = height;
		this.effectiveDragArea = effectiveDragArea;
		this.FTP = FTP;
		this.riderStyle = riderStyle;
		this.bikeObj = bikeObj;
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

}
