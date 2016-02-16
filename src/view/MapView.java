package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import util.Config;
import util.Parser;
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
public class MapView 
extends GameComponent 
implements MouseListener, Observer, ActionListener
{

	//Temp variable until the proper world gets used
	int worldSize=800;
	
	// Determines the zoom level
	protected float mapSize = 1;
	private int margin = 5;
	
	private ArrayList<GameFigure> theList = new ArrayList<GameFigure>();
	
	//Update itself every now and then
	Timer t;
		
	Color bg_color1 = new Color(255,255,255,255);
	Color bg_color2 = new Color(255,255,255,200);
	Color bg_color3 = new Color(255,255,255,255);
	Color border_color = new Color(0,0,0,200);
	
	/**
	 * Constructor that generates the view.
	 */
	public MapView ()
	{
		System.out.println("Mapview created");		
		this.build();
		this.addMouseListener(this);
		
		bg_color1 = Parser.ColorFromString(Config.get("Map_bg_color1"));
		bg_color2 = Parser.ColorFromString(Config.get("Map_bg_color2"));
		bg_color3 = Parser.ColorFromString(Config.get("Map_bg_color3"));
		border_color = Parser.ColorFromString(Config.get("Map_border_color"));
		
		t = new Timer(500,this);
        t.start();
	}
	
	
	/**
	 * The actual constructor of the view. Only called from the constructors.
	 */
	private void build()
	{
		//Use coordinates for positioning
		this.setLayout(null);
		mapSize = Integer.parseInt(Config.get("Map_size"));
		
		//For testing
		for (int i=0;i<10;i++) {
			if (Math.random()>0.5)
				theList.add(new GameFigure((int)(Math.random()*400)-200, (int)(Math.random()*100)-50, 50,this));
			else
				theList.add(new BlackHole((int)(Math.random()*400)-200, (int)(Math.random()*100)-50, 50,this));
		}
	}

	
	/**
	 * This draws the map.
	 */
	@Override
    public void paintComponent(final Graphics g) {
		
		Graphics2D g2 =(Graphics2D)g;
        super.paintComponent(g2);
        
        //Set center to (0,0)
        g2.translate(this.getWidth()-mapSize/2-margin,this.getHeight()-mapSize/2-margin); 
        
        // Set zoom
        g2.scale(mapSize/worldSize, mapSize/worldSize);
        
        // Draw the world
        g2.setRenderingHint(
    	        RenderingHints.KEY_ANTIALIASING,
    	        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2*worldSize/mapSize));
        
        Point2D center = new Point2D.Double(0, 0);
        Point2D focus = center;//new Point2D.Float(40, 40);
        float[] dist = {0.0f, 0.9f, 1.0f};
        Color[] colors = {bg_color1, bg_color2, bg_color3};
        RadialGradientPaint rgrad = new RadialGradientPaint(center, (float) worldSize/2, focus, dist, colors, CycleMethod.NO_CYCLE);
        g2.setPaint(rgrad);
        
        
        
    	g2.setColor(border_color);
        g2.drawOval(-worldSize/2-1, -worldSize/2-1, worldSize+2, worldSize+2);
        //g2.setColor(new Color(255,255,255,200));
        g2.setPaint(rgrad);
        g2.fillOval(-worldSize/2, -worldSize/2, worldSize, worldSize);  
        
        //Draw all items:
        for (GameFigure figure : theList)
        {
        	g2.setColor(figure.getColor());
        	g2.drawLine(figure.getX(), figure.getY(), figure.getX(), figure.getY());
        }
    }
	
	
	//Do nothing on any MouseEvents
	@Override public void mouseClicked(MouseEvent arg0) {}
	@Override public void mouseEntered(MouseEvent arg0) {}
	@Override public void mouseExited(MouseEvent arg0) {}
	@Override public void mousePressed(MouseEvent arg0) {}
	@Override public void mouseReleased(MouseEvent arg0) {}
	
	
	/**
	 * Needed for the rounded corners.
	 */
	@Override
	public boolean contains (int x, int y) {
		double x1 = (this.getWidth()-margin-mapSize/2)-x;
		double y1 = (this.getHeight()-margin-mapSize/2)-y;
		return mapSize*mapSize/4 > x1*x1+y1*y1;
	}
	
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


	/**
	 * For commands sent from the game view menu
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}