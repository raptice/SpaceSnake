package model;
import util.*;
public class WorldObject {
	private Vector2D position;
	private double mass;
	public WorldObject(double xPos, double yPos, double mass){
		this.position.setX(xPos);
		this.position.setY(yPos);
		this.mass= mass;
	}
}
