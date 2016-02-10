package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.SpringLayout;
import javax.swing.JButton;

public class GUIResult extends JFrame {

	private Dimension screenSize;
	private Dimension boxSize;
	private Dimension borderSize;
	private Dimension diagramSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIResult window = new GUIResult();
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
	public GUIResult() {
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		borderSize = new Dimension((int) screenSize.getWidth() - 50, (int) screenSize.getHeight() - 50);
		boxSize = new Dimension((int) borderSize.getWidth() * 1 / 3, 1000);
		diagramSize = new Dimension((int) borderSize.getWidth() * 2 / 3, 1000);
		setupFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void setupFrame() {
		GUIPopulateDate populatedData = new GUIPopulateDate();
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

		JLabel populatedDiagram = populatedData.getDigram();
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
		checkboxPane.setPreferredSize(boxSize);

		JScrollPane checkboxScrollPane = new JScrollPane(checkboxPane);

		checkboxScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		checkboxScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		checkboxScrollPane.setPreferredSize(boxSize);
		checkboxScrollPane.setPreferredSize(boxSize);

		splitPane.setLeftComponent(checkboxScrollPane);
		SpringLayout sl_checkboxPane = new SpringLayout();
		checkboxPane.setLayout(sl_checkboxPane);
		populatedData.setupCheckbox(sl_checkboxPane, checkboxPane);
		
		JButton btnNewButton = new JButton("New button");
		sl_checkboxPane.putConstraint(SpringLayout.NORTH, btnNewButton, 937, SpringLayout.NORTH, menuBar);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, btnNewButton, -184, SpringLayout.EAST, checkboxPane);
		checkboxPane.add(btnNewButton);

	}
}
