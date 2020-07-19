package de.clusterfreak.ClusterGraphix;

import de.clusterfreak.ClusterCore.PointPixel;
import de.clusterfreak.ClusterCore.FuzzyCMeans;
import de.clusterfreak.ClusterCore.Point2D;

/**
 * Functions for ClusterBots
 * 
 * @version 0.1.2 (2020-07-18)
 * @author Thomas Heym
 */
public class ClusterBotNet {
	private ClusterBot clusterNet[];

	public ClusterBotNet(ClusterBot net[]) {
		clusterNet = net;
	}

	public ClusterBot[] getClusterBots() {
		return clusterNet;
	}

	/**
	 * PointMik-Value of each point in clusterNet &gt; mik_value for the
	 * associated cluster or &lt; mik_value for all other clusters if minimum 1
	 * Point in every cluster
	 * 
	 * @param mik_value
	 *            mik
	 * @return true/false
	 */
	public boolean clusterQuality(double mik_value) {
		ClusterQuality cq = new ClusterQuality();
		boolean quality = false;
		if (clusterNet != null) {
			quality = true;
			cq.setPointInTheCluster(true);
			cq.setEachPointInTheCluster(true);
			cq.setAllOtherClusters(true);
			for (int i = 0; i < clusterNet.length; i++) {
				if (clusterNet[i].getPoints() == 0 && clusterNet[i].getPointsPixel() == 0)
					cq.setPointInTheCluster(false);
				else {
					for (int p = 0; p < clusterNet[i].getPointMik().length; p++) {
						for (int k = 0; k < clusterNet[i].getPointMik()[p].length; k++) {
							if (k == i) {
								// the mik-value must be gt mik_value for the
								// associated cluster
								if (clusterNet[i].getPointMik()[p][k] < mik_value)
									cq.setEachPointInTheCluster(false);
							} else {
								// the mik-value must be lt mik_value for other
								// clusters
								if (clusterNet[i].getPointMik()[p][k] > mik_value)
									cq.setAllOtherClusters(false);
							}
						}
					}
				}
			}
		}
		if (!cq.isPointInTheCluster()) {
			quality = false;
		} else {
			if (clusterNet.length > 10) {
				if (!cq.isEachPointInTheCluster() || !cq.isAllOtherClusters()) {
					quality = false;
				}
			} else {
				if (!cq.isEachPointInTheCluster() && !cq.isAllOtherClusters()) {

					quality = false;
				}
			}
		}
		return quality;

	}

	/**
	 * Set vi to the center with a fcm
	 */
	public void clusterBotCenter() {
		if (clusterNet != null) {
			for (int i = 0; i < clusterNet.length; i++) {
				double object[][] = new double[clusterNet[i].getPoints()][2];
				double vi[][] = new double[1][2];
				for (int p = 0; p < clusterNet[i].getPoints(); p++) {
					object[p][0] = clusterNet[i].getPoint()[p].x;
					object[p][1] = clusterNet[i].getPoint()[p].y;
				}
				FuzzyCMeans fcm = new FuzzyCMeans(object, 1);
				vi = fcm.determineClusterCenters(true, false);
				Point2D center = new Point2D(0.0, 0.0);
				center.x = vi[0][0];
				center.y = vi[0][1];
				clusterNet[i].setCenter(center);
				clusterNet[i].setCenterPixel(center.toPointPixel(clusterNet[i].getPixelOffset()));
				clusterNet[i].setPointMik(fcm.getMik());
				clusterNet[i].setModified(true);
				fcm = null;
			}
		}
	}

	/**
	 * Get number of the cluster from cluster center pixel point (nothing found
	 * returns 999999999)
	 * 
	 * @param center Center
	 * @return Number of the Cluster
	 */
	public int clusterCenterNumber(PointPixel center) {
		int centerNumber = 999999999;
		if (clusterNet != null) {
			for (int i = 0; i < clusterNet.length; i++) {
				if (clusterNet[i].getCenterPixel().x == center.x && clusterNet[i].getCenterPixel().y == center.y)
					centerNumber = clusterNet[i].getNumber();
			}
		}
		return centerNumber;
	}

	/**
	 * Get number of cluster from cluster center pixel point as a string
	 * 
	 * @param center Center
	 * @return Number of the Cluster
	 */
	public String clusterCenterString(PointPixel center) {
		String string = "";
		if (clusterNet != null) {
			for (int i = 0; i < clusterNet.length; i++) {
				if (clusterNet[i].getCenterPixel().x == center.x && clusterNet[i].getCenterPixel().y == center.y) {
					if (!string.equals(""))
						string = string.concat(", ");
					string = string.concat(String.valueOf(clusterNet[i].getNumber()));
				}
			}
		}
		return string;
	}

	/**
	 * Get number of cluster for object pixel point (nothing found returns
	 * 999999999)
	 * 
	 * @param point Point
	 * @return Number of the Cluster
	 */
	public int clusterCenterPointNumber(PointPixel point) {
		int centerNumber = 999999999;
		if (clusterNet != null) {
			for (int i = 0; i < clusterNet.length; i++) {
				for (int k = 0; k < clusterNet[i].getPointPixel().length; k++) {
					if (clusterNet[i].getPointPixel()[k].x == point.x && clusterNet[i].getPointPixel()[k].y == point.y)
						centerNumber = clusterNet[i].getNumber();
				}
			}
		}
		return centerNumber;
	}

	/**
	 * Get number of cluster for object pixel point as a string
	 * 
	 * @param point Point
	 * @return Number of the Cluster
	 */
	public String clusterCenterPointString(PointPixel point) {
		String string = "";
		if (clusterNet != null) {
			for (int i = 0; i < clusterNet.length; i++) {
				for (int k = 0; k < clusterNet[i].getPointPixel().length; k++) {
					if (clusterNet[i].getPointPixel()[k].x == point.x
							&& clusterNet[i].getPointPixel()[k].y == point.y) {
						if (!string.equals(""))
							string = string.concat(", ");
						string = string.concat(String.valueOf(clusterNet[i].getNumber()));
					}
				}
			}
		}
		return string;
	}
}
