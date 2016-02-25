package util;

import java.awt.event.ActionEvent;

/**
 * Internal class making commands sent through events more readable and makes sure that they are unique.
 * @author Gustav
 */

/*
 * Comment
 * This should be remade as a subclass to ActionListener which is extensively used in this project. 
 * Maybe then renamed to GameAction?
 */

@SuppressWarnings("serial")
public class GameEvent extends ActionEvent{
	public static String
	ESC_PRESSED = "ESC pressed",
	WINDOW_CLOSED = "Window closed",
	RETURN_TO_GAME = "Return to game",
	SAVE_GAME = "Save game",
	EXIT_GAME = "Exit game",
	START_NEW_GAME = "Start new game",
	LOAD_GAME = "Load game",
	EXIT = "Exit",
	BACK_TO_MENU = "Back to menu",
	MOUSE_PRESSED = "Mouse pressed",
	MOUSE_RELEASED = "Mouse released",
	MOUSE_DRAGGED = "Mouse dragged",
	ZOOM_IN = "Zoom in",
	ZOOM_OUT = "Zoom out";

	/**
	 * A vector that is used for sendingposition with a mousseclick
	 */
	private Vector2D vector;
	
	/**
	 * Constructor that generates a normal ActionEvent
	 * @param who		The object triggering the event
	 * @param action	The action sent to the listeners
	 */
	public GameEvent(Object who, String action) {
		super(who, ActionEvent.ACTION_PERFORMED, action, System.currentTimeMillis(), 0);
	}
	
	
	/**
	 * Constructor that extends the ActionEvent with a vector
	 * @param who		The object triggering the event
	 * @param action	The action sent to the listeners
	 * @param vector	The vector that get sent along
	 */
	public GameEvent(Object who, String action, Vector2D vector) {
		super(who, ActionEvent.ACTION_PERFORMED, action, System.currentTimeMillis(), 0);
		this.vector = vector;
	}
	
	
	/**
	 * Returns the vector that was sent along. Returns null if no vector was specified.
	 * @return	The vector
	 */
	public Vector2D getVector() {
		return vector;
	}
	
}
/*public class Command {

	public static String
		ESC_PRESSED = "ESC pressed",
		WINDOW_CLOSED = "Window closed",
		RETURN_TO_GAME = "Return to game",
		SAVE_GAME = "Save game",
		EXIT_GAME = "Exit game",
		START_NEW_GAME = "Start new game",
		LOAD_GAME = "Load game",
		EXIT = "Exit",
		MOUSE_PRESSED = "Mouse pressed",
		MOUSE_RELEASED = "Mouse released",
		MOUSE_DRAGGED = "Mouse dragged",
		ZOOM_IN = "Zoom in",
		ZOOM_OUT = "Zoom out";
	
	//public Command () {}

}*/

/*public enum Command{
    
	ESC_PRESSED("ESC pressed"),
    WINDOW_CLOSED("Window closed"),
    		RETURN_TO_GAME("Return to game"),
    		SAVE_GAME("Save game"),
    		EXIT_GAME("Exit game"),
    		START_NEW_GAME("Start new game"),
    		LOAD_GAME ("Load game"),
    		EXIT ("Exit"),
    		MOUSE_PRESSED ("Mouse pressed"),
    		MOUSE_RELEASED ("Mouse released"),
    		MOUSE_DRAGGED ("Mouse dragged"),
    		ZOOM_IN ("Zoom in"),
    		ZOOM_OUT ("Zoom out");
    
    private String value;

    private Command(String value) {
        this.value = value;
    }
}
*/