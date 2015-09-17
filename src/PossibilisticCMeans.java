import java.util.Vector;
/**
 * Possivilistic-C-Means (PCM) <P>
 * Berechnung der Klassenzentren einer Punktmenge
 * <PRE>
 * Schritt 1: Initialisierung
 * Schritt 2: Bestimmung der Clusterzentren
 * Schritt 3: Berechnen der neuen Partitionsmatrix inkl. Berechnung ni
 * Schritt 4: Abbruch oder Wiederholung
 * Schritt 5: optional - Wiederhole Berechnung (Schritte 2 bis 4)</PRE>
 *
 * @version 1.1.4 (28.02.2012)
 * @author Thomas Heym
 * @see FuzzyCMeans
 */
public class PossibilisticCMeans {
/**
 * Anzahl der Cluster, Initialwert 2
 */
  private int cluster = 2;
/**
 * euklidische Abstandsnorm; Exponent, Initialwert 2
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
 * npcm
 */
  private double ni[];
/**
 * bei false werden nur die Klassenzentren zurückgegeben
 */
  private static boolean path=false;
/**
 * Berechnung von ni durchführen
 */
  private static boolean ni_calc = true;
/**
 * Anzahl der Durchläufe PCM
 */
  private int durchlauf=1;
/**
 * Zugehörigkeitswerte des k-ten Elements zum i-ten Cluster
 */
  private static double getMik[][];
  
/**
 * generiert ein PCM-Object aus einer Menge von Punkten
 * @param     object Objekte
 * @param     clusterCount Anzahl der Cluster
 * @param     durchlauf Anzahl der der PCM-Durchläufe bei der Bestimmung der Clusterzentren
 * @see       FuzzyCMeans
 */
  public PossibilisticCMeans (double object[][],int clusterCount,int durchlauf){
    this.object=object;
    this.cluster=clusterCount;
    this.vi=new double[cluster][2];
    this.ni=new double[cluster];
    this.durchlauf=durchlauf;
  }
/**
 * generiert ein PCM-Object aus einer Menge von Punkten
 * @param     object Objekte
 * @param     clusterCount Anzahl der Cluster
 * @param     durchlauf Anzahl der der PCM-Durchläufe bei der Bestimmung der Clusterzentren
 * @param     e Abbruchschwelle (Standard ist 1.0e-7)
 * @see       FuzzyCMeans
 */
  public PossibilisticCMeans (double object[][],int clusterCount,int durchlauf,double e){
    this.object=object;
    this.cluster=clusterCount;
    this.vi=new double[cluster][2];
    this.ni=new double[cluster];
    this.durchlauf=durchlauf;
    this.e=e;
  }
/**
 * liefert die Clusterzentren zurück
 * @param      returnPath bestimmt, ob der komplette Suchpfad zurückgeliefert wird. Werte: <code>true</code>, <code>false</code>
 * @return     Clusterzentren und Suchpfad (optional); Die Clusterzentren stehen am Ende.
 */
  public double[][] clusterzentrenBestimmen(boolean returnPath){
    double euklidischerAbstand;
    path=returnPath;
    Vector<Punkt2D> viPath = new Vector<Punkt2D>();
// Schritt 1: Initialisierung
    FuzzyCMeans fcm;
    if(e==1.0e-7){
      fcm=new FuzzyCMeans(object,cluster);
    }
    else{
      fcm=new FuzzyCMeans(object,cluster,e);
    }
    double getViPath[][]=fcm.clusterzentrenBestimmen(true);
    for(int v=0;v<getViPath.length;v++) viPath.add(new Punkt2D(getViPath[v][0],getViPath[v][1]));
    vi=fcm.getVi();
    double mik[][]=fcm.getMik();
    do { //while (durchlauf>0)
      durchlauf--;
      ni_calc=true;
      do { //while (euklidischerAbstand>=e)
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
        double miks[]= new double[vi.length];
        if(ni_calc==true) {
          // ni berechnen (Abstand vom Clusterzentrum bis zum Punkt mit dem Zugehörigkeitswert 0.5 zum aktuellen Cluster)
          // ni = 0
          for(int i=0;i<vi.length;i++) {
            ni[i]=0.0;
            miks[i]=0.0;
          }
          // ni = Summe mik²*dik²
          for(int i=0;i<mik.length;i++) {
            for(int k=0;k<vi.length;k++) {
              double dik=Math.sqrt(Math.pow(object[i][0]-vi[k][0],2)+Math.pow(object[i][1]-vi[k][1],2));
              ni[k]+=Math.pow(Math.pow(mik[i][k],2),2)*Math.pow(dik,2);
              miks[k]+=Math.pow(mik[i][k],2);
            }
          }
          // ni = Summe(mik²*dik²) / Summe mik²
          for(int i=0;i<vi.length;i++) {
            ni[i]/=miks[i];
          }
          ni_calc=false;
        }
        for(int k=0;k<vi.length;k++) {
          for(int i=0;i<mik.length;i++) {
            mik_vorher[i][k]=mik[i][k];
            mik[i][k]=1/(1+(Math.pow(Math.sqrt(Math.pow(object[i][0]-vi[k][0],2)
                                             + Math.pow(object[i][1]-vi[k][1],2)),2))/ni[k]);
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
    }
    while (durchlauf>0);
    getMik=mik;
    //Wertrückgabe
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
 * erlaubt die Manipulation der Partitionsmatrix
 * @param setMik Partitionsmatrix
 */
  public static void setMik(double setMik[][]){
    getMik=setMik;
  }
}
