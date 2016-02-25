package model.objects;

import model.WorldObject;
import util.Vector2D;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class SnakeTail 
extends SnakePart
{

	public SnakeTail(double xSpeed, double ySpeed, double xPos, double yPos,
			double mass, double radius) {
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
	}
	
	public SnakeTail(Vector2D velocity, Vector2D position, double mass, double radius) {
		super(velocity, position, mass, radius);
	}
	
	/**
	 * Overrides function in SnakePart to only make collisions happen if the controller lets it
	 */
	@Override
	public void collision (WorldObject obj) {
		if (obj instanceof BlackHole){
			 die();
		} else {
			super.collision(obj);
		}
	}
	private void die() {
		SnakePart next = nextPart;
		while (next != null)
		{
			next.kill();
			//theWorld.remove(next);
			next=next.getTail();
		}
		this.kill();
		//theWorld.remove(this);
		//set previous parts tail to null.
		
	}
}
