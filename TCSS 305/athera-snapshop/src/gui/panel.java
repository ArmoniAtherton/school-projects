import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int SMALL = 25;
	public static final int MEDIUM = 75;
	public static final int LARGE = 150;


	public MyPanel() {
		setBackground(Color.WHITE);
	}
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g2d = (Graphics2D) theGraphics;
		for(int i = 0; i <8; i +=3) {
			g2d.fill(new Ellipse2D.Double(i + SMALL, 0, SMALL, SMALL));
	}
		g2d.draw(new Rectangle2D.Double(SMALL, SMALL, LARGE, LARGE));
		g2d.fill(new Rectangle2D.Double(MEDIUM + SMALL, SMALL, MEDIUM, MEDIUM));
		g2d.fill(new Rectangle2D.Double(SMALL, MEDIUM + SMALL, MEDIUM, MEDIUM));
}
	public static void main(String theArgs) {
		  MyPanel Armoni = new MyPanel();
		  final JFrame frame = new JFrame("EllipseAndRectanglePanel Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(Armoni);
	        frame.setLocationByPlatform(true);
	        frame.pack();
	        frame.setVisible(true);

	}
}
