
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.*;
import model.objects.BlackHole;
import model.objects.Edible;
import model.objects.SnakeHead;
import model.objects.SnakePart;
import model.objects.SnakeTail;
import view.*;

import util.GameEvent;
import util.Vector2D;

/**
 * Handles events from the GameView
 */
public class GameController implements ActionListener {
	private WorldCollection worldCollection;
	private PhysicsEngine physicsEngine;
	private GameView gameView;
	private MapView mapView;
	
	SnakeHead head;

	private static final int MAX_SPAWN = 10;
	private static final int MIN_SPAWN = 3;

	private double new_Tail_radius = 10;
	private double new_Tail_mass = 10;

	private static final double testValue = 1;
	private static final long longValue = 50;
	
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
		physicsEngine = new PhysicsEngine(worldCollection, 1, longValue);
		worldCollection.setWorldSize(randomWorldSize());
		
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
	
	//TODO: Create randomized object
	//		fill world with objects
	public void createObjects() {
		ArrayList<WorldObject> gameObjects = new ArrayList<WorldObject>();
		
		head = new SnakeHead(1,-7,20,100,10,20, this);
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
	
	public Map<String,Integer> randomSpawns() {		
		Map<String,Integer> spawns = new HashMap<String,Integer>();
		Random random = new Random();
		
		int totalObjects = 0; /*random.nextInt((MAX_SPAWN - MIN_SPAWN) + 1)+ MIN_SPAWN;*/
		//loop logic here, this is for test purposes only
			int floater = 1;
			int edible = 1;
			int blackHole = 0;
			int mobs = totalObjects - edible + blackHole;
			
			spawns.put("Floater",floater);
			spawns.put("Edible",edible);
			spawns.put("BlackHole",blackHole);
			spawns.put("Mobs",mobs);
			//System.out.println("total objects is: " +totalObjects);
			System.out.println(spawns);
		
		return spawns;
	}
	
	public void addToWorld(ArrayList<WorldObject> gameObjects, Map<String,Integer> spawn){
		double more = 1; 
		double x = 0;
		double y = 0;
		if( spawn.containsKey("Floater") ){
			for(int i=0; i< spawn.get("Floater"); i++){
				more = more + i; 
				//x = more++ ;
				//y = more++;
				double z = testValue-100+more++;
				double u = testValue;
				double v = 100- more++;
				double o = 50-more++;
					
				gameObjects.add( new Floater(x,y,z,u,v,o) );
			}
		}
		if( spawn.containsKey("BlackHole") ){
			for(int i=0; i< spawn.get("BlackHole"); i++){
				more = more + i; 
				//x = more++ ;
				//y = more++;
				double z = testValue-100+more++;
				double u = testValue;
					
				gameObjects.add( new BlackHole(x,y,z,u) );
			}
		}
		if( spawn.containsKey("Edible") ){
			for(int i=0; i< spawn.get("Edible"); i++){
				gameObjects.add( new Edible(new Vector2D(0,0), new Vector2D(0,0),10,10) );
			}
		}
	}
	
	public int randomWorldSize() {
		Random random = new Random();
		
		//storlek bana -> ett tal som är slumpat mellan olika värden
		int worldSize = random.nextInt(1000) + 500;
		
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
			head.accelerate(e.getVector().div(100));
			//Maybe should set something in the physicsengine that released unsets?
			//head.startAccelerating();
		}
		else if (e.getActionCommand() == GameEvent.MOUSE_RELEASED) {
			//System.out.println("GameController: Mouse released: "+e.getVector());
			//head.stopAccelerating();
		}
		else if (e.getActionCommand() == GameEvent.MOUSE_DRAGGED) {
			//System.out.println("GameController: Mouse dragged: "+e.getVector());
			head.accelerate(e.getVector().div(100));
			//head.changeAccelerationDirection();
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
	
	/**
	 * Method called from the snakehead upon collision that determines what should happen. 
	 * Returns true if it should be a normal collision.
	 * @param what
	 * @return	true if it should be a normal collision
	 */
	public boolean snakeCollision (WorldObject what) {
		if (what instanceof Edible)
		{
			
			//Do stuff here:
			
			//Find the last and second last part of the snake
			SnakePart last = head;
			SnakePart second_last = head;
			while (last.getTail() != null)
			{
				second_last = last;
				last = last.getTail();
			}
			
			//Build a tailpart
			SnakeTail tail;
			if (last.equals(head))
				tail = new SnakeTail(head.getVelocity(), 
						head.getPosition().sub(head.getVelocity().normalize().scale(head.getRadius()+new_Tail_radius)),
						new_Tail_radius,new_Tail_mass);
			else
				tail = new SnakeTail(
						last.getVelocity(), 
						last.getPosition().add(last.getPosition().sub(second_last.getPosition()).normalize().scale(last.getRadius()+new_Tail_radius)),  
						new_Tail_radius,new_Tail_mass);
			
			// Add the tailpart
			last.addTail(tail);
			worldCollection.add(tail);
			
			//kill the edible object
			what.kill();
			worldCollection.remove(what);
			
			//Get points?
			//other actions?
			
			return false;
		}
		else
		{
			return true;
		}
	}
}
