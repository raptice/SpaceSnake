
package controller;

import java.lang.Math;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;
import util.GameEvent;
import util.Vector2D;

/**
 * Class that handles events from the GameView and methods for the PhysicsEngine
 * 
 * @author Ingrid, Micaela
 * @version 2016-02-28
 */

public class GameController 
implements ActionListener, Observer 
{
	private WorldCollection worldCollection;
	private PhysicsEngine physicsEngine;
	private WorldFactory worldFactory;
	
	private MainController parent;

	private static final long gameSpeed = 50;
	
	/**
	 * Constructor that adds a reference to the parent controller
	 * @param parent	The parent controller
	 */
	public GameController(MainController parent){
		this.parent = parent;
	}
	
	/**
	 * Creates a new game
	 */
	public void newGame () {
		worldCollection = new WorldCollection();
		worldCollection.addObserver(this);
		worldCollection.setWorldSize(randomWorldSize());
		physicsEngine = new PhysicsEngine(worldCollection, 1, gameSpeed);
		worldFactory = new WorldFactory(this, worldCollection);
	}
	
	/**
	 * Loads a game
	 */
	public void loadGame (WorldCollection theWorld) {
		worldCollection = theWorld;
		worldCollection.addObserver(this);
		physicsEngine = new PhysicsEngine(worldCollection, 1, gameSpeed);
	}
	
	/**
	 * Returns the world. Used in order to save a game.
	 * @return worldCollection	the world of the game
	 */
	public WorldCollection getWorldCollection() {
		return worldCollection;
	}
	
	/**
	 * Starts the thread where the physics run
	 */
	public void runPhysics () {
		physicsEngine.start();
	}
	
	/**
	 * Stops the thread where the physics run
	 */
	public void stopPhysics () {
		physicsEngine.interrupt();
	}
	
	/**
	 * Pauses the thread where the physics run
	 */
	public void pausePhysics() {
		physicsEngine.setPaused();
	}
	
	/**
	 * Resumes the thread where the physics run
	 */
	public void resumePhysics() {
		synchronized(physicsEngine) {
			physicsEngine.setResumed();
			physicsEngine.notifyAll();
		}
	}
	
	/**
	 * Randomizes the size of the map
	 * @return worldSize	Randomized double
	 */
	private double randomWorldSize() {
		return (Math.random() * 10000) + 5000;
	}
	
	/**
	 * Adds an observer to the world
	 * @param gameObserver	The observer that will be added
	 */
	public void addObserver(GameObserver gameObserver) {
		worldCollection.addObserver(gameObserver);
		gameObserver.addWorld(worldCollection);
	}	
	
	/**
	 * Handles mouse events in the game
	 * @param e_in		The ActionEvent sent from a mouse press
	 */
	public void actionPerformed(ActionEvent e_in) {
		GameEvent e = (GameEvent) e_in;
		if (e.getActionCommand() == GameEvent.MOUSE_PRESSED) {
			//System.out.println("GameController: Mouse pressed: "+e.getVector());
			this.physicsEngine.SnakePull(e.getVector().div(100));
			//Maybe should set something in the physicsengine that released unsets?
		}
		else if (e.getActionCommand() == GameEvent.MOUSE_RELEASED) {
			//System.out.println("GameController: Mouse released: "+e.getVector());
			this.physicsEngine.SnakePull(null);
		}
		else if (e.getActionCommand() == GameEvent.MOUSE_DRAGGED) {
			//System.out.println("GameController: Mouse dragged: "+e.getVector());
			this.physicsEngine.SnakePull(e.getVector().div(100));
		}
		else {
			System.out.println("GameViewController: Unknown button: " + e.paramString()); //debugging
		}
	}
	
	public void accelerate(Vector2D	acc) {
		physicsEngine.SnakePull(acc);
	}

	@Override
	public void update(Observable arg0, Object arg1) { //TODO: Ändra variabelnamn...
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
