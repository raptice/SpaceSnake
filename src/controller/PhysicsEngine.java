package controller;

import objects.SnakePart;
import model.IGravity;
import model.Moveable;
import model.Physics;
import model.WorldCollection;
import model.WorldObject;

/**
 * Write a description of class GameThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicsEngine extends Thread
{
    private long interval;
    private WorldCollection data;

    public PhysicsEngine(WorldCollection data, long interval){
    	this.data = data;
        this.interval = interval;
    }
    public void collisionResolve(){
    	
    }
    public void run(){
        while ( ! interrupted() ) {
            try{ 
                sleep(interval);
            }
            catch(InterruptedException e){
                break;
            }
           
            //DO USEFUL WORK HERE
            for(WorldObject obj : data.getCollection()){
            	if(obj instanceof IGravity ){
            		((IGravity)obj).gravityPull(data);
            	}       	
            }
            for(WorldObject obj : data.getCollection()){
            	if(obj instanceof Moveable){
            		((Moveable)obj).move();
            	}
            }
            for(WorldObject obj : data.getCollection()){
            	Physics.Collision(obj, data);
            }
            for(WorldObject obj : data.getCollection()){
            	if(obj instanceof SnakePart ){
            		((SnakePart)obj).pullAtNext();
            	}
            }
            
            
        }
    }
}