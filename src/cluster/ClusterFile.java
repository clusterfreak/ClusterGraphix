package cluster;
/**
 * Verfügbare Daten
 * @version 0.0.5 (22.09.2015)
 * @author Thomas Heym
 */
public class ClusterFile {
  private boolean data[]=ClusterData.data;

/**
 * Variable initialisieren
 * @param variable
 */
  public void setInitial(String variable){
	  this.setData(variable, ClusterData.getData(variable));
  }
  
/**
 * setzt die Information, ob die Variable Daten hat
 * @param i Index
 * @param d true/false
 */
  public void setData(int i,boolean d){
      data[i]=d;
  }

/**
 * liefert die Information, ob die Variable Daten hat
 * @param i Index
 * @return d
 */
  public boolean getData(int d){
  	return data[d];
  }

/**
 * setzt die Information, ob die Variable Daten hat
 * @param s
 * @param d
 */
  public void setData(String s,boolean d){
	  data[ClusterData.getIndexInt(s)]=d;
  }
  
/**
 * liefert die Information, ob die Variable Daten hat
 * @param s
 * @return
 */
  public boolean getData(String s){
	  return data[ClusterData.getIndexInt(s)];
  }
}
