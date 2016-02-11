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
		if (e.getActionCommand() == "Button1") {
			//different than the others because of different code in StartupMenu in the GUI package
			System.out.println("Controller: Button1");
		}
		else if (e.getActionCommand() == "Command2") {
			System.out.println("Controller: Button2");
		}
		else if (e.getActionCommand() == "Command3") {
			System.out.println("Controller: Button3");
		}
		else {
			System.out.println("Controller: Unknown button: " + e.paramString()); //debugging
		}
	}
}
