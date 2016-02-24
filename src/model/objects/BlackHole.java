package model.objects;

import java.util.ArrayList;

import model.IGravity;
import model.Moveable;
import model.WorldCollection;
import model.WorldObject;
import util.Config;
import util.Vector2D;

/**
 * An non movable object with gravity
 * @author Gustav
 *
 */

public class BlackHole
extends WorldObject
implements IGravity
{

	private double gravity = 18;
	
	public BlackHole(double xPos, double yPos, double mass, double radius) {
		this(new Vector2D(xPos, yPos), mass, radius);
	}
	
	public BlackHole(Vector2D position, double mass, double radius) {
		super(position, mass, radius);
		gravity=Double.parseDouble(Config.get("Gravity_constant"));
	}

	@Override
	public double getGravity() {
		return gravity;
	}

	@Override
	public void gravityPull(ArrayList<WorldObject> data, double dT) {
		for(WorldObject obj : data){
			if(obj instanceof Moveable)
			{
				Vector2D distance = position.sub(obj.getPosition());
				double r = radius + obj.getRadius();
				if (r*r<distance.lengthsquared())
					((Moveable)obj).accelerate(distance.normalize().scale(gravity*mass*obj.getMass()/distance.lengthsquared()), dT);
			}
		}
	}


}
