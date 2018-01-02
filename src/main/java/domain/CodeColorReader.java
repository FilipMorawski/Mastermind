package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Read codes written as String and convert them for jawa.awt.Color
 *
 */
public class CodeColorReader {

	public List<Color> read(List<String> colourList) {

		List<Color> readResult = new ArrayList<Color>();
		for (String colour : colourList) {
			if (colour.equals("Blue")) {
				readResult.add(Color.BLUE);
			}
			if (colour.equals("Red")) {
				readResult.add(Color.RED);
			}
			if (colour.equals("Gray")) {
				readResult.add(Color.GRAY);
			}
			if (colour.equals("Purple")) {
				readResult.add(Color.MAGENTA);
			}
			if (colour.equals("Orange")) {
				readResult.add(Color.ORANGE);
			}
			if (colour.equals("Green")) {
				readResult.add(Color.GREEN);
			}
			if (colour.equals("Yellow")) {
				readResult.add(Color.YELLOW);
			}
			if (colour.equals("Black")) {
				readResult.add(Color.BLACK);
			}
		}
		return readResult;
	}

}
