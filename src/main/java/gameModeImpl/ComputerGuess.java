package gameModeImpl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Code;
import domain.Guess;
import domain.GuessMechanism;
import domain.HistoryChecker;
import domain.ScoreCalculator;
import gui.ColorPickerFrame;
import gui.GameBoard;
import interfaces.GameMode;
import repositories.AvailableColours;
import repositories.GuessHistory;

public class ComputerGuess implements GameMode {

	private GameBoard gameboard;
	private String playerName;
	private AvailableColours availableColours;
	private Code code;
	private Guess guess;
	private ScoreCalculator calculator;
	private int availableMoves;
	private int moves;
	private GuessHistory guessHistory;
	private GuessMechanism guessMechanism;
	private HistoryChecker historyChecker;

	public ComputerGuess(String name, int availableMoves) {
		this.moves = 0;
		this.playerName = name;
		this.availableMoves = availableMoves;
		this.guessHistory = new GuessHistory();
		this.availableColours = new AvailableColours();
		this.guessMechanism = new GuessMechanism();
		this.historyChecker = new HistoryChecker();
	}

	public void setCode() {
		List<String> colourList;
		this.code = new Code();

		ColorPickerFrame cf = new ColorPickerFrame();
		colourList = cf.getColoursList();

		code.setCode(colourList);
		this.calculator = new ScoreCalculator(this.code);
		this.gameboard.paintCode(code);
	}

	public void buildBoard() {
		this.gameboard = new GameBoard(this.availableMoves);
	}

	public void makeGuess() {
		List<String> colourList = new ArrayList<String>();
		do {
			if (moves == 0) {
				colourList = guessMechanism.makeFirstGuess(availableColours);
			} else {
				colourList = guessMechanism.makeGuess(availableColours, guessHistory);
			}
			guess = new Guess(colourList, code, calculator);
		} while (!historyChecker.check(guessHistory, guess));
		guessHistory.addGuess(guess);
		moves++;
//		printHistory();
		gameboard.paintGuess(guess, moves);
		String title = "Next guess";
		String message = "Click OK to continue";
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void finishGame() {
		String title;
		String message;
		if (guess.getScore() == 28) {
			title = "You lose!";
			message = "You lose " + playerName + "! Computer guessed your code in " + moves + " moves!";
		} else {
			title = "You won!";
			message = "You won " + playerName + "! Computer did not guess your code!";
		}
		JOptionPane.showMessageDialog(gameboard, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean checkFinishCondition() {
		return ((guess.getScore() == 28) || (moves == availableMoves));
	}

	public void gameInstructions() {
		String message = "Welcome in Mastermind game : \n"
				+ "Prepare the code made from 4 colors, computer will try to broke it \n" + "Colors symbols are :  \n"
				+ "Green,Purple,Gray,Yellow,Blue,Orange  \n" + "Computer moves will be rated by this manner \n"
				+ "Black - Computer predicted color and  its position \n"
				+ "Yellow - Computer predicted color, but not its position";
		String title = "Game instructions";
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	private void printHistory() {
		System.out.print(guessHistory.toString() + "Remaining moves - " + (availableMoves - moves) + "\n");
	}

}
