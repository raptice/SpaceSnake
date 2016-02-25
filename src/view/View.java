package view;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * This is the view class that handles every graphical interaction
 * 
 * @author Gustav
 * @version 2016-02-12
 */


public class View
{
	
	private IngameMenu ingameMenu;
	private GameView gameView;
	private GameViewMenu gameViewMenu;
	private MapView mapView;
	private StartupMenu startupMenu;
	private GameOverMenu gameOverMenu;
	private MainWindow mainWindow;

	
	/**
	 * Constructor that creates the different parts but only show the (empty) main window.
	 */
	public View()
	{
		System.out.println("view: started");

		mainWindow = new MainWindow();
		gameView = new GameView();
		gameViewMenu = new GameViewMenu();
		mapView = new MapView();
		startupMenu = new StartupMenu();
		ingameMenu = new IngameMenu();
		gameOverMenu = new GameOverMenu();
	}
	
	
	/**
	 * Add an ActionListener to the view
	 * @param the ActionListener
	 */
	public void addActionListener(ActionListener AL) {
		mainWindow.addActionListener(AL);
	}
	
	
	/**
	 * Adds a keyListener to the window that sends events to the ActionListeners
	 * @param key		The keyCode
	 * @param command	The command that gets sent to the ActionListeners
	 */
	public void addKeyListener (int key, String command) {
		mainWindow.addKeyListener (key, command);
	}
	

	/**
	 * Shows the startup menu
	 * @param AL		The ActionListener that handles the generated events..
	 */
	public void showStartupMenu(ActionListener AL)
	{
		startupMenu.addActionListener(AL);
		mainWindow.addGameComponent(startupMenu,MainWindow.MENULAYER);
	}
	
	
	/**
	 * removes the startup menu from the main window.
	 */
	public void hideStartupMenu()
	{
		mainWindow.remove(startupMenu);
	}
	
	
	/**
	 * Shows the ingame menu.
	 * 
	 * @param AL		The ActionListener that handles the generated events..
	 */
	public void showIngameMenu(ActionListener AL)
	{
		ingameMenu.addActionListener(AL);
		mainWindow.addGameComponent(ingameMenu,MainWindow.GAMEMENULAYER);
	}

	
	/**
	 * removes the ingame menu from the main window.
	 */
	public void hideIngameMenu()
	{
		mainWindow.remove(ingameMenu);
	}
	
	/**
	 * Shows the gameover menu.
	 * 
	 * @param AL		The ActionListener that handles the generated events..
	 */
	public void showGameOverMenu(ActionListener AL)
	{
		gameOverMenu.addActionListener(AL);
		mainWindow.addGameComponent(gameOverMenu,MainWindow.GAMEMENULAYER);
	}

	
	/**
	 * removes the gameover menu from the main window.
	 */
	public void hideGameOverMenu()
	{
		mainWindow.remove(gameOverMenu);
	}
	
	
	/**
	 * Shows a Game in which everything happens. Can send actions to an {@link ActionListener} (the controller)
	 * the actions are: accelerate in different directions with different forces and to stop accelerating.
	 * maybe also on click a one time acceleration "pulse".
	 * 
	 * @param AL	The ActionListener to which the game sends its actions
	 */
	public GameObserver showGame(ActionListener AL)
	{
		gameView.addActionListener(AL);
		gameViewMenu.addActionListener(gameView);
		
		mainWindow.addGameComponent(gameView, MainWindow.GAMELAYER);
		mainWindow.addGameComponent(gameViewMenu, MainWindow.GAMECONTROLLAYER);
		
		return gameView;
	}
	public GameObserver showNewGame(ActionListener AL)
	{
		gameView.clear();
		return showGame(AL);
	}
	
	/**
	 * Shows a Map in which everything happens.
	 * @param AL	The ActionListener to which the game sends its actions
	 */
	public GameObserver showMap()
	{
		mainWindow.addGameComponent(mapView, MainWindow.MAPLAYER);
		return mapView;
	}
	public GameObserver showNewMap()
	{
		mapView.clear();
		return showMap();
	}
	
	/**
	 * Hides the game.
	 */
	public void hideGame()
	{
		mainWindow.remove(gameView);
		mainWindow.remove(gameViewMenu);
		mainWindow.remove(mapView);
	}
	
}


