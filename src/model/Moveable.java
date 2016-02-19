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

	/**
	 * Help method that checks collision between this Moveable object and the WorldCollection,
	 * if collisioncheck detects collision with another object it calls CollisionResponse() method
	 * and stores the collision vector in instancevariable velocity_diff.
	 * @param WorldCollection the rest of the world
	 * @return void f
	 */
	public void Collision(WorldCollection data){
		for(WorldObject obj : data.getCollection()){
			if(!this.equals(obj)){
				double length = Math.sqrt(this.getPosition().distancesquared(obj.getPosition()));
				double radlength = this.getRadius() + obj.getRadius();
				if(length < radlength && obj instanceof Moveable){
					velocity.setValues(CollisionResponse(obj));
				}
			}
		}
	}
	
	/**
	 * Check if this object collides with another
	 */
	protected boolean collides(WorldObject other) {
		double lengthsqr = this.position.sub(other.getPosition()).lengthsquared();
		double radlength = this.getRadius() + other.getRadius();
		return (lengthsqr < radlength*radlength);
	}
	
	/**
	 * Calculates the collisionvector as elastic between this object and the object it collides with.
	 * @param WorldObject the object this Moveable collides with
	 * @return Vector2D the collisionvector
	 */
	public Vector2D CollisionResponse(WorldObject obj){
		Vector2D v1 = this.getVelocity();
		Vector2D v2 = ((Moveable)obj).getVelocity();
		Vector2D p1 = this.getPosition();
		Vector2D p2 = ((Moveable)obj).getPosition();
		double m1 = this.getMass();
		double m2 = ((Moveable)obj).getMass();
		double massScalar = (2*m2)/(m1+m2);
		Vector2D p1minusp2 = p1.sub(p2);
		Vector2D v1minusv2 = v1.sub(v2);
		double dstsqr = p1minusp2.lengthsquared();
		double dot = Vector2D.dot(v1minusv2, p1minusp2);
		double constant = (massScalar * dot)/dstsqr;
		Vector2D newV1 = v1.sub(p1minusp2.scale(constant));
		newV1.setX(-newV1.getX());
		newV1.setY(-newV1.getY());
		return newV1; //the collision response: velocity_diff 
	}
}
