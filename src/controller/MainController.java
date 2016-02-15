package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import util.Config;
import view.View;


public class MainController implements ActionListener {
	private View theGui;
	//private Model model;
	private StartupMenuController startupMenuController;
	
	public MainController(View gui){
		System.out.println("Controller: adding view");
		this.theGui = gui;
		
		configView();
		
		startupMenuController = new StartupMenuController(this);
		theGui.showStartupMenu(startupMenuController);
		theGui.showIngameMenu(startupMenuController);
		theGui.showGame(startupMenuController);
		
	}
	
	private void configView () {
		theGui.addActionListener(this);
		theGui.addKeyListener(KeyEvent.VK_ESCAPE, "ESC Pressed");
	}
	
	
	/* Controller för view (vi får strängar):
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
		}*/
		if (e.getActionCommand().equals("ESC Pressed")) {
			System.out.println("Controller: ESC");
		}
		else if (e.getActionCommand().equals("Window closed")) {
			System.out.println("Controller: window closed");
			System.exit(0);
		}
		else {
			System.out.println(e); //debugging
		}
	}
}
