package GUI.Figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class BlackHole 
extends GameFigure {

	public BlackHole(double x, double y, double size, JComponent parent){
		super(x, y, size, parent);		
	}

    /**
     * Paints itself.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g_in) {
    	//super.paintComponent(g_in);
    	Graphics2D g = (Graphics2D)g_in;
    	g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
    						RenderingHints.VALUE_ANTIALIAS_ON);
    	
    	g.setColor(Color.DARK_GRAY);
        g.fillOval(0, 0, (int)size, (int)size);
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, (int)size, (int)size);
        
        //g.drawImage(Toolkit.getDefaultToolkit().getImage("3D_Geometrical_Figures_24.svg.png"), 0, 0, null);
    }

}
