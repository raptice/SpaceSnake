package model;
import java.io.Serializable;
import java.util.*;

import model.objects.Edible;
import model.objects.SnakeHead;

/**
 * WorldCollection stores the WorldObjects and has appropriate methods to manage the
 * list of of WorldObjects
 * 
 * @author Victor
 * @version 1.0.0.0
 */
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
	
	
	/**
	 * Constructor for WorldCollection class
	 */
	public WorldCollection(){
		data = new ArrayList<>();
	}
	
	
	/**
	 * Returns the ArrayList of WorldObjects for this WorldCollection
	 * 
	 * @return data - The ArrayList of WorldObjects
	 */
	public ArrayList<WorldObject> getCollection(){
		return data;
	}
	
	
	/**
	 * Sets the worldsize to a certain value.
	 * 
	 * @param worldSize - The worldsize.
	 * @return void
	 */
	public void setWorldSize(double worldSize) {
		this.worldSize = worldSize;
		//Needs to notify the view and physics engine
	}
	
	
	/**
	 * Returns the worldsize.
	 * 
	 * @return worldSize - The size of the world.
	 */
	public double getWorldSize() {
		return worldSize;
	}
	
	
	/**
	 * Adds a given WorldObject to the WorldCollection.
	 * 
	 * @param obj - The WorldObject to be added.
	 * @return void
	 */
	public void add(WorldObject obj){
		data.add(obj);
		update(obj);
	}
	
	/**
	 * Removes an object from the WorldCollection
	 * 
	 * @param obj The object to be removed.
	 * @return void
	 */
	public void remove(WorldObject obj) {
		data.remove(obj);
	}
	
	
	/**
	 * Deletes given object
	 * 
	 * @param obj WorldObject to be removed
	 * @return void
	 */
	public void delete(WorldObject obj){ 
		if (obj instanceof SnakeHead) {
			gameover();
		} else if (obj instanceof Edible) {
			this.remove(obj);
			newEdible();
		}
	}
	
	
	/**
	 * Update the Observs that the WorldCollection has been changed.
	 * @param obj - That has been changed somehow.
	 * @return void
	 */
	public void update(WorldObject obj){
		setChanged();
		notifyObservers(obj);
	}
	
	/**
	 * Update the observers that the game is over
	 * @return void
	 */
	public void gameover() {
		setChanged();
		notifyObservers(new String("GAMEOVER"));
	}
	
	/**
	 * Update the observers of a new Edible.
	 * @return void
	 */
	public void newEdible() {
		setChanged();
		notifyObservers(new String("NEWEDIBLE"));
	}
}
