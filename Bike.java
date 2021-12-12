package Default;

/**
 * An object class which holds the important variables to simulate a bicycle.
 * 
 * IMPORTANT VARIABLES OF THE CLASS:
 * 	- bikeMass : The total mass of the bike, measured in kg.
 * 	- rollingResistanceCoe* : The coefficent which represents the rolling 
 * 	  resistance between the bike tire and the surface it is traversing.
 * 	- classification : The type of bike. This grants no tradeoffs in the 
 *    current state of the program. 
 *     
 *    *Currently set to represent firm, road-racing, tires against pavement 
 * @author baude2d, dunha2j
 */
public class Bike{
	
	private String make;
	private String model;
	private int year;
	private double bikeMass;
	private double rollingResistanceCoe;
	private String frameMaterial; 
	private String classification;
	
	public Bike(String make, String model, int year, double bikeMass, double rollingResistanceCoe, String frameMaterial, String classification) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.bikeMass = bikeMass;
		this.rollingResistanceCoe = rollingResistanceCoe;
		this.frameMaterial = frameMaterial;
		this.classification = classification;
	}
	
	public static String getBikeString(Bike bike) {
		String str = "";
		str += (bike.getMake() + " | " + bike.getModel() +
				"\nYear = " + bike.getYear() + "\nMass = " + bike.getBikeMass() +
				"\nRolling Resistance Coefficient = " + bike.getRollingResistanceCoe() + 
				"\nFrame Material = " + bike.getFrameMaterial() + "\nClassification = " +
				bike.getClassification());
		return str;
	}
	
	public String getMake() {
        return make;
    }
	
	public String getModel() {
		return model;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getFrameMaterial() {
		return frameMaterial;
	}
	
	public String getClassification() {
		return classification;
	}

	public double getRollingResistanceCoe() {
		return rollingResistanceCoe;
	}

	public void setRollingResistanceCoe(double rollingResistanceCoe) {
		this.rollingResistanceCoe = rollingResistanceCoe;
	}

	public double getBikeMass() {
		return bikeMass;
	}

	public void setBikeWeight(double bikeMass) {
		this.bikeMass = bikeMass;
	}
}