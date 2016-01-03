package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Control extends JPanel {
	
	Color color;

	/**
	 * Create the panel.
	 */
	public Control() {
		setOpaque(false);
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2= (Graphics2D)g;
		g2.setColor(color);
		g2.setStroke(new BasicStroke(2));
		super.paintComponent(g2);
		g2.drawOval((getWidth()-80)/2, (getHeight()-120)/2, 80, 120);
		g2.drawString("Control", (getWidth()-40)/2, getHeight()/2);
		
		
	}

	public static void main(String [] args){
		Control p = new Control();
		p.setColor(Color.yellow);
		JFrame app = new JFrame ();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500,500);
		app.setVisible(true);
		app.add(p);
	}

}
