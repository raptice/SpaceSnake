package model;

public class Physics {

	/**
	 * Static help method that checks collision between two WorldObjects by comparing the sum
	 * of their radiuses and the distance between them.
	 * @param WorldObject the object to compare with the rest of the world
	 * @param WorldCollection the rest of the world
	 * @return true if WorldObject collides
	 * @return false if WorldObject doesn't collide
	 */
	public static boolean Collision(WorldObject obj1, WorldCollection data){
		for(WorldObject obj2 : data.getCollection()){
			if(!obj1.equals(obj2)){
				double length = Math.sqrt(obj1.getPosition().distancesquared(obj2.getPosition()));
				double radlength = obj1.getRadius() + obj2.getRadius();
				System.out.println("Distance between:" + length);
				System.out.println("Combined radiuses:" + radlength);
				if(length < radlength){
					System.out.println("COLLISION DETECTED");
					return true;
				}
			}
		}
		return false;
	}
}
