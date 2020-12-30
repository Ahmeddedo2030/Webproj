package swingpack;

import java.io.IOException;
import javax.swing.JOptionPane;

public class readChildProcess {

	private ControlGui cg;
	private JFrameInitialisation initial;
	private InputOutputStream inout;
	private SubProcess sp;
	
	
	public readChildProcess(ControlGui cg, JFrameInitialisation initial, InputOutputStream inout, SubProcess sp) {

		this.cg = cg;
		this.initial = initial;
		this.inout = inout;
		this.sp = sp;

	}

	public void readChildProcessStream(String path) throws IOException, InterruptedException {

		sp.createSubProcess(path);

		inout.setInputStream(sp.childProcess.getInputStream()); // lesen die Inputstreams des Child-Prozesses

//		 while (sp.childProcess.isAlive()) {
//		
//		 System.out.println("aktiv");
//		
//		 }

		String line;

		while ((line = inout.br.readLine()) != null) {

			try {

				inout.getInputOutputStream(line, initial.frame);
			}

			catch (XYCoordinatesException e) {
				
				// X,y-Koordinaten werden überschritten

				JOptionPane.showMessageDialog(null,
						"Mögliche X oder Y Koordinate wurde die Größe ihres JPanels überschritten, bitte geben Sie nochmal die richtigen Koordinaten ein",
						"Warning", 2);

				sp.killSubProcess(); // Kindprozess eliminieren
				cg.fillPixelListZero(cg.getWidth(),cg.getHeight()); // Filling the Pixellist Array with the Background Color

				return;
				
			}

		}

			cg.render();
			cg.repaint();
		
	}

}
