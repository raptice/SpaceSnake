package model;
import java.io.Serializable;
import java.util.ArrayList;

import util.*;
/**
 * Floater class represents a generic floating object in space
 * having both gravity an velocity.
 * 
 * @author Model-team 
 * @version 1.0.0.0
 */
public class Floater extends Moveable implements IGravity, Serializable{
	
	/**
	 * Change this value if any change is made to any fields.
	 */
	private static final long serialVersionUID = 1L;
	
	private double gravity=12;
	
	public Floater(WorldCollection world, Vector2D velocity, Vector2D position, double mass, double radius){
		super(world, velocity, position, mass, radius);
		gravity=Double.parseDouble(Config.get("Gravity_constant"));
	}
	
	
	/**
	 * Adds gravitational force to this Floater velocity
	 * 
	 * @param Affecting WorldObject
	 * @return	void
	 */
	public void gravityPull(ArrayList<WorldObject> data, double dT) {
		for(WorldObject obj : data){
			if(!obj.equals(this) && obj instanceof Moveable)
				((Moveable)obj).accelerate(this.calcuateGravity(obj), dT);
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
		double r = radius + arg.getRadius();
		if (r*r>temp.lengthsquared())
			return new Vector2D(0,0);
		temp = temp.normalize();
		temp = temp.scale(mass*gravity*arg.getMass());
		temp = temp.div(this.getPosition().distancesquared(arg.getPosition()));
		return temp;
	}
	/**
	 * Return this objects Gravity.
	 * 
	 * @return Double the gravity.
	 */
	public double getGravity() {
		return gravity;
	}
	/**
	 * Return this objects velocity vector.
	 * @return Vector2D the velocity vector.
	 */
	public Vector2D getVelocity(){
		return velocity;
	}
}