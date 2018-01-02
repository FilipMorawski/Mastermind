package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Calculate all feedback data for guesses 
 *
 */
public class ScoreCalculator {
	
	private List<String> code;
	private Answer answer;
	private int score;
	private int correctHits;
	private int predictedColours;
	
	public ScoreCalculator(Code code) {
		super();
		this.code = code.getColorCode();
	}

	public int getHits(List<String> guess) {
		return this.correctHits;
	}
	
	
	public int getScore(List<String> guess) {
		return this.score;
	}

	public int getPredictedColours(List<String> colorsGuessed) {
		return this.predictedColours;
	}
	
//	prepare answer object for guesses as a list of "Black" and "Yellow" Strings
//	and calculating any specific data used by program
	public Answer getAnswer(List<String> guess) {
		score = 0;
		correctHits = 0;
		predictedColours = 0;
		answer = new Answer();
		ArrayList<String> answerList = new ArrayList<String>();	
		ArrayList<String> helpingList = new ArrayList<String>(this.code);
	
		for (int k=0; k<4; k++) {
			if (code.get(k).equals(guess.get(k))) {
				answerList.add("Black");
				score = score + 7;
				helpingList.set(k, null);
				correctHits++;
			}
		}
		
		for (int j=0; j<4; j++) {
			if (code.contains(guess.get(j)) && !(code.get(j).equals(guess.get(j))) && helpingList.contains(guess.get(j)) ){
				answerList.add("Yellow");
				score = score + 6;
				helpingList.remove(guess.get(j));
				predictedColours++;
			}
		}
		Collections.shuffle(answerList);
		answer.setAnswer(answerList);
		return answer;
	}
}
