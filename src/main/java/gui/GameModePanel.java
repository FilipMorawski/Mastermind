package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class GameModePanel extends JPanel {

	private final String MESSAGE = "Choose game mode";
	private final String DEFAULTGAMEMMODECODE = "1";
	private JPanel labelPanel, radioPanel;
	private JLabel label;
	private JRadioButton button1;
	private JRadioButton button2;
	private ButtonGroup group;
	private String gameModeCode;

	public GameModePanel() {
		this.setLayout(new BorderLayout());
		labelPanel = new JPanel();
		radioPanel = new JPanel();
		gameModeCode = DEFAULTGAMEMMODECODE;
		label = new JLabel(MESSAGE);
		labelPanel.add(label);

		ModeButtonListener radioListener = new ModeButtonListener();

		button1 = new JRadioButton("Player guess");
		button1.setActionCommand("1");
		button1.addActionListener(radioListener);
		button2 = new JRadioButton("Computer guess");
		button2.setActionCommand("2");
		button2.addActionListener(radioListener);
		group = new ButtonGroup();
		group.add(button1);
		group.add(button2);

		radioPanel.add(button1);
		radioPanel.add(button2);

		this.add(labelPanel, BorderLayout.NORTH);
		this.add(radioPanel, BorderLayout.SOUTH);
	}

	private class ModeButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			gameModeCode = group.getSelection().getActionCommand();
		}
	}

	public String getGameModeCode() {
		return gameModeCode;
	}

}
