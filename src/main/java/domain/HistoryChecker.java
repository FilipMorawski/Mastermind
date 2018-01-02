package domain;

import java.util.ArrayList;
import java.util.List;

import repositories.GuessHistory;


/**
 * Check if in history is saved same guess as processed
 *
 */
public class HistoryChecker {
	private List<String> currentGuessColoursList;
	private List<List<String>> coloursListHistory;
	
	public boolean check(GuessHistory guessHistory, Guess guess) {
		
		boolean checkResult = true;
		boolean isInHistory;
		
		this.currentGuessColoursList = guess.getColorCode();
		List<Guess> guessHistoryList = guessHistory.getGuessHistory();
		coloursListHistory = new ArrayList<List<String>>();
		for(Guess guessFromHistory : guessHistoryList) {
			 coloursListHistory.add(guessFromHistory.getColorCode()); 
		}
		

		for(Guess guessFromHistory : guessHistoryList) {
			isInHistory = currentGuessColoursList.equals(guessFromHistory.getColorCode());
			if (isInHistory) {checkResult = false;}
		}
		return checkResult;
	}
}
