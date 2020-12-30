package swingpack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlGui extends JPanel {

	public int centerzoomlevel = 1;
	public boolean centerbool = true;
	public int xyzoomlevel = 1;
	public int maxzoomlevel = 3;
	public int minzioomlevel = 1;
	public int[][] pixellist; // Alle die mögliche Pixels
	public int[][] pixellisttmp; // Alle die mögliche Pixels
	public Color defaultcol; // Hintergrundfarbe
	private BufferedImage frontBuffer = null; // Bufferimage zum Zeigen der Pixels
	private BufferedImage backBuffer = null; // Bufferimage zum Schreiben der Pixels
	public boolean bufferbool = true;
	private final Object lock = new Object();  // Synchrnisation von Pixellist und Pixellisttmp
	public int mouseX;   // Mouse X Koordinate
	public int mouseY;   // Mouse Y Koordinate
	

	public void fillPixelListZero(int width, int height) { // Pixelsfüllen mit der Hintergrundfarbe

		synchronized (lock) {

			for (int x = 0; x < pixellist.length; x++) {

				for (int y = 0; y < pixellist[x].length; y++) {

					pixellist[x][y] = getIntRGB(defaultcol.getRed(), defaultcol.getGreen(), defaultcol.getBlue());

				}
			}

		}

		if (bufferbool == true) {

			backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			bufferbool = false;

		}

	}

	public void fillPixelListtmpZero(int width, int height) {

		synchronized (lock) {

			for (int x = 0; x < pixellisttmp.length; x++) {

				for (int y = 0; y < pixellisttmp[x].length; y++) {

					pixellisttmp[x][y] = getIntRGB(defaultcol.getRed(), defaultcol.getGreen(), defaultcol.getBlue());

				}
			}

		}

	}

	public void copyPixelsinPixellisttmp(int[][] pixellist, int[][] pixellisttmp) {   // Copying Pixels temporary from Pixellist in Pixellisttmp

		synchronized (lock) {

			if (pixellisttmp.length <= pixellist.length && pixellisttmp[0].length <= pixellist[0].length) {

				for (int i = 0; i < pixellisttmp.length; i++) {

					for (int i2 = 0; i2 < pixellisttmp[i].length; i2++) {

						pixellisttmp[i][i2] = pixellist[i][i2];

					}
				}

			} else {

				for (int i = 0; i < pixellist.length; i++) {

					for (int i2 = 0; i2 < pixellist[i].length; i2++) {

						pixellisttmp[i][i2] = pixellist[i][i2];

					}
				}

			}

		}
	}

	public void copyPixelsinPixellist(int[][] pixellist, int[][] pixellisttmp) {  // Copying Pixels back from Pixellisttmp in Pixellist

		synchronized (lock) {

			for (int i = 0; i < pixellist.length; i++) {

				for (int i2 = 0; i2 < pixellist[i].length; i2++) {

					pixellist[i][i2] = pixellisttmp[i][i2];

				}
			}

		}
	}

	public void clearGUI() {

		bufferbool = true;
		pixellist = new int[getWidth()][getHeight()];
		fillPixelListZero(getWidth(), getHeight());
		render();
		repaint();

	}

	public int getIntRGB(int red, int green, int blue) {   // Alle RGB-Werte in einem Integer zusammenfügen

		int val = 0;

		val |= red;
		val <<= 8; // 8 Bits nach links verschieben

		val |= green;
		val <<= 8; // 8 Bits nach links verschieben

		val |= blue;

		return val;
	}

	public void render() { // Schreiben der Pixels in der Pixelliste

		synchronized (lock) {

			for (int x = 0; x < pixellist.length; x++) {

				for (int y = 0; y < pixellist[x].length; y++) {

					backBuffer.setRGB(x, y, pixellist[x][y]);

				}
			}

		}

		if (frontBuffer == null) { // render was never called before
			frontBuffer = backBuffer;
		} // else: front buffer holds old frame
		else {
			// swap front with back buffer
			BufferedImage temp = frontBuffer;
			frontBuffer = backBuffer;
			backBuffer = temp;
		}

	}

	public String getPixel(int x, int y) { // Pixelwert erhalten als String von X,Y Koordinaten

		String rgb;

		int rgbcopy = 0;

		int red = 255;
		int green = 255;
		int blue = 255;

		synchronized (lock) {

			rgbcopy |= pixellist[x][y]; // erzeugung einer Kopie von einem Wert in der Pixelliste

		}

		blue &= rgbcopy;
		rgbcopy >>= 8; // 8 Bits nach links verschieben

		green &= rgbcopy;
		rgbcopy >>= 8; // 8 Bits nach links verschieben

		red &= rgbcopy;

		rgb = String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue); // Alle Farbestring
																								// zusammenfügen

		return rgb;
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();

		if (frontBuffer == null) {
			return;
		}
		
		drawJPanel(centerzoomlevel, frontBuffer, g2d,xyzoomlevel);

	}

	public void drawJPanel(int centerzoomlevel, BufferedImage frontBuffer, Graphics2D g2d,int xyzoomlevel) {

		double width = getWidth(); // Width des JPanels
		double height = getHeight(); // Height des JPanels
		double zoomFactor;
		double zoomWidth;
		double zoomHeight;
		double centerx;
		double centery;
		double x_position;
		double y_position;
		
		if(centerbool == true) {

		if (centerzoomlevel == 1) {

			g2d.drawImage(frontBuffer, 0, 0, frontBuffer.getWidth(), frontBuffer.getHeight(), null); // Bufferimage zeichnen
																									
			g2d.dispose(); // Alle Grafikobjekte werden freigeschaltet

		}  else if (centerzoomlevel == 2) {

			zoomFactor = 2;
			zoomWidth = width * zoomFactor;
			zoomHeight = height * zoomFactor;
			centerx = (width - zoomWidth) / 2; // Das Zentrum von x wird berechnet
			centery = (height - zoomHeight) / 2; // Das Zentrum von y wird berechnet

			AffineTransform at = new AffineTransform();
			at.translate(centerx, centery); // Move the origin to the center of the JPanel
			at.scale(zoomFactor, zoomFactor); // Um Faktor 2 vergrößern

			g2d.setTransform(at);
			g2d.drawImage(frontBuffer, 0, 0, frontBuffer.getWidth(), frontBuffer.getHeight(), null); // Bufferimage zeichnen																			 

			g2d.dispose(); // Alle Grafikobjekte werden freigeschaltet

		} else if (centerzoomlevel == 3) {

			zoomFactor = 3;
			zoomWidth = width * zoomFactor;
			zoomHeight = height * zoomFactor;
			centerx = (width - zoomWidth) / 2; // Das Zentrum von x wird berechnet
			centery = (height - zoomHeight) / 2; // Das Zentrum von y wird berechnet

			AffineTransform at = new AffineTransform();
			at.translate(centerx, centery); // Move the origin to the center of the JPanel
			at.scale(zoomFactor, zoomFactor); // Um Faktor 3 vergrößern

			g2d.setTransform(at);
			g2d.drawImage(frontBuffer, 0, 0, frontBuffer.getWidth(), frontBuffer.getHeight(), null); // Bufferimage zeichnen
																										

			g2d.dispose(); // Alle Grafikobjekte werden freigeschaltet

		}
		
		} else {
			
			if (xyzoomlevel == 1) {

				g2d.drawImage(frontBuffer, 0, 0, frontBuffer.getWidth(), frontBuffer.getHeight(), null); // Bufferimage zeichnen
																										
				g2d.dispose(); // Alle Grafikobjekte werden freigeschaltet

			} else if(xyzoomlevel == 2) {
				
				zoomFactor = 2;
				zoomWidth = mouseX * zoomFactor;
				zoomHeight = mouseY * zoomFactor;
				x_position = (mouseX - zoomWidth);  // Die Position von Mouse-Xkoordinate wird berechnet
				y_position = (mouseY - zoomHeight); // Die Position von Mouse-Ykoordinate wird berechnet

				AffineTransform at = new AffineTransform();
				at.translate(x_position, y_position);  // Move the origin to the center of the JPanel
				at.scale(zoomFactor, zoomFactor);     // Um Faktor 2 vergrößern

				g2d.setTransform(at);
				g2d.drawImage(frontBuffer, 0, 0, frontBuffer.getWidth(), frontBuffer.getHeight(), null); // Bufferimage zeichnen																			 

				g2d.dispose(); // Alle Grafikobjekte werden freigeschaltet
				
				
			} else if(xyzoomlevel == 3) {
				
				zoomFactor = 3;
				zoomWidth = mouseX * zoomFactor;
				zoomHeight = mouseY * zoomFactor;
				x_position = (mouseX - zoomWidth); // Die Position von Mouse-Xkoordinate wird berechnet
				y_position = (mouseY - zoomHeight); // Die Position von Mouse-Ykoordinate wird berechnet

				AffineTransform at = new AffineTransform();
				at.translate(x_position, y_position); // Move the origin to the center of the JPanel
				at.scale(zoomFactor, zoomFactor); // Um Faktor 3 vergrößern

				g2d.setTransform(at);
				g2d.drawImage(frontBuffer, 0, 0, frontBuffer.getWidth(), frontBuffer.getHeight(), null); // Bufferimage zeichnen																			 

				g2d.dispose(); // Alle Grafikobjekte werden freigeschaltet
				
			}
		}
	}

}
