package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import model.PhysicsEngine;
import util.Config;
import util.Command;

import view.GameObserver;
import view.View;
import view.GameView;

/**
 * Creates view and model, handles events from the keyboard and implements actions from subcontrollers
 */
public class MainController implements ActionListener {
	private View view;
	private String state;
	
	private static final String STARTUP_MENU = "Startup Menu";
	private static final String INGAME_MENU = "Ingame Menu";
	private static final String GAME_VIEW = "Game View";
	
	private StartupMenuController startupMenuController;
	private IngameMenuController ingameMenuController;
	private GameController gameController;
	
	/**
	 * Constructor that adds a reference to the view, configures the view and adds
	 * subcontrollers and starts the startup menu
	 * 
	 * @param view	Reference to the view
	 */
	public MainController(View view){
		System.out.println("Controller: adding view");
		this.view = view;
		state = STARTUP_MENU;
		
		configView();
		
		startupMenuController = new StartupMenuController(this);
		ingameMenuController = new IngameMenuController(this);
		gameController = new GameController(this);
		
		view.showStartupMenu(startupMenuController);
	}
	
	/**
	 * Adds an ActionListener and a KeyListener to the view
	 */
	private void configView () {
		view.addActionListener(this);
		view.addKeyListener(KeyEvent.VK_ESCAPE, Command.ESC_PRESSED);
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
	 * 		-GameOver TODO: Add gameOver() method
	 * 
	 * */
	
	/* TODO: Add model and fix where to have the methods for GameController
	public void addModel(Model model){
		System.out.println("Controller: adding model");
		this.model = model;
	}*/
	public GameController getGameController(){
		return this.gameController;
	}
	/**
	 * Starts a new game
	 */
	public void startNewGame() {
		view.hideStartupMenu();			
		gameController.addObserver(view.showGame(gameController));
		gameController.addObserver(view.showMap());
		state = GAME_VIEW;
		gameController.runPhysics();
	}
		
	/**
	 * Loads the game
	 * TODO: Add method to load game
	 */
	public void loadGame() {
	}
	
	/**
	 * Exits the game
	 */
	public void exit() {
		System.exit(0);
	}
	
	/**
	 * Resumes the game
	 */
	public void resumeGame() {
		view.hideIngameMenu();
		view.showGame(gameController);
		state = GAME_VIEW;
	}
	
	/**
	 * Saves the game
	 * TODO: Add method to save game
	 */
	public void saveGame() {
	}
	
	/**
	 * Exits the game from the ingame menu and shows the startup menu
	 */
	public void exitMenu() {
		view.hideIngameMenu();
		view.showStartupMenu(startupMenuController);
		state = STARTUP_MENU;
	}
	
	public void exitGame() {
		view.hideGame();
		view.showIngameMenu(ingameMenuController);
		state = INGAME_MENU;
	}
	
	/**
	 * Handles events coming from the ESC button on the keyboard and from when the window is closed
	 * @param e		The ActionEvent sent from a button press or by closing the window
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(Command.ESC_PRESSED)) {
			System.out.println("MainController: ESC");
			switch (state) {
			case STARTUP_MENU: 
				break;
			case INGAME_MENU: resumeGame();
				break;
			case GAME_VIEW: exitGame();
				break;
			}
		}
		else if (e.getActionCommand() == Command.WINDOW_CLOSED) {
			System.out.println("MainController: Window closed");
			exit();
		}
		else {
			System.out.println(e); //debugging
		}
	}
}
