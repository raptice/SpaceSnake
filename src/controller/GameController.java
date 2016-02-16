
package controller;

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
	}
	
	public void createWorld() {
		physicsEngine = new PhysicsEngine(worldCollection, longValue);
	}
	
	public void addView(GameView gameView) {
		this.gameView = gameView;
		//gameView.addWorld(worldCollection);
	}
	
	//TODO: Create snake, create randomized object
	//fill world with objects
	public void createObject() {
		worldObject = new Floater(testValue, testValue, testValue, testValue, testValue);
		//for (worldCollection object: WorldObject)
		if(worldCollection.addcheck(worldObject)) {
			worldCollection.add(worldObject);
			
		}
		worldObject = null;
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
