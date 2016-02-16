package model;

import java.util.*;
/**
 * Write a description of class GameThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameThread extends Thread
{
    private long interval;
    private ArrayList<WorldObject> data;

    public GameThread(WorldCollection datacoll, long interval){
        data = new ArrayList<>();
        data = datacoll.getCollection();
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
            for(WorldObject obj : data){
            	obj.
            }
            
        }
    }
}