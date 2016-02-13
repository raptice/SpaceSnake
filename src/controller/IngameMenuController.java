
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngameMenuController implements ActionListener {
	private MainController parent;
	
	public IngameMenuController(MainController AL){
		parent = AL;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "RETURN_TO_GAME") {
			System.out.println("IngameMenuController: Resume game");
			parent.resumeGame();
		}
		else if (e.getActionCommand() == "SAVE_GAME") {
			System.out.println("IngameMenuController: Save game");
			parent.saveGame();
		}
		else if (e.getActionCommand() == "EXIT_GAME") {
			System.out.println("IngameMenuController: Exit game");
			parent.exitGame();
		}
		else {
			System.out.println("IngameMenuController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
