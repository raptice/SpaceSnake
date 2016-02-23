package model.objects;

import controller.GameController;
import model.WorldCollection;
import model.WorldObject;
import util.Vector2D;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class SnakeHead 
extends SnakePart
{

	private GameController controller;
	public SnakeHead(double xSpeed, double ySpeed, double xPos, double yPos,
			double mass, double radius, GameController controller) {
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
		this.controller = controller;
	}
	
	public SnakeHead(Vector2D velocity, Vector2D position, double mass, double radius) {
		super(velocity, position, mass, radius);
	}
	
	
	/**
	 * Overrides function in SnakePart to only make collisions happen if the controller lets it
	 */
	@Override
	public void collision (WorldObject obj) {
		if (controller.snakeCollision(obj)) 
			super.collision(obj);
	}
	
	
}
