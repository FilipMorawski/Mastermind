package gui;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOptionPanel extends JOptionPane {

	private JPanel mainPanel;
	NamePanel namePanel;
	DifficultyPanel difficultyPanel;
	GameModePanel gameModePanel;

	public GameOptionPanel() {
		mainPanel = new JPanel();
		namePanel = new NamePanel();
		gameModePanel = new GameModePanel();
		difficultyPanel = new DifficultyPanel();

		Box box = Box.createVerticalBox();
		box.add(namePanel);
		box.add(difficultyPanel);
		box.add(gameModePanel);

		mainPanel.add(box);

		JOptionPane.showMessageDialog(null, mainPanel, "Choose Game options", JOptionPane.QUESTION_MESSAGE);

	}

	public int getAvailableMoves() {
		return difficultyPanel.getAvailableMoves();
	}

	public String getName() {
		return namePanel.getName();
	}

	public String getGameModeCode() {
		return gameModePanel.getGameModeCode();
	}

}
