/**
 * 
 */
package Default;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

@FunctionalInterface
interface Func {
	double apply(double w, double cRVN, double cD, double A, double AD, double fRG, double cM, double p);
	//double apply(double a, double b, double w, double cRVN, double cD, double A, double PAD);
}

/**
 * @author baude2d, dunha2j
 *	The Utility class consists of helper methods to assist in computations necessary for simulation.
 */
public class Utility {

	
	//Current slope
	public static double currSlope;
	//List to index different checkpoints
	public static ArrayList<Integer> cPList = new ArrayList<Integer>();	
	//List to distinguish different slopes at different checkpoints
	public static ArrayList<Double> mList = new ArrayList<Double>();

	//Establish number of checkpoints in the race based on the length in miles
	public static double createCheckPoints(double lengthInMiles) {
		int numCP = (int) lengthInMiles / 2;
		for(int i = 0; i < numCP; i++) {
			cPList.add(i);
		}
		return (int) numCP;
	}
	
	//Generate random slopes; Inclines range from [-10, 10]; Race start always has slope of 0;
	public static void generateRandomSlopes(int cP) {
		Random rand = new Random();
		double randomSlope = ((Math.random() * (-10 - 10)) - 10);
		mList.add(0.0);
		for(int i = 1; i < cP; i++) {
			mList.add(randomSlope);
			
		}
	}
	
	//Check the current slope
	public static double checkSlope(double cP) {
		currSlope = mList.get((int) cP);
		return currSlope;
	}
	
	//Unnecessary calls for checking terrain and hazards;
	//Change Simulation implementation to rc.getTerrain, or generate random terrain, etc.
	public static String checkTerrain(double cP) {
		String t = "None";
		return t;
	}
	
	public static String checkHazards(double cP) {
		String t = "None";
		return t;
	}
	


};
