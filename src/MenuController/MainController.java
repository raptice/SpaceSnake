package MenuController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;


public class MainController implements ActionListener {
	private GUI theGui;
	
	public MainController(){
		this.theGui = new GUI(this);

		theGui.showStartupMenu(this);
		theGui.showGame(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Controller ActionEvent");
	}
}
