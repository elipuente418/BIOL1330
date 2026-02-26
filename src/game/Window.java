package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Window
 * @author petrnemecek
 *
 */

public class Window extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8804446439773037674L;

	private int graphicHeight = 250;
	private int graphicWidth = 640;

	private int gameWidth = 375;

	private int gameHeight = graphicHeight + 375;
	//original width: 375
	private int windowWidth = graphicWidth + gameWidth;

	// ELIJAH - changing height from 450 to accomodate "key"
	// original height: 450
	private int windowHeight = gameHeight;

	public Window(String title) {
		super(title); // Window title

		this.setLayout( new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close window to exit
		this.setSize(this.windowWidth, this.windowHeight);
		this.setLocationRelativeTo(null); // centering

		this.setResizable(false);
		this.setFocusable(true); // set focus on window so KeyListener works	

		getContentPane().add( new Grid() );
		
        JPanel panel = new JPanel();
		panel.add(createSimpleButton("NEW GAME"));
		
        panel.setPreferredSize(new Dimension(1000, 760));


		this.setVisible(true); // show window

	}

	private static JButton createSimpleButton(String text) {
		JButton button = new JButton(text);
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		return button;
	}

	public int getWidth() {
		return windowWidth;
	}

	public int getGameWidth() {return gameWidth;}

	public int getGameHeight() {return gameHeight;}

	public int getHeight() {
		return windowHeight;
	}

}