package model;

import util.Vector2D;

public class BufferedWorldObject implements Gravity {
	private Gravity decoObj;
	
	public BufferedWorldObject(Gravity decoObj){
		this.decoObj = decoObj;
	}
	@Override
	public Vector2D getVelocity() { return decoObj.getVelocity(); }
	@Override
	public double getGravity() { return decoObj.getGravity(); }
	@Override
	public Vector2D calcuateGravity(WorldObject arg) { return decoObj.calcuateGravity(arg); }
	@Override
	public void gravityPull(WorldObject arg) { decoObj.gravityPull(arg); }
	//.......................Add common methods for decorated objects here.......................
		
	}