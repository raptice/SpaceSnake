package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;


public class MainController implements ActionListener {
	private GUI theGui;
	//private Model model;
	
	public MainController(){

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
	public void addView(GUI gui){
		System.out.println("Controller: adding view");
		this.theGui = gui;
	}
	
	/*
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	}*/
	
	public void startMenu(){
		theGui.showStartupMenu(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Start new game" /*"Button1"*/) {
			System.out.println("Controller: Button1");
		}
		else if (e.getActionCommand() == "Load game" /*"Command2"*/) {
			System.out.println("Controller: Button2");
		}
		else if (e.getActionCommand() == "Exit" /*"Command3"*/) {
			System.out.println("Controller: Button3");
		}
		else {
			System.out.println("Controller: Unknown button: " + e.paramString()); //debugging
		}
	}
}
