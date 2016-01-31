package cluster;
/**
 * Cluster meta data
 * @version 0.0.1 (24.09.2015)
 * @author Thomas Heym
 */
public class ClusterData {
	public static int length = 36;
    public static int number[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35};
    public static String type[] = {"boolean","int","int","boolean","int","int","double","String","double","double","boolean","boolean","int","double","double","boolean","boolean","boolean","boolean","boolean","boolean","boolean","boolean","boolean","String","int","String","String","String","String","String","boolean","ClusterFile","ClusterBot","boolean","boolean"};
    public static String name[] = {"pixel","pixelDim","pixelOffset","pixelOriginal","cluster","objects","object","objectDescription","vi","viPath","pathOption","descriptionDisplay","durchlauf","mik","e","calculate","fuzzyCMeans","possibilisticCMeans","sortCluster","fivtyFivtyJoker","clusterMax","pixelObject","pixelVi","pixelViPath","pixelString","zoom","title","version","jahr","titleString","ready","clusterfreak","clusterFile","clusterBot","error","headUpDisplay"};
    public static String description[] = {
    		"General importance for conversion and saving",
    		"Pixel resolution",
    		"Pixel size",
    		"Pixel object original indicator",
    		"Quantity/number of clusters",
    		"Quantity/number of objects",
    		"Object matrix",
    		"Object description with associated clusters",
    		"Cluster centers matrix vi",
    		"History of cluster centers detection matrix",
    		"Option to display the history of cluster centers detection",
    		"Option to display the object description with associated clusters",
    		"Number of PCM passes",
    		"Partition matrix (Membership values of the k-th object to the i-th cluster)",
    		"Termination threshold",
    		"Recalculation indicator",
    		"Calculate with Fuzzy-C-Means clustering algorithm",
    		"Calculate with Possibilistic-C-Means clustering algorithm",
    		"Matrixes mik, object, vi will be sorted with objectDescription in cluster sequence",
    		"Object description only for affiliation >0.5",
    		"Object description according to the largest affiliation",
    		"Pixel object matrix",
    		"Pixel cluster centers matrix",
    		"Pixel history of cluster centers detection matrix",
    		"Pixel string memory",
    		"Zoom factor",
    		"Back part of title string",
    		"Program version",
    		"Development year of Program version",
    		"Static part of titel string",
    		"Ready indicator string for status bar",
    		"Pixel image for program start",
    		"Variables",
    		"Cluster bot memory",
    		"Error status from quickCheck()",
    		"Head up display"};
    public static String initial[] = {"true","2","100","true","0","0","null","[0]","[0][2]","null","false","false","1","[0][0]","1.0e-7","false","false","false","true","false","false","[100]","[100]","[100]","0","5","","0.94.9","2015","ClusterGraphics "+"0.94.9"+" ©"+"2015"+" Thomas Heym - "," ready","{...","false","0","false","true"};
    public static String gui[] = {"Menü pixelMode","Data misc","Data misc","","Data misc","Data misc","Data object","Data objectDescription","Data vi","Data viPath","Menü pathOption","Menü descriptionDisplay","Data misc","Data mik","Data misc","Data misc","Data misc","Data misc","Data misc","Data misc","Data misc","pixelObject","pixelVi","pixelViPath","Data pixelString","Data misc","Data misc","Data misc","Data misc","Data misc","Data misc","Data misc","","","Data misc","Data misc"};
    public static String set[] = {"public","public","-","private","public","-","public","private","private","private","public","public","public","private","public","private","private","private","private","private","private","private","private","private","private","private","public","-","-","-","-","-","-","-","private","private"};
    public static String get[] = {"public","public","private","private","public","private","public","private","public","private","public","public","public","public","public","private","private","private","private","private","private","private","private","private","private","private","public","-","-","-","-","-","-","-","private","private"};
    public static String edit[] = {"x","x","-","-","x","-","","-","","","x","x","","","x","x","","","","","","","","","","x","x","-","-","-","-","-","-","-","-","x"};
    public static String save[] = {"x","x","-","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","-","-","-","-","-","x","x"};
    public static String load[] = {"x","x","-","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","x","-","-","-","-","-","x","x"};
    public static String nameCapital[] = {"Pixel","PixelDim","PixelOffset","PixelOriginal","Cluster","Objects","Object","ObjectDescription","Vi","ViPath","PathOption","DescriptionDisplay","Durchlauf","Mik","E","Calculate","FuzzyCMeans","PossibilisticCMeans","SortCluster","FivtyFivtyJoker","ClusterMax","PixelObject","PixelVi","PixelViPath","PixelString","Zoom","Title","Version","Jahr","TitleString","Ready","Clusterfreak","ClusterFile","ClusterBot","Error","HeadUpDisplay"};
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

	/**
	 *     Fehler abfangen !!!!!!!!
	 * @param variable
	 * @return
	 */
	    public static boolean getData(String variable){
	    	int i=getIndexInt(variable);
	    	return ClusterData.data[i];
	    }
}
