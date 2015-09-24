/**
 * Datenstruktur
 * @version 0.0.1 (24.09.2015)
 * @author Thomas Heym
 */
public class ClusterData {
	private int number[];
	private String[] type[];
	public ClusterData(int number[], String type[]){
		setNumber(number);
		setType(type[])
	}
	/**
	 * @return the number
	 */
	public int[] getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int[] number[]) {
		this.number = number;
	}
	/**
	 * @return the type
	 */
	public String[] getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String[] type[]) {
		this.type = type;
	}
}
