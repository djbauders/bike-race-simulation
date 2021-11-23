/**
 * 
 */
package Default;

/**
 * @author baude2d, dunha2j
 *
 */
public class Cyclist {

	public Double riderWeight;
	public int riderFTP;
	public String riderStyle;
	public Bike bikeObj;
	
	public Cyclist () {}
	
	public Cyclist (Double riderWeight, int riderFTP, String riderStyle, Bike bikeObj) { 
		this.riderWeight = riderWeight;
		this.riderFTP = riderFTP;
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

	public int getRiderFTP() {
		return this.riderFTP;
	}

	public void setRiderFTP(int riderFTP) {
		this.riderFTP = riderFTP;
	}

	public String getRiderStyle() {
		return riderStyle;
	}

	public void setRiderStyle(String riderStyle) {
		this.riderStyle = riderStyle;
	}
	
	public double getTotalWeight() {
		return bikeObj.getBikeWeight() + this.riderWeight;
	}
	
}
