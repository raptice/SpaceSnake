package model;
import java.util.Observable;

import util.*;

/**
 * WorldObject class represents any object contained within the WorldCollection class.
 * 
 * @author Model-team
 */
public  abstract class WorldObject extends Observable implements Gravity, Cloneable {
	protected Vector2D position;
	protected double mass;
	protected double radius;

	
	
	protected final static double GRAVITY=1; //Detta hör väl till världen man spelar i.
	
	
	
	public WorldObject(double xPos, double yPos, double mass){
		position = new Vector2D(xPos, yPos);
		this.mass= mass;
	}
	
	/**
	 * Calculates gravity vector between two WorldObjects.
	 * 
	 * @param WorldObject
	 * @return Scaled gravitational vector between this WorldObject and affecting WorldObject 
	 */
	public Vector2D calcuateGravity(WorldObject arg) {
		Vector2D temp = Vector2D.diff(this.getPosition(), arg.getPosition());
		temp = temp.normalize();
		temp = temp.scale(mass*GRAVITY*arg.getMass());
		temp = temp.div(this.getPosition().distancesquared(arg.getPosition()));
		return temp;
		
	}
	
	
	public void gravityPull(WorldCollection data) {
		
	}
	public double getGravity() {
		return GRAVITY;
	}
	public double getMass(){
		return mass;
	}
	public Vector2D getPosition(){
		return position;
	}
	public double getRadius(){
		return radius;
	}
	public double getDiameter(){
		return radius*2;
	}
	public void update(){
		setChanged();
		notifyObservers(position);
	}
	public void move(){}
	
}
