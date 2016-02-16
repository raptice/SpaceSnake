package model;
import java.util.*;
public class WorldCollection {
	private ArrayList<WorldObject> data;
	
	public WorldCollection(){
		data = new ArrayList<>();
	}
	public ArrayList<WorldObject> getCollection(){
		return data;
	}
	public void add(WorldObject obj){/*Add an worldobject here*/ }
	public boolean addcheck(WorldObject obj){/* Check if position is free for requested object*/ return true;}
	public void delete(WorldObject obj){ /**/}
	public ArrayList<WorldObject> surrounding(WorldObject obj){
		return new ArrayList<WorldObject>();
	}

}
