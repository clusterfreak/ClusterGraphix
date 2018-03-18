package de.clusterfreak.ClusterTest;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * 
 * Write sample pbm file with clusters
 * 
 * @version 0.1.0 (2016-04-10)
 * @author Thomas Heym
 * 
 */

public class WritePbm {

	private static int pixelOffset = 1000;
	private static boolean pixelObject[][] = new boolean[pixelOffset][pixelOffset];
	private static String pixelString[] = new String[pixelOffset];

	public static void drawClusterInMatrix(boolean[][] matrix, int x, int y, int ray) {
		double yy, xx;
		double diameter = ray * 2.0;
		double yEdgeLeftBottom = y + diameter;
		double numberOfPixelOfLine;
		boolean pointBorderlineNotFound = true;

		for (int row = 0; row <= ray; row++) {
			yy = ray - row;
			pointBorderlineNotFound = true;
			for (int column = 0; (pointBorderlineNotFound && (column < diameter)); column++) {
				xx = ray - column;
				if (Math.hypot(xx, yy) <= ray) {
					pointBorderlineNotFound = false;
					numberOfPixelOfLine = (diameter - (column * 2.0));
					// the top-part
					writeLine(matrix, x + column, y + row, numberOfPixelOfLine);
					// the bottom-part
					writeLine(matrix, x + column, (int) Math.round(yEdgeLeftBottom - row), numberOfPixelOfLine);
				}
			}
		}
	}

	private static void writeLine(boolean[][] matrix, int xx, int yy, double distance) {
		boolean[] row = matrix[yy];
		for (int i = 0; i < distance; i++) {
			// if (i == 0 || i == 1 || i == distance - 1 || i == distance - 2)
			row[xx + i] = true;
		}
	}

	public static void main(String[] args) {
		try {
			// get a pixelObject
			for (int i = 0; i < pixelOffset; i++) {
				for (int k = 0; k < pixelOffset; k++) {
					pixelObject[i][k] = false;
				}
			}
			drawClusterInMatrix(pixelObject, 681, 281, 20);
			drawClusterInMatrix(pixelObject, 681, 681, 20);
			drawClusterInMatrix(pixelObject, 281, 481, 20);
			drawClusterInMatrix(pixelObject, 481, 481, 20);
			drawClusterInMatrix(pixelObject, 81, 881, 20);
			// make a pixelString
			for (int i = 0; i < pixelOffset; i++) {
				pixelString[i] = "";
				for (int k = 0; k < pixelOffset; k++) {
					if (pixelObject[k][i] == true)
						pixelString[i] += "1";
					else
						pixelString[i] += "0";
				}
			}
			// write the file
			PrintWriter out = new PrintWriter(new FileWriter("1000.pbm"));
			out.println("P1");
			out.println("#created by Clusterfreak");
			out.println(pixelOffset + " " + pixelOffset);
			for (int i = 0; i < pixelString.length; i++) {
				out.println(pixelString[i]);
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
