import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 * This is the GUI class that handles every graphical interaction
 * 
 * @author Gustav
 * @version 2016-02-05
 */

/*
 * Comments:
 * Borde kanske göras om till en user interface klass?
 * 
 */

public class GUI
{
	
	private IngameMenu ingameMenu;
	private GameView gameView;
	private StartupMenu startupMenu;
	private JDialog theMenu;
	private MainWindow mainWindow;

	
	/**
	 * Constructor, does nothing. To perform something the different methods generating parts needs to be called.
	 */
	public GUI()
	{
		// start everything
		System.out.println("GUI: started");
				
	}
	
	
	/**
	 * Starts the main window in which everything happens
	 * Move to constructor? Or subclass?
	 */
	public void startMainWindow(ActionListener AL)
	{
		if (mainWindow==null) mainWindow = new MainWindow();
		mainWindow.addActionListener(AL);
	}
	
	
	/**
	 * Shows the startup menu
	 * 
	 * If a startup menu exists it is reused
	 * 
	 */
	public void showStartupMenu(ActionListener AL)
	{
		if (startupMenu==null) startupMenu = new StartupMenu();
		startupMenu.addActionListener(AL);
		mainWindow.addGameComponent(startupMenu,mainWindow.MENULAYER);
	}
	
	/**
	 * removes the startup menu from the main window.
	 */
	public void hideStartupMenu()
	{
		mainWindow.remove(startupMenu);
	}
	
	
	/**
	 * Shows the ingame menu
	 * Uses the class IngameMenu.
	 * More to move there?
	 */
	public void showIngameMenu(ActionListener AL)
	{
		/*theMenu = new JDialog(theWindow,"Game Menu");
		theMenu.add(new IngameMenu(AL));
		theMenu.setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
		theMenu.setPreferredSize(new Dimension(400, 300));
		theMenu.pack();
		//theMenu.setLocation(theWindow.getLocation().x+(int)theWindow.getSize().getWidth()/2-(int)menuDialog.getSize().getWidth()/2,
		//		theWindow.getLocation().y+(int)theWindow.getSize().getHeight()/2-(int)menuDialog.getSize().getHeight()/2);
		theMenu.setVisible(true);
		*/
	}
	public void hideIngameMenu()
	{
		//theMenu.dispose();
	}
	
	
	/**
	 * Shows a Game in which everything happens. Can send actions to an {@link ActionListener} (the controller)
	 * the actions are: accelerate in different directions with different forces and to stop accelerating.
	 * maybe also on click a one time acceleration "pulse".
	 * 
	 * @param AL	The Actionlistener to which the game sends its actions
	 */
	public void showGame(ActionListener AL)
	{
		if (gameView==null) gameView = new GameView();
		gameView.addActionListener(AL);
		mainWindow.addGameComponent(gameView, mainWindow.GAMELAYER);
	}
	
	/**
	 * Hides the game.
	 * TODO: what happens if no game exists?
	 */
	public void hideGame()
	{
		mainWindow.remove(gameView);
	}
	
}


