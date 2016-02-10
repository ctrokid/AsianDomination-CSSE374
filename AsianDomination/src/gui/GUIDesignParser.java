package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class GUIDesignParser {

	private JFrame frame;

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
		
		JButton configButton = new JButton("Load Config");
		configButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicking config");
			}
		});
		configButton.setBounds(58, 88, 119, 25);
		frame.getContentPane().add(configButton);
		
		JButton anaylyzeButton = new JButton("Analyze");
		anaylyzeButton.setBounds(253, 88, 102, 25);
		anaylyzeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicking anaylyze");
			}
		});
		frame.getContentPane().add(anaylyzeButton);
		
		JLabel guiMessage = new JLabel("Analyzing");
		guiMessage.setBounds(173, 147, 84, 25);
		frame.getContentPane().add(guiMessage);
		
		
		
	}
}
