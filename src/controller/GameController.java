
package controller;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import objects.SnakeHead;
import objects.SnakeTail;

import model.*;
import view.*;

import util.Command;

/**
 * Handles events from the GameView
 */
public class GameController implements ActionListener {
	private WorldCollection worldCollection;
	private WorldObject worldObject;
	private WorldObject worldObject2;
	private PhysicsEngine physicsEngine;
	private GameView gameView;
	private MapView mapView;
	
	private static final double testValue = 1;
	private static final long longValue = 100;
	
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
		createObject();
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
	public void createObject() {
		ArrayList<WorldObject> gameObjects = new ArrayList<WorldObject>();
		worldObject = new Floater(0, 0, testValue-100, testValue, testValue+10, 50);
		worldObject2 = new Floater(0, 0, testValue+100, testValue, testValue+10, 50);
		gameObjects.add(worldObject);
		//gameObjects.add(worldObject2);
		SnakeHead head = new SnakeHead(-2,2,20,100,100,20);
		SnakeTail tail = new SnakeTail(-5,0,-30,100,100,20);
		head.addTail(tail);
		SnakeTail tail2 = new SnakeTail(-4,2,-70,100,100,20);
		tail.addTail(tail2);
		gameObjects.add(head);
		gameObjects.add(tail);
		gameObjects.add(tail2);
		
		for (WorldObject worldObject: gameObjects) {
			worldCollection.add(worldObject);
		}
	}
	
	/**
	 * Handles events in the game
	 * @param e		The ActionEvent sent from a mouse press
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Command.MOUSE_PRESSED) {
			System.out.println("GameViewController: Mouse pressed");
			//startAccelerating();
		}
		else if (e.getActionCommand() == Command.MOUSE_RELEASED) {
			System.out.println("GameViewController: Mouse released");
			//stopAccelerating();
		}
		else if (e.getActionCommand() == Command.MOUSE_DRAGGED) {
			System.out.println("GameViewController: Mouse dragged");
			//changeAccelerationDirection();
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
