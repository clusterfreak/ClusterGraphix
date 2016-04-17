package ClusterGraphix;

/**
 * Functions for ClusterBots
 * @version 0.1.0 (04-10-2016)
 * @author Thomas Heym
 */
public class ClusterBotNet {
	private ClusterBot clusterNet[];
	public ClusterBotNet(ClusterBot net[]){
		clusterNet=net;
	}
	public ClusterBot[] getClusterBots(){
		return clusterNet;
	}
}
