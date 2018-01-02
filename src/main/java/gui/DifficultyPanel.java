package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class DifficultyPanel extends JPanel {

	private final String MESSAGE = "Select in how many moves You/Computer have to break a code";
	private final int DEFAULTLEVEL = 10;

	private JRadioButton button1, button2, button3;
	private int availableMoves;
	private JPanel labelPanel, radioPanel;
	private JLabel label;
	private ButtonGroup group;

	public DifficultyPanel() {
		this.setLayout(new BorderLayout());
		availableMoves = DEFAULTLEVEL;
		labelPanel = new JPanel();
		radioPanel = new JPanel();
		label = new JLabel(MESSAGE);
		labelPanel.add(label);

		DifficultyButtonListener radioListener = new DifficultyButtonListener();

		button1 = new JRadioButton("12");
		button1.setActionCommand("12");
		button1.addActionListener(radioListener);
		button2 = new JRadioButton("10");
		button2.setActionCommand("10");
		button2.addActionListener(radioListener);
		button3 = new JRadioButton("8");
		button3.setActionCommand("8");
		button3.addActionListener(radioListener);

		group = new ButtonGroup();
		group.add(button1);
		group.add(button2);
		group.add(button3);

		radioPanel.add(button1);
		radioPanel.add(button2);
		radioPanel.add(button3);

		this.add(labelPanel, BorderLayout.NORTH);
		this.add(radioPanel, BorderLayout.SOUTH);
	}

	private class DifficultyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String actionCommand = group.getSelection().getActionCommand();
			availableMoves = Integer.valueOf(actionCommand);
		}
	}

	public int getAvailableMoves() {
		return availableMoves;
	}

}
