package model;
import util.*;
/**
 * Floater class represents a generic floating object in space
 * having both gravity an velocity.
 * 
 * @author Model-team 
 * @version 1.0.0.0
 */
public class Floater extends Moveable implements IGravity{
	protected final static double GRAVITY=1;
	public Floater(double xSpeed, double ySpeed, double xPos, double yPos, double mass, double radius){
		super(xSpeed, ySpeed, xPos, yPos, mass, radius);
	}
	/**
	 * Adds gravitational force to this Floater velocity
	 * 
	 * @param Affecting WorldObject
	 * @return	void
	 */
	public void gravityPull(WorldCollection data) {
		for(WorldObject obj : data.getCollection()){
			if(!obj.equals(this) && obj instanceof Moveable)
				((Moveable)obj).accelerate(this.calcuateGravity(obj));
		}
	}
	/**
	 * Calculates the scaled gravitational vector between two WorldObjects.
	 * 
	 * @param WorldObject
	 * @return Vector2D the scaled gravity vector
	 */
	public Vector2D calcuateGravity(WorldObject arg) {
		Vector2D temp = Vector2D.diff(this.getPosition(), arg.getPosition());
		temp = temp.normalize();
		temp = temp.scale(mass*GRAVITY*arg.getMass());
		temp = temp.div(this.getPosition().distancesquared(arg.getPosition()));
		return temp;
	}
	/**
	 * Return this objects Gravity.
	 * 
	 * @return Double the gravity.
	 */
	public double getGravity() {
		return GRAVITY;
	}
	/**
	 * Return this objects velocity vector.
	 * @return Vector2D the velocity vector.
	 */
	public Vector2D getVelocity(){
		return velocity;
	}
}