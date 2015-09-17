import java.lang.String;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Cluster Master<P>
 * Testprogramm zur Demonstration der Clusteranalyse
 * @version 0.4.3 (29.06.2012)
 * @author Thomas Heym
 */
public class ClusterMaster extends JFrame {
  private static final long serialVersionUID = -647438477344019030L;
  private String[] jComboBoxClassDaten = {};
  private JComboBox<String> jComboBoxClass = new JComboBox<String>(jComboBoxClassDaten);
  //object
  private String[] jComboBoxObjectDaten = {};
  private JComboBox<String> jComboBoxObject = new JComboBox<String>(jComboBoxObjectDaten);
  //cluster
  private String[] jComboBoxClusterDaten = {};
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private JComboBox jComboBoxCluster = new JComboBox(jComboBoxClusterDaten);
  //e
  private String[] jComboBoxEDaten = {};
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private JComboBox jComboBoxE = new JComboBox(jComboBoxEDaten);
  //durchlauf
  private String[] jComboBoxDurchlaufDaten = {};
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private JComboBox jComboBoxDurchlauf = new JComboBox(jComboBoxDurchlaufDaten);
  //ClusterGraphics
  private JCheckBox jCheckBoxSortCluster = new JCheckBox();
  private JCheckBox jCheckBoxFivtyFivtyJoker = new JCheckBox();
  private JCheckBox jCheckBoxClusterMax = new JCheckBox();
  private JCheckBox jCheckBoxClusterGraphics = new JCheckBox();
  private JCheckBox jCheckBoxDescriptionDisplay = new JCheckBox();

  private JButton jButton1 = new JButton();
  
  private static double test2[][] = {{0.2,0.2},{0.2,0.4},{0.4,0.2},
                                    {0.4,0.4},{0.3,0.3},
                                    {0.75,0.75},{0.85,0.85},
                                    {0.85,0.75},{0.75,0.85},

                                    {0.7,0.7},{0.7,0.9},{0.9,0.7},
                                    {0.9,0.9},{0.8,0.8},
                                    {0.25,0.25},{0.35,0.35},
                                    {0.35,0.25},{0.25,0.35}
                                   };
  private static double test3[][] = {{0.2,0.2},{0.2,0.4},{0.4,0.2},
                                     {0.4,0.4},
                                     {0.75,0.75},{0.85,0.85},
                                     {0.85,0.75},{0.75,0.85},

                                     {0.7,0.7},{0.7,0.9},{0.9,0.7},
                                     {0.9,0.9},
                                     {0.25,0.25},{0.35,0.35},
                                     {0.35,0.25},{0.25,0.35},

                                     {0.9,0.1},{0.8,0.2},{0.9,0.2}
                                    };
                                    
  private static double test4[][]={
    {0.2,0.1},{0.25,0.1},{0.3,0.1},{0.35,0.1},{0.4,0.1},
    {0.15,0.15},{0.25,0.15},{0.35,0.15},{0.4,0.15},
    {0.1,0.2},{0.15,0.2},{0.2,0.2},{0.25,0.2},{0.3,0.2},{0.35,0.2},{0.4,0.2},
    {0.1,0.25},{0.15,0.25},{0.2,0.25},{0.25,0.25},{0.3,0.25},{0.4,0.25},
    {0.1,0.3},{0.15,0.3},{0.2,0.3},{0.25,0.3},{0.3,0.3},{0.35,0.3},{0.4,0.3},
    {0.1,0.35},{0.15,0.35},{0.2,0.35},{0.25,0.35},{0.3,0.35},{0.35,0.35},
    {0.1,0.4},{0.15,0.4},{0.2,0.4},{0.25,0.4},{0.3,0.4},
    
    {0.7,0.2},{0.75,0.2},{0.8,0.2},{0.85,0.2},{0.9,0.2},
    {0.65,0.25},{0.75,0.25},{0.85,0.25},{0.9,0.25},
    {0.6,0.3},{0.65,0.3},{0.7,0.3},{0.75,0.3},{0.8,0.3},{0.85,0.3},{0.9,0.3},
    {0.6,0.35},{0.65,0.35},{0.7,0.35},{0.75,0.35},{0.8,0.35},{0.9,0.35},
    {0.6,0.4},{0.65,0.4},{0.7,0.4},{0.75,0.4},{0.8,0.4},{0.85,0.4},{0.9,0.4},
    {0.6,0.45},{0.65,0.45},{0.7,0.45},{0.75,0.45},{0.8,0.45},{0.85,0.45},
    {0.6,0.5},{0.65,0.5},{0.7,0.5},{0.75,0.5},{0.8,0.5},
    
    {0.2,0.6},{0.25,0.6},{0.3,0.6},{0.35,0.6},{0.4,0.6},
    {0.15,0.65},{0.25,0.65},{0.35,0.65},{0.4,0.65},
    {0.1,0.7},{0.15,0.7},{0.2,0.7},{0.25,0.7},{0.3,0.7},{0.35,0.7},{0.4,0.7},
    {0.1,0.75},{0.15,0.75},{0.2,0.75},{0.25,0.75},{0.3,0.75},{0.4,0.75},
    {0.1,0.8},{0.15,0.8},{0.2,0.8},{0.25,0.8},{0.3,0.8},{0.35,0.8},{0.4,0.8},
    {0.1,0.85},{0.15,0.85},{0.2,0.85},{0.25,0.85},{0.3,0.85},{0.35,0.85},
    {0.1,0.9},{0.15,0.9},{0.2,0.9},{0.25,0.9},{0.3,0.9},

    {0.6,0.6},{0.65,0.6},{0.7,0.6},{0.75,0.6},{0.8,0.6},
    {0.55,0.65},{0.65,0.65},{0.75,0.65},{0.8,0.65},
    {0.5,0.7},{0.55,0.7},{0.6,0.7},{0.65,0.7},{0.7,0.7},{0.75,0.7},{0.8,0.7},
    {0.5,0.75},{0.55,0.75},{0.6,0.75},{0.65,0.75},{0.7,0.75},{0.8,0.75},
    {0.5,0.8},{0.55,0.8},{0.6,0.8},{0.65,0.8},{0.7,0.8},{0.75,0.8},{0.8,0.8},
    {0.5,0.85},{0.55,0.85},{0.6,0.85},{0.65,0.85},{0.7,0.85},{0.75,0.85},
    {0.5,0.9},{0.55,0.9},{0.6,0.9},{0.65,0.9},{0.7,0.9}
  };

  private static double smily[][]={
    {0.425,0.2},{0.45,0.2},{0.475,0.2},{0.5,0.2},{0.525,0.2},{0.55,0.2},{0.575,0.2},
    {0.4,0.225},{0.425,0.225},{0.45,0.225},{0.475,0.225},{0.5,0.225},{0.525,0.225},{0.55,0.225},{0.575,0.225},{0.6,0.225},
    {0.4,0.25},{0.425,0.25},{0.45,0.25},{0.55,0.25},{0.575,0.25},{0.6,0.25},
    
    {0.325,0.25},{0.35,0.25},{0.375,0.25},{0.625,0.25},{0.65,0.25},{0.675,0.25},
    {0.3,0.275},{0.325,0.275},{0.35,0.275},{0.375,0.275},{0.4,0.275},{0.6,0.275},{0.625,0.275},{0.65,0.275},{0.675,0.275},{0.7,0.275},
    {0.3,0.3},{0.325,0.3},{0.35,0.3},{0.65,0.3},{0.675,0.3},{0.7,0.3},
    
    {0.275,0.3},{0.725,0.3},
    {0.25,0.325},{0.275,0.325},{0.3,0.325},{0.7,0.325},{0.725,0.325},{0.75,0.325},
    {0.25,0.35},{0.275,0.35},{0.3,0.35},{0.7,0.35},{0.725,0.35},{0.75,0.35},
    {0.25,0.375},{0.275,0.375},{0.725,0.375},{0.75,0.375},
    {0.25,0.4},{0.275,0.4},{0.725,0.4},{0.75,0.4},
    
    {0.225,0.4},{0.775,0.4},
    {0.2,0.425},{0.225,0.425},{0.25,0.425},{0.75,0.425},{0.775,0.425},{0.8,0.425},
    {0.2,0.45},{0.225,0.45},{0.25,0.45},{0.75,0.45},{0.775,0.45},{0.8,0.45},
    {0.2,0.475},{0.225,0.475},{0.775,0.475},{0.8,0.475},
    {0.2,0.5},{0.225,0.5},{0.775,0.5},{0.8,0.5},
    {0.2,0.525},{0.225,0.525},{0.775,0.525},{0.8,0.525},
    {0.2,0.55},{0.225,0.55},{0.25,0.55},{0.75,0.55},{0.775,0.55},{0.8,0.55},
    {0.2,0.575},{0.225,0.575},{0.25,0.575},{0.75,0.575},{0.775,0.575},{0.8,0.575},
    {0.225,0.6},{0.775,0.6},
    
    {0.25,0.6},{0.275,0.6},{0.725,0.6},{0.75,0.6},
    {0.25,0.625},{0.275,0.625},{0.725,0.625},{0.75,0.625},
    {0.25,0.65},{0.275,0.65},{0.3,0.65},{0.7,0.65},{0.725,0.65},{0.75,0.65},
    {0.25,0.675},{0.275,0.675},{0.3,0.675},{0.7,0.675},{0.725,0.675},{0.75,0.675},
    {0.275,0.7},{0.725,0.7},

    {0.3,0.7},{0.325,0.7},{0.35,0.7},{0.65,0.7},{0.675,0.7},{0.7,0.7},
    {0.3,0.725},{0.325,0.725},{0.35,0.725},{0.375,0.725},{0.4,0.725},{0.6,0.725},{0.625,0.725},{0.65,0.725},{0.675,0.725},{0.7,0.725},
    {0.325,0.75},{0.35,0.75},{0.375,0.75},{0.625,0.75},{0.65,0.75},{0.675,0.75},

    {0.4,0.75},{0.425,0.75},{0.45,0.75},{0.55,0.75},{0.575,0.75},{0.6,0.75},
    {0.4,0.775},{0.425,0.775},{0.45,0.775},{0.475,0.775},{0.5,0.775},{0.525,0.775},{0.55,0.775},{0.575,0.775},{0.6,0.775},
    {0.425,0.8},{0.45,0.8},{0.475,0.8},{0.5,0.8},{0.525,0.8},{0.55,0.8},{0.575,0.8},
 // Augen
    {0.375,0.35},{0.4,0.35},{0.425,0.35},{0.575,0.35},{0.6,0.35},{0.625,0.35},
    {0.35,0.375},{0.375,0.375},{0.4,0.375},{0.425,0.375},{0.45,0.375},{0.55,0.375},{0.575,0.375},{0.6,0.375},{0.625,0.375},{0.65,0.375},
    {0.35,0.4},{0.375,0.4},{0.4,0.4},{0.425,0.4},{0.45,0.4},{0.55,0.4},{0.575,0.4},{0.6,0.4},{0.625,0.4},{0.65,0.4},
    {0.35,0.425},{0.375,0.425},{0.4,0.425},{0.425,0.425},{0.45,0.425},{0.55,0.425},{0.575,0.425},{0.6,0.425},{0.625,0.425},{0.65,0.425},
    {0.375,0.45},{0.4,0.45},{0.425,0.45},{0.575,0.45},{0.6,0.45},{0.625,0.45},
// Mund
    {0.3,0.5},{0.7,0.5},
    {0.3,0.525},{0.325,0.525},{0.675,0.525},{0.7,0.525},
    {0.325,0.55},{0.35,0.55},{0.65,0.55},{0.675,0.55},

    {0.375,0.55},{0.625,0.55},
    {0.35,0.575},{0.375,0.575},{0.4,0.575},{0.6,0.575},{0.625,0.575},{0.65,0.575},
    {0.375,0.6},{0.4,0.6},{0.6,0.6},{0.625,0.6},

    {0.425,0.6},{0.45,0.6},{0.55,0.6},{0.575,0.6},
    {0.4,0.625},{0.425,0.625},{0.45,0.625},{0.475,0.625},{0.5,0.625},{0.525,0.625},{0.55,0.625},{0.575,0.625},{0.6,0.625},
    {0.45,0.65},{0.475,0.65},{0.5,0.65},{0.525,0.65},{0.55,0.65},
  };

  private ClusterGraphics cg=new ClusterGraphics();
  
// Ende Attribute

  private JCheckBox jCheckBoxSetPathOption = new JCheckBox();
  private JCheckBox jCheckBoxSetObject = new JCheckBox();
  private JCheckBox jCheckBoxSetCluster = new JCheckBox();
  private JCheckBox jCheckBoxSetDruchlauf = new JCheckBox();
  private JCheckBox jCheckBoxUse = new JCheckBox();
  private JCheckBox jCheckBoxSetE = new JCheckBox();
// Ende Variablen

  @SuppressWarnings("unchecked")
public ClusterMaster(String title) {
    // Frame-Initialisierung
    super(title);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) { dispose(); }
    });
    int frameWidth = 284;
    int frameHeight = 556;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2 ;
    setLocation(x, y);
    Container cp = getContentPane();
    cp.setLayout(null);

// Anfang Komponenten
// Auswahl Cluster-Alg.
    jComboBoxClass.setBounds(34, 193, 145, 24);
    jComboBoxClass.addItem("FuzzyCMeans");
    jComboBoxClass.addItem("PossibilisticCMeans");
    jComboBoxClass.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jComboBoxClass_ActionPerformed(evt);
      }
    });
    jComboBoxClass.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jComboBoxClass);
// Abbruchschwelle jComboBoxE
    jCheckBoxSetE.setBounds(16, 224, 105, 17);
    jCheckBoxSetE.setText(".setE(double)");
    jCheckBoxSetE.setSelected(true);
    jCheckBoxSetE.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxSetE);
    jComboBoxE.setBounds(34, 241, 145, 24);
    jComboBoxE.addItem(0.0000001);
    jComboBoxE.addItem(0.000001);
    jComboBoxE.addItem(0.00001);
    jComboBoxE.addItem(0.0001);
    jComboBoxE.addItem(0.001);
    jComboBoxE.addItem(0.01);
    jComboBoxE.addItem(0.1);
    jComboBoxE.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jComboBoxE);
// Auswahl Testdaten
    jComboBoxObject.setBounds(34, 49, 145, 24);
    jComboBoxObject.addItem("test2");
    jComboBoxObject.addItem("test3");
    jComboBoxObject.addItem("test4");
    jComboBoxObject.addItem("smily");
    jComboBoxObject.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jComboBoxObject);
// Anzahl Cluster
    jComboBoxCluster.setBounds(34, 97, 145, 24);
    jComboBoxCluster.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jComboBoxCluster);
    for(int i=1;i<11;i++){
      jComboBoxCluster.addItem(i);
    }
    jComboBoxCluster.setSelectedIndex(1);
// Anzahl Durchläufe
    jComboBoxDurchlauf.setBounds(34, 145, 145, 24);
    for(int i=1;i<10;i++){
      jComboBoxDurchlauf.addItem(i);
    }
    jComboBoxDurchlauf.setEnabled(false);
    jComboBoxDurchlauf.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jComboBoxDurchlauf);
// Auswahl sortCluster
    jCheckBoxSortCluster.setBounds(16, 280, 249, 17);
    jCheckBoxSortCluster.setText(".sortCluster()");
    jCheckBoxSortCluster.setSelected(true);
    jCheckBoxSortCluster.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxSortCluster);
// fivty
    jCheckBoxFivtyFivtyJoker.setBounds(16, 296, 249, 17);
    jCheckBoxFivtyFivtyJoker.setText(".fivtyFivtyJoker()");
    jCheckBoxFivtyFivtyJoker.setSelected(true);
    jCheckBoxFivtyFivtyJoker.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxFivtyFivtyJoker);
    jCheckBoxFivtyFivtyJoker.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jCheckBoxFivtyFivtyJoker_ActionPerformed(evt);
      }
    });
// max
    jCheckBoxClusterMax.setBounds(16, 312, 249, 17);
    jCheckBoxClusterMax.setText(".clusterMax()");
    jCheckBoxClusterMax.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxClusterMax);
    jCheckBoxClusterMax.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jCheckBox7_ActionPerformed(evt);
      }
    });
// Cluster-Graphics
    jCheckBoxClusterGraphics.setBounds(0, 0, 273, 17);
    jCheckBoxClusterGraphics.setText("ClusterGraphics");
    jCheckBoxClusterGraphics.setSelected(true);
    jCheckBoxClusterGraphics.setEnabled(false);
    jCheckBoxClusterGraphics.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxClusterGraphics);
// Cluster anzeigen
// Description Display
    jCheckBoxDescriptionDisplay.setBounds(16, 328, 249, 17);
    jCheckBoxDescriptionDisplay.setText("ClusterGraphics.descriptionDisplay()");
    jCheckBoxDescriptionDisplay.setSelected(true);
    jCheckBoxDescriptionDisplay.setEnabled(false);
    jCheckBoxDescriptionDisplay.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxDescriptionDisplay);
// Test-Button
    jButton1.setBounds(8, 488, 251, 25);
    jButton1.setText("Test");
    jButton1.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jButton1);
    jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton1_ActionPerformed(evt);
      }
    });
    jCheckBoxSetPathOption.setBounds(16, 16, 249, 17);
    jCheckBoxSetPathOption.setSelected(true);
    jCheckBoxSetPathOption.setText(".setPathOption(boolean)");
    jCheckBoxSetPathOption.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxSetPathOption);
    jCheckBoxSetObject.setBounds(16, 32, 249, 17);
    jCheckBoxSetObject.setSelected(true);
    jCheckBoxSetObject.setText(".setObject(double[][])");
    jCheckBoxSetObject.setEnabled(false);
    jCheckBoxSetObject.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxSetObject);
    jCheckBoxSetCluster.setBounds(16, 80, 249, 17);
    jCheckBoxSetCluster.setSelected(true);
    jCheckBoxSetCluster.setText(".setCluster(int)");
    jCheckBoxSetCluster.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxSetCluster);
    jCheckBoxSetDruchlauf.setBounds(16, 128, 249, 17);
    jCheckBoxSetDruchlauf.setSelected(true);
    jCheckBoxSetDruchlauf.setText(".setDurchlauf(int)");
    jCheckBoxSetDruchlauf.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxSetDruchlauf);
    jCheckBoxUse.setBounds(16, 176, 249, 17);
    jCheckBoxUse.setSelected(true);
    jCheckBoxUse.setText(".use...()");
    jCheckBoxUse.setFont(new Font("Dialog", Font.PLAIN, 13));
    cp.add(jCheckBoxUse);
    // Ende Komponenten

    setResizable(false);
    setVisible(true);
  }

  // Anfang Methoden

  // Anfang Ereignisprozeduren
  public void jButton1_ActionPerformed(ActionEvent evt) {
    cg.clearAll();
    cg.setPixel(false);
//setPathOption
    cg.setPathOption(jCheckBoxSetPathOption.isSelected());
//setObject
    if(jComboBoxObject.getSelectedItem()=="test2") {
      cg.setObject(test2);
    }
    if(jComboBoxObject.getSelectedItem()=="test3") {
      cg.setObject(test3);
    }
    if(jComboBoxObject.getSelectedItem()=="test4") {
      cg.setObject(test4);
    }
    if(jComboBoxObject.getSelectedItem()=="smily") {
      cg.setObject(smily);
    }
//setCluster
    cg.setCluster(jComboBoxCluster.getSelectedIndex()+1);
//setDurchlauf
    cg.setDurchlauf(1);
    for(int i=0;i<9;i++){
      if(jComboBoxDurchlauf.getSelectedIndex()==i){
        cg.setDurchlauf(i+1);
      }
    }
//setE
    cg.setE(Double.valueOf(jComboBoxE.getSelectedItem().toString()));
//useFuzzyCMeans
    if (jComboBoxClass.getSelectedItem()=="FuzzyCMeans"){
      cg.setTitle("FCM");
      cg.useFuzzyCMeans();
    }
//usePossibilisticCMeans
    if (jComboBoxClass.getSelectedItem()=="PossibilisticCMeans"){
      cg.setTitle("PCM");
      cg.usePossibilisticCMeans();
    }
    if(jCheckBoxSortCluster.isSelected()==true){
        cg.sortCluster();
    }
    if(jCheckBoxFivtyFivtyJoker.isSelected()==true){
        cg.fivtyFivtyJoker();
    }
    if(jCheckBoxClusterMax.isSelected()==true){
        cg.clusterMax();
    }
    if(jCheckBoxDescriptionDisplay.isSelected()==true){
        cg.setDescriptionDisplay(true);
    } else {
        cg.setDescriptionDisplay(false);
    }
    cg.repaint();
  }

  public void jComboBoxClass_ActionPerformed(ActionEvent evt) {
//
    if (jComboBoxClass.getSelectedItem()=="PossibilisticCMeans"){
      jComboBoxDurchlauf.setEnabled(true);
    }
    else{
      jComboBoxDurchlauf.setEnabled(false);
      jComboBoxDurchlauf.setSelectedIndex(0);
      cg.setDurchlauf(1);
    }
  }

  public void jCheckBoxFivtyFivtyJoker_ActionPerformed(ActionEvent evt){
    if(jCheckBoxFivtyFivtyJoker.isSelected()==true){
      jCheckBoxDescriptionDisplay.setSelected(true);
      jCheckBoxClusterMax.setSelected(false);
    }
    else{
      if(jCheckBoxClusterMax.isSelected()==false){
        jCheckBoxDescriptionDisplay.setSelected(false);
      }
    }
  }
  public void jCheckBox7_ActionPerformed(ActionEvent evt){
    if(jCheckBoxClusterMax.isSelected()==true){
      jCheckBoxDescriptionDisplay.setSelected(true);
      jCheckBoxFivtyFivtyJoker.setSelected(false);
    }
    else{
      if(jCheckBoxFivtyFivtyJoker.isSelected()==false){
        jCheckBoxDescriptionDisplay.setSelected(false);
      }
    }
  }
  // Ende Ereignisprozeduren
  public void actionPerformed(ActionEvent jComboBoxE){

  }

  public static void main(String[] args) {
    new ClusterMaster("Cluster Master 0.4.3 ©2012 Thomas Heym");
  }
  // Ende Methoden
}
