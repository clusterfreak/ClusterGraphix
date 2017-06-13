package ClusterTest;

import ClusterCore.FuzzyCMeans;
import ClusterCore.PossibilisticCMeans;

/**
 * 
 * Internal Core Tests
 * 
 * @version 0.1.0 (2016-04-10)
 * @author Thomas Heym
 * 
 */

public class CoreTest {
	
	private static double object[][]={{0.1,0.3},{0.1,0.5},{0.1,0.7},{0.7,0.3},{0.7,0.7},{0.8,0.5},{0.9,0.5}};
	private static double vi[][];
	
	public static void main(String[] args) {
		
		FuzzyCMeans fcm = new FuzzyCMeans(object, 2);
		vi=fcm.determineClusterCenters(true, false);
		System.out.println("FCM Test");
		System.out.println("0: 0.147070835, 0.5");
		System.out.println("1: 0.758778663, 0.5");
		for(int i=0;i<vi.length;i++) System.out.println(i+": "+vi[i][0]+", "+vi[i][1]);
		
		PossibilisticCMeans pcm = new PossibilisticCMeans(object, 2, 1);
		vi=pcm.determineClusterCenters(true, false);
		System.out.println("PCM Test 1. Durchlauf");
		System.out.println("0: 0.102492638, 0.5");
		System.out.println("1: 0.83065648, 0.5");
		for(int i=0;i<vi.length;i++) System.out.println(i+": "+vi[i][0]+", "+vi[i][1]);
		
		pcm = new PossibilisticCMeans(object, 2, 2);
		vi=pcm.determineClusterCenters(true, false);
		System.out.println("PCM Test 2. Durchlauf");
		System.out.println("0: 0.10000244, 0.5");
		System.out.println("1: 0.801756421, 0.5");
		for(int i=0;i<vi.length;i++) System.out.println(i+": "+vi[i][0]+", "+vi[i][1]);
	}
}
