package swingpack;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class SubProcess {
	
public Process childProcess;
	
	public void createSubProcess(String path) throws IOException {

		File file = new File(path);

		ProcessBuilder processBuilder = new ProcessBuilder(file.getAbsolutePath());

		processBuilder.redirectErrorStream(true); // ErrorStreams werden mit InputStreams zum Parentprozess geschickt

		childProcess = processBuilder.start(); // Erzeugung des Child Prozesses

	}
	
	public void killSubProcess() throws InterruptedException {  // Kindprozess eliminieren
		
		childProcess.destroy();
		
		new Thread(new Runnable() {   // Kill das Prozess im neuen Thread um die anderen Funktionen nicht zu blockieren
			
			@Override
			public void run() {
				
				try {
					TimeUnit.SECONDS.sleep(3);     // Warten bis das Prozess aufgeräumt wird
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				childProcess.destroyForcibly();

				JOptionPane.showMessageDialog(null,"The Subprocess has been killed...","Message",1);
				
			}
		}).start();

		
	}

}
