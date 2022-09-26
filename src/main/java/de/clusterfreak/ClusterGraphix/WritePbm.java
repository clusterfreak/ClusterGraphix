package de.clusterfreak.ClusterGraphix;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Write sample pbm file with clusters
 *
 * @author Thomas Heym
 * @version 0.1.3 (2022-09-24)
 */

public class WritePbm {
    /**
     * int pixelOffset
     */
    private static final int pixelOffset = 1000;
    /**
     * boolean[][] pixelObject
     */
    private static final boolean[][] pixelObject = new boolean[pixelOffset][pixelOffset];
    /**
     * String[] pixelString
     */
    private static final String[] pixelString = new String[pixelOffset];

    /**
     * drawClusterInMatrix
     * @param matrix matrix
     * @param x x
     * @param y y
     * @param ray ray
     */
    public static void drawClusterInMatrix(boolean[][] matrix, int x, int y, int ray) {
        double yy, xx;
        double diameter = ray * 2.0;
        double yEdgeLeftBottom = y + diameter;
        double numberOfPixelOfLine;
        boolean pointBorderlineNotFound;

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

    /**
     * writeLine
     * @param matrix matrix
     * @param xx xx
     * @param yy yy
     * @param distance distance
     */
    private static void writeLine(boolean[][] matrix, int xx, int yy, double distance) {
        boolean[] row = matrix[yy];
        for (int i = 0; i < distance; i++) {
            // if (i == 0 || i == 1 || i == distance - 1 || i == distance - 2)
            row[xx + i] = true;
        }
    }

    /**
     * public static void main(String[] args)
     * @param args args
     */
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
                    if (pixelObject[k][i])
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
            for (String s : pixelString) {
                out.println(s);
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
