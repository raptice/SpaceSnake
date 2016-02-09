package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * This is a class that just contains the game itself
 * 
 * Is listener to itself in order tozoom and other stuff. Som events gets sent to others...
 * is observer at the model on updates there (new objects?)
 * 
 * @author Gustav
 * @version 2016-02-05
 */


public class GameView 
extends GameComponent 
implements MouseWheelListener, MouseMotionListener, MouseListener, Observer
{

	//Temp variable until the proper world gets used
	int worldSize=400;
	
	// Determines the zoom level
	protected float zoom = 2;
	
	//How much should the zoom change on zoom in/out
	private final float zoomstep = (float) 1.2; 

	//All stuff within the gameview:
	private LinkedList<GameFigure> figures = new LinkedList<GameFigure>();
	
	
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
	 * 
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
		this.setLayout(null);
		
		for (int i=0;i<10;i++)
			this.add(new GameFigure((int)(Math.random()*400)-200, (int)(Math.random()*100)-50, 50));
			//figures.add(new GameFigure((int)(Math.random()*400)-200, (int)(Math.random()*100)-50, 50));
		figures.add(new GameFigure());
		
		//repaint();  //Don't know here...
	}

	
	/**
	 * This draws the world. Is NOT properly functional.
	 * 
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
        g.setColor(Color.BLACK);
        g.drawOval(-worldSize/2, -worldSize/2, worldSize, worldSize);
        g.setColor(Color.WHITE);
        g.fillOval(-worldSize/2, -worldSize/2, worldSize, worldSize);
        
        //for(Component c : this.getComponents())
        //	c.repaint();
        
        //Draw all figures
        for (GameFigure figure : figures)
        	figure.render(g2);
        
    }
	
	
	/**
	 * Zoom in
	 * @return The zoom level after the zoom action
	 */
	public float zoomIn()
	{
		return zoom *= zoomstep;
	}

	
	/**
	 * Zoom in
	 * @return The zoom level after the zoom action
	 */
	public float zoomOut()
	{
		return zoom /= zoomstep;
	}
	
	
	@Override //Zoom in or out
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		//TODO
		System.out.println(arg0);
	}
	
	@Override //Accelerate a bit?
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		System.out.println("click");
	}
	
	@Override //Should do nothing
	public void mouseEntered(MouseEvent arg0) {}
	
	@Override //Stop accelerating?
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	
	@Override //Start accelerating
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	
	@Override //Stop accelerating
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	
	@Override //Change acceleration direction
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	
	@Override //Should do nothing
	public void mouseMoved(MouseEvent arg0) {}
	
	@Override //Something happened in the world!!!
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}
	
}
