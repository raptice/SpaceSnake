package model.objects;

import model.Moveable;
import util.Vector2D;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class Edible 
extends Moveable
{

	public Edible(double xSpeed, double ySpeed, double xPos, double yPos,
			double mass, double radius) {
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
	}
	
	public Edible(Vector2D velocity, Vector2D position, double mass, double radius) {
		super(velocity, position, mass, radius);
	}
}
