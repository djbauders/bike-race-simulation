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
	public double windSpeedKPH;
	public double temperatureInF;
	public String visibility;
	//public int visibilityInYards;
	
	
	public Weather () {}
	
	public Weather (String precipitation, double windSpeedKPH, double temperatureInF, String visibility) {
		this.precipitation = precipitation;
		this.windSpeedKPH = windSpeedKPH;
		this.temperatureInF = temperatureInF;
		this.visibility = visibility;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}
	
	public double getWindSpeedKPH() {
		return windSpeedKPH;
	}
	
	public void setWindSpeedKPH(double windSpeedKPH) {
		this.windSpeedKPH = windSpeedKPH;
	}

	public double getTemperatureInF() {
		return temperatureInF;
	}

	public void setTemperatureInF(double temperatureInF) {
		this.temperatureInF = temperatureInF;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
