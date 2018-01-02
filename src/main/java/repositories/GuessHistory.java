package repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Guess;

public class GuessHistory {
	
	private List<Guess> guessHistory;

	public GuessHistory() {
		super();
		this.guessHistory = new ArrayList<Guess>();
	}
	
	public void addGuess(Guess guess) {
		guessHistory.add(guess);
	}
		
	public List<Guess> getGuessHistory() {
		return guessHistory;
	}
	
	public Guess getLastGuess() {
		return guessHistory.get(guessHistory.size()-1);
	}
	
	public Guess getSeconLastGuess() {
		if (guessHistory.size() > 1) {
			return guessHistory.get(guessHistory.size()-2);
		} else {
			return getLastGuess();
		}
	}

	public Guess getHighestScore() {
		ArrayList<Guess> guessHistoryCopy = new ArrayList<Guess>(this.guessHistory);
		Collections.sort(guessHistoryCopy, new Guess());
		return guessHistoryCopy.get(0);
	}	
	
	@Override
	public String toString() {
		StringBuilder history = new StringBuilder();
		for (Guess guess : guessHistory) {
			history.append(guess.toString());
		}
		String historyString = history.toString();
		return historyString;
	}

}
