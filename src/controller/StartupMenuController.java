package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartupMenuController implements ActionListener {
	private MainController parent;
	
	public StartupMenuController(MainController AL){
		parent = AL;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Start new game") {
			System.out.println("Controller: Button1");
			//parent.startNewGame();
		}
		else if (e.getActionCommand() == "Load game") {
			System.out.println("Controller: Button2");
		}
		else if (e.getActionCommand() == "Exit") {
			System.out.println("Controller: Button3");
			System.exit(0);
		}
		else {
			System.out.println("Controller: Unknown button: " + e.paramString()); //debugging
		}
	}
}
