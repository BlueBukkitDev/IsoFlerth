package dev.blue.isoFlerth.experience;

public class Settings {
	private boolean cardinalMovement = false;
	
	public Settings() {
		
	}
	
	/**
	 *@param cardinal sets whether the movement style is cardinal (N/S, E/W) as opposed to natural (up/down, left/right)
	 **/
	public void setMovementStyle(boolean cardinal) {
		cardinalMovement = cardinal;
	}
	
	/**
	 *@return true if the movement style is cardinal (N/S, E/W) and false if it is natural (up/down, left/right)
	 **/
	public boolean getMovementStyle() {
		return cardinalMovement;
	}
}
