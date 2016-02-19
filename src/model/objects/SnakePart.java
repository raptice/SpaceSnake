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
	private SnakePart nextPart;
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
	public void pullAtNext () {
		if (nextPart != null) {
			
			//Elastic pull/push
			Vector2D distance = this.position.sub( nextPart.getPosition() );
			double x = distance.length() - linkLength;
			this.accelerate( distance.normalize().scale(-x*stiffness) );
			nextPart.accelerate( distance.normalize().scale(x*stiffness) );
			
			//Damping
			Vector2D v = this.velocity.sub(nextPart.getVelocity());
			this.accelerate(v.scale(-damping));
			nextPart.accelerate(v.scale(damping));
			
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
	 * Checks if this part has a tail.
	 * @return true if it has a tail, false otherwise
	 */
	public boolean checkTail () {
		if (nextPart == null) return false;
		return true;
	}

	@Override
	/*public void collision (WorldCollection data) {
		for(WorldObject obj : data.getCollection()){
			if(!obj.equals(this) && collides(obj))
			{
				if (obj instanceof SnakePart)
				{
					SnakePart other = (SnakePart)obj;
					if (!other.equals(nextPart) && !this.equals(other.nextPart))
							collide(obj);
				}
				else collide(obj);
			}
		}
	}*/
	
	public void collision(WorldCollection data){
		for(WorldObject obj : data.getCollection()){
			if(!this.equals(obj) && collides(obj))
			{
				if (obj instanceof SnakePart)
				{
					if (!obj.equals(nextPart) && !((SnakePart)obj).nextPart.equals(this))
						velocity_diff = velocity_diff.sub(CollisionResponse(obj));
				}
				else
					velocity_diff = velocity_diff.sub(CollisionResponse(obj));
			}
		}
	}
}
