package ClusterGraphix;

import ClusterCore.Point2D;
import ClusterCore.PointPixel;

/**
 * Cluster structure
 * @version 0.1.1 (04-10-2016)
 * @author Thomas Heym
 */
public class ClusterBot {
	private int number;
	private String name;
	private int points;
	private Point2D point[];
	private Point2D center;
	private int pointsPixel=0;
	private PointPixel pointPixel[];
	private PointPixel centerPixel;
	private double offset[]={0.0,0.0};
	public ClusterBot(int number,String name,int points,Point2D point[],Point2D center){
		setNumber(number);
		setName(name);
		setPoints(points);
		setPoint(point);
		setCenter(center);
		setPointsPixel(0);
		PointPixel pp = new PointPixel(0,0);
		setCenterPixel(pp);
	}
	/**
	 * Add Point pixel format
	 * @param pp PointPixel
	 */
	public void addPointPixel(PointPixel pp){
		PointPixel ppNew[];
		if(getPointsPixel()==0){
			ppNew=new PointPixel[1];
			ppNew[0]=pp;
			setPointPixel(ppNew);
			setPointsPixel(1);
		}
		else{
			boolean newPoint=true;
			ppNew=new PointPixel[getPointPixel().length+1];
			for(int i=0;i<getPointPixel().length;i++){
				ppNew[i]=getPointPixel()[i];
				if((ppNew[i].x==pp.x)&&(ppNew[i].y==pp.y))newPoint=false;
			}
			if(newPoint){
				ppNew[getPointPixel().length]=pp;
				setPointsPixel(getPointPixel().length+1);
				setPointPixel(ppNew);
			}
		}
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * @return the point
	 */
	public Point2D[] getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(Point2D point[]) {
		this.point = point;
	}
	/**
	 * @return the center
	 */
	public Point2D getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(Point2D center) {
		this.center = center;
	}
	/**
	 * @return the offset
	 */
	public double[] getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(double offset[]) {
		this.offset = offset;
	}
	/**
	 * @return the pointsPixel
	 */
	public int getPointsPixel() {
		return pointsPixel;
	}
	/**
	 * @param pointsPixel the pointsPixel to set
	 */
	public void setPointsPixel(int pointsPixel) {
		this.pointsPixel = pointsPixel;
	}
	/**
	 * @return the pointPixel
	 */
	public PointPixel[] getPointPixel() {
		return pointPixel;
	}
	/**
	 * @param pointPixel the pointPixel to set
	 */
	public void setPointPixel(PointPixel pointPixel[]) {
		this.pointPixel = pointPixel;
	}
	/**
	 * @return the centerPixel
	 */
	public PointPixel getCenterPixel() {
		return centerPixel;
	}
	/**
	 * @param centerPixel the centerPixel to set
	 */
	public void setCenterPixel(PointPixel centerPixel) {
		this.centerPixel = centerPixel;
	}
}
