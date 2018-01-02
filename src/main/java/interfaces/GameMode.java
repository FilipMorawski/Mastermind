package interfaces;

public interface GameMode {
	
	void setCode();
	
	void buildBoard();
	
	void makeGuess();
		
	boolean checkFinishCondition();
	
	void finishGame();

	void gameInstructions();
}
