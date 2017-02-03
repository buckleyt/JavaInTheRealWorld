import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Grapher extends JPanel {

	private Equation e;

	public Grapher(String str) {
		e = new Equation(str);
	}

	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		Graphics2D g = (Graphics2D) G;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.WHITE);
		g.fillRect(2, 2, 296, 296);
		g.fillRect(2, 302, 296, 296);
		g.fillRect(302, 2, 296, 296);
		g.fillRect(302, 302, 296, 296);
		g.setColor(Color.BLACK);
		// Marks the scale of the x axis
		for (int i = 0; i < 24; i++) {
			g.fillRect(i * 25 - 2, 296, 4, 8);
			if (i != 12) {
				g.drawString("" + (i - 12), i * 25 - 5, 320);
			}
		}
		// Marks the scale of the y axis
		for (int i = 0; i < 24; i++) {
			g.fillRect(296, i * 25 - 2, 8, 4);
			if (i != 12 && i != 13 && i != 0) {
				g.drawString("" + (-1)*(i - 12), 320, i * 25 + 5);
			}
		}
		
		
		g.setColor(new Color(0, 0, 123));
		g.fillRect(0, 600, 600, 400);
		g.fillRect(600, 0, 200, 1000);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans", Font.BOLD, 30));
		g.drawString("f(x) = " + e.toString(), 10, 630);
		g.setColor(Color.RED);
		g.drawString("Original Function in Red", 10, 670);
		g.setColor(Color.GREEN);
		g.drawString("First Derivative in Green", 10, 710);
		g.setColor(Color.YELLOW);
		g.drawString("Integral (in Yellow) from 0 to 3 = " + integrate(0, 3, g), 10, 750);
		graphEquation(g);
		graphDerivative(g);
		
	}

	public void graphEquation(Graphics2D g) {
		g.setColor(Color.RED);
		for (double i = -12; i <= 12; i += .01) {
			drawPoint(i, e.solve(i), g);
		}
	}
	
	public void graphDerivative(Graphics2D g) {
		g.setColor(Color.GREEN);
		for (double i = -12; i <= 12; i += .01){
			drawPoint(i, slopeAt(i), g);
		}
	}
	
	public double integrate(double startX, double endX, Graphics2D g) {
		if (startX > endX) {
			return -integrate(endX, startX, g);
		}
		double dx = .0001;
		double area = 0;
		for (double i = startX; i < endX-dx; i += dx) {
			area += e.solve(i) * dx;
			int x1 = (int) (300 + (i * 25));
			int y1 = (int) (300 - (e.solve(i) * 25));
			if (g!= null) {
				g.setColor(Color.YELLOW);
				g.fillRect(x1, y1, 1, (int) (e.solve(i) * 25));
			}
		}
		area = (int) (area * 10000.0 + .5) / 10000.0;
		return area;
	}
	
	public double slopeAt(double d) {
		double deltaY = e.solve(d + 0.0001) - e.solve(d);
		return deltaY/0.0001;
	}
	
	public void drawPoint(double x, double y, Graphics2D g) {
		g.fillOval((int) (x * 25 + 296), (int) (-y * 25 + 298), 4, 4);
	}

}
