package cluster;
/**
 * Clusterstruktur
 * @version 0.0.2 (28.02.2013)
 * @author Thomas Heym
 */
public class ClusterBot {
	private int number;
	private String name;
	private int points;
	private Punkt2D point[];
	private Punkt2D center;
	private double offset[]={0.0,0.0};
	public ClusterBot(int number,String name,int points,Punkt2D point[],Punkt2D center){
		setNumber(number);
		setName(name);
		setPoints(points);
		setPoint(point);
		setCenter(center);
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
	public Punkt2D[] getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(Punkt2D point[]) {
		this.point = point;
	}
	/**
	 * @return the center
	 */
	public Punkt2D getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(Punkt2D center) {
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
}
