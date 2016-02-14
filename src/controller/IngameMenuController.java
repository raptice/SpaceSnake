
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.Command;

/**
 * Handles events from the IngameMenu
 */
public class IngameMenuController implements ActionListener {
	private MainController parent;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public IngameMenuController(MainController parent){
		this.parent = parent;
	}
	
	/**
	 * Handles events coming from buttons on the ingame menu
	 * @param e		The ActionEvent sent from a button press
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Command.RETURN_TO_GAME) {
			System.out.println("IngameMenuController: Resume game");
			parent.resumeGame();
		}
		else if (e.getActionCommand() == Command.SAVE_GAME) {
			System.out.println("IngameMenuController: Save game");
			parent.saveGame();
		}
		else if (e.getActionCommand() == Command.EXIT_GAME) {
			System.out.println("IngameMenuController: Exit game");
			parent.exitGame();
		}
		else {
			System.out.println("IngameMenuController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
