package model;
import java.io.Serializable;
import java.util.*;

import model.objects.Edible;
import model.objects.SnakeHead;


public class WorldCollection 
extends Observable
implements Serializable
{

	/**
	 * Change this value if any change is made to any fields.
	 */
	private static final long serialVersionUID = 1L;
	

	private ArrayList<WorldObject> data;
	private double worldSize;
	
	public WorldCollection(){
		data = new ArrayList<>();
	}
	
	public ArrayList<WorldObject> getCollection(){
		return data;
	}
	
	public void setWorldSize(double worldSize) {
		this.worldSize = worldSize;
		//Needs to notify the view and physics engine
	}
	
	public double getWorldSize() {
		return worldSize;
	}
	
	public void add(WorldObject obj){
		data.add(obj);
		update(obj);
	}
	
	/**
	 * Removes an object from the world
	 * @param obj the object that gets removed
	 */
	public void remove(WorldObject obj) {
		data.remove(obj);
	}
	
	public void delete(WorldObject obj){ 
		if (obj instanceof SnakeHead) {
			gameover();
		} else if (obj instanceof Edible) {
			this.remove(obj);
			newEdible();
		}
	}
	public ArrayList<WorldObject> surrounding(WorldObject obj){
		return new ArrayList<WorldObject>();
	}
	public void update(WorldObject obj){
		setChanged();
		notifyObservers(obj);
	}
	public void gameover() {
		setChanged();
		notifyObservers(new String("GAMEOVER"));
	}
	public void newEdible() {
		setChanged();
		notifyObservers(new String("NEWEDIBLE"));
	}
}
