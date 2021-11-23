/**
 * 
 */
package Default;

/**
 * @author baude2d, dunha2j
 *
 */
public class Weather {
	
	public String precipitation;
	// public int precInInches;
	public int temperatureInF;
	public String visibility;
	//public int visibilityInYards;
	
	
	public Weather () {}
	
	public Weather (String precipitation, int temperatureInF, String visibility) {
		this.precipitation = precipitation;
		this.temperatureInF = temperatureInF;
		this.visibility = visibility;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	public int getTemperatureInF() {
		return temperatureInF;
	}

	public void setTemperatureInF(int temperatureInF) {
		this.temperatureInF = temperatureInF;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
