
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		if (e.getActionCommand() == "Mouse pressed") {
			System.out.println("GameViewController: Mouse pressed");
			//startAccelerating();
		}
		else if (e.getActionCommand() == "Mouse released") {
			System.out.println("GameViewController: Mouse released");
			//stopAccelerating();
		}
		else if (e.getActionCommand() == "Mouse dragged") {
			System.out.println("GameViewController: Mouse dragged");
			//changeAccelerationDirection();
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
