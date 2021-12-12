/**
 * 
 */
package Default;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
//import java.awt.Cursor;
//import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.BasicStroke;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.GridLayout;
//import java.awt.Point;
//import java.awt.RenderingHints;
//import java.awt.Stroke;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

/**
 * @author baude2d
 *
 */
public class Menu extends JFrame implements ActionListener{

	
	ArrayList<String> raceNameList = new ArrayList<String>();
	ArrayList<String> courseNameList = new ArrayList<String>();
	static ArrayList<Bike> bikeList = new ArrayList<Bike>();
	static ArrayList<Cyclist> cyclistList = new ArrayList<Cyclist>();
	static ArrayList<RaceCourse> courseList = new ArrayList<RaceCourse>();
	static ArrayList<Weather> weatherList = new ArrayList<Weather>();
	static ArrayList<String> cyclistStyleList = new ArrayList<String>();
	static ArrayList<Double> velocityList = new ArrayList<Double>();
	static Map<String, Double> hazardMap = new LinkedHashMap<String, Double>();
	String cName;
	
	static double currLength = 120;
	static double currCP = 32;
	
	
	static int iterations = 0;
	
	DecimalFormat df = new DecimalFormat("#,###.###");
	
	Color gold = new Color(252, 205, 53);
	Color maroon = new Color(128, 0, 0);
	Color black = new Color(0, 0, 0);
	Font tnr = new Font("TimesNewRoman", 20, 20);
	Font tinyTNR = new Font("TimesNewRoman", 12, 12);
	Font title = new Font("TimesNewRoman", 30, 30);
	Border lineBorder = new LineBorder(new Color(252,205,53),5);
	Border gLineBorder = new LineBorder(gold, 3);
	Border bLineBorder = new LineBorder(black, 3);

	CardLayout clMenu = new CardLayout();
	GridLayout glMenu;
	LineBorder bBorder = new LineBorder(gold, 5);
	LineBorder tBorder = new LineBorder(gold, 3);

	public JTabbedPane menuTabbedPane = new JTabbedPane();
	
	JPanel jpPanelContainer = new JPanel(); // Panel container for entire tab
	JPanel jpMenuCard = new JPanel(clMenu); // Menu for simulation and setting values/objs
	JPanel jpBuildCyclist = new JPanel(new BorderLayout()); // Panel for building cyclists
	JPanel jpBuildCourse = new JPanel(new BorderLayout()); // Panel for building a race course
	JPanel jpViewObjs = new JPanel(new BorderLayout()); // Panel to view saved objects

	// Menu JComponents and Global Variables
	JButton jbtSaveRace, jbtRunSim, jbtCreateCourse, jbtCreateCyclist, jbtCreateBike, jbtSetWeather;
	JLabel jlMenuTitle, jlRaceName, jlCourse, jlCyclist, jlBike, jlWeather;
	JComboBox<String>  jcbCyclist, jcbBike, jcbWeather, jcbVOCyclist;
	JComboBox<RaceCourse> jcbCourse;
	String[] exampleStringArr = {"1", "2", "3", "test"};
	JTextField jtfRaceName = new JTextField();
	
	// Course JComponents and Global Variables
	JButton jbtSaveCourse, jbtSaveCName, jbtSaveLength, jbtSaveCP, jbtSaveME, jbtGenerateHills;
	JLabel jlCourseName, jlLength, jlCP, jlMaxElevation, jlHills;
	JComboBox<String> jcbSaveLength, jcbSaveCP, jcbSaveME;
	JTextField jtfCourseName, jtfLength, jtfCP;
	static JTextField jtfME;
	JTextArea jtaHills;
	
	// Cyclist JComponents and Global Variables
	JButton jbtRName, jbtRMass, jbtRHeight, jbtLinkEDA, jbtRFTP, jbtRStyle, jbtRBike;
	JLabel jlCyclistName, jlCMass, jlCHeight, jlEDA, jlFTP, jlStyle, jlCBike, jlRiderStyle;
	JComboBox<String> jcbStyle, jcbFTP, jcbCBike;
	JTextField jtfCyclistName, jtfCMass, jtfCHeight, jtfEDA; 
	
	public Menu() throws Exception {		
		//Initializing JComponents
		JComponent menuPage = createMenuPage();
		JComponent coursePage = createCoursePage();
		JComponent cyclistPage = createCyclistPage();
//		JComponent bikePage = createBikePage();
//		JComponent weatherPage = createWeatherPage();
		JComponent viewObjectsPage = createViewObjectsPage();
		JComponent simulationPage = createSimulationPage();
		
		//Adding pages to card layout
		jpMenuCard.add(menuPage, "Menu");
//		jpMenuCard.add(coursePage, "Build Course");
//		jpMenuCard.add(cyclistPage, "Build Cyclist");
//		jpMenuCard.add(savedPage, "Saved Objects");
		
		//Action events/listeners
		
		//Add tabs
		menuTabbedPane.addTab("Menu", menuPage);
		menuTabbedPane.addTab("Build Course", coursePage);
		menuTabbedPane.addTab("Build Cyclist", cyclistPage);
//		menuTabbedPane.addTab("Build Bike", bikePage);
//		menuTabbedPane.addTab("Build Weather", weatherPage);
		menuTabbedPane.addTab("View Objects", viewObjectsPage);
		menuTabbedPane.addTab("View Simulation", simulationPage);
		jpMenuCard.add(menuTabbedPane);
		
		add(menuTabbedPane);
		
		setTitle("Bike Race Simulation - CPS270");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,750);
		setVisible(true);
	}
	
	public static void loadDefaultObjects() throws Exception {
		// Bike: /make/model/year/bikeMass(kg)/rollingResistanceCoe/frameMaterial/classification
		bikeList.add(new Bike("Giant", "TCR Advanced Pro", 2021, 7.6, 0.00330, "Carbon", "Road Race"));
		bikeList.add(new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.00330, "Carbon", "Time Trail"));
		bikeList.add(new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race")); 
		bikeList.add(new Bike("LiteSpeed", "Ultimate Gravel", 2021, 8.93, 0.00460, "Titanium", "Gravel"));
		
		// Cylist: /name/mass(kg)/height(cm)/effectiveDragArea(m2)/FTP(W)/riderStyle/bikeObj
//		cyclistList.add(new Cyclist("Eddy Merckx", 72, 170, 0.4889, 0.96759,430,"Sprinter",bikeList.get(4)));
		//DOMESTIC PRO
		
		//CAT 1
		cyclistList.add(new Cyclist("Brandon", 70.76, 158, 0.5758, 0.96759, 345, "Climber", bikeList.get(3)));//4.89w/kg
		cyclistList.add(new Cyclist("Alec", 59.87, 150, 0.4397, 0.96759, 303, "Climber", bikeList.get(2))); //5.07 w/kg
		//CAT 2
		cyclistList.add(new Cyclist("Mason", 83.91, 179, 0.5270, 0.96759, 365, "Sprinter", bikeList.get(0))); //4.35 w/kg
		//CAT 3
		cyclistList.add(new Cyclist("Josh", 106, 155, 0.5478, 0.96759, 414, "Sprinter", bikeList.get(2))); //Cat 3 Racer, 3.91 w/kg, W: 414
		cyclistList.add(new Cyclist("Jon", 90, 190, 0.5529, 0.96775, 400, "Sprinter", bikeList.get(0))); //W:400

		////CYCLIST STYLES////////////////////////////////////////////////////////////////////////////////////////////////////////
		cyclistStyleList.add("Climber"); // Bonus on Velocity when Climbing, Slower on Flat Grades and Descents up to -5%
		cyclistStyleList.add("Sprinter"); // Bonus on Velocity for Flat Grades and Descents up to -5%, Slower on climbs
		cyclistStyleList.add("All-Arounder"); // Neither Bonus nor Disadvantages
		cyclistStyleList.add("Time Trialist"); // Slight Bonus when riding alone 
		cyclistStyleList.add("Descender"); //Can still output wattage at up to 15% grade
		
		weatherList.add(new Weather("None", 0, 65, "Clear"));

		courseList.add(new RaceCourse("Course 1", 100, 0, 32, hazardMap, weatherList.get(0)));
	}
	
	public class CyclistListCellRenderer extends DefaultListCellRenderer {

	    public Component getListCellRendererComponent(
	                                   JList<?> list,
	                                   Object value,
	                                   int index,
	                                   boolean isSelected,
	                                   boolean cellHasFocus) {
	        if (value instanceof Cyclist) {
	            value = ((Cyclist)value).getName();
	        }
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        return this;
	    }
	}
	
	public class BikeListCellRenderer extends DefaultListCellRenderer {
	    public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
	    	if (value instanceof Bike) {
	    		value = ((Bike)value).getMake();
	    	}
	    	super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    	return this;
}
}
	
	public class CourseListCellRenderer extends DefaultListCellRenderer {
	    public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
	    	if (value instanceof RaceCourse) {
	    		value = ((RaceCourse)value).getCourseName();
	    	}
	    	super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    	return this;
}
}
	
	
	public JPanel createButtonPanel(JButton jbtButton, String text) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(maroon);
		JLabel jlLabel = new JLabel(text);
		jlLabel.setFont(tinyTNR);
		jlLabel.setForeground(gold);
		jbtButton.setSize(5,5);
		buttonPanel.add(jlLabel);
		buttonPanel.add(jbtButton);
		return buttonPanel;
	}
	
	public JPanel create2ButtonPanel(JButton jbtButton,JButton jbtButton2, String text, String text2) {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(maroon);
		JLabel jlLabel = new JLabel(text);
		JLabel jlLabel2 = new JLabel(text2);
		jlLabel.setFont(tinyTNR);
		jlLabel2.setFont(tinyTNR);
		jlLabel.setForeground(gold);
		jlLabel2.setForeground(gold);
		jbtButton.setSize(5,5);
		jbtButton2.setSize(5,5);
		buttonPanel.add(jlLabel);
		buttonPanel.add(jbtButton);
		buttonPanel.add(jlLabel2);
		buttonPanel.add(jbtButton2);
		return buttonPanel;
	}
	
	public String generateRandomCyclistName() throws FileNotFoundException {
		File rNames = new File("rNames.txt");
		Scanner sr = new Scanner(rNames);
		ArrayList<String> namesList = new ArrayList<String>();
		while (sr.hasNextLine()) {
			String line = sr.nextLine();
			namesList.add(line);
		}
		double randomNum = ((Math.random() * (98 - 1)) + 1);
		sr.close();
		return namesList.get((int) randomNum);
	}
	
	public String generateRandomCyclistMass() {
		String str = "";
		//Average mass in kg for the USA = 80 == 177 lbs
		//random mass returns +- 20 kg from average
		double randomMass = ((int) (Math.random() * (100 - 60) + 60));
		return str + randomMass;
	}
	
	public String generateRandomCyclistHeight() {
		String str = "";
		//Average height in cm for the USA is approximately 169cm
		//randomHeight returns +- 30 cm from the average
		double randomHeight = ((int) (Math.random() * (199 - 139) + 139));
		return str + randomHeight;
	}

	
	public String buildClock(int numIterations) {
		String clock = "";
		int p1 = numIterations % 60;
		int p2 = numIterations / 60;
		int p3 = p2 % 60;
		p2 = p2 / 60;
		clock = p2 + ":" + p3 + ":" + p1 + "\n\n";
		return clock;
	}

	public String buildString(int numIterations) {
		String results = "";
		double distance = 0;
		for(int j = numIterations; j > 0; j--) {
		for(int i = 0; i < cyclistList.size(); i++) {
			String strName = "";
			strName = cyclistList.get(i).getName();
			if(iterations <= 1) {
				velocityList.add(Simulation.calculateVelocity(cyclistList.get(i), courseList.get(0), weatherList.get(0), iterations));
				distance = velocityList.get(i);
				velocityList.set(i, distance);
				distance = distance / 1000;
			}
			if(iterations >= 2) {
				distance = velocityList.get(i) + Simulation.calculateVelocity(cyclistList.get(i), courseList.get(0), weatherList.get(0), iterations);
				velocityList.set(i, distance);
				distance = distance / 1000;
			}
		
			if(j != 1) {
				results = "";
			} else {
			results += strName + "\t Current Distance (KM): " + df.format(distance) + "\n";
			}
		}
		}
		
		
		return results;
	}
	
	public JComponent createMenuPage() throws Exception {
		JPanel menuPage = new JPanel();
		menuPage.setLayout(new GridLayout(0,1,30,30));
		JPanel  menuGrid = new JPanel(new GridLayout(0,3,30,30));
		menuGrid.setBackground(maroon);
		JPanel menuBPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		menuBPanel.setBackground(maroon);
		
		TitledBorder menuTitle = new TitledBorder("Bike Race Simulation Menu");
		menuTitle.setTitleColor(gold);
		menuPage.setBorder(menuTitle);
				
		//START OF menuGrid
		//menuGrid || Row 1 || Race Name
		JLabel jlRaceName = new JLabel("Race Name");
		jlRaceName.setFont(tnr);
		jlRaceName.setForeground(gold);
		jlRaceName.setBackground(maroon);
		
		JButton jbtRaceName = new JButton("+");
		
		JTextField jtfRaceName = new JTextField();
		jtfRaceName.setBorder(gLineBorder);
		
		jbtRaceName.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				int nPlusOne = raceNameList.size() + 1;
				jtfRaceName.setText("Race " + nPlusOne);
			}
		});
		
		//menuGrid || Row 2 || Race Course
		JLabel jlCourse = new JLabel("Race Course");
		jlCourse.setFont(tnr);
		jlCourse.setForeground(gold);
		jlCourse.setBackground(maroon);
		
		JButton jbtCreateCourse = new JButton("+");
		jbtCreateCourse.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				menuTabbedPane.setSelectedIndex(1);
			}
		});
		
		JComboBox<RaceCourse> jcbCourse = new JComboBox<RaceCourse>();
		jcbCourse.addItem(new RaceCourse("Course 1", 100, 0,32, hazardMap, weatherList.get(0)));
		jcbCourse.setRenderer(new CourseListCellRenderer());
		jcbCourse.setBorder(bLineBorder);
		//menuGrid || Row 3 || Your Cyclist
		JLabel jlCyclist = new JLabel("Your Cyclist");
		jlCyclist.setFont(tnr);
		jlCyclist.setForeground(gold);
		jlCyclist.setBackground(maroon);
		
		JButton jbtCreateCyclist = new JButton("+");
		jbtCreateCyclist.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				menuTabbedPane.setSelectedIndex(2);
			}
		});
		
		JComboBox<Cyclist> jcbCyclist = new JComboBox<Cyclist>();
		for(int i = 0; i < cyclistList.size(); i++) {
			jcbCyclist.addItem(cyclistList.get(i));
		}

		jcbCyclist.setBorder(bLineBorder);
		jcbCyclist.setRenderer(new CyclistListCellRenderer());
		//menuGrid || Row 4 || Your Bike
//		JLabel jlBike = new JLabel("Your Bike");
//		jlBike.setFont(tnr);
//		jlBike.setForeground(gold);
//		jlBike.setBackground(maroon);
//		
//		JButton jbtCreateBike = new JButton("+");
//		jbtCreateBike.addActionListener(new ActionListener() { 
//			public void actionPerformed(ActionEvent e) {
//				menuTabbedPane.setSelectedIndex(3);
//			}
//		});
//		
//		JComboBox<String> jcbBike = new JComboBox<String>(exampleStringArr);
//		jcbBike.setBorder(bLineBorder);
//		//menuGrid || Row 5 || Weather
//		JLabel jlWeather = new JLabel("Weather Conditions");
//		jlWeather.setFont(tnr);
//		jlWeather.setForeground(gold);
//		jlWeather.setBackground(maroon);
//		
//		JButton jbtCreateWeather = new JButton("+");
//		jbtCreateWeather.addActionListener(new ActionListener() { 
//			public void actionPerformed(ActionEvent e) {
//				menuTabbedPane.setSelectedIndex(4);
//			}
//		});
//		
//		JComboBox<String> jcbWeather = new JComboBox<String>(exampleStringArr);
//		jcbWeather.setBorder(bLineBorder);
		//END OF menuGrid
		
		//START OF menuBPanel
		
		//menuBPanel || Row 1 || Save Race
		JButton jbtSaveRace = new JButton("Save Race");
		//!!!!
		//Be sure to append List of race names with contents of jtfRaceName
		//!!!!
		//menuBPanel || Row 2 || Run Simulation
		JButton jbtRunSim = new JButton("Run Simulation");
		//END OF menuBPanel
		
		//add components

		//R1
		menuGrid.add(jlRaceName);
		menuGrid.add(createButtonPanel(jbtRaceName, "Generate Name > "));
		menuGrid.add(jtfRaceName);
		//R2
		menuGrid.add(jlCourse);
		menuGrid.add(createButtonPanel(jbtCreateCourse, "Create Course > "));
		menuGrid.add(jcbCourse);
		//R3
		menuGrid.add(jlCyclist);
		menuGrid.add(createButtonPanel(jbtCreateCyclist, "Create Cyclist > "));
		menuGrid.add(jcbCyclist);
//		//R4
		
//		menuGrid.add(jlBike);
//		menuGrid.add(createButtonPanel(jbtCreateBike, "Create Bike > "));
//		menuGrid.add(jcbBike);
//		//R5
//		menuGrid.add(jlWeather);
//		menuGrid.add(createButtonPanel(jbtCreateWeather, "Create Weather > "));
//		menuGrid.add(jcbWeather);
		
		menuPage.add(menuGrid);
		
		//R1
		menuBPanel.add(jbtSaveRace);
		menuBPanel.add(jbtRunSim);
		
		
		menuPage.add(menuBPanel);
		
		menuPage.setBackground(new Color(128,0,0));
		
		return menuPage;
	}
	
	public JComponent createCoursePage() {
		JPanel coursePage = new JPanel();
		coursePage.setLayout(new GridLayout(0,1,30,30));
		coursePage.setBackground(maroon);
		JPanel  courseGrid = new JPanel(new GridLayout(0,3,20,20));
		courseGrid.setBackground(maroon);
		JPanel graphPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		graphPanel.setBackground(maroon);
		
		TitledBorder courseTitle = new TitledBorder("Build Course");
		courseTitle.setTitleColor(gold);
		coursePage.setBorder(courseTitle);
		
		
		//START OF courseGrid
		//courseGrid || Row 1 || Course Name
		JLabel jlCourseName = new JLabel("Course Name");
		jlCourseName.setFont(tnr);
		jlCourseName.setForeground(gold);
		jlCourseName.setBackground(maroon);
		
		JButton jbtSaveCName = new JButton("+");
		JButton jbtRCName = new JButton("R");

		JTextField jtfCourseName = new JTextField();
		jtfCourseName.setBorder(gLineBorder);
		
		jbtSaveCName.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String cName = jtfCourseName.getText();
				System.out.print(cName);
			}
		});
		jbtRCName.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int nPlusOne = courseNameList.size() + 1;
				jtfCourseName.setText("Race " + nPlusOne);
				
			}
		});
		
		
		//courseGrid || Row 2 || Length in Km
		JLabel jlLength = new JLabel("Length in Km");
		jlLength.setFont(tnr);
		jlLength.setForeground(gold);
		jlLength.setBackground(maroon);
		
		JButton jbtDefaultLength = new JButton("+");
		
		JTextField jtfLength = new JTextField();
		jtfLength.setBorder(bLineBorder);
		
		jbtDefaultLength.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtfLength.setText("" + 120);
			}
		});
		//courseGrid || Row 3 || Number of Checkpoints
		JLabel jlCP = new JLabel("Number of Checkpoints");
		jlCP.setFont(tnr);
		jlCP.setForeground(gold);
		jlCP.setBackground(maroon);
		
		JButton jbtDefaultCP = new JButton("+");
		
		JTextField jtfCP = new JTextField();
		jtfCP.setBorder(bLineBorder);
		
		jbtDefaultCP.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtfCP.setText("" + 32);
			}
		});
		//courseGrid || Row 4 || Max Elevation
		JLabel jlME = new JLabel("Maximum Elevation");
		jlME.setFont(tnr);
		jlME.setForeground(gold);
		jlME.setBackground(maroon);
		
		JButton jbtDefaultME = new JButton("+");
		
		JTextField jtfME = new JTextField();
		jtfME.setBorder(bLineBorder);
		
		jbtDefaultME.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtfME.setText("" + 100);
			}
		});
		//courseGrid || Row 5 || Generate Hills
		JLabel jlHills = new JLabel("Generate Hills");
		jlHills.setFont(tnr);
		jlHills.setForeground(gold);
		jlHills.setBackground(maroon);
		
		JButton jbtGenerateHills = new JButton("+");
		
		JTextField jtfNumHills = new JTextField();
		jtfNumHills.setEditable(false);
		jtfNumHills.setBackground(new Color(211,211,211));
		
		jbtGenerateHills.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				for(int x = 0; x < 1; x++) {
//					generateHills();
				 //Creating hills and elevations
				  ArrayList<Integer> cPList = new ArrayList<Integer>();
				  ArrayList<Integer> peakList = new ArrayList<Integer>();
				  ArrayList<Integer> elevationList = new ArrayList<Integer>();
//				  Random r = new Random();
				  String jtfLengthStr = jtfLength.getText();
				  String jtfCPStr = jtfCP.getText();
				  String jtfMEStr = jtfME.getText();
				  if (jtfLength.getText() == null) {
					  jtfLength.setText("Length is null");
					  break;
				  }
				  if (jtfCP.getText() == null) {
					  jtfCP.setText("Check points is null");
				  }
				  if (jtfME.getText() == null) {
					  jtfME.setText("Max elevation is null");
				  }
				  
					int lengthInKm = Integer.parseInt(jtfLengthStr);
					int numCP = Integer.parseInt(jtfCPStr);
					int maxElevation = Integer.parseInt(jtfMEStr);
				  int numHills = (int) ((Math.random() * ((numCP / 3) - 1)) + 1);
				  int ascend, descend;

				  //Fill checkpoints with indexes given numCP
				  for(int i = 0; i < numCP; i++) {
					  cPList.add( i);
				  }
				  
				  //Generate peak elevations for each hill
				  for(int i = 0; i < numHills; i++) {
					  peakList.add((int) ((Math.random() * ((maxElevation - 1)) + 1)));
					  System.out.println("Hill num: " + (i + 1)  + "\nPeak elevation: " + peakList.get(i));
				  }
				  
				  for(int i = 0; i < peakList.size(); i++) {
					  ascend = 0;
					  descend = 0;
					  for(int j = ascend; j < peakList.get(i); j++) {
						if(ascend >= peakList.get(i)) {
							
						} else {
						ascend += (int) ((Math.random() * ((10 - 1)) + 1));
						System.out.println("Rand ase: " + ascend + "Hill: " + i);
					  	elevationList.add(ascend);
						}
					  }
					  descend = ascend;
					  for(int k = descend; k > 0; k--) {
//						  if(descend < 0) {
//							  descend = 0;
//						  }
						  if(descend <= 0) {
						  
						  } else {
							  descend -= (int) ((Math.random() * ((10 - 1)) + 1));
							  System.out.println("Random des: " + descend + "Hill: " + i);
							  elevationList.add(descend);
						  }
					  } 
				  }
				  
				  List<Integer> elevationDistanceList = new ArrayList<Integer>();
				  
				  for(int j = 0; j < elevationList.size(); j++) {
					  for(int i = (int) lengthInKm * 1000; i >= 0; i -= 10) {
					  elevationDistanceList.add(elevationList.get(j));
				  	}
				  }
				  jtfNumHills.setText("" + numHills);
			}
				
			}
		});
		//course Grid || Row 6 || Save Course button
		JTextField space1 = new JTextField();
		space1.setEditable(false);
		space1.setBorder(null);
		space1.setBackground(maroon);
		space1.setForeground(maroon);
		
		JTextField space2 = new JTextField();
		space2.setEditable(false);
		space2.setBorder(null);
		space2.setBackground(maroon);
		space2.setForeground(maroon);
		JButton jbtSaveCourse = new JButton("Save Your Course");
		jbtSaveCourse.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
			}
		});
				//!!!!
				//Be sure to append List of course names with contents of jtfRaceName
				//!!!!

		//END OF courseGrid
		
		//START OF graphPanel
		//Creating Hills and Elevations

		
		//R1
		courseGrid.add(jlCourseName);
		courseGrid.add(createButtonPanel(jbtRCName, "Generate Name > "));
		courseGrid.add(jtfCourseName);
		//R2
		courseGrid.add(jlLength);
		courseGrid.add(createButtonPanel(jbtDefaultLength, "Default Length > "));
		courseGrid.add(jtfLength);
		//R3
		courseGrid.add(jlCP);
		courseGrid.add(createButtonPanel(jbtDefaultCP, "Calulate Checkpoints > "));
		courseGrid.add(jtfCP);
		//R4
		courseGrid.add(jlME);
		courseGrid.add(createButtonPanel(jbtDefaultME, "Default Max Elev. > "));
		courseGrid.add(jtfME);
		//R5
		courseGrid.add(jlHills);
		courseGrid.add(createButtonPanel(jbtGenerateHills, "Generate Hills > "));
		courseGrid.add(jtfNumHills);
		//R6 with blank text fields to fill space
		courseGrid.add(space1);
		courseGrid.add(space2);
		courseGrid.add(jbtSaveCourse);
		
		

		
		coursePage.add(courseGrid);
		coursePage.add(graphPanel);
		return coursePage;
	}
	
	public JComponent createCyclistPage() {
		JPanel cyclistPage = new JPanel();
		cyclistPage.setLayout(new GridLayout(0,1,30,30));
		cyclistPage.setBackground(maroon);
		JPanel  cyclistGrid = new JPanel(new GridLayout(0,3,20,20));
		cyclistGrid.setBackground(maroon);
		JPanel cyclistBPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cyclistBPanel.setBackground(maroon);
		
		TitledBorder cyclistTitle = new TitledBorder("Build Cyclist");
		cyclistTitle.setTitleColor(gold);
		cyclistPage.setBorder(cyclistTitle);
		
		//START OF cyclistGrd
		//cyclistGrid || Row 1 || Cyclist Name
		JLabel jlCyclistName = new JLabel("Cyclist's Name");
		jlCyclistName.setFont(tnr);
		jlCyclistName.setForeground(gold);
		jlCyclistName.setBackground(maroon);
		
		JButton jbtRName = new JButton("+");
		
		JTextField jtfCyclistName = new JTextField();
		jtfCyclistName.setBorder(gLineBorder);
		
		jbtRName.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try {
					jtfCyclistName.setText(generateRandomCyclistName());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//cyclistGrid || Row 2 || Cyclist Mass (kg)
		JLabel jlCMass = new JLabel("Cyclist Mass");
		jlCMass.setFont(tnr);
		jlCMass.setForeground(gold);
		jlCMass.setBackground(maroon);
		
		JButton jbtCMass = new JButton("+");
		
		JTextField jtfCMass = new JTextField();
		jtfCMass.setBorder(bLineBorder);
		
		jbtCMass.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtfCMass.setText(generateRandomCyclistMass());
			}
		});
		//cyclistGrid || Row 3 || Cyclist Height ()
		JLabel jlCHeight = new JLabel("Cyclist Height");
		jlCHeight.setFont(tnr);
		jlCHeight.setForeground(gold);
		jlCHeight.setBackground(maroon);
		
		JButton jbtRHeight = new JButton("+");
		
		JTextField jtfCHeight = new JTextField();
		jtfCHeight.setBorder(bLineBorder);
		
		jbtRHeight.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtfCHeight.setText(generateRandomCyclistHeight());
			}
		});
		//cyclistGrid || Row 4 || Bike object
		JLabel jlCBike = new JLabel("Bike Object");
		jlCBike.setFont(tnr);
		jlCBike.setForeground(gold);
		jlCBike.setBackground(maroon);
		
		JButton jbtRBike = new JButton("R");
		
		JComboBox<Bike> jcbBike = new JComboBox<Bike>();
		jcbBike.addItem(new Bike("Giant", "TCR Advanced Pro", 2021, 7.6, 0.00330, "Carbon", "Road Race"));
		jcbBike.addItem(new Bike("Canyon", "SpeedMax CF 8", 2021, 8.39, 0.00330, "Carbon", "Time Trail"));
		jcbBike.addItem(new Bike("Trek", "Madone SLR 9", 2021, 7.5, 0.00330, "Carbon", "Road Race")); 
		jcbBike.addItem(new Bike("LiteSpeed", "Ultimate Gravel", 2021, 8.93, 0.00460, "Titanium", "Gravel"));
		jcbBike.setRenderer(new BikeListCellRenderer());
		jbtRBike.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
			double i = ((Math.random() * (4 - 1)) + 1);
			jcbBike.setSelectedIndex((int) i - 1);
			}
		});
		//cyclistGrid || Row 5 || Effective Drag Area
		JLabel jlEDA = new JLabel("Effective Drag Area");
		jlEDA.setFont(tnr);
		jlEDA.setForeground(gold);
		jlEDA.setBackground(maroon);
		
		JButton jbtEDA = new JButton("+");
		
		JTextField jtfEDA = new JTextField();
		jtfEDA.setBorder(bLineBorder);
		
		jbtEDA.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Desktop d = Desktop.getDesktop();
				try {
					d.browse(new URI("http://www.kreuzotter.de/english/espeed.htm"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		//cyclistGrid || Row 6 || FTP - Wattage
		JLabel jlFTP = new JLabel("Cyclist FTP");
		jlFTP.setFont(tnr);
		jlFTP.setForeground(gold);
		jlFTP.setBackground(maroon);
		
		JButton jbtFTP = new JButton("+");
		
		JTextField jtfFTP = new JTextField();
		jtfFTP.setBorder(bLineBorder);
		
		jbtFTP.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String cMass = jtfCMass.getText();
				double weightInLbs = 2.2 * Double.parseDouble(cMass); //Weight in kg to weight in lbs
				double originalFTP = weightInLbs * 2;
				double adjustment = (Math.random() * (((originalFTP) + originalFTP * 0.1) - ((originalFTP) - originalFTP * 0.05)) + ((originalFTP) - originalFTP * 0.05));
				
				jtfFTP.setText("" + (int) adjustment);
			}
		});
		//cyclistGrid || Row 7 || Rider Style
		JLabel jlRiderStyle = new JLabel("Rider style");
		jlRiderStyle.setFont(tnr);
		jlRiderStyle.setForeground(gold);
		jlRiderStyle.setBackground(maroon);
		
		JButton jbtRStyle = new JButton("R");
		
		JComboBox<String> jcbRStyle = new JComboBox<String>(exampleStringArr);
		//END OF cyclistGrid
		
		//START OF cyclistBGrid
		JTextField space3 = new JTextField();
		space3.setEditable(false);
		space3.setBorder(null);
		space3.setBackground(maroon);
		space3.setForeground(maroon);
		
		JTextField space4 = new JTextField();
		space4.setEditable(false);
		space4.setBorder(null);
		space4.setBackground(maroon);
		space4.setForeground(maroon);
		JButton jbtSaveCyclist = new JButton("Save Your Cyclist");
		//Be sure to append menu cyclist list
		//END OF cyclistBGrid
		
		//R1
		cyclistGrid.add(jlCyclistName);
		cyclistGrid.add(createButtonPanel(jbtRName,"Random Name > "));
		cyclistGrid.add(jtfCyclistName);
		//R2
		cyclistGrid.add(jlCMass);
		cyclistGrid.add(createButtonPanel(jbtCMass, "Random Mass > "));
		cyclistGrid.add(jtfCMass);
		//R3
		cyclistGrid.add(jlCHeight);
		cyclistGrid.add(createButtonPanel(jbtRHeight,"Random Height > "));
		cyclistGrid.add(jtfCHeight);
		//R4
		cyclistGrid.add(jlCBike);
		cyclistGrid.add(createButtonPanel(jbtRBike,"Random Bike > "));
		cyclistGrid.add(jcbBike);
		//R5
		cyclistGrid.add(jlEDA);
		cyclistGrid.add(createButtonPanel(jbtEDA, "Link to calculator > "));
		cyclistGrid.add(jtfEDA);
		//R5
		cyclistGrid.add(jlFTP);
		cyclistGrid.add(createButtonPanel(jbtFTP, "Calculate Random FTP > "));
		cyclistGrid.add(jtfFTP);
		//R6
		cyclistGrid.add(jlRiderStyle);
		cyclistGrid.add(createButtonPanel(jbtRStyle, "Random Style > "));
		cyclistGrid.add(jcbRStyle);
		
		//R1
		cyclistBPanel.add(space3);
		cyclistBPanel.add(space4);
		cyclistBPanel.add(jbtSaveCyclist);
		
		cyclistPage.add(cyclistGrid);
		cyclistPage.add(cyclistBPanel);
		return cyclistPage;
	}
	
	public JComponent createViewObjectsPage() {
		JPanel viewObjectsPage = new JPanel();
		viewObjectsPage.setLayout(new GridLayout(0,1,30,30));
		viewObjectsPage.setBackground(maroon);
		JPanel  objGrid = new JPanel(new GridLayout(0,3,20,20));
		objGrid.setBackground(maroon);
		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		summaryPanel.setBackground(maroon);
		
		TitledBorder objTitle = new TitledBorder("View Default Objects");
		objTitle.setTitleColor(gold);
		viewObjectsPage.setBorder(objTitle);
		
		//START OF objGrid
		//objGrid || Row 1 || 
		JLabel voCyclist = new JLabel("Default Cyclists");
		voCyclist.setFont(tnr);
		voCyclist.setForeground(gold);
		
		JButton displayCyclist = new JButton("D");
		
		JComboBox<Cyclist> jcbVOCyclist = new JComboBox<Cyclist>();
		for(int i = 0; i < cyclistList.size(); i++) {
			jcbVOCyclist.addItem(cyclistList.get(i));
		}
		//objGrid || Row 2 ||
		JLabel voBike = new JLabel("Default Cyclists");
		voBike.setFont(tnr);
		voBike.setForeground(gold);
		
		JButton displayBike = new JButton("D");
		
		JComboBox<Bike> jcbVOBike = new JComboBox<Bike>();
		for(int i = 0; i < bikeList.size(); i++) {
			jcbVOBike.addItem(bikeList.get(i));
		}
		//objGrid || Row 3 ||
		JLabel voWeather = new JLabel("Default Weather Conditions");
		voWeather.setFont(tnr);
		voWeather.setForeground(gold);
		
		JButton displayWeather = new JButton("D");
		
		JComboBox<Weather> jcbVOWeather= new JComboBox<Weather>();
		for(int i = 0; i < weatherList.size(); i++) {
			jcbVOWeather.addItem(weatherList.get(i));
		}
		//objGrid || Row 4 ||
		JLabel voHazards = new JLabel("Default Hazards");
		voHazards.setFont(tnr);
		voHazards.setForeground(gold);
		
		JButton displayHazards = new JButton("D");
		
		//Add hazard List and create combo box with hazardList.get(i)
		JComboBox<Map<String, Double>> jcbVOHazards = new JComboBox<Map<String, Double>>();
		for(int i = 0; i < hazardMap.size(); i++) {
			jcbVOHazards.addItem(hazardMap);
		}
		
		//objGrid || Row 5 || 
//		JLabel voRStyle = new JLabel("Default Rider Styles");
//		
//		JButton displayRStyle = new JButton("D");
		
		//Add rsyle list and create combox with styleList.get(i);
		//END OF objGrid
		
		objGrid.add(voCyclist);
		objGrid.add(createButtonPanel(displayCyclist, "Display > "));
		objGrid.add(jcbVOCyclist);
		
		objGrid.add(voBike);
		objGrid.add(createButtonPanel(displayBike, "Display > "));
		objGrid.add(jcbVOBike);
		
		objGrid.add(voWeather);
		objGrid.add(createButtonPanel(displayWeather, "Display > "));
		objGrid.add(jcbVOWeather);
		
		objGrid.add(voHazards);
		objGrid.add(createButtonPanel(displayHazards, "Display > "));
//		objGrid.add(jcbVOHazards);
		
//		objGrid.add(voRStyle);
//		objGrid.add(createButtonPanel(displayRStyle, "Display > "));
//		objGrid.add(jcbVORStyle);
		
		viewObjectsPage.add(objGrid);
		viewObjectsPage.add(summaryPanel);
		return viewObjectsPage;
	}
	
	public JComponent createSimulationPage() {
		JPanel simulationPage = new JPanel();
		simulationPage.setLayout(new GridLayout(0,1,30,30));
		simulationPage.setBackground(maroon);
		JPanel viewSimPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		viewSimPanel.setBackground(maroon);
		JPanel simButtonPanel = new JPanel(new GridLayout(0,3,30,30));
		simButtonPanel.setBackground(maroon);
		
		TitledBorder simTitle = new TitledBorder("View Simulation");
		simTitle.setTitleColor(gold);
		simulationPage.setBorder(simTitle);
		
		//START OF viewSimPanel
		//viewSimPanel || JTextArea to display race
		JTextArea jtaSimulation = new JTextArea("Run Simulation on the menu to get started. \n"
				+ "If you run without making changes, a default race will take place.");
		
		jtaSimulation.setBorder(bLineBorder);
		jtaSimulation.setEditable(false);
		//END OF viewSimPanel
		
		//START OF simButtonPanel
		//simButtonPanel || Buttons to add functionality to simulation
		JButton jbtOneSecond = new JButton(">");
		jbtOneSecond.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				iterations += 1;
				String str = buildString(1);
				String clock = buildClock(iterations);

				jtaSimulation.setText(clock + str);
				}
		});
		JButton jbt30Seconds = new JButton(">>");
		jbt30Seconds.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				iterations += 30;
				String str = buildString(30);
				String clock = buildClock(iterations);

				jtaSimulation.setText(clock + str);
			}
		});
		JButton jbtOneMinute = new JButton(">>>");
		jbtOneMinute.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				iterations += 60;
				String str = buildString(60);
				String clock = buildClock(iterations);

				jtaSimulation.setText(clock + str);
			}
		});
		
		//END OF simButtonPanel
		
		
		viewSimPanel.add(jtaSimulation);
		simButtonPanel.add(createButtonPanel(jbtOneSecond,"One second > "));
		simButtonPanel.add(createButtonPanel(jbt30Seconds, "30 Seconds > "));
		simButtonPanel.add(createButtonPanel(jbtOneMinute, "One Minute > "));
		simulationPage.add(viewSimPanel);
		simulationPage.add(simButtonPanel);
		return simulationPage;
	}
	public static void main(String[] args) throws Exception {
		loadDefaultObjects();
		JFrame frame = new Menu();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
