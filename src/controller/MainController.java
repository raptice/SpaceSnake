package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import util.GameEvent;

import view.View;

/**
 * Creates view and model, handles events from the keyboard and implements actions from subcontrollers
 */
public class MainController implements ActionListener {
	private View view;
	private String state;
	
	private static final String STARTUP_MENU = "Startup Menu";
	private static final String INGAME_MENU = "Ingame Menu";
	private static final String GAME_VIEW = "Game View";
	private static final String GAME_OVER = "Game Over";
	
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
		view.addKeyListener(KeyEvent.VK_ESCAPE, GameEvent.ESC_PRESSED);
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
		gameController.newGame();
		view.hideStartupMenu();			
		gameController.addObserver(view.showNewGame(gameController));
		gameController.addObserver(view.showNewMap());
		gameController.runPhysics();
		state = GAME_VIEW;
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
		gameController.resumePhysics();
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
		gameController.stopPhysics();
		view.hideIngameMenu();
		view.showStartupMenu(startupMenuController);
		state = STARTUP_MENU;
	}
	/**
	 * Puases, and exits, a running game and shows the in game menu
	 */
	public void exitGame() {
		gameController.pausePhysics();
		view.hideGame();
		view.showIngameMenu(ingameMenuController);
		state = INGAME_MENU;
	}
	
	public void setGameOver() {
		state = GAME_OVER;
	}
	
	/**
	 * Handles events coming from the ESC button on the keyboard and from when the window is closed
	 * @param e		The ActionEvent sent from a button press or by closing the window
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(GameEvent.ESC_PRESSED)) {
			System.out.println("MainController: ESC");
			switch (state) {
			case STARTUP_MENU: 
				break;
			case INGAME_MENU: resumeGame();
				break;
			case GAME_VIEW: exitGame();
				break;
			case GAME_OVER:
			}
		}
		else if (e.getActionCommand() == GameEvent.WINDOW_CLOSED) {
			System.out.println("MainController: Window closed");
			exit();
		}
		else {
			System.out.println(e); //debugging
		}
	}
}
