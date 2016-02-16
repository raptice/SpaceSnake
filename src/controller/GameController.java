
package controller;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

import util.Command;

/**
 * Handles events from the GameView
 */
public class GameController implements ActionListener {
	private MainController parent;
	private WorldCollection worldCollection;
	private WorldObject worldObject;
	private WorldObject worldObject2;
	private PhysicsEngine physicsEngine;
	private GameView gameView;
	
	private static final double testValue = 1;
	private static final long longValue = 1;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameController(MainController parent){
		this.parent = parent;
		worldCollection = new WorldCollection();
		physicsEngine = new PhysicsEngine(worldCollection, longValue);
	}
	public PhysicsEngine getEngine(){ return physicsEngine;}
	public void addView(GameView gameView) {
		this.gameView = gameView;
		worldCollection.addObserver(gameView);
		gameView.addWorld(worldCollection);
	}
	
	//TODO: Create snake, create randomized object
	//fill world with objects
	public void createObject() {
		ArrayList<WorldObject> gameObjects = new ArrayList<WorldObject>();
		worldObject = new Floater(testValue, testValue, testValue, testValue, testValue+10000000);
		worldObject2 = new Floater(testValue, testValue, testValue+100, testValue, testValue+10000000);
		gameObjects.add(worldObject);
		gameObjects.add(worldObject2);
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
