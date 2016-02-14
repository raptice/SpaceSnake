package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import util.Config;
import view.View;


public class MainController implements ActionListener {
	private View gui;
	//private Model model;
	private StartupMenuController startupMenuController;
	private IngameMenuController ingameMenuController;
	private GameViewController gameViewController;
	
	public MainController(View gui){
		System.out.println("Controller: adding view");
		this.gui = gui;
		
		configView();
		
		startupMenuController = new StartupMenuController(this);
		ingameMenuController = new IngameMenuController(this);
		gameViewController = new GameViewController(this);
		
		gui.showStartupMenu(startupMenuController);
	}
	
	private void configView () {
		gui.addActionListener(this);
		gui.addKeyListener(KeyEvent.VK_ESCAPE, "ESC Pressed");
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
	
	
		public void startNewGame() {
			gui.hideStartupMenu();
			gui.showGame(gameViewController);
		}
		
		//TODO: Add method to load game
		public void loadGame() {
		}
		
		public void exit() {
			System.exit(0);
		}
	
	public void resumeGame() {
		gui.hideIngameMenu();
		gui.showGame(gameViewController);
	}
	
	//TODO: Add method to save game
	public void saveGame() {
	}
	
	public void exitGame() {
		gui.hideIngameMenu();
		gui.showStartupMenu(startupMenuController);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("ESC Pressed")) {
			System.out.println("MainController: ESC");
			gui.hideGame();
			gui.showIngameMenu(ingameMenuController);
		}
		else if (e.getActionCommand().equals("Window closed")) {
			System.out.println("MainController: Window closed");
			exit();
		}
		else {
			System.out.println(e); //debugging
		}
	}
}
