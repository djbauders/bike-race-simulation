/**
 * 
 */
package Default;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author baude2d, dunha2j
 *
 */
public class Menu extends JFrame implements ActionListener{

	ArrayList<String> raceNameList = new ArrayList<String>();
	String cName;
	
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
	JPanel jpSavedObjs = new JPanel(new BorderLayout()); // Panel to view saved objects

	// Menu JComponents and Global Variables
	JButton jbtSaveRace, jbtRunSim, jbtCreateCourse, jbtCreateCyclist, jbtCreateBike, jbtSetWeather;
	JLabel jlMenuTitle, jlRaceName, jlCourse, jlCyclist, jlBike, jlWeather;
	JComboBox<String> jcbCourse, jcbCyclist, jcbBike, jcbWeather;
	String[] exampleStringArr = {"1", "2", "3", "test"};
	JTextField jtfRaceName = new JTextField();
	
	// Course JComponents and Global Variables
	JButton jbtSaveCourse, jbtSaveCName, jbtSaveLength, jbtSaveCP, jbtSaveME, jbtGenerateHills;
	JLabel jlCourseName, jlLength, jlCP, jlMaxElevation, jlHills;
	JComboBox<String> jcbSaveLength, jcbSaveCP, jcbSaveME;
	JTextField jtfCourseName, jtfLength, jtfCP, jtfME;
	JTextArea jtaHills;
	
	// Cyclist JComponents and Global Variables
	JButton jbtRName, jbtRMass, jbtRHeight, jbtLinkEDA, jbtRFTP, jbtRStyle, jbtRBike;
	JLabel jlCyclistName, jlCMass, jlCHeight, jlEDA, jlFTP, jlStyle;
	JComboBox<String> jcbStyle, jcbFTP, jcbCBike;
	JTextField jtfCyclistName, jtfCMass, jtfCHeight, jtfEDA; 
	
	public Menu() {
		//Menu is a tabbed pane
		
		//Initializing JComponents
		JComponent menuPage = createMenuPage();
		JComponent coursePage = createCoursePage();
		JComponent cyclistPage = createCyclistPage();
		JComponent bikePage = createBikePage();
		JComponent weatherPage = createWeatherPage();
		JComponent savedPage = createSavedPage();
		
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
		menuTabbedPane.addTab("Build Bike", bikePage);
		menuTabbedPane.addTab("Build Weather", weatherPage);
		menuTabbedPane.addTab("Saved Objects", savedPage);
		jpMenuCard.add(menuTabbedPane);
		
		add(jpMenuCard);
		
		setTitle("Bike Race Simulation - CPS270");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,750);
		setVisible(true);
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
	
	public JComponent createMenuPage() {
		
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
		
		JComboBox<String> jcbCourse = new JComboBox<String>(exampleStringArr);
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
		
		JComboBox<String> jcbCyclist = new JComboBox<String>(exampleStringArr);
		jcbCyclist.setBorder(bLineBorder);
		//menuGrid || Row 4 || Your Bike
		JLabel jlBike = new JLabel("Your Bike");
		jlBike.setFont(tnr);
		jlBike.setForeground(gold);
		jlBike.setBackground(maroon);
		
		JButton jbtCreateBike = new JButton("+");
		jbtCreateBike.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				menuTabbedPane.setSelectedIndex(3);
			}
		});
		
		JComboBox<String> jcbBike = new JComboBox<String>(exampleStringArr);
		jcbBike.setBorder(bLineBorder);
		//menuGrid || Row 5 || Weather
		JLabel jlWeather = new JLabel("Weather Conditions");
		jlWeather.setFont(tnr);
		jlWeather.setForeground(gold);
		jlWeather.setBackground(maroon);
		JButton jbtCreateWeather = new JButton("+");
		jbtCreateWeather.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				menuTabbedPane.setSelectedIndex(4);
			}
		});
		
		JComboBox<String> jcbWeather = new JComboBox<String>(exampleStringArr);
		jcbWeather.setBorder(bLineBorder);
		//END OF menuGrid
		
		//START OF menuBPanel
		
		//menuBPanel || Row 1 || Save Race
		JButton jbtSaveRace = new JButton("Save Race");
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
		//R4
		menuGrid.add(jlBike);
		menuGrid.add(createButtonPanel(jbtCreateBike, "Create Bike > "));
		menuGrid.add(jcbBike);
		//R5
		menuGrid.add(jlWeather);
		menuGrid.add(createButtonPanel(jbtCreateWeather, "Create Weather > "));
		menuGrid.add(jcbWeather);
		
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
				//Read in random name from file of popular baby names (Not including any default racer names)
				
				
				
			}
		});
		
		
		//courseGrid || Row 2 || Length in Km
		JLabel jlLength = new JLabel("Length in Km");
		jlLength.setFont(tnr);
		jlLength.setForeground(gold);
		jlLength.setBackground(maroon);
		
		JButton jbtSaveLength = new JButton("+");
		
		JTextField jtfLength = new JTextField();
		jtfLength.setBorder(bLineBorder);
		
		jbtSaveLength.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String cLength = jtfLength.getText();
				System.out.print(cLength);
			}
		});
		//courseGrid || Row 3 || Number of Checkpoints
		JLabel jlCP = new JLabel("Number of Checkpoints");
		jlCP.setFont(tnr);
		jlCP.setForeground(gold);
		jlCP.setBackground(maroon);
		
		JButton jbtSaveCP = new JButton("+");
		
		JTextField jtfCP = new JTextField();
		jtfCP.setBorder(bLineBorder);
		
		jbtSaveCP.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String strCP = jtfCP.getText();
				System.out.print(strCP);
			}
		});
		//courseGrid || Row 4 || Max Elevation
		JLabel jlME = new JLabel("Maximum Elevation");
		jlME.setFont(tnr);
		jlME.setForeground(gold);
		jlME.setBackground(maroon);
		
		JButton jbtSaveME = new JButton("+");
		
		JTextField jtfME = new JTextField();
		jtfME.setBorder(bLineBorder);
		
		jbtSaveME.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String strME = jtfME.getText();
				System.out.print(strME);
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

		//END OF courseGrid
		
		//START OF graphPanel
		//Figure out how to implement graph or scrap it before deadline
		//END OF graphPanel
		
		//R1
		courseGrid.add(jlCourseName);
		courseGrid.add(create2ButtonPanel(jbtRCName, jbtSaveCName, "Random Name > ", "Save Name > "));
		courseGrid.add(jtfCourseName);
		//R2
		courseGrid.add(jlLength);
		courseGrid.add(createButtonPanel(jbtSaveLength, "Save Length > "));
		courseGrid.add(jtfLength);
		//R3
		courseGrid.add(jlCP);
		courseGrid.add(createButtonPanel(jbtSaveCP, "Save Checkpoints > "));
		courseGrid.add(jtfCP);
		//R4
		courseGrid.add(jlME);
		courseGrid.add(createButtonPanel(jbtSaveME, "Save Max Elev. > "));
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
		//cyclistGrid || Row 2 || Cyclist Mass (kg)
		JLabel jlCMass = new JLabel("Cyclist Mass");
		jlCMass.setFont(tnr);
		jlCMass.setForeground(gold);
		jlCMass.setBackground(maroon);
		
		JButton jbtCMass = new JButton("+");
		
		JTextField jtfCMass = new JTextField();
		jtfCMass.setBorder(bLineBorder);
		//cyclistGrid || Row 3 || Cyclist Height ()
		JLabel jlCHeight = new JLabel("Cyclist Height");
		jlCHeight.setFont(tnr);
		jlCHeight.setForeground(gold);
		jlCHeight.setBackground(maroon);
		
		JButton jbtRHeight = new JButton("+");
		
		JTextField jtfCHeight = new JTextField();
		jtfCHeight.setBorder(bLineBorder);
		//cyclistGrid || Row 4 || Effective Drag Area
		JLabel jlEDA = new JLabel("Cyclist's Name");
		jlEDA.setFont(tnr);
		jlEDA.setForeground(gold);
		jlEDA.setBackground(maroon);
		
		JButton jbtEDA = new JButton("+");
		
		JTextField jtfEDA = new JTextField();
		jtfEDA.setBorder(bLineBorder);
		//cyclistGrid || Row 5 || FTP - Wattage
		JLabel jlFTP = new JLabel("Cyclist FTP");
		jlFTP.setFont(tnr);
		jlFTP.setForeground(gold);
		jlFTP.setBackground(maroon);
		
		JButton jbtFTP = new JButton("+");
		
		JTextField jtfFTP = new JTextField();
		jtfFTP.setBorder(bLineBorder);
		//cyclistGrid || Row 6 || Bike Object
		
		//END OF cyclistPage
		
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
		cyclistGrid.add(jlEDA);
		cyclistGrid.add(createButtonPanel(jbtEDA, "Link to calculator > "));
		cyclistGrid.add(jtfEDA);
		//R5
		cyclistGrid.add(jlFTP);
		cyclistGrid.add(createButtonPanel(jbtFTP, "Random FTP > "));
		cyclistGrid.add(jtfFTP);
		//R6
		
		
		cyclistPage.add(cyclistGrid);
		cyclistPage.add(cyclistBPanel);
		return cyclistPage;
	}
	
	public JComponent createBikePage() {
		JPanel bikePage = new JPanel();
		
		return bikePage;
	}
	
	public JComponent createWeatherPage() {
		JPanel weatherPage = new JPanel();
		
		return weatherPage;
	}
	
	public JComponent createSavedPage() {
		JPanel savedPage = new JPanel();
		
		return savedPage;
	}
	public static void main(String[] args) {
		JFrame frame = new Menu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
