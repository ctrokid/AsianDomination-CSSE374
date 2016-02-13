package gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class GUIPopulateData {

	private int northIndex = 10;
	private final int detectedPatternWestIndex = 10;
	private final int subclassWestIndex = 50;
	private SpringLayout sl_checkboxPane;
	private JPanel checkboxPane;
	private Map<String, ArrayList<JCheckBox>> testmap;
	private String path;

	public GUIPopulateData() {
		testmap = new HashMap<String, ArrayList<JCheckBox>>();
		path = getInitialDiagram();

	}

	public void setupCheckbox(SpringLayout sl_checkboxPane, JPanel checkboxPane) {
		this.sl_checkboxPane = sl_checkboxPane;
		this.checkboxPane = checkboxPane;
		// go through model, populate all the classes, if is instance of
		// decorator, put in map<String, List<String>>
		ArrayList<JCheckBox> decoratorList = new ArrayList<>();
		decoratorList.add(new JCheckBox("hi"));
		decoratorList.add(new JCheckBox("there"));
		decoratorList.add(new JCheckBox("my"));

		ArrayList<JCheckBox> adapterList = new ArrayList<>();
		decoratorList.add(new JCheckBox("hi"));
		adapterList.add(new JCheckBox("hohohoho"));
		testmap.put("Decorator", decoratorList);
		testmap.put("Adapter", adapterList);

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

	public void setNewImage() {
		path = "docs/M6/funny-panda-wallpaper.jpg";

	}

	private void positionCheckbox(JCheckBox box, int indent) {
		sl_checkboxPane.putConstraint(SpringLayout.NORTH, box, northIndex, SpringLayout.NORTH, checkboxPane);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, box, indent, SpringLayout.WEST, checkboxPane);
		box.setForeground(Color.GREEN);
		checkboxPane.add(box);
		northIndex += 20;
	}

	public int getLastBoxNorthPosition() {
		return northIndex;
	}
	public int getWestPosition(){
		return this.detectedPatternWestIndex;
	}

	public String getPath() {
		return path;
	}

	public JLabel getDigram() {

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
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

}
