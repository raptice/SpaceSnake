package view;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;

import util.FillAllLayout;

import java.io.*;

/**
 * This is a class that just contains the MainWindow in one JFrame
 * 
 * @author Gustav
 * @version 2016-02-05
 */

@SuppressWarnings("serial")
public class MainWindow 
extends GameComponent 
implements WindowListener 
{		

	/** Default layer for the game (far back). */
	public static int GAMELAYER = JLayeredPane.DEFAULT_LAYER;
	/** Default layer for the menu (in front). */
	public static int MENULAYER = JLayeredPane.POPUP_LAYER;
	/** Default layer for the menu (in over the game). */
	public static int GAMEMENULAYER = JLayeredPane.MODAL_LAYER;
	/** Default layer for the menu (just over the game). */
	public static int MESSAGELAYER = JLayeredPane.PALETTE_LAYER;
	/** Default layer for the menu (top of everything). */
	public static int TOPLAYER = JLayeredPane.DRAG_LAYER;
	
	//The JFrame that contains everything
	private JFrame theWindow;
	//The pane to which everything is added in different layers
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
		theWindow = new JFrame("Space Snake");
				
		//Set up the window appearance...
		//theWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
//new ImageIcon(getClass().getClassLoader().getResource("PATH/TO/YourImage.png")));
		//URL iconURL = getClass().getResource("src/3D_Geometrical_Figures_24.svg.png");
		// iconURL is null when not found
		//ImageIcon icon = new ImageIcon(iconURL);
		//theWindow.setIconImage(icon.getImage());
		
		theWindow.setPreferredSize(new Dimension(800, 600));
		theWindow.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        theWindow.setLocation(d.width/3 - theWindow.getWidth()/3, d.height/3 - theWindow.getHeight()/3);
        theWindow.setVisible(true);
		
        // Proper closing: (Should instead send action to controller)
        //theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add key listener (if ESC is pressed)
        addKeyListener(KeyEvent.VK_ESCAPE, "ESC Pressed");
		        
        theWindow.getContentPane().add(theContent);
        theContent.setLayout(new FillAllLayout());    
	}
	
	/**
	 * Adds KeyStrokeEvents and sends ActionEvents to the ActionListeners.
	 */
	public void addKeyListener (int key, String theCommand) {
		
		final String code = new String("Key_"+key);
	    final String command = new String(theCommand);
	    
		InputMap inputMap  = ((JComponent)theWindow.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = ((JComponent)theWindow.getContentPane()).getActionMap();
	    
	    inputMap.put(KeyStroke.getKeyStroke(key, 0), code); //KeyEvent.VK_ESCAPE
	    actionMap.put(code, new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	    		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED, command, Calendar.getInstance().getTime().getTime(),0));
	        }
	    });
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


	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Window closed",Calendar.getInstance().getTime().getTime(),0));
	}
	@Override
	public void windowClosing(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
	
}
