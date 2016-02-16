package model;
import util.*;
/**
 * Interface with methods representing generic gravitational functionality.
 * @author Victor
 *
 */
public interface Gravity {
	public double getGravity();
	public Vector2D calcuateGravity(WorldObject arg);
	public void gravityPull(WorldObject arg);
}
