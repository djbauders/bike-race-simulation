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
	public double windSpeedMPS;
	public double tempInCelsius;
	public String visibility;
	
	
	public Weather () {}
	
	public Weather (String precipitation, double windSpeedMPS, double tempInCelsius, String visibility) {
		this.precipitation = precipitation;
		this.windSpeedMPS = windSpeedMPS;
		this.tempInCelsius = tempInCelsius;
		this.visibility = visibility;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}
	
	public double getWindSpeedMPS() {
		return windSpeedMPS;
	}
	
	public void setWindSpeedMPS(double windSpeedMPS) {
		this.windSpeedMPS = windSpeedMPS;
	}

	public double getTempInCelsius() {
		return tempInCelsius;
	}

	public void setTempInCelsius(double tempInCelsius) {
		this.tempInCelsius = tempInCelsius;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
