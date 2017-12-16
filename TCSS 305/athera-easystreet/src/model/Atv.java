/*
 * Atv.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-3
 */
package model;

import java.util.Map;

/**
 * This class will create a Atv and give specific 
 * directions on terrain and traffic light rules.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public class Atv extends AbstractVehicle implements Vehicle {
    /**
     * This is a constant used to hold the death time of the Atv.
     */
    public static final int DEFAULT_ATV_DEATH_TIME = 20;
    
    /**
     * This constructs a Atv in which will use inheritance 
     * to reduce code redundancy and in this class set up 
     * if it can pass the correct directions that are chosen.
     * 
     * @param theX the incoming x value of Atv.
     * @param theY the incoming y value of Atv.
     * @param theDir the incoming direction of the Atv.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEFAULT_ATV_DEATH_TIME);     
    }
    
    /**
     * This will determine if what terrain types the Atv
     * can travel on and if it has a light restrictions.
     * 
     * @return result of if terrain is valid or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        //return (theTerrain != Terrain.WALL)
        boolean result = false; 
        if (theTerrain != Terrain.WALL) { 
            result = true;
        }
        return result;  
    }
   
    /**
     * This will determine a correct direction for Atv
     * to go and try all possible directions but not allowing
     * it to reverse.
     * 
     * @return Direction that is valid for Atv to go.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        boolean flag = true;
        //Stores the direction to go.
        Direction resultDirection = null;
        //Will iterate until finding a valid direction.
        while (flag) {
            //Stores a random direction such as north, east, south, west.
            final Direction randomDirection = Direction.random();
            //Stores the terrain of a random direction and sees if valid.
            final Terrain vaildDirection = theNeighbors.get(randomDirection);
            if (getDirection().reverse() != randomDirection && canPass(vaildDirection, null)) {
                //If valid direction will set to a variable to be returned.
                resultDirection = randomDirection; 
                flag = false;
            }
        }
        return resultDirection;  
    }
    

}
