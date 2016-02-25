package model;
import java.util.ArrayList;

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
	/*public Moveable(WorldCollection world, double xSpeed, double ySpeed, double xPos, double yPos, double mass, double radius){
		this(world, new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
	}*/
	
	
	/**
	 * Constructor
	 */
	public Moveable (WorldCollection world, Vector2D velocity, Vector2D position, double mass, double radius) {
		super(world, position, mass, radius);
		this.velocity = velocity;
		collision_damping = Double.parseDouble(Config.get("Collision_damping"));
	}
	
	
	/**
	 * Move this Floater and notify observers
	 * 
	 * @param dt - delta time
	 * @return void
	 */
	public void move(double dT){
		velocity = velocity.add(velocity_diff);	// V1 = V1 + A*dT
		velocity_diff = new Vector2D(0,0);
		position = position.add(velocity.scale(dT));		//P1=P1 + V * dT
		update();
	}
	
	
	/**
	 * Accelerates this using some force.
	 * @param force
	 */
	public void accelerate(Vector2D force, double dT) {
		velocity_diff = velocity_diff.add(force.div(mass).scale(dT));
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
	public void collisions(ArrayList<WorldObject> data){
		for(WorldObject obj : data){
			if(!this.equals(obj) && collides(obj)){
				collision(obj);
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
	protected void collision(WorldObject obj){
		if (obj instanceof Moveable) 
		{	
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
			double dot = -Math.abs(Vector2D.dot(v1minusv2, p1minusp2));
			double constant = (massScalar * dot)/dstsqr;
			Vector2D newV1 = p1minusp2.scale(constant*collision_damping);
			velocity_diff = velocity_diff.sub( newV1 );
			
			/*
			Moveable moveable = (Moveable) obj;
			double dot = velocity.sub(moveable.getVelocity()).dot(position.sub(moveable.getPosition()));
			Vector2D dv = position.sub(moveable.getPosition());
			dv = dv.scale(  dot/position.sub(moveable.getPosition()).lengthsquared());
			dv = dv.div(mass+moveable.getMass());
			return dv.scale(collision_damping*2*moveable.getMass());
			*/
			
		} else {
			double dot = velocity.dot(position.sub(obj.getPosition()));
			Vector2D dv = position.sub(obj.getPosition());
			dv = dv.scale(  dot/position.sub(obj.getPosition()).lengthsquared());
			velocity_diff = velocity_diff.sub( dv.scale(collision_damping*2) );
		}	
	}
	public void wallCollide(){
		double r = theWorld.getWorldSize()/2-this.getRadius();
		if(position.lengthsquared() > r*r){
			Vector2D temp = position.normalize();
			double projection = velocity.dot(temp);
			temp = temp.scale(projection);
			velocity_diff = velocity_diff.sub(temp);
			velocity_diff = velocity_diff.sub(temp);
		}
	}
	
}
