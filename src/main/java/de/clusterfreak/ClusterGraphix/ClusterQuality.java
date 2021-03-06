package de.clusterfreak.ClusterGraphix;

/**
 * Structure for the cluster quality
 *
 * @author Thomas Heym
 * @version 0.1.1 (2020-07-18
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

    public boolean isPointInTheCluster() {
        return pointInTheCluster;
    }

    public void setPointInTheCluster(boolean pointInTheCluster) {
        this.pointInTheCluster = pointInTheCluster;
    }

    public boolean isEachPointInTheCluster() {
        return eachPointInTheCluster;
    }

    public void setEachPointInTheCluster(boolean eachPointInTheCluster) {
        this.eachPointInTheCluster = eachPointInTheCluster;
    }

    public boolean isAllOtherClusters() {
        return allOtherClusters;
    }

    public void setAllOtherClusters(boolean allOtherClusters) {
        this.allOtherClusters = allOtherClusters;
    }
}
