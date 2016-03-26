package cluster;
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
//javax.imageio.*
import javax.imageio.ImageIO;
//javax.swing.*
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
 * ClusterGraphix<P>
 * Display of objects and clusters with integrated cluster analysis
 * @version 0.95.0 (01-24-2016)
 * @author Thomas Heym
 */
public class ClusterGraphix extends JPanel implements ActionListener{
  private static final long serialVersionUID = -3221752010018099832L;
/**
 * General importance for conversion and saving
 */
  private boolean pixel=true;
/**
 * Pixel resolution
 */
  private int pixelDim=2;
/**
 * Pixel size
 */
  private int pixelOffset=100;
/**
 * Pixel object original indicator
 */
  private boolean pixelOriginal=true;
/**
 * Quantity/number of clusters
 */
  private int cluster=0;
/**
 * Quantity/number of objects
 */
  private int objects=0;
/**
 * Object matrix
 */
  private double object[][]=null;
/**
 * Object description with associated clusters
 */
  private String objectDescription[]=null;
/**
 * Cluster centers matrix vi
 */
  private double vi[][]=null;
/**
 * History of cluster centers detection matrix
 */
  private double viPath[][]=null;
/**
 * Option to display the history of cluster centers detection
 */
  private boolean pathOption=false;
/**
 * Option to display the object description with associated clusters
 */
  private boolean descriptionDisplay=false;
/**
 * Number of PCM passes
 */
  private int repeat=1;
/**
 * Partition matrix (Membership values of the k-th object to the i-th cluster)
 */
  private double mik[][]=null;
/**
 * Termination threshold
 */
  private double e = 1.0e-7;
/**
 * Recalculation indicator
 */
  private boolean calculate=false;
/**
 * Calculate with Fuzzy-C-Means clustering algorithm
 */
  private boolean fuzzyCMeans=false;
/**
 * Calculate with Possibilistic-C-Means clustering algorithm
 */
  private boolean possibilisticCMeans=false;
/**
 * Matrixes mik, object, vi will be sorted with objectDescription in cluster sequence
 */
  private boolean sortCluster=true;
/**
 * Object description only for affiliation >0.5
 */
  private boolean fiftyFiftyJoker=false;
/**
 * Object description according to the largest affiliation
 */
  private boolean clusterMax=false;
/**
 * Pixel object matrix
 */
  private boolean pixelObject[][]=null;
/**
 * Pixel cluster centers matrix
 */
  private boolean pixelVi[][]=null;
/**
 * Pixel history of cluster centers detection matrix
 */
  private boolean pixelViPath[][]=null;
/**
 * Pixel string memory
 */
  private String pixelString[]=null;
/**
 * Zoom factor
 */
  private int zoom=5;
/**
 * Back part of title string
 */
  private String title="";
/**
 * Program version
 */
  private String version;
/**
 * Development year of Program version
 */
  private String jahr;
/**
 * Static part of titel string
 */
  private String titleString;
/**
 * Ready indicator string for status bar
 */
  private String ready=" ready";
/**
 * Pixel image for program start
 */
  private final static String clusterfreak[] = {
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
 * Variables
 */
  private ClusterFile clusterFile= new ClusterFile();
/**
 * Cluster bot memory  
 */
  private ClusterBot[] clusterBot;
/**
 * Error status from quickCheck()  
 */
  private boolean error;
/**
 * Head up display
 */
  private boolean headUpDisplay;
/**
 * ....
 */
  private boolean random;
/**
 * Main frame
 */
  private JFrame f;
  private JLabel l=new JLabel();
/**
 * Menu main frame
 */
  private final JMenuBar clusterMenu=new JMenuBar();
  private final JMenu clusterMenuFile=new JMenu("File");
  private final JMenuItem clusterMenuFileOpen=new JMenuItem("Open");
  private final JMenuItem clusterMenuFileSave=new JMenuItem("Save");
  private final JMenuItem clusterMenuFileValidate=new JMenuItem("Validate");
  private final JMenuItem clusterMenuFileImport=new JMenuItem("Import");
  private final JMenuItem clusterMenuFileExport=new JMenuItem("Export");
  private final JMenu clusterMenuView=new JMenu("View");
  private final JCheckBoxMenuItem clusterMenuViewPixelMode=new JCheckBoxMenuItem("PixelMode",true);
  private final JCheckBoxMenuItem clusterMenuViewPathOption=new JCheckBoxMenuItem("pathOption",false);
  private final JCheckBoxMenuItem clusterMenuViewDescriptionDisplay=new JCheckBoxMenuItem("descriptionDisplay",false);
  private final JCheckBoxMenuItem clusterMenuViewHeadUpDisplay=new JCheckBoxMenuItem("headUpDisplay",true);
  private final JMenuItem clusterMenuViewRefresh=new JMenuItem("refresh");
  private final JMenu clusterMenuZoom=new JMenu("Zoom");
  private final JMenuItem clusterMenuZoomDefault=new JMenuItem("default");
  private final JMenuItem clusterMenuZoomOut=new JMenuItem("+");
  private final JMenuItem clusterMenuZoomIn=new JMenuItem("-");
  private final JMenu clusterMenuData=new JMenu("Data");
  private final JMenuItem clusterMenuDataShow=new JMenuItem("Show");
  private final JMenuItem clusterMenuDataCheck=new JMenuItem("Check");
  private final JMenu clusterMenuSet=new JMenu("Set");
  private final JMenuItem clusterMenuSetPixelDim=new JMenuItem("pixelDim");
  private final JMenuItem clusterMenuSetCluster=new JMenuItem("cluster");
  private final JMenuItem clusterMenuSetE=new JMenuItem("e");
  private final JRadioButtonMenuItem clusterMenuSetFuzzyCMeans=new JRadioButtonMenuItem("FuzzyCMeans",false);
  private final JRadioButtonMenuItem clusterMenuSetPossibilisticCMeans = new JRadioButtonMenuItem("PossibilisticCMeans",false);
  private final ButtonGroup clusterButtonGroupSet1=new ButtonGroup();
  private final JMenuItem clusterMenuSetRepeat=new JMenuItem("repeat");
  private final JCheckBoxMenuItem clusterMenuSetRandom=new JCheckBoxMenuItem("random",true);
  private final JCheckBoxMenuItem clusterMenuSetSortCluster=new JCheckBoxMenuItem("SortCluster",true);
  private final JRadioButtonMenuItem clusterMenuSetFiftyFiftyJoker=new JRadioButtonMenuItem("FiftyFiftyJoker",false);
  private final JRadioButtonMenuItem clusterMenuSetClusterMax=new JRadioButtonMenuItem("ClusterMax",false);
  private final ButtonGroup clusterButtonGroupSet2=new ButtonGroup();
  private final JMenuItem clusterMenuSetTitle=new JMenuItem("title");
  private final JMenu clusterMenuTools=new JMenu("Tools");
  private final JMenuItem clusterMenuToolsError=new JMenuItem("Error");
  private final JMenuItem clusterMenuToolsAddPoint=new JMenuItem("Add Point");
  private final JMenuItem clusterMenuToolsDelete=new JMenuItem("Delete Cluster");
  private final JMenuItem clusterMenuToolsDeleteNotAssigned=new JMenuItem("Delete not assigned");
  private final JMenuItem clusterMenuToolsClearAll=new JMenuItem("Clear All");
  private final JMenu clusterMenuHelp=new JMenu("?");
  private final JMenuItem clusterMenuHelpDataFile=new JMenuItem("DataFile");
  private final JMenuItem clusterMenuHelpInfo=new JMenuItem("Info");
  private final JToolBar clusterToolBar=new JToolBar("ClusterGraphix");
  private final JButton clusterButtonCalculate=new JButton("Calculate");
  private final JButton clusterButtonError=new JButton("Error");
  private final ThreadGroup clusterThreadGroup = new ThreadGroup( "CusterTreadGroup" );
  private final JFileChooser clusterChooser=new JFileChooser();
/**
 * FileFilter for XML-files
 */
  private final javax.swing.filechooser.FileFilter clusterFileFilterXML=new javax.swing.filechooser.FileFilter(){
	  public boolean accept(File filen) {
	      if (filen.isDirectory()) return true;
	      return filen.getName().toLowerCase().endsWith(".xml");
	  }
	  public String getDescription () { return "ClusterGraphix-Files (*.xml)"; }  
  };
/**
 * FileFilter for PBA-files
 */
  private final javax.swing.filechooser.FileFilter clusterFileFilterPBM=new javax.swing.filechooser.FileFilter(){
    public boolean accept(File filen) {
  	    if (filen.isDirectory()) return true;
  	    return filen.getName().toLowerCase().endsWith(".pbm");
   }
   public String getDescription () { return "Portable Bitmap (*.pbm)"; }  
  };  
/**
 * Status bar main frame
 */
  private final JLabel clusterStatus=new JLabel();
/**
 * FileValidation frame
 */
  private JFrame fValidate;
  private final JPanel validatePanel=new JPanel();
  private final JScrollPane validateScrollPane=new JScrollPane();
  private final String[] validateColHeads={"Typ","Variable","Daten","Datei"};
  private JTable validateTable=new JTable();
/**
 * Data frame
 */
  private JFrame fData;
  private final JTabbedPane tabbedPaneData=new JTabbedPane();
  private final JPanel objectPanel=new JPanel();
  private final JScrollPane objectScrollPane=new JScrollPane();
  private final String[] objectColHeads={"#","X","Y"};
  private JTable objectTable=new JTable();
  private final JPanel descriptionPanel=new JPanel();
  private final JScrollPane descriptionScrollPane=new JScrollPane();
  private final String[] descriptionColHeads={"#","Cluster"};
  private JTable descriptionTable=new JTable();
  private final JPanel viPanel=new JPanel();
  private final JScrollPane viScrollPane=new JScrollPane();
  private final String[] viColHeads={"#","X","Y"};
  private JTable viTable=new JTable();
  private final JPanel mikPanel=new JPanel();
  private final JScrollPane mikScrollPane=new JScrollPane();
  private String[] mikColHeads={"#","1","2"};
  private JTable mikTable=new JTable();
  private final JPanel viPathPanel=new JPanel();
  private final JScrollPane viPathScrollPane=new JScrollPane();
  private final String[] viPathColHeads={"#","X","Y"};
  private JTable viPathTable=new JTable();
  private final JPanel pixelObjectPanel=new JPanel();
  private final JScrollPane pixelObjectScrollPane=new JScrollPane();
  private String[] pixelObjectColHeads={"#","1","2"};
  private JTable pixelObjectTable=new JTable();
  private final JPanel pixelViPanel=new JPanel();
  private final JScrollPane pixelViScrollPane=new JScrollPane();
  private String[] pixelViColHeads={"#","X","Y"};
  private JTable pixelViTable=new JTable();
  private final JPanel pixelViPathPanel=new JPanel();
  private final JScrollPane pixelViPathScrollPane=new JScrollPane();
  private String[] pixelViPathColHeads={"#","X","Y"};
  private JTable pixelViPathTable=new JTable();
  private final JPanel pixelStringPanel=new JPanel();
  private final JScrollPane pixelStringScrollPane=new JScrollPane();
  private final String[] pixelStringColHeads={"#","String"};
  private JTable pixelStringTable=new JTable();
  private final JPanel miscPanel=new JPanel();
  private final JScrollPane miscScrollPane=new JScrollPane();
  private final String[] miscColHeads={"number","type","variable","value","data"};
  private JTable miscTable=new JTable();
/**
 * Check report frame
 */
  private JFrame fCheck; 
  private final JScrollPane checkScrollPane=new JScrollPane();
  private final JTextArea checkTextArea=new JTextArea();
  private final GregorianCalendar clusterCalendar=new GregorianCalendar();
  private DateFormat clusterDateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
  
/**
 * Display of objects and clusters 
 */
  public ClusterGraphix() throws InterruptedException{ 
    clusterGraphixGenerator();
    clearAll();
    setPixelString(ClusterGraphix.clusterfreak);
    pixelStringToObject();
    setPixelOriginal(true);
    pixelMatrix();
    setTitle("clusterfreak");
    f.repaint();
    Thread.sleep(500);
    clearAll();
  }

/**
 * Display of objects and clusters
 * @param     object Objects matrix
 */
  public ClusterGraphix(double object[][]){
	clusterGraphixGenerator();
    clearAll();
	setObject(object);
  }

/**
 * Display of objects and clusters
 * @param     object Objects matrix
 * @param     userTitle Back part of title string
 */
  public ClusterGraphix(double object[][], String userTitle){
	clusterGraphixGenerator();
	clearAll();
	setObject(object);
    setTitle(userTitle);
  }

/**
 * Generation of GUI
 */
  private final void clusterGraphixGenerator(){
    //Main frame
    f = new JFrame(titleString+getTitle());
    try{
    	f.setIconImage(ImageIO.read(getClass().getResource("/cluster/sphere32.png")));
    }catch(Exception e){
    	JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setIconImage",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE); 
    }
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
//Menu
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
//Menu File
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
//Menu View
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
//Menu Zoom
    clusterMenu.add(clusterMenuZoom);
    clusterMenuZoom.add(clusterMenuZoomDefault);
    clusterMenuZoom.setMnemonic('Z');
    clusterMenuZoomDefault.addActionListener(this);
    clusterMenuZoomDefault.setEnabled(false);
    clusterMenuZoom.add(clusterMenuZoomOut);
    clusterMenuZoomOut.addActionListener(this);
    clusterMenuZoom.add(clusterMenuZoomIn);
    clusterMenuZoomIn.addActionListener(this);
//Menu Data
    clusterMenu.add(clusterMenuData);
    clusterMenuData.add(clusterMenuDataShow);
    clusterMenuData.setMnemonic('D');
    clusterMenuDataShow.addActionListener(this);
    clusterMenuData.add(clusterMenuDataCheck);
    clusterMenuDataCheck.addActionListener(this);
//Menu Set
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
    clusterMenuSet.add(clusterMenuSetRepeat);
    clusterMenuSetRepeat.addActionListener(this);
    clusterMenuSet.add(clusterMenuSetRandom);
    clusterMenuSetRandom.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
        	setRandom(clusterMenuSetRandom.isSelected());
        }
      });
    clusterMenuSet.addSeparator();
    clusterMenuSet.add(clusterMenuSetSortCluster);
    clusterMenuSetSortCluster.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
        	setSortCluster(clusterMenuSetSortCluster.isSelected());
        }
      });
    clusterMenuSetSortCluster.addActionListener(this);
    clusterMenuSet.addSeparator();
    clusterMenuSet.add(clusterMenuSetFiftyFiftyJoker);
    clusterMenuSetFiftyFiftyJoker.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
    	  setFiftyFiftyJoker(clusterMenuSetFiftyFiftyJoker.isSelected());
      }
    });
    clusterMenuSetFiftyFiftyJoker.addActionListener(this);
    clusterButtonGroupSet2.add(clusterMenuSetFiftyFiftyJoker);
    
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
//Menu Tools
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
//Menu ?
    clusterMenu.add(clusterMenuHelp);
    clusterMenuHelp.add(clusterMenuHelpDataFile);
    clusterMenuHelp.setMnemonic('D');
    clusterMenuHelpDataFile.addActionListener(this);
    clusterMenuHelp.add(clusterMenuHelpInfo);
    clusterMenuHelp.setMnemonic('?');
    clusterMenuHelpInfo.addActionListener(this);
    clusterMenuHelpInfo.setMnemonic('I');
//File chooser
    clusterChooser.addChoosableFileFilter(clusterFileFilterXML);
    clusterChooser.addChoosableFileFilter(clusterFileFilterPBM);
    clusterChooser.setFileFilter(clusterFileFilterXML);
    clusterChooser.setMultiSelectionEnabled(false);
//Main frame
    f.pack();
    f.setVisible(true);
//FileValidation frame
    fValidate= new JFrame(titleString+" Validate");
    try{
    	fValidate.setIconImage(ImageIO.read(getClass().getResource("/cluster/sphere32.png")));
    }catch(Exception e){
    	JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setIconImage",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE); 
    }
    fValidate.setSize(500,500);
    validatePanel.setLayout(new GridLayout(1,1));
    validatePanel.add(validateScrollPane,BorderLayout.CENTER);
    fValidate.getContentPane().add(validatePanel);
//Data frame
    fData= new JFrame(titleString+" Data");
    try{
    	fData.setIconImage(ImageIO.read(getClass().getResource("/cluster/sphere32.png")));
    }catch(Exception e){
    	JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setIconImage",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE); 
    }
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
//Check report frame
    fCheck= new JFrame(titleString+" Check");
    try{
    	fCheck.setIconImage(ImageIO.read(getClass().getResource("/cluster/sphere32.png")));
    }catch(Exception e){
    	JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setIconImage",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE); 
    }
    fCheck.setLayout(new BorderLayout());
    fCheck.setSize(500,500);
    checkScrollPane.setViewportView(this.checkTextArea);
    fCheck.getContentPane().add(checkScrollPane);
  }

/**
 * Processing of menu and button events
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
                		//clusterStatus.setText(ready);
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
            		int response = Integer.valueOf((String) JOptionPane.showInputDialog(null,"set Cluster","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,String.valueOf(altCluster))).intValue();
            		if(response!=altCluster){
            			setCluster(response);
            			setCalculate(true);
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
            		String oldTitle=getTitle();
            		String response = JOptionPane.showInputDialog(null,"set title ("+String.valueOf(oldTitle)+")","ClusterGraphix",JOptionPane.QUESTION_MESSAGE);
            		if(response!=oldTitle){
            			setTitle(response);
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setTitle",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
            		double response = Double.valueOf(JOptionPane.showInputDialog(null,"set e ("+String.valueOf(altE)+")","ClusterGraphix",JOptionPane.QUESTION_MESSAGE)).doubleValue();
            		if(response!=altE){
            			setE(response);
            			setCalculate(true);
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setE",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
            		int response = Integer.valueOf((String) JOptionPane.showInputDialog(null,"set pixelDim","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,String.valueOf(altPixelDim))).intValue();
            		if(response!=altPixelDim){
            			if((response<1)||(response>2)){
    	            		JOptionPane.showConfirmDialog (null,"Nur Werte zwischen 1 und 2 erlaubt.","ClusterGraphix.setPixelDim",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
                		}
                		else{
                			setPixelDim(response);
                			setCalculate(true);
                		}
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.setPixelDim",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
//setRepeat
    if ("repeat".equals(actionCommand)){
    	clusterStatus.setText(" setRepeat");
    	Thread clusterThread = new Thread(clusterThreadGroup,"repeat") {
            public void run() {
            	try{
            		int oldRepeat=getRepeat();
            		int response=Integer.valueOf(JOptionPane.showInputDialog(null,"set repeat ("+String.valueOf(oldRepeat)+")","ClusterGraphix",JOptionPane.QUESTION_MESSAGE)).intValue();
            		if(response!=oldRepeat){
            			if((response<1)||(response>10)){
    	            		JOptionPane.showConfirmDialog (null,"Nur Werte zwischen 1 und 10 erlaubt.","ClusterGraphix.setRepeat",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
                		}
                		else{
                			setRepeat(response);
                			setCalculate(true);
                		}
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.setRepeat",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
    	("FiftyFiftyJoker".equals(actionCommand))||
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
            		if(pixel){
                		int responseX = Integer.valueOf((String) JOptionPane.showInputDialog(null,"Add Point - X","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,"")).intValue();
                		int responseY = Integer.valueOf((String) JOptionPane.showInputDialog(null,"Add Point - Y","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,"")).intValue();
                	    addPointPixelObject(responseX,responseY);            			
            		}
            		else{
                		double responseX = Double.valueOf((String) JOptionPane.showInputDialog(null,"Add Point - X","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,".")).doubleValue();
                		double responseY = Double.valueOf((String) JOptionPane.showInputDialog(null,"Add Point - Y","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,".")).doubleValue();
                	    addPointObject(responseX,responseY);	
            		}
            	    setCalculate(true);
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.addPoint",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
            		int response = Integer.valueOf((String) JOptionPane.showInputDialog(null,"Delete Cluster","ClusterGraphix",JOptionPane.QUESTION_MESSAGE,null,null,"")).intValue();
            		if(response>getCluster()-1){
	            		JOptionPane.showConfirmDialog (null,"Cluster "+response+" ist nicht vorhanden.","ClusterGraphix.deleteCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	            		
            		}
            		else{
            			deleteCluster(response);
            			calculateCluster();
            		}
            	}catch(Exception e){
            		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.deleteCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
                  		JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.deleteNotAssigned",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
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
//DataFile
    if ("DataFile".equals(actionCommand)){
  	  clusterStatus.setText(" DataFile");
  	  Thread clusterThread = new Thread(clusterThreadGroup,"DataFile") {
            public void run() {
          		dataFile();
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
            	JOptionPane.showConfirmDialog (null,"Copyright 1999-"+jahr+" Thomas Heym","ClusterGraphix "+version,JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
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
      String miscData[][]=new String[ClusterData.length][5];
      for(int l=0;l<ClusterData.length;l++){
    	  miscData[l][0]=ClusterData.indexString2[l];
    	  miscData[l][1]=ClusterData.type[l];
    	  miscData[l][2]=ClusterData.nameExtended[l];
    	  if(clusterFile.getData(l))miscData[l][4]="X";else miscData[l][4]="";
      }
      miscData[0][3]=String.valueOf(getPixel());
      miscData[1][3]=String.valueOf(getPixelDim());
      miscData[2][3]=String.valueOf(getPixelOffset());
      miscData[3][3]=String.valueOf(getPixelOriginal());
      miscData[4][3]=String.valueOf(getCluster());
      miscData[5][3]=String.valueOf(getObjects());
      if(getObject()!=null)miscData[6][3]="["+String.valueOf(getObject().length)+"]";else miscData[6][3]="[null]";
      if(getObjectDescription()!=null)miscData[7][3]="["+String.valueOf(getObjectDescription().length)+"]";else miscData[7][3]="[null]";
      if(getVi()!=null)miscData[8][3]="["+String.valueOf(getVi().length)+"]";else miscData[8][3]="[null]";
      if(getViPath()!=null)miscData[9][3]="["+String.valueOf(getViPath().length)+"]";else miscData[9][3]="[null]";
      miscData[10][3]=String.valueOf(getPathOption());
      miscData[11][3]=String.valueOf(getDescriptionDisplay());
      miscData[12][3]=String.valueOf(getRepeat());
      if(getMik()!=null)miscData[13][3]="["+String.valueOf(getMik().length)+"]";else miscData[13][3]="[null]";
      miscData[14][3]=String.valueOf(getE());
      miscData[15][3]=String.valueOf(getCalculate());
      miscData[16][3]=String.valueOf(getFuzzyCMeans());
      miscData[17][3]=String.valueOf(getPossibilisticCMeans());
      miscData[18][3]=String.valueOf(getSortCluster());
      miscData[19][3]=String.valueOf(getFiftyFiftyJoker());
      miscData[20][3]=String.valueOf(getClusterMax());
      if(getPixelObject()!=null)miscData[21][3]="["+String.valueOf(getPixelObject().length)+"]";else miscData[21][3]="[null]";
      if(getPixelVi()!=null)miscData[22][3]="["+String.valueOf(getPixelVi().length)+"]";else miscData[22][3]="[null]";
      if(getPixelViPath()!=null)miscData[23][3]="["+String.valueOf(getPixelViPath().length)+"]";else miscData[23][3]="[null]";
      if(getPixelString()!=null)miscData[24][3]="["+String.valueOf(getPixelString().length)+"]";else miscData[24][3]="[null]";
      miscData[25][3]=String.valueOf(getZoom());
      miscData[26][3]=getTitle();
      miscData[27][3]=this.version;
      miscData[28][3]=this.jahr;
      miscData[29][3]=this.titleString;
      miscData[30][3]=this.ready;
      miscData[31][3]="["+String.valueOf(ClusterGraphix.clusterfreak.length)+"]";
      miscData[32][3]=String.valueOf(this.clusterFile.hashCode());
      if(clusterBot!=null)miscData[33][3]="["+String.valueOf(clusterBot.length)+"]";else miscData[33][3]="[null]";
      miscData[34][3]=String.valueOf(getError());
      miscData[35][3]=String.valueOf(getHeadUpDisplay());
      miscData[36][3]=String.valueOf(getRandom());
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
        JOptionPane.showConfirmDialog (null,"Maximale Vergrößerung erreicht.","ClusterGraphix Hinweis",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
        clusterMenuZoomOut.setEnabled(false);
      }
    }
    if ("-".equals(actionCommand)){
      if (getZoom() > 1)setZoom(getZoom()-1);
      else{
        JOptionPane.showConfirmDialog (null,"Maximale Verkleinerung erreicht.","ClusterGraphix Hinweis",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
        clusterMenuZoomIn.setEnabled(false);
      }
    }
  }

  protected void paintComponent(Graphics g){
    try{
      Graphics2D g2 = (Graphics2D) g;
      //White background, clear
      g2.setBackground(Color.white);
      g2.clearRect(0,0,f.getWidth(),f.getHeight());
      //Gray gridlines
      g2.setColor(Color.gray);
      g2.setStroke(new BasicStroke(getZoom()/3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      for(int k = 1; k < 10; k++) {
        g2.drawLine(0,getZoom()*k*10,getZoom()*100,getZoom()*k*10);
        g2.drawLine(getZoom()*k*10,0,getZoom()*k*10,getZoom()*100);
      }
      //Black frame
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
      //Yellow points (Path)
      if ((getPathOption()==true) & (getViPath()!=null)){
        g2.setColor(Color.yellow);
        for(int k = 0; k < getViPath().length; k++){
          g2.fillOval((int)(getViPath()[k][0]*getZoom()*100-getZoom()),
                      (int)(getViPath()[k][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
        }
      }
      //Blue points (objects)
      if(getObject()!=null){
    	  g2.setColor(Color.blue);
	      for(int k = 0; k < getObject().length; k++){
	        g2.fillOval((int)(getObject()[k][0]*getZoom()*100-getZoom()),
	                    (int)(getObject()[k][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
	      //Description of blue points
	        if(getDescriptionDisplay()==true){
	          if(getObjectDescription()[k]!=null){
	            g2.drawString(getObjectDescription()[k],(int)(getObject()[k][0]*getZoom()*100-getZoom()),
	                                               (int)(getObject()[k][1]*getZoom()*100-getZoom()));
	          }
	        }
	      }
      }
      //Red points (clusters)
      if(getVi()!=null){
    	  g2.setColor(Color.red);
	      if(getVi().length > 1){
	        for(int k=getVi().length-getCluster();k<getVi().length;k++){
	          g2.fillOval((int)(getVi()[k][0]*getZoom()*100-getZoom()),
	                      (int)(getVi()[k][1]*getZoom()*100-getZoom()),getZoom()*2,getZoom()*2);
	        //Description for red points
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
      //cluserBot lines
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
    	  int x=5; int y=15; int z=15;//00 pixel
    	  if(clusterFile.getData("pixel"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("pixel")+" "+ClusterData.getName("pixel"),x,y); 
    	  y=y+z;//03 pixelOriginal
    	  if(clusterFile.getData("pixelOriginal"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("pixelOriginal")+" "+ClusterData.getName("pixelOriginal"),x,y);
    	  y=y+z;//04 cluster
    	  if(clusterFile.getData("Cluster"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("cluster")+" "+ClusterData.getName("cluster")+": "+getCluster(),x,y); 
    	  y=y+z;//05 objects
    	  if(clusterFile.getData("Objects"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("objects")+" "+ClusterData.getName("objects")+": "+getObjects(),x,y); 
    	  y=y+z;//07 objectDescription
    	  if(clusterFile.getData("objectDescription"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("objectDescription")+" "+ClusterData.getName("objectDescription"),x,y); 
    	  y=y+z;//08 vi
    	  if(clusterFile.getData("Vi"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  if(getVi()!=null)g2.drawString(ClusterData.getIndexString2("vi")+" "+ClusterData.getName("vi")+": "+String.valueOf(getVi().length),x,y);
    	              else g2.drawString(ClusterData.getIndexString2("vi")+" "+ClusterData.getName("vi")+": 0",x,y); 
    	  y=y+z;//33 clusterBot
    	  if(clusterFile.getData("ClusterBot"))g2.setColor(Color.green);else g2.setColor(Color.red);
    	  g2.drawString(ClusterData.getIndexString2("clusterBot")+" "+ClusterData.getName("clusterBot"),x,y); 
    	  y=y+z;
      }
      clusterMenu.updateUI();
  }
  catch(Exception e){
      JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.paintComponent",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
    }
  }

/**
 * Switch for pixel and normal mode 
 * @param pixelMode true false
 */
  public void setPixel(boolean pixelMode){
	  this.pixel=pixelMode;
	  this.clusterMenuViewPixelMode.setSelected(pixelMode);
	  if(pixel)pixelMatrix();
	  if(pixel)clusterFile.setData("Pixel",false);else clusterFile.setData("Pixel",true);
  }
  
/**
 * Pixel mode active?  
 * @return pixel yes/no
 */
  public boolean getPixel(){
	  return pixel;
  }

/**
 * Set pixel resolution
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
 * Get pixel resolution  
 * @return pixelDim 1..2
 */
  public int getPixelDim(){
	  return pixelDim;
  }

/**
 * Get pixel size 
 * @return pixelOffset
 */
  private int getPixelOffset(){
	  return pixelOffset;
  }
  
 /**
  * Set Pixel object or Double object as the original
  * @param pixelOriginal
  */
  private void setPixelOriginal(boolean pixelOriginal){
	  this.pixelOriginal=pixelOriginal;
	  if(pixelOriginal)clusterFile.setData("PixelOriginal",false);else clusterFile.setData("PixelOriginal",true);
  }
  
/**
 * Pixel object or Double object as the original  
 * @return pixelOriginal pixelOriginal
 */
  private boolean getPixelOriginal(){
	  return pixelOriginal;
  }
  
/**
 * Set number of clusters
 * @param cluster New number of clusters
 */
  public void setCluster(int cluster){
    this.cluster=cluster;
    if(cluster==0)this.clusterFile.setData("Cluster",false);else this.clusterFile.setData("Cluster",true);
  }

/**
 * Get number of clusters
 * @return cluster Actual number of clusters
 */
  public int getCluster(){
    return cluster;
  }

/**
 * Set number of objects  
 * @param objects Number of objects
 */
  private void setObjects(int objects){
	  this.objects=objects;
	  if(objects==0)this.clusterFile.setData("Objects",false);else this.clusterFile.setData("Objects",true);
  }
 
/**
 * Get number of objects  
 * @return objects Number of objects
 */
  private int getObjects(){
	  return objects;
  }
  
/**
 * Set new objects matrix, clear existing object matrix, reset number of objects
 * @param clusterObject Objects matrix
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
 * Get objects matrix 
 * @return object Objects matrix
 */
  public double[][] getObject(){
    return object;
  }

/**
 * Set description of objects
 * @param objectDescription
 */
  private void setObjectDescription(String[] objectDescription){ 
	  this.objectDescription=objectDescription;
	  if(objectDescription!=null){
		  if(objectDescription.length>0){
			    boolean data=false;
			    for(int i=0;i<getObjectDescription().length;i++)
			    	if(getObjectDescription()[i]==null);
			    	else if(!getObjectDescription()[i].equals(""))data=true;
			    if(data)clusterFile.setData("objectDescription", true);
			    else    clusterFile.setData("objectDescription", false);
		  }
		  else clusterFile.setData("ObjectDescription",false);
	  }
	  else clusterFile.setData("ObjectDescription",false);
  }
  
/**
 * Set description of objects (membership values for clusters)
 * @return objectDescription
 */
  private String[] getObjectDescription(){
	return objectDescription;
  }
  
/**
 * Set cluster centers  
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
 * Get cluster centers
 * @return vi
 */
  public double[][] getVi(){
    return vi;
  }

/**
 * Set the history of cluster centers detection matrix  
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
 * Get the history of cluster centers detection matrix 
 * @return viPath viPath
 */
  private double[][] getViPath(){
	  return viPath;
  }
  
/**
 * Set the option to display the history of cluster centers detection
 * @param path true/false
 */
  public void setPathOption(boolean path){
    this.pathOption=path;
    this.clusterMenuViewPathOption.setState(path);
    if(pathOption)clusterFile.setData("PathOption",true);else clusterFile.setData("PathOption",false);
  }

/**
 * Get the option to display the history of cluster centers detection
 * @return pathOption pathOption
 */
  public boolean getPathOption(){
	  return pathOption;
  }
  
/**
  * Set the option to display the object description with associated clusters
  * @param descriptionDisplay true/false
  */
  public void setDescriptionDisplay(boolean descriptionDisplay) {
    this.descriptionDisplay=descriptionDisplay;
    this.clusterMenuViewDescriptionDisplay.setState(descriptionDisplay);
    if(descriptionDisplay)clusterFile.setData("DescriptionDisplay",true);else clusterFile.setData("DescriptionDisplay",false);
  }

/**
 * Get the option to display the object description with associated clusters
 * @return descriptionDisplay true/false
 */
  public boolean getDescriptionDisplay(){
	  return descriptionDisplay;
  }
  
/**
 * Set number of PCM passes (normaly 1)
 * @param number Number of passes
 */
  public void setRepeat(int number){
    this.repeat=number;
    if(repeat==1)clusterFile.setData("Repeat",false);else clusterFile.setData("Repeat",true);
  }

/**
 * Set number of PCM passes
 * @return repeat Number of passes
 */
  public int getRepeat(){
	  return repeat;
  }

/**
 * Set partition matrix (Membership values of the k-th object to the i-th cluster) 
 * @param mik Partition matrix
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
 * Get partition matrix (Membership values of the k-th object to the i-th cluster)
 * @return mik Partition matrix
 */
  public double[][] getMik(){
    return mik;
  }

/**
 * Set termination threshold (normaly 1.0e-7)
 * @param e Termination threshold
 */
  public void setE(double e){
    this.e=e;
    if(e==1.0e-7)clusterFile.setData("E",false);
    else clusterFile.setData("E",true);
  }

/**
 * Get termination threshold  
 * @return e Termination threshold
 */
  public double getE(){
	  return e;
  }
  
/**
 * Set recalculation indicator  
 * @param calc
 */
  private void setCalculate(boolean calc){
	  this.calculate=calc;
	  this.clusterButtonCalculate.setEnabled(calc);
	  if(calculate)clusterFile.setData("Calculate",true);else clusterFile.setData("Calculate",false);
  }

/**
 * Get recalculation indicator 
 * @return calculate calculate
 */
  private boolean getCalculate(){
	  return calculate;
  }
  
/**
 * Set flag to calculate with Fuzzy-C-Means clustering algorithm  
 * @param fcm true false
 */
  private void setFuzzyCMeans(boolean fcm){
	  this.fuzzyCMeans=fcm;
	  this.clusterMenuSetFuzzyCMeans.setSelected(fcm);
	  if(fuzzyCMeans)clusterFile.setData("FuzzyCMeans",true);else clusterFile.setData("FuzzyCMeans",false);
  }

/**
 * Get flag to calculate with Fuzzy-C-Means clustering algorithm   
 * @return fuzzyCMeans
 */
  private boolean getFuzzyCMeans(){
	  return fuzzyCMeans;
  }
  
/**
 * Set flag to calculate with Possibilistic-C-Means clustering algorithm  
 * @param pcm
 */
  private void setPossibilisticCMeans(boolean pcm){
	  this.possibilisticCMeans=pcm;
	  this.clusterMenuSetPossibilisticCMeans.setSelected(pcm);
	  if(possibilisticCMeans)clusterFile.setData("PossibilisticCMeans",true);else clusterFile.setData("PossibilisticCMeans",false);
  }

/**
 * Get flag to calculate with Possibilistic-C-Means clustering algorithm 
 * @return possibilisticCMeans
 */
  private boolean getPossibilisticCMeans(){
	  return possibilisticCMeans;
  }

/**
 * Set random  
 * @param random
 */
  private void setRandom(boolean random){
	  this.random=random;
	  this.clusterMenuSetRandom.setSelected(random);
	  if(random)clusterFile.setData("Random",false);else clusterFile.setData("Random",true);
  }
  
/**
 * Get random  
 * @return
 */
  private boolean getRandom(){
	  return random;
  }
  
/**
 * Set flag for sorting matrixes mik, object, vi with objectDescription in cluster sequence
 * @param sortCluster
 */
  private void setSortCluster(boolean sortCluster){
	  this.sortCluster=sortCluster;
	  this.clusterMenuSetSortCluster.setSelected(sortCluster);
	  if(sortCluster)clusterFile.setData("SortCluster",false);else clusterFile.setData("SortCluster",true);
  }

/**
 * Get flag for sorting matrixes mik, object, vi with objectDescription in cluster sequence  
 * @return sortCluster
 */
  private boolean getSortCluster(){
	  return sortCluster;
  }
  
/**
 * Set flag for object description only for affiliation >0.5  
 * @param fiftyFiftyJoker true false
 */
  private void setFiftyFiftyJoker(boolean fiftyFiftyJoker){
	  this.fiftyFiftyJoker=fiftyFiftyJoker;
	  this.clusterMenuSetFiftyFiftyJoker.setSelected(fiftyFiftyJoker);
	  if(fiftyFiftyJoker)clusterFile.setData("FiftyFiftyJoker",true);else clusterFile.setData("FiftyFiftyJoker",false);
  }

/**
 * Get flag for object description only for affiliation >0.5 
 * @return fiftyFiftyJoker
 */
  private boolean getFiftyFiftyJoker(){
	  return fiftyFiftyJoker;
  }
  
/**
 * Set flag for object description according to the largest affiliation  
 * @param clusterMax true false
 */
  private void setClusterMax(boolean clusterMax){
	  this.clusterMax=clusterMax;
	  this.clusterMenuSetClusterMax.setSelected(clusterMax);
	  if(clusterMax)clusterFile.setData("ClusterMax",true);else clusterFile.setData("ClusterMax",false);
  }
 
  
/**
 * Get flag for object description according to the largest affiliation   
 * @return clusterMax
 */
  private boolean getClusterMax(){
	  return clusterMax;
  }
  
  /**
   * Set pixel object matrix
   * @param pixelObject
   */
  private void setPixelObject(boolean pixelObject[][]){
	  this.pixelObject=pixelObject;
	  if(pixelObject==null)clusterFile.setData("PixelObject",false);
	  else clusterFile.setData("PixelObject",true);
  }
  
  /**
   * Get pixel object matrix
   * @return pixelObject
   */
  private boolean[][] getPixelObject(){
	  return pixelObject;
  }
  
  /**
   * Set pixel cluster centers matrix
   * @param pixelVi
   */
  private void setPixelVi(boolean pixelVi[][]){
	  this.pixelVi=pixelVi;
	  if(pixelVi==null)clusterFile.setData("PixelVi",false);
	  else clusterFile.setData("PixelVi",true);
  }
  
  /**
   * Get pixel cluster centers matrix
   * @return pixelVi pixelVi
   */
  private boolean[][] getPixelVi(){
	  return pixelVi;
  }
  
  /**
   * Set pixel history of cluster centers detection matrix
   * @param pixelViPath
   */
  private void setPixelViPath(boolean pixelViPath[][]){
	  this.pixelViPath=pixelViPath;
	  if(pixelViPath==null)clusterFile.setData("PixelViPath",false);
	  else clusterFile.setData("PixelViPath",true);
  }
  
  /**
   * Get pixel history of cluster centers detection matrix
   * @return pixelViPath pixelViPath
   */
  private boolean[][] getPixelViPath(){
	  return pixelViPath;
  }
  
 /**
  * Set pixel string memory 
  * @param pixelString
  */
  private void setPixelString(String[] pixelString){
	  this.pixelString=pixelString;
	  if(pixelString==null)clusterFile.setData("PixelString",false);
	  else clusterFile.setData("PixelString",true);
  }
  
/**
 * Get pixel string memory 
 * @return pixelString
 */
  private String[] getPixelString(){
	  return pixelString;
  }
  
/**
 * Set zoom factor  
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
 * Get zoom factor
 * @return zoom 1..10
 */
  private int getZoom(){
	  return zoom;
  }
  
/**
 * Set the back part of title string for main frame and first part for other frames
 * @param titel
 */
  public void setTitle(String titel){
    this.title=titel;
    this.f.setTitle(titleString+title);
    this.fCheck.setTitle(titel+" Check");
    this.fData.setTitle(titel+" Data");
    this.fValidate.setTitle(titel+" Validate");
    if(title.equals(""))clusterFile.setData("Title",false);else clusterFile.setData("Title",true);
  }
  
/**
 * Get the back part of title string
 * @return title
 */
  public String getTitle(){
	  return title;
  }
  
/**
 * Set error status from quickCheck()  
 * @param error
 */
  private void setError(boolean error){
	  this.error=error;
	  this.clusterButtonError.setEnabled(error);
	  if(error==true)clusterFile.setData("Error",true);else clusterFile.setData("Error",false);
  }
  
/**
 * Get error status from quickCheck()  
 * @return
 */
  private boolean getError(){
	  return error;
  }
  
/**
 * Switch head up display on/off  
 * @param headUpDisplay
 */
  private void setHeadUpDisplay(boolean headUpDisplay){
	  this.headUpDisplay=headUpDisplay;
	  if(headUpDisplay==false)clusterFile.setData("HeadUpDisplay",true);else clusterFile.setData("HeadUpDisplay",false);
  }
  
/**
 * Get status of head up display  
 */
  private boolean getHeadUpDisplay(){
	  return headUpDisplay;
  }
  
/**
 * Recalculation of all
 */
  private void calculateCluster(){
	  final long timeBegin = System.currentTimeMillis(); 
	  clusterStatus.setText(" calculate with Fuzzy-C-Means clustering algorithm");
	  if(getFuzzyCMeans())useFuzzyCMeans();
	  clusterStatus.setText(" calculate with Possibilistic-C-Means clustering algorithm");
	  if(getPossibilisticCMeans())usePossibilisticCMeans();
	  clusterStatus.setText(" calculate -> sortCluster()");
	  if(getSortCluster())sortCluster();
	  clusterStatus.setText(" calculate -> fiftyFiftyJoker()");
	  if(getFiftyFiftyJoker())fiftyFiftyJoker();
	  clusterStatus.setText(" calculate -> clusterMax()");
	  if(getClusterMax())clusterMax();
	  clusterStatus.setText(" calculate -> createClusterBots()");
	  createClusterBots();
	  clusterStatus.setText(" calculate -> pixelMatrix()");
	  if(getPixel())pixelMatrix();
	  clusterStatus.setText(" calculate -> set/getObject()");
	  if(object!=null)setObjects(getObject().length);else setObjects(0);
	  clusterStatus.setText(" calculate");
	  setCalculate(false);
	  repaint();
	  long timeEnd = System.currentTimeMillis()-timeBegin;
	  clusterStatus.setText(ready+" ("+String.valueOf(timeEnd)+"ms)");
  }

/**
 * Generates Cluster Bots  
 */
  private void createClusterBots(){
	  clusterBot=new ClusterBot[getCluster()];
	  for(int i=0;i<getCluster();i++){
		  int l=0;
		  for(int p=0;p<getObjectDescription().length;p++){
			  if(String.valueOf(i).equals(getObjectDescription()[p]))l++;
		  }
		  Point2D[] point2D=new Point2D[l];
		  PointPixel[] pointPixel=new PointPixel[l];
		  l=0;
		  for(int p=0;p<getObjectDescription().length;p++){
			  if(String.valueOf(i).equals(getObjectDescription()[p])){
				  point2D[l]=new Point2D(0.0,0.0);
				  point2D[l].x=getObject()[p][0];
				  point2D[l].y=getObject()[p][1];
				  pointPixel[l]=point2D[l].toPointPixel(getPixelOffset());
				  l++;
			  }
		  }
		  Point2D center=new Point2D(0.0,0.0);
		  center.x=getVi()[i][0];
		  center.y=getVi()[i][1];
		  clusterBot[i]=new ClusterBot(i,String.valueOf(i),l,point2D,center);
		  for(int ip=0;ip<pointPixel.length;ip++){
			  clusterBot[i].addPointPixel(pointPixel[ip]);
		  }
		  clusterBot[i].setCenterPixel(center.toPointPixel(getPixelOffset()));
	  }
	  if(clusterBot!=null){
		  if(clusterBot.length>0)clusterFile.setData("ClusterBot",true);
		  else clusterFile.setData("ClusterBot",false);
	  }
	  else clusterFile.setData("ClusterBot",false);
  }

/**
 * Use the Fuzzy-C-Means clustering algorithm
 */
  public void useFuzzyCMeans(){
	  setFuzzyCMeans(true);
	  setPossibilisticCMeans(false);
	  clusterMenuSetFuzzyCMeans.setSelected(true);
	  FuzzyCMeans fcm=new FuzzyCMeans(getObject(), getCluster(), getE());
	  setVi(fcm.determineClusterCenters(random, false));
	  if(getPathOption())setViPath(fcm.determineClusterCenters(random, true));
	  else setViPath(null);
	  setMik(fcm.getMik());
	  fcm=null;
	  if(getPixel())this.pixelMatrix();
  }

 /**
  * Use the Possibilistic-C-Means clustering algorithm
  */
  public void usePossibilisticCMeans(){
	  setFuzzyCMeans(false);
	  setPossibilisticCMeans(true);
	  clusterMenuSetPossibilisticCMeans.setSelected(true);
	  PossibilisticCMeans pcm=new PossibilisticCMeans(getObject(), getCluster(), getRepeat(), getE());
	  setVi(pcm.determineClusterCenters(random, false));
	  if(getPathOption())setViPath(pcm.determineClusterCenters(random, true));
	  else setViPath(null);
	  setMik(pcm.getMik());
	  pcm=null;
	  if(getPixel())this.pixelMatrix();
  }

 /**
 * Matrixes mik, object, vi will be sorted with objectDescription in cluster sequence
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
//sorting mik
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
//sorting vi
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
 * Produce object description only for affiliation >0.5
 */
  public void fiftyFiftyJoker(){
	setFiftyFiftyJoker(true);
    setClusterMax(false);
    this.clusterMenuSetFiftyFiftyJoker.setSelected(true);
	setObjectDescription(new String[getMik().length]);
    for(int i=0;i<getMik().length;i++){
      for(int k=0;k<getCluster();k++){
        if (getMik()[i][k]>0.5) {
        	getObjectDescription()[i]=String.valueOf(k);
        }
      }
    }
    boolean data=false;
    for(int i=0;i<getObjectDescription().length;i++)
    	if(getObjectDescription()[i]==null);
    	else if(!getObjectDescription()[i].equals(""))data=true;
    if(data)clusterFile.setData("objectDescription", true);
    else    clusterFile.setData("objectDescription", false);
  }

/**
 * Produce object description according to the largest affiliation
 */
  public void clusterMax(){
	setFiftyFiftyJoker(false);
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
    boolean data=false;
    for(int i=0;i<getObjectDescription().length;i++)
    	if(getObjectDescription()[i]==null);
    	else if(!getObjectDescription()[i].equals(""))data=true;
    if(data)clusterFile.setData("objectDescription", true);
    else    clusterFile.setData("objectDescription", false);
  }
 
/**
 * Deletes all objects of a cluster, no parameters, input with confirm dialog
 */
  public void deleteCluster(int clusterToDelete) {
	int tempI=0;
    int tempK=0;
    double tempObject[][]=new double[getObject().length][2];
    String tempObjectDescription[]=new String[getObject().length];
    double tempVi[][]=new double[getVi().length][2];
    double tempMik[][]=new double[getMik().length][getCluster()];
//Delete objects in object[][] and mik[][]
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
      JOptionPane.showConfirmDialog (null,e,"ClusterGraphix.deleteCluster",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
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
//Delete objects in objectDescription[], vi[][] und mik[][]
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
//Number of clusters -1
    setCluster(getCluster()-1);
  }
  
/**
 * Deletes Objects witch not assigned to a cluster
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
   * Recalculate pixel matrixes
   */
  private void pixelMatrix(){
	  boolean noPixelObject=false;
	  Point2D point2D = new Point2D(0.0,0.0);
	  PointPixel pointPixel = new PointPixel(0,0);
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
	  if(getObject()!=null){
		  if((!getPixelOriginal())||(noPixelObject)){
			  for(int i=0;i<getObject().length;i++){
				  point2D=new Point2D(getObject()[i][0],getObject()[i][1]);
				  pointPixel=point2D.toPointPixel(getPixelOffset());
				  getPixelObject()[pointPixel.x][pointPixel.y]=true;
			  }	
		  }
	  }else setPixelObject(null);
	  
	  if(getVi()!=null)if(getVi().length>0){
		  for(int i=0;i<getVi().length;i++){
			  point2D=new Point2D(getVi()[i][0],getVi()[i][1]);
			  pointPixel=point2D.toPointPixel(getPixelOffset());
			  getPixelVi()[pointPixel.x][pointPixel.y]=true;
		  }	
	  }else setPixelVi(null);
	  
	  if(getViPath()!=null){
		  for(int i=0;i<getViPath().length;i++){
			  point2D=new Point2D(getViPath()[i][0],getViPath()[i][1]);
			  pointPixel=point2D.toPointPixel(getPixelOffset());
			  getPixelViPath()[pointPixel.x][pointPixel.y]=true;
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
   * Recalculate pixelObject[][] and object[][] object matrixes from pixel string memory pixelString[]
   * (used at start, not for objectDescription jet, error)
   */
  private void pixelStringToObject(){
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
   * Add an object with doubles
   * @param y 
   * @param x 
   */
  public void addPointObject(double x, double y){
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
	  setPixelOriginal(false);
	  pixelMatrix();
  }
  
  /**
   * Add an object with pixels
   * @param y 
   * @param x 
   */
  public void addPointPixelObject(int x, int y){
	  if(!clusterFile.getData("pixelString")){
		  setPixelString(new String[getPixelOffset()]);
		  for(int i=0;i<getPixelOffset();i++){
			  String s="";
			  for(int k=0;k<(getPixelOffset());k++)s=s.concat("0");
			  getPixelString()[i]=s;
		  }  
	  }	 
	  getPixelString()[y]=getPixelString()[y].substring(0,x)+'1'+getPixelString()[y].substring(x+1);
	  pixelStringToObject();
	  setPixelOriginal(true);
	  pixelMatrix();
	  f.repaint();	  
  }
  
  /**
   * Save all data in an XML-file
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
  		  eventWriter.add(eventFactory.createStartElement("",	"", "ClusterGraphix")); 
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
  		  if(clusterFile.getData(12))eventWriter.add(eventFactory.createAttribute(ClusterData.name[12], String.valueOf(getRepeat())));
  		  if(clusterFile.getData(13))eventWriter.add(eventFactory.createAttribute(ClusterData.name[13], String.valueOf(clusterFile.getData(13))));
  		  if(clusterFile.getData(14))eventWriter.add(eventFactory.createAttribute(ClusterData.name[14], String.valueOf(getE())));
  		  if(clusterFile.getData(15))eventWriter.add(eventFactory.createAttribute(ClusterData.name[15], String.valueOf(getCalculate())));
  		  if(clusterFile.getData(16))eventWriter.add(eventFactory.createAttribute(ClusterData.name[16], String.valueOf(getFuzzyCMeans())));
  		  if(clusterFile.getData(17))eventWriter.add(eventFactory.createAttribute(ClusterData.name[17], String.valueOf(getPossibilisticCMeans())));
  		  if(clusterFile.getData(18))eventWriter.add(eventFactory.createAttribute(ClusterData.name[18], String.valueOf(getSortCluster())));
  		  if(clusterFile.getData(19))eventWriter.add(eventFactory.createAttribute(ClusterData.name[19], String.valueOf(getFiftyFiftyJoker())));
  		  if(clusterFile.getData(20))eventWriter.add(eventFactory.createAttribute(ClusterData.name[20], String.valueOf(getClusterMax())));
  		  if(clusterFile.getData(21))eventWriter.add(eventFactory.createAttribute(ClusterData.name[21], String.valueOf(clusterFile.getData(21))));
  		  if(clusterFile.getData(22))eventWriter.add(eventFactory.createAttribute(ClusterData.name[22], String.valueOf(clusterFile.getData(22))));
  		  if(clusterFile.getData(23))eventWriter.add(eventFactory.createAttribute(ClusterData.name[23], String.valueOf(clusterFile.getData(23))));
  		  if(clusterFile.getData(24))eventWriter.add(eventFactory.createAttribute(ClusterData.name[24], String.valueOf(clusterFile.getData(24))));
  		  if(clusterFile.getData(25))eventWriter.add(eventFactory.createAttribute(ClusterData.name[25], String.valueOf(getZoom())));
  		  if(clusterFile.getData(26))eventWriter.add(eventFactory.createAttribute(ClusterData.name[26], String.valueOf(getTitle())));
  		  if(clusterFile.getData(27))eventWriter.add(eventFactory.createAttribute(ClusterData.name[27], String.valueOf(this.version)));
  		  if(clusterFile.getData(28))eventWriter.add(eventFactory.createAttribute(ClusterData.name[28], String.valueOf(this.jahr)));
  		  if(clusterFile.getData(34))eventWriter.add(eventFactory.createAttribute(ClusterData.name[34], String.valueOf(getError())));
  		  if(clusterFile.getData(35))eventWriter.add(eventFactory.createAttribute(ClusterData.name[35], String.valueOf(getHeadUpDisplay())));
  		  if(clusterFile.getData(36))eventWriter.add(eventFactory.createAttribute(ClusterData.name[36], String.valueOf(getRandom())));
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
  		  eventWriter.add(eventFactory.createEndElement("", "", "ClusterGraphix"));
  		  eventWriter.add(end);
  		  eventWriter.add(eventFactory.createEndDocument());
  		  eventWriter.close();
	  }}
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.write",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }
  
  /**
   * Read all the data from an XML-file
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
				  if(startElement.getName().getLocalPart().equals("ClusterGraphix")){
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
							if(attribute.getName().toString().equals(ClusterData.name[12]))this.setRepeat(Integer.parseInt(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[13]))this.clusterFile.setData(13,Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[14]))this.setE(Double.parseDouble(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[15]))this.setCalculate(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[16]))this.setFuzzyCMeans(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[17]))this.setPossibilisticCMeans(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[18]))this.setSortCluster(Boolean.parseBoolean(attribute.getValue()));
							if(attribute.getName().toString().equals(ClusterData.name[19]))this.setFiftyFiftyJoker(Boolean.parseBoolean(attribute.getValue()));
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
							if(attribute.getName().toString().equals(ClusterData.name[36]))this.setRandom(Boolean.parseBoolean(attribute.getValue()));
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
				  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.read (XML-Fehler)",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
			  }
		  createClusterBots();
		  if(zversion.equals(version));
		  else JOptionPane.showConfirmDialog (null,"The file version "+zversion+" is different from program version "+version,
				  "ClusterGraphix Note",JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
		  //ende
	  }
	  }
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.read",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }
  
/**
 * File validation  
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
					  if(startElement.getName().getLocalPart().equals("ClusterGraphix")){
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
				  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.validation (XML-Error)",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
			  }
		}
		  
		  
		  

          validateTable=new JTable(validateData,validateColHeads);
          validateScrollPane.setViewportView(validateTable);
          fValidate.setVisible(true);
	  }
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.validate",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }

/**
 * Imports a PBM-file
 */
  public void importPBM(){
	  clusterChooser.setFileFilter(clusterFileFilterPBM);
	  try{if (clusterChooser.showOpenDialog(this.f) == JFileChooser.APPROVE_OPTION){
		  BufferedReader in = new BufferedReader(new FileReader(clusterChooser.getSelectedFile()));
		  if(in.readLine().equals("P1")){
			  String s=in.readLine();
			  String[] si={"1001","1001"};
			  while(s.startsWith("#")){
				  //comment lines
				  s=in.readLine();
			  }
			  si=s.split(" ");
			  int siz=Integer.parseInt(si[0]);
			  if(Integer.parseInt(si[1])>siz)siz=Integer.parseInt(si[1]);;
			  int size[]=new int[2];
			  //length
			  size[0]=Integer.parseInt(si[0]);
			  //width
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
				  pixelStringToObject();
				  setPixelOriginal(true);
				  pixelMatrix();
				  setTitle(clusterChooser.getSelectedFile().getName());
				  f.repaint();
			  }
			  else JOptionPane.showConfirmDialog(null,"> 1000 Pixel","ClusterGraphix.importPBM",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
		  }  
		  in.close();
	  }}
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.importPBM",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }	  
  }

/**
 * Exports a PBM-file
 */
  public void exportPBM(){
	  clusterChooser.setFileFilter(clusterFileFilterPBM);
	  if (clusterFile.getData("PixelString")){
		  try{if (clusterChooser.showSaveDialog(this.f) == JFileChooser.APPROVE_OPTION){
			  PrintWriter out = new PrintWriter(new FileWriter (clusterChooser.getSelectedFile()));
			  out.println("P1");
			  out.println("#created by Clusterfreak ClusterGraphix "+version+" ("+jahr+")");
			  out.println(getPixelOffset()+" "+getPixelOffset());
			  for(int i=0;i<getPixelString().length;i++){
				  out.println(getPixelString()[i]);
			  }
			  out.close();
		  }}
		  catch(Exception e){
			  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.exportPBM",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
		  }
	  }
  }
  
/**
 * Resets all variables  
 */
  public void clearAll(){
    //Variablen
	  setPixel(true);//0
	  setPixelDim(2);//1
	//pixelOffset > setPixelDim//2
	  setPixelOriginal(true);//3
	  setCluster(0);//4
	//objects > setObject//5 
	  setObject(null);//6
	  setObjectDescription(new String[0]);//7
	  setVi(new double[getCluster()][2]);//8
	  setViPath(null);//9
	  setPathOption(false);//10
	  setDescriptionDisplay(false);//11
	  setRepeat(1);//12
	  setMik(new double[getObjects()][getCluster()]);//13
	  setE(1.0e-7);//14
	  setCalculate(false);//15
	  setFuzzyCMeans(false);//16
	  setPossibilisticCMeans(false);//17
	  setSortCluster(true);//18
	  setFiftyFiftyJoker(false);//19
	  setClusterMax(false);//20
	//pixelObject > setPixelDim//21
	//pixelVi > setPixelDim//22
	//pixelViPath > setPixelDim//23
	//pixelString > setPixelDim//24
	  setZoom(5);//25
	  setTitle("");//26
	  this.version=ClusterData.initial[ClusterData.getIndexInt("version")];clusterFile.setInitial("Version");//27
	  this.jahr=ClusterData.initial[ClusterData.getIndexInt("jahr")];clusterFile.setInitial("Jahr");//28
	  this.titleString=ClusterData.initial[ClusterData.getIndexInt("titleString")];	  clusterFile.setInitial("TitleString");//29
	  this.ready=ClusterData.initial[ClusterData.getIndexInt("ready")];clusterFile.setInitial("Ready");//30
	  clusterFile.setInitial("Clusterfreak");//31
	  clusterFile.setInitial("ClusterFile");//32
	  createClusterBots();clusterFile.setInitial("ClusterBot");//33
	  setError(false);//34
	  clusterFile.setInitial("Error");//34
	  setHeadUpDisplay(true);//35
	  setRandom(true);//36
//user interface reset
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
 * Simple consistency check 
 */
  private boolean quickCheck(){
	  boolean quickCheckStatus=true;
	  setError(false);
  //Check, if a clustering algorithm is selected
	  if((getFuzzyCMeans()==false)&&
	     (getPossibilisticCMeans()==false)){
		  JOptionPane.showConfirmDialog (null,"no clustering algorithm selected","ClusterGraphix.quickCheck",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
	      setError(true);
		  quickCheckStatus=false;
	  }
  //Check, if objects present
	  if((getObjects()==0)){
		  JOptionPane.showConfirmDialog (null,"no objects","ClusterGraphix.quickCheck",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
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
	  checkTextArea.setText("ClusterGraphix "+version+" ©"+jahr+" Thomas Heym - Check Report\n");
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
	  //12; repeat
	  //13; mik
	  //e
	  //calculate
	  //fuzzyCMeans
	  //possibilisticCMeans
	  //sortCluster
	  //fiftyFiftyJoker
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
	  //clusterBot-Info
	  checkTextArea.append("clusterBot-Info\n");
	  if(clusterFile.getData("clusterBot")){
		  for(int i=0;i<clusterBot.length;i++){
			  checkTextArea.append(clusterBot[i].getNumber()+" - "+
					  			   clusterBot[i].getName()+" ("+
					  			   clusterBot[i].getPoints()+" Points, x="+
					  			   clusterBot[i].getCenter().x+", y="+
					  			   clusterBot[i].getCenter().y+", "+
					  			   clusterBot[i].getPointsPixel()+" Pixelpoints, x="+
					  			   clusterBot[i].getCenterPixel().x+", y="+
					  			   clusterBot[i].getCenterPixel().y+")\n");
			  for(int p=0;p<clusterBot[i].getPointsPixel();p++){
				  checkTextArea.append("	Point "+p+" ("+clusterBot[i].getPointPixel()[p].x+", "+clusterBot[i].getPointPixel()[p].x+")\n");
			  }
		  }
	  }
  }

/**
 * Exports an HTML-file with the metadata structure
 */
  private void dataFile(){
	  try{
		  PrintWriter dataFile = new PrintWriter(new FileWriter ("DataFile.html"));
		  String tab="";
		  dataFile.println("<!doctype html>");
		  dataFile.println("<html lang=\"en\">");
		  tab="\t";
		  //<head>
		  dataFile.println(tab+"<head>");
		  tab=tab.concat("\t");
		  dataFile.println(tab+"<meta charset=\"utf-8\">");
		  dataFile.println(tab+"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		  dataFile.println(tab+"<title>ClusterGraphix-DataFile</title>");
		  dataFile.println(tab+"<link rel=\"shortcut icon\" href=\"http://clusterfreak.com/favicon.ico\" type=\"image/x-icon\">");
		  tab=tab.substring(1);
		  dataFile.println(tab+"</head>");
		  //<body>
		  dataFile.println(tab+"<body bgcolor=\"#f0f8ff\" text=\"#001C66\">");
		  tab=tab.concat("\t");
		  dataFile.println(tab+"<font face=\"Arial\">");
		  dataFile.println(tab+"ClusterGraphix "+version+" "+jahr);
		  //<table>
		  dataFile.println(tab+"<table border=\"1\" bgcolor=#ffffff bordercolor=#000099>");
		  tab=tab.concat("\t");
		  dataFile.println(tab+"<p style=\"font-size:small\">");
		  dataFile.println(tab+"<tr bgcolor=#BDBDBD>");
		  tab=tab.concat("\t");
		  dataFile.println(tab+"<th align=center rowspan=\"2\">number</th>");
		  dataFile.println(tab+"<th align=left colspan=\"12\"><font color=#04B431>description</font></th>");
		  tab=tab.substring(1);
		  dataFile.println(tab+"</tr>");
		  dataFile.println(tab+"<tr bgcolor=#BDBDBD>");
		  tab=tab.concat("\t");
		  dataFile.println(tab+"<th align=left><font color=#FA5858>type</font></th>");
		  dataFile.println(tab+"<th align=left>"+"name"+"</th>");
		  dataFile.println(tab+"<th align=left>"+"initial"+"</th>");
		  dataFile.println(tab+"<th align=left>"+"gui"+"</th>");
		  dataFile.println(tab+"<th align=left><font color=#FA5858>"+"set"+"</font></th>");
		  dataFile.println(tab+"<th align=left><font color=#FA5858>"+"get"+"</font></th>");
		  dataFile.println(tab+"<th align=center>"+"edit"+"</th>");
		  dataFile.println(tab+"<th align=center>"+"save"+"</th>");
		  dataFile.println(tab+"<th align=center>"+"Load"+"</th>");
		  dataFile.println(tab+"<th align=left>"+"nameCapital"+"</th>");
		  dataFile.println(tab+"<th align=left>"+"nameExtended"+"</th>");
		  dataFile.println(tab+"<th align=left>"+"data"+"</th>");
		  tab=tab.substring(1);
		  dataFile.println(tab+"</tr>");
		  for(int i=0;i<ClusterData.length;i++){
			  dataFile.println(tab+"<tr>");
			  tab=tab.concat("\t");
			  dataFile.println(tab+"<th align=center rowspan=\"2\">"+ClusterData.number[i]+"</th>");
			  dataFile.println(tab+"<th align=left colspan=\"12\"><font color=#04B431>"+ClusterData.description[i]+"</font></th>");
			  tab=tab.substring(1);
			  dataFile.println(tab+"</tr>");
			  dataFile.println(tab+"<tr>");
			  tab=tab.concat("\t");
			  dataFile.println(tab+"<th align=left><font color=#FA5858>"+ClusterData.type[i]+"</font></th>");
			  dataFile.println(tab+"<th align=left>"+ClusterData.name[i]+"</th>");
			  dataFile.println(tab+"<th align=left>"+ClusterData.initial[i]+"</th>");
			  dataFile.println(tab+"<th align=left>"+ClusterData.gui[i]+"</th>");
			  dataFile.println(tab+"<th align=left><font color=#FA5858>"+ClusterData.set[i]+"</font></th>");
			  dataFile.println(tab+"<th align=left><font color=#FA5858>"+ClusterData.get[i]+"</font></th>");
			  dataFile.println(tab+"<th align=center>"+ClusterData.edit[i]+"</th>");
			  dataFile.println(tab+"<th align=center>"+ClusterData.save[i]+"</th>");
			  dataFile.println(tab+"<th align=center>"+ClusterData.load[i]+"</th>");
			  dataFile.println(tab+"<th align=left>"+ClusterData.nameCapital[i]+"</th>");
			  dataFile.println(tab+"<th align=left>"+ClusterData.nameExtended[i]+"</th>");
			  dataFile.println(tab+"<th align=left>"+ClusterData.data[i]+"</th>");
			  tab=tab.substring(1);
			  dataFile.println(tab+"</tr>");
		  }
		  dataFile.println(tab+"</p>");
		  tab=tab.substring(1);
		  dataFile.println(tab+"</table>");
		  tab=tab.substring(1);
		  dataFile.println(tab+"</body>");
		  tab=tab.substring(1);
		  dataFile.println(tab+"</html>");
		  dataFile.close();
	  }
	  catch(Exception e){
		  JOptionPane.showConfirmDialog(null,e,"ClusterGraphix.dataFile",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);  
	  }
  }
  
/**
 * Starts the program without parameters
 * @param args
 */
  public static void main(String[] args) {
	    try {
			new ClusterGraphix();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
}