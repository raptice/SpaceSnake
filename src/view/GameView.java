package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;


import model.Floater;
import model.WorldCollection;
import model.WorldObject;
import model.objects.SnakeHead;
import model.objects.SnakeTail;

import util.Command;
import util.Vector2D;
import view.Figures.*;

/**
 * This is a class that just contains the game itself
 * 
 * Is listener to itself in order to zoom and other stuff. Some events gets sent to others...
 * is observer at the model on updates there (new objects?)
 * 
 * @author Gustav
 * @version 2016-02-05
 */


@SuppressWarnings("serial")
public class GameView 
extends GameComponent 
implements MouseWheelListener, MouseMotionListener, MouseListener, GameObserver, ActionListener
{

	//Temp variable until the proper world gets used
	int worldSize=800;
	
	// Determines the zoom level
	protected float zoom = 1;
	
	//How much should the zoom change on zoom in/out
	private double zoomstep = 1.01; 
	
	//The snake position
	private Vector2D snakePosition=new Vector2D(0,0);
	
	/**
	 * Constructor that generates the view.
	 */
	public GameView ()
	{
		System.out.println("Gameview created");		
		this.build();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}
	
	
	/**
	 * Constructor that generates the game view and adds an ActionListener to it.
	 * @param AL		The ActionListener to which events get sent
	 */
	public GameView (ActionListener AL)
	{
		this();
		this.addActionListener(AL);
	}
	
	
	/**
	 * Removes everything from the view. Equal to restart the view.
	 */
	public void clear() {
		removeAll();
	}
	
	
	/**
	 * The actual constructor of the view. Only called from the constructors.
	 */
	private void build()
	{
		//Use coordinates for positioning
		this.setLayout(null);
	}

	
	/**
	 * This draws the world.
	 */
	@Override
    public void paintComponent(final Graphics g) {
		
		Graphics2D g2 =(Graphics2D)g;
        super.paintComponent(g2);
        
        //Set center to (0,0)
        g2.translate(this.getWidth()/2 - snakePosition.getX()*zoom, this.getHeight()/2 - snakePosition.getY()*zoom); 
        
        // Set zoom
        g2.scale(zoom, zoom);
       
        // Draw the world
        g2.setRenderingHint(
    	        RenderingHints.KEY_ANTIALIASING,
    	        RenderingHints.VALUE_ANTIALIAS_ON);
    	g2.setColor(Color.BLACK);
        g2.drawOval(-worldSize/2-1, -worldSize/2-1, worldSize+2, worldSize+2);
        g2.setColor(Color.WHITE);
        g2.fillOval(-worldSize/2, -worldSize/2, worldSize, worldSize);  
    }
	
	
	/**
	 * Zoom in or out
	 * @param amount	The amount to zoom. Logarithmic scale	
	 * @return The zoom level after the zoom action
	 */
	public float zoom(int amount){
		zoom *= Math.pow(zoomstep, amount);
		repaint();
		return zoom;
	}
	
	
	@Override //Zoom in or out
	public void mouseWheelMoved(MouseWheelEvent e) {
		zoom(e.getUnitsToScroll());
	}
	
	@Override //Accelerate a bit?
	public void mouseClicked(MouseEvent arg0) {	
		//fireEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Mouse clicked", Timestamp.now(), 0));
	}
	
	@Override //For keeping track?
	public void mouseEntered(MouseEvent arg0) {
		//fireEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Mouse inside game area", Timestamp.now(), 0));
	}
	
	@Override //Stop accelerating?
	public void mouseExited(MouseEvent arg0) {
		//fireEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Mouse out of game area", Timestamp.now(), 0));
	}
	
	@Override //Start accelerating
	public void mousePressed(MouseEvent arg0) {
		fireEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Command.MOUSE_PRESSED, System.currentTimeMillis(), 0));
	}
	
	@Override //Stop accelerating
	public void mouseReleased(MouseEvent arg0) {
		fireEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Command.MOUSE_RELEASED, System.currentTimeMillis(), 0));
	}
	
	@Override //Change acceleration direction
	public void mouseDragged(MouseEvent arg0) {
		fireEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Command.MOUSE_DRAGGED, System.currentTimeMillis(), 0));
	}
	
	@Override //Should do nothing
	public void mouseMoved(MouseEvent arg0) {}
	
	
	/**
	 * Adds a new world to the view including all objects and all constants.
	 */
	public void addWorld (WorldCollection world) {
		for (WorldObject thing : world.getCollection()) {
			addItem(thing);
		}
		System.out.println("Addworld i GameView");
		
	}
	
	
	/**
	 * When something is added to the world it gets sent here
	 */
	@Override //Something happened in the world!!!
	public void update(Observable who, Object what) {
		//If it was a worldObject: add it.
		if (what instanceof WorldObject) {
			addItem((WorldObject) what);
		}
	}
	
	
	/**
	 * Adds some item to the world
	 * @param what	The item to add
	 */
	private void addItem (WorldObject what) {
		final GameFigure figure;
		if (what instanceof Floater) {
			figure = new FloaterView(what.getPosition().getX(), what.getPosition().getY(), what.getRadius()*2 ,this);
		} else if (what instanceof SnakeHead) {
			figure = new SnakeHeadView(what.getPosition().getX(), what.getPosition().getY(), what.getRadius()*2 ,this);
		} else if (what instanceof SnakeTail) {
			figure = new SnakeTailView(what.getPosition().getX(), what.getPosition().getY(), what.getRadius()*2 ,this);
		} else {
			figure = new GameFigure(what.getPosition().getX(), what.getPosition().getY(), what.getRadius()*2 ,this);
		}
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() { addFigure(figure); }	    
		});
		what.addObserver(figure);		
	}
	
	
	/**
	 * Adds a GameFigure too this so it gets painted.
	 * @param figure
	 */
	private void addFigure (GameFigure figure) {
		this.add(figure);
	}
	
	
	/**
	 * Remove some item from the world. Called from the items themselves.
	 * @param who	The item to remove
	 */
	public void removeMe(GameFigure who) {
		this.remove(who);
	}
	
	/**
	 * Used for the snake to update where it is.
	 */
	public void updateSnakePosition(Vector2D position) {
		this.snakePosition = position;
	}


	/**
	 * For commands sent from the game view menu
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(Command.ZOOM_IN)) zoom(10);
		else if (e.getActionCommand().equals(Command.ZOOM_OUT)) zoom(-10);
		
	}
}
