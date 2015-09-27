import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.String;
import java.lang.Runnable;
import java.util.Iterator;
import java.util.GregorianCalendar;
import java.text.DateFormat;
//java.awt.*
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
//javax.swing.*
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
//javax.xml*
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.events.Characters;
//import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;
/**
 * Cluster Graphics<P>
 * Grafische Anzeige von Punktemengen und Klassenzentren
 * @version 0.94.9 (22.09.2015)
 * @author Thomas Heym
 */
public class ClusterGraphics extends JPanel implements ActionListener{
  private static final long serialVersionUID = -3221752010018099832L;
/**
 * Pixelmodus ja oder nein, zentrale Bedeutung für Umwandlung und Speicherung 
 */
  private boolean pixel=true;
/**
 * Grad der Pixelauflösung, Initialwert 2
 */
  private int pixelDim=2;
/**
 * Größe der Pixelauflösung; Initialwert 100
 */
  private int pixelOffset=100;
/**
 * Pixelobjekt oder Doubleobjekt hat Originaldaten
 */
  private boolean pixelOriginal=true;
/**
 * Anzahl der Cluster, Initialwert 0
 */
  private int cluster=0;
/**
 * Anzahl der Objekte, Intitialwert 0
 */
  private int objects=0;
/**
 * Objektmatrix, Punkte
 */
  private double object[][]=null;
/**
 * Beschriftung der Punkte mit der zugehörigen Klasse (Objekte mit zugehörigen Clustern)
 */
  private String objectDescription[]=null;
/**
 * Klassenzentren vi
 */
  private double vi[][]=null;
/**
 * Verlauf der Findung der Klassenzentren
 */
  private double viPath[][]=null;
/**
 * Option für den Suchpfad der Findung der Klassenzentren, Initialwert false
 */
  private boolean pathOption=false;
/**
 * Option für Beschriftung der Punkte mit der zugehörigen Klasse, Initialwert false
 */
  private boolean descriptionDisplay=false;
/**
 * Anzahl der Durchläufe PCM, Initialwert 1
 */
  private int durchlauf=1;
/**
 * Zugehörigkeitswerte des k-ten Elements zum i-ten Cluster
 */
  private double mik[][]=null;
/**
 * Abbruchschwelle, Initialwert 1.0e-7
 */
  private double e = 1.0e-7;
/**
 * Wenn true, dann wurden Werte verändert und eine Neuberechnung wird ermöglicht.
 */
  private boolean calculate=false;
/**
 * Berechnung nach dem Fuzzy-C-Means Algorithmus
 */
  private boolean fuzzyCMeans=false;
/**
 * Berechnung nach dem Possibilistic-C-Means Algorithmus
 */
  private boolean possibilisticCMeans=false;
/**
 * legt fest, ob mik, object, vi auf Basis von objectDescription in Cluster-Reihenfolge sortiert werden sollen
 */
  private boolean sortCluster=true;
/**
 * Beschriftung mit Zugehörigkeit >0.5
 */
  private boolean fivtyFivtyJoker=false;
/**
 * Beschriftung nach größter Zugehörigkeit
 */
  private boolean clusterMax=false;
/**
 * Pixelmatrix für Darstellung der Objekte
 */
  private boolean pixelObject[][]=null;
/**
 * Pixelmatrix für Darstellung der Klassenzentren
 */
  private boolean pixelVi[][]=null;
/**
 * Pixelmatrix für Darstellung des Suchpfades de Klassenzentren
 */
  private boolean pixelViPath[][]=null;
/**
 * nimmt die Pixelgrafik auf
 */
  private String pixelString[]=null;
/**
 * Zoomfaktor
 */
  private int zoom=5;
/**
 *  hinteren Teil des Textes in der Titelzeile
 */
  private String title="";
/**
 * version of Cluster Graphics
 */
  private static String version = "0.94.9";
/**
 * Entwicklungsjahr der Version von Cluster Graphics
 */
  private static String jahr = "2015";
/**
 *  fester vorderer Teil des Textes in der Titelzeile
 */
  private static String titleString = "ClusterGraphics "+version+" ©"+jahr+" Thomas Heym - ";
/**
 * Fertigmeldung für Statuszeile
 */
  private static String ready=" ready";
/**
 * Pixelgrafik für Startbild
 */
  private static String clusterfreak[] = {
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000001010101010100000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000001101011111111111110101000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000101010101010101010101010001000000000000000000000000000000000000",
	  "0000000000000000000000000000000001111111111111111111111111111111101000000000000000000000000000000000",
	  "0000000000000000000000000000000101010101010101011101110111011101110100000000000000000000000000000000",
	  "0000000000000000000000000000001110111011111111111110101010101010101010100000000000000000000000000000",
	  "0000000000000000000000000000010101010101010101010101010101010001000100010000000000000000000000000000",
	  "0000000000000000000000000011111011101111111111101010101010101010101010101000000000000000000000000000",
	  "0000000000000000000000000101010101010101010101010101010101010101010001000100000000000000000000000000", 
	  "0000000000000000000000001110101110111011101010101010101010101010101010100000000000000000000000000000",
	  "0000000000000000000000010101010101010101010101010101010101010101000100000000000000000000000000000000",
	  "0000000000000000000001111110111011111110111011101110101010101010100000000010101000000000000000000000",
	  "0000000000000000000011010101010101010101010101010101010101010100000000000000000000000000000000000000",
	  "0000000000000000000110101011101110111011101110101010101010100000000010101010001010000000000000000000",
	  "0000000000000000000101010101010101010101010101010101010100000000000000000000000000000000000000000000",
	  "0000000000000000001111101110111111111111111011101110110000000000101010101010101010100000000000000000",
	  "0000000000000000010101010101010101010101010101010100000000000000000000000000000000000000000000000000",
	  "0000000000000000111010101011111110111011101110111000000000001010001010100010001000101000000000000000",
	  "0000000000000001010101010101010101010101010101000000000000000000000000000000000000000000000000000000",
	  "0000000000000001101010101111111111111111111110000000000010101010101010101010101010101000000000000000",
	  "0000000000000001010101011101010101010101010000000000000000000000000000000000000000000000000000000000",
	  "0000000000000010101010111111111111111011100000000000101010100010101000101010001010100010000000000000",
	  "0000000000000111010101010101010101010100000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000110101011111111111111111100000000000010101010101010101010101010101010101010100000000000",
	  "0000000000001101010111010101010101010000000000000100000000000000000000000000000000000000000000000000",
	  "0000000000011010101011111111111110100000000000101010101000101010001010100010101000100010000000000000",
	  "0000000000010001000101010101010100000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000011010101111111111111000000000000010101010101010101010101010101010101010101010100000000000",
	  "0000000000010101010111011101010000000000000001000100010000000000000000000000000000000000000000000000",
	  "0000000000101010111111111111100000000000001010101010101010101010101000101010001010100010101000000000",
	  "0000000000110001011101010101000000000000000100000001000000000000000000000000000000000000000000000000",
	  "0000000000101011111111111100000000000000101010101010101010101010101010101010101010101010101000000000",
	  "0000000001100101110111011000000000000000010001000100010000000000000000000000000000000000000000000000",
	  "0000000001101011111111110000000000001010101010101010101010101010001010100010101000101010001000000000",
	  "0000000001010011011101100000000000000001000000010000000000000000000000000000000000000000000000000000",
	  "0000000001101111111111000000000000101010101010101010101010101010101010101010101010101010101000000000",
	  "0000000001010101110110000000000000000100010001000100010001000000000000000000000000000000000000000000",
	  "0000000001101111111000000000000000101010101010101010101010101010101010101010001010100010101000000000",
	  "0000000011010111011000000000000000010000000100000001000000010000000000000000000000000000000000000000",
	  "0000000011101111110000000000000010101010101010101010101010101010101010101010101010101010101000000000",
	  "0000000011011111100000000000000001000100010001000100010001000100000000000000000000000000000000000000",
	  "0000000011111111000000000000001010101010101010101010101010101010101010100010101000101010001000000000",
	  "0000000001010110000000000000000100000001000000010000000000000000000000000000000000000000000000000000",
	  "0000000011111110000000000000101010101010101010101010101010101010101010101010101010101010101000000000",
	  "0000000011111100000000000000010001000100010001000100010001000100010001000000000000000000000000000000",
	  "0000000011111000000000000000101010101010101010101010101010101010101010101010101010100010101000000000",
	  "0000000011110000000000000001000100010001000100000001000000010000000100000000000000000000000000000000",
	  "0000000001110000000000000000101010101010101010101010101010101010101010101010101010101010101000000000",
	  "0000000001110000000000000000010001000100010001000100010001000100010001000100010000000000000000000000",
	  "0000000001110000000000000010101010101010101010101010101010101010101010101010101000101010001000000000",
	  "0000000001100000000000000001000100010001000000010000000100000000000000000000000000000000000000000000",
	  "0000000001100000000000000010101010101010101010101010101010101010101010101010101010101010101000001000",
	  "0000000000100000000000000000010001000100010001000100010001000100010001000100010001000100010001000000",
	  "0000000000000000000000000000101010101010101010101010101010101010101010101010101010101000000010100000",
	  "0000000000000000000000000001000100010001000100010001000000010000000100000001000000000000000100000000",
	  "0000000000000000000000000000101010101010101010101010101010101010101010101010101000000000101000000000",
	  "0000000000000000000000000000010001000100010001000100010001000100010001000100000000000101010000000000",
	  "0000000000000000000000000000001010101010101010101010101010101010101010100000000000001010100000000000",
	  "0000000000000000000000000000000100010001000000010000000100000001000000000000000000010101000000000000",
	  "0000000000000000000000000000000000101010101010101010101010101010000000000000001010101010000000000000",
	  "0000000000000000000000000000000000000100010001000100010000000000000000000000010101010100000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000010101010101010000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000010101010101010000000000000000",
	  "0000000000000010000000000000000000000000000000000000000000000000001011101010101010111000000000000000",
	  "0000000000000001100000000000000000000000000000000000000000000001010101010101010101110000000000000000",
	  "0000000000000000111000000000000000000000000000000000000000001011101010101010101011100000000000000000",
	  "0000000000000000011101000000000000000000000000000000000101010101010101010101010101010000000000000000",
	  "0000000000000000011111111000000000000000000000001111111011111110111011101110101111100000000000000000",
	  "0000000000000000001111011101110111010101110101010101010101010101010101010101010111000000000000000000",
	  "0000000000000000000111111111111111111111111111111111101110111011101110101010111110000000000000000000",
	  "0000000000000000000001110111010101010101010101010101010101010101010101010101011100000000000000000000",
	  "0000000000000000000001111111111111111111111111111111111111101110111011101111111000000000000000000000",
	  "0000000000000000000000010101110111011101010101010101010101010101010101011101110000000000000000000000",
	  "0000000000000000000000011011111111111111111111111011101110111011101010111111100000000000000000000000",
	  "0000000000000000000000000101010101010101010101010101010101010101010101010101000000000000000000000000",
	  "0000000000000000000000000110101111111111111111111111111111111110111111111110000000000000000000000000",
	  "0000000000000000000000000001010101011101110101010101010101010101110111010000000000000000000000000000",
	  "0000000000000000000000000000111010101010111111111111101111111111111111100000000000000000000000000000",
	  "0000000000000000000000000000000101010101010101010101010101010101010101000000000000000000000000000000",
	  "0000000000000000000000000000000011101110111011101110111111111111111000000000000000000000000000000000",
	  "0000000000000000000000000000000000011101010101010101010101010101010000000000000000000000000000000000",
	  "0000000000000000000000000000000000000111101010111011101110111010000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000010101010101010101000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000011111100000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
	  "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"};
/**
 * Variablen
 */
  private ClusterFile clusterFile= new ClusterFile();
/**
 * Bot Speicher  
 */
  private ClusterBot[] clusterBot;
/**
 * Fehler aus quickCheck()  
 */
  private boolean error;
/**
 * Head Up Display
 */
  private boolean headUpDisplay;
/**
 * Hauptfenster
 */
  private JFrame f;
  private JLabel l=new JLabel();
/**
 * Menu Hauptfenster
 */
  private JMenuBar clusterMenu=new JMenuBar();
  private JMenu clusterMenuFile=new JMenu("File");
  private JMenuItem clusterMenuFileOpen=new JMenuItem("Open");
  private JMenuItem clusterMenuFileSave=new JMenuItem("Save");
  private JMenuItem clusterMenuFileValidate=new JMenuItem("Validate");
  private JMenuItem clusterMenuFileImport=new JMenuItem("Import");
  private JMenuItem clusterMenuFileExport=new JMenuItem("Export");
  private JMenu clusterMenuView=new JMenu("View");
  private JCheckBoxMenuItem clusterMenuViewPixelMode=new JCheckBoxMenuItem("PixelMode",true);
  private JCheckBoxMenuItem clusterMenuViewPathOption=new JCheckBoxMenuItem("pathOption",false);
  private JCheckBoxMenuItem clusterMenuViewDescriptionDisplay=new JCheckBoxMenuItem("descriptionDisplay",false);
  private JCheckBoxMenuItem clusterMenuViewHeadUpDisplay=new JCheckBoxMenuItem("headUpDisplay",true);
  private JMenuItem clusterMenuViewRefresh=new JMenuItem("refresh");
  private JMenu clusterMenuZoom=new JMenu("Zoom");
  private JMenuItem clusterMenuZoomDefault=new JMenuItem("default");
  private JMenuItem clusterMenuZoomOut=new JMenuItem("+");
  private JMenuItem clusterMenuZoomIn=new JMenuItem("-");
  private JMenu clusterMenuData=new JMenu("Data");
  private JMenuItem clusterMenuDataShow=new JMenuItem("Show");
  private JMenuItem clusterMenuDataCheck=new JMenuItem("Check");
  private JMenu clusterMenuSet=new JMenu("Set");
  private JMenuItem clusterMenuSetPixelDim=new JMenuItem("pixelDim");
  private JMenuItem clusterMenuSetCluster=new JMenuItem("cluster");
  private JMenuItem clusterMenuSetE=new JMenuItem("e");
  private JRadioButtonMenuItem clusterMenuSetFuzzyCMeans=new JRadioButtonMenuItem("FuzzyCMeans",false);
  private JRadioButtonMenuItem clusterMenuSetPossibilisticCMeans = new JRadioButtonMenuItem("PossibilisticCMeans",false);
  private ButtonGroup clusterButtonGroupSet1=new ButtonGroup();
  private JMenuItem clusterMenuSetDurchlauf=new JMenuItem("durchlauf");
  private JCheckBoxMenuItem clusterMenuSetSortCluster=new JCheckBoxMenuItem("SortCluster",true);
  private JRadioButtonMenuItem clusterMenuSetFivtyFivtyJoker=new JRadioButtonMenuItem("FivtyFivtyJoker",false);
  private JRadioButtonMenuItem clusterMenuSetClusterMax=new JRadioButtonMenuItem("ClusterMax",false);
  private ButtonGroup clusterButtonGroupSet2=new ButtonGroup();
  private JMenuItem clusterMenuSetTitle=new JMenuItem("title");
  private JMenu clusterMenuTools=new JMenu("Tools");
  private JMenuItem clusterMenuToolsError=new JMenuItem("Error");
  private JMenuItem clusterMenuToolsAddPoint=new JMenuItem("Add Point");
  private JMenuItem clusterMenuToolsDelete=new JMenuItem("Delete Cluster");
  private JMenuItem clusterMenuToolsDeleteNotAssigned=new JMenuItem("Delete not assigned");
  private JMenuItem clusterMenuToolsClearAll=new JMenuItem("Clear All");
  private JMenu clusterMenuHelp=new JMenu("?");
  private JMenuItem clusterMenuHelpInfo=new JMenuItem("Info");
  private JToolBar clusterToolBar=new JToolBar("ClusterGraphics");
  private JButton clusterButtonCalculate=new JButton("Calculate");
  private JButton clusterButtonError=new JButton("Error");
  private ThreadGroup clusterThreadGroup = new ThreadGroup( "CusterTreadGroup" );
  private JFileChooser clusterChooser=new JFileChooser();
/**
 * FileFilter für XML-Dateien
 */
  private javax.swing.filechooser.FileFilter clusterFileFilterXML=new javax.swing.filechooser.FileFilter(){
	  public boolean accept(File filen) {
	      if (filen.isDirectory()) return true;
	      return filen.getName().toLowerCase().endsWith(".xml");
	  }
	  public String getDescription () { return "ClusterGraphics-Dateien (*.xml)"; }  
  };
/**
 * FileFilter für PBA-Dateien
 */
  private javax.swing.filechooser.FileFilter clusterFileFilterPBM=new javax.swing.filechooser.FileFilter(){
    public boolean accept(File filen) {
  	    if (filen.isDirectory()) return true;
  	    return filen.getName().toLowerCase().endsWith(".pbm");
   }
   public String getDescription () { return "Portable Bitmap (*.pbm)"; }  
  };  
/**
 * Statuszeile Hauptfenster
 */
  private JLabel clusterStatus=new JLabel();
/**
 * Fenster für File Validation
 */
  private JFrame fValidate;
  private JPanel validatePanel=new JPanel();
  private JScrollPane validateScrollPane=new JScrollPane();
  private String[] validateColHeads={"Typ","Variable","Daten","Datei"};
  private JTable validateTable=new JTable();
/**
 * Datafenster
 */
  private JFrame fData;
  private JTabbedPane tabbedPaneData=new JTabbedPane();
  private JPanel objectPanel=new JPanel();
  private JScrollPane objectScrollPane=new JScrollPane();
  private String[] objectColHeads={"#","X","Y"};
  private JTable objectTable=new JTable();
  private JPanel descriptionPanel=new JPanel();
  private JScrollPane descriptionScrollPane=new JScrollPane();
  private String[] descriptionColHeads={"#","Cluster"};
  private JTable descriptionTable=new JTable();
  private JPanel viPanel=new JPanel();
  private JScrollPane viScrollPane=new JScrollPane();
  private String[] viColHeads={"#","X","Y"};
  private JTable viTable=new JTable();
  private JPanel mikPanel=new JPanel();
  private JScrollPane mikScrollPane=new JScrollPane();
  private String[] mikColHeads={"#","1","2"};
  private JTable mikTable=new JTable();
  private JPanel viPathPanel=new JPanel();
  private JScrollPane viPathScrollPane=new JScrollPane();
  private String[] viPathColHeads={"#","X","Y"};
  private JTable viPathTable=new JTable();
  private JPanel pixelObjectPanel=new JPanel();
  private JScrollPane pixelObjectScrollPane=new JScrollPane();
  private String[] pixelObjectColHeads={"#","1","2"};
  private JTable pixelObjectTable=new JTable();
  private JPanel pixelViPanel=new JPanel();
  private JScrollPane pixelViScrollPane=new JScrollPane();
  private String[] pixelViColHeads={"#","X","Y"};
  private JTable pixelViTable=new JTable();
  private JPanel pixelViPathPanel=new JPanel();
  private JScrollPane pixelViPathScrollPane=new JScrollPane();
  private String[] pixelViPathColHeads={"#","X","Y"};
  private JTable pixelViPathTable=new JTable();
  private JPanel pixelStringPanel=new JPanel();
  private JScrollPane pixelStringScrollPane=new JScrollPane();
  private String[] pixelStringColHeads={"#","String"};
  private JTable pixelStringTable=new JTable();
  private JPanel miscPanel=new JPanel();
  private JScrollPane miscScrollPane=new JScrollPane();
  private String[] miscColHeads={"Typ","Variable","Wert","Daten"};
  private JTable miscTable=new JTable();
/**
 * Fenster für Checkreport
 */
  private JFrame fCheck; 
  private JScrollPane checkScrollPane=new JScrollPane();
  private JTextArea checkTextArea=new JTextArea();
  private GregorianCalendar clusterCalendar=new GregorianCalendar();
  private DateFormat clusterDateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
  
/**
 * Ausgabe einer Clustergrafik 
 */
  public ClusterGraphics() throws InterruptedException{ 
    clusterGraphicsGenerator();
    clearAll();
    setPixelString(ClusterGraphics.clusterfreak);
    pixelToObject();
    setPixelOriginal(true);
    pixelMatrix();
    setTitle("clusterfreak");
    f.repaint();
    Thread.sleep(500);
    clearAll();
  }

/**
 * Ausgabe einer Clustergrafik
 * @param     object Objekte
 */
  public ClusterGraphics(double object[][]){
	clusterGraphicsGenerator();
    clearAll();
	setObject(object);
  }

/**
 * Ausgabe einer Clustergrafik
 * @param     object Objekte
 * @param     userTitle Beschreibung in der Titelzeile des Fensters
 */
  public ClusterGraphics(double object[][], String userTitle){
	clusterGraphicsGenerator();
	clearAll();
	setObject(object);
    setTitle(userTitle);
  }

/**
 * Generierung der GUI
 */
  private void clusterGraphicsGenerator(){
    //Hauptfenster
    f = new JFrame(titleString+getTitle());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    f.setLayout(new BorderLayout());
    this.setLayout(new BorderLayout());
    this.setSize((int)(getZoom()*100+1),(int)(getZoom()*100+1));
    l.setPreferredSize(new Dimension(this.getHeight(),this.getWidth()));
    final Dimension d = this.getToolkit().getScreenSize();
    f.setLocation((int)(d.getWidth()/2-(this.getWidth()/2)),(int)(d.getHeight()/2-(this.getHeight()/2)));
    f.getContentPane().add(this,BorderLayout.CENTER);
    f.getContentPane().add(l,BorderLayout.CENTER);
    clusterStatus.setSize(new Dimension(100,16));
    clusterStatus.setText(ready);
    f.getContentPane().add(this.clusterStatus,BorderLayout.SOUTH);
//Menü
    f.setJMenuBar(clusterMenu);
    clusterMenu.add(clusterToolBar);
    clusterToolBar.add(clusterButtonCalculate);
    clusterButtonCalculate.addActionListener(this);
    clusterButtonCalculate.setMnemonic('C');
    clusterButtonCalculate.setEnabled(getCalculate());
    clusterToolBar.add(clusterButtonError);
    clusterButtonError.addActionListener(this);
    clusterButtonError.setMnemonic('E');
    clusterButtonError.setEnabled(getError());
    clusterButtonError.setRolloverEnabled(false);
    clusterButtonError.setFocusable(false);
//Menü File
    clusterMenu.add(clusterMenuFile);
    clusterMenuFile.setMnemonic('F');
    clusterMenuFile.add(clusterMenuFileOpen);
    clusterMenuFile.add(clusterMenuFileSave);
    clusterMenuFile.addSeparator();
    clusterMenuFile.add(clusterMenuFileValidate);
    clusterMenuFile.addSeparator();
    clusterMenuFile.add(clusterMenuFileImport);
    clusterMenuFile.add(clusterMenuFileExport);
    clusterMenuFileOpen.addActionListener(this);
    clusterMenuFileOpen.setMnemonic('O');
    clusterMenuFileSave.addActionListener(this);
    clusterMenuFileSave.setMnemonic('S');
    clusterMenuFileValidate.addActionListener(this);
    clusterMenuFileImport.addActionListener(this);
    clusterMenuFileImport.setMnemonic('I');
    clusterMenuFileExport.addActionListener(this);
    clusterMenuFileExport.setMnemonic('E');
//Menü View
    clusterMenu.add(clusterMenuView);
    clusterMenuView.add(clusterMenuViewPixelMode);
    clusterMenuView.setMnemonic('V');
    clusterMenuViewPixelMode.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setPixel(clusterMenuViewPixelMode.isSelected());
        }
     });
    clusterMenuViewPixelMode.addActionListener(this);
    clusterMenuView.addSeparator();
    clusterMenuView.add(clusterMenuViewPathOption);
    clusterMenuViewPathOption.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setPathOption(clusterMenuViewPathOption.isSelected());
      }
    });
    clusterMenuViewPathOption.addActionListener(this);
    clusterMenuView.add(clusterMenuViewDescriptionDisplay);
    clusterMenuViewDescriptionDisplay.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setDescriptionDisplay(clusterMenuViewDescriptionDisplay.isSelected());
      }
    });
    clusterMenuViewDescriptionDisplay.addActionListener(this);
    clusterMenuView.add(clusterMenuViewHeadUpDisplay);
    clusterMenuViewHeadUpDisplay.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setHeadUpDisplay(clusterMenuViewHeadUpDisplay.isSelected());
      }
    });
    clusterMenuViewHeadUpDisplay.addActionListener(this);
    clusterMenuView.addSeparator();
    clusterMenuView.add(clusterMenuViewRefresh);
    clusterMenuView.setMnemonic('R');
    clusterMenuViewRefresh.addActionListener(this);
// Menü Zoom
    clusterMenu.add(clusterMenuZoom);
    clusterMenuZoom.add(clusterMenuZoomDefault);
    clusterMenuZoom.setMnemonic('Z');
    clusterMenuZoomDefault.addActionListener(this);
    clusterMenuZoomDefault.setEnabled(false);
    clusterMenuZoom.add(clusterMenuZoomOut);
    clusterMenuZoomOut.addActionListener(this);
    clusterMenuZoom.add(clusterMenuZoomIn);
    clusterMenuZoomIn.addActionListener(this);
// Menü Data
    clusterMenu.add(clusterMenuData);
    clusterMenuData.add(clusterMenuDataShow);
    clusterMenuData.setMnemonic('D');
    clusterMenuDataShow.addActionListener(this);
    clusterMenuData.add(clusterMenuDataCheck);
    clusterMenuDataCheck.addActionListener(this);
// Menü Set
    clusterMenu.add(clusterMenuSet);
    clusterMenuSet.add(clusterMenuSetPixelDim);
    clusterMenuSet.setMnemonic('S');
    clusterMenuSetPixelDim.addActionListener(this);
    clusterMenuSet.add(clusterMenuSetCluster);
    clusterMenuSetCluster.addActionListener(this);
    clusterMenuSet.add(clusterMenuSetE);
    clusterMenuSetE.addActionListener(this);
    clusterMenuSet.addSeparator();
    clusterMenuSet.add(clusterMenuSetFuzzyCMeans);
    clusterMenuSetFuzzyCMeans.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setFuzzyCMeans(clusterMenuSetFuzzyCMeans.isSelected());
      }
    });
    clusterMenuSetFuzzyCMeans.addActionListener(this);
    clusterButtonGroupSet1.add(clusterMenuSetFuzzyCMeans);
    clusterMenuSet.add(clusterMenuSetPossibilisticCMeans);
    clusterMenuSetPossibilisticCMeans.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setPossibilisticCMeans(clusterMenuSetPossibilisticCMeans.isSelected());
      }
    });
    clusterMenuSetPossibilisticCMeans.addActionListener(this);
    clusterButtonGroupSet1.add(clusterMenuSetPossibilisticCMeans);
    clusterMenuSet.add(clusterMenuSetDurchlauf);
    clusterMenuSetDurchlauf.addActionListener(this);
    clusterMenuSet.addSeparator();
    clusterMenuSet.add(clusterMenuSetSortCluster);
    clusterMenuSetSortCluster.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
        	setSortCluster(clusterMenuSetSortCluster.isSelected());
        }
      });
    clusterMenuSetSortCluster.addActionListener(this);
    clusterMenuSet.addSeparator();
    clusterMenuSet.add(clusterMenuSetFivtyFivtyJoker);
    clusterMenuSetFivtyFivtyJoker.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
    	  setFivtyFivtyJoker(clusterMenuSetFivtyFivtyJoker.isSelected());
      }
    });
    clusterMenuSetFivtyFivtyJoker.addActionListener(this);
    clusterButtonGroupSet2.add(clusterMenuSetFivtyFivtyJoker);
    
    clusterMenuSet.add(clusterMenuSetClusterMax);
    clusterMenuSetClusterMax.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
    	  setClusterMax(clusterMenuSetClusterMax.isSelected());
      }
    });
    clusterMenuSetClusterMax.addActionListener(this);
    clusterButtonGroupSet2.add(clusterMenuSetClusterMax);
    clusterMenuSet.addSeparator();
    clusterMenuSet.add(clusterMenuSetTitle);
    clusterMenuSetTitle.addActionListener(this);
// Menü Tools
    clusterMenu.add(clusterMenuTools);
    clusterMenuTools.setMnemonic('T');
    clusterMenuTools.add(clusterMenuToolsError);
    clusterMenuToolsError.addActionListener(this);
    clusterMenuTools.add(clusterMenuToolsAddPoint);
    clusterMenuToolsAddPoint.addActionListener(this);
    clusterMenuTools.add(clusterMenuToolsDelete);
    clusterMenuToolsDelete.addActionListener(this);
    clusterMenuTools.add(clusterMenuToolsDeleteNotAssigned);
    clusterMenuToolsDeleteNotAssigned.addActionListener(this);
    clusterMenuTools.add(clusterMenuToolsClearAll);
    clusterMenuToolsClearAll.addActionListener(this);
// Menü ?
    clusterMenu.add(clusterMenuHelp);
    clusterMenuHelp.add(clusterMenuHelpInfo);
    clusterMenuHelp.setMnemonic('?');
    clusterMenuHelpInfo.addActionListener(this);
    clusterMenuHelpInfo.setMnemonic('I');
// Dateiauswahl
    clusterChooser.addChoosableFileFilter(clusterFileFilterXML);
    clusterChooser.addChoosableFileFilter(clusterFileFilterPBM);
    clusterChooser.setFileFilter(clusterFileFilterXML);
    clusterChooser.setMultiSelectionEnabled(false);
//Hauptfenster
    try{
    	f.setIconImage(new ImageIcon(getClass().getResource("res/favicon.ico")).getImage());
    }catch(Exception e){
    	JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.getResource",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE); 
    }
    f.pack();
    f.setVisible(true);
//FileValidationFenster
    fValidate= new JFrame(titleString+" Validate");
    fValidate.setSize(500,500);
    validatePanel.setLayout(new GridLayout(1,1));
    validatePanel.add(validateScrollPane,BorderLayout.CENTER);
    fValidate.getContentPane().add(validatePanel);
//Datafenster
    fData= new JFrame(titleString+" Data");
    fData.setSize(500,500);
    miscPanel.setLayout(new GridLayout(1,1));
    miscPanel.add(miscScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("misc",miscPanel);
    objectPanel.setLayout(new GridLayout(1,1));
    objectPanel.add(objectScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("object",objectPanel);
    descriptionPanel.setLayout(new GridLayout(1,1));
    descriptionPanel.add(descriptionScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("objectDescription",descriptionPanel);
    viPanel.setLayout(new GridLayout(1,1));
    viPanel.add(viScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("vi",viPanel);
    mikPanel.setLayout(new GridLayout(1,1));
    mikPanel.add(mikScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("mik",mikPanel);
    viPathPanel.setLayout(new GridLayout(1,1));
    viPathPanel.add(viPathScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("viPath",viPathPanel);
    pixelObjectPanel.setLayout(new GridLayout(1,1));
    pixelObjectPanel.add(pixelObjectScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("pixelObject", pixelObjectPanel);
    pixelViPanel.setLayout(new GridLayout(1,1));
    pixelViPanel.add(pixelViScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("pixelVi", pixelViPanel);
    pixelViPathPanel.setLayout(new GridLayout(1,1));
    pixelViPathPanel.add(pixelViPathScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("pixelViPath", pixelViPathPanel);
    pixelStringPanel.setLayout(new GridLayout(1,1));
    pixelStringPanel.add(pixelStringScrollPane, BorderLayout.CENTER);
    tabbedPaneData.addTab("pixelString",pixelStringPanel);
    fData.add(tabbedPaneData);
//Checkreportfenster
    fCheck= new JFrame(titleString+" Check");
    fCheck.setLayout(new BorderLayout());
    fCheck.setSize(500,500);
    checkScrollPane.setViewportView(this.checkTextArea);
    fCheck.getContentPane().add(checkScrollPane);
  }

/**
 * Verarbeitung der Menü- und Button-Ereignisse
 */
  public void actionPerformed(ActionEvent ev){
   	String actionCommand = ev.getActionCommand();
    if ("Error".equals(actionCommand)){
    	clusterStatus.setText(" quickCheck");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Error") {
            public void run() {
          		quickCheck();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Check".equals(actionCommand)){
    	clusterStatus.setText(" check");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Check") {
            public void run() {
          		checkReport();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Clear All".equals(actionCommand)){
    	clusterStatus.setText(" clear all");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Clear all") {
            public void run() {
          		clearAll();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Calculate".equals(actionCommand)){
    	clusterStatus.setText(" calculate");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Calculate") {
            public void run() {
          		if(quickCheck())calculateCluster();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Open".equals(actionCommand)){
    	clusterStatus.setText(" open");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Open") {
            public void run() {
          		open();
          		repaint();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Save".equals(actionCommand)){
    	clusterStatus.setText(" save");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Save") {
            public void run() {
          		save();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Validate".equals(actionCommand)){
    	clusterStatus.setText(" validate");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Validate") {
            public void run() {
            	validation();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Import".equals(actionCommand)){
    	clusterStatus.setText(" import");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Import") {
            public void run() {
            	importPBM();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Export".equals(actionCommand)){
    	clusterStatus.setText(" export");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Export") {
            public void run() {
            	exportPBM();
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
//setCluster
    if ("cluster".equals(actionCommand)){
    	clusterStatus.setText(" setCluster");
    	Thread clusterThread = new Thread(clusterThreadGroup,"cluster") {
            public void run() {
            	try{
            		int altCluster=getCluster();
            		int response = Integer.valueOf((String) JOptionPane.showInputDialog(null,"set Cluster","ClusterGraphics",JOptionPane.QUESTION_MESSAGE,null,null,String.valueOf(altCluster))).intValue();
            		if(response!=altCluster){
            			setCluster(response);
            			setCalculate(true);
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.setCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
//setTitle
    if ("title".equals(actionCommand)){
    	clusterStatus.setText(" setTitle");
    	Thread clusterThread = new Thread(clusterThreadGroup,"title") {
            public void run() {
            	try{
            		String altTitle=getTitle();
            		String response = JOptionPane.showInputDialog(null,"set title ("+String.valueOf(altTitle)+")","ClusterGraphics",JOptionPane.QUESTION_MESSAGE);
            		if(response!=altTitle){
            			setTitle(response);
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.setTitle",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }    
//setE
    if ("e".equals(actionCommand)){
    	clusterStatus.setText(" setE");
    	Thread clusterThread = new Thread(clusterThreadGroup,"e") {
            public void run() {
            	try{
            		double altE=getE();
            		double response = Double.valueOf(JOptionPane.showInputDialog(null,"set e ("+String.valueOf(altE)+")","ClusterGraphics",JOptionPane.QUESTION_MESSAGE)).doubleValue();
            		if(response!=altE){
            			setE(response);
            			setCalculate(true);
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.setE",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
  //setPixelDim
    if ("pixelDim".equals(actionCommand)){
    	clusterStatus.setText(" setPixelDim");
    	Thread clusterThread = new Thread(clusterThreadGroup,"pixelDim") {
            public void run() {
            	try{
            		int altPixelDim=getPixelDim();
            		int response = Integer.valueOf((String) JOptionPane.showInputDialog(null,"set pixelDim","ClusterGraphics",JOptionPane.QUESTION_MESSAGE,null,null,String.valueOf(altPixelDim))).intValue();
            		if(response!=altPixelDim){
            			if((response<1)||(response>2)){
    	            		JOptionPane.showConfirmDialog (null,"Nur Werte zwischen 1 und 2 erlaubt.","ClusterGraphics.setPixelDim",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
                		}
                		else{
                			setPixelDim(response);
                			setCalculate(true);
                		}
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.setPixelDim",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
//setDurchlauf
    if ("durchlauf".equals(actionCommand)){
    	clusterStatus.setText(" setDurchlauf");
    	Thread clusterThread = new Thread(clusterThreadGroup,"durchlauf") {
            public void run() {
            	try{
            		int altDurchlauf=getDurchlauf();
            		int response=Integer.valueOf(JOptionPane.showInputDialog(null,"set durchlauf ("+String.valueOf(altDurchlauf)+")","ClusterGraphics",JOptionPane.QUESTION_MESSAGE)).intValue();
            		if(response!=altDurchlauf){
            			if((response<1)||(response>10)){
    	            		JOptionPane.showConfirmDialog (null,"Nur Werte zwischen 1 und 10 erlaubt.","ClusterGraphics.setDurchlauf",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
                		}
                		else{
                			setDurchlauf(response);
                			setCalculate(true);
                		}
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.setDurchlauf",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
//PixelMode, pathOption, descriptionDisplay, refresh, headUpDisplay
    if ("PixelMode".equals(actionCommand))this.repaint();
    if ("pathOption".equals(actionCommand))this.repaint();
    if ("descriptionDisplay".equals(actionCommand))this.repaint();
    if ("refresh".equals(actionCommand))this.repaint();
    if ("headUpDisplay".equals(actionCommand))this.repaint();
//computeCluster
    if (("FuzzyCMeans".equals(actionCommand))||
    	("PossibilisticCMeans".equals(actionCommand))||
    	("SortCluster".equals(actionCommand))||
    	("FivtyFivtyJoker".equals(actionCommand))||
    	("ClusterMax".equals(actionCommand))){
    	clusterStatus.setText(" computeCluster");
    	Thread clusterThread = new Thread(clusterThreadGroup,"computeCluster") {
            public void run() {
            	setCalculate(true);
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
//add Point   
    if ("Add Point".equals(actionCommand)){
    	clusterStatus.setText(" addPoint");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Add Point") {
            public void run() {
            	try{
            		double responseX = Double.valueOf((String) JOptionPane.showInputDialog(null,"Add Point - X","ClusterGraphics",JOptionPane.QUESTION_MESSAGE,null,null,".")).doubleValue();
            		double responseY = Double.valueOf((String) JOptionPane.showInputDialog(null,"Add Point - Y","ClusterGraphics",JOptionPane.QUESTION_MESSAGE,null,null,".")).doubleValue();
            	    addPoint(responseX,responseY);
            	    setCalculate(true);
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.addPoint",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
        
                	}
                });
            }
          };
          clusterThread.start();

    }    
//delete Cluster    
    if ("Delete Cluster".equals(actionCommand)){
    	clusterStatus.setText(" deleteCluster");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Delete Cluster") {
            public void run() {
            	try{
            		int response = Integer.valueOf((String) JOptionPane.showInputDialog(null,"Delete Cluster","ClusterGraphics",JOptionPane.QUESTION_MESSAGE,null,null,"")).intValue();
            		if(response>getCluster()-1){
	            		JOptionPane.showConfirmDialog (null,"Cluster "+response+" ist nicht vorhanden.","ClusterGraphics.deleteCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	            		
            		}
            		else{
            			deleteCluster(response);
            			calculateCluster();
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.deleteCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
            	}
            	
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
//delete not assigned
          if ("Delete not assigned".equals(actionCommand)){
          	clusterStatus.setText(" deleteNotAssigned");
          	Thread clusterThread = new Thread(clusterThreadGroup,"Delete not assigned") {
                  public void run() {
                  	try{
                  			deleteNotAssigned();
                  			//calculateCluster();
                  	}catch(Exception e){
                  		JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.deleteNotAssigned",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
                  	}
                  	
                      SwingUtilities.invokeLater(new Runnable() {
                      	public void run() {
                      		clusterStatus.setText(ready);
                      	}
                      });
                  }
                };
                clusterThread.start();
    }
//info
    if ("Info".equals(actionCommand)){
    	clusterStatus.setText(" info");
    	Thread clusterThread = new Thread(clusterThreadGroup,"Info") {
            public void run() {
            	JOptionPane.showConfirmDialog (null,"Copyright 1999-"+jahr+" Thomas Heym","ClusterGraphics "+version,JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(new Runnable() {
                	public void run() {
                		clusterStatus.setText(ready);
                	}
                });
            }
          };
          clusterThread.start();
    }
    if ("Show".equals(actionCommand)){
      //misc
      String miscData[][]=new String[ClusterData.length][4];
      for(int l=0;l<ClusterData.length;l++){
    	  miscData[l][0]=ClusterData.type[l];
    	  miscData[l][1]=ClusterData.nameExtended[l];
    	  if(clusterFile.getData(l))miscData[l][3]="X";else miscData[l][3]="";
      }
      miscData[0][2]=String.valueOf(getPixel());
      miscData[1][2]=String.valueOf(getPixelDim());
      miscData[2][2]=String.valueOf(getPixelOffset());
      miscData[3][2]=String.valueOf(getPixelOriginal());
      miscData[4][2]=String.valueOf(getCluster());
      miscData[5][2]=String.valueOf(getObjects());
      if(getObject()!=null)miscData[6][2]="["+String.valueOf(getObject().length)+"]";else miscData[6][2]="[null]";
      if(getObjectDescription()!=null)miscData[7][2]="["+String.valueOf(getObjectDescription().length)+"]";else miscData[7][2]="[null]";
      if(getVi()!=null)miscData[8][2]="["+String.valueOf(getVi().length)+"]";else miscData[8][2]="[null]";
      if(getViPath()!=null)miscData[9][2]="["+String.valueOf(getViPath().length)+"]";else miscData[9][2]="[null]";
      miscData[10][2]=String.valueOf(getPathOption());
      miscData[11][2]=String.valueOf(getDescriptionDisplay());
      miscData[12][2]=String.valueOf(getDurchlauf());
      if(getMik()!=null)miscData[13][2]="["+String.valueOf(getMik().length)+"]";else miscData[13][2]="[null]";
      miscData[14][2]=String.valueOf(getE());
      miscData[15][2]=String.valueOf(getCalculate());
      miscData[16][2]=String.valueOf(getFuzzyCMeans());
      miscData[17][2]=String.valueOf(getPossibilisticCMeans());
      miscData[18][2]=String.valueOf(getSortCluster());
      miscData[19][2]=String.valueOf(getFivtyFivtyJoker());
      miscData[20][2]=String.valueOf(getClusterMax());
      if(getPixelObject()!=null)miscData[21][2]="["+String.valueOf(getPixelObject().length)+"]";else miscData[21][2]="[null]";
      if(getPixelVi()!=null)miscData[22][2]="["+String.valueOf(getPixelVi().length)+"]";else miscData[22][2]="[null]";
      if(getPixelViPath()!=null)miscData[23][2]="["+String.valueOf(getPixelViPath().length)+"]";else miscData[23][2]="[null]";
      if(getPixelString()!=null)miscData[24][2]="["+String.valueOf(getPixelString().length)+"]";else miscData[24][2]="[null]";
      miscData[25][2]=String.valueOf(getZoom());
      miscData[26][2]=getTitle();
      miscData[27][2]=ClusterGraphics.version;
      miscData[28][2]=ClusterGraphics.jahr;
      miscData[29][2]=ClusterGraphics.titleString;
      miscData[30][2]=ClusterGraphics.ready;
      miscData[31][2]="["+String.valueOf(ClusterGraphics.clusterfreak.length)+"]";
      miscData[32][2]=String.valueOf(this.clusterFile.hashCode());
      if(clusterBot!=null)miscData[33][2]="["+String.valueOf(clusterBot.length)+"]";else miscData[33][2]="[null]";
      miscData[34][2]=String.valueOf(getError());
      miscData[35][2]=String.valueOf(getHeadUpDisplay());
      miscTable=new JTable(miscData,miscColHeads);
      miscScrollPane.setViewportView(miscTable);
      //object
      if (getObject()!=null){
    	  String objectData[][]=new String[getObject().length][3];
    	  for(int i=0;i<getObject().length;i++){
    		  objectData[i][0]=String.valueOf(i);
    		  objectData[i][1]=String.valueOf(getObject()[i][0]);
    		  objectData[i][2]=String.valueOf(getObject()[i][1]);
    	  }
    	  objectTable = new JTable(objectData, objectColHeads);
    	  objectScrollPane.setViewportView(objectTable);
      }else objectTable=null;
      //objectDescription
      if (getObjectDescription()!=null){
    	  String descriptionData[][]=new String[getObjectDescription().length][2];
    	  for(int i=0;i<getObjectDescription().length;i++){
    		  descriptionData[i][0]=String.valueOf(i);
    		  descriptionData[i][1]=getObjectDescription()[i];
    	  }
    	  descriptionTable = new JTable(descriptionData, descriptionColHeads);
    	  descriptionScrollPane.setViewportView(descriptionTable);
      }else descriptionTable=null;
      //vi
      if (getVi()!=null){
    	 String viData[][]=new String[getVi().length][3];
    	 for(int i=0;i<getVi().length;i++){
    		 viData[i][0]=String.valueOf(i);
    		 viData[i][1]=String.valueOf(getVi()[i][0]);
    		 viData[i][2]=String.valueOf(getVi()[i][1]);
    	 }
    	 viTable=new JTable(viData,viColHeads);
    	 viScrollPane.setViewportView(viTable); 
      }else viTable=null;
      //mik
      if (getMik()!=null){
    	  String mikData[][]=new String[getMik().length][getCluster()+1];
    	  for(int i=0;i<getMik().length;i++){
    		  mikData[i][0]=String.valueOf(i);
    		  for(int k=0;k<getCluster();k++){
    			  mikData[i][k+1]=String.valueOf(getMik()[i][k]);
    		  }
    	  }
    	  mikColHeads=new String[getCluster()+1];
    	  mikColHeads[0]="#";
    	  for(int k=0;k<getCluster();k++){
    		  mikColHeads[k+1]=String.valueOf(k);
    	  }
    	  mikTable=new JTable(mikData,mikColHeads);
    	  mikScrollPane.setViewportView(mikTable);
      }else mikTable=null;
      //viPath
      if (getViPath()!=null){
    	  String viPathData[][]=new String[getViPath().length][3];
    	  for(int i=0;i<getViPath().length;i++){
    		  viPathData[i][0]=String.valueOf(i);
    		  viPathData[i][1]=String.valueOf(getViPath()[i][0]);
    		  viPathData[i][2]=String.valueOf(getViPath()[i][1]);
    	  }
    	  viPathTable=new JTable(viPathData,viPathColHeads);
    	  viPathScrollPane.setViewportView(viPathTable);
      }else viPathTable=null;
      //pixelObject
      if (getPixelObject()!=null){
    	  String pixelObjectData[][]=new String[getPixelObject().length][getPixelObject().length+1];
    	  for(int i=0;i<getPixelObject().length;i++){
    		  pixelObjectData[i][0]=String.valueOf(i);
    		  for(int k=0;k<getPixelObject().length;k++){
    			  pixelObjectData[i][k+1]=String.valueOf(getPixelObject()[i][k]);
    		  }
    	  }
    	  pixelObjectColHeads=new String[getPixelObject().length+1];
    	  pixelObjectColHeads[0]="#";
    	  for(int k=0;k<getPixelObject().length;k++){
    		  pixelObjectColHeads[k+1]=String.valueOf(k);
    	  }
    	  pixelObjectTable=new JTable(pixelObjectData,pixelObjectColHeads);
    	  pixelObjectScrollPane.setViewportView(pixelObjectTable);
      }else pixelObjectTable=null;
      //pixelVi
      if (getPixelVi()!=null){
    	  String pixelViData[][]=new String[getPixelVi().length][getPixelVi().length+1];
    	  for(int i=0;i<getPixelVi().length;i++){
    		  pixelViData[i][0]=String.valueOf(i);
    		  for(int k=0;k<getPixelVi().length;k++){
    			  pixelViData[i][k+1]=String.valueOf(getPixelVi()[i][k]);
    		  }
    	  }
    	  pixelViColHeads=new String[getPixelVi().length+1];
    	  pixelViColHeads[0]="#";
    	  for(int k=0;k<getPixelVi().length;k++){
    		  pixelViColHeads[k+1]=String.valueOf(k);
    	  }
    	  pixelViTable=new JTable(pixelViData,pixelViColHeads);
    	  pixelViScrollPane.setViewportView(pixelViTable);
      }else pixelViTable=null;
      //pixelViPath
      if (getPixelViPath()!=null){
    	  String pixelViPathData[][]=new String[getPixelViPath().length][getPixelViPath().length+1];
    	  for(int i=0;i<getPixelViPath().length;i++){
    		  pixelViPathData[i][0]=String.valueOf(i);
    		  for(int k=0;k<getPixelViPath().length;k++){
    			  pixelViPathData[i][k+1]=String.valueOf(getPixelViPath()[i][k]);
    		  }
    	  }
    	  pixelViPathColHeads=new String[getPixelViPath().length+1];
    	  pixelViPathColHeads[0]="#";
    	  for(int k=0;k<getPixelViPath().length;k++){
    		  pixelViPathColHeads[k+1]=String.valueOf(k);
    	  }
    	  pixelViPathTable=new JTable(pixelViPathData,pixelViPathColHeads);
    	  pixelViPathScrollPane.setViewportView(pixelViPathTable);
      }else pixelViPathTable=null;
      //pixelString
      if (getPixelString()!=null){
    	  String pixelStringData[][]=new String[getPixelString().length][2];
    	  for(int i=0;i<getPixelString().length;i++){
    		  pixelStringData[i][0]=String.valueOf(i);
    		  pixelStringData[i][1]=String.valueOf(getPixelString()[i]);
    	  }
    	  pixelStringTable=new JTable(pixelStringData,pixelStringColHeads);
    	  pixelStringScrollPane.setViewportView(pixelStringTable);
      }else pixelStringTable=null;
      fData.setVisible(true);
    }
//zoom
    if ("default".equals(actionCommand))setZoom(5);
    if ("+".equals(actionCommand)){
      if (getZoom() < 10)setZoom(getZoom()+1);
      else{
        JOptionPane.showConfirmDialog (null,"Maximale Vergrößerung erreicht.","Cluster Graphics Hinweis",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
        clusterMenuZoomOut.setEnabled(false);
      }
    }
    if ("-".equals(actionCommand)){
      if (getZoom() > 1)setZoom(getZoom()-1);
      else{
        JOptionPane.showConfirmDialog (null,"Maximale Verkleinerung erreicht.","Cluster Graphics Hinweis",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
        clusterMenuZoomIn.setEnabled(false);
      }
    }
  }

  protected void paintComponent(Graphics g){
    try{
      Graphics2D g2 = (Graphics2D) g;
      // weißer Hintergrund, löschen
      g2.setBackground(Color.white);
      g2.clearRect(0,0,f.getWidth(),f.getHeight());
      // graue Gitternetzlinien
      g2.setColor(Color.gray);
      g2.setStroke(new BasicStroke(getZoom()/3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      for(int k = 1; k < 10; k++) {
        g2.drawLine(0,getZoom()*k*10,getZoom()*100,getZoom()*k*10);
        g2.drawLine(getZoom()*k*10,0,getZoom()*k*10,getZoom()*100);
      }
      // schwarzer Rahmen
      g2.setColor(Color.black);
      g2.drawRect(0,0,getZoom()*100,getZoom()*100);
      //pixel
      if (getPixel()==true){
    	double pixelPaint = 100*Math.pow(10.0,(double)-getPixelDim());//th72
        for(int i=0;i<getPixelOffset();i++){
  		  for(int k=0;k<getPixelOffset();k++){
  			  try{
  				  if (getPixelViPath()[i][k]==true){
					  g2.setColor(Color.yellow);
					  if((int)(pixelPaint*getZoom())<1)
						  g2.fillRect((int)((i)*pixelPaint*getZoom()),(int)((k)*pixelPaint*getZoom()),1,1);
					  else
					      g2.fillRect((int)((i)*pixelPaint*getZoom()),(int)((k)*pixelPaint*getZoom()),(int)(pixelPaint*getZoom()),(int)(pixelPaint*getZoom())); 
  				  }
  			  }catch(Exception e){
			  }	 
  			  try{
	  			  if (getPixelObject()[i][k]==true){
	  				  g2.setColor(Color.blue);
	  				if((int)(pixelPaint*getZoom())<1)
						  g2.fillRect((int)((i)*pixelPaint*getZoom()),(int)((k)*pixelPaint*getZoom()),1,1);
					  else
						  g2.fillRect((int)((i)*pixelPaint*getZoom()),(int)((k)*pixelPaint*getZoom()),(int)(pixelPaint*getZoom()),(int)(pixelPaint*getZoom()));
	  			  }
  			  }catch(Exception e){
			  }	 
  			  try{
	  			  if (getPixelVi()[i][k]==true){
					  g2.setColor(Color.red);
					  if((int)(pixelPaint*getZoom())<1)
						  g2.fillRect((int)((i)*pixelPaint*getZoom()),(int)((k)*pixelPaint*getZoom()),1,1);
					  else
						  g2.fillRect((int)((i)*pixelPaint*getZoom()),(int)((k)*pixelPaint*getZoom()),(int)(pixelPaint*getZoom()),(int)(pixelPaint*getZoom()));
				  }
  			  }catch(Exception e){
			  }	 
  		  }
  	    }  
      }
      else{  
      // gelbe Punkte (Pfad)
      if ((getPathOption()==true) & (getViPath()!=null)){
        g2.setColor(Color.yellow);
        for(int k = 0; k < getViPath().length; k++){
          g2.fillOval((int)(getViPath()[k][0]*getZoom()*100-getZoom()),
                      (int)(getViPath()[k][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
        }
      }
      // blaue Punkte (Objekte)
      if(getObject()!=null){
    	  g2.setColor(Color.blue);
	      for(int k = 0; k < getObject().length; k++){
	        g2.fillOval((int)(getObject()[k][0]*getZoom()*100-getZoom()),
	                    (int)(getObject()[k][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
	      // Beschreibung der blauen Punkte
	        if(getDescriptionDisplay()==true){
	          if(getObjectDescription()[k]!=null){
	            g2.drawString(getObjectDescription()[k],(int)(getObject()[k][0]*getZoom()*100-getZoom()),
	                                               (int)(getObject()[k][1]*getZoom()*100-getZoom()));
	          }
	        }
	      }
      }
      // rote Punkte (Cluster)
      if(getVi()!=null){
    	  g2.setColor(Color.red);
	      if(getVi().length > 1){
	        for(int k=getVi().length-getCluster();k<getVi().length;k++){
	          g2.fillOval((int)(getVi()[k][0]*getZoom()*100-getZoom()),
	                      (int)(getVi()[k][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
	        // Beschreibung für rote Punkte
	          if(getDescriptionDisplay()==true){
	            g2.drawString(String.valueOf(k-(getVi().length-getCluster())), (int)(getVi()[k][0]*getZoom()*100-getZoom()),
	                                                                            (int)(getVi()[k][1]*getZoom()*100-getZoom()));
	          }
	        }
	      }
	      else if (getVi().length == 1){
	        g2.fillOval((int)(getVi()[0][0]*getZoom()*100-getZoom()),
	                    (int)(getVi()[0][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
	      }
	      }//else
      }
      //cluserBot Linien
      if(clusterFile.getData("ClusterBot")){
    	  for(int b=0;b<clusterBot.length;b++){
    		  if(clusterBot[b].getPoints()>0){
    			  for(int i=0;i<clusterBot[b].getPoint().length;i++){
    				  g2.drawLine((int)(clusterBot[b].getPoint()[i].x*getZoom()*100), 
    			    		      (int)(clusterBot[b].getPoint()[i].y*getZoom()*100), 
    			    		      (int)(clusterBot[b].getCenter().x*getZoom()*100), 
    			    		      (int)(clusterBot[b].getCenter().y*getZoom()*100)); 
    			  }
    		  }
    	  }
      }
      //HeadUpDisplay
      if(getHeadUpDisplay()){
    	  //cluster
    	  int x=5; int y=15; int z=15;
    	  if(getPixel())g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("pixel")+" "+ClusterData.getName("pixel"),x,y); 
    	  y=y+z;
    	  if(clusterFile.getData("Cluster"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("cluster")+" "+ClusterData.getName("cluster")+": "+getCluster(),x,y); 
    	  y=y+z;
    	  if(clusterFile.getData("Objects"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("objects")+" "+ClusterData.getName("objects")+": "+getObjects(),x,y); 
    	  y=y+z;
    	  if(clusterFile.getData("Vi"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  if(getVi()!=null)g2.drawString(ClusterData.getIndexString2("vi")+" "+ClusterData.getName("vi")+": "+String.valueOf(getVi().length),x,y);
    	              else g2.drawString(ClusterData.getIndexString2("vi")+" "+ClusterData.getName("vi")+": 0",x,y); y=y+z;
    	  if(clusterFile.getData("ClusterBot"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("clusterBot")+" "+ClusterData.getName("clusterBot"),x,y); 
    	  y=y+z;
      }
      clusterMenu.updateUI();
  }
  catch(Exception e){
      JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.paintComponent",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
    }
  }

/**
 * schaltet den Pixel- und den Nomal-Modus um
 * @param pixelMode true false
 */
  public void setPixel(boolean pixelMode){
	  this.pixel=pixelMode;
	  this.clusterMenuViewPixelMode.setSelected(pixelMode);
	  if(pixel)pixelMatrix();
	  if(pixel)clusterFile.setData("Pixel",false);else clusterFile.setData("Pixel",true);
  }
  
/**
 * Pixelmodus aktiv?  
 * @return pixel ja/nein
 */
  public boolean getPixel(){
	  return pixel;
  }

/**
 * legt den Grad der Pixelauflösung fest
 * 1 -> 10 * 10 = 10^1
 * 2 -> 100 * 100 = 10^2
 * 3 -> 1000 * 1000 = 10^3 
 * @param pixelDim 1..2
 */
  public void setPixelDim(int pixelDim){
	  this.pixelDim=pixelDim;
	  this.pixelOffset=(int)Math.pow(10, pixelDim);
	  setPixelObject(null);
	  setPixelVi(null);
	  setPixelViPath(null);
	  setPixelString(null);
	  if(pixelDim==2)clusterFile.setData("PixelDim",false);else clusterFile.setData("PixelDim",true);
	  clusterFile.setData("PixelOffset",false);
	  clusterFile.setData("PixelString",false);
  }
  
/**
 * liefert den Grad der Pixelauflösung  
 * @return pixelDim 1..2
 */
  public int getPixelDim(){
	  return pixelDim;
  }

/**
 * liefert die Größe der Pixelauflösung  
 * @return pixelOffset
 */
  private int getPixelOffset(){
	  return pixelOffset;
  }
  
 /**
  * Pixelobjekt oder Doubleobject ist Original
  * @param pixelOriginal
  */
  private void setPixelOriginal(boolean pixelOriginal){
	  this.pixelOriginal=pixelOriginal;
	  if(pixelOriginal)clusterFile.setData("PixelOriginal",false);else clusterFile.setData("PixelOriginal",true);
  }
  
/**
 * liefert Aussage, ob PixelObjekt oder DoubleObjekt das Original ist  
 * @return pixelOriginal pixelOriginal
 */
  private boolean getPixelOriginal(){
	  return pixelOriginal;
  }
  
/**
 * verändert die Anzahl der Cluster
 * @param cluster neue Anzahl der Cluster
 */
  public void setCluster(int cluster){
    this.cluster=cluster;
    if(cluster==0)this.clusterFile.setData("Cluster",false);else this.clusterFile.setData("Cluster",true);
  }

/**
 * liefert die Anzahl der Cluster
 * @return cluster aktuelle Anzahl der Cluster
 */
  public int getCluster(){
    return cluster;
  }

/**
 * setze die Anzahl der Objekte  
 * @param objects
 */
  private void setObjects(int objects){
	  this.objects=objects;
	  if(objects==0)this.clusterFile.setData("Objects",false);else this.clusterFile.setData("Objects",true);
  }
 
/**
 * liefert die Anzahl der Objecte  
 * @return objects
 */
  private int getObjects(){
	  return objects;
  }
  
/**
 * übergibt neue Objekte, vorhandene Objekte werden gelöscht, Anzahl der Objekte wird neu definiert
 * @param clusterObject Objektmatrix
 */
  public void setObject(double clusterObject[][]){
    this.object=clusterObject;
    if(object!=null){
    	setObjects(object.length);
    	if(!clusterFile.getData("ObjectDescription"))setObjectDescription(new String[object.length]);
        clusterFile.setData("Object",true);
    }
    else{
    	setObjects(0);
    	clusterFile.setData("Object",false);
    }
  }

/**
 * liefert die Objekte zurück  
 * @return object Objektmatrix
 */
  public double[][] getObject(){
    return object;
  }

/**
 * Setzt die Beschriftung der Punkte
 * @param objectDescription
 */
  private void setObjectDescription(String[] objectDescription){ 
	  this.objectDescription=objectDescription;
	  if(objectDescription!=null){
		  if(objectDescription.length>0)clusterFile.setData("ObjectDescription",true);
		  else clusterFile.setData("ObjectDescription",false);
	  }
	  else clusterFile.setData("ObjectDescription",false);
  }
  
/**
 * liefert die Beschriftung der Punkte mit der zugehörigen Klass  
 * @return objectDescription
 */
  private String[] getObjectDescription(){
	return objectDescription;
  }
  
/**
 * setzt die Cluster (Klassenzentren)  
 * @param vi
 */
  private void setVi(double vi[][]){
	  this.vi=vi;
	  if(vi!=null){
		  if(vi.length>0)clusterFile.setData("Vi",true);
		  else clusterFile.setData("Vi",false);
	  }
	  else clusterFile.setData("Vi",false);
  }
  
/**
 * liefert die Cluster (Klassenzentren) zurück
 * @return vi Clusterkoordinaten
 */
  public double[][] getVi(){
    return vi;
  }

/**
 * setzt den Verlauf der Findung der Klassenzentren  
 * @param viPath
 */
  private void setViPath(double viPath[][]){
	  this.viPath=viPath;
	  if(viPath!=null){
		  if(viPath.length>0)clusterFile.setData("ViPath",true);
		  else clusterFile.setData("ViPath",false);
	  }
	  else{
		  clusterFile.setData("ViPath",false);
		  this.setPixelViPath(null);
	  }
  }

/**
 * liefert den Verlauf der Findung der Klassenzentren  
 * @return viPath viPath
 */
  private double[][] getViPath(){
	  return viPath;
  }
  
/**
 * steuert die Anzeige des Suchpfades
 * @param path true/false
 */
  public void setPathOption(boolean path){
    this.pathOption=path;
    this.clusterMenuViewPathOption.setState(path);
    if(pathOption)clusterFile.setData("PathOption",true);else clusterFile.setData("PathOption",false);
  }

/**
 * liefert den Status der Anzeige des Suchpfades
 * @return pathOption pathOption
 */
  public boolean getPathOption(){
	  return pathOption;
  }
  
/**
  * Steuert die Anzeige der Beschriftung (ja/nein)
  * @param descriptionDisplay true oder false
  */
  public void setDescriptionDisplay(boolean descriptionDisplay) {
    this.descriptionDisplay=descriptionDisplay;
    this.clusterMenuViewDescriptionDisplay.setState(descriptionDisplay);
    if(descriptionDisplay)clusterFile.setData("DescriptionDisplay",true);else clusterFile.setData("DescriptionDisplay",false);
  }

/**
 * liefert den Status der Anzeige der Beschriftung
 * @return descriptionDisplay true oder false
 */
  public boolean getDescriptionDisplay(){
	  return descriptionDisplay;
  }
  
/**
 * legt die Anzahl der Durchläufe für den PossibilisticCMeans fest (normalerweise 1)
 * @param anzahl Anzahl der Durchläufe
 */
  public void setDurchlauf(int anzahl){
    this.durchlauf=anzahl;
    if(durchlauf==1)clusterFile.setData("Durchlauf",false);else clusterFile.setData("Durchlauf",true);
  }

/**
 * liefert die Anzahl der Durchläufe für den PossibilisticCMeans zurück
 * @return durchlauf Anzahl der Durchläufe
 */
  public int getDurchlauf(){
	  return durchlauf;
  }

/**
 * setzt die Partitionsmatrix mit den Zugehörigkeitswerten der Objekte zu den Clustern (k. Objet zur i. Klasse)  
 * @param mik
 */
  private void setMik(double[][] mik){
	  this.mik=mik;
	  if(mik!=null){
		  if(mik.length>0)clusterFile.setData("Mik",true);
		  else clusterFile.setData("Mik",false);
	  }
	  else clusterFile.setData("Mik",false);
  }
  
/**
 * liefert die Partitionsmatrix mit den Zugehörigkeitswerten der Objekte zu den Clustern zurück (k. Objekt zur i. Klasse)
 * @return mik Partitionsmatrix
 */
  public double[][] getMik(){
    return mik;
  }

/**
 * legt die Abbruchschwelle e fest (normalerweise 1.0e-7)
 * @param e
 */
  public void setE(double e){
    this.e=e;
    if(e==1.0e-7)clusterFile.setData("E",false);
    else clusterFile.setData("E",true);
  }

/**
 * liefert die Abbruchschwelle e zurück  
 * @return e Abbruchschwelle
 */
  public double getE(){
	  return e;
  }
  
/**
 * ändert calculate zur Neuberechnung  
 * @param calc Wert (true/false)
 */
  private void setCalculate(boolean calc){
	  this.calculate=calc;
	  this.clusterButtonCalculate.setEnabled(calc);
	  if(calculate)clusterFile.setData("Calculate",true);else clusterFile.setData("Calculate",false);
  }

/**
 * liefert die Informatin, ob eine Neuberechnung erforderlich ist 
 * @return calculate calculate
 */
  private boolean getCalculate(){
	  return calculate;
  }
  
/**
 * setzt Flag für Fuzzy-C-Means Algorithmus  
 * @param fcm true false
 */
  private void setFuzzyCMeans(boolean fcm){
	  this.fuzzyCMeans=fcm;
	  this.clusterMenuSetFuzzyCMeans.setSelected(fcm);
	  if(fuzzyCMeans)clusterFile.setData("FuzzyCMeans",true);else clusterFile.setData("FuzzyCMeans",false);
  }

/**
 * liefert Flag für Fuzzy-C-Means Algorithmus  
 * @return fuzzyCMeans
 */
  private boolean getFuzzyCMeans(){
	  return fuzzyCMeans;
  }
  
/**
 * setzt Flag für Possibilistic-C-Means Algorithmus  
 * @param pcm
 */
  private void setPossibilisticCMeans(boolean pcm){
	  this.possibilisticCMeans=pcm;
	  this.clusterMenuSetPossibilisticCMeans.setSelected(pcm);
	  if(possibilisticCMeans)clusterFile.setData("PossibilisticCMeans",true);else clusterFile.setData("PossibilisticCMeans",false);
  }

/**
 * liefert Flag für Possibilistic-C-Means Algorithmus  
 * @return possibilisticCMeans
 */
  private boolean getPossibilisticCMeans(){
	  return possibilisticCMeans;
  }
  
/**
 * setzt Flag ob mik, object, vi auf Basis von objectDescription in Cluster-Reihenfolge sortiert werden sollen
 * @param sortCluster
 */
  private void setSortCluster(boolean sortCluster){
	  this.sortCluster=sortCluster;
	  this.clusterMenuSetSortCluster.setSelected(sortCluster);
	  if(sortCluster)clusterFile.setData("SortCluster",false);else clusterFile.setData("SortCluster",true);
  }

/**
 * liefert Flag ob mik, object, vi auf Basis von objectDescription in Cluster-Reihenfolge sortiert werden sollen  
 * @return sortCluster
 */
  private boolean getSortCluster(){
	  return sortCluster;
  }
  
/**
 * setzt Variable für Beschriftung mit Zugehörigkeit >0.5  
 * @param fivtyFivtyJoker true false
 */
  private void setFivtyFivtyJoker(boolean fivtyFivtyJoker){
	  this.fivtyFivtyJoker=fivtyFivtyJoker;
	  this.clusterMenuSetFivtyFivtyJoker.setSelected(fivtyFivtyJoker);
	  if(fivtyFivtyJoker)clusterFile.setData("FivtyFivtyJoker",true);else clusterFile.setData("FivtyFivtyJoker",false);
  }

/**
 * liefert Variable für Beschriftung mit Zugehörigkeit >0.5  
 * @return fivtyFivtyJoker
 */
  private boolean getFivtyFivtyJoker(){
	  return fivtyFivtyJoker;
  }
  
/**
 * setzt Variable für Beschriftung nach größter Zugehörigkeit  
 * @param clusterMax true false
 */
  private void setClusterMax(boolean clusterMax){
	  this.clusterMax=clusterMax;
	  this.clusterMenuSetClusterMax.setSelected(clusterMax);
	  if(clusterMax)clusterFile.setData("ClusterMax",true);else clusterFile.setData("ClusterMax",false);
  }
 
  
/**
 * liefert Variable für Beschriftung nach größter Zugehörigkeit  
 * @return clusterMax
 */
  private boolean getClusterMax(){
	  return clusterMax;
  }
  
  /**
   * setzt die Pixelmatrix für Darstellung der Objekte
   * @param pixelObject
   */
  private void setPixelObject(boolean pixelObject[][]){
	  this.pixelObject=pixelObject;
	  if(pixelObject==null)clusterFile.setData("PixelObject",false);
	  else clusterFile.setData("PixelObject",true);
  }
  
  /**
   * liefert die Pixelmatrix für Darstellung der Objekte
   * @return pixelObject
   */
  private boolean[][] getPixelObject(){
	  return pixelObject;
  }
  
  /**
   * setzt die die Pixelmatrix für Darstellung der Klassenzentren
   * @param pixelVi
   */
  private void setPixelVi(boolean pixelVi[][]){
	  this.pixelVi=pixelVi;
	  if(pixelVi==null)clusterFile.setData("PixelVi",false);
	  else clusterFile.setData("PixelVi",true);
  }
  
  /**
   * liefert die Pixelmatrix für Darstellung der Klassenzentren
   * @return pixelVi pixelVi
   */
  private boolean[][] getPixelVi(){
	  return pixelVi;
  }
  
  /**
   * setzt die Pixelmatrix für Darstellung des Suchpfades de Klassenzentren
   * @param pixelViPath
   */
  private void setPixelViPath(boolean pixelViPath[][]){
	  this.pixelViPath=pixelViPath;
	  if(pixelViPath==null)clusterFile.setData("PixelViPath",false);
	  else clusterFile.setData("PixelViPath",true);
  }
  
  /**
   * liefert die Pixelmatrix für Darstellung des Suchpfades de Klassenzentren
   * @return pixelViPath pixelViPath
   */
  private boolean[][] getPixelViPath(){
	  return pixelViPath;
  }
  
 /**
  * setzt Variable pixelString 
  * @param pixelString
  */
  private void setPixelString(String[] pixelString){
	  this.pixelString=pixelString;
	  if(pixelString==null)clusterFile.setData("PixelString",false);
	  else clusterFile.setData("PixelString",true);
  }
  
/**
 * liefert Variable pixelString  
 * @return pixelString
 */
  private String[] getPixelString(){
	  return pixelString;
  }
  
/**
 * verändert die Größe der Grafik  
 * @param zoom 1..10
 */
  private void setZoom(int zoom){
	  this.zoom=zoom;
      this.setSize((int)(zoom*100+1),(int)(zoom*100+1));
      l.setPreferredSize(new Dimension(this.getHeight(),this.getWidth()));
      f.pack();
      this.repaint();
      if(zoom==5)clusterMenuZoomDefault.setEnabled(false);
      else clusterMenuZoomDefault.setEnabled(true);
      if (zoom < 10) clusterMenuZoomOut.setEnabled(true);
      if (zoom > 1) clusterMenuZoomIn.setEnabled(true);
      if (zoom != 5) clusterMenuZoomDefault.setEnabled(true);
      if (zoom == 5){
    	  clusterMenuZoomDefault.setEnabled(false);
    	  clusterFile.setData("Zoom",false);
      }
      else clusterFile.setData("Zoom",true);
  }

/**
 * liefert die Größe der Grafik
 * @return zoom 1..10
 */
  private int getZoom(){
	  return zoom;
  }
  
/**
 * verändert den hinteren Teil des Textes in der Titelzeile
 * @param titel Text, der angezeigt werden soll
 */
  public void setTitle(String titel){
    this.title=titel;
    this.f.setTitle(titleString+title);
    if(title.equals(""))clusterFile.setData("Title",false);else clusterFile.setData("Title",true);
  }

/**
 * setzt den Fehlerstatus aus quickCheck()  
 * @param error
 */
  private void setError(boolean error){
	  this.error=error;
	  this.clusterButtonError.setEnabled(error);
	  if(error==true)clusterFile.setData("Error",true);else clusterFile.setData("Error",false);
  }

/**
 * Switch Head Up Display on/off  
 */
  private void setHeadUpDisplay(boolean headUpDisplay){
	  this.headUpDisplay=headUpDisplay;
	  if(headUpDisplay==false)clusterFile.setData("HeadUpDisplay",true);else clusterFile.setData("HeadUpDisplay",false);
  }
  
/**
 * liefert den hinteren Teil des Textes in der Titelzeile zurück
 * @return title Titel
 */
  public String getTitle(){
	  return title;
  }
  
/**
 * liefert den Fehlerzustand aus quickCheck()  
 * @return
 */
  private boolean getError(){
	  return error;
  }

/**
 * get status Head Up Display  
 */
  private boolean getHeadUpDisplay(){
	  return headUpDisplay;
  }
  
/**
   * berechnet alles neu
   */
  private void calculateCluster(){
	  if(getFuzzyCMeans())useFuzzyCMeans();
	  if(getPossibilisticCMeans())usePossibilisticCMeans();
	  if(getSortCluster())sortCluster();
	  if(getFivtyFivtyJoker())fivtyFivtyJoker();
	  if(getClusterMax())clusterMax();
	  if(getPixel())pixelMatrix();
	  if(object!=null)setObjects(getObject().length);else setObjects(0);
	  createClusterBots();
	  setCalculate(false);
	  repaint();
  }

/**
 * erzeugt lustige ClusterBots  
 */
  private void createClusterBots(){
	  clusterBot=new ClusterBot[getCluster()];
	  for(int i=0;i<getCluster();i++){
		  int l=0;
		  for(int p=0;p<getObjectDescription().length;p++){
			  if(String.valueOf(i).equals(getObjectDescription()[p]))l++;
		  }
		  Punkt2D[] punkt=new Punkt2D[l];
		  l=0;
		  for(int p=0;p<getObjectDescription().length;p++){
			  if(String.valueOf(i).equals(getObjectDescription()[p])){
				  punkt[l]=new Punkt2D(0.0,0.0);
				  punkt[l].x=getObject()[p][0];
				  punkt[l].y=getObject()[p][1];
				  l++;
			  }
		  }
		  Punkt2D center=new Punkt2D(0.0,0.0);
		  center.x=getVi()[i][0];
		  center.y=getVi()[i][1];
		  clusterBot[i]=new ClusterBot(i,String.valueOf(i),l,punkt,center);
	  }
	  if(clusterBot!=null){
		  if(clusterBot.length>0)clusterFile.setData("ClusterBot",true);
		  else clusterFile.setData("ClusterBot",false);
	  }
	  else clusterFile.setData("ClusterBot",false);
  }

/**
 * führt den Fuzzy-C-Means Algorithmus aus
 */
  public void useFuzzyCMeans(){
	  setFuzzyCMeans(true);
	  setPossibilisticCMeans(false);
	  clusterMenuSetFuzzyCMeans.setSelected(true);
	  FuzzyCMeans fcm=new FuzzyCMeans(getObject(), getCluster(), getE());
	  setVi(fcm.clusterzentrenBestimmen(false));
	  if(getPathOption())setViPath(fcm.clusterzentrenBestimmen(true));
	  else setViPath(null);
	  setMik(fcm.getMik());
	  fcm=null;
	  if(getPixel())this.pixelMatrix();
  }

 /**
  * führt den Possibilistic-C-Means Algorithmus aus 
  */
  public void usePossibilisticCMeans(){
	  setFuzzyCMeans(false);
	  setPossibilisticCMeans(true);
	  clusterMenuSetPossibilisticCMeans.setSelected(true);
	  PossibilisticCMeans pcm=new PossibilisticCMeans(getObject(), getCluster(), getDurchlauf(), getE());
	  setVi(pcm.clusterzentrenBestimmen(false));
	  if(getPathOption())setViPath(pcm.clusterzentrenBestimmen(true));
	  else setViPath(null);
	  setMik(pcm.getMik());
	  pcm=null;
	  if(getPixel())this.pixelMatrix();
  }

 /**
 * sortiert die mik, object, vi in Cluster-Reihenfolge
 */
  public void sortCluster(){
	this.setSortCluster(true);
	if((getCluster()>0)&(getMik()!=null)){
	if(getMik().length>0){
    int[]clusterSorter=new int[getMik().length];
    int[]sorterCluster=new int[getMik().length];
    double tempMik[][]=new double [getMik().length][getCluster()];
    double tempObject[][]=new double [getObject().length][2];
    double tempVi[][]=new double[getVi().length][2];
    double distanceVi[][]=new double[getVi().length*getVi().length][getVi().length];
    for(int i=0;i<getMik().length;i++){
      for(int k=0;k<getCluster();k++){
        if (getMik()[i][k]>0.5){
          clusterSorter[i] = k;
        }
      }
    }
// soriert mik
    int si=0;
    for(int k=0;k<getCluster();k++){
      for(int i=0;i<clusterSorter.length;i++){
        if (clusterSorter[i]==k){
          for(int sk=0;sk<getCluster();sk++){
            tempMik[si][sk]=getMik()[i][sk];
          }
          for(int ck=0;ck<2;ck++){
            tempObject[si][ck]=getObject()[i][ck];
            sorterCluster[si]=k;
          }
          si++;
        }
      }
    }
// sortiert vi
    for(int c=0;c<getVi().length;c++){
      for(int i=0;i<clusterSorter.length;i++){
        if (sorterCluster[i]==c){
          for(int k=0;k<getVi().length;k++){
            distanceVi[c][k]=distanceVi[c][k]+Math.sqrt(Math.pow(tempObject[i][0]-getVi()[k][0],2)+Math.pow(tempObject[i][1]-getVi()[k][1],2));
          }
        }
      }
    }
    for(int c=0;c<getVi().length;c++){
      double minDistance=100;
      int viIndex=0;
      for(int k=0;k<getVi().length;k++){
        if(distanceVi[c][k]<minDistance){
          minDistance = distanceVi[c][k];
          viIndex = k;
        }
      }
      tempVi[c][0]=getVi()[viIndex][0];
      tempVi[c][1]=getVi()[viIndex][1];
    }
    setMik(tempMik);
    setObject(tempObject);
    setVi(tempVi);
	}
	}
  }

/**
 * Cluster-Nummer zur Beschriftung in der Grafik
 * Es werden nur die Objekte mit einer Zugehörigkeit > 0.5 entsprechend beschriftet.
 */
  public void fivtyFivtyJoker(){
	setFivtyFivtyJoker(true);
    setClusterMax(false);
    this.clusterMenuSetFivtyFivtyJoker.setSelected(true);
	setObjectDescription(new String[getMik().length]);
    for(int i=0;i<getMik().length;i++){
      for(int k=0;k<getCluster();k++){
        if (getMik()[i][k]>0.5) {
        	getObjectDescription()[i]=String.valueOf(k);
        }
      }
    }
  }

/**
 * Cluster-Nummer zur Beschriftung in der Grafik
 * Alle Objekte werden mit dem Cluster beschriftet, welches die größte Zugehörigkeit hat.
 */
  public void clusterMax(){
	setFivtyFivtyJoker(false);
    setClusterMax(true);
    this.clusterMenuSetClusterMax.setSelected(true);
	setObjectDescription(new String[getMik().length]);
    for(int i=0;i<getMik().length;i++){
      double maxCluster=0;
      for(int k=0;k<getCluster();k++){
        if (getMik()[i][k]>maxCluster) {
          maxCluster=getMik()[i][k];
          getObjectDescription()[i]=String.valueOf(k);
        }
      }
    }
  }
 
/**
 * löscht ein Cluster, keine Parameterübergabe, Abfrage durch Confirm Dialog
 */
  public void deleteCluster(int clusterToDelete) {
	int tempI=0;
    int tempK=0;
    double tempObject[][]=new double[getObject().length][2];
    String tempObjectDescription[]=new String[getObject().length];
    double tempVi[][]=new double[getVi().length][2];
    double tempMik[][]=new double[getMik().length][getCluster()];
//Punkte in object[][] und mik[][] löschen
    try{
	    for(int i=0;i<getObjectDescription().length;i++){
	      if(getObjectDescription()[i]==null)getObjectDescription()[i]=String.valueOf(Integer.MIN_VALUE);
	      if(Integer.valueOf(getObjectDescription()[i])!=clusterToDelete){
	        tempObject[tempI][0]=getObject()[i][0];
	        tempObject[tempI][1]=getObject()[i][1];
	        for(int k=0;k<getCluster();k++){
	          tempMik[tempI][k]=getMik()[i][k];
	        }
	      tempI++;
	      }
	    }
    }
    catch(Exception e){
      JOptionPane.showConfirmDialog (null,e,"ClusterGraphics.deleteCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
    }
    setObject(new double [tempI][2]);
    setMik(new double [tempI][getCluster()]);
    for(int i=0;i<tempI;i++){
      for(int k=0;k<getCluster();k++){
    	  getMik()[i][k]=tempMik[i][k];
      }
      getObject()[i][0]=tempObject[i][0];
      getObject()[i][1]=tempObject[i][1];
    }
//Cluster in objectDescription[], vi[][] und mik[][] löschen
    tempMik=new double [getMik().length][getCluster()-1];
    tempI=0;
    tempK=0;
    for(int i=0;i<getCluster();i++){
      if(i!=clusterToDelete){
        for(int j=0;j<getObjectDescription().length;j++){
          if(Integer.valueOf(getObjectDescription()[j])==i){
            if(i<clusterToDelete){
              tempObjectDescription[tempI]=String.valueOf(i);
            }
            else{
              tempObjectDescription[tempI]=String.valueOf(tempK);
            }
            tempI++;
          }
        }
        tempVi[tempK][0]=getVi()[i][0];
        tempVi[tempK][1]=getVi()[i][1];
        for(int m=0;m<tempMik.length;m++){
          tempMik[m][tempK]=getMik()[m][i];
        }
        tempK++;
      }
    }
    setObjectDescription(new String[tempI]);
    for(int i=0;i<tempI;i++){
    	getObjectDescription()[i]=tempObjectDescription[i];
    }
    setVi(new double [tempK][2]);
    for(int i=0;i<tempK;i++){
    	getVi()[i][0]=tempVi[i][0];
    	getVi()[i][1]=tempVi[i][1];
    }
    setMik(new double [tempI][tempK]);
    for(int i=0;i<tempI;i++){
      for(int k=0;k<tempK;k++){
    	  getMik()[i][k]=tempMik[i][k];
      }
    }
//Clusteranzahl -1
    setCluster(getCluster()-1);
  }
  
/**
 * löscht alle nicht zugeordneten Punkte
 */
  public void deleteNotAssigned(){
	 if(getObjectDescription()!=null){
		 int countDescription=0;
		 for(int i=0;i<getObjectDescription().length;i++){
			 if(getObjectDescription()[i]==null);else countDescription++; 
		 }
		 double tempObject[][]=new double[getObject().length][2];
		 String tempObjectDescription[]=new String[getObject().length];
		 double tempMik[][]=new double[getMik().length][getCluster()];
		 tempObject=getObject();
		 tempObjectDescription=getObjectDescription();
		 tempMik=getMik();
		 setObject(new double [countDescription][2]);
		 setObjectDescription(new String[countDescription]);
		 setMik(new double[countDescription][getCluster()]);
		 int o=0;
		 for(int i=0;i<tempObjectDescription.length;i++){
			 if(tempObjectDescription[i]==null);else {
				 getObject()[o][0]=tempObject[i][0];
				 getObject()[o][1]=tempObject[i][1];
				 getObjectDescription()[o]=tempObjectDescription[i];
				 getMik()[o][0]=tempMik[i][0];
				 getMik()[o][1]=tempMik[i][1];
				 o++;
			 }
		 }
	 }
  }
  
  /**
   * berechnet die Pixel-Datenstrukturen neu
   */
  private void pixelMatrix(){
	  boolean noPixelObject=false;
	  if(!clusterFile.getData("pixelObject"))noPixelObject=true;
	  if((!getPixelOriginal())||(noPixelObject))setPixelObject(new boolean[getPixelOffset()][getPixelOffset()]);
	  setPixelVi(new boolean[getPixelOffset()][getPixelOffset()]);
	  setPixelViPath(new boolean[getPixelOffset()][getPixelOffset()]);
	  if(!getPixelOriginal())setPixelString(new String[getPixelOffset()]);
	  for(int i=0;i<getPixelOffset();i++){
		  for(int k=0;k<getPixelOffset();k++){
			  if(!getPixelOriginal())getPixelObject()[i][k]=false;
			  getPixelVi()[i][k]=false;
			  getPixelViPath()[i][k]=false;
		  }
	  }
	  int x; int y; double o; double p;
	  if(getObject()!=null){
		  if((!getPixelOriginal())||(noPixelObject)){
			  for(int i=0;i<getObject().length;i++){
				  x=0; y=0;
				  for(int t=0;t<getPixelOffset();t++){
					  o = (double)t/getPixelOffset();
					  p = o+(double)1/getPixelOffset();
					  p=Math.round(p*100.)/100.;
					  if((getObject()[i][0]>o) & (getObject()[i][0]<=p))x=t;  
					  if((getObject()[i][1]>o) & (getObject()[i][1]<=p))y=t;
					  //Vorsicht! 0 und 1 sind hier bisher als Extremwerte ausgeschlossen
				  }
				  getPixelObject()[x][y]=true;
			  }	
		  }
	  }else setPixelObject(null);
	  
	  if(getVi()!=null)if(getVi().length>0){
		  for(int i=0;i<getVi().length;i++){
			  x=0; y=0;
			  for(int t=0;t<getPixelOffset();t++){
				  o = (double)t/getPixelOffset();
				  p = o+(double)1/getPixelOffset();
				  p=Math.round(p*100.)/100.;
				  if((getVi()[i][0]>o) & (getVi()[i][0]<=p))x=t;  
				  if((getVi()[i][1]>o) & (getVi()[i][1]<=p))y=t;
				  //Vorsicht! 0 und 1 sind hier bisher als Extremwerte ausgeschlossen
			  }
			  getPixelVi()[x][y]=true;
		  }	
	  }else setPixelVi(null);
	  
	  if(getViPath()!=null){
		  for(int i=0;i<getViPath().length;i++){
			  x=0; y=0;
			  for(int t=0;t<getPixelOffset();t++){
				  o = (double)t/getPixelOffset();
				  p = o+(double)1/getPixelOffset();
				  p=Math.round(p*100.)/100.;
				  if((getViPath()[i][0]>o) & (getViPath()[i][0]<=p))x=t;  
				  if((getViPath()[i][1]>o) & (getViPath()[i][1]<=p))y=t;
				  //Vorsicht! 0 und 1 sind hier bisher als Extremwerte ausgeschlossen
			  }
			  getPixelViPath()[x][y]=true;
		  }
	  }else setPixelViPath(null);
	  
	  if(getPixelObject()!=null){
		  if(!getPixelOriginal()){
			  for(int i=0;i<getPixelOffset();i++){
				  getPixelString()[i]="";
				  for(int k=0;k<getPixelOffset();k++){
					  if(getPixelObject()[k][i]==true)getPixelString()[i]+="1";	
					  else getPixelString()[i]+="0";
				  }
			  }
		  }
	  }else setPixelString(null); 
  }
  
  /**
   * berechnet die Objektdatenstrukturen pixelObject[][] und object[][] aus der Pixelgrafik pixelString[] neu
   * (wird momentan nur beim Start aufgerufen und lässt ObjectDescription außen von, sodass da ein Fehler gemeldet wird)
   */
  private void pixelToObject(){
	  setPixelObject(new boolean[getPixelOffset()][getPixelOffset()]);
	  for(int i=0;i<getPixelOffset();i++){
		  for(int k=0;k<getPixelOffset();k++){
			  getPixelObject()[i][k]=false;
		  }
	  }
	  int objekte=0;
	  for(int i=0;i<getPixelOffset();i++){
		for(int k=0;k<getPixelOffset();k++){
			if(getPixelString()[i].substring(k,k+1).equals("1")){
				getPixelObject()[k][i]=true;
				objekte++;
			}
		}
	  }
	  setObject(new double [objekte][2]);
	  int m=0;
	  for(int i=0;i<getPixelOffset();i++){
			for(int k=0;k<getPixelOffset();k++){
				if(getPixelObject()[i][k]==true){
					getObject()[m][0]=(double)i/getPixelOffset();
					getObject()[m][1]=(double)k/getPixelOffset();
				    m++;
				}
			}
	  }
	  this.setObjects(getObject().length);
  }
  
  /**
   * ermöglicht das Hinzufügen eines Punktes
   * @param y 
   * @param x 
   */
  public void addPoint(double x, double y){
	  if(clusterFile.getData("Object")){
		  double tempObject[][]=new double [getObject().length+1][2];
		  for(int i=0;i<getObject().length;i++){
			  tempObject[i][0]=getObject()[i][0];
			  tempObject[i][1]=getObject()[i][1];
		  }
		  tempObject[getObject().length][0]=x;
		  tempObject[getObject().length][1]=y;
		  setObject(tempObject);
	  }else{
		  setObject(new double[1][2]);
		  getObject()[0][0]=x;
		  getObject()[0][1]=y;
	  }
	  setPixelOriginal(false);//th72
	  pixelMatrix();
  }
  
  /**
   * speichert sämtliche Daten in eine XML-Datei
   */
  public void save(){
	  clusterChooser.setFileFilter(clusterFileFilterXML);
	  try{if (clusterChooser.showSaveDialog(this.f) == JFileChooser.APPROVE_OPTION){
          XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
          XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(clusterChooser.getSelectedFile()));
          XMLEventFactory eventFactory = XMLEventFactory.newInstance();
          XMLEvent end = eventFactory.createDTD("\n");
  		  XMLEvent tab = eventFactory.createDTD("\t");
  		  StartDocument startDocument = eventFactory.createStartDocument();
  		  eventWriter.add(startDocument);
  		  eventWriter.add(eventFactory.createStartElement("",	"", "ClusterGraphics")); 
  		  if(clusterFile.getData(0))eventWriter.add(eventFactory.createAttribute(ClusterData.name[0], String.valueOf(getPixel())));
  		  if(clusterFile.getData(1))eventWriter.add(eventFactory.createAttribute(ClusterData.name[1], String.valueOf(getPixelDim())));
  		  if(clusterFile.getData(3))eventWriter.add(eventFactory.createAttribute(ClusterData.name[3], String.valueOf(getPixelOriginal())));
  		  if(clusterFile.getData(4))eventWriter.add(eventFactory.createAttribute(ClusterData.name[4], String.valueOf(getCluster())));
  		  if(clusterFile.getData(5))eventWriter.add(eventFactory.createAttribute(ClusterData.name[5], String.valueOf(getObjects())));
  		  if(clusterFile.getData(6))eventWriter.add(eventFactory.createAttribute(ClusterData.name[6], String.valueOf(clusterFile.getData(6))));
  		  if(clusterFile.getData(7))eventWriter.add(eventFactory.createAttribute(ClusterData.name[7], String.valueOf(clusterFile.getData(7))));
  		  if(clusterFile.getData(8))eventWriter.add(eventFactory.createAttribute(ClusterData.name[8], String.valueOf(clusterFile.getData(8))));
  		  if(clusterFile.getData(9))eventWriter.add(eventFactory.createAttribute(ClusterData.name[9], String.valueOf(clusterFile.getData(9))));
  		  if(clusterFile.getData(10))eventWriter.add(eventFactory.createAttribute(ClusterData.name[10], String.valueOf(getPathOption())));
  		  if(clusterFile.getData(11))eventWriter.add(eventFactory.createAttribute(ClusterData.name[11], String.valueOf(getDescriptionDisplay())));
  		  if(clusterFile.getData(12))eventWriter.add(eventFactory.createAttribute(ClusterData.name[12], String.valueOf(getDurchlauf())));
  		  if(clusterFile.getData(13))eventWriter.add(eventFactory.createAttribute(ClusterData.name[13], String.valueOf(clusterFile.getData(13))));
  		  if(clusterFile.getData(14))eventWriter.add(eventFactory.createAttribute(ClusterData.name[14], String.valueOf(getE())));
  		  if(clusterFile.getData(15))eventWriter.add(eventFactory.createAttribute(ClusterData.name[15], String.valueOf(getCalculate())));
  		  if(clusterFile.getData(16))eventWriter.add(eventFactory.createAttribute(ClusterData.name[16], String.valueOf(getFuzzyCMeans())));
  		  if(clusterFile.getData(17))eventWriter.add(eventFactory.createAttribute(ClusterData.name[17], String.valueOf(getPossibilisticCMeans())));
  		  if(clusterFile.getData(18))eventWriter.add(eventFactory.createAttribute(ClusterData.name[18], String.valueOf(getSortCluster())));
  		  if(clusterFile.getData(19))eventWriter.add(eventFactory.createAttribute(ClusterData.name[19], String.valueOf(getFivtyFivtyJoker())));
  		  if(clusterFile.getData(20))eventWriter.add(eventFactory.createAttribute(ClusterData.name[20], String.valueOf(getClusterMax())));
  		  if(clusterFile.getData(21))eventWriter.add(eventFactory.createAttribute(ClusterData.name[21], String.valueOf(clusterFile.getData(21))));
  		  if(clusterFile.getData(22))eventWriter.add(eventFactory.createAttribute(ClusterData.name[22], String.valueOf(clusterFile.getData(22))));
  		  if(clusterFile.getData(23))eventWriter.add(eventFactory.createAttribute(ClusterData.name[23], String.valueOf(clusterFile.getData(23))));
  		  if(clusterFile.getData(24))eventWriter.add(eventFactory.createAttribute(ClusterData.name[24], String.valueOf(clusterFile.getData(24))));
  		  if(clusterFile.getData(25))eventWriter.add(eventFactory.createAttribute(ClusterData.name[25], String.valueOf(getZoom())));
  		  if(clusterFile.getData(26))eventWriter.add(eventFactory.createAttribute(ClusterData.name[26], String.valueOf(getTitle())));
  		  if(clusterFile.getData(27))eventWriter.add(eventFactory.createAttribute(ClusterData.name[27], String.valueOf(ClusterGraphics.version)));
  		  if(clusterFile.getData(28))eventWriter.add(eventFactory.createAttribute(ClusterData.name[28], String.valueOf(ClusterGraphics.jahr)));
  		  if(clusterFile.getData(34))eventWriter.add(eventFactory.createAttribute(ClusterData.name[34], String.valueOf(getError())));
  		  if(clusterFile.getData(35))eventWriter.add(eventFactory.createAttribute(ClusterData.name[35], String.valueOf(getHeadUpDisplay())));
  		  if(clusterFile.getData("ViPath"))if(getViPath()!=null)eventWriter.add(eventFactory.createAttribute("viPathLength", String.valueOf(getViPath().length)));
  		  eventWriter.add(end);
  		  eventWriter.add(tab);
  		  //object[][], objectDescription[], mik[][]
  		  if(clusterFile.getData("Object"))if(getObject()!=null){
  			  for(int i=0;i<getObject().length;i++){
  			  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[6])); 
  			  eventWriter.add(eventFactory.createAttribute("x",String.valueOf(getObject()[i][0])));
  			  eventWriter.add(eventFactory.createAttribute("y",String.valueOf(getObject()[i][1])));
  			  if(clusterFile.getData("ObjectDescription"))if(getObjectDescription()!=null)if(getObjectDescription().length>0)eventWriter.add(eventFactory.createAttribute(ClusterData.name[7],String.valueOf(getObjectDescription()[i])));
  			  if(clusterFile.getData("Mik"))if(getMik()!=null)for(int k=0;k<getCluster();k++)eventWriter.add(eventFactory.createAttribute("k"+String.valueOf(k),String.valueOf(getMik()[i][k])));
  			  eventWriter.add(end);
  			  eventWriter.add(eventFactory.createCharacters(String.valueOf(i)));
  			  eventWriter.add(end);
  			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[6]));
  			  eventWriter.add(end);
  			  }
  		  }  
  		  //vi[][]
  		  if(clusterFile.getData("Vi"))if(getVi()!=null){
  			  for(int i=0;i<getVi().length;i++){
			  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[8])); 
			  eventWriter.add(eventFactory.createAttribute("x",String.valueOf(getVi()[i][0])));
			  eventWriter.add(eventFactory.createAttribute("y",String.valueOf(getVi()[i][1])));
			  eventWriter.add(end);
			  eventWriter.add(eventFactory.createCharacters(String.valueOf(i)));
			  eventWriter.add(end);
			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[8]));
			  eventWriter.add(end);
  			  }
  		  }
  		  //viPath[][]
  		  if(clusterFile.getData("ViPath"))if(getViPath()!=null){
  			  for(int i=0;i<getViPath().length;i++){
			  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[9])); 
			  eventWriter.add(eventFactory.createAttribute("x",String.valueOf(getViPath()[i][0])));
			  eventWriter.add(eventFactory.createAttribute("y",String.valueOf(getViPath()[i][1])));
			  eventWriter.add(end);
			  eventWriter.add(eventFactory.createCharacters(String.valueOf(i)));
			  eventWriter.add(end);
			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[9]));
			  eventWriter.add(end);
  			  }
  		  }
  		  //pixelObject[]
  		  if(clusterFile.getData("PixelObject"))if(getPixelObject()!=null){
  			  for(int i=0;i<getPixelObject().length;i++){
  				  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[21]));
  				  for(int k=0;k<getPixelObject().length;k++)eventWriter.add(eventFactory.createAttribute("k"+String.valueOf(k), String.valueOf(getPixelObject()[i][k])));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createCharacters(String.valueOf(i)));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[21]));
	  			  eventWriter.add(end);
  			  }
  		  }
  		  //pixelVi[]
  		  if(clusterFile.getData("PixelVi"))if(getPixelVi()!=null){
  			  for(int i=0;i<getPixelVi().length;i++){
  				  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[22]));
  				  for(int k=0;k<getPixelVi().length;k++)eventWriter.add(eventFactory.createAttribute("k"+String.valueOf(k), String.valueOf(getPixelVi()[i][k])));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createCharacters(String.valueOf(i)));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[22]));
	  			  eventWriter.add(end);
  			  }
  		  }
  		  //pixelViPath[]
  		  if(clusterFile.getData("PixelViPath"))if(getPixelViPath()!=null){
  			  for(int i=0;i<getPixelViPath().length;i++){
  				  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[23]));
  				  for(int k=0;k<getPixelViPath().length;k++)eventWriter.add(eventFactory.createAttribute("k"+String.valueOf(k), String.valueOf(getPixelViPath()[i][k])));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createCharacters(String.valueOf(i)));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[23]));
	  			  eventWriter.add(end);
  			  }
  		  }
  		  //pixelString[]
  		  if(clusterFile.getData("PixelString"))if(getPixelString()!=null){
  			  for(int i=0;i<getPixelString().length;i++){
  				  eventWriter.add(eventFactory.createStartElement("",	"",ClusterData.name[24])); 
  				  eventWriter.add(eventFactory.createAttribute("i", String.valueOf(i)));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createCharacters(getPixelString()[i]));
	  			  eventWriter.add(end);
	  			  eventWriter.add(eventFactory.createEndElement("", "",ClusterData.name[24]));
	  			  eventWriter.add(end);
  			  }
  		  }
  		  //Ende
  		  eventWriter.add(eventFactory.createEndElement("", "", "ClusterGraphics"));
  		  eventWriter.add(end);
  		  eventWriter.add(eventFactory.createEndDocument());
  		  eventWriter.close();
	  }}
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.write",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }
  
  /**
   * läd sämtliche Daten aus einer XML-Datei
   */
  public void open(){
	  clusterChooser.setFileFilter(clusterFileFilterXML);
	  String zversion="";
	  try{
		  if(clusterChooser.showOpenDialog(this.f) == JFileChooser.APPROVE_OPTION){
		  XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		  try{XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(clusterChooser.getSelectedFile()));
		  while (eventReader.hasNext()) {
			  XMLEvent event = eventReader.nextEvent();
			  if(event.isStartElement()) {
				  StartElement startElement = event.asStartElement();
				  if(startElement.getName().getLocalPart().equals("ClusterGraphics")){
					this.clearAll();
					@SuppressWarnings("unchecked")
					Iterator<Attribute> attributes = startElement.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if(attribute.getName().toString().equals(ClusterData.name[0]))this.setPixel(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[1]))this.setPixelDim(Integer.parseInt(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[3]))this.setPixelOriginal(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[4]))this.setCluster(Integer.parseInt(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[5]))this.setObjects(Integer.parseInt(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[6]))this.clusterFile.setData(6,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[7]))this.clusterFile.setData(7,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[8]))this.clusterFile.setData(8,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[9]))this.clusterFile.setData(9,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[10]))this.setPathOption(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[11]))this.setDescriptionDisplay(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[12]))this.setDurchlauf(Integer.parseInt(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[13]))this.clusterFile.setData(13,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[14]))this.setE(Double.parseDouble(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[15]))this.setCalculate(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[16]))this.setFuzzyCMeans(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[17]))this.setPossibilisticCMeans(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[18]))this.setSortCluster(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[19]))this.setFivtyFivtyJoker(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[20]))this.setClusterMax(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[21]))this.clusterFile.setData(21,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[22]))this.clusterFile.setData(22,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[23]))this.clusterFile.setData(23,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[24]))this.clusterFile.setData(24,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[25]))this.setZoom(Integer.parseInt(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[26]))this.setTitle(attribute.getValue());
							if(attribute.getName().toString().equals(ClusterData.name[27]))zversion=attribute.getValue();
							if(attribute.getName().toString().equals(ClusterData.name[28])) {}
							if(attribute.getName().toString().equals(ClusterData.name[34]))this.setError(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[35]))this.setHeadUpDisplay(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals("viPathLength"))setViPath(new double[Integer.parseInt(attribute.getValue())][2]);
						}
						if(clusterFile.getData("Cluster"))setVi(new double[getCluster()][2]);
						if(clusterFile.getData("Objects"))setObject(new double[getObjects()][2]);
						if(clusterFile.getData("Objects"))if(clusterFile.getData("ObjectDescription"))setObjectDescription(new String[getObjects()]);
						if(clusterFile.getData("Objects"))if(clusterFile.getData("Cluster"))if(clusterFile.getData("Mik"))setMik(new double[getObjects()][getCluster()]);
						if(clusterFile.getData("PixelObject"))setPixelObject(new boolean[getPixelOffset()][getPixelOffset()]);
						if(clusterFile.getData("PixelVi"))setPixelVi(new boolean[getPixelOffset()][getPixelOffset()]);
						if(clusterFile.getData("PixelViPath"))setPixelViPath(new boolean[getPixelOffset()][getPixelOffset()]);
						if(clusterFile.getData("PixelString"))setPixelString(new String[getPixelOffset()]);
				  }
				  //object, objectDescription, mik
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[6]))if(clusterFile.getData("Object")){
					  event = eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							int objectI;
							while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								objectI=Integer.parseInt(event.asCharacters().getData().replaceAll("\\n", ""));
								if (attribute.getName().toString().equals("x"))getObject()[objectI][0]=Double.parseDouble(attribute.getValue());
								if (attribute.getName().toString().equals("y"))getObject()[objectI][1]=Double.parseDouble(attribute.getValue());
								if (attribute.getName().toString().equals(ClusterData.name[7])){
									if(String.valueOf(attribute.getValue()).equals("null"))getObjectDescription()[objectI]=" ";
									else getObjectDescription()[objectI]=String.valueOf(attribute.getValue());
								}
								if (attribute.getName().toString().substring(0, 1).equals("k"))getMik()[objectI][Integer.parseInt(attribute.getName().toString().substring(1))]=Double.parseDouble(attribute.getValue());				  		
							}
							this.clusterFile.setData("Object",true);
				  }
				  //vi
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[8]))if(clusterFile.getData("Vi")){
					  event=eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							int viI;
					  		while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								viI=Integer.parseInt(event.asCharacters().getData().replaceAll("\\n", ""));
								if (attribute.getName().toString().equals("x"))getVi()[viI][0]=Double.parseDouble(attribute.getValue());
								if (attribute.getName().toString().equals("y"))getVi()[viI][1]=Double.parseDouble(attribute.getValue());
					  		}
					  this.clusterFile.setData("Vi",true);
				  }
				  //viPath
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[9]))if(clusterFile.getData("ViPath")){
					  event=eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							int viPathI;
					  		while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								viPathI=Integer.parseInt(event.asCharacters().getData().replaceAll("\\n", ""));
								if (attribute.getName().toString().equals("x"))getViPath()[viPathI][0]=Double.parseDouble(attribute.getValue());
								if (attribute.getName().toString().equals("y"))getViPath()[viPathI][1]=Double.parseDouble(attribute.getValue());
					  		}
					  this.clusterFile.setData("ViPath",true);
				  }
				  //pixelObject
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[21]))if(clusterFile.getData("PixelObject")){
					  event=eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							int pixelObjectI;
					  		while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								pixelObjectI=Integer.parseInt(event.asCharacters().getData().replaceAll("\\n", ""));
								if (attribute.getName().toString().substring(0, 1).equals("k"))
									getPixelObject()[pixelObjectI][Integer.parseInt(attribute.getName().toString().substring(1))]=Boolean.parseBoolean(attribute.getValue());
					  		}
					  this.clusterFile.setData("ViPath",true);
				  }
				  //pixelVi
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[22]))if(clusterFile.getData("PixelVi")){
					  event=eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							int pixelViI;
					  		while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								pixelViI=Integer.parseInt(event.asCharacters().getData().replaceAll("\\n", ""));
								if (attribute.getName().toString().substring(0, 1).equals("k"))
									getPixelVi()[pixelViI][Integer.parseInt(attribute.getName().toString().substring(1))]=Boolean.parseBoolean(attribute.getValue());
					  		}
					  this.clusterFile.setData("PixelVi",true);
				  }
				  //pixelViPath
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[23]))if(clusterFile.getData("PixelViPath")){
					  event=eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							int pixelViPathI;
					  		while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								pixelViPathI=Integer.parseInt(event.asCharacters().getData().replaceAll("\\n", ""));
								if (attribute.getName().toString().substring(0, 1).equals("k"))
									getPixelViPath()[pixelViPathI][Integer.parseInt(attribute.getName().toString().substring(1))]=Boolean.parseBoolean(attribute.getValue());
					  		}
					  this.clusterFile.setData("PixelViPath",true);
				  }
				  //pixelString
				  if (startElement.getName().getLocalPart().equals(ClusterData.name[24]))if(clusterFile.getData("PixelString")){
					  event = eventReader.nextEvent();
					  @SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								if(attribute.getName().toString().equals("i")){
									int i=Integer.parseInt(attribute.getValue());
									String s=event.asCharacters().getData().replaceAll("\\n", "");
									getPixelString()[i]=s;
								}
							}
					  this.clusterFile.setData("PixelString",true);
				  }
			  }
			  if (event.isEndElement()) {
			  }
			  }
			  }catch(Exception e){
				  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.read (XML-Fehler)",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
			  }
		  createClusterBots();
		  if(zversion.equals(version));
		  else JOptionPane.showConfirmDialog (null,"Die Dateiversion "+zversion+" weicht von der Programmversion "+version+" ab.",
				  "Cluster Graphics Hinweis",JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
		  //ende
	  }
	  }
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.read",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }
  
/**
 * Dateianalyse  
 */
  private void validation(){
	try{  
		ClusterFile validateFile= new ClusterFile();
		  String validateData[][]=new String[ClusterData.length][4];
          for(int l=0;l<ClusterData.length;l++){
        	  validateData[l][0]=ClusterData.type[l];
        	  validateData[l][1]=ClusterData.nameExtended[l];
        	  if(clusterFile.getData(l))validateData[l][2]="X"; else validateData[l][2]="";
        	  validateData[l][3]="";
          }
		if(clusterChooser.showOpenDialog(this.f) == JFileChooser.APPROVE_OPTION){
			  XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			  try{XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(clusterChooser.getSelectedFile()));
			  while (eventReader.hasNext()) {
				  XMLEvent event = eventReader.nextEvent();
				  if(event.isStartElement()) {
					  StartElement startElement = event.asStartElement();
					  if(startElement.getName().getLocalPart().equals("ClusterGraphics")){
						@SuppressWarnings("unchecked")
						Iterator<Attribute> attributes = startElement.getAttributes();
							while (attributes.hasNext()) {
								Attribute attribute = attributes.next();
								for(int f=0;f<ClusterData.length;f++){
									if(attribute.getName().toString().equals(ClusterData.name[f])){
										validateFile.setData(f,true);
										validateData[f][3]="X "+attribute.getValue();
									}
								}
								if(attribute.getName().toString().equals("viPathLength")) {}
							}
					  }
				  }
			  }  
			  }catch(Exception e){
				  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.validation (XML-Fehler)",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
			  }
		}
		  
		  
		  

          validateTable=new JTable(validateData,validateColHeads);
          validateScrollPane.setViewportView(validateTable);
          fValidate.setVisible(true);
	  }
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.validate",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }

/**
 * importiert eine PBM-Datei
 */
  public void importPBM(){
	  clusterChooser.setFileFilter(clusterFileFilterPBM);
	  try{if (clusterChooser.showOpenDialog(this.f) == JFileChooser.APPROVE_OPTION){
		  BufferedReader in = new BufferedReader(new FileReader(clusterChooser.getSelectedFile()));
		  if(in.readLine().equals("P1")){
			  String s=in.readLine();
			  String[] si={"1001","1001"};
			  while(s.startsWith("#")){
				  //Kommentarzeilen
				  s=in.readLine();
			  }
			  si=s.split(" ");
			  int siz=Integer.parseInt(si[0]);
			  if(Integer.parseInt(si[1])>siz)siz=Integer.parseInt(si[1]);;
			  int size[]=new int[2];
			  //Länge
			  size[0]=Integer.parseInt(si[0]);
			  //Breite
			  size[1]=Integer.parseInt(si[1]);
			  //PixelDim
			  if(siz<=10)setPixelDim(1);
			  else if(siz<=100)setPixelDim(2);
			  else if(siz<=1000)setPixelDim(3);
			  if(siz<=1000){
				  setPixelString(new String[getPixelOffset()]);
				  String offsetB="";
				  if(size[0]<getPixelOffset())for(int b=0;b<(getPixelOffset()-size[0]);b++)offsetB=offsetB.concat("0");
				  String offsetH="";
				  for(int h=0;h<getPixelOffset();h++)offsetH=offsetH.concat("0");
				  int i=0;
				  String inS="";
				  String inL=offsetB;
				  if(size[1]<getPixelOffset())
					  for(int h=0;h<(getPixelOffset()-size[1]);h++){
						  getPixelString()[i]=offsetH;
						  i++;
					  }
				  while((inS=in.readLine()) != null){
					  inS=inS.replace(" ", "");
					  if(inL.length()+inS.length()<=getPixelOffset()){
						  inL=inL.concat(inS);
						  if(inL.length()==getPixelOffset()){
							  getPixelString()[i]=inL;
							  i++;
							  inL=offsetB;
						  }
					  }else{
						  int rest=getPixelOffset()-inL.length()-inS.length();
						  if(rest>0){
							  String string1=inS.substring(0, rest);
							  String string2=inS.substring(rest);
							  inL=inL.concat(string1);
							  getPixelString()[i]=inL;
							  i++;
						  	  inL=string2;
						  }else{
							  String string1=inS.substring(0, getPixelOffset()-inL.length());
							  String string2=inS.substring(getPixelOffset()-inL.length());
							  inL=inL.concat(string1);
							  getPixelString()[i]=inL;
							  i++;
							  inL=offsetB;
							  while((rest=getPixelOffset()-string2.length())<0){
								  inL=string2.substring(0, getPixelOffset());
								  getPixelString()[i]=inL;
								  i++;
								  inL=offsetB;
								  string2=string2.substring(getPixelOffset());
							  }
							  if(rest>=0){
								  inL=inL.concat(string2);
								  if(rest==0){
									  getPixelString()[i]=inL;
									  i++;
									  inL=offsetB;
								  }
								  
							  }
						  }
						  
					  }
				  }
				  pixelToObject();
				  setPixelOriginal(true);
				  pixelMatrix();
				  if(!clusterFile.getData("TitleString"))this.setTitle(clusterChooser.getSelectedFile().getName());
				  f.repaint();
			  }
			  else JOptionPane.showConfirmDialog(null,"> 1000 Pixel","ClusterGraphics.importPBM",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
		  }  
		  in.close();
	  }}
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.importPBM",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }	  
  }

/**
 * exportiert eine PBM-Datei
 */
  public void exportPBM(){
	  //th72
	  clusterChooser.setFileFilter(clusterFileFilterPBM);
	  if (clusterFile.getData("PixelString")){
		  try{if (clusterChooser.showSaveDialog(this.f) == JFileChooser.APPROVE_OPTION){
			  PrintWriter out = new PrintWriter(new FileWriter (clusterChooser.getSelectedFile()));
			  out.println("P1");
			  out.println("#created by Clusterfreak ClusterGraphics "+version+" ("+jahr+")");
			  out.println(getPixelOffset()+" "+getPixelOffset());
			  for(int i=0;i<getPixelString().length;i++){
				  out.println(getPixelString()[i]);
			  }
			  out.close();
		  }}
		  catch(Exception e){
			  JOptionPane.showConfirmDialog(null,e,"ClusterGraphics.exportPBM",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
		  }
	  }
  }
  
/**
 * setzt alle Variablen zurück  
 */
  public void clearAll(){
    //Variablen
	  setPixel(true);
	  setPixelDim(2);
	//pixelOffset > setPixelDim
	  setPixelOriginal(true);
	  setCluster(0);
	//objects > setObject 
	  setObject(null);
	  setObjectDescription(new String[0]);
	  setVi(new double[getCluster()][2]);
	  setViPath(null);
	  setPathOption(false);
	  setDescriptionDisplay(false);
	  setDurchlauf(1);
	  setMik(new double[getObjects()][getCluster()]);
	  setE(1.0e-7);
	  setCalculate(false);
	  setFuzzyCMeans(false);
	  setPossibilisticCMeans(false);
	  setSortCluster(true);
	  setFivtyFivtyJoker(false);
	  setClusterMax(false);
	//pixelObject > setPixelDim
	//pixelVi > setPixelDim
	//pixelViPath > setPixelDim
	//pixelString > setPixelDim
	  setZoom(5);
	  setTitle("");
	  setError(false);
	  setHeadUpDisplay(true);
	  clusterFile.setData("Version",true);
	  clusterFile.setData("Jahr",false);
	  clusterFile.setData("TitleString",false);
	  clusterFile.setData("Ready",false);
	  clusterFile.setData("Clusterfreak",false);
	  clusterFile.setData("ClusterFile",false);
	  clusterFile.setData("ClusterBot",false);
	  clusterFile.setData("Error",false);
	  createClusterBots();
	  this.miscTable=null;
	  this.objectTable=null;
	  this.descriptionTable=null;
	  this.viTable=null;
	  this.viPathTable=null;
	  this.mikTable=null;
	  this.pixelObjectTable=null;
	  this.pixelViTable=null;
	  this.pixelViPathTable=null;
	  this.pixelStringTable=null;
	  this.clusterButtonGroupSet1.clearSelection();
	  this.clusterButtonGroupSet2.clearSelection();
	  fValidate.dispose();
	  fData.dispose();
	  fCheck.dispose();
  }
  
/**
 * einfache Konsistenzprüfung  
 */
  private boolean quickCheck(){
	  boolean quickCheckStatus=true;
	  setError(false);
  // Prüfung, ob ein Clusteralgorithmus gewählt ist
	  if((getFuzzyCMeans()==false)&&
	     (getPossibilisticCMeans()==false)){
		  JOptionPane.showConfirmDialog (null,"kein Clusteralgorithmus gewählt","ClusterGraphics.quickCheck",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
	      setError(true);
		  quickCheckStatus=false;
	  }
  // Prüfung, ob Objekte vorhanden sind
	  if((getObject()==null)){
		  JOptionPane.showConfirmDialog (null,"keine Objekte vorhanden","ClusterGraphics.quickCheck",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
	      setError(true);
		  quickCheckStatus=false;
	  }
	  return quickCheckStatus;
  }
  
/**
 * Erstellung des Checkreports  
 */
  private void checkReport(){
	  String t="";
	  fCheck.setVisible(true);
	  checkTextArea.setText("Cluster Graphics "+version+" ©"+jahr+" Thomas Heym - Check Report\n");
	  checkTextArea.append("*** begin *** ");
	  clusterDateFormat=DateFormat.getDateInstance(DateFormat.LONG);
	  checkTextArea.append(clusterDateFormat.format(clusterCalendar.getTime())+", ");
	  clusterDateFormat=DateFormat.getTimeInstance(DateFormat.LONG);
	  checkTextArea.append(clusterDateFormat.format(clusterCalendar.getTime())+"\n");
	  //0; pixel
	  //1; pixelDim
	  //2; pixelOffset
	  //3; pixelOriginal
	  //4; cluster; 8; vi.length
	  if(clusterFile.getData("Cluster")){
		  t=String.valueOf("cluster="+getCluster()+"; ");
		  if(clusterFile.getData("Vi")){
			  t=t+"vi.length="+getVi().length;
			  if(getVi().length!=getCluster()) checkTextArea.append("error; "+t+"\n");
			  else checkTextArea.append("ok; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"vi.length=?\n");
	  }
	  //4; cluster; 9; viPath.length
	  if(clusterFile.getData("Cluster")){
		  t=String.valueOf("cluster="+getCluster()+"; ");
		  if(clusterFile.getData("ViPath")){
			  t=t+"viPath.length="+getViPath().length;
			  if(getViPath().length>getCluster()) checkTextArea.append("ok; "+t+"\n");
			  else checkTextArea.append("error; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"viPath.length=?\n");
	  }
	  //4; cluster; 7; objectDescription
	  if(clusterFile.getData("Cluster")){
		  t=String.valueOf("cluster="+getCluster()+"; ");
		  if(clusterFile.getData("ObjectDescription")){
			  int objectDescriptionM=0;
			  try{
				  for(int i=0;i<getObjectDescription().length;i++){
					  if(getObjectDescription()[i]!=" "){
						  if(Integer.parseInt(getObjectDescription()[i])+1>objectDescriptionM)objectDescriptionM=Integer.parseInt(getObjectDescription()[i])+1;
					  }
				  }
			  }catch(Exception e){
				  checkTextArea.append("error; "+t+"objectDescription "+e+"\n");  
			  }
			  t=t+"objectDesciption "+objectDescriptionM;
			  if(objectDescriptionM!=getCluster())checkTextArea.append("error ; "+t+"\n");
			  else checkTextArea.append("ok; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"objectDescription ?\n");
	  }
	  //5; objects; 6; object.length
	  if(clusterFile.getData("Objects")){
		  t=String.valueOf("objects="+getObjects()+"; ");
		  if(clusterFile.getData("Object")){
			  t=t+"object.length="+getObject().length+"; ";
			  if(getObjects()==getObject().length)checkTextArea.append("ok; "+t+"\n");
			  else checkTextArea.append("error; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"object.length=?\n");
	  }
	  //5; objects; 13; mik.length
	  if(clusterFile.getData("Objects")){
		  t=String.valueOf("objects="+getObjects()+"; ");
		  if(clusterFile.getData("Mik")){
			  t=t+"mik.length="+getMik().length+"; ";
			  if(getObjects()==getMik().length)checkTextArea.append("ok; "+t+"\n");
			  else checkTextArea.append("error; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"mik.length=?\n");
	  }
      //5; object.length; 7; objectDescription.length
	  if(clusterFile.getData("Object")){
		  t=String.valueOf("object.length="+getObject().length+"; ");
		  if(clusterFile.getData("ObjectDescription")){
			  t=t+"objectDescription.length="+getObjectDescription().length;
			  if(getObject().length==getObjectDescription().length)checkTextArea.append("ok; "+t+"\n");
			  else checkTextArea.append("error; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"objectDescription.length=?\n");
	  }  
	  //5; object.length; 21; pixelOject 
	  if(clusterFile.getData("Object")){
		  t=String.valueOf("object.length="+getObject().length+"; ");
		  if(clusterFile.getData("PixelObject")){
			  int pixelObjectC=0;
			  if(getPixelObject()!=null){
				  for(int i=0;i<getPixelObject().length;i++){
					  for(int k=0;k<getPixelObject().length;k++){
						  if(getPixelObject()[i][k])pixelObjectC++;
					  }
				  }
			  }
			  t=t+"pixelObject "+pixelObjectC;
			  if(pixelObjectC==getObject().length)checkTextArea.append("ok; "+t+"\n");
			  else checkTextArea.append("error; "+t+"\n");
		  }
		  else checkTextArea.append("error; "+t+"pixelObject ?\n"); 
	  }
	  //6; object
	  //7; objectDescription
	  //8; vi
	  //9; viPath
	  //10; pathOption
	  //11; descriptionDisplay
	  //12; durchlauf
	  //13; mik
	  //e
	  //calculate
	  //fuzzyCMeans
	  //possibilisticCMeans
	  //sortCluster
	  //fivtyFivtyJoker
	  //clusterMax
	  //pixelObject
	  if(clusterFile.getData("PixelObject")){
		  if(getPixelObject()!=null){
			  if(getPixelOffset()==getPixelObject().length){
				  int pixelObjectC=0;
				  for(int i=0;i<getPixelObject().length;i++){
					  for(int k=0;k<getPixelOffset();k++){
						  if(getPixelObject()[i][k])pixelObjectC++;
					  }
				  }
				  if(pixelObjectC==getObjects())checkTextArea.append(" pixelObject - ("+pixelObjectC+" ["+getObjects()+"] ([100])) ok\n");
				  else checkTextArea.append(" pixelObject - ("+pixelObjectC+" ["+getObjects()+"] ([100]) objects) error\n");			  
			  }else checkTextArea.append(" pixelObject - ("+getPixelObject().length+" ["+getPixelOffset()+"] ([100]) pixelOffset) error\n");
		  }else{
			  if(getObjects()==0)checkTextArea.append(" pixelObject - (null ["+getObjects()+"] ([100])) ok\n");
			  else checkTextArea.append(" pixelObject - (null ["+getObjects()+"] ([100]) objects) error\n");
		  }
	  }
	  //pixelVi
	  if(clusterFile.getData("PixelVi")){
		  if(getPixelVi()!=null){
			  if(getPixelOffset()==getPixelVi().length){
				  int viC=0;
				  for(int i=0;i<getPixelVi().length;i++)for(int k=0;k<getPixelOffset();k++)if(getPixelVi()[i][k])viC++;
				  if(viC==getCluster())checkTextArea.append(" pixelVi - ("+viC+" ["+getCluster()+"] ([100])) ok\n");
				  else checkTextArea.append(" pixelVi - ("+viC+" ["+getCluster()+"] ([100]) cluster) error\n");			  
			  }else checkTextArea.append(" pixelVi - ("+getPixelVi().length+" ["+getPixelOffset()+"] ([100]) pixelOffset) error\n");
		  }else{
			  if(getCluster()==0)checkTextArea.append(" pixelVi - (null ["+getCluster()+"] ([100])) ok\n");
			  else checkTextArea.append(" pixelVi - (null ["+getCluster()+"] ([100]) cluster) error\n");
		  }
	  }
	  //pixelViPath
	  if(clusterFile.getData("PixelViPath")){
		  if(getPixelViPath()!=null){
			  if(getPixelOffset()==getPixelViPath().length){
				  int viPathC=0;
				  for(int i=0;i<getPixelViPath().length;i++)for(int k=0;k<getPixelOffset();k++)if(getPixelViPath()[i][k])viPathC++;
				  if(viPathC==getCluster())checkTextArea.append(" pixelViPath - ("+viPathC+" ["+getCluster()+"] ([100])) ok\n");
				  else checkTextArea.append(" pixelViPath - ("+viPathC+" ["+getCluster()+"] ([100]) cluster) error\n");			  
			  }else checkTextArea.append(" pixelViPath - ("+getPixelViPath().length+" ["+getPixelOffset()+"] ([100]) pixelOffset) error\n");
		  }else{
			  if(getCluster()==0)checkTextArea.append(" pixelViPath - (null ["+getCluster()+"] ([100])) ok\n");
			  else checkTextArea.append(" pixelViPath - (null ["+getCluster()+"] ([100]) cluster) error\n");
		  }
	  }
	  //pixelString
	  if(clusterFile.getData("PixelString")){
		  if(getPixelString()!=null){
			  if(getPixelOffset()==getPixelString().length){
				  if(getPixelString()[0].length()==getPixelOffset()){
					  checkTextArea.append(" pixelString - ("+getPixelString().length+" ["+getPixelOffset()+"] ([100])) ok\n");
				  }else checkTextArea.append(" pixelString - ("+getPixelString()[0].length()+" ["+getPixelOffset()+"] ([100]) pixelOffset) error\n");
			  }else checkTextArea.append(" pixelString - ("+getPixelString().length+" ["+getPixelOffset()+"] ([100]) pixelOffset) error\n");
		  }else{
			  if(getObjects()==0)checkTextArea.append(" pixelString - (null ["+getObjects()+"] ([100])) ok\n");
			  else checkTextArea.append(" pixelString - (null ["+getObjects()+"] ([100]) objects) error\n");
		  }
	  }
	  //zoom, title, version, jahr, titleString, ready
	  //clusterfreak
	  if(clusterFile.getData("Clusterfreak")){
		  int clusterfreakC=0;
		  for(int i=0;i<clusterfreak.length;i++){
			  for(int k=0;k<clusterfreak.length;k++){
				  if(clusterfreak[i].substring(k, k+1).equals("1"))clusterfreakC++;
			  }
		  }
		  checkTextArea.append(" clusterfreak - ("+clusterfreakC+" ["+clusterfreakC+"] ("+clusterfreakC+")) ok\n");
	  }
	  try{Thread.sleep(1);}catch(Exception sleep){}
	  checkTextArea.append("*** end *** ");
	  clusterDateFormat=DateFormat.getDateInstance(DateFormat.LONG);
	  checkTextArea.append(clusterDateFormat.format(clusterCalendar.getTime())+", ");
	  clusterDateFormat=DateFormat.getTimeInstance(DateFormat.LONG);
	  checkTextArea.append(clusterDateFormat.format(clusterCalendar.getTime())+"\n");
	  if(checkTextArea.getText().contains("error"))checkTextArea.append(" error\n");
	  else checkTextArea.append(" ok\n");
  }
  
/**
 * ermöglicht einen direkten Start ohne Parameterübergabe
 * @param args
 */
  public static void main(String[] args) {
	    try {
			new ClusterGraphics();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
}
