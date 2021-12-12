package Default;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CyclistSpec {

	@Test
	public void totalMassIs100For90kgCyclistAnd10kgBike(){
		Bike testBike = new Bike("Giant", "TCR Advanced Pro", 2021, 10, 0.00330, "Carbon", "Road Race");
		Cyclist testCyclist = new Cyclist("Jon", 90, 190, 0.5529, 0.96775, 400, "Sprinter", testBike); 
		assertTrue(testCyclist.getTotalMass() == 100);
	}
	
	@Test
	public void FTPIs300WhenSetTo300() {
		Bike testBike = new Bike("Giant", "TCR Advanced Pro", 2021, 10, 0.00330, "Carbon", "Road Race");
		Cyclist testCyclist = new Cyclist("Jon", 90, 190, 0.5529, 0.96775, 400, "Sprinter", testBike); 
		testCyclist.setRiderFTP(300);
		assertTrue(testCyclist.getRiderFTP() == 300);
	}
 
}
