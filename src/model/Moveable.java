package model;
import util.*;

/**
 * Movable class represents a movable object in space
 * 
 * @author Victor
 *
 */

public class Moveable 
extends WorldObject 
{
	
	protected Vector2D velocity;
	protected Vector2D velocity_diff = new Vector2D(0,0);
	

	/**
	 * Constructor
	 */
	public Moveable(double xSpeed, double ySpeed, double xPos, double yPos, double mass, double radius){
		super(xPos, yPos, mass, radius);
		velocity = new Vector2D(xSpeed, ySpeed);
	}
	
	
	/**
	 * Constructor
	 */
	public Moveable (Vector2D velocity, Vector2D position, double mass, double radius) {
		super(position, mass, radius);
		this.velocity = velocity;
	}
	
	
	/**
	 * Move this Floater and notify observers
	 * 
	 * @param dt - delta time
	 * @return void
	 */
	public void move(){
		velocity = velocity.add(velocity_diff);
		velocity_diff = new Vector2D(0,0);
		position = position.add(velocity);
		update();
	}
	
	
	/**
	 * Accelerates this using some force.
	 * @param force
	 */
	public void accelerate(Vector2D force) {
		velocity_diff = velocity_diff.add(force.div(mass));
	}
	
	
	/**
	 * Return this objects velocity vector.
	 * @return Vector2D the velocity vector.
	 */
	public Vector2D getVelocity(){
		return velocity;
	}
}
