package swingpack;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JFrameInitialisation {

	private JMenuBar jmbar;
	public JFrame frame;
	private JFramListener jfactlis;

	public JFrameInitialisation(ControlGui cg,JFramListener jfactlis) {

		this.jfactlis = jfactlis;
		frame = new JFrame();
		frame.setSize(800, 600);
		createToolbar();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Drawing GUI");
		frame.setLocation(70, 70);
		frame.setResizable(true);
		frame.getContentPane().add(cg);
		frame.setVisible(true);
		frame.setJMenuBar(jmbar);
		cg.defaultcol = cg.getBackground();
		
	}

	protected void createToolbar() {

		JMenuItem one, two, three;
		JMenu zoomjmenu;
		JMenuItem clearbutton,choosebutton,killprocessbutton;
		JMenu option;

		jmbar = new JMenuBar();

		zoomjmenu = new JMenu("Zoom Level");
		option = new JMenu("Option");

		clearbutton = new JMenuItem("Clear");
		clearbutton.setOpaque(false);
		clearbutton.setContentAreaFilled(false);
		clearbutton.setBorderPainted(false);
		clearbutton.addActionListener(jfactlis);
		clearbutton.setActionCommand("clear");
		option.add(clearbutton);

		choosebutton = new JMenuItem("Choose compiled File");
		choosebutton.setOpaque(false);
		choosebutton.setContentAreaFilled(false);
		choosebutton.setBorderPainted(false);
		choosebutton.addActionListener(jfactlis);
		choosebutton.setActionCommand("choose");
		option.add(choosebutton);

		killprocessbutton = new JMenuItem("Kill Subprocess");
		killprocessbutton.setOpaque(false);
		killprocessbutton.setContentAreaFilled(false);
		killprocessbutton.setBorderPainted(false);
		killprocessbutton.addActionListener(jfactlis);
		killprocessbutton.setActionCommand("killsubprocess");
		option.add(killprocessbutton);

		one = new JMenuItem("1");
		one.addActionListener(jfactlis);
		one.setActionCommand("one");
		zoomjmenu.add(one);

		two = new JMenuItem("2");
		two.addActionListener(jfactlis);
		two.setActionCommand("two");
		zoomjmenu.add(two);

		three = new JMenuItem("3");
		three.addActionListener(jfactlis);
		three.setActionCommand("three");
		zoomjmenu.add(three);
		
		jmbar.add(zoomjmenu);
		jmbar.add(option);
		
		frame.addMouseMotionListener(jfactlis);

	}

}
