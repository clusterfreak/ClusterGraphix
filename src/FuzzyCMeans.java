import java.util.Vector;
/**
 * Fuzzy-C-Means (FCM)<P>
 * Berechnung der Klassenzentren einer Punktmenge
 * <PRE>
 * Schritt 1: Initialisierung
 * Schritt 2: Bestimmung der Clusterzentren
 * Schritt 3: Berechnen der neuen Partitionsmatrix
 * Schritt 4: Abbruch oder Wiederholung
 * Schritt 5: optional - Wiederhole Berechnung (Schritte 2 bis 4)</PRE>
 *
 * @version 1.5.4 (28.02.2012)
 * @author Thomas Heym
 */
public class FuzzyCMeans {
/**
 * Anzahl der Cluster, Initialwert 2
 */
  private int cluster = 2;
/**
 * euklidische Abstandsnorm; Exponent
 */
  private static int m = 2;
/**
 * Abbruchschwelle, Initialwert 1.0e-7
 */
  private double e = 1.0e-7;
/**
 * Objekte repräsentieren je 1 Cluster vi
 */
  private double object[][];
/**
 * Klassenzentren vi
 */
  private double vi[][];
/**
 * Zugehörigkeitswerte des k-ten Elements zum i-ten Cluster
 */
  private static double getMik[][];
/**
 * bei false werden nur die Klassenzentren zurückgegeben
 */
  private static boolean path=false;
/**
 * generiert ein FCM-Object aus einer Menge von Punkten
 * @param     object Objekte
 * @param     clusterCount Anzahl der Cluster
 */
  public FuzzyCMeans (double object[][],int clusterCount){
    this.object=object;
    this.cluster=clusterCount;
    this.vi=new double[cluster][2];
  }
/**
 * generiert ein FCM-Object aus einer Menge von Punkten
 * @param     object Objekte
 * @param     clusterCount Anzahl der Cluster
 * @param     e Abbruchschwelle (Standard ist 1.0e-7)
 */
  public FuzzyCMeans (double object[][],int clusterCount,double e){
    this.object=object;
    this.cluster=clusterCount;
    this.vi=new double[cluster][2];
    this.e=e;
  }
/**
 * liefert die Clusterzentren zurück
 * @param      returnPath bestimmt, ob der komplette Suchpfad zurückgeliefert wird. Werte: <code>true</code>, <code>false</code>
 * @return     Clusterzentren und Suchpfad (optional); Die Clusterzentren stehen am Ende.
 */
  public double[][] clusterzentrenBestimmen(boolean returnPath){
    double euklidischerAbstand;
    double mik[][]= new double [object.length][cluster];
    path=returnPath;
    Vector<Punkt2D> viPath = new Vector<Punkt2D>();
// Schritt 1: Initialisierung
    for(int i = 0; i < mik.length; i++){
      for(int k=0;k<cluster;k++){
        mik[i][k] = Math.random();
      }
    }
    do {
// Schritt 2: Bestimmung der Clusterzentren
// --> Schritt 5: optional - Wiederhole Berechnung (Schritte 2 bis 4)
      for(int k=0;k<vi.length;k++) {
        double mikm=0.0,mikm0=0.0,mikm1=0.0,mikms=0.0;
        for(int i=0;i<mik.length;i++) {
          mikm=Math.pow(mik[i][k],m);
          mikm0+=mikm*object[i][0];
          mikm1+=mikm*object[i][1];
          mikms+=mikm;
        }
        vi[k][0]=mikm0/mikms;
        vi[k][1]=mikm1/mikms;
      }
      //Clusterpunkte aufzeichnen
      if(path==true){
        for(int k=0;k<vi.length;k++) viPath.add(new Punkt2D(vi[k][0],vi[k][1]));
      }
// Schritt 3: Berechnen der neuen Partitionsmatrix
      double mik_vorher[][]= new double [mik.length][cluster];
      for(int k=0;k<vi.length;k++) {
        for(int i=0;i<mik.length;i++) {
          double dik=0.0;
          mik_vorher[i][k]=mik[i][k];
          for(int j=0;j<vi.length;j++) {
            dik+=Math.pow(1/(Math.sqrt(Math.pow(object[i][0]-vi[j][0],2)
                                     + Math.pow(object[i][1]-vi[j][1],2))),1/(m-1));
          }
          mik[i][k]=Math.pow(1/(Math.sqrt(Math.pow(object[i][0]-vi[k][0],2)
                                        + Math.pow(object[i][1]-vi[k][1],2))),1/(m-1))
                      /dik;
        }
      }
// euklidischen Abstand berechnen
      euklidischerAbstand=0.0;
      for(int k=0;k<vi.length;k++) {
        for(int i=0;i<mik.length;i++) {
          euklidischerAbstand+=Math.pow((mik[i][k]-mik_vorher[i][k]),2);
        }
      }
      euklidischerAbstand=Math.sqrt(euklidischerAbstand);
    }
// Schritt 4: Abbruch oder Wiederholung
    while (euklidischerAbstand>=e);
    getMik=mik;
    if(path==true){
      double viPathCut[][]=new double [viPath.size()][2];
      for(int k=0;k<viPathCut.length;k++){
        Punkt2D cut = viPath.elementAt(k);
        viPathCut[k][0]=cut.x;
        viPathCut[k][1]=cut.y;
      }
      return viPathCut;
    }
    else {
      return vi;
    }
  }

 /**
 * Liefert die Partitionsmatrix zurück. (Zugehörigkeitswerte des k-ten Elements zum i-ten Cluster)
 * Diese Funktion wird auch aus dem PossibilisticCMeans heraus aufgerufen.
 * @return Partitionsmatrix
 */
  public double[][] getMik(){
    return getMik;
  }

/**
 * Erlaubt die Manipulation der Partintionsmatrix
 * @param setMik
 */
  public static void setMik(double setMik[][]){
    getMik=setMik;
  }

  /**
 * Liefert die Klassenzentren vi zurück
 * @return Klassenzentren
 */
  public double[][] getVi(){
	return vi;
  } 
}