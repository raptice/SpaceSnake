package model;
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
            	obj.gravityPull(data);
            	obj.move();
            }
            
        }
    }
}