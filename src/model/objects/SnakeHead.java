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

	private WorldCollection theWorld;
	
	private double new_Tail_radius = 10;
	private double new_Tail_mass = 10;

	
	public SnakeHead(double xSpeed, double ySpeed, double xPos, double yPos,
			double mass, double radius, WorldCollection world) {
		this(new Vector2D(xSpeed, ySpeed), new Vector2D(xPos, yPos), mass, radius);
		this.theWorld = world;
	}
	
	public SnakeHead(Vector2D velocity, Vector2D position, double mass, double radius) {
		super(velocity, position, mass, radius);
	}
	
	
	/**
	 * Overrides function in SnakePart to only make collisions happen if the controller lets it
	 */
	@Override
	public void collision (WorldObject obj) {
		if (obj instanceof Edible)
		{
			eat((Edible)obj);
		} else 
			super.collision(obj);
	}
	
	
	private void eat(Edible what)
	{
		//Find the last and second last part of the snake
		SnakePart last = this;
		SnakePart second_last = this;
		while (last.getTail() != null)
		{
			second_last = last;
			last = last.getTail();
		}
		
		//Build a tailpart
		SnakeTail tail;
		if (last.equals(this))
			tail = new SnakeTail(this.getVelocity(), 
					this.getPosition().sub(this.getVelocity().normalize().scale(this.getRadius()+new_Tail_radius)),
					new_Tail_radius,new_Tail_mass);
		else
			tail = new SnakeTail(
					last.getVelocity(), 
					last.getPosition().add(last.getPosition().sub(second_last.getPosition()).normalize().scale(last.getRadius()+new_Tail_radius)),  
					new_Tail_radius,new_Tail_mass);
		
		// Add the tailpart
		last.addTail(tail);
		theWorld.add(tail);
		
		//kill the edible object
		what.kill();
		theWorld.remove(what);
		
		//Get points?
		//other actions?
	}

	
}
