package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.GameEvent;

/**
 * Handles events from the GameOverMenu
 */
public class GameOverMenuController implements ActionListener {
	private MainController parent;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameOverMenuController(MainController parent){
		this.parent = parent;
	}
	
	/**
	 * Handles events coming from buttons on the startup menu
	 * @param e		The ActionEvent sent from a button press
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == GameEvent.BACK_TO_MENU) {
			parent.exitMenu();
		}
		else {
			System.out.println("StartupMenuController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
