package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class GUIUserInput extends JFrame {
	private JTextField pathInputTextField;
	private JTextField packageTextField;
	private JTextField classesTextField;
	private JTextField outputPathTextField;
	private JTextField dotPathTextField;
	private JCheckBox loadClassBox;
	private JCheckBox dotGeneration;
	private JButton btnGenerate;

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

		JLabel folderPathLabel = new JLabel("Folder Path");
		springLayout.putConstraint(SpringLayout.NORTH, folderPathLabel, 28, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, folderPathLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(folderPathLabel);

		pathInputTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, pathInputTextField, -3, SpringLayout.NORTH, folderPathLabel);
		springLayout.putConstraint(SpringLayout.WEST, pathInputTextField, 6, SpringLayout.EAST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.EAST, pathInputTextField, 421, SpringLayout.EAST, folderPathLabel);
		getContentPane().add(pathInputTextField);
		pathInputTextField.setColumns(10);

		JLabel pacakgeLabel = new JLabel("Packages");
		springLayout.putConstraint(SpringLayout.NORTH, pacakgeLabel, 30, SpringLayout.SOUTH, folderPathLabel);
		springLayout.putConstraint(SpringLayout.WEST, pacakgeLabel, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(pacakgeLabel);

		packageTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, packageTextField, 27, SpringLayout.SOUTH, pathInputTextField);
		springLayout.putConstraint(SpringLayout.WEST, packageTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, packageTextField, 0, SpringLayout.EAST, pathInputTextField);
		packageTextField.setColumns(10);
		getContentPane().add(packageTextField);

		JLabel classLabel = new JLabel("Classes");
		springLayout.putConstraint(SpringLayout.NORTH, classLabel, 27, SpringLayout.SOUTH, pacakgeLabel);
		springLayout.putConstraint(SpringLayout.WEST, classLabel, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(classLabel);

		classesTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, classesTextField, 0, SpringLayout.NORTH, classLabel);
		springLayout.putConstraint(SpringLayout.WEST, classesTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, classesTextField, 0, SpringLayout.EAST, pathInputTextField);
		classesTextField.setColumns(10);
		getContentPane().add(classesTextField);

		JLabel lblChoosePhases = new JLabel("Choose Phases");
		springLayout.putConstraint(SpringLayout.WEST, lblChoosePhases, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(lblChoosePhases);

		loadClassBox = new JCheckBox("Load Classes");
		springLayout.putConstraint(SpringLayout.SOUTH, lblChoosePhases, -6, SpringLayout.NORTH, loadClassBox);
		springLayout.putConstraint(SpringLayout.WEST, loadClassBox, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(loadClassBox);

		JCheckBox decoratorBox = new JCheckBox("Detect Decorator");
		springLayout.putConstraint(SpringLayout.NORTH, decoratorBox, 0, SpringLayout.NORTH, loadClassBox);
		getContentPane().add(decoratorBox);

		JCheckBox singletonBox = new JCheckBox("Detect Singleton");
		springLayout.putConstraint(SpringLayout.NORTH, singletonBox, 0, SpringLayout.NORTH, loadClassBox);
		springLayout.putConstraint(SpringLayout.WEST, singletonBox, 31, SpringLayout.EAST, decoratorBox);
		getContentPane().add(singletonBox);

		JCheckBox adapterBox = new JCheckBox("Detect Adapter");
		springLayout.putConstraint(SpringLayout.SOUTH, loadClassBox, -6, SpringLayout.NORTH, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, adapterBox, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, adapterBox, -28, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(adapterBox);

		JCheckBox compositeBox = new JCheckBox("Detect Composite");
		springLayout.putConstraint(SpringLayout.WEST, compositeBox, 16, SpringLayout.EAST, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, decoratorBox, 0, SpringLayout.WEST, compositeBox);
		springLayout.putConstraint(SpringLayout.NORTH, compositeBox, 0, SpringLayout.NORTH, adapterBox);
		getContentPane().add(compositeBox);

		dotGeneration = new JCheckBox("Dot Generation");
		springLayout.putConstraint(SpringLayout.NORTH, dotGeneration, 0, SpringLayout.NORTH, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, dotGeneration, 0, SpringLayout.WEST, singletonBox);
		getContentPane().add(dotGeneration);
		dotGeneration.addItemListener(new enableButtonListener());

		JLabel outputPathLabel = new JLabel("Output Path");
		springLayout.putConstraint(SpringLayout.WEST, outputPathLabel, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, outputPathLabel, -66, SpringLayout.NORTH, lblChoosePhases);
		getContentPane().add(outputPathLabel);

		outputPathTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, outputPathTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.SOUTH, outputPathTextField, -66, SpringLayout.NORTH, lblChoosePhases);
		springLayout.putConstraint(SpringLayout.EAST, outputPathTextField, 0, SpringLayout.EAST, pathInputTextField);
		outputPathTextField.setColumns(10);
		getContentPane().add(outputPathTextField);

		JLabel dotPathLabel = new JLabel("Dot Path");
		springLayout.putConstraint(SpringLayout.NORTH, dotPathLabel, 23, SpringLayout.SOUTH, outputPathLabel);
		springLayout.putConstraint(SpringLayout.EAST, dotPathLabel, 0, SpringLayout.EAST, folderPathLabel);
		getContentPane().add(dotPathLabel);

		dotPathTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, dotPathTextField, 0, SpringLayout.NORTH, dotPathLabel);
		springLayout.putConstraint(SpringLayout.WEST, dotPathTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, dotPathTextField, 0, SpringLayout.EAST, pathInputTextField);
		dotPathTextField.setColumns(10);
		getContentPane().add(dotPathTextField);
		btnGenerate = new JButton("Generate");

		btnGenerate.setEnabled(false);
		loadClassBox.addItemListener(new enableButtonListener());

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		springLayout.putConstraint(SpringLayout.WEST, btnGenerate, 6, SpringLayout.EAST, dotGeneration);
		springLayout.putConstraint(SpringLayout.SOUTH, btnGenerate, -10, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(btnGenerate);

	}

	private class enableButtonListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (loadClassBox.isSelected() && dotGeneration.isSelected()) {
				btnGenerate.setEnabled(true);
			} else
				btnGenerate.setEnabled(false);
		}

	}

}
