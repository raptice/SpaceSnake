package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComponent;


/**
 * Master class for all components used and added to the GUI that fixes everything concerning the ActionListers.
 * Keeps a list of the ActionListeners and methods to add and remove from it as well as a protected method to fire an event.
 * 
 * @author Gustav
 * @version 2016-02-05
 */

public class GameComponent 
extends JComponent
{		

	// The ActionListeners. ArrayList is simple...
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

	
	/**
	 * Constructor
	 */
	public GameComponent () {}
	
	
	/**
	 * Constructor that takes an ActionListener and adds it to its listeners.
	 * 
	 * @param listener	The ActionListener that should be added
	 */
	public GameComponent (ActionListener listener)
	{
		this();
		this.addActionListener(listener);
	}	

	
	/**
	 * Add an ActionListener to the listeners which gets called if some event is triggered from the GameComponent.
	 * 
	 * @param listener	The ActionListener that should be added
	 */
	public void addActionListener (ActionListener listener){
		if (!listeners.contains(listener))
			listeners.add(listener);
	}
	
	
	/**
	 * Removes a ActionListener from its list of listeners that gets called on events
	 * 
	 * @param listener	The ActionListener that should be removed
	 */
	public void removeActionListener (ActionListener listener){
		listeners.remove(listener);
	}
	
	
	/**
	 * The method that triggers an event in all the listeners. Can only be called from subclasses.
	 * 
	 * @param event		The event thats get sent to the listeners
	 */
	protected void fireEvent (ActionEvent event){
		for (ActionListener listener : listeners)
			listener.actionPerformed(event);
	}

}
