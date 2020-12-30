package swingpack;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFramListener implements java.awt.event.ActionListener, MouseMotionListener {

	private ControlGui cg;
	private SubProcess sp;
	public String path;

	public JFramListener(ControlGui cg, SubProcess sp) {

		this.cg = cg;
		this.sp = sp;

		cg.getInputMap().put(KeyStroke.getKeyStroke('+'), "Zoomin");

		cg.getActionMap().put("Zoomin", new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(cg.xyzoomlevel < cg.maxzoomlevel) {
				
				cg.xyzoomlevel++;
				cg.centerbool = false;
				cg.repaint();
				
				}
				
			}
		});
		
		cg.getInputMap().put(KeyStroke.getKeyStroke('-'), "Zoomout");

		cg.getActionMap().put("Zoomout", new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				if(cg.xyzoomlevel > cg.minzioomlevel) {
					
					cg.xyzoomlevel--;
					cg.centerbool = false;
					cg.repaint();
					
					}

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) { // Warten eine Aktion von dem Nutzer

		String command = e.getActionCommand();

		if (command.equals("one")) { // Zoomlevel auswählen

			cg.centerzoomlevel = 1;
			cg.xyzoomlevel = 1;
			cg.centerbool = true;
			cg.repaint();

		} else if (command.equals("two")) { // Zoomlevel auswählen

			cg.centerzoomlevel = 2;
			cg.xyzoomlevel = 2;
			cg.centerbool = true;
			cg.repaint();

		} else if (command.equals("three")) { // Zoomlevel auswählen

			cg.centerzoomlevel = 3;
			cg.xyzoomlevel = 3;
			cg.centerbool = true;
			cg.repaint();

		} else if (command.equals("clear")) { // Zeichnung löschen

			cg.clearGUI();

		} else if (command.equals("choose")) { // Eine kompilierte Datei auswählen

			JFileChooser jfc = new JFileChooser("E:\\Die Unifächer\\Semester 7\\Projektarbeit");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("EXE Files", "EXE",
					"exe"); /*
							 * Nur EXE-Dateien können ausgewählt werden
							 */

			jfc.setFileFilter(filter);
			jfc.setAcceptAllFileFilterUsed(false);
			int returnValue = jfc.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {

				File selectedFile = jfc.getSelectedFile();

				path = selectedFile.getAbsolutePath();

				try {

					Main.exec();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (command.equals("killsubprocess")) { // Kindprozess eliminieren

			try {

				sp.killSubProcess();

			}

			catch (NullPointerException e1) {

			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		cg.mouseX = e.getX();
		cg.mouseY = e.getY();

	}

}
