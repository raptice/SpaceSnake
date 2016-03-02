package model.objects;

import java.io.Serializable;

import model.Moveable;
import model.WorldCollection;
import util.Vector2D;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class Edible 
extends Moveable
implements Serializable
{

	/**
	 * Change this value if any change is made to any fields.
	 */
	private static final long serialVersionUID = 1L;
	
	public Edible(WorldCollection world, Vector2D velocity, Vector2D position, double mass, double radius) {
		super(world, velocity, position, mass, radius);
	}
}
