
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.GameEvent;

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
		if (e.getActionCommand() == GameEvent.RETURN_TO_GAME) {
			System.out.println("IngameMenuController: Resume game");
			parent.resumeGame();
		}
		else if (e.getActionCommand() == GameEvent.SAVE_GAME) {
			System.out.println("IngameMenuController: Save game");
			parent.saveGame();
		}
		else if (e.getActionCommand() == GameEvent.EXIT_GAME) {
			System.out.println("IngameMenuController: Exit game");
			parent.exitMenu();
		}
		else {
			System.out.println("IngameMenuController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
