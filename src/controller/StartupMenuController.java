package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.GameEvent;

/**
 * Handles events from the StartupMenu
 */
public class StartupMenuController implements ActionListener {
	private MainController parent;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public StartupMenuController(MainController parent){
		this.parent = parent;
	}
	
	/**
	 * Handles events coming from buttons on the startup menu
	 * @param e		The ActionEvent sent from a button press
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == GameEvent.START_NEW_GAME) {
			System.out.println("StartupMenuController: Start game");
			parent.startNewGame();
		}
		else if (e.getActionCommand() == GameEvent.LOAD_GAME) {
			System.out.println("StartupMenuController: Load game");
			parent.loadGame();
		}
		else if (e.getActionCommand() == GameEvent.EXIT) {
			System.out.println("StartupMenuController: Exit");
			parent.exit();
		}
		else {
			System.out.println("StartupMenuController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
