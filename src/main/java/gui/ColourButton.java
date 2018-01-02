package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ColourButton extends JButton {
	
	public ColourButton(int radius) {
		this.setBorder(new RoundBorder(radius));
		this.setBackground(Color.LIGHT_GRAY);
		this.setOpaque(false);
		this.setEnabled(false);
	}
	
	  protected void paintComponent(Graphics g) {
		    if (getModel().isArmed()) {
		      g.setColor(Color.BLACK);
		    } else {
		      g.setColor(getBackground());
		    }
		    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
		 
		    super.paintComponent(g);
		  }
	
	  protected void paintBorder(Graphics g) {
		    g.setColor(Color.BLACK);
		    g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
		  }
}
