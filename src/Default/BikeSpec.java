package Default;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikeSpec {
	
	@Test
	public void bikeMassIs10WhenSetTo10() {
		Bike testBike = new Bike("Giant", "TCR Advanced Pro", 2021, 5, 0.00330, "Carbon", "Road Race");
		testBike.setBikeWeight(10);
		assertTrue(testBike.getBikeMass() == 10);
	}
	
	@Test
	public void rollingResistanceCoeIs2WhenSetTo2() {
		Bike testBike = new Bike("Giant", "TCR Advanced Pro", 2021, 5, 0.00330, "Carbon", "Road Race");
		testBike.setRollingResistanceCoe(2);;
		assertTrue(testBike.getRollingResistanceCoe() == 2);
	}
}
