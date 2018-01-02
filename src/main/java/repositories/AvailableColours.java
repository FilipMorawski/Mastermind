package repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * List of colors which can be used by Computer or Player for composing codes.
 * In "Computer Guess" mode colors can be removed from list.
 */
public class AvailableColours {

	private List<String> availableColours;

	public AvailableColours() {
		availableColours = new ArrayList<String>();

		availableColours.add("Green");
		availableColours.add("Red");
		availableColours.add("Purple");
		availableColours.add("Gray");
		availableColours.add("Blue");
		availableColours.add("Orange");
	}

	public void removeColour(String colour) {
		boolean containColour = availableColours.contains(colour);

		if (containColour) {
			availableColours.remove(colour);
		}
	}

	public boolean validateInput(String colour) {
		boolean containColour = availableColours.contains(colour);

		if (!containColour) {
			System.out.println("Wpisz poprawne oznaczenie! Zwróæ uwagê na wielkoœæ liter!");
			return false;
		}
		return true;
	}
	
	public boolean isColourAvailable(String colour) {
		boolean containColour = availableColours.contains(colour);

		if (!containColour) {
			return false;
		}
		return true;

	}

	public List<String> getList() {
		return availableColours;
	}
	
	
}
