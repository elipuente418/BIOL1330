package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Grid extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int TILE_RADIUS = 15;
	private static final int WIN_MARGIN = 20;
	private static final int TILE_SIZE = 65;
	private static final int TILE_MARGIN = 15;
	private static final String FONT = "Tahoma";
	private BufferedImage graphicImage;

	// adding graphic image to the bottom of the screen

	private void drawImage(Graphics2D g) {
		try {
			graphicImage = ImageIO.read(getClass().getResource("/game/graphicKey.png"));
			if (graphicImage != null) {
				System.out.println("Image successfully loaded into BufferedImage.");
				// You can now use the bufferedImage object for further manipulation (e.g., get width, height, pixel data)
				System.out.println("Image dimensions: " + graphicImage.getWidth() + "x" + graphicImage.getHeight());
			} else {
				System.out.println("Could not read image. Check file path or format.");
			}

		} catch (IOException e) {
			// Handle the error here
			System.err.println("Error during I/O operation: " + e.getMessage());
			// You can log the exception or perform alternative actions
			e.printStackTrace(); // print the stack trace for debugging
		}
//		BufferedImage graphicImage = ImageIO.read(new File("C:/Users/epuen/OneDrive/Desktop/BIOL1330/2048/2048-JAVA/src/game/Graphic.png"));
		g.drawImage(graphicImage, null, Game.WINDOW.getGameWidth(), 0);
	}

	public Grid() {
		super(true); // turn on doublebuffering
	}

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);

		Graphics2D g = ((Graphics2D) g2); // cast to get context for drawing

		/* turn on antialiasing for smooth and non-pixelated edges */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		drawBackground(g);
		drawTitle(g);
		drawScoreBoard(g);
		drawBoard(g);
		drawImage(g);

		g.dispose(); // release memory
	}

	private static void drawTitle(Graphics g) {
		g.setFont( new Font(FONT, Font.BOLD, 38) );
		g.setColor( ColorScheme.BRIGHT );
		g.drawString("2048", WIN_MARGIN, 50);
	}

	private void drawScoreBoard(Graphics2D g) {
		int width = 80;
		int height = 40;
		int xOffset = Game.WINDOW.getWidth() - WIN_MARGIN - width;
		int yOffset = 20;
		g.fillRoundRect(xOffset, yOffset, width, height, TILE_RADIUS, TILE_RADIUS);
		g.setFont( new Font(FONT, Font.BOLD, 10) );
		g.setColor( new Color(0XFFFFFF) );
		g.drawString("SCORE", xOffset + 22, yOffset + 15);
		g.setFont( new Font(FONT, Font.BOLD, 12) );
		g.drawString(String.valueOf(Game.BOARD.getScore()), xOffset + 35, yOffset + 30);
	}

	private static void drawBackground(Graphics g) {
		g.setColor(ColorScheme.WINBG);
		g.fillRect(0, 0, Game.WINDOW.getWidth(), Game.WINDOW.getHeight());		
	}

	private static void drawBoard(Graphics2D g) {
		g.translate(WIN_MARGIN, 80);
		g.setColor(ColorScheme.GRIDBG);
		g.fillRoundRect(0, 0, Game.WINDOW.getGameWidth() - (WIN_MARGIN * 2), 320 + TILE_MARGIN, TILE_RADIUS, TILE_RADIUS);

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				drawTile(g, Game.BOARD.getTileAt(row, col), col, row);
			}
		}
	}

	private static void drawTile(Graphics2D g, Tile tile, int x, int y) {
		int value = tile.getValue();
		int xOffset = x * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
		int yOffset = y * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
		g.setColor(Game.COLORS.getTileBackground(value));
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, TILE_RADIUS, TILE_RADIUS);

		g.setColor(Game.COLORS.getTileColor(value));
		g.drawImage(Game.COLORS.getTileImage(value), null, xOffset, yOffset);

		final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
		final Font font = new Font(FONT, Font.BOLD, size);
		g.setFont(font);

//		String s = String.valueOf(value);
//		final FontMetrics fm = g.getFontMetrics(font);

//		final int w = fm.stringWidth(s);
//		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
//
//		if (value != 0) {
//			Game.BOARD.getTileAt(y, x).setPosition(y, x); // tile gets its new position
//			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
//		}
		
		

		if (Game.BOARD.getWonOrLost() != null && !Game.BOARD.getWonOrLost().isEmpty()) {
			g.setColor(new Color(255, 255, 255, 40));
			g.fillRect(0, 0, Game.WINDOW.getGameWidth(), Game.WINDOW.getGameHeight());
			g.setColor(ColorScheme.BRIGHT);
			g.setFont(new Font(FONT, Font.BOLD, 30));
			g.drawString("You " + Game.BOARD.getWonOrLost() + "!", 68, 150);
			Game.CONTROLS.unbind();
		}


	}

}
