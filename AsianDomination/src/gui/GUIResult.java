package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.SpringLayout;

import framework.IPhase;
import input.InputCommand;
import output.UMLDiagramOutputStream;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class GUIResult extends JFrame {

	private Dimension screenSize;
	private Dimension boxSize;
	private Dimension borderSize;
	private Dimension diagramSize;
	private JLabel populatedDiagram;
	private String imagePath;
	private GUIPopulateData populatedData;
	private List<String> filters;
	private InputCommand cmd;

	/**
	 * Create the application.
	 */
	public GUIResult(String imagePath, GUIPopulateData populatedData, InputCommand cmd) {
		this.populatedData = populatedData;
		this.cmd = cmd;
		filters = new ArrayList<String>();
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		borderSize = new Dimension((int) screenSize.getWidth() - 50, (int) screenSize.getHeight() - 50);
		boxSize = new Dimension((int) borderSize.getWidth() * 1 / 3, 1000);
		diagramSize = new Dimension((int) borderSize.getWidth() * 2 / 3, 1000);
		this.imagePath = imagePath;
		setupFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void setupFrame() {

		populatedData.setNewImage(imagePath);
		setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

		setMinimumSize(borderSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("help");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);
		getContentPane().setLayout(new GridBagLayout());
		JSplitPane splitPane = new JSplitPane();
		splitPane.setMinimumSize(borderSize);

		getContentPane().add(splitPane);

		JPanel diagramPic = new JPanel();

		populatedDiagram = populatedData.getDigram();
		populatedDiagram.setSize(this.diagramSize);
		diagramPic.add(populatedDiagram);

		JScrollPane diagramPane = new JScrollPane(diagramPic);
		diagramPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		diagramPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		diagramPane.setMinimumSize(diagramSize);
		diagramPane.setPreferredSize(diagramSize);
		splitPane.setRightComponent(diagramPane);

		JPanel checkboxPane = new JPanel();

		checkboxPane.setMinimumSize(boxSize);

		JScrollPane checkboxScrollPane = new JScrollPane(checkboxPane);

		checkboxScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		checkboxScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		checkboxScrollPane.setPreferredSize(boxSize);

		splitPane.setLeftComponent(checkboxScrollPane);
		SpringLayout sl_checkboxPane = new SpringLayout();
		checkboxPane.setLayout(sl_checkboxPane);
		populatedData.setupCheckbox(sl_checkboxPane, checkboxPane);

		JButton generateButton = new JButton("Generate");

		sl_checkboxPane.putConstraint(SpringLayout.NORTH, generateButton, populatedData.getLastBoxNorthPosition() + 10,
				SpringLayout.SOUTH, menuBar);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, generateButton, populatedData.getWestPosition(),
				SpringLayout.WEST, checkboxPane);
		checkboxPane.add(generateButton);
		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/// this is for generation second time
				// add the checked pattern.
				Map<String, ArrayList<JCheckBox>> map = populatedData.getAllCheckBoxes();
				filters = new ArrayList<String>();
				for (String k : map.keySet()) {
					for (JCheckBox box : map.get(k)) {
						if (box.isSelected()) {
							filters.add(box.getText());
						}
					}
				}

				IPhase phase = cmd.getPhase(UMLDiagramOutputStream.class);
				((UMLDiagramOutputStream) phase).setClassFilter(filters);
				phase.execute(cmd.getProjectModel());

				populatedData.setNewImage(imagePath);
				populatedDiagram = populatedData.getDigram();
				JScrollPane imagePanel = new JScrollPane(populatedDiagram);
				diagramPane.setViewportView(imagePanel);
				diagramPane.revalidate();
				diagramPane.repaint();

			}

		});

	}

}
