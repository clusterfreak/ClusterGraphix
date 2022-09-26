package de.clusterfreak.ClusterGraphix;

import de.clusterfreak.ClusterCore.Point2D;
import de.clusterfreak.ClusterCore.PointPixel;

/**
 * Cluster structure
 *
 * @author Thomas Heym
 * @version 0.1.3 (2022-09-24)
 */
public class ClusterBot {
    /**
     * number
     */
    private int number;
    /**
     * name
     */
    private String name;
    /**
     * pixelOffset
     */
    private int pixelOffset;
    /**
     * points
     */
    private int points;
    /**
     * point
     */
    private Point2D[] point;
    /**
     * center
     */
    private Point2D center;
    /**
     * pointsPixel
     */
    private int pointsPixel = 0;
    /**
     * pointPixel
     */
    private PointPixel[] pointPixel;
    /**
     * centerPixel
     */
    private PointPixel centerPixel;
    /**
     * pointMik
     */
    private double[][] pointMik;
    /**
     * offset
     */
    private double[] offset = {0.0, 0.0};
    /**
     * modified
     */
    private boolean modified;
    /**
     * radius
     */
    private double radius;

    /**
     * Generate ClusterBot
     * @param number number
     * @param name name
     * @param pixelOffset pixelOffset
     * @param points points
     * @param point point
     * @param center center
     * @param pointMik paintMik
     */
    public ClusterBot(int number, String name, int pixelOffset, int points, Point2D[] point, Point2D center,
                      double[][] pointMik) {
        setNumber(number);
        setName(name);
        setPixelOffset(pixelOffset);
        setPoints(points);
        setPoint(point);
        setCenter(center);
        setPointsPixel(0);
        PointPixel pp = new PointPixel(0, 0);
        setCenterPixel(pp);
        setPointMik(pointMik);
        setModified(false);
        setRadius();
    }

    /**
     * Add Point pixel format
     *
     * @param pp PointPixel
     */
    public void addPointPixel(PointPixel pp) {
        PointPixel[] ppNew;
        if (getPointsPixel() == 0) {
            ppNew = new PointPixel[1];
            ppNew[0] = pp;
            setPointPixel(ppNew);
            setPointsPixel(1);
        } else {
            boolean newPoint = true;
            ppNew = new PointPixel[getPointPixel().length + 1];
            for (int i = 0; i < getPointPixel().length; i++) {
                ppNew[i] = getPointPixel()[i];
                if ((ppNew[i].x == pp.x) && (ppNew[i].y == pp.y))
                    newPoint = false;
            }
            if (newPoint) {
                ppNew[getPointPixel().length] = pp;
                setPointsPixel(getPointPixel().length + 1);
                setPointPixel(ppNew);
            }
        }
        setRadius();
    }

    /**
     * Return number
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set number
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get points
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set points
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Get point 2D
     * @return the point
     */
    public Point2D[] getPoint() {
        return point;
    }

    /**
     * Set point 2D
     * @param point the point to set
     */
    public void setPoint(Point2D[] point) {
        this.point = point;
    }

    /**
     * Get center
     * @return the center
     */
    public Point2D getCenter() {
        return center;
    }

    /**
     * Set center
     * @param center the center to set
     */
    public void setCenter(Point2D center) {
        this.center = center;
    }

    /**
     * Get offset
     * @return the offset
     */
    public double[] getOffset() {
        return offset;
    }

    /**
     * Set Offset
     * @param offset the offset to set
     */
    public void setOffset(double[] offset) {
        this.offset = offset;
    }

    /**
     * Get pointsPixel
     * @return the pointsPixel
     */
    public int getPointsPixel() {
        return pointsPixel;
    }

    /**
     * Set pointsPixel
     * @param pointsPixel the pointsPixel to set
     */
    public void setPointsPixel(int pointsPixel) {
        this.pointsPixel = pointsPixel;
    }

    /**
     * Get pointPixel
     * @return the pointPixel
     */
    public PointPixel[] getPointPixel() {
        return pointPixel;
    }

    /**
     * Set pointPixel
     * @param pointPixel the pointPixel to set
     */
    public void setPointPixel(PointPixel[] pointPixel) {
        this.pointPixel = pointPixel;
    }

    /**
     * Get centerPixel
     * @return the centerPixel
     */
    public PointPixel getCenterPixel() {
        return centerPixel;
    }

    /**
     * Set centerPixel
     * @param centerPixel the centerPixel to set
     */
    public void setCenterPixel(PointPixel centerPixel) {
        this.centerPixel = centerPixel;
    }

    /**
     * Get pointMik
     * @return the pointMik
     */
    public double[][] getPointMik() {
        return pointMik;
    }

    /**
     * Set pointMik
     * @param pointMik2 the pointMik to set
     */
    public void setPointMik(double[][] pointMik2) {
        this.pointMik = pointMik2;
    }

    /**
     * Get pixelOffset
     * @return the pixelOffset
     */
    public int getPixelOffset() {
        return pixelOffset;
    }

    /**
     * Set pixelOffset
     * @param pixelOffset the pixelOffset to set
     */
    public void setPixelOffset(int pixelOffset) {
        this.pixelOffset = pixelOffset;
    }

    /**
     * Get modified
     * @return the modified
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Set modified
     * @param modified the modified to set
     */
    public void setModified(boolean modified) {
        this.modified = modified;
    }

    /**
     * Get radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set and calculate radius
     */
    private void setRadius() {
        double r = 0.0;
        for (int i = 0; i < points; i++) {
            double s = Math.sqrt(Math.pow(point[i].x - center.x, 2) + Math.pow(point[i].y - center.y, 2));
            if (s > r)
                r = s;
        }
        this.radius = r;
    }
}
