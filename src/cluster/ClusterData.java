package cluster;
/**
 * Datenstruktur
 * @version 0.0.1 (24.09.2015)
 * @author Thomas Heym
 */
public class ClusterData {
	public static int length = 36;
    public static int number[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
    public static String type[] = {"boolean","int","int","boolean","int","int","double","String","double","double","boolean","boolean","int","double","double","boolean","boolean","boolean","boolean","boolean","boolean","boolean","boolean","boolean","String","int","String","String","String","String","String","boolean","ClusterFile","ClusterBot","boolean","boolean"};
    public static String name[] = {"pixel","pixelDim","pixelOffset","pixelOriginal","cluster","objects","object","objectDescription","vi","viPath","pathOption","descriptionDisplay","durchlauf","mik","e","calculate","fuzzyCMeans","possibilisticCMeans","sortCluster","fivtyFivtyJoker","clusterMax","pixelObject","pixelVi","pixelViPath","pixelString","zoom","title","version","jahr","titleString","ready","clusterfreak","clusterFile","clusterBot","error","headUpDisplay"};
    private static String nameCapital[] = {"Pixel","PixelDim","PixelOffset","PixelOriginal","Cluster","Objects","Object","ObjectDescription","Vi","ViPath","PathOption","DescriptionDisplay","Durchlauf","Mik","E","Calculate","FuzzyCMeans","PossibilisticCMeans","SortCluster","FivtyFivtyJoker","ClusterMax","PixelObject","PixelVi","PixelViPath","PixelString","Zoom","Title","Version","Jahr","TitleString","Ready","Clusterfreak","ClusterFile","ClusterBot","Error","HeadUpDisplay"};
    public static String nameExtended[] = {"pixel","pixelDim","pixelOffset","pixelOriginal","cluster","objects","object[][]","objectDescription[]","vi[][]","viPath[][]","pathOption","descriptionDisplay","durchlauf","mik[][]","e","calculate","fuzzyCMeans","possibilisticCMeans","sortCluster","fivtyFivtyJoker","clusterMax","pixelObject[][]","pixelVi[][]","pixelViPath[][]","pixelString[]","zoom","title","version","jahr","titleString","ready","clusterfreak","clusterFile","clusterBot","error","headUpDisplay"};
    public static String indexString2[] = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35"};
    public static boolean data[] = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false};
    
    /**
     * liefert den extended String der Variable
     * @param variable
     * @return
     */
        public static String getNameExtended(String variable){
        	String s="?";
        	for(int i=0;i<name.length;i++)if(name[i].equals(variable)){
        		s=nameExtended[i];
        		break;
        	}
        	if(s.equals("?")){
        			for(int i=0;i<nameCapital.length;i++)if(nameCapital[i].equals(variable)){
                		s=nameExtended[i];
                		break;
                	}
        	}
        	return s;
        }
    
	/**
	 * liefert den String der Variable
	 * @param variable
	 * @return
	 */
	    public static String getName(String variable){
	    	String s="?";
	    	for(int i=0;i<name.length;i++)if(name[i].equals(variable)){
	    		s=name[i];
	    		break;
	    	}
        	if(s.equals("?")){
    			for(int i=0;i<nameCapital.length;i++)if(nameCapital[i].equals(variable)){
            		s=name[i];
            		break;
            	}
    	}	    	
	    	return s;
	    }
	    
	/**
	 * liefert Index der Variable als String (2 Zeichen)
	 * @param variable
	 * @return Index String
	 */
	    public static String getIndexString2(String variable){
	  	  String s="--";
	  	  for(int i=0;i<name.length;i++)if(name[i].equals(variable)){
	  		  s=indexString2[i];
	  	      break;
	  	  }
      	if(s.equals("--")){
  			for(int i=0;i<nameCapital.length;i++)if(nameCapital[i].equals(variable)){
          		s=indexString2[i];
          		break;
          	}
  	}	    		  	  
	  	  return s;
	    }
	
	/**
	 * liefert Index der Variable als Integer
	 * @param variable
	 * @return Index Integer
	 */
	    public static int getIndexInt(String variable){
	  	  int i=99;
	  	  for(int n=0;n<name.length;n++)if(name[n].equals(variable)){
	  		  i=number[n];
	  		  break;
	  	  }
	  	  if(i==99){
   	  	      for(int n=0;n<nameCapital.length;n++)if(nameCapital[n].equals(variable)){
		  		  i=number[n];
		  		  break;
		  	  }
	  	  }
	  	  return i;
	    }
}
