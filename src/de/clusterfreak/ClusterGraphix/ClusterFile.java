package de.clusterfreak.ClusterGraphix;

/**
 * Available data values for ClusterGraphix
 * 
 * @version 0.1.1 (2016-04-10)
 * @author Thomas Heym
 */
public class ClusterFile {
	private boolean data[] = ClusterData.data;

	/**
	 * Initialize variable
	 * 
	 * @param variable
	 *            variable
	 */
	public void setInitial(String variable) {
		this.setData(variable, ClusterData.getData(variable));
	}

	/**
	 * Set the information of available data
	 * 
	 * @param i
	 *            Index of Variable
	 * @param d
	 *            Data true/false
	 */
	public void setData(int i, boolean d) {
		data[i] = d;
	}

	/**
	 * Get the information of available data
	 * 
	 * @param i
	 *            Index of Variable
	 * @return Data true/false
	 */
	public boolean getData(int i) {
		return data[i];
	}

	/**
	 * Set the information of available data
	 * 
	 * @param s
	 *            Variable
	 * @param d
	 *            Data true/false
	 */
	public void setData(String s, boolean d) {
		data[ClusterData.getIndexInt(s)] = d;
	}

	/**
	 * Get the information of available data
	 * 
	 * @param s
	 *            Variable
	 * @return Data true/false
	 */
	public boolean getData(String s) {
		return data[ClusterData.getIndexInt(s)];
	}
}
