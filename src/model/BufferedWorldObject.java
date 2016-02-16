package model;

import util.Vector2D;

public class BufferedWorldObject implements Gravity {
	private Gravity decoObj;
	
	public BufferedWorldObject(Gravity decoObj){
		this.decoObj = decoObj;
	}
	@Override
	public double getGravity() { return decoObj.getGravity(); }
	@Override
	public Vector2D calcuateGravity(WorldObject arg) { return decoObj.calcuateGravity(arg); }
	@Override
	public void gravityPull(WorldCollection data) { decoObj.gravityPull(data); }
	//.......................Add common methods for decorated objects here.......................
		
	}