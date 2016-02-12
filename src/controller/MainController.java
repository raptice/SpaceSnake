package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;


public class MainController implements ActionListener {
	private GUI theGui;
	//private Model model;
	private StartupMenuController startupMenuController;
	
	public MainController(GUI gui){
		System.out.println("Controller: adding view");
		this.theGui = gui;
		startupMenuController = new StartupMenuController(this);
		theGui.showStartupMenu(startupMenuController);
	}
	
	
	/* Controller för GUI (vi får strängar):
	 * 
	 * Vi ska starta startup menu när vi startar spelet (när den skapas)
	 *  - start new game
	 *  - load saved game
	 *  - exit Program
	 *  
	 * In Game:
	 * +escape button stops game & shows InGame menu
	 *   In Game Menu:
	 *   	 - save Game
	 *   	 - exit Game
	 *    	 - resume Game
	 *   MainWindow:
	 *    	ESC <-Dynamiskt(?)
	 *    	Close Window
	 *   Game:
	 * 		Mouse events:
	 * 			- Mouse Pressed
	 * 			- Mouse Released
	 * 			- Mouse Drag
	 * 	 Model:
	 * 		-GameOver
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	/*
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	}*/
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		
		/*if (e.getActionCommand() == "Start new game") {
			System.out.println("Controller: Button1");
		}
		else if (e.getActionCommand() == "Load game") {
			System.out.println("Controller: Button2");
		}
		else if (e.getActionCommand() == "Exit") {
			System.out.println("Controller: Button3");
			System.exit(0);
		}
		else {*/
			System.out.println("Hej"); //debugging
		//}
	}
}
