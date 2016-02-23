package model;

import java.util.ArrayList;

/**
 * Interface with methods representing generic gravitational functionality.
 * @author Victor
 *
 */
public interface IGravity {
	
	/**
	 * To get the gravity constant for this object
	 * @return
	 */
	public double getGravity();
	
	
	/**
	 * Calculate the gravitational force on movable objects among the WorldObjects.
	 * 
	 * @param Affecting WorldObject
	 * @return	void
	 */
	public void gravityPull(ArrayList<WorldObject> data);
}
