package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.Floater;
import model.WorldCollection;
import model.WorldObject;
import model.objects.BlackHole;
import model.objects.Edible;
import model.objects.SnakeHead;
import model.objects.SnakeTail;
import util.Vector2D;

public class WorldFactory {
	
	private static final int MAX_SPAWN = 50;
	private static final int MIN_SPAWN = 20;
	
	SnakeHead head;
	GameController parent;
	WorldCollection worldCollection;
	
	public WorldFactory(GameController parent, WorldCollection worldCollection) {
		this.parent = parent;
		this.worldCollection = worldCollection;
		createObjects();
	}
	
	/**
	 * Creates a snake and adds objects to the world
	 * TODO: 	Probably move snake creation to a new separate method
	 * 			so this one only has the responsibility of adding 
	 * 			everything to the world
	 */
	public void createObjects() {
		ArrayList<WorldObject> gameObjects = new ArrayList<WorldObject>();
		
		head = new SnakeHead(worldCollection, new Vector2D(1,-7), new Vector2D(20,100),10,20);
		SnakeTail tail = new SnakeTail(worldCollection, new Vector2D(0,0),new Vector2D(-30,100),5,15);
		SnakeTail tail2 = new SnakeTail(worldCollection, new Vector2D(0,2),new Vector2D(-70,100),5,15);
		
		head.addTail(tail);
		tail.addTail(tail2);
		
		gameObjects.add(head);
		gameObjects.add(tail);
		gameObjects.add(tail2);
		
		addToWorld(gameObjects, randomSpawns());
		
		for (WorldObject worldObject: gameObjects) {
			worldCollection.add(worldObject);
		}
	}
	
	/**
	 * Randomizes how many objects that should be creates and of which type
	 * @return spawns	A hashmap of types of objects and how many of them are to be created
	 * TODO: Randomize how many objects are to be created of different types
	 */
	public Map<String,Integer> randomSpawns() {		
		Map<String,Integer> spawns = new HashMap<String,Integer>();
		Random random = new Random();
		//int size = worldCollection.getWorldSize();
		int totalObjects = random.nextInt(MAX_SPAWN)+ MIN_SPAWN;
			
			int floater = totalObjects/3;
			int edible = totalObjects - floater;
			int blackHole = totalObjects /4;
		 
			spawns.put("Floater",floater);
			spawns.put("Edible",edible);
			spawns.put("BlackHole",blackHole);

			System.out.println(spawns);
		
		return spawns;
	}
	
	/**
	 * Creates objects with randomized coordinates that are added to an arraylist
	 * @param pos	Vector with x and y coordinates that are to be checked
	 * TODO: Move randomization of position to a new separate method randomizePosition() 
	 * 		that returns a Vector2D of random position, which also checks if it's in the map
	 */
	public void addToWorld(ArrayList<WorldObject> gameObjects, Map<String,Integer> spawn){
		Vector2D speed;
		Vector2D pos;
		double mass;
		double radius;
		
		if( spawn.containsKey("Floater") ){
			for(int i=0; i< spawn.get("Floater"); i++){
				speed = randomSpeed();
				pos = randomPosition();
				mass = 100;
				radius = 50;
				
				gameObjects.add( new Floater(worldCollection, speed, pos, mass, radius) );
			}
		}
		if( spawn.containsKey("BlackHole") ){
			for(int i=0; i< spawn.get("BlackHole"); i++){
				pos = randomPosition();
				mass = 100;
				radius = 50;
				
				gameObjects.add( new BlackHole(worldCollection, pos, mass, radius) );
			}
		}
		if( spawn.containsKey("Edible") ){
			for(int i=0; i< spawn.get("Edible"); i++){
				speed = randomSpeed();
				pos = randomPosition();
				mass = 10;
				radius = 10;

				gameObjects.add( new Edible(worldCollection, speed, pos, mass, radius) );
			}
		}
	}
	
	public Vector2D randomPosition() {
		Vector2D pos = new Vector2D(Math.random() * worldCollection.getWorldSize() - worldCollection.getWorldSize()/2, Math.random() * worldCollection.getWorldSize() - worldCollection.getWorldSize()/2);
		while ( !isInsideWorld(pos) ) {
			pos = new Vector2D(Math.random() * worldCollection.getWorldSize() - worldCollection.getWorldSize()/2, Math.random() * worldCollection.getWorldSize() - worldCollection.getWorldSize()/2);
		}
		return pos;
	}
	
	public Vector2D randomSpeed() {
		double speedMultiplier = 30;
		Vector2D speed = new Vector2D((Math.random() * speedMultiplier) - speedMultiplier/2, (Math.random() * speedMultiplier) - speedMultiplier/2);
		return speed;
	}
	
	/**
	 * Checks if a coordinates is inside the map
	 * @param pos	Vector with x and y coordinates that are to be checked
	 * @return 	true if coordinates are inside of the map
	 * 			false if coordinates are outside of the map
	 */
	public boolean isInsideWorld(Vector2D pos) {
		double worldRadius = worldCollection.getWorldSize()/2;
		if (pos.getX()*pos.getX() + pos.getY()*pos.getY() < worldRadius*worldRadius) {
			return true;
		}
		else
			return false;
	}
}
