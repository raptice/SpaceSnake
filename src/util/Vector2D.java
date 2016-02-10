package util;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Class for all 2d-vector calculations.
 * 
 * @author Gustav
 */

public class Vector2D {
	public double x,y;
	
	/* ************************************************
	 *  Constructors
	 ************************************************ */
	/**
	 * Constructor that generates an (0,0)-vector
	 */
	public Vector2D () {
		x=0;
		y=0;
	}
	/**
	 * Constructor that sets the vector x- and y-values.
	 */
	public Vector2D (double x, double y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Constructor that sets the vector x- and y-values.
	 */
	public Vector2D (float x, float y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Constructor that sets the vector x- and y-values.
	 */
	public Vector2D (int x, int y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Constructor that sets the vector x- and y-values.
	 */
	public Vector2D (Integer x, Integer y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Constructor that sets the vector x- and y-values.
	 */
	public Vector2D (Dimension d) {
		this.x=d.getWidth();
		this.y=d.getHeight();
	}
	/**
	 * Constructor that sets the vector x- and y-values.
	 */
	public Vector2D (Point p) {
		this.x=p.getX();
		this.y=p.getY();
	}
	/**
	 * Constructor that copies another Vector2D.
	 */
	public Vector2D (Vector2D other) {
		this.x=other.getX();
		this.y=other.getY();
	}

	/* ************************************************
	 *  Boolean stuff
	 ************************************************ */
	/**
	 * Check if a vector equals another
	 */
	public boolean equals (Vector2D other) {
		return other.getX()==x && other.getY()==y;
	}
	/**
	 * Check if two vectors equals one another
	 */
	public boolean equals (Vector2D first, Vector2D second) {
		return first.getX()==second.getX() && first.getY()==second.getY();
	}
	/**
	 * Checks if this vector is colinear with an other.
	 */
	public boolean isColinear(Vector2D other) {
		return 	(x==0&&y==0&&other.getX()==0&&other.getY()==0)
				||
				(x>0==other.getX()>0)&&(x/y==other.getX()/other.getY());
	}
	/**
	 * Checks if two vectors are colinear with each other.
	 */
	public boolean isColinear(Vector2D first,Vector2D second) {
		return 	(first.getX()==0 && first.getY()==0 && second.getX()==0 && second.getY()==0)
				||
				(first.getX()>0==second.getX()>0)&&(first.getX()/first.getY()==second.getX()/second.getY());
	}
	/**
	 * Checks if this vector is orthogonal with an other.
	 */
	public boolean isOrthogonal(Vector2D other) {
		return 0==cross(other);
	}
	/**
	 * Checks if two vectors are orthogonal with each other.
	 */
	public boolean isOrthogonal(Vector2D first, Vector2D second) {
		return 0==cross(first, second);
	}
	
	/* ************************************************
	 *  Opposites
	 ************************************************ */
	/**
	 * Returns the opposite of this vector such that the sum is (0,0).
	 */
	public Vector2D opposite() {
		return new Vector2D(-x, -y);
	}
	/**
	 * Returns the opposite of a vector such that the sum is (0,0).
	 */
	public Vector2D opposite(Vector2D vector) {
		return new Vector2D(-vector.getX(), -vector.getY());
	}
	
	/* ************************************************
	 *  Get values
	 ************************************************ */
	/**
	 * Get the x-value.
	 * @return	the x-value
	 */
	public double x () {
		return x;
	}
	/**
	 * Get the y-value.
	 * @return	the y-value
	 */
	public double y () {
		return y;
	}
	/**
	 * Get the x-value.
	 * @return	the x-value
	 */
	public double getX () {
		return x;
	}
	/**
	 * Get the y-value.
	 * @return	the y-value
	 */
	public double getY () {
		return y;
	}
	
	/* ************************************************
	 *  Manipulation
	 ************************************************ */
	/**
	 * Set the values.
	 */
	public void setValues (Vector2D vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}
	/**
	 * Set the values.
	 */
	public void setValues (Dimension d) {
		this.x = d.getWidth();
		this.y = d.getHeight();
	}
	/**
	 * Set the values.
	 */
	public void setValues (Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	/**
	 * Set the x-value.
	 * @return	the new x-value
	 */
	public double setX (double x) {
		return this.x = x;
	}
	/**
	 * Set the y-value.
	 * @return	the new y-value
	 */
	public double setY (double y) {
		return this.y = y;
	}
	/**
	 * Set the x-value.
	 * @return	the new x-value
	 */
	public double setX (float x) {
		return this.x = x;
	}
	/**
	 * Set the y-value.
	 * @return	the new y-value
	 */
	public double setY (float y) {
		return this.y = y;
	}
	/**
	 * Set the x-value.
	 * @return	the new x-value
	 */
	public double setX (int x) {
		return this.x = x;
	}
	/**
	 * Set the y-value.
	 * @return	the new y-value
	 */
	public double setY (int y) {
		return this.y = y;
	}
	/**
	 * Set the x-value.
	 * @return	the new x-value
	 */
	public double setX (Integer x) {
		return this.x = x;
	}
	/**
	 * Set the y-value.
	 * @return	the new y-value
	 */
	public double setY (Integer y) {
		return this.y = y;
	}
	
	/* ************************************************
	 *  Addition
	 ************************************************ */
	/**
	 * Adds to the vector another vector.
	 * @param other		The vector that adds to this
	 * @return			The new vector
	 */
	public Vector2D add (Vector2D other) {
		return new Vector2D(this.x+other.getX(), this.y+other.getY());
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D add (double x, double y) {
		return new Vector2D(this.x+x, this.y+y);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D add (float x, float y) {
		return new Vector2D(this.x+x, this.y+y);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D add (int x, int y) {
		return new Vector2D(this.x+x, this.y+y);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D add (Integer x, Integer y) {
		return new Vector2D(this.x+x, this.y+y);
	}
	/**
	 * Adds two vectors together.
	 * @return			the sum of the two
	 */
	public Vector2D add (Vector2D first, Vector2D second) {
		return new Vector2D(first.getX()+second.getX(), first.getY()+second.getY());
	}
	
	/* ************************************************
	 *  Subtraction
	 ************************************************ */
	/**
	 * Adds to the vector another vector.
	 * @return			The new vector
	 */
	public Vector2D sub (Vector2D other) {
		this.x -= other.getX();
		this.y -= other.getY();
		return new Vector2D(this);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D sub (double x, double y) {
		return new Vector2D(this.x-x, this.y-y);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D sub (float x, float y) {
		return new Vector2D(this.x-x, this.y-y);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D sub (int x, int y) {
		return new Vector2D(this.x-x, this.y-y);
	}
	/**
	 * Adds to the vector some x and y.
	 * @return			The new vector
	 */
	public Vector2D sub (Integer x, Integer y) {
		return new Vector2D(this.x-x, this.y-y);
	}
	/**
	 * Calculate the difference of two vectors
	 * @return			The new vector
	 */
	public Vector2D sub (Vector2D first, Vector2D second) {
		return diff(first, second);
	}
	/**
	 * Calculate the difference of two vectors
	 * @return			the difference of the two
	 */
	public Vector2D diff (Vector2D first, Vector2D second) {
		return new Vector2D(first.getX()-second.getX(), first.getY()-second.getY());
	}
	
	/* ************************************************
	 *  Multiplication
	 ************************************************ */
	/**
	 * Scales a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D scale(double scalar) {
		return new Vector2D(this.x*scalar, this.y*scalar);
	}
	/**
	 * Scales a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D scale(float scalar) {
		return new Vector2D(this.x*scalar, this.y*scalar);
	}
	/**
	 * Scales a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D scale(int scalar) {
		return new Vector2D(this.x*scalar, this.y*scalar);
	}
	/**
	 * Scales a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D scale(Integer scalar) {
		return new Vector2D(this.x*scalar, this.y*scalar);
	}
	/**
	 * Scales a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D scale(Vector2D vector, double scalar) {
		return new Vector2D(vector.getX()*scalar, vector.getY()*scalar);
	}
	
	/* ************************************************
	 *  Division
	 ************************************************ */
	/**
	 * Divides a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D div(double scalar) {
		return new Vector2D(this.x/scalar, this.y/scalar);
	}
	/**
	 * Divides a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D div(float scalar) {
		return new Vector2D(this.x/scalar, this.y/scalar);
	}
	/**
	 * Divides a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D div(int scalar) {
		return new Vector2D(this.x/scalar, this.y/scalar);
	}
	/**
	 * Divides a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D div(Integer scalar) {
		return new Vector2D(this.x/scalar, this.y/scalar);
	}
	/**
	 * Divides a vector with a scalar
	 * @return			The new vector
	 */
	public Vector2D div(Vector2D vector, double scalar) {
		return new Vector2D(vector.getX()/scalar, vector.getY()/scalar);
	}
	
	/* ************************************************
	 *  Lengths
	 ************************************************ */
	/**
	 * normalizes a vector
	 */
	public Vector2D normalize() {
		return new Vector2D(this.scale(1/this.length()));
	}
	/**
	 * returns a normalized vector
	 */
	public Vector2D normalize(Vector2D vector) {
		return new Vector2D(vector.scale(1/vector.length()));
	}
	/**
	 * calculates the length of this vector
	 */
	public double length() {
		return Math.sqrt(this.lengthsquared());
	}
	/**
	 * Calculates the length squared of this vector.
	 */
	public double lengthsquared() {
		return this.x*this.x+this.y*this.y;
	}
	/**
	 * calculates the length of a vector
	 */
	public double length (Vector2D vector) {
		return Math.sqrt(vector.lengthsquared());
	}
	/**
	 * Calculates the length squared of a vector.
	 */
	public double lengthsquared(Vector2D vector) {
		return vector.getX()*vector.getX()+vector.getY()*vector.getY();
	}
	
	/* ************************************************
	 *  Vector calculus
	 ************************************************ */
	/**
	 * Calculate the scalar product of two vectors
	 */
	public double dot (Vector2D first, Vector2D second) {
		return first.getX()*second.getY()+first.getY()*second.getX();
	}
	/**
	 * Calculate the scalar product of this and another vector
	 */
	public double dot (Vector2D second) {
		return x*second.getY()+y*second.getX();
	}
	/**
	 * Get the cross product of the two vectors, defined by : x1*y2 - x2*y1.
	 */
	public double cross (Vector2D other) {
		return x*other.getY() - y*other.getX();
	}
	/**
	 * Get the cross product of the two vectors, defined by : x1*y2 - x2*y1.
	 */
	public double cross (Vector2D first, Vector2D second) {
		return first.getX()*second.getY() - first.getY()*second.getX();
	}
	
	/* ************************************************
	 *  Angles
	 ************************************************ */
	/**
	 * Calculates the angle between two vectors
	 * @return the angle in the interval 0-2pi
	 */
	public double angle (Vector2D v1, Vector2D v2) {
		return Math.atan2(v2.y,v2.x) - Math.atan2(v1.y,v1.x);
	}
	/**
	 * Calculates the angle this and another vector
	 * @return the angle in the interval 0-2pi
	 */
	public double angle (Vector2D v2) {
		return Math.atan2(v2.y,v2.x) - Math.atan2(y,x);
	}
	/**
	 * Calculates the angle between this and the x-axis
	 * @return the angle in the interval 0-2pi
	 */
	public double angle () {
		return Math.atan2(y,x);
	}
	/**
	 * Rotates this vector an angle
	 */
	public Vector2D rotate(double angle) {
		double cosine = Math.cos(angle);
		double sine = Math.sin(angle);
		return new Vector2D(x * cosine - y * sine,
							x * sine + y * cosine);
	}
	/**
	 * Rotates a vector an angle
	 */
	public Vector2D rotate(Vector2D vector, double angle) {
		double cosine = Math.cos(angle);
		double sine = Math.sin(angle);
		return new Vector2D(vector.getX() * cosine - vector.getY() * sine,
							vector.getX() * sine + vector.getY() * cosine);
	}
	
}
