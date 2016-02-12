package modell;
public class posVector {
	private double xPosition, yPosition;		//Objektets position
	private double xSpeed, ySpeed;			//Hastighet och riktning
	
public posVector(double speed, double angleDegree, double xPosition, double yPosition){
		this.xSpeed = speed * Math.cos(Math.toRadians(angleDegree));
		this.ySpeed = -speed * Math.sin(Math.toRadians(angleDegree));
		this.xPosition = xPosition;
		this.yPosition = yPosition;
}
/*public posVector(double xSpeed, double ySpeed, double xPosition, double yPosition){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
}*/
//Hämta vektorns längd ( magnitud )
public double getSpeedMagnitude(){
	return Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
}
//Hämta vilken mellan x-planet och vekorn
public double getAngleDegree(){
	return Math.toDegrees(Math.atan2(-ySpeed, xSpeed));
}
public double scalar(posVector vector1){
	return (this.xSpeed*vector1.xSpeed + this.ySpeed*vector1.ySpeed);
}
//addera given inparameters vektor med denna vektorn
public void addVector(posVector vector1){
	this.xSpeed += vector1.xSpeed;
	this.ySpeed += vector1.ySpeed;
}
//subtrahera given inparameters vektor med denna vektorn
public void subVector(posVector vector1){
	this.xSpeed -= vector1.xSpeed;
	this.ySpeed -= vector1.ySpeed;
}
//Förflytta denna vektorns position (xPosition, yPosition) med (xSpeed, ySpeed) antal pixlar
public void moveVector(){
	xPosition += xSpeed;
	yPosition += ySpeed;
}
//Hämta enhetsvektorn för denna vektorn
public posVector normalize(){
	posVector returnvec = this.clone();
	if(this.getSpeedMagnitude() != 0){
		returnvec.xSpeed = this.xSpeed / this.getSpeedMagnitude();
		returnvec.ySpeed = this.ySpeed / this.getSpeedMagnitude();
	}
	return returnvec;
}
public double squaredistance(posVector vector1){
	double dX = this.xPosition - vector1.xPosition;
	double dY = this.yPosition - vector1.yPosition;
	return dX*dX+dY*dY;
}
public double distance(posVector vector1){
	return Math.sqrt(squaredistance(vector1));
}
public double getX(){
	return this.xSpeed;
}
public double getY(){
	return this.ySpeed;
}
@Override
public posVector clone(){
	try{
		return (posVector)super.clone();
	}
	catch(CloneNotSupportedException e){
		throw new InternalError();
	}
}
}
