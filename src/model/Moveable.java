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
	private double collision_damping = 0.99;
	

	/**
	 * Constructor
	 */
	public Moveable(double xSpeed, double ySpeed, double xPos, double yPos, double mass, double radius){
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
	}
	
	
	/**
	 * Constructor
	 */
	public Moveable (Vector2D velocity, Vector2D position, double mass, double radius) {
		super(position, mass, radius);
		this.velocity = velocity;
		collision_damping = Double.parseDouble(Config.get("Collision_damping"));
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
	
	public void collisions (WorldCollection data) {
		for(WorldObject obj : data.getCollection()){
			if(!obj.equals(this) && collides(obj))
			{
				collide(obj);
			}
		}
	}
	
	protected boolean collides(WorldObject other) 
	{
		double r = radius+other.getRadius();
		return r*r > position.sub(other.getPosition()).lengthsquared();
	}
	
	protected void collide (WorldObject other)
	{
		if (other instanceof Moveable) 
		{
			Moveable moveable = (Moveable) other;
			double dot = velocity.sub(moveable.getVelocity()).dot(position.sub(moveable.getPosition()));
			Vector2D dv = position.sub(moveable.getPosition());
			dv = dv.scale(  dot/position.sub(moveable.getPosition()).lengthsquared());
			dv = dv.div(mass+moveable.getMass());
			velocity_diff = velocity_diff.sub(dv.scale(collision_damping*2*moveable.getMass()));
		} else {
			double dot = velocity.dot(position.sub(other.getPosition()));
			Vector2D dv = position.sub(other.getPosition());
			dv = dv.scale(  dot/position.sub(other.getPosition()).lengthsquared());
			velocity_diff = velocity_diff.sub(dv.scale(collision_damping*2));
		}
	}
}
