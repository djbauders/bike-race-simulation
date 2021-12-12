/**
 * 
 */
package cmu_baude2d;

/**
 * @author baude2d, dunha2j
 *
 */
public class Cyclist {

	public Double riderWeight;
	public int wattage;
	public String riderStyle;
	public Bike bikeObj;
	
	public Cyclist () {}
	
	public Cyclist (Double riderWeight, int wattage, String riderStyle, Bike bikeObj) { 
		this.riderWeight = riderWeight;
		this.wattage = wattage;
		this.riderStyle = riderStyle;
		this.bikeObj = bikeObj;
	}

	public Bike getBikeObj() {
		return bikeObj;
	}

	public void setBikeObj(Bike bikeObj) {
		this.bikeObj = bikeObj;
	}

	public Double getRiderWeight() {
		return riderWeight;
	}

	public void setRiderWeight(Double riderWeight) {
		this.riderWeight = riderWeight;
	}

	public int getWattage() {
		return wattage;
	}

	public void setWattage(int wattage) {
		this.wattage = wattage;
	}

	public String getRiderStyle() {
		return riderStyle;
	}

	public void setRiderStyle(String riderStyle) {
		this.riderStyle = riderStyle;
	}
	
}
