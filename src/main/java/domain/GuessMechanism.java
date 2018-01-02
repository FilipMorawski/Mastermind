package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import repositories.AvailableColours;
import repositories.GuessHistory;


/**
 * "The Mind" of game, responsible of preparing guess, analyzing history and scores 
 *	Program makes first move, which is now "base code" and switching its last index color (phase I).
 *	According to feedback answer, depending on number of hits, predicted colors and score Computer
 *	will change color in this index again or decide to switch index and memorize color on it.
 *	Mechanism firstly, tries to get minimum score of 24 (phase II)  so that all colors are predicted, but in wrong positions.
 *	If it can assure itself that some of indexes are correct well (in position and color) program lock that index.
 *	In second phase program just shuffle or rotate unlocked positions.
 */
public class GuessMechanism {

	private final int CODESIZE = 4;
	private AvailableColours availableColours;
	private GuessHistory guessHistory;
	private Guess lastGuess;
	private Guess recordHolder; /* guess with highest score, except last guess */
	private int moves; /* move counter */
	private List<Integer> availableIndexes; /* list of indexes, that can be switched by program in base code */
	private List<Integer> deadlockIndexes; /* program is sure, that colors on these indexes are predicted well */
	private List<String> coloursList;
	private int currentSwitchIndex; /* index of color code list, which program currently switching trying to broke code */
	private int scoreRecord;
	private int secondPhaseCounter; /* != 0 , when program entered 2nd phase before */
	private int roundCounter; /* Count, how many times program change color in current switch index */
	private boolean score24; /* defines the special case when score equals 24 */

	public List<String> makeFirstGuess(AvailableColours availableColours) {
		this.availableColours = availableColours;
		deadlockIndexes = new ArrayList<Integer>();
		availableIndexes = new ArrayList<Integer>();
		currentSwitchIndex = CODESIZE - 1;
		secondPhaseCounter = 0;
		scoreRecord = 0;
		for (int i = 0; i < CODESIZE; i++) {
			availableIndexes.add(i);
		}

		coloursList = new ArrayList<String>();
		for (int i = 0; i < CODESIZE - 1; i++) {
			int random = (int) (Math.random() * (availableColours.getList().size()));
			String colour = availableColours.getList().get(random);
			if (i < 1) {
				coloursList.add(colour);
				coloursList.add(colour);
			} else {
				coloursList.add(colour);
			}
		}
		return coloursList;
	}

//	detecting phase, and decide what type of move should make (phase one, phase two or score24 case
	public List<String> makeGuess(AvailableColours availableColours, GuessHistory guessHistory) {
		this.availableColours = availableColours;
		this.guessHistory = guessHistory;
		coloursList = new ArrayList<String>();
		moves = guessHistory.getGuessHistory().size();
		lastGuess = guessHistory.getLastGuess();
		int highestScore = guessHistory.getHighestScore().getScore();

		int lastGuessScore = lastGuess.getScore();
		if (moves == 1) {
			scoreRecord = lastGuessScore;
		}

		if (highestScore < 24) {
			if (lastGuessScore == 0) {
				restrictColours();
				coloursList = makeFirstGuess(availableColours);
			} else {
				coloursList = phaseOneMove();
				roundCounter++;
			}
		}

		if (highestScore >= 24) {
			if (secondPhaseCounter == 0) {
				compareLastMoves(recordHolder);
				restrictColours();
				analyzeLostDeadlocks();
				secondPhaseCounter++;
			}

			if (highestScore == 24) {
				score24 = true;
				changeAllPositions();
			}

			int size = lastGuess.getColorCode().size();
			calculateChangableSpots();
			boolean allHitsAreDeadLocked = ((guessHistory.getHighestScore().getCorrectHits()
					+ availableIndexes.size()) == size);
			boolean atLeastOneDeadlock = (deadlockIndexes.size() > 0);

			if (atLeastOneDeadlock && allHitsAreDeadLocked && !score24) {
				shuffleUnLockedPositions();
			} else if (!score24) {
				coloursList = phaseOneMove();
				roundCounter++;
			}

			if (score24) {
				changeAllPositions();
			}
			secondPhaseCounter++;
		}

		scoreRecord = guessHistory.getHighestScore().getScore();
		recordHolder = guessHistory.getHighestScore();
		return coloursList;
	}

// 	when program entering second phase when not all indexes was ever switch, so can't assure itself about some indexes
//	sometimes in special conditions, it can be predicted according to guess history and that indexes can be locked
	private void analyzeLostDeadlocks() {
		int highestGuessHits = guessHistory.getHighestScore().getCorrectHits();

		if (highestGuessHits > deadlockIndexes.size()) {
			int i = currentSwitchIndex + 1;
			int j = i + deadlockIndexes.size();
			boolean check = (j == highestGuessHits);
			if ((!deadlockIndexes.contains(currentSwitchIndex)) && check) {
				deadlockIndexes.add(currentSwitchIndex);
			}
		}
	}
	
//  update list of indexes which colors can be swapped
	private void calculateChangableSpots() {
		for (int i = availableIndexes.size() - 1; i >= 0; i--) {
			int index = availableIndexes.get(i);
			if (deadlockIndexes.contains(index)) {
				availableIndexes.remove(i);
			}
		}
	}
	
//  swap unlocked indexes
	private List<String> shuffleUnLockedPositions() {
		addCollection(lastGuess);

		List<String> coloursToRandomize = new ArrayList<String>();
		for (int i = 0; i < availableIndexes.size(); i++) {
			int colourIndex = availableIndexes.get(i);
			String colour = coloursList.get(colourIndex);
			coloursToRandomize.add(colour);
		}

		boolean everyPositionSwitched = true;
		do {
			Collections.rotate(coloursToRandomize, 1);
			for (int i = 0; i < coloursToRandomize.size(); i++) {
				int index = availableIndexes.get(i);
				if (coloursList.get(index).equals(coloursToRandomize.get(i))) {
					everyPositionSwitched = false;
				}
			}
		} while (!everyPositionSwitched);
		for (int i = 0; i < availableIndexes.size(); i++) {
			int indexToPut = availableIndexes.get(i);
			String colour = coloursToRandomize.get(i);
			coloursList.set(indexToPut, colour);
		}
		return coloursList;
	}

// in case of score 24 shuffle all positions
	private List<String> changeAllPositions() {
		addCollection(lastGuess);
		List<String> coloursToRandomize = new ArrayList<String>();
		for (String colour : coloursList) {
			coloursToRandomize.add(colour);
		}
		boolean everyPositionSwitched = true;
		do {
			Collections.shuffle(coloursToRandomize);
			everyPositionSwitched = true;
			for (int i = 0; i < coloursToRandomize.size(); i++) {
				if (coloursList.get(i).equals(coloursToRandomize.get(i))) {
					everyPositionSwitched = false;
				}
			}
		} while (!everyPositionSwitched);
		coloursList = coloursToRandomize;

		return coloursList;
	}
	
// mechanism of phase I moves
	private List<String> phaseOneMove() {
		if (moves < 2) {
			addCollection(lastGuess);
		} else {
			Guess secondLastGuess = guessHistory.getSeconLastGuess();
			coloursList = compareLastMoves(secondLastGuess);
		}
		boolean colourRecentlyUsed;
		String colour;
		do {
			if (roundCounter == availableColours.getList().size() + 2) {
				availableColours = new AvailableColours();
			}
			Collections.rotate(availableColours.getList(), 1);
			colour = availableColours.getList().get(0);
			colourRecentlyUsed = (colour == coloursList.get(currentSwitchIndex));
		} while (colourRecentlyUsed);
		coloursList.set(currentSwitchIndex, colour);
		return coloursList;
	}
	
// compare last move to move with highest score. Decide when swap color on current index again or move forward.
	private List<String> compareLastMoves(Guess secondLastGuess) {
		int lastGuessScore = lastGuess.getScore();
		int secondLastGuessScore = secondLastGuess.getScore();
		int lastGuessHits = lastGuess.getCorrectHits();
		int lastGuessPredictedColours = lastGuess.getPredictedColours();
		int highestGuessScore = guessHistory.getHighestScore().getScore();
		Guess highestGuess = guessHistory.getHighestScore();
		int highestGuessHits = highestGuess.getCorrectHits();
		int highestGuessPredictedColours = highestGuess.getPredictedColours();
		int recordHolderHits = recordHolder.getCorrectHits();
		int recordHolderPredictedColours = recordHolder.getPredictedColours();

		boolean notFirstMoves = ((lastGuessScore != 0) && (secondLastGuessScore != 0));

		if (notFirstMoves) {

			if (lastGuessScore > scoreRecord) {
				if (lastGuessHits > recordHolderHits) {
					lockColour();
					switchIndex();
					addCollection(lastGuess);
				}
				if (lastGuessPredictedColours > recordHolderPredictedColours ) {
					switchIndex();
					addCollection(lastGuess);
				}
			}

			if ((lastGuessScore < scoreRecord)) {

				if ((highestGuessScore - lastGuessScore) > 1) {
					removeColour(lastGuess.getColorCode().get(currentSwitchIndex));
				}
				if (highestGuessHits > lastGuessHits) {

					lockColour();
					switchIndex();
					addCollection(highestGuess);
				}
				if (highestGuessPredictedColours > lastGuessPredictedColours) {
					if (scoreRecord < 24) {
						switchIndex();
					}
					addCollection(highestGuess);
				}
			}

			if (lastGuessScore == scoreRecord) {
				if (moves >= 3) {
					removeColour(lastGuess.getColorCode().get(currentSwitchIndex));
				}
				addCollection(lastGuess);
			}

		} else {
			addCollection(lastGuess);
		}

		return coloursList;
	}

//	checking special conditions, when color can be removed from available list
	private void removeColour(String c) {
		List<String> coloursUsed = new ArrayList<String>();
		List<Guess> guessHistory2 = guessHistory.getGuessHistory();
		int size = guessHistory2.size();
		for (int i = 0; i < size - 2; i++) {
			Guess guess = guessHistory.getGuessHistory().get(i);
			for (String colour : guess.getColorCode()) {
				coloursUsed.add(colour);
			}
		}
		if (!coloursUsed.contains(c)) {
			availableColours.removeColour(c);
		}
	}

//  copy a base code color code to current move	
	private void addCollection(Guess guess) {
		coloursList = new ArrayList<String>();
		coloursList.addAll(guess.getColorCode());
	}

// 	switch index on which program operates in phase I
	private void switchIndex() {
		do {
			if (currentSwitchIndex != 0) {
				currentSwitchIndex--;
			} else {
				currentSwitchIndex = CODESIZE - 1;
			}
		} while (deadlockIndexes.contains(currentSwitchIndex));
		roundCounter = 0;
		Collections.shuffle(this.availableColours.getList());
	}

	private void lockColour() {
		deadlockIndexes.add(currentSwitchIndex);
	}

// 	last guess got 0 score or at least 24, change available colors list
	private void restrictColours() {
		boolean lastMoveScoresNull = (lastGuess.getScore() == 0);
		boolean lastMoveGuessAllColours = (lastGuess.getAnswer().size() == 4);

		List<String> lastMove = lastGuess.getColorCode();

		if (lastMoveScoresNull) {
			for (String colour : lastMove) {
				if (lastMove.contains(colour)) {
					availableColours.removeColour(colour);
				}
			}
		}

		if (lastMoveGuessAllColours) {
			List<String> list = availableColours.getList();
			list.removeAll(list);
			list.addAll(lastMove);
		}
	}
}
