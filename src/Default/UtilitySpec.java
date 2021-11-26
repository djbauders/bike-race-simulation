package Default;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilitySpec {
	
	
	@Test
	void rollingFrictionEquals015655AtMassBike10MassRider90AndGrade4(){
		assertTrue(Utility.calcRollingFriction(10, 90, 4) < 0.15655);
	}
}
