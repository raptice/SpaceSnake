package defaultpackage;
import java.util.*;

public class posVector {
	private float xPosition, yPosition;
	private float xSpeed, ySpeed;
	
public posVector(float speed, float angleDegree, float xPosition, float yPosition){
		this.xSpeed = (float)(speed * Math.cos(Math.toRadians(angleDegree)));
		this.ySpeed = (float)(-speed * Math.sin(Math.toRadians(angleDegree)));
		this.xPosition = xPosition;
		this.yPosition = yPosition;
}
/*public posVector(float xSpeed, float ySpeed, float xPosition, float yPosition){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
}*/
public float getSpeedMagnitude(){
	return (int)Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
}
public float getAngleDegree(){
	return (float)Math.toDegrees(Math.atan2(-ySpeed, xSpeed));
}
public void addVector(Vector vector1){
	this.xSpeed += vector1.xSpeed;
	this.ySpeed += vector1.xSpeed;
}
public void subVector(Vector vector1){
	this.xSpeed -= vector1.xSpeed;
	this.ySpeed -= vector1.ySpeed;
}
public 






}
