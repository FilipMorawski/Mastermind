package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.ColorCode;
import repositories.GuiColors;

@SuppressWarnings("serial")
public class MarkPanel extends JPanel {

	private Map<Integer, JPanel> panelList;
	private Box box;

	public MarkPanel(int size) {
		this.setLayout(new BorderLayout());
		panelList = new HashMap<Integer, JPanel>();
		JLabel label = new JLabel("Feedback");
		this.add(label, BorderLayout.NORTH);
		box = Box.createVerticalBox();
		int i = 0;
		while (i < size) {
			AnswerPanel answerPanel = new AnswerPanel();
			panelList.put(i + 1, answerPanel);
			box.add(answerPanel);
			i++;
		}
		setBackgrounds(GuiColors.getMARKCOLOR());
		this.add(box, BorderLayout.CENTER);
	}

	private void setBackgrounds(Color color) {
		this.setBackground(color);
		box.setBackground(color);
	}

	public void paintColor(ColorCode guess, int moves) {
		AnswerPanel answerPanel = (AnswerPanel) panelList.get(moves);
		answerPanel.paintColor(guess);
	}

}
