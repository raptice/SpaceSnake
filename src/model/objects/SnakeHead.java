package model.objects;

import util.Vector2D;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class SnakeHead 
extends SnakePart
{

	public SnakeHead(double xSpeed, double ySpeed, double xPos, double yPos,
			double mass, double radius) {
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
	}
	
	public SnakeHead(Vector2D velocity, Vector2D position, double mass, double radius) {
		super(velocity, position, mass, radius);
	}
}
