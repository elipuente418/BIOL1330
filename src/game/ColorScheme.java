package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ColorScheme {

	public final static Color WINBG = new Color (0XFAF8EF);
	public final static Color GRIDBG = new Color (0XBBADA0);

	public final static Color BRIGHT = new Color (0X776E65);
	public final static Color LIGHT = new Color (0XF9F6F2);

	private HashMap<Integer, Color> background = new HashMap<>();

	private HashMap<Integer, BufferedImage> image = new HashMap<>();

	private BufferedImage exceptionImage;

	public ColorScheme() {
		initBackrounds();
		initImages();
	}

	private void initBackrounds() {
		background.put(0,		new Color (238, 228, 218, 90));
		background.put(2,		new Color (0XEEE4DA));
		background.put(4,		new Color (0XEDE0C8));
		background.put(8,		new Color (0XF2B179));
		background.put(16,		new Color (0XF59563));
		background.put(32,		new Color (0XF67C5F));
		background.put(64,		new Color (0XF65E3B));
		background.put(128,		new Color (0XEDCF72));
		background.put(256,		new Color (0XEDCC61));
		background.put(512,		new Color (0XEDC850));
		background.put(1024, 	new Color (0XEDC53F));
		background.put(2048, 	new Color (0XEDC22E));
	}

	private void initImages() {
		image.put(0,		null);
		image.put(2,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Spermicide.png"));
		image.put(4,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/fertility-awarenessBasedMethods.png"));
		image.put(8,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Withdrawl.png"));
		image.put(16,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/femaleCondom.png"));
		image.put(32,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/maleCondom.png"));
		image.put(64,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Ring.png"));
		image.put(128,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Patch.png"));
		image.put(256,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Pill.png"));
		image.put(512,		catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Injectable.png"));
		image.put(1024, 	catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/IUD.png"));
		image.put(2048, 	catchException("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/2048 Icons/Implant.png"));
	}

	private BufferedImage catchException(String file) {
		try {
			exceptionImage = ImageIO.read(new File(file));
			if (exceptionImage != null) {
				System.out.println("Image successfully loaded into BufferedImage.");
				// You can now use the bufferedImage object for further manipulation (e.g., get width, height, pixel data)
				System.out.println("Image dimensions: " + exceptionImage.getWidth() + "x" + exceptionImage.getHeight());
			} else {
				System.out.println("Could not read image. Check file path or format.");
			}
		} catch (IOException e) {
			// Handle the error here
			System.err.println("Error during I/O operation: " + e.getMessage());
			// You can log the exception or perform alternative actions
			e.printStackTrace(); // print the stack trace for debugging
		}
		return exceptionImage;
	}

//	private void drawImage(Graphics2D g) {
//		try {
//			graphicImage = ImageIO.read(new File("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/resizedGraphic.png"));
//			if (graphicImage != null) {
//				System.out.println("Image successfully loaded into BufferedImage.");
//				// You can now use the bufferedImage object for further manipulation (e.g., get width, height, pixel data)
//				System.out.println("Image dimensions: " + graphicImage.getWidth() + "x" + graphicImage.getHeight());
//			} else {
//				System.out.println("Could not read image. Check file path or format.");
//			}
//
//		} catch (IOException e) {
//			// Handle the error here
//			System.err.println("Error during I/O operation: " + e.getMessage());
//			// You can log the exception or perform alternative actions
//			e.printStackTrace(); // print the stack trace for debugging
//		}
////		BufferedImage graphicImage = ImageIO.read(new File("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/Graphic.png"));
//		g.drawImage(graphicImage, null, Game.WINDOW.getGameWidth(), 0);
//	}

	public Color getTileBackground(int value) {
		return background.get(value);
	}

	public Color getTileColor(int value) {
		if (value <= 8) {
			return BRIGHT;
		} else {
			return LIGHT;
		}
	}

	public BufferedImage getTileImage(int value) {
		return image.get(value);
		}

}
