package de.clusterfreak.ClusterGraphix;

/**
 * Structure for the cluster quality
 *
 * @author Thomas Heym
 * @version 0.1.2 (2022-09-24)
 */
public class ClusterQuality {
    /**
     * minimum 1 point in every cluster
     */
    private boolean pointInTheCluster = false;
    /**
     * PointMik-Value of each point in clusterNet &gt; mik_value for the
     * associated cluster
     */
    private boolean eachPointInTheCluster = false;
    /**
     * PointMik-Value of each point in clusterNet &lt; mik_value for all other
     * clusters if minimum 1 Point in every cluster
     */
    private boolean allOtherClusters = false;
    /**
     * isPointInTheCluster
     * @return pointInTheCluster
     */
    public boolean isPointInTheCluster() {
        return pointInTheCluster;
    }

    /**
     * setPointInTheCluster
     * @param pointInTheCluster pointInTheCluster
     */
    public void setPointInTheCluster(boolean pointInTheCluster) {
        this.pointInTheCluster = pointInTheCluster;
    }

    /**
     * isEachPointInTheCluster
     * @return eachPointInTheCluster eachPointInTheCluster
     */
    public boolean isEachPointInTheCluster() {
        return eachPointInTheCluster;
    }

    /**
     * setEachPointInTheCluster
     * @param eachPointInTheCluster eachPointInTheCluster
     */
    public void setEachPointInTheCluster(boolean eachPointInTheCluster) {
        this.eachPointInTheCluster = eachPointInTheCluster;
    }

    /**
     * isAllOtherClusters
     * @return allOtherClusters allOtherClusters
     */
    public boolean isAllOtherClusters() {
        return allOtherClusters;
    }

    /**
     * etAllOtherClusters
     * @param allOtherClusters allOtherClusters
     */
    public void setAllOtherClusters(boolean allOtherClusters) {
        this.allOtherClusters = allOtherClusters;
    }
}
