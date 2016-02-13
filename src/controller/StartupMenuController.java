package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupMenuController implements ActionListener {
	private MainController parent;
	
	public StartupMenuController(MainController AL){
		parent = AL;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "START_NEW_GAME") {
			System.out.println("StartupMenuController: Start game");
			parent.startNewGame();
		}
		else if (e.getActionCommand() == "LOAD_GAME") {
			System.out.println("StartupMenuController: Load game");
			parent.loadGame();
		}
		else if (e.getActionCommand() == "EXIT") {
			System.out.println("StartupMenuController: Exit");
			parent.exit();
		}
		else {
			System.out.println("StartupMenuController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
