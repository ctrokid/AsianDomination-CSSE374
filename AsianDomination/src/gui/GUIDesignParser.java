package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import framework.IPhase;
import input.InputCommand;
import output.UMLDiagramOutputStream;
import utils.ProjectConfiguration;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class GUIDesignParser {
	private final static int interval = 10;
	private Timer timer;
	private JFrame frame;
	private Integer i;
	private GUIPopulateData populatedData;
	private InputCommand cmd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDesignParser window = new GUIDesignParser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIDesignParser() {
		this.populatedData = new GUIPopulateData();
		cmd = null;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(132, 174, 146, 14);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		frame.getContentPane().add(progressBar);

		timer = new Timer(interval, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 100) {
					timer.stop();
				} else {
					i += 1;
					progressBar.setValue(i);
				}
			}
		});

		JButton configButton = new JButton("Load Config");
		configButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String imagePath = getGeneratedDiagram();
				GUIResult window = new GUIResult(imagePath, populatedData, cmd);
				window.setVisible(true);
			}
		});
		configButton.setBounds(58, 88, 119, 25);
		frame.getContentPane().add(configButton);

		JButton anaylyzeButton = new JButton("Analyze");
		anaylyzeButton.setBounds(253, 88, 102, 25);
		anaylyzeButton.addActionListener(new analyzeActionListener());
		frame.getContentPane().add(anaylyzeButton);

		JLabel guiMessage = new JLabel("Analyzing");
		guiMessage.setBounds(173, 147, 84, 25);
		frame.getContentPane().add(guiMessage);

	}

	private String getGeneratedDiagram() {
		Properties prop = new Properties();
		InputStream input = null;
		String output = "";
		try {

			String filename = "config.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return "";
			}

			prop.load(input);
			output = prop.getProperty("output-dir");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return output;
	}

	private class analyzeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			i = 0;
			timer.start();
			ProjectConfiguration config = new ProjectConfiguration("resources/config.properties");
			InputCommand _cmd = config.getInputCommand();
			cmd = _cmd;
			_cmd.execute();
			populatedData.accessTargetClasses(_cmd.getProjectModel());
		}
	}
}
