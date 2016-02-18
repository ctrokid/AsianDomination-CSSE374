package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


import api.IProjectModel;
import api.ITargetClass;
import impl.ProjectModel;
import pattern.decoration.GraphVizStyleTargetClass;

public class GUIPopulateData {

	private int northIndex = 10;
	private final int detectedPatternWestIndex = 10;
	private final int subclassWestIndex = 50;
	private SpringLayout sl_checkboxPane;
	private JPanel checkboxPane;
	private Map<String, ArrayList<JCheckBox>> testmap;
	private String path;
	private IProjectModel projectModel;

	public GUIPopulateData() {
		testmap = new HashMap<String, ArrayList<JCheckBox>>();
		path = "";
		projectModel = new ProjectModel();
	}

	public void setupCheckbox(SpringLayout sl_checkboxPane, JPanel checkboxPane) {
		this.sl_checkboxPane = sl_checkboxPane;
		this.checkboxPane = checkboxPane;

		for (String k : testmap.keySet()) {
			JCheckBox mainbox = new JCheckBox(k);
			positionCheckbox(mainbox, detectedPatternWestIndex);
			mainbox.addItemListener(new detectedPatternCheckListener());
			for (JCheckBox b : testmap.get(k)) {
				positionCheckbox(b, subclassWestIndex);
				// b.addItemListener(new subclassCheckListener());
			}
		}
	}

	public void setNewImage(String imagePath) {
		path = imagePath;

	}

	private void positionCheckbox(JCheckBox box, int indent) {
		sl_checkboxPane.putConstraint(SpringLayout.NORTH, box, northIndex, SpringLayout.NORTH, checkboxPane);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, box, indent, SpringLayout.WEST, checkboxPane);

		Color color;
		try {
			color = (Color) Color.class.getField(box.getName()).get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}

		if (color != null) {
			box.setForeground(color);
		}
		checkboxPane.add(box);
		checkboxPane.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()*1/3, northIndex+100));
		northIndex += 20;
	}

	public int getLastBoxNorthPosition() {
		return northIndex;
	}

	public int getWestPosition() {
		return this.detectedPatternWestIndex;
	}

	public String getPath() {
		return path;
	}

	public JLabel getDigram() {
		JLabel picLabel = null;
		path.replace('\\', '/');
		path += ".png";
		Icon myPicture = new ImageProxy(path);
		picLabel = new JLabel(myPicture);
		return picLabel;
	}

	public String getInitialDiagram() {
		return "docs/M6/ProjectGeneratedUML.png";
	}

	private class detectedPatternCheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox boxChecked = (JCheckBox) e.getSource();
			for (JCheckBox b : testmap.get(boxChecked.getText())) {
				b.setSelected(e.getStateChange() == ItemEvent.SELECTED);
			}
		}
	}

	public Map<String, ArrayList<JCheckBox>> getCheckBoxStates() {
		return testmap;
	}

	// might need this later? should delete when done
	private class subclassCheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
		}
	}

	public void accessTargetClasses(IProjectModel projectModel) {
		this.projectModel = projectModel;
		populateToCheckBox();
	}

	private void populateToCheckBox() {
		Collection<ITargetClass> clazz = projectModel.getTargetClasses();
		for (ITargetClass current : clazz) {
			GraphVizStyleTargetClass decorated = (GraphVizStyleTargetClass) current;
//			if (!decorated.getPatternType().equals(PATTERN_TYPE.GRAPHVIZ_DEFAULT)) {
				JCheckBox checkBox = new JCheckBox(decorated.getClassName());
				checkBox.setName(decorated.getColor());
				String patternType = decorated.getPatternType().name().split("_")[0];
				if (!testmap.containsKey(patternType)) {
					testmap.put(patternType, new ArrayList<JCheckBox>());
				}
				testmap.get(patternType).add(checkBox);
//			}
		}
	}
	
	public Map<String, ArrayList<JCheckBox>> getAllCheckBoxes(){
		return this.testmap;
	}

}
