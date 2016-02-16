
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;

import util.Command;

/**
 * Handles events from the GameView
 */
public class GameController implements ActionListener {
	private MainController parent;
	private WorldCollection worldCollection;
	private WorldObject worldObject;
	private PhysicsEngine physicsEngine;
	
	private static final double testValue = 1;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameController(MainController parent){
		this.parent = parent;
		
		worldCollection = new WorldCollection();
		physicsEngine = new PhysicsEngine(worldCollection, testValue);
	}
	
	public void createObject() {
		worldObject = new BlackHole(testValue, testValue, testValue);
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
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
}
