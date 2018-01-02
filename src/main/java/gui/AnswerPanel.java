package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import domain.CodeColorReader;
import interfaces.ColorCode;
import repositories.GuiColors;



/**
 * Class of JPanel with 4 buttons, able to get chosen color.
 * Used for presentation of answer codes.
 *
 */
@SuppressWarnings("serial")
public class AnswerPanel extends JPanel{

	private List<JButton> buttonList;
	private final int CODESIZE = 4;
	private final int BUTTONSIZE = 3;
	private JPanel upperAnswerPanel;
	private JPanel bottomAnswerPanel;
	private Box box;
	
	
	public AnswerPanel() {
		box = Box.createVerticalBox();		
		upperAnswerPanel = new JPanel();
		bottomAnswerPanel = new JPanel();
		
		buttonList = new ArrayList<JButton>();
		int i = 0;
		while(i < CODESIZE) {
			JButton button = new ColourButton(BUTTONSIZE);
			buttonList.add(button);
			if (i <2) {
				upperAnswerPanel.add(button);
			} else {
				bottomAnswerPanel.add(button);
			}
		i++;
		}
		box.add(upperAnswerPanel);
		box.add(bottomAnswerPanel);
		setBackgrounds(GuiColors.getMARKCOLOR());
		this.add(box, BorderLayout.CENTER);
	
	}


	private void setBackgrounds(Color color) {
		upperAnswerPanel.setBackground(color);
		bottomAnswerPanel.setBackground(color);
		this.setBackground(color);
	}


	public void paintColor(ColorCode guess) {
		List<String> colourList = guess.getAnswer();
		List<Color> codeColorList = new CodeColorReader().read(colourList);
		int i=0;
		while(i < colourList.size()) {
			JButton button = buttonList.get(i);
			Color color = codeColorList.get(i);
			button.setBackground(color);
			i++;
		}
	}
}
