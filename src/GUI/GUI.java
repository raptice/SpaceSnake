package GUI;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 * This is the GUI class that handles every graphical interaction
 * 
 * @author Gustav
 * @version 2016-02-09
 */


public class GUI
{
	
	//private IngameMenu ingameMenu;
	private GameView gameView;
	private StartupMenu startupMenu;
	//private JDialog theMenu;
	private MainWindow mainWindow;

	
	/**
	 * Constructor that creates the different parts but only show the (empty) main window.
	 * 
	 * @param AL		The ActionListener that handles the events created
	 */
	public GUI(ActionListener AL)
	{
		System.out.println("GUI: started");

		mainWindow = new MainWindow(AL);
		startupMenu = new StartupMenu();
		gameView = new GameView();		
	}
	

	/**
	 * Shows the startup menu
	 * 
	 * @param AL		The ActionListener that handles the generated events..
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
	 * Shows the ingame menu. Not implemented!
	 * 
	 * @param AL		The ActionListener that handles the generated events..
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

	
	/**
	 * removes the ingame menu from the main window. Not implemented!
	 */
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


