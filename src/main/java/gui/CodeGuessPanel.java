package gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JPanel;

import interfaces.ColorCode;

@SuppressWarnings("serial")
public class CodeGuessPanel extends JPanel {

	private Map<Integer,JPanel> panelList;
	private Box box;
	
	public CodeGuessPanel(int size) {
		panelList = new HashMap<Integer,JPanel>();
		box = Box.createVerticalBox();
		int i =0;
		while (i < size)  {
			String label = "Guess " + (i+1);
			CodePanel codePanel = new CodePanel();
			codePanel.setLabel(label);
			panelList.put(i+1, codePanel);
			box.add(codePanel);
			i++;
		}
		this.add(box);
	}

	public void paintColor(ColorCode guess, int moves) {
		CodePanel panel = (CodePanel) panelList.get(moves);
		panel.paintColors(guess);
	}

}
