package Default;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SimulationSpec {
	
	@Test
	void velocityWithCylcistAlecAtBaseMeasurementsIs10_1744() throws Exception{
		Weather weatherObj = new Weather("Rain", 0, 0, "Foggy");
		RaceCourse course = new RaceCourse("Test", 100, 0, 1, weatherObj);
		Cyclist Alec = new Cyclist("Alec", 59.87, 150, 0.4387, 0.96759, 303, "Climber",  new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race"));
		
		assertTrue(Simulation.calculateVelocity(Alec, course, 1.0) == 9.764558084711839);
	}

	@Test
	void veloctiyWithCyclistJoshAtSlopeOf8IS4_004627() throws Exception{
		Weather weatherObj = new Weather("Rain", 0, 0, "Foggy");
		RaceCourse course = new RaceCourse("Test", 100, 8, 1, weatherObj);
		Cyclist Josh = new Cyclist("Josh", 106, 155, 0.5478, 0.96759, 414, "Sprinter", new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.00330, "Carbon", "Time Trail"));
		
		assertTrue(Simulation.calculateVelocity(Josh, course, 1) == 4.044627381573982);
	}
	
	@Test
	void velocityWithCyclistBrandonAtSlopeOf8AndWindSpeedOf3MPSIS4_18998() throws Exception{
		Weather weatherObj = new Weather("Rain", 3, 0, "Foggy");
		RaceCourse course = new RaceCourse("Test", 100, 0, 1, weatherObj);
		Cyclist Brandon = new Cyclist("Brandon", 70.76, 158, 0.4738, 0.96759, 345, "Climber", new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race"));
		
		assertTrue(Simulation.calculateVelocity(Brandon, course, 1) == 4.189988825341539);
	}

	@Test
	void rollingFrictionWithCylistAlecIS2_18969() throws Exception {
		Weather weatherObj = new Weather("Rain", 0, 0, "Foggy");
		RaceCourse course = new RaceCourse("Test", 100, 0, 1, weatherObj);
		Cyclist Alec = new Cyclist("Alec", 59.87, 150, 0.4377, 0.96759, 303, "Climber",  new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race"));
		
		assertTrue(Simulation.calcRollingFriction(Alec, course.getSlope()) == 2.18096901);
	}

}
