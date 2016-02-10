package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.SpringLayout;

public class GUIResult {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIResult window = new GUIResult();
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
	public GUIResult() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

		Dimension borderSize = new Dimension((int) screenSize.getWidth()-50, (int) screenSize.getHeight()-50);
		frame.setMinimumSize(borderSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("help");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().setLayout(new GridBagLayout());
		JSplitPane splitPane = new JSplitPane();
		splitPane.setMinimumSize(borderSize);

		frame.getContentPane().add(splitPane);

		Dimension boxSize = new Dimension((int) borderSize.getWidth() * 1 / 3 , 1000);
		Dimension size = new Dimension((int) borderSize.getWidth() * 2 / 3, 1000);

		JPanel diagramPic = new JPanel();
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("docs/M6/funny-panda-wallpaper.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setSize(size);
		diagramPic.add(picLabel);
		
		
		JScrollPane diagramPane = new JScrollPane(diagramPic);
		diagramPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		diagramPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		diagramPane.setMinimumSize(size);
		diagramPane.setPreferredSize(size);
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

		JCheckBox Decorator = new JCheckBox("Decorator");
		sl_checkboxPane.putConstraint(SpringLayout.NORTH, Decorator, 10, SpringLayout.NORTH, checkboxPane);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, Decorator, 10, SpringLayout.WEST, checkboxPane);
		Decorator.setForeground(Color.GREEN);
		checkboxPane.add(Decorator);

		
		// main class at north 10 west 10
		// sub class north +=20, west = 50
		// north keep increment by 20
		JCheckBox classVisitor = new JCheckBox("org.objectweb.asm.ClassVisitor");
		classVisitor.setForeground(Color.GREEN);
		checkboxPane.add(classVisitor);
		
		
		JCheckBox classVisitor2 = new JCheckBox("org.objectweb.asm.ClassVisitor2");
		sl_checkboxPane.putConstraint(SpringLayout.NORTH, classVisitor, 30, SpringLayout.NORTH, checkboxPane);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, classVisitor, 50, SpringLayout.WEST, checkboxPane);
		sl_checkboxPane.putConstraint(SpringLayout.NORTH, classVisitor2, 50, SpringLayout.NORTH, checkboxPane);
		sl_checkboxPane.putConstraint(SpringLayout.WEST, classVisitor2, 50, SpringLayout.WEST, checkboxPane);
		classVisitor2.setForeground(Color.GREEN);
		checkboxPane.add(classVisitor2);
	}
}
