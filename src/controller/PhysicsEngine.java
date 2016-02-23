package controller;

import java.util.ArrayList;

import model.IGravity;
import model.Moveable;
import model.WorldCollection;
import model.WorldObject;
import model.objects.SnakeHead;
import model.objects.SnakePart;
import util.Vector2D;

/**
 * Write a description of class GameThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicsEngine extends Thread
{
    private double dT;
    private WorldCollection data;
    private boolean setPaused;
    private double gameSpeed;
    private Vector2D MouseDir = new Vector2D (0,0);
    private double MouseAccPower = 1;
    

    public PhysicsEngine(WorldCollection data, double dT, double gameSpeed){
    	this.gameSpeed = gameSpeed;
    	this.data = data;
        this.dT = dT;
    }
    public void SnakePull(Vector2D acc){
    	if(acc==null)
    	{
    		MouseDir = new Vector2D (0,0);
    	}
    	else
    	{
    		MouseDir = acc.scale(MouseAccPower);
    	}
    	
    }
    public void collisionResolve(){
    	
    }
    public void run(){
    	Thread thisThread = Thread.currentThread();
        while ( ! isInterrupted() ) {
            try{ 
                sleep((long)dT*(long)gameSpeed);
                synchronized(this) {
                	while(setPaused) {
                		thisThread.wait();
                	}
                }
            }
            catch(InterruptedException e){
                break;
            }
           
            //Make a clone so changes (additions and deletions only affect next iteration
            ArrayList<WorldObject> collection = (ArrayList<WorldObject>) data.getCollection().clone();
    		
            
            for(WorldObject obj : collection){
            	if(obj instanceof IGravity ){
            		((IGravity)obj).gravityPull(collection, dT);
            	}       	
            }
            for(WorldObject obj : collection){
            	if(obj instanceof Moveable)
            		((Moveable)obj).collisions(collection);
            }
            for(WorldObject obj : collection){
            	if(obj instanceof Moveable){
            		((Moveable)obj).move(dT);
            	}
            }
            for(WorldObject obj : collection){
            	if(obj instanceof SnakePart ){
            		((SnakePart)obj).pullAtNext(dT);
            	}
            }
            for(WorldObject obj : collection){
            	if(obj instanceof SnakeHead){
            		((SnakeHead)obj).accelerate(MouseDir,dT);
            	}	
            }
            
            
        }
    }
    public void setPaused() {
    	setPaused = true;
    }
    
    public void setResumed() {
    	setPaused = false;
    }
}