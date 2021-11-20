package cmu_baude2d;

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

	
	

}
