
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

import model.*;
import model.objects.SnakeHead;
import model.objects.SnakeTail;
import model.objects.BlackHole;
import view.*;

import util.Command;

/**
 * Handles events from the GameView
 */
public class GameController implements ActionListener {
	private WorldCollection worldCollection;
	//private WorldObject worldObject;
	//private WorldObject worldObject2;
	//private WorldObject worldObject3;
	private PhysicsEngine physicsEngine;
	//private GameView gameView;
	//private MapView mapView;
	
	SnakeHead head;
	
	private static final double testValue = 1;
	private static final long longValue = 100;
	private static final int MAX_SPAWN = 10;
	private static final int MIN_SPAWN = 3;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameController(MainController parent){
		newGame();
	}
	
	/**
	 * Starts the thread where the physics run
	 */
	public void newGame () {
		worldCollection = new WorldCollection();
		physicsEngine = new PhysicsEngine(worldCollection, longValue);
		
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
	
	//TODO: Create snake, create randomized object
	//		fill world with objects
	public void createObjects() {
		ArrayList<WorldObject> gameObjects = new ArrayList<WorldObject>();
		
		//skapa en orm
				head = new SnakeHead(-2,2,20,100,10,20);
				SnakeTail tail = new SnakeTail(-1,0,-30,100,5,20);

				head.addTail(tail);
				SnakeTail tail2 = new SnakeTail(-4,2,-70,100,5,20);
				tail.addTail(tail2);
				gameObjects.add(head);
				gameObjects.add(tail);
				gameObjects.add(tail2);
		
		//randomSpawns();
		/*worldObject = new Floater(0, 0, testValue-100, testValue, testValue+100, testValue);
		gameObjects.add(worldObject);

		gameObjects.add(worldObject2);
		SnakeHead head = new SnakeHead(0,0,20,100,100,100);
		SnakeTail tail = new SnakeTail(0,0,-20,100,100,100);*/

		//worldObject = new Floater(0, 0, testValue-100, testValue, 100, 50);
		//worldObject2 = new Floater(0, 0, testValue+100, testValue, 100, 50);
		//gameObjects.add(worldObject);
		//gameObjects.add(worldObject2);

		Map<String,Integer> spawn = randomSpawns();
		
		//add spawns in gameObjectslist
		//addToWorld(gameObjects, randomSpawns());
		addToWorld(gameObjects, spawn );
		
		for (WorldObject worldObject: gameObjects) {
			worldCollection.add(worldObject);
		}
	}
	
	public Map<String,Integer> randomSpawns() {		
		Map<String,Integer> spawns = new HashMap<String,Integer>();
		Random random = new Random();
		int totalObjects = random.nextInt((MAX_SPAWN - MIN_SPAWN) + 1)+ MIN_SPAWN;
		//loop logic here, this is for test purposes only
			int edible = 6;
			int blackHole = 2;
			int mobs = totalObjects - edible + blackHole;
			
			spawns.put("Edible",edible);
			spawns.put("BlackHole",blackHole);
			spawns.put("Mobs",mobs);
			System.out.println("total objects is: " +totalObjects);
			System.out.println(spawns);
		return spawns;
	}
	
	public void addToWorld(ArrayList<WorldObject> gameObjects, Map<String,Integer> spawn){
		//this method has lolcode and testvalues, ignore!
		double more = 1; 
		double x = 0;
		double y = 0;
		if( spawn.containsKey("BlackHole") ){
				for(int i =0; i< spawn.get("BlackHole"); i++){
					more = more + i; 
					x = more++ ;
					y = more++;
					double z = testValue-100+more++;
					double u = testValue;
					//double v = 100- more++;
					//double o = 50-more++;
					
					//gameObjects.add( new Floater(x,y,z,u,v,o) );
					gameObjects.add( new BlackHole(x,y,z,u) );
					//System.out.println("made an object");
					//System.out.println(spawn.get("BlackHole"));
					//System.out.println(gameObjects);
				}
			}
	}
	
	/**
	 * Handles events in the game
	 * @param e		The ActionEvent sent from a mouse press
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Command.MOUSE_PRESSED) {
			System.out.println("GameViewController: Mouse pressed");
			//head.startAccelerating();
		}
		else if (e.getActionCommand() == Command.MOUSE_RELEASED) {
			System.out.println("GameViewController: Mouse released");
			//head.stopAccelerating();
		}
		else if (e.getActionCommand() == Command.MOUSE_DRAGGED) {
			System.out.println("GameViewController: Mouse dragged");
			//head.changeAccelerationDirection();
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
