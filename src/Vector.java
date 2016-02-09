package defaultpackage;
import java.util.*;

public class Vector implements Cloneable{
private float xSpeed, ySpeed;
private float xPosition, yPosition;
private static final double GRAVITY = 1;
private float mass;
public Vector(float speed, float angleDegree, float xPosition, float yPosition, float mass){
	this.xSpeed = (float)(speed * Math.cos(Math.toRadians(angleDegree)));
	this.ySpeed = (float)(-speed * Math.sin(Math.toRadians(angleDegree)));
	this.xPosition = xPosition;
	this.yPosition = yPosition;
	this.mass = mass;
}
public float getSpeedMagnitude(){
	return (int)Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
}
public float getAngleDegree(){
	return (float)Math.toDegrees(Math.atan2(-ySpeed, xSpeed));
}
//accelerate
public void addVector(Vector vector1){
	this.xSpeed += vector1.xSpeed;
	this.ySpeed += vector1.xSpeed;
}
//deaccelerate
public void subVector(Vector vector1){
	this.xSpeed -= vector1.xSpeed;
	this.ySpeed -= vector1.ySpeed;
}
//p += vdt
public void moveVector(){
	xPosition += xSpeed;
	yPosition += ySpeed;
}
//gravitationpull from close gravitational objects
public void gravPull(List<Vector> gravobjects){
	for(Vector vec : gravobjects){
		float xDist = this.xPosition-vec.xPosition;
		float yDist = this.yPosition-vec.yPosition;
		double dist = Math.sqrt(xDist*xDist + yDist+yDist);
		double accel = GRAVITY*this.mass*vec.mass / (dist*dist);
		this.xSpeed += accel*(this.xPosition/dist);
		this.ySpeed += accel*(this.yPosition/dist);
	}
}
public Vector normalize(){
	Vector returnvec = this.clone();
	if(this.getSpeedMagnitude() != 0){
		returnvec.xSpeed = this.xSpeed / this.getSpeedMagnitude();
		returnvec.ySpeed = this.ySpeed / this.getSpeedMagnitude();
	}
	return returnvec;
}
public float getXSpeed(){
	return this.xSpeed;
}
public float getYSpeed(){
	return this.ySpeed;
}
@Override
public Vector clone(){
	try{
		return (Vector)super.clone();
	}
	catch(CloneNotSupportedException e){
		throw new InternalError();
	}
}
}
