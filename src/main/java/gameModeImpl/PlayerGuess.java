package gameModeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import domain.Code;
import domain.Guess;
import domain.ScoreCalculator;
import gui.ColorPickerFrame;
import gui.GameBoard;
import interfaces.GameMode;
import repositories.AvailableColours;
import repositories.GuessHistory;

public class PlayerGuess implements GameMode {

	private String playerName;
	private AvailableColours inputValidator;
	private Code code;
	private Guess guess;
	private ScoreCalculator calculator;
	private int availableMoves;
	private int moves;
	private GuessHistory guessHistory;
	private GameBoard gameboard;
	private final int CODESIZE = 4;

	public PlayerGuess(String name, int availableMoves) {
		this.playerName = name;
		this.availableMoves = availableMoves;
		this.guessHistory = new GuessHistory();
		this.inputValidator = new AvailableColours();
	}

	public void setCode() {
		List<String> colourList = new ArrayList<String>();
		Random random = new Random();

		while (colourList.size() < CODESIZE) {
			int index = random.nextInt(inputValidator.getList().size());
			String colour = inputValidator.getList().get(index);
			colourList.add(colour);
		}
		this.code = new Code();
		this.code.setCode(colourList);
		this.calculator = new ScoreCalculator(this.code);
	}

	public void buildBoard() {
		this.gameboard = new GameBoard(this.availableMoves);
	}

	public void makeGuess() {
		List<String> colourList = new ArrayList<String>();

		ColorPickerFrame cf = new ColorPickerFrame();
		colourList = cf.getColoursList();

		guess = new Guess(colourList, this.code, this.calculator);
		guessHistory.addGuess(guess);
		moves++;
		gameboard.paintGuess(guess, moves);
		printHistory();
	}

	public boolean checkFinishCondition() {
		return ((guess.getScore() == 28) || (moves == availableMoves));
	}

	public void finishGame() {
		String title;
		String message;
		if (guess.getScore() == 28) {
			title = "You have won!";
			message = "You have won " + playerName + "! You guessed code in " + moves + "moves!";
		} else {
			title = "You have lose!";
			message = "You have lose, " + playerName + "! Look at the code!";
		}
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
		gameboard.paintCode(code);
	}

	public void gameInstructions() {
		String message = "Welcome in Mastermind game : \n"
				+ "Guess the code made from 4 colors, prepared by Computer \n" + "Colors symbols are :  \n"
				+ "Green,Purple,Gray,Yellow,Blue,Orange  \n" + "Guess by inputing color symbols in panel \n"
				+ "Computer will rate your guessing by this manner \n"
				+ "Black - You predicted color and its position \n"
				+ "Yellow - You predicted color, but not its position";
		String title = "Game instructions";
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	private void printHistory() {
		System.out.print(guessHistory.toString() + "Remaining moves - " + (availableMoves - moves) + "\n");
	}

}
