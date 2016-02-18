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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUIUserInput extends JFrame {
	private JTextField pathInputTextField;
	private JTextField packageTextField;
	private JTextField classesTextField;
	private JTextField outputPathTextField;
	private JTextField dotPathTextField;
	private Map<String, JTextField> allFields;
	private JCheckBox loadClassBox;
	private JCheckBox dotGeneration;
	private JButton btnGenerate;
	private Map<String, String> propertiesDataMap;
	private ArrayList<JCheckBox> checkBoxes;
	private JComboBox singletonComboBox;
	private GUIReadConfigFile configFileData;
	private JSpinner adapterSpinner;
	private JComboBox compositeComboBox;

	public GUIUserInput() {
		propertiesDataMap = new HashMap<>();
		checkBoxes = new ArrayList<>();
		allFields = new HashMap<>();
		singletonComboBox = new JComboBox();
		compositeComboBox = new JComboBox();
		adapterSpinner = new JSpinner();
		singletonComboBox.setName("Singleton-Detection");
		compositeComboBox.setName("Composite-Detection");
		configFileData = new GUIReadConfigFile();
		configFileData.readFromFile();
		propertiesDataMap = configFileData.getPropertiesDataMap();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 550, 450);
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
		pathInputTextField.setName("input-folder");
		pathInputTextField.setText(propertiesDataMap.get(pathInputTextField.getName()));
		getContentPane().add(pathInputTextField);
		pathInputTextField.setColumns(10);
		allFields.put(pathInputTextField.getName(), pathInputTextField);

		JLabel pacakgeLabel = new JLabel("Packages");
		springLayout.putConstraint(SpringLayout.NORTH, pacakgeLabel, 30, SpringLayout.SOUTH, folderPathLabel);
		springLayout.putConstraint(SpringLayout.WEST, pacakgeLabel, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(pacakgeLabel);

		packageTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, packageTextField, 27, SpringLayout.SOUTH, pathInputTextField);
		springLayout.putConstraint(SpringLayout.WEST, packageTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, packageTextField, 0, SpringLayout.EAST, pathInputTextField);
		packageTextField.setName("input-packages");
		packageTextField.setText(propertiesDataMap.get(packageTextField.getName()));
		packageTextField.setColumns(10);
		getContentPane().add(packageTextField);
		allFields.put(packageTextField.getName(), packageTextField);

		JLabel classLabel = new JLabel("Classes");
		springLayout.putConstraint(SpringLayout.NORTH, classLabel, 27, SpringLayout.SOUTH, pacakgeLabel);
		springLayout.putConstraint(SpringLayout.WEST, classLabel, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(classLabel);

		classesTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, classesTextField, 0, SpringLayout.NORTH, classLabel);
		springLayout.putConstraint(SpringLayout.WEST, classesTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, classesTextField, 0, SpringLayout.EAST, pathInputTextField);
		classesTextField.setName("input-classes");
		classesTextField.setText(propertiesDataMap.get(classesTextField.getName()));
		classesTextField.setColumns(10);
		getContentPane().add(classesTextField);
		allFields.put(classesTextField.getName(), classesTextField);

		JLabel outputPathLabel = new JLabel("Output Path");
		springLayout.putConstraint(SpringLayout.WEST, outputPathLabel, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.NORTH, outputPathLabel, 40, SpringLayout.NORTH, classesTextField);
		getContentPane().add(outputPathLabel);

		outputPathTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, outputPathTextField, -3, SpringLayout.NORTH, outputPathLabel);
		springLayout.putConstraint(SpringLayout.WEST, outputPathTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, outputPathTextField, 0, SpringLayout.EAST, pathInputTextField);
		outputPathTextField.setName("output-dir");
		outputPathTextField.setText(propertiesDataMap.get(outputPathTextField.getName()));
		outputPathTextField.setColumns(10);
		getContentPane().add(outputPathTextField);
		allFields.put(outputPathTextField.getName(), outputPathTextField);

		JLabel dotPathLabel = new JLabel("Dot Path");
		springLayout.putConstraint(SpringLayout.WEST, dotPathLabel, 0, SpringLayout.WEST, folderPathLabel);
		getContentPane().add(dotPathLabel);

		dotPathTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, dotPathTextField, 20, SpringLayout.SOUTH, outputPathTextField);
		springLayout.putConstraint(SpringLayout.NORTH, dotPathLabel, 3, SpringLayout.NORTH, dotPathTextField);
		springLayout.putConstraint(SpringLayout.WEST, dotPathTextField, 0, SpringLayout.WEST, pathInputTextField);
		springLayout.putConstraint(SpringLayout.EAST, dotPathTextField, 0, SpringLayout.EAST, pathInputTextField);
		dotPathTextField.setName("dot-path");
		dotPathTextField.setText(propertiesDataMap.get(dotPathTextField.getName()));
		dotPathTextField.setColumns(10);
		getContentPane().add(dotPathTextField);
		allFields.put(dotPathTextField.getName(), dotPathTextField);

		JLabel lblChoosePhases = new JLabel("Choose Phases");
		springLayout.putConstraint(SpringLayout.WEST, lblChoosePhases, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblChoosePhases, 260, SpringLayout.NORTH, getContentPane());
		getContentPane().add(lblChoosePhases);

		loadClassBox = new JCheckBox("UML-Class-Loading");
		springLayout.putConstraint(SpringLayout.WEST, loadClassBox, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, loadClassBox, 50, SpringLayout.NORTH, lblChoosePhases);
		getContentPane().add(loadClassBox);
		checkBoxes.add(loadClassBox);

		JCheckBox decoratorBox = new JCheckBox("Decorator-Detection");
		springLayout.putConstraint(SpringLayout.WEST, compositeComboBox, 0, SpringLayout.WEST, decoratorBox);
		springLayout.putConstraint(SpringLayout.NORTH, decoratorBox, 0, SpringLayout.NORTH, loadClassBox);
		springLayout.putConstraint(SpringLayout.WEST, decoratorBox, 10, SpringLayout.EAST, loadClassBox);
		getContentPane().add(decoratorBox);
		checkBoxes.add(decoratorBox);

		JCheckBox singletonBox = new JCheckBox("Singleton-Detection");
		springLayout.putConstraint(SpringLayout.NORTH, singletonBox, 0, SpringLayout.NORTH, loadClassBox);
		springLayout.putConstraint(SpringLayout.WEST, singletonBox, 31, SpringLayout.EAST, decoratorBox);
		getContentPane().add(singletonBox);
		singletonBox.addItemListener(new enableChoiceListener());
		checkBoxes.add(singletonBox);

		JCheckBox adapterBox = new JCheckBox("Adapter-Detection");
		springLayout.putConstraint(SpringLayout.WEST, adapterBox, 0, SpringLayout.WEST, folderPathLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, adapterBox, 85, SpringLayout.NORTH, loadClassBox);
		getContentPane().add(adapterBox);

		springLayout.putConstraint(SpringLayout.NORTH, adapterSpinner, 6, SpringLayout.SOUTH, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, adapterSpinner, 30, SpringLayout.WEST, getContentPane());
		adapterSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		adapterSpinner.setName("Adapter-Detection");
		adapterSpinner.setVisible(adapterBox.isSelected());
		if (propertiesDataMap.containsKey("adapter-method-delegation"))
			adapterSpinner.setValue(Integer.parseInt(propertiesDataMap.get("adapter-method-delegation")));
		getContentPane().add(adapterSpinner);

		adapterBox.addItemListener(new enableChoiceListener());
		checkBoxes.add(adapterBox);

		JCheckBox compositeBox = new JCheckBox("Composite-Detection");
		springLayout.putConstraint(SpringLayout.NORTH, compositeComboBox, 6, SpringLayout.SOUTH, compositeBox);
		springLayout.putConstraint(SpringLayout.NORTH, compositeBox, 0, SpringLayout.NORTH, adapterBox);
		springLayout.putConstraint(SpringLayout.EAST, compositeBox, 0, SpringLayout.EAST, decoratorBox);
		getContentPane().add(compositeBox);
		compositeBox.addItemListener(new enableChoiceListener());
		checkBoxes.add(compositeBox);

		dotGeneration = new JCheckBox("DOT-Generation");
		springLayout.putConstraint(SpringLayout.NORTH, dotGeneration, 0, SpringLayout.NORTH, adapterBox);
		springLayout.putConstraint(SpringLayout.WEST, dotGeneration, 31, SpringLayout.EAST, compositeBox);
		getContentPane().add(dotGeneration);
		checkBoxes.add(dotGeneration);
		dotGeneration.addItemListener(new enableButtonListener());

		btnGenerate = new JButton("Generate");
		springLayout.putConstraint(SpringLayout.SOUTH, btnGenerate, -18, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGenerate, 0, SpringLayout.EAST, singletonBox);

		String[] patterns = propertiesDataMap.get("phases").trim().split(",");
		for (String p : patterns) {
			for (JCheckBox b : checkBoxes) {
				if (b.getText().toLowerCase().equals(p.trim().toLowerCase())) {
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
				for (String key : propertiesDataMap.keySet()) {
					for (String name : allFields.keySet()) {
						if (key.equals(name)) {
							propertiesDataMap.put(key, allFields.get(name).getText());
						}
					}
					if (key.equals("phases")) {
						ArrayList<String> selectedPhases = new ArrayList<>();
						for (JCheckBox b : checkBoxes) {
							if (b.isSelected())
								selectedPhases.add(b.getText());
						}
						propertiesDataMap.put(key,
								selectedPhases.toString().substring(1, selectedPhases.toString().length() - 1));
					}
					if (key.equals("singleton-require-getInstance")) {
						String statement = "true";
						if (singletonComboBox.getSelectedIndex() == 1) {
							statement = "false";
						}
						propertiesDataMap.put(key, statement);
					}
					if (key.equals("composite-require-addAndRemoveMethodsOneParameter")) {
						String statement = "true";
						if (compositeComboBox.getSelectedIndex() == 1) {
							statement = "false";
						}
						propertiesDataMap.put(key, statement);
					}

					if (key.equals("adapter-method-delegation")) {
						String percent = "0";
						if (adapterBox.isSelected()) {
							percent = adapterSpinner.getValue().toString();
						}
						propertiesDataMap.put(key, percent);
					}
				}
				configFileData.writeToFile(propertiesDataMap);
				dispose();
			}

		});
		getContentPane().add(btnGenerate);

		int selectedIndex = 0;
		singletonComboBox.setModel(new DefaultComboBoxModel(new String[] { "True\t", "False" }));
		if (propertiesDataMap.containsKey("singleton-require-getInstance") && propertiesDataMap.get("singleton-require-getInstance").equals("false")) {
			selectedIndex = 1;
		}
		singletonComboBox.setVisible(singletonBox.isSelected());
		singletonComboBox.setSelectedIndex(selectedIndex);
		springLayout.putConstraint(SpringLayout.NORTH, singletonComboBox, 6, SpringLayout.SOUTH, singletonBox);
		springLayout.putConstraint(SpringLayout.EAST, singletonComboBox, -146, SpringLayout.EAST, getContentPane());
		getContentPane().add(singletonComboBox);

		int compositeIndex = 0;
		compositeComboBox.setModel(new DefaultComboBoxModel(new String[] { "True\t", "False" }));
		if (propertiesDataMap.containsKey("composite-require-addAndRemoveMethodsOneParameter") && propertiesDataMap.get("composite-require-addAndRemoveMethodsOneParameter").equals("false")) {
			compositeIndex = 1;
		}
		compositeComboBox.setVisible(compositeBox.isSelected());
		compositeComboBox.setSelectedIndex(compositeIndex);
		getContentPane().add(compositeComboBox);

	}

	private class enableChoiceListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox currentBox = (JCheckBox) e.getSource();

			if (adapterSpinner.getName().equals(currentBox.getText())) {
				adapterSpinner.setVisible(currentBox.isSelected());
			}
			if (singletonComboBox.getName().equals(currentBox.getText())) {
				singletonComboBox.setVisible(currentBox.isSelected());
			}
			if (compositeComboBox.getName().equals(currentBox.getText())) {
				compositeComboBox.setVisible(currentBox.isSelected());
			}
		}

	}

	private class enableButtonListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (loadClassBox.isSelected() && dotGeneration.isSelected()) {
				btnGenerate.setEnabled(true);
			} else {
				btnGenerate.setEnabled(false);
			}
		}
	}
}
