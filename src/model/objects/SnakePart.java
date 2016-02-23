package model.objects;

import util.Config;
import util.Vector2D;
import model.Moveable;
import model.WorldCollection;
import model.WorldObject;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class SnakePart
extends Moveable 
{
	protected SnakePart nextPart;
	private double stiffness = 10;
	private double linkLength = 40;
	private double damping = 0.9;

	
	/**
	 * Constructor with doubles
	 */
	public SnakePart(double xSpeed, double ySpeed, double xPos, double yPos,
			double mass, double radius) {
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
	}
	
	
	/**
	 * Constructor with vectors
	 */
	public SnakePart(Vector2D velocity, Vector2D position, double mass, double radius) {
		super(velocity, position, mass, radius);
		stiffness = Double.parseDouble(Config.get("Snake_link_stiffness"));
		damping = Double.parseDouble(Config.get("Snake_link_damping"));
	}
	
	
	/**
	 * Calculates an accelerates this and the next part depending on a spring constant
	 */
	public void pullAtNext (double dT) {
		if (nextPart != null) {
			
			//Elastic pull/push
			Vector2D distance = this.position.sub( nextPart.getPosition() );
			double x = distance.length() - linkLength;
			this.accelerate( distance.normalize().scale(-x*stiffness), dT );
			nextPart.accelerate( distance.normalize().scale(x*stiffness), dT );
			
			//Damping
			Vector2D v = this.velocity.sub(nextPart.getVelocity());
			this.accelerate(v.scale(-damping), dT);
			nextPart.accelerate(v.scale(damping), dT);
			
		}
	}
	
	
	/**
	 * Adds a new part at the end of this.
	 * @param tail	The part that should get added
	 * @return	true if the tail was added, false if it already has a tail
	 */
	public boolean addTail (SnakePart tail) {
		if (nextPart != null) return false;
		nextPart = tail;
		linkLength = this.getRadius() + tail.getRadius();
		return true;
	}
	
	
	/**
	 * Returns the tail
	 * @return the tail
	 */
	public SnakePart getTail () {
		return nextPart;
	}


	/**
	 * Overrides function in Moveable to only make collisions happen if it is not the next neighbor.
	 */
	@Override
	public void collision (WorldObject obj) {
		if (!isNeighbor(obj)) 
			super.collision(obj);
	}
	
	
	/**
	 * Checks if a obj is the neighbor of this one.
	 * @param obj
	 * @return
	 */
	protected boolean isNeighbor (WorldObject obj) {
		if (obj instanceof SnakePart)
		{
			if (((SnakePart) obj).nextPart != null && ((SnakePart) obj).nextPart.equals(this))
				return true;
			if (nextPart !=null && obj.equals(nextPart))
				return true;
		}
		return false;
	}
}
