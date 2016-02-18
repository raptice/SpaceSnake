package model;
import java.util.Observable;

import util.*;

/**
 * WorldObject class represents any object contained within the WorldCollection class.
 * 
 * @author Model-team
 */
public abstract class WorldObject extends Observable{
	protected Vector2D position;
	protected double radius;
	protected double mass;
	
	public WorldObject(double xPos, double yPos, double mass, double radius){
		position = new Vector2D();
		this.position.setX(xPos);
		this.position.setY(yPos);
		this.mass = mass;
		this.radius = radius;
	}
	/**
	 * Return this objects mass.
	 * @return mass
	 */
	public double getMass(){
		return mass;
	}
	/**
	 * Return the Position vector of this object.
	 * @return Vector2D the position vector
	 */
	public Vector2D getPosition(){
		return position;
	}
	/**
	 * Return this objects radius.
	 * @return Double the radius
	 */
	public double getRadius(){
		return radius;
	}
	/**
	 * Return this objects diameter.
	 * @return Double the diameter
	 */
	public double getDiameter(){
		return radius*2;
	}
	/**
	 * Sets this object as changed and notify it's observers.
	 * @return void
	 */
	public void update(){
		setChanged();
		notifyObservers(position);
	}
}
