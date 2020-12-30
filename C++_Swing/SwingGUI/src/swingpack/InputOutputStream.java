package swingpack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JFrame;

public class InputOutputStream {

	private ControlGui cg;
	private SubProcess sp;
	public BufferedReader br = null;
	public BufferedWriter bw = null;

	public InputOutputStream(ControlGui cg, SubProcess sp) {

		this.cg = cg;
		this.sp = sp;

	}

	public void setInputStream(InputStream inputStream) {
		this.br = new BufferedReader(new InputStreamReader(inputStream));
	}

	public void setOutputStream(OutputStream outputStream) {
		this.bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	}

	public void getInputOutputStream(String value, JFrame jf) throws IOException, XYCoordinatesException {

		setOutputStream(sp.childProcess.getOutputStream()); // Schicken das Stream zum Kindprozess

		if (value.length() == 12) { // Stringvalue enthält X,Y,R,G,B Werte in HEX

			int x = Integer.parseInt(value.substring(0, 3), 16); // x ist 12 Bits
			int y = Integer.parseInt(value.substring(3, 6), 16); // y ist 12 Bits
			int red = Integer.parseInt(value.substring(6, 8), 16); // red ist 8 Bits bis 255
			int green = Integer.parseInt(value.substring(8, 10), 16); // green ist 8 Bits bis 255
			int blue = Integer.parseInt(value.substring(10, 12), 16); // blue ist 8 Bits bis 255

			if (x > cg.getWidth() - 1 || y > cg.getHeight() - 1 || x < 0
					|| y < 0) {    /*
								    * Exception werfen, wenn x und/oder y Koordinaten überschritten werden
								    */

				throw new XYCoordinatesException();

			} else {

				cg.pixellist[x][y] = cg.getIntRGB(red, green, blue); // Alle RGB-Werte in einem Integer zusammenfügen

			}

		} else if (value.length() == 6) { // Stringvalue enthält nur X,Y-Werte in HEX

			int x = Integer.parseInt(value.substring(0, 3), 16);
			int y = Integer.parseInt(value.substring(3, 6), 16);

			if (x > cg.getWidth() - 1 || y > cg.getHeight() - 1) {

				throw new XYCoordinatesException();

			} else {

				bw.write(cg.getPixel(x, y) + "\n");
				bw.flush();

			}

		} else if (value.indexOf(',') != -1) { // Stringvalue enthält RGB-Werte R,G,B in Decimal

			System.out.println(value);

		} else if (value.equals("H")) { // Stringvalue enthält Height-Wert in Decimal

			bw.write(cg.getHeight() + "\n");
			bw.flush();

		} else if (value.equals("W")) { // Stringvalue enthält Width-Wert in Decimal

			bw.write(cg.getWidth() + "\n");
			bw.flush();

		}
	}

}

class XYCoordinatesException extends Exception { 
    
	private static final long serialVersionUID = 1L;

	public XYCoordinatesException() {
        
    }
}
