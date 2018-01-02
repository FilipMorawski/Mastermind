package main;

import gameModeImpl.ComputerGuess;
import gameModeImpl.PlayerGuess;
import gui.GameOptionPanel;
import interfaces.GameMode;

/**
 * Create user interface, set all options, start and finish game. 
 *
 */
public class GameManager {

	private GameMode gameMode;
	private String name;
	private int availableMoves;

	public GameManager() {
		super();
	}

	public void startGame() {
		setOptions();

		gameMode.buildBoard();
		gameMode.gameInstructions();
		gameMode.setCode();

		do {
			gameMode.makeGuess();
		} while (!gameMode.checkFinishCondition());
		gameMode.finishGame();
	}

	private void setOptions() {
		GameOptionPanel optionPanel = new GameOptionPanel();
		name = optionPanel.getName();
		availableMoves = optionPanel.getAvailableMoves();
		String gameModeCode = optionPanel.getGameModeCode();
		if (gameModeCode.equals("1")) {
			gameMode = new PlayerGuess(name, availableMoves);
		} else {
			gameMode = new ComputerGuess(name, availableMoves);
		}
	}
}
