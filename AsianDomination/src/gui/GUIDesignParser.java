package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.awt.event.ActionEvent;

import input.InputCommand;
import input.PhaseProgress;
import utils.ProjectConfiguration;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class GUIDesignParser extends JFrame implements Observer {
	private GUIPopulateData populatedData;
	private JProgressBar progressBar;
	private InputCommand cmd;
	private JLabel progressLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDesignParser window = new GUIDesignParser();
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
	public GUIDesignParser() {
		this.populatedData = new GUIPopulateData();
		cmd = null;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(20, 30, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setBackground(Color.white);
		String path = "docs/M7/panda.png";
		File file = new File(path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(image));
		
		label.setLocation(125, 10);
		label.setSize(new Dimension(image.getHeight(), image.getWidth()));
		getContentPane().add(label);

		progressBar = new JProgressBar();
		progressBar.setBounds(150, 426, 175, 14);
		progressBar.setValue(0);
		progressBar.setForeground(Color.green);
		progressBar.setStringPainted(true);
		this.getContentPane().add(progressBar);

		JButton configButton = new JButton("Load Config");
		configButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIUserInput window = new GUIUserInput();
				window.setVisible(true);
				window.setResizable(false);
			}
		});
		configButton.setBounds(90, 340, 102, 25);
		this.getContentPane().add(configButton);

		JButton anaylyzeButton = new JButton("Analyze");
		anaylyzeButton.setBounds(285, 340, 102, 25);

		progressLabel = new JLabel("Waiting for you to hit 'Analyze'...");
		progressLabel.setBounds(148, 399, 184, 25);
		this.getContentPane().add(progressLabel);

		anaylyzeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					try {
						ProjectConfiguration config = new ProjectConfiguration("resources/config.properties");
						cmd = config.getInputCommand();
						cmd.addObserver(GUIDesignParser.this);
						cmd.execute();
						populatedData.accessTargetClasses(cmd.getProjectModel());
						String imagePath = getGeneratedDiagram();
						GUIResult window = new GUIResult(imagePath, populatedData, cmd);
						window.setVisible(true);
						dispose();
					} catch (Exception e2) {
						progressBar.setValue(0);
						progressLabel.setText("Error in Config File");
						return;
					}

				}) {
					{
						start();
					}
				};
			}

		});

		this.getContentPane().add(anaylyzeButton);

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

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof PhaseProgress == false)
			return;
		PhaseProgress p = (PhaseProgress) arg;
		progressBar.setValue(p.getPercentage());
		progressLabel.setText(p.getCurrentPhase());
	}
}
