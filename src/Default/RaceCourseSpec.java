package Default;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RaceCourseSpec {

	@Test
	void lengthBoundsOutOfOrder() {
		Weather weatherObj = new Weather("Rain", 65, "Foggy");
		
		Assertions.assertThrows(Exception.class, () -> {
			new RaceCourse(-12, 0, 0.0, "", "", weatherObj);
			//rc.getLengthInMiles();
			});
	}
	
	@Test
	void checkPointsGreaterThan0() {
		Weather weatherObj = new Weather("Rain", 65, "Foggy");
		
		Assertions.assertThrows(Exception.class, () -> {
			
			new RaceCourse(20, 0, 0.0, "", "", weatherObj);
		});
	}
	
	@Test
	void stringsNotEmpty() {
		Weather weatherObj = new Weather("Rain", 65, "Foggy");
		
		Assertions.assertThrows(Exception.class, () -> {
			
			new RaceCourse(20, 0, 0.0, "", "", weatherObj);
		});
	}

	/* Test ideas:
	 * 
	 * Establish different hazards
	 * Validate strings ... (Example: Precipitation == Rain, Snow, None, etc.
	 * Length of slopeList (mList) == length of checkPointList (cPList)
	 * Values in mList are in bounds? (We need to establish range for inclines
	 * 
	 * Add more if you have anything in mind!
	 */
	

}
