package model;
import util.*;
/**
 * Floater class represents a generic floating object in space
 * having both gravity an velocity.
 * 
 * @author Model-team 
 * @version 1.0.0.0
 */
public class Floater extends WorldObject{
	protected Vector2D velocity;
	public Floater(double xSpeed, double ySpeed, double xPos, double yPos, double mass){
		super(xPos, yPos, mass);
		velocity = new Vector2D();
		this.velocity.setX(xSpeed);
		this.velocity.setY(ySpeed);
	}
	/**
	 * Adds gravitational force to this Floater velocity
	 * 
	 * @param Affecting WorldObject
	 * @return	void
	 */
	@Override
	public void gravityPull(WorldCollection data) {
		for(WorldObject obj : data.getCollection()){
			if(!obj.equals(this))
				velocity = velocity.add(this.calcuateGravity(obj));
		}
	}
	/**
	 * Move this Floater and notify observers
	 * 
	 * @param dt - delta time
	 * @return
	 */
	public void move(){
		position = position.add(velocity);
		update();
	}
	public Vector2D getVelocity(){
		return velocity;
	}
}