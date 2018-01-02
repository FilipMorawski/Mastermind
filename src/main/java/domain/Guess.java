package domain;

import java.util.Comparator;
import java.util.List;

import interfaces.ColorCode;


/**
 * Represent any guess made by Computer or Player.
 * Keep info about feedback, score and code.
 *
 */
public class Guess implements Comparator<Guess>, ColorCode {

	private List<String> colorsGuessed;
	private int score;
	private Answer answer;
	private ScoreCalculator scoreCalculator;
	private int correctHits;
	private int predictedColours;

	public Guess() {}
	
	public Guess(List<String> colorsGuessed, Code code, ScoreCalculator scoreCalculator) {
		super();
		this.colorsGuessed = colorsGuessed;
		this.scoreCalculator = scoreCalculator;
		this.answer = setAnswer();
		this.score = setScore();
		this.correctHits = setHits();
		this.predictedColours = setPredictedColours();
	}

	private int setHits() {
		return scoreCalculator.getHits(this.colorsGuessed);
	}

	private int setScore() {
		return scoreCalculator.getScore(this.colorsGuessed);
	}

	private int setPredictedColours() {
		return scoreCalculator.getPredictedColours(this.colorsGuessed);
	}
	private Answer setAnswer() {
		return scoreCalculator.getAnswer(this.colorsGuessed);
	}

	public List<String> getColorCode() {
		return colorsGuessed;
	}

	public int getScore() {
		return score;
	}

	public int getCorrectHits() {
		return correctHits;
	}

	public int getPredictedColours() {
		return predictedColours;
	}

	public int compare(Guess o1, Guess o2) {
		return o2.getScore() - o1.getScore();
	}

	public void setCorrectHits(int i) {
		this.correctHits = i;
	}
	
	public List<String> getAnswer() {
		return answer.getAnswer();
	}

	@Override
	public String toString() {
		return this.colorsGuessed + this.answer.toString();
	}
}
