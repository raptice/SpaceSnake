
package controller;

import java.lang.Math;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.*;
import view.*;
import util.Config;
import util.GameEvent;

/**
 * Handles events from the GameView, creates a new world, creates objects and adds them to world...
 * TODO: Create new classes with more specified roles
 */
public class GameController 
implements ActionListener, Observer 
{
	private WorldCollection worldCollection;
	private PhysicsEngine physicsEngine;
	private WorldFactory worldFactory;
	
	MainController parent;


	private static final long longValue = 50;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameController(MainController parent){
		this.parent = parent;
	}
	
	/**
	 * Starts the thread where the physics run
	 */
	public void newGame () {
		worldCollection = new WorldCollection();
		worldCollection.addObserver(this);
		worldCollection.setWorldSize(randomWorldSize());
		physicsEngine = new PhysicsEngine(worldCollection, 1, longValue);
		worldFactory = new WorldFactory(this, worldCollection);
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
	
	/**
	 * Randomizes the size of the map
	 * @return worldSize	Randomized double to be used when creating a new map
	 */
	private double randomWorldSize() {
		return (Math.random() * 10000) + 5000;
	}
	
	public void addObserver(GameObserver gameObserver) {
		worldCollection.addObserver(gameObserver);
		gameObserver.addWorld(worldCollection);
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
				parent.setGameOver();
				pausePhysics(); //Bad!
			}
		}
	}
	
}
