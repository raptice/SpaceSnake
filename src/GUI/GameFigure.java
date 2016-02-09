import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The main class for figures in the game view. Should be subclassed for all different kinds of figures.
 * Is an observer for events like a new figure added.
 * Is an ActionListener to be able to listen to itself.
 *  
 * @author Gustav
 * @version 2016-02-05
 */

public class GameFigure 
extends JPanel
implements Observer, ActionListener
{

	int size;
	int x,y;
	
	//Only for testing:
	Timer t;
	
	
	/**
	 * Constructor that takes a position (x,y) and a size and generates a GameFigure.
	 * 
	 * @param x		The x-position
	 * @param y		The y-position
	 * @param size	The size of the GameFigure
	 */
    public GameFigure(int x, int y, int size)
    {
        //super();
    	
        this.x=x;
        this.y=y;
        this.size=size;
        
        this.setLocation(x, y);
        this.setSize(size,size);
        
        //Needed if it should be a JPanel
        this.setOpaque(false);
        
        //For testing:
        t = new Timer(20,this);
        t.start();
    }
    
    
    /**
     * Constructor that creates a default GameFigure with position (0,0) and size 100.
     */
    public GameFigure()
	{
		this(0,0,100);	
	}
	
    
    /**
     * Used for testing only.
     */
    @Override
	public void actionPerformed(ActionEvent e) {
		x += 20*(Math.round(Math.random())*2-1);
		y += 20*(Math.round(Math.random())*2-1);
		this.setLocation(x, y);
		super.repaint();
	}
	
    
    /**
     * paints itself. Does NOT work as intended!
     * @param g
     */
    public void paintComponent(final Graphics2D g) {
		super.paintComponent(g);
		render(g);
	}
    
    
    /**
     * Paints itself. Does NOT work on updates!
     * @param g
     */
    public void render(final Graphics2D g)
    {
    	int x = this.getLocation().x;
    	int y = this.getLocation().y;
    	//int diameter = this.getSize().height;
    	
    	g.setColor(Color.YELLOW);
        g.fillOval(x-size/2, y-size/2, size, size);
        g.setColor(Color.BLACK);
        g.drawOval(x-size/2, y-size/2, size, size);

        //g.drawLine(x + 20, y + 10, x + 20, y + 20);
        //g.drawLine(x + 30, y + 10, x + 30, y + 20);

        //g.drawArc(x + 15, y + 15, 20, 20, 180, 180);
        //Graphics2D g2 = (Graphics2D)g;

        //Line2D line = new Line2D.Double(10, 10, 40, 40);
        //g2.setColor(Color.blue);
        //g2.setStroke(new BasicStroke(10));
        //g2.draw(line);
    }
    
    
    /**
     * Used when "the model" sends notifyObservers(arg1).
     */
	@Override //Movement (or something)
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub	
	}
	
}
