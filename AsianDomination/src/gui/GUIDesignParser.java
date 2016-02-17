package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

public class GUIDesignParser implements Observer{
	private JFrame frame;
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

		progressBar = new JProgressBar();
		progressBar.setBounds(132, 174, 175, 14);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		frame.getContentPane().add(progressBar);

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
		anaylyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new Thread(() ->{
                     ProjectConfiguration config = new ProjectConfiguration("resources/config.properties");
                     cmd = config.getInputCommand();
                     cmd.addObserver(GUIDesignParser.this);
                     
                     cmd.execute();
                      populatedData.accessTargetClasses(cmd.getProjectModel());
               }){{start();}};
            }
            
     });
		
		frame.getContentPane().add(anaylyzeButton);

		progressLabel = new JLabel("Waiting for you to hit 'Analyze'...");
		progressLabel.setBounds(130, 147, 184, 25);
		frame.getContentPane().add(progressLabel);

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
			ProjectConfiguration config = new ProjectConfiguration("resources/config.properties");
			InputCommand _cmd = config.getInputCommand();
			cmd = _cmd;
			_cmd.execute();
			populatedData.accessTargetClasses(_cmd.getProjectModel());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof PhaseProgress==false)
			return;
		PhaseProgress p = (PhaseProgress) arg;
		progressBar.setValue(p.getPercentage());
		progressLabel.setText(p.getCurrentPhase());
	}
}
