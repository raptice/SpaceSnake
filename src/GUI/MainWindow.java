package GUI;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;


/**
 * This is a class that just contains the MainWindow in one JFrame
 * 
 * @author Gustav
 * @version 2016-02-05
 */

public class MainWindow 
extends GameComponent{		

	/** Default layer for the game (far back). */
	public static int GAMELAYER = JLayeredPane.DEFAULT_LAYER;
	/** Default layer for the menu (in front). */
	public static int MENULAYER = JLayeredPane.PALETTE_LAYER;
	

	private JFrame theWindow;
	private JLayeredPane theContent = new JLayeredPane();

	
	/**
	 * Constructor that generates the window.
	 */
	public MainWindow ()
	{
		System.out.println("Main window created");		
		this.build();
	}
	
	
	/**
	 * Constructor that creates the main window and adds an {@link ActionListener} to it.
	 * @param AL	The ActionListener to be added to the main window.
	 */
	public MainWindow (ActionListener AL)
	{
		this();
		this.addActionListener(AL);
	}
	
	
	/**
	 * The actual constructor that generates everything. Called from the constructor
	 * to keep that method nicer.
	 */
	private void build()
	{
		//Load a window
		theWindow = new JFrame("Main Window");
				
		//Set up the window appearance...
		theWindow.setPreferredSize(new Dimension(400, 300));
		theWindow.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        theWindow.setLocation(d.width/3 - theWindow.getWidth()/3, d.height/3 - theWindow.getHeight()/3);
        theWindow.setVisible(true);
		        
        // Proper closing: (Should instead send action to controller)
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add key listener (if ESC is pressed)
        InputMap inputMap  = ((JComponent)theWindow.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = ((JComponent)theWindow.getContentPane()).getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
        actionMap.put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	fireEvent(e);
            }
        });	        
		        
        theWindow.getContentPane().add(theContent);
        theContent.setLayout(new FillAllLayout());    
	}
	
	
	/**
	 * Adds a game component as a part to the main window.
	 * 
	 * @param c		The component that should be added
	 * @param layer	An integer determining in what layer it should be added
	 */
	public void addGameComponent (GameComponent c, int layer)
	{
		theContent.add(c);
		theContent.setLayer(c, layer); //Needed to get the layer correct
		pack();
	}
	
	
	/**
	 * Refits everything in the window. Should be called after anything is added or removed 
	 * so everything gets rendered properly.
	 */
	private void pack()
	{
		theWindow.pack();
	}
	
	
	/**
	 * removes a component from the main window making it invisible.
	 * 
	 * @param c		The component that should be removed
	 */
	public void remove(Component c)
	{
		theContent.remove(c);
	}
}
