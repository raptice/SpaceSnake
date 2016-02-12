package modell;

public class Floater extends Vector2D implements Gravity{
	private Vector2D position;
	
	public Floater(double xSpeed, double ySpeed, double xPos, double yPos){
		super(xSpeed, ySpeed);
		this.position.setX(xPos);
		this.position.setY(yPos);
	}
}
