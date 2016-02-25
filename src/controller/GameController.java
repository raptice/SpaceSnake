
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.*;
import model.objects.BlackHole;
import model.objects.Edible;
import model.objects.SnakeHead;
import model.objects.SnakeTail;
import view.*;

import util.GameEvent;
import util.Vector2D;

/**
 * Handles events from the GameView, creates a new world, creates objects and adds them to world...
 * TODO: Create new classes with more specified roles
 */
public class GameController 
implements ActionListener, Observer 
{
	private WorldCollection worldCollection;
	private PhysicsEngine physicsEngine;
	
	SnakeHead head;
	MainController parent;

	private static final int MAX_SPAWN = 50;
	private static final int MIN_SPAWN = 20;

	private static final long longValue = 50;
	
	/**
	 * TODO: Do we need to create a new game here? 
	 * It will always create one when you press "New Game" in the start menu, 
	 * so it does this twice, thereby throwing away the first one?
	 */
	public GameController(MainController parent){
		newGame();
	}
	
	/**
	 * Starts the thread where the physics run
	 */
	public void newGame () {
		worldCollection = new WorldCollection();
		worldCollection.addObserver(this);
		worldCollection.setWorldSize(randomWorldSize());
		physicsEngine = new PhysicsEngine(worldCollection, 1, longValue);
		
		head = null;
		createObjects();
	}
	
	/**
	 * Starts the thread where the physics run
	 */
	public void runPhysics () {
		physicsEngine.start();
	}
	
	
	/**
	 * Pauses the thread where the physics run
	 */
	public void stopPhysics () {
		physicsEngine.interrupt();
	}
	
	public void pausePhysics() {
		physicsEngine.setPaused();
	}
	
	public void resumePhysics() {
		synchronized(physicsEngine) {
			physicsEngine.setResumed();
			physicsEngine.notifyAll();
		}
	}
	
	
	/*public void addView(GameView gameView) {
		this.gameView = gameView;
		this.mapView = mapView;
		worldCollection.addObserver(gameView);
		worldCollection.addObserver(mapView);
		gameView.addWorld(worldCollection);
	}*/
	public void addObserver(GameObserver gameObserver) {
		worldCollection.addObserver(gameObserver);
		gameObserver.addWorld(worldCollection);
	}

	/**
	 * Creates a snake and adds objects to the world
	 * TODO: 	Probably move snake creation to a new separate method
	 * 			so this one only has the responsibility of adding 
	 * 			everything to the world
	 */
	public void createObjects() {
		ArrayList<WorldObject> gameObjects = new ArrayList<WorldObject>();
		
		head = new SnakeHead(1,-7,20,100,10,20, worldCollection);
		SnakeTail tail = new SnakeTail(0,0,-30,100,5,15);
		SnakeTail tail2 = new SnakeTail(0,2,-70,100,5,15);
		
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
		int size = worldCollection.getWorldSize();
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
		Random randomPos = new Random();
		Vector2D speed;
		Vector2D pos;
		double mass;
		double radius;
		
		if( spawn.containsKey("Floater") ){
			for(int i=0; i< spawn.get("Floater"); i++){
				speed = new Vector2D(0,0);
				pos = new Vector2D(randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2,randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2);
				mass = 100;
				radius = 50;
				
				while ( !isInsideWorld(pos) ) {
					pos = new Vector2D(randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2,randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2);
				}
				gameObjects.add( new Floater(speed, pos, mass, radius) );
			}
		}
		if( spawn.containsKey("BlackHole") ){
			for(int i=0; i< spawn.get("BlackHole"); i++){
				pos = new Vector2D(randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2,randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2);
				mass = 100;
				radius = 50;
				
				while ( !isInsideWorld(pos) ) {
					pos = new Vector2D(randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2,randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2);
				}
				gameObjects.add( new BlackHole(pos, mass, radius) );
			}
		}
		if( spawn.containsKey("Edible") ){
			for(int i=0; i< spawn.get("Edible"); i++){
				speed = new Vector2D(0,0);
				pos = new Vector2D(randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2,randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2);
				mass = 10;
				radius = 10;
				
				while ( !isInsideWorld(pos) ) {
					pos = new Vector2D(randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2,randomPos.nextInt(worldCollection.getWorldSize()) - worldCollection.getWorldSize()/2);
				}
				gameObjects.add( new Edible(speed, pos, mass, radius) );
			}
		}
	}
	
	/**
	 * Checks if a coordinates is inside the map
	 * @param pos	Vector with x and y coordinates that are to be checked
	 * @return 	true if coordinates are inside of the map
	 * 			false if coordinates are outside of the map
	 */
	public boolean isInsideWorld(Vector2D pos) {
		int worldRadius = worldCollection.getWorldSize()/2;
		if (pos.getX()*pos.getX() + pos.getY()*pos.getY() < worldRadius*worldRadius) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Randomizes the size of the map
	 * @return worldSize	Randomized double to be used when creating a new map
	 * TODO: Change from int to double?
	 */
	public int randomWorldSize() {
		Random random = new Random();
		
		//storlek bana -> ett tal som är slumpat mellan olika värden
		int worldSize = random.nextInt(10000) + 5000;
		
		return worldSize;
	}
	
	/**
	 * Handles events in the game
	 * @param e		The ActionEvent sent from a mouse press
	 */
	public void actionPerformed(ActionEvent e_in) {
		GameEvent e = (GameEvent) e_in;
		if (e.getActionCommand() == GameEvent.MOUSE_PRESSED) {
			//System.out.println("GameController: Mouse pressed: "+e.getVector());
			this.physicsEngine.SnakePull(e.getVector().div(100));
			//Maybe should set something in the physicsengine that released unsets?
			//head.startAccelerating();
		}
		else if (e.getActionCommand() == GameEvent.MOUSE_RELEASED) {
			//System.out.println("GameController: Mouse released: "+e.getVector());
			//head.stopAccelerating();
			this.physicsEngine.SnakePull(null);
		}
		else if (e.getActionCommand() == GameEvent.MOUSE_DRAGGED) {
			//System.out.println("GameController: Mouse dragged: "+e.getVector());
			this.physicsEngine.SnakePull(e.getVector().div(100));
			//head.changeAccelerationDirection();
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof String)
		{	
			if (((String)arg1).equals("GAMEOVER"))
			{
				System.out.println("GAME OVER");
				parent.setGameOver();
				pausePhysics(); //Bad!
			}
		}
	}
	
}
