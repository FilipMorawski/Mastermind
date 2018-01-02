package domain;

import java.util.List;

/**
 * Keep feedback information for specific guess.
 *
 */
public class Answer {

	private List<String> answer;

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "    " + answer + "\n";
	}
	
	
	
}
