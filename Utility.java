/**
 * 
 */
package cmu_baude2d;

import java.util.ArrayList;
import java.util.Random;

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

	public static double createCheckPoints(double lengthInMiles) {
		int numCP = (int) lengthInMiles / 2;
		for(int i = 0; i < numCP; i++) {
			cPList.add(i);
		}
		return (int) numCP;
	}
	
	public static void generateRandomSlopes(int cP) {
		Random rand = new Random();
		double randomSlope = ((Math.random() * (-10 - 10)) - 10);
		mList.add(0.0);
		for(int i = 1; i < cP; i++) {
			mList.add(randomSlope);
			
		}
	}
	
	public static double checkSlope(double cP) {
		currSlope = mList.get((int) cP);
		return currSlope;
	}
	
	public static String checkTerrain(double cP) {
		String t = "None";
		return t;
	}
	
	public static String checkHazards(double cP) {
		String t = "None";
		return t;
	}
}
