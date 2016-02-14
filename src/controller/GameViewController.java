
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Hanterar event från GameView
public class GameViewController implements ActionListener {
	private MainController parent;
	
	public GameViewController(MainController AL){
		parent = AL;
	}
	
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
