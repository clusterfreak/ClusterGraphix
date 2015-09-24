/**
 * Datenstruktur
 * @version 0.0.5 (22.09.2015)
 * @author Thomas Heym
 */
public class ClusterFile { 
  private int length=36;
  private boolean pixel=false;
  private boolean pixelDim=false;
  private boolean pixelOffset=false;
  private boolean pixelOriginal=false;
  private boolean cluster=false;
  private boolean objects=false;
  private boolean object=false;
  private boolean objectDescription=false;
  private boolean vi=false;
  private boolean viPath=false;
  private boolean pathOption=false;
  private boolean descriptionDisplay=false;
  private boolean durchlauf=false;
  private boolean mik=false;
  private boolean e=false;
  private boolean calculate=false;
  private boolean fuzzyCMeans=false;
  private boolean possibilisticCMeans=false;
  private boolean sortCluster=false;
  private boolean fivtyFivtyJoker=false;
  private boolean clusterMax=false;
  private boolean pixelObject=false;
  private boolean pixelVi=false;
  private boolean pixelViPath=false;
  private boolean pixelString=false;
  private boolean zoom=false;
  private boolean title=false;
  private boolean version=false;
  private boolean jahr=false;
  private boolean titleString=false;
  private boolean ready=false;
  private boolean clusterfreak=false;
  private boolean clusterFile=false;
  private boolean clusterBot=false;
  private boolean error=false;
  private boolean headUpDisplay=false;

/**
 * Liefert die Anzahl der Variablen  
 * @return Anzahl der Variablen
 */
  public int getLength(){
	  return length;
  }
  
/**
 * Liefert den Datentyp der Variable als String  
 * @param t Nummer der Variable
 * @return Datentyp als String
 */
  public String getType(int t){
	  String s="";
	  switch(t){
	  case 0:s="boolean";break;
	  case 1:s="int";break;
	  case 2:s="int";break;
	  case 3:s="boolean";break;
	  case 4:s="int";break;
	  case 5:s="int";break;
	  case 6:s="double";break;
	  case 7:s="String";break;
	  case 8:s="double";break;
	  case 9:s="double";break;
	  case 10:s="boolean";break;
	  case 11:s="boolean";break;
	  case 12:s="int";break;
	  case 13:s="double";break;
	  case 14:s="double";break;
	  case 15:s="boolean";break;
	  case 16:s="boolean";break;
	  case 17:s="boolean";break;
	  case 18:s="boolean";break;
	  case 19:s="boolean";break;
	  case 20:s="boolean";break;
	  case 21:s="boolean";break;
	  case 22:s="boolean";break;
	  case 23:s="boolean";break;
	  case 24:s="String";break;
	  case 25:s="int";break;
	  case 26:s="String";break;
	  case 27:s="String";break;
	  case 28:s="String";break;
	  case 29:s="String";break;
	  case 30:s="String";break;
	  case 31:s="boolean";break;
	  case 32:s="ClusterFile";break;
	  case 33:s="ClusterBot";break;
	  case 34:s="boolean";break;
	  case 35:s="boolean";break;
	  }
	  return s;
  }
  
/**
 * liefert den Namen der Variable
 * @param n Index
 * @param more mehr Information
 * @return Name der Variable
 */
  public String getName(int n, boolean more){
	  String s="";
	  switch(n){
	  case 0:s="pixel";break;
	  case 1:s="pixelDim";break;
	  case 2:s="pixelOffset";break;
	  case 3:s="pixelOriginal";break;
	  case 4:s="cluster";break;
	  case 5:s="objects";break;
	  case 6:if(more)s="object[][]";else s="object";break;
	  case 7:if(more)s="objectDescription[]";else s="objectDescription";break;
	  case 8:if(more)s="vi[][]";else s="vi";break;
	  case 9:if(more)s="viPath[][]";else s="viPath";break;
	  case 10:s="pathOption";break;
	  case 11:s="descriptionDisplay";break;
	  case 12:s="durchlauf";break;
	  case 13:if(more)s="mik[][]";else s="mik";break;
	  case 14:s="e";break;
	  case 15:s="calculate";break;
	  case 16:s="fuzzyCMeans";break;
	  case 17:s="possibilisticCMeans";break;
	  case 18:s="sortCluster";break;
	  case 19:s="fivtyFivtyJoker";break;
	  case 20:s="clusterMax";break;
	  case 21:if(more)s="pixelObject[][]";else s="pixelObject";break;
	  case 22:if(more)s="pixelVi[][]";else s="pixelVi";break;
	  case 23:if(more)s="pixelViPath[][]";else s="pixelViPath";break;
	  case 24:if(more)s="pixelString[]";else s="pixelString";break;
	  case 25:s="zoom";break;
	  case 26:s="title";break;
	  case 27:s="version";break;
	  case 28:s="jahr";break;
	  case 29:s="titleString";break;
	  case 30:s="ready";break;
	  case 31:s="clusterfreak";break;
	  case 32:s="clusterFile";break;
	  case 33:s="clusterBot";break;
	  case 34:s="error";break;
	  case 35:s="headUpDisplay";break;
	  }
	  return s;
  }

/**
 * liefert Index der Variable als String (2 Zeichen)
 * @param variable
 * @return Index String
 */
    public String getIndexString2(String variable){
  	  String s="--";
  	  switch(variable){
  	  case "pixel":s="00";break;
  	  case "pixelDim":s="01";break;
  	  case "pixelOffset":s="02";break;
  	  case "pixelOriginal":s="03";break;
  	  case "cluster":s="04";break;
  	  case "objects":s="05";break;
  	  case "object":s="06";break;
  	  case "objectDescription":s="07";break;
  	  case "vi":s="08";break;
  	  case "viPath":s="09";break;
  	  case "pathOption":s="10";break;
  	  case "descriptionDisplay":s="11";break;
  	  case "durchlauf":s="12";break;
  	  case "mik":s="13";break;
  	  case "e":s="14";break;
  	  case "calculate":s="15";break;
  	  case "fuzzyCMeans":s="16";break;
  	  case "possibilisticCMeans":s="17";break;
  	  case "sortCluster":s="18";break;
  	  case "fivtyFivtyJoker":s="19";break;
  	  case "clusterMax":s="20";break;
  	  case "pixelObject":s="21";break;
  	  case "pixelVi":s="22";break;
  	  case "pixelViPath":s="23";break;
  	  case "pixelString":s="24";break;
  	  case "zoom":s="25";break;
  	  case "title":s="26";break;
  	  case "version":s="27";break;
  	  case "jahr":s="28";break;
  	  case "titleString":s="29";break;
  	  case "ready":s="30";break;
  	  case "clusterfreak":s="31";break;
  	  case "clusterFile":s="32";break;
  	  case "clusterBot":s="33";break;
  	  case "error":s="34";break;
  	  case "headUpDisplay":s="35";break;
  	  }
  	  return s;
    }  

    /**
     * liefert Index der Variable als Integer
     * @param variable
     * @return Index Integer
     */
        public int getIndexInt(String variable){
      	  int i=99;
      	  switch(variable){
      	  case "pixel":i=0;break;
      	  case "pixelDim":i=1;break;
      	  case "pixelOffset":i=2;break;
      	  case "pixelOriginal":i=3;break;
      	  case "cluster":i=4;break;
      	  case "objects":i=5;break;
      	  case "object":i=6;break;
      	  case "objectDescription":i=7;break;
      	  case "vi":i=8;break;
      	  case "viPath":i=9;break;
      	  case "pathOption":i=10;break;
      	  case "descriptionDisplay":i=11;break;
      	  case "durchlauf":i=12;break;
      	  case "mik":i=13;break;
      	  case "e":i=14;break;
      	  case "calculate":i=15;break;
      	  case "fuzzyCMeans":i=16;break;
      	  case "possibilisticCMeans":i=17;break;
      	  case "sortCluster":i=18;break;
      	  case "fivtyFivtyJoker":i=19;break;
      	  case "clusterMax":i=20;break;
      	  case "pixelObject":i=21;break;
      	  case "pixelVi":i=22;break;
      	  case "pixelViPath":i=23;break;
      	  case "pixelString":i=24;break;
      	  case "zoom":i=25;break;
      	  case "title":i=26;break;
      	  case "version":i=27;break;
      	  case "jahr":i=28;break;
      	  case "titleString":i=29;break;
      	  case "ready":i=30;break;
      	  case "clusterfreak":i=31;break;
      	  case "clusterFile":i=32;break;
      	  case "clusterBot":i=33;break;
      	  case "error":i=34;break;
      	  case "headUpDisplay":i=35;break;
      	  }
      	  return i;
        }  
        
/**
 * setzt die Information, ob die Variable Daten hat
 * @param d Index
 * @param data true/false
 */
  public void setData(int d,boolean data){
      switch(d){
  	  case 0:setPixel(data);break;
  	  case 1:setPixelDim(data);break;
  	  case 2:setPixelOffset(data);break;
  	  case 3:setPixelOriginal(data);break;
  	  case 4:setCluster(data);break;
  	  case 5:setObjects(data);break;
  	  case 6:setObject(data);break;
  	  case 7:setObjectDescription(data);break;
  	  case 8:setVi(data);break;
  	  case 9:setViPath(data);break;
  	  case 10:setPathOption(data);break;
  	  case 11:setDescriptionDisplay(data);break;
  	  case 12:setDurchlauf(data);break;
  	  case 13:setMik(data);break;
  	  case 14:setE(data);break;
  	  case 15:setCalculate(data);break;
  	  case 16:setFuzzyCMeans(data);break;
  	  case 17:setPossibilisticCMeans(data);break;
  	  case 18:setSortCluster(data);break;
  	  case 19:setFivtyFivtyJoker(data);break;
  	  case 20:setClusterMax(data);break;
  	  case 21:setPixelObject(data);break;
  	  case 22:setPixelVi(data);break;
  	  case 23:setPixelViPath(data);break;
  	  case 24:setPixelString(data);break;
  	  case 25:setZoom(data);break;
  	  case 26:setTitle(data);break;
  	  case 27:setVersion(data);break;
  	  case 28:setJahr(data);break;
  	  case 29:setTitleString(data);break;
  	  case 30:setReady(data);break;
  	  case 31:setClusterfreak(data);break;
  	  case 32:setClusterFile(data);break;
  	  case 33:setClusterBot(data);break;
  	  case 34:setError(data);break;
  	  case 35:setHeadUpDisplay(data);break;
  	  }
  }

/**
   * liefert die Information, ob die Variable Daten hat
   * @param d Index
   * @return Daten
   */
    public boolean getData(int d){
  	  boolean b=false;
      switch(d){
  	  case 0:b=isPixel();break;
  	  case 1:b=isPixelDim();break;
  	  case 2:b=isPixelOffset();break;
  	  case 3:b=isPixelOriginal();break;
  	  case 4:b=isCluster();break;
  	  case 5:b=isObjects();break;
  	  case 6:b=isObject();break;
  	  case 7:b=isObjectDescription();break;
  	  case 8:b=isVi();break;
  	  case 9:b=isViPath();break;
  	  case 10:b=isPathOption();break;
  	  case 11:b=isDescriptionDisplay();break;
  	  case 12:b=isDurchlauf();break;
  	  case 13:b=isMik();break;
  	  case 14:b=isE();break;
  	  case 15:b=isCalculate();break;
  	  case 16:b=isFuzzyCMeans();break;
  	  case 17:b=isPossibilisticCMeans();break;
  	  case 18:b=isSortCluster();break;
  	  case 19:b=isFivtyFivtyJoker();break;
  	  case 20:b=isClusterMax();break;
  	  case 21:b=isPixelObject();break;
  	  case 22:b=isPixelVi();break;
  	  case 23:b=isPixelViPath();break;
  	  case 24:b=isPixelString();break;
  	  case 25:b=isZoom();break;
  	  case 26:b=isTitle();break;
  	  case 27:b=isVersion();break;
  	  case 28:b=isJahr();break;
  	  case 29:b=isTitleString();break;
  	  case 30:b=isReady();break;
  	  case 31:b=isClusterfreak();break;
  	  case 32:b=isClusterFile();break;
  	  case 33:b=isClusterBot();break;
  	  case 34:b=isError();break;
  	  case 35:b=isHeadUpDisplay();break;
  	  }
  	  return b;
    }

/**
 * @return the pixel
 */
public boolean isPixel(){return pixel;}
/**
 * @param pixel the pixel to set
 */
public void setPixel(boolean pixel){this.pixel=pixel;}
/**
 * @return the pixelDim
 */
public boolean isPixelDim(){return pixelDim;}
/**
 * @param pixelDim the pixelDim to set
 */
public void setPixelDim(boolean pixelDim){this.pixelDim=pixelDim;}
/**
 * @return the pixelOffset
 */
public boolean isPixelOffset(){return pixelOffset;}
/**
 * @param pixelOffset the pixelOffset to set
 */
public void setPixelOffset(boolean pixelOffset){this.pixelOffset=pixelOffset;}
/**
 * @return the pixelOriginal
 */
public boolean isPixelOriginal(){return pixelOriginal;}
/**
 * @param pixelOriginal the pixelOriginal to set
 */
public void setPixelOriginal(boolean pixelOriginal){this.pixelOriginal=pixelOriginal;}
/**
 * @return the cluster
 */
public boolean isCluster(){return cluster;}
/**
 * @param cluster the cluster to set
 */
public void setCluster(boolean cluster){this.cluster=cluster;}
/**
 * @return the objects
 */
public boolean isObjects(){return objects;}
/**
 * @param objects the objects to set
 */
public void setObjects(boolean objects){this.objects=objects;}
/**
 * @return the object
 */
public boolean isObject(){return object;}
/**
 * @param object the object to set
 */
public void setObject(boolean object){this.object = object;}
/**
 * @return the objectDescription
 */
public boolean isObjectDescription(){return objectDescription;}
/**
 * @param objectDescription the objectDescription to set
 */
public void setObjectDescription(boolean objectDescription){this.objectDescription=objectDescription;}
/**
 * @return the vi
 */
public boolean isVi(){return vi;}
/**
 * @param vi the vi to set
 */
public void setVi(boolean vi){this.vi=vi;}
/**
 * @return the viPath
 */
public boolean isViPath(){return viPath;}
/**
 * @param viPath the viPath to set
 */
public void setViPath(boolean viPath){this.viPath=viPath;}
/**
 * @return the pathOption
 */
public boolean isPathOption(){return pathOption;}
/**
 * @param pathOption the pathOption to set
 */
public void setPathOption(boolean pathOption){this.pathOption=pathOption;}
/**
 * @return the descriptionDisplay
 */
public boolean isDescriptionDisplay(){return descriptionDisplay;}
/**
 * @param descriptionDisplay the descriptionDisplay to set
 */
public void setDescriptionDisplay(boolean descriptionDisplay){this.descriptionDisplay=descriptionDisplay;}
/**
 * @return the durchlauf
 */
public boolean isDurchlauf(){return durchlauf;}
/**
 * @param durchlauf the durchlauf to set
 */
public void setDurchlauf(boolean durchlauf){this.durchlauf=durchlauf;}
/**
 * @return the mik
 */
public boolean isMik(){return mik;}
/**
 * @param mik the mik to set
 */
public void setMik(boolean mik){this.mik=mik;}
/**
 * @return the e
 */
public boolean isE(){return e;}
/**
 * @param e the e to set
 */
public void setE(boolean e){this.e=e;}
/**
 * @return the calculate
 */
public boolean isCalculate(){return calculate;}
/**
 * @param calculate the calculate to set
 */
public void setCalculate(boolean calculate){this.calculate=calculate;}
/**
 * @return the fuzzyCMeans
 */
public boolean isFuzzyCMeans(){return fuzzyCMeans;}
/**
 * @param fuzzyCMeans the fuzzyCMeans to set
 */
public void setFuzzyCMeans(boolean fuzzyCMeans){this.fuzzyCMeans=fuzzyCMeans;}
/**
 * @return the possibilisticCMeans
 */
public boolean isPossibilisticCMeans(){return possibilisticCMeans;}
/**
 * @param possibilisticCMeans the possibilisticCMeans to set
 */
public void setPossibilisticCMeans(boolean possibilisticCMeans){this.possibilisticCMeans=possibilisticCMeans;}
/**
 * @return the sortCluster
 */
public boolean isSortCluster(){return sortCluster;}
/**
 * @param sortCluster the sortCluster to set
 */
public void setSortCluster(boolean sortCluster){this.sortCluster=sortCluster;}
/**
 * @return the fivtyFivtyJoker
 */
public boolean isFivtyFivtyJoker(){return fivtyFivtyJoker;}
/**
 * @param fivtyFivtyJoker the fivtyFivtyJoker to set
 */
public void setFivtyFivtyJoker(boolean fivtyFivtyJoker){this.fivtyFivtyJoker=fivtyFivtyJoker;}
/**
 * @return the clusterMax
 */
public boolean isClusterMax(){return clusterMax;}
/**
 * @param clusterMax the clusterMax to set
 */
public void setClusterMax(boolean clusterMax){this.clusterMax=clusterMax;}
/**
 * @return the pixelObject
 */
public boolean isPixelObject(){return pixelObject;}
/**
 * @param pixelObject the pixelObject to set
 */
public void setPixelObject(boolean pixelObject){this.pixelObject=pixelObject;}
/**
 * @return the pixelVi
 */
public boolean isPixelVi(){return pixelVi;}
/**
 * @param pixelVi the pixelVi to set
 */
public void setPixelVi(boolean pixelVi){this.pixelVi=pixelVi;}
/**
 * @return the pixelViPath
 */
public boolean isPixelViPath(){return pixelViPath;}
/**
 * @param pixelViPath the pixelViPath to set
 */
public void setPixelViPath(boolean pixelViPath){this.pixelViPath=pixelViPath;}
/**
 * @return the pixelString
 */
public boolean isPixelString(){return pixelString;}
/**
 * @param pixelString the pixelString to set
 */
public void setPixelString(boolean pixelString){this.pixelString=pixelString;}
/**
 * @return the zoom
 */
public boolean isZoom(){return zoom;}
/**
 * @param zoom the zoom to set
 */
public void setZoom(boolean zoom){this.zoom=zoom;}
/**
 * @return the title
 */
public boolean isTitle(){return title;}
/**
 * @param title the title to set
 */
public void setTitle(boolean title){this.title=title;}
/**
 * @return the version
 */
public boolean isVersion(){return version;}
/**
 * @param version the version to set
 */
public void setVersion(boolean version){this.version=version;}
/**
 * @return the jahr
 */
public boolean isJahr(){return jahr;}
/**
 * @param jahr the jahr to set
 */
public void setJahr(boolean jahr){this.jahr=jahr;}
/**
 * @return the titleString
 */
public boolean isTitleString(){return titleString;}
/**
 * @param titleString the titleString to set
 */
public void setTitleString(boolean titleString){this.titleString=titleString;}
/**
 * @return the ready
 */
public boolean isReady(){return ready;}
/**
 * @param ready the ready to set
 */
public void setReady(boolean ready){this.ready=ready;}
/**
 * @return the clusterfreak
 */
public boolean isClusterfreak(){return clusterfreak;}
/**
 * @param clusterfreak the clusterfreak to set
 */
public void setClusterfreak(boolean clusterfreak){this.clusterfreak=clusterfreak;}
/**
 * @return the clusterFile
 */
public boolean isClusterFile(){return clusterFile;}
/**
 * @param clusterFile the clusterFile to set
 */
public void setClusterFile(boolean clusterFile){this.clusterFile=clusterFile;}
/**
 * @return the clusterBot
 */
public boolean isClusterBot() {return clusterBot;}
/**
 * @param clusterBot the clusterBot to set
 */
public void setClusterBot(boolean clusterBot) {this.clusterBot = clusterBot;}

/**
 * @return the error
 */
public boolean isError() {return error;}
/**
 * @param error the error to set
 */
public void setError(boolean error) {this.error = error;
}
/**
 * @return the headUpDisplay
 */
public boolean isHeadUpDisplay() {return headUpDisplay;
}
/**
 * @param headUpDisplay the headUpDisplay to set
 */
public void setHeadUpDisplay(boolean headUpDisplay) {	this.headUpDisplay = headUpDisplay;
}
}
