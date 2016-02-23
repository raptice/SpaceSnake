package model;
import java.util.*;
public class WorldCollection extends Observable{
	private ArrayList<WorldObject> data;
	
	public WorldCollection(){
		data = new ArrayList<>();
	}
	public ArrayList<WorldObject> getCollection(){
		return data;
	}
	public void add(WorldObject obj){
		if(addcheck(obj)){
			data.add(obj);
			update(obj);
		}
	}
	
	/**
	 * Removes an object from the world
	 * @param obj the object that gets removed
	 */
	public void remove(WorldObject obj) {
		data.remove(obj);
	}
	
	
	public boolean addcheck(WorldObject obj){/* Check if position is free for requested object*/ return true;}
	public void delete(WorldObject obj){ 
		
	}
	public ArrayList<WorldObject> surrounding(WorldObject obj){
		return new ArrayList<WorldObject>();
	}
	public void update(WorldObject obj){
		setChanged();
		notifyObservers(obj);
	}

}
