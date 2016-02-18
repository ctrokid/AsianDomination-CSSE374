package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import java.awt.BorderLayout;

public class HelpAndAbout extends JFrame {
	private barTypes type;
	private HashMap<barTypes, JLabel> typeMap;

	public enum barTypes {
		ABOUT, HELP
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpAndAbout window = new HelpAndAbout(barTypes.HELP);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelpAndAbout(barTypes type) {
		this.type = type;
		typeMap = new HashMap<>();
		setupJLabels();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 800, 260);
		if(this.type.equals(barTypes.ABOUT)){
			setBounds(100, 100, 500, 150);
		}
		JPanel panel = new JPanel();

		panel.add(typeMap.get(type));
		getContentPane().add(panel, BorderLayout.CENTER);

	}

	private void setupJLabels() {
		String helpString = "<html> <div style='text-align: center;'> Help <html> <br> "
				+ "<div style='text-align: left;'> Load configuration: User is able to generate the diagram based on their personal preference. <br>"
				+ "Folder Path: The path must ends with \\src in order for the program to run <br>"
				+ "Classes: The class needs to be in the project directory or in one of the java default package. <br>"
				+ "Output Path: The path must be under the project. <br> "
				+ "Dot Path: The path of the dot generation executable <br>"
				+ "Phases: UML class loading, and Dot generation phase are required. "
				+ "The pattern Detection phase is optional, and they are configurable <br>"
				+ "Singleton Detection: User have the option to force that the getInstance() "
				+ " method return the same type as the singleton stored instance. <br>"
				+ "Adapter Detection: User could decide how many percent of the classes <bt>"
				+ "an adapter needs to use its adaptee function. <br>"
				+ "Composite detection: User is able to enforce add-remove is used in the composite pattern or not. <br>"
				+ "Generate: it will write to the current configuration file, and remember user’s preference. <html>";
		JLabel helpLabel = new JLabel(helpString);
		String aboutString = "<html> <div style='text-align: center;'> About <br> The copyright of this project belongs to Haolin (Coco) Liu, <br> Collin Trowbridge, and Runzhi Yang, which is designed for a  <br> seven-week long project forCSSE374, Software Design, with <br> Chandan Raj Rupakheti at Rose-Hulman Institute of Technology.<html>";
		JLabel aboutLabel = new JLabel(aboutString);
		typeMap.put(barTypes.ABOUT, aboutLabel);
		typeMap.put(barTypes.HELP, helpLabel);
	}
}
