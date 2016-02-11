package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class GUIUserInput extends JFrame {
	private JTextField pathInput;
	private JTextField classesInput;
	private JTextField outputPathInput;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIUserInput window = new GUIUserInput();
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
	public GUIUserInput() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 550, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("User Input");
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel folderPath = new JLabel("Folder Path");
		springLayout.putConstraint(SpringLayout.NORTH, folderPath, 28, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, folderPath, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(folderPath);
		
		pathInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, pathInput, -3, SpringLayout.NORTH, folderPath);
		springLayout.putConstraint(SpringLayout.WEST, pathInput, 6, SpringLayout.EAST, folderPath);
		springLayout.putConstraint(SpringLayout.EAST, pathInput, 421, SpringLayout.EAST, folderPath);
		getContentPane().add(pathInput);
		pathInput.setColumns(10);
		
		JLabel inputClasses = new JLabel("Input Classes");
		springLayout.putConstraint(SpringLayout.NORTH, inputClasses, 30, SpringLayout.SOUTH, folderPath);
		springLayout.putConstraint(SpringLayout.WEST, inputClasses, 0, SpringLayout.WEST, folderPath);
		getContentPane().add(inputClasses);
		
		classesInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, classesInput, 0, SpringLayout.NORTH, inputClasses);
		springLayout.putConstraint(SpringLayout.WEST, classesInput, 0, SpringLayout.WEST, pathInput);
		springLayout.putConstraint(SpringLayout.EAST, classesInput, 0, SpringLayout.EAST, pathInput);
		classesInput.setColumns(10);
		getContentPane().add(classesInput);
		
		JLabel outputPath = new JLabel("Output Path");
		springLayout.putConstraint(SpringLayout.NORTH, outputPath, 30, SpringLayout.SOUTH, inputClasses);
		springLayout.putConstraint(SpringLayout.WEST, outputPath, 0, SpringLayout.WEST, folderPath);
		getContentPane().add(outputPath);
		
		outputPathInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, outputPathInput, 0, SpringLayout.NORTH, outputPath);
		springLayout.putConstraint(SpringLayout.WEST, outputPathInput, 0, SpringLayout.WEST, pathInput);
		springLayout.putConstraint(SpringLayout.EAST, outputPathInput, 0, SpringLayout.EAST, pathInput);
		outputPathInput.setColumns(10);
		getContentPane().add(outputPathInput);
		
		JLabel lblChoosePhases = new JLabel("Choose Phases");
		springLayout.putConstraint(SpringLayout.WEST, lblChoosePhases, 0, SpringLayout.WEST, folderPath);
		springLayout.putConstraint(SpringLayout.SOUTH, lblChoosePhases,175, SpringLayout.NORTH, folderPath);
		getContentPane().add(lblChoosePhases);
		
		JCheckBox loadClassBox = new JCheckBox("Load Classes");
		springLayout.putConstraint(SpringLayout.NORTH, loadClassBox, 11, SpringLayout.SOUTH, lblChoosePhases);
		springLayout.putConstraint(SpringLayout.WEST, loadClassBox, 0, SpringLayout.WEST, folderPath);
		getContentPane().add(loadClassBox);
		
		JCheckBox decoratorBox = new JCheckBox("Detect Decorator");
		springLayout.putConstraint(SpringLayout.WEST, decoratorBox, 30, SpringLayout.EAST, loadClassBox);
		springLayout.putConstraint(SpringLayout.SOUTH, decoratorBox, 0, SpringLayout.SOUTH, loadClassBox);
		getContentPane().add(decoratorBox);
		
		JCheckBox singletonBox = new JCheckBox("Detect Singleton");
		springLayout.putConstraint(SpringLayout.WEST, singletonBox, 30, SpringLayout.EAST, decoratorBox);
		springLayout.putConstraint(SpringLayout.SOUTH, singletonBox, 0, SpringLayout.SOUTH, decoratorBox);
		getContentPane().add(singletonBox);
		
		JCheckBox adapterBox = new JCheckBox("Detect Adapter");
		springLayout.putConstraint(SpringLayout.NORTH, adapterBox, 6, SpringLayout.SOUTH, loadClassBox);
		springLayout.putConstraint(SpringLayout.WEST, adapterBox, 0, SpringLayout.WEST, folderPath);
		getContentPane().add(adapterBox);
		
		JCheckBox compositeBox = new JCheckBox("Detect Composite");
		springLayout.putConstraint(SpringLayout.WEST, compositeBox, 0, SpringLayout.WEST, decoratorBox);
		springLayout.putConstraint(SpringLayout.SOUTH, compositeBox, 0, SpringLayout.SOUTH, adapterBox);
		getContentPane().add(compositeBox);
		
		JCheckBox dotGeneration = new JCheckBox("Dot Generation");
		springLayout.putConstraint(SpringLayout.WEST, dotGeneration, 0, SpringLayout.WEST, singletonBox);
		springLayout.putConstraint(SpringLayout.SOUTH, dotGeneration, 0, SpringLayout.SOUTH, adapterBox);
		getContentPane().add(dotGeneration);
		
		
	}
}
