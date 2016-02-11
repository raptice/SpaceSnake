package GUI;
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
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import GUI.Figures.*;

/**
 * This is a class that just contains the game itself
 * 
 * Is listener to itself in order to zoom and other stuff. Som events gets sent to others...
 * is observer at the model on updates there (new objects?)
 * 
 * @author Gustav
 * @version 2016-02-05
 */


@SuppressWarnings("serial")
public class GameView 
extends GameComponent 
implements MouseWheelListener, MouseMotionListener, MouseListener, Observer
{

	//Temp variable until the proper world gets used
	int worldSize=400;
	
	// Determines the zoom level
	protected float zoom = 2;
	
	//How much should the zoom change on zoom in/out
	private double zoomstep = 1.01; 
	
	
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
	 * The actual constructor of the view. Only called from the constructors.
	 */
	private void build()
	{
		//Use coordinates for positioning
		this.setLayout(null);
		
		//For testing
		for (int i=0;i<10;i++) {
			if (Math.random()>0.5)
				this.add(new GameFigure((int)(Math.random()*400)-200, (int)(Math.random()*100)-50, 50,this));
			else
				this.add(new BlackHole((int)(Math.random()*400)-200, (int)(Math.random()*100)-50, 50,this));
		}
	}

	
	/**
	 * This draws the world.
	 */
	@Override
    public void paintComponent(final Graphics g) {
		
		Graphics2D g2 =(Graphics2D)g;
        super.paintComponent(g2);
        
        //Set center to (0,0)
        g2.translate(this.getWidth()/2,this.getHeight()/2); 
        
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
		return zoom *= Math.pow(zoomstep, amount);
	}
	
	
	@Override //Zoom in or out
	public void mouseWheelMoved(MouseWheelEvent e) {
		zoom(e.getUnitsToScroll());
	}
	
	@Override //Accelerate a bit?
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Mouse clicked",Calendar.getInstance().getTime().getTime(),0));
	}
	
	@Override //For keeping track?
	public void mouseEntered(MouseEvent arg0) {
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Mouse inside game area",Calendar.getInstance().getTime().getTime(),0));
	}
	
	@Override //Stop accelerating?
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Mouse out of game area",Calendar.getInstance().getTime().getTime(),0));
	}
	
	@Override //Start accelerating
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Mouse pressed",Calendar.getInstance().getTime().getTime(),0));
	}
	
	@Override //Stop accelerating
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Mouse released",Calendar.getInstance().getTime().getTime(),0));
	}
	
	@Override //Change acceleration direction
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Mouse dragged",Calendar.getInstance().getTime().getTime(),0));
	}
	
	@Override //Should do nothing
	public void mouseMoved(MouseEvent arg0) {}
	
	@Override //Something happened in the world!!!
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Adds some item to the world
	 * @param what	The item to add
	 */
	private void addItem (String what) {
		//TODO
		//Check what for type
		//figure = new GameFigureType(...);
		//model.addObsever(figure);
		//this.add(figure);
	}
	
	/**
	 * Remove some item from the world. Called from the items themselves.
	 * @param who	The item to remove
	 */
	public void removeMe(GameFigure who) {
		this.remove(who);
	}
}
