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
public class Menu extends JFrame{

	Color gold = new Color(252, 205, 53);
	Color maroon = new Color(128, 0, 0);
	Color black = new Color(0, 0, 0);
	Font tnr = new Font("TimesNewRoman", 20, 20);
	Font title = new Font("TimesNewRoman", 30, 30);
	Border lineBorder = new LineBorder(new Color(252,205,53),5);

	CardLayout clMenu = new CardLayout();
	GridLayout glMenu;
	LineBorder bBorder = new LineBorder(gold, 5);
	LineBorder tBorder = new LineBorder(gold, 3);

	JPanel jpPanelContainer = new JPanel(); // Panel container for entire tab
	JPanel jpMenuCard = new JPanel(clMenu); // Menu for simulation and setting values/objs
	JPanel jpBuildCyclist = new JPanel(new BorderLayout()); // Panel for building cyclists
	JPanel jpBuildCourse = new JPanel(new BorderLayout()); // Panel for building a race course
	JPanel jpSavedObjs = new JPanel(new BorderLayout()); // Panel to view saved objects

	// Menu JComponents and Global Variables
	JButton jbtSaveRace, jbtRunSim, jbtCreateCourse, jbtCreateCyclist, jbtCreateBike, jbtSetWeather;
	JLabel jlMenuTitle, jlRaceName, jlCourse, jlCyclist, jlBike, jlWeather;
	JComboBox<String> jcbCourse, jcbCyclist, jcbBike, jcbWeather;
	JTextField jtfRaceName = new JTextField();

	public Menu() {
		//Menu is a tabbed pane
		JTabbedPane menuTabbedPane = new JTabbedPane();
		
		//Initializing JComponents
		JComponent menuPage = createMenuPage();
		JComponent coursePage = createCoursePage();
		JComponent cyclistPage = createCyclistPage();
		JComponent savedPage = createSavedPage();
		
		//Adding pages to card layout
		jpMenuCard.add(menuPage, "Menu");
		jpMenuCard.add(coursePage, "Build Course");
		jpMenuCard.add(cyclistPage, "Build Cyclist");
		jpMenuCard.add(savedPage, "Saved Objects");
		
		//Action events/listeners
		
		
		add(jpMenuCard);
		setTitle("Bike Race Simulation - CPS270");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,475);
		setVisible(true);
	}
	
	public JComponent createMenuPage() {
		
		JPanel menuPage = new JPanel();
		menuPage.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JPanel  menuGrid = new JPanel(new GridLayout(3,5));
		menuGrid.setBorder(lineBorder);
		
		//add components
		JLabel jlMenuTitle = new JLabel("					Bike Race Sim Menu					");
		jlMenuTitle.setFont(tnr);
		jlMenuTitle.setForeground(new Color(255,204,51));
		jlMenuTitle.setHorizontalAlignment(SwingConstants.RIGHT);

		menuPage.add(jlMenuTitle);
		
		menuPage.setBackground(new Color(128,0,0));
		
		return menuPage;
	}
	
	public JComponent createCoursePage() {
		JPanel coursePage = new JPanel();
		
		return coursePage;
	}
	
	public JComponent createCyclistPage() {
		JPanel cyclistPage = new JPanel();
		
		return cyclistPage;
	}
	
	public JComponent createSavedPage() {
		JPanel savedPage = new JPanel();
		
		return savedPage;
	}
	public static void main(String[] args) {
		JFrame frame = new Menu();
	}
}
