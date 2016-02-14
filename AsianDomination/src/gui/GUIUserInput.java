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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
	private Map<String, String> propertiesDataMap;
	private ArrayList<JCheckBox> checkBoxes;

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
		propertiesDataMap = new HashMap<>();
		checkBoxes = new ArrayList<>();
		GUIReadConfigFile configFileData = new GUIReadConfigFile();
		configFileData.readFromFile();
		propertiesDataMap = configFileData.getPropertiesDataMap();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 550, 450);
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
		pathInputTextField.setText(propertiesDataMap.get("input-folder"));
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
		packageTextField.setText(propertiesDataMap.get("input-packages"));
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
		classesTextField.setText(propertiesDataMap.get("input-classes"));
		classesTextField.setColumns(10);
		getContentPane().add(classesTextField);

		JLabel outputPathLabel = new JLabel("Output Path");
		springLayout.putConstraint(SpringLayout.WEST, outputPathLabel, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.NORTH, outputPathLabel, 40, SpringLayout.NORTH, classesTextField);
		getContentPane().add(outputPathLabel);

		outputPathTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, outputPathTextField, -3, SpringLayout.NORTH, outputPathLabel);
		springLayout.putConstraint(SpringLayout.WEST, outputPathTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, outputPathTextField, 0, SpringLayout.EAST, pathInputTextField);
		outputPathTextField.setText(propertiesDataMap.get("output-dir"));
		outputPathTextField.setColumns(10);
		getContentPane().add(outputPathTextField);

		JLabel dotPathLabel = new JLabel("Dot Path");
		springLayout.putConstraint(SpringLayout.WEST, dotPathLabel, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(dotPathLabel);

		dotPathTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, dotPathTextField, 20, SpringLayout.SOUTH, outputPathTextField);
		springLayout.putConstraint(SpringLayout.NORTH, dotPathLabel, 3, SpringLayout.NORTH, dotPathTextField);
		springLayout.putConstraint(SpringLayout.WEST, dotPathTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, dotPathTextField, 0, SpringLayout.EAST, pathInputTextField);
		dotPathTextField.setText(propertiesDataMap.get("dot-path"));
		dotPathTextField.setColumns(10);
		getContentPane().add(dotPathTextField);
		
		
		JLabel lblChoosePhases = new JLabel("Choose Phases");
		springLayout.putConstraint(SpringLayout.WEST, lblChoosePhases, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblChoosePhases, 260, SpringLayout.NORTH, getContentPane());
		getContentPane().add(lblChoosePhases);

		loadClassBox = new JCheckBox("Class-Loading");
		springLayout.putConstraint(SpringLayout.WEST, loadClassBox, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, loadClassBox, 50, SpringLayout.NORTH, lblChoosePhases);
		getContentPane().add(loadClassBox);
		checkBoxes.add(loadClassBox);

		JCheckBox decoratorBox = new JCheckBox("Decorator-Detection");
		springLayout.putConstraint(SpringLayout.NORTH, decoratorBox, 0, SpringLayout.NORTH, loadClassBox);
		getContentPane().add(decoratorBox);
		checkBoxes.add(decoratorBox);

		JCheckBox singletonBox = new JCheckBox("Singleton-Detection-Visitor");
		springLayout.putConstraint(SpringLayout.NORTH, singletonBox, 0, SpringLayout.NORTH, loadClassBox);
		springLayout.putConstraint(SpringLayout.WEST, singletonBox, 31, SpringLayout.EAST, decoratorBox);
		getContentPane().add(singletonBox);
		checkBoxes.add(singletonBox);

		JCheckBox adapterBox = new JCheckBox("Adapter-Detection");
		springLayout.putConstraint(SpringLayout.WEST, adapterBox, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, adapterBox, 70, SpringLayout.SOUTH, lblChoosePhases);
		getContentPane().add(adapterBox);
		checkBoxes.add(adapterBox);

		JCheckBox compositeBox = new JCheckBox("Composite-Detection");
		springLayout.putConstraint(SpringLayout.WEST, compositeBox, 16, SpringLayout.EAST, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, decoratorBox, 0, SpringLayout.WEST, compositeBox);
		springLayout.putConstraint(SpringLayout.NORTH, compositeBox, 0, SpringLayout.NORTH, adapterBox);
		getContentPane().add(compositeBox);
		checkBoxes.add(compositeBox);

		dotGeneration = new JCheckBox("DOT-Generation");
		springLayout.putConstraint(SpringLayout.NORTH, dotGeneration, 0, SpringLayout.NORTH, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, dotGeneration, 0, SpringLayout.WEST, singletonBox);
		getContentPane().add(dotGeneration);
		checkBoxes.add(dotGeneration);
		dotGeneration.addItemListener(new enableButtonListener());
		
		
		
		btnGenerate = new JButton("Generate");
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 30, SpringLayout.SOUTH, dotGeneration);
		springLayout.putConstraint(SpringLayout.EAST, btnGenerate, 0, SpringLayout.EAST, singletonBox);

		String[] patterns = propertiesDataMap.get("phases").trim().split(",");
		for (String p : patterns) {
			for (JCheckBox b : checkBoxes) {
				if (b.getText().toLowerCase().equals(p.toLowerCase())) {

					b.setSelected(true);
				}
			}
		}

		if (loadClassBox.isSelected() && dotGeneration.isSelected()) {
			btnGenerate.setEnabled(true);
		} else {
			btnGenerate.setEnabled(false);
		}
		loadClassBox.addItemListener(new enableButtonListener());

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO:
			}
		});
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
