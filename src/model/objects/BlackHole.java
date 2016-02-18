package model.objects;

import model.IGravity;
import model.Moveable;
import model.WorldCollection;
import model.WorldObject;
import util.Vector2D;

/**
 * An moveable object...
 * @author Gustav
 *
 */

public class BlackHole
extends WorldObject
implements IGravity
{

	private double gravity_constant = 1;
	
	public BlackHole(double xPos, double yPos, double mass, double radius) {
		this(new Vector2D(xPos, yPos), mass, radius);
	}
	
	public BlackHole(Vector2D position, double mass, double radius) {
		super(position, mass, radius);
	}

	@Override
	public double getGravity() {
		return gravity_constant;
	}

	@Override //Not needed
	public Vector2D calcuateGravity(WorldObject arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void gravityPull(WorldCollection data) {
		for(WorldObject obj : data.getCollection()){
			if(obj instanceof Moveable)
			{
				Vector2D distance = position.sub(obj.getPosition());
				((Moveable)obj).accelerate(distance.normalize().scale(gravity_constant*mass*obj.getMass()/distance.lengthsquared()));
			}
		}
	}


}
