package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.CodeColorReader;
import interfaces.ColorCode;
import repositories.GuiColors;



@SuppressWarnings("serial")
public class CodePanel extends JPanel {

	private List<JButton> buttonList;
	private final int CODESIZE = 4;
	private final int BUTTONSIZE = 5;
	private JPanel labelPanel;
	private JPanel codePanel;

	
	public CodePanel() {
		this.setLayout(new BorderLayout());
		labelPanel = new JPanel();
		String label = "Code";
		labelPanel.add(new JLabel(label));
		this.add(labelPanel,BorderLayout.NORTH);
		
		codePanel = new JPanel();
		
		buttonList = new ArrayList<JButton>();
		int i = 0;
		while(i < CODESIZE) {
		JButton button = new ColourButton(BUTTONSIZE);
		buttonList.add(button);
		codePanel.add(button, BorderLayout.CENTER);
		i++;
		}
		setBackgrounds(GuiColors.getBGCOLOR());
		this.add(codePanel, BorderLayout.CENTER);
	}
	
	private void setBackgrounds(Color color) {
		labelPanel.setBackground(color);
		codePanel.setBackground(color);
	}

	public void paintColors(ColorCode code) {
		List<String> colourList = code.getColorCode();
		List<Color> codeColorList = new CodeColorReader().read(colourList);
		int i=0;
		while(i < CODESIZE) {
			JButton button = buttonList.get(i);
			Color color = codeColorList.get(i);
			button.setBackground(color);
			i++;
		}
	}

	public void setLabel(String label) {
		JLabel labelToChange = (JLabel) labelPanel.getComponent(0);
		labelToChange.setText(label);
	}

	
	
}
