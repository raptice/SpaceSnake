package view.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingUtilities;

import util.Vector2D;

/**
 * The class for figures in the game view.
 * This is an observer for events like a new figure added.
 * Is also an ActionListener to be able to listen to itself.
 *  
 * @author Gustav
 * @version 2016-03-04
 */

public class MapFigure 
implements Observer
{

	protected double size;
	protected Vector2D position;
	protected Color color = new Color(255,0,0);
	
	//Extra buffer when determining bounds
	protected int extra=1;
	
	//Needed to repaint the containing panel and to delete itself
	protected MapView parent;
	
	
	/**
	 * Constructor that takes a position (x,y) and a size and generates a GameFigure.
	 *
	 * @param parent	The containing GameView
	 * @param position	The position vector.
	 * @param size		The size of the GameFigure.S
	 * @param color		The color.
	 */
    public MapFigure(MapView parent, Vector2D position, double size, Color color)
    {
    	this.parent = parent;
        this.position = position;
        this.size=size;
        this.color = color;
    }
	
    
    /**
     * Set a new size for the figure
     * @param new_size	The new size
     */
    private void resize (double new_size) {
    	size = new_size;
	}
    
    /**
     * Returns the color of itself to be used in the map.
     * @return		the current color
     */
    public Color getColor() {
    	return color;
    }
    
    /**
     * Sets the color of itself to be used in the map.
     * @param c		the new color
     */
    public void setColor(Color c) {
    	color = c;
    }
    
    /**
     * Paints itself.
     * @param g		the graphics object that is used
     */
    public void paintComponent(Graphics g) {
    	g.setColor(color);
    	g.drawLine((int)position.getX(), (int)position.getY(), (int)position.getX(), (int)position.getY());
    }
    
    
    /**
     * Used when an update has happened. Can handle new positions, sizes and if the object died.
     * @param who		The observable that was updated
     * @param what		What that was updated, either a Vector2D if it is a new position, a Double if it is a new size
     * 					or a String containing "Died" if it died.
     */
	@Override //Movement (or something)
	public void update(Observable who, Object what) {
		
		//If it was a vector: move there.
		if (what instanceof Vector2D) {
			final Vector2D position = new Vector2D((Vector2D) what);
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() { setPosition(position); }	    
			});
		}
				
		//If it was a Double: use it as size
		if (what instanceof Double) {
			final double size = (double) what;
			SwingUtilities.invokeLater(new Runnable() {
			    public void run() { resize(size); }	    
			});
		}	
				
		// if (died) parent.removeItem(this);
		if (what instanceof String) {
			if (((String)what).equals("Died"))
			{
				final MapFigure reference = this;
				SwingUtilities.invokeLater(new Runnable() {
				    public void run() { parent.removeMe(reference); }	    
				});
			}
		}
	}
	
	
	/**
	 * Moves the figure to a new position
	 * @param position	The new position
	 */
	private void setPosition (Vector2D position) {
		this.position = position;
	}
	
	/**
	 * Returns the x-coordinate
	 * @return the x-coordinate
	 */
	public double positionX() {
		return position.getX();
	}
	
	/**
	 * Returns the y-coordinate
	 * @return the y-coordinate
	 */
	public double positionY() {
		return position.getY();
	}
}
