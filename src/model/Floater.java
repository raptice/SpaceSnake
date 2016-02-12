package model;
import java.util.List;
import util.*;

public class Floater extends WorldObject implements Gravity{
	private Vector2D velocity;
	private double gravity;
	public Floater(double xSpeed, double ySpeed, double xPos, double yPos, double mass){
		super(xPos, yPos, mass);
		this.velocity.setX(xPos);
		this.velocity.setY(yPos);
	}
	@Override
	public Vector2D getGravity() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector2D calcuateGravity(Vector2D arg) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void gravityPull(List<WorldObject> argList) {
		// TODO Auto-generated method stub
		
	}
	
	
}