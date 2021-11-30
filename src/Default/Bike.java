package Default;

/**
 * @author baude2d, dunha2j
 *
 */
public class Bike{
	
	private String make;
	private String model;
	private int year;
	private double bikeMass;
	private double rollingResistanceCoe;
	private String frameMaterial; 
	private String classification;
	
	//private string tires??

	public Bike () {}
	
	public Bike(String make, String model, int year, double bikeMass, double rollingResistanceCoe, String frameMaterial, String classification) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.bikeMass = bikeMass;
		this.rollingResistanceCoe = rollingResistanceCoe;
		this.frameMaterial = frameMaterial;
		this.classification = classification;
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