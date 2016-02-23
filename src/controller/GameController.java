
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.*;
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
	private WorldObject worldObject;
	private WorldObject worldObject2;
	private WorldObject worldObject3;
	private PhysicsEngine physicsEngine;
	private GameView gameView;
	private MapView mapView;
	
	SnakeHead head;
	
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
		
		//randomSpawns();
		/*worldObject = new Floater(0, 0, testValue-100, testValue, testValue+100, testValue);
		gameObjects.add(worldObject);

		gameObjects.add(worldObject2);
		SnakeHead head = new SnakeHead(0,0,20,100,100,100);
		SnakeTail tail = new SnakeTail(0,0,-20,100,100,100);*/

		worldObject = new Floater(0, -0.5, -100, 0, 300, 50);
		worldObject2 = new Floater(0, 1.5, +100, 0, 100, 30);
		gameObjects.add(worldObject);
		gameObjects.add(worldObject2);
		head = new SnakeHead(1,-7,20,100,10,20, this);
		SnakeTail tail = new SnakeTail(0,0,-30,100,5,15);

		head.addTail(tail);
		SnakeTail tail2 = new SnakeTail(0,2,-70,100,5,15);
		tail.addTail(tail2);
		gameObjects.add(head);
		gameObjects.add(tail);
		gameObjects.add(tail2);
		
		Edible eatme = new Edible(new Vector2D(0,0), new Vector2D(0,0),10,10);
		gameObjects.add(eatme);
		for (WorldObject worldObject: gameObjects) {
			worldCollection.add(worldObject);
		}
	}
	
	public ArrayList<Integer> randomSpawns() {		
		ArrayList<Integer> spawns = new ArrayList<Integer>();
		Random random = new Random();
		
		//antal objekt : fasta, rörliga, (ätbara)
		int totalObjects = random.nextInt(10) + 3;
		
		return spawns;
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
			SnakePart last = head;
			while (last.getTail() != null)
				last = last.getTail();
			SnakeTail tail = new SnakeTail(new Vector2D(0,0),new Vector2D(0,0),10,10);
			last.addTail(tail);
			worldCollection.add(tail);
			
			System.out.println("Hit an edible!");
			
			//Add extra tail and kill the edible object
			//Get points
			//other actions
			
			return false;
		}
		else
		{
			return true;
		}
	}
}
