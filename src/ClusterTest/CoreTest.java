package ClusterTest;

import ClusterCore.FuzzyCMeans;

/**
 * 
 * Internal Core Tests
 * 
 * @version 0.1.0 (2016-04-10)
 * @author Thomas Heym
 * 
 */

public class CoreTest {
	
	private static double object[][]={{0.1,0.1}};
	private static double vi[][];
	
	public static void main(String[] args) {
		
		FuzzyCMeans fcm = new FuzzyCMeans(object, 1);
		vi=fcm.determineClusterCenters(true, false);
		
		for(int i=0;i<vi.length;i++) System.out.println(i+": "+vi[i][0]+", "+vi[i][1]);
	}
}
