
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.Command;

/**
 * Handles events from the GameView
 */
public class GameViewController implements ActionListener {
	private MainController parent;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameViewController(MainController parent){
		this.parent = parent;
	}
	
	/**
	 * Handles events in the game
	 * @param e		The ActionEvent sent from a mouse press
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Command.MOUSE_PRESSED) {
			System.out.println("GameViewController: Mouse pressed");
			//startAccelerating();
		}
		else if (e.getActionCommand() == Command.MOUSE_RELEASED) {
			System.out.println("GameViewController: Mouse released");
			//stopAccelerating();
		}
		else if (e.getActionCommand() == Command.MOUSE_DRAGGED) {
			System.out.println("GameViewController: Mouse dragged");
			//changeAccelerationDirection();
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
