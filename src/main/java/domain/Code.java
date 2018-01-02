package domain;

import java.util.List;

import interfaces.ColorCode;

/**
 * Keep code info for specific guess
 *
 */
public class Code implements ColorCode {

	private List<String> code;

	public List<String> getColorCode() {
		return code;
	}

	public void setCode(List<String> code) {
		this.code = code;
	}

	public List<String> getAnswer() {
		return null;
	}
	
	
}
