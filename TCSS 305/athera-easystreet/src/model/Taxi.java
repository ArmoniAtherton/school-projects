/*
 * Taxi.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-3
 */
package model;

/**
 * This class will create a Taxi and give specific 
 * directions on terrain and traffic light rules.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public class Taxi extends Car {
    /**
     * This is a constant used to hold the Cycle time to wait.
     */
    public static final int CYCYLE_TIMER = 3;
    
    /**
     * This is a counter to check current cycle time when at red light. 
     */
    private int myCurrentCycleTimer;
    
    /**
     * This constructs a Taxi in which will use inheritance 
     * to reduce code redundancy and in this class set up 
     * if it can pass the correct directions that are chosen.
     * 
     * @param theX the incoming x value of Taxi.
     * @param theY the incoming y value of Taxi.
     * @param theDir the incoming direction of the Taxi.
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir); 
        myCurrentCycleTimer = 0;
    }
    
    /**
     * This will determine if what terrain types the Taxi
     * can travel on and if it has a light restrictions if at a  red light
     * will initiate a timer for 3 seconds then go.
     * 
     * @return result of boolean if terrain is valid or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = true;
        if (myCurrentCycleTimer == CYCYLE_TIMER 
            || theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            myCurrentCycleTimer = 0; 
            result = true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            result = false;
            myCurrentCycleTimer++;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            result = false;
        }
        return result; 
    }
}
