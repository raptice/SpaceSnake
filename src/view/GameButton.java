package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import util.Config;

@SuppressWarnings("serial")
public class GameButton 
extends JButton
{
	private Color bg_color = new Color(200,200,240,200);
	private Color border_color = new Color(100,100,140,200);
	private Color highlight_color = new Color(255,0,255,255);
	private int corner_r = 20;
	
	public GameButton(String string) {
		super(string);
		setContentAreaFilled(false);
		corner_r = Integer.parseInt(Config.get("Menu_corner_r"));
		this.setFont(getFont().deriveFont(Font.BOLD, Integer.parseInt(Config.get("Menu_button_fontsize"))));
	}

	public void setBackground(Color c) {
		bg_color=c;
	}
	public void setBorderColor(Color c) {
		border_color=c;
	}
	public void setHighlightColor(Color c) {
		highlight_color=c;
	}
	
	public void paintComponent(Graphics g_in) {
		Graphics2D g = (Graphics2D) g_in;
		int width = this.getWidth();
		int height = this.getHeight();

		g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(bg_color);
		g.fillRoundRect(0, 0, width-1, height-1, corner_r, corner_r);
		g.setColor(border_color);
		g.drawRoundRect(0, 0, width-1, height-1, corner_r, corner_r);
		
		if (getModel().isRollover())
			g.setColor(highlight_color);
		else if (getModel().isPressed())
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.BLACK);
        
		String text=this.getText();
        g.setFont(this.getFont());
        Rectangle2D r = g.getFont().getStringBounds(text, g.getFontRenderContext());
        g.drawString(text, (int)(width/2-r.getCenterX()),(int)(height/2-r.getCenterY()));
	}
	
	// Do not paint other borders than in paintComponent
	@Override public void paintBorder(Graphics g_in) {}
	
	/**
	 * Checks if some coordinates are within the button. Needed to override due to the rounded corners. 
	 */
	@Override
	public boolean contains (int x, int y) {
		int width = this.getWidth();
		int height = this.getHeight();
		return (new RoundRectangle2D.Double(0, 0, width-1, height-1, corner_r, corner_r)).contains(x, y);
	}
	
	@Override
	public Dimension getPreferredSize() {
		FontMetrics fm = this.getFontMetrics(this.getFont());
		Rectangle2D r = fm.getStringBounds(this.getText(), this.getGraphics());
		return new Dimension((int)r.getWidth()+corner_r,(int)r.getHeight()+corner_r/2);
	}
	
}
