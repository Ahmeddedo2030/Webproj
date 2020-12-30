package swingpack;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {

	private static ControlGui cg;
	private static JFrameInitialisation in;
	private static JFramListener jfactlis;
	private static readChildProcess rchproc;

	public static void main(String[] args) {

		Start();

	}

	public static void Start() {

		cg = new ControlGui();
		SubProcess sp = new SubProcess();
		InputOutputStream inout = new InputOutputStream(cg, sp);
		jfactlis = new JFramListener(cg, sp);
		in = new JFrameInitialisation(cg, jfactlis);
		rchproc = new readChildProcess(cg, in, inout, sp);
		cg.pixellist = new int[cg.getWidth()][cg.getHeight()];
		cg.fillPixelListZero(cg.getWidth(), cg.getHeight());
	}

	public static void exec() throws IOException {

		cg.bufferbool = true;

		cg.pixellisttmp = new int[cg.getWidth()][cg.getHeight()];
		cg.fillPixelListtmpZero(cg.getWidth(), cg.getHeight()); // Filling the Pixellisttmp Array with the Background Color
																 
		cg.copyPixelsinPixellisttmp(cg.pixellist, cg.pixellisttmp); // Copying Pixels temporary from Pixellist in Pixellisttmp
																	
		cg.pixellist = new int[cg.getWidth()][cg.getHeight()];
		cg.fillPixelListZero(cg.getWidth(), cg.getHeight()); // Filling the Pixellist Array with the Background Color
		cg.copyPixelsinPixellist(cg.pixellist, cg.pixellisttmp); // Copying Pixels back from Pixellisttmp in Pixellist

		Thread t = new Thread(new Runnable() { // Lesen des Kindprozesses im neuen Thread

			@Override
			public void run() {

				try {

					rchproc.readChildProcessStream(jfactlis.path);

				} catch (IOException e) {

					JOptionPane.showMessageDialog(null, "Ungültige Datei wurde ausgewählt...", "Error", 0);

				} catch (NumberFormatException e) {

					JOptionPane.showMessageDialog(null, "Ungültige Datei wurde ausgewählt...", "Error", 0);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		t.start(); // Start des Threads
		t.interrupt(); 
						 /* Interrupt-Flag wird gesetzt um sicher zu stellen, dass die Ausführung des
						 * Threads ohne Block-Ursache erfolgreich beendet wird
						 */

	}

}
