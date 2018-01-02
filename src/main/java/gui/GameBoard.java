package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import interfaces.ColorCode;
import repositories.GuiColors;

@SuppressWarnings("serial")
public class GameBoard extends JFrame{
	
	private MarkPanel markPanel;
	private CodeGuessPanel codeGuessPanel;
	private CodePanel codePanel;
	private int size;
	
	public GameBoard(int size) {
		this.size = size;
		createFrame();
	}

	private void createFrame() {
		this.setTitle("Mastermind");
		setSize();
		this.codePanel = new CodePanel();
		this.markPanel = new MarkPanel(size);
		this.codeGuessPanel = new CodeGuessPanel(size);
		setBackGrounds();
		
		this.add(codePanel, BorderLayout.SOUTH);
		this.add(codeGuessPanel, BorderLayout.CENTER);
		this.add(markPanel, BorderLayout.EAST);
		
		
		setLocation();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setLocation() {
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();
		
		int displayWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int displayHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		int xPos = (int) ((displayWidth/2) - (frameWidth*2.2));
		int yPos = (int) ((displayHeight/2) - (frameHeight*0.5));
		
		this.setLocation(xPos, yPos);
	}

	private void setBackGrounds() {
		codePanel.setBackground(GuiColors.getBGCOLOR());
		codeGuessPanel.setBackground(GuiColors.getBGCOLOR());
		markPanel.setBackground(GuiColors.getMARKCOLOR());
	}

	private void setSize() {
		int width = 210;
		if(size == 8 ) {
			this.setSize(width, 500);
		}
		if(size == 10 ) {
			this.setSize(width, 602);
		}
		if(size == 12 ) {
			this.setSize(width, 702);
		}
	}

	public void paintCode(ColorCode code) {
		codePanel.paintColors(code);
	}
	
	public void paintGuess(ColorCode guess, int moves) {
		codeGuessPanel.paintColor(guess,moves);
		markPanel.paintColor(guess, moves);
	}
	
}
