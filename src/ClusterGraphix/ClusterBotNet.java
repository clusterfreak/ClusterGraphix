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
	public boolean clusterQuality(double mik_value){
		boolean quality=false;
		if(clusterNet!=null){
			quality=true;
			for(int i=0;i<clusterNet.length;i++){
				for(int p=0;p<clusterNet[i].getPointMik().length;p++){
					for(int k=0;k<clusterNet[i].getPointMik()[p].length;k++){
						if(k==i){
							if(clusterNet[i].getPointMik()[p][k]<mik_value)quality=false;
						}else{
							if(clusterNet[i].getPointMik()[p][k]>mik_value)quality=false;
						}
					}
				}
			}
		}
		return quality;
	}
}
