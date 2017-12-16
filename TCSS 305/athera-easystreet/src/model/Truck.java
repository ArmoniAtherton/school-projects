/*
 * Truck.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-3
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class will create a Truck and give specific 
 * directions on terrain and traffic light rules.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public class Truck extends AbstractVehicle implements Vehicle {
    /**
     * This is used for random picking out of a array.
     */
    private final Random myRandom;
    
    /**
     * This constructs a Truck in which will use inheritance 
     * to reduce code redundancy and in this class set up 
     * if it can pass the correct directions that are chosen.
     * 
     * @param theX the incoming x value of Truck.
     * @param theY the incoming y value of Truck.
     * @param theDir the incoming direction of the Truck.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 0);
        myRandom = new Random();
    }
    
    /**
     * This will determine if what terrain types the Truck
     * can travel on and if it has a light restrictions.
     * 
     * @return result of boolean if terrain is valid or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = true;
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            result = false;
        }
        return result;

    }

    /**
     * This will determine a correct direction for Truck
     * to go and try all possible directions but if not valid
     * will reverse.
     * 
     * @return Direction that is valid for Truck to go.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = null;
        final List<Direction> possibleDirections = new ArrayList<Direction>(); 
        
        if (validTerrain(theNeighbors, getDirection())) { 
            possibleDirections.add(getDirection());
        }
        if (validTerrain(theNeighbors, getDirection().left())) {
            possibleDirections.add(getDirection().left());
        }
        if (validTerrain(theNeighbors, getDirection().right())) {
            possibleDirections.add(getDirection().right());
        }
        
        if (possibleDirections.isEmpty()) {
            result = getDirection().reverse();
        } else  {
            result = possibleDirections.get(myRandom.nextInt(possibleDirections.size()));
        }
        return result;
    }
    
    /**
     * This is a helper method used to determine if the 
     * Truck has a valid terrain to choose from.
     * 
     * @param theNeighbors contains the list of terrain types and directions.
     * @param theDirection current direction such as left, right or strait ahead.
     * @return result of true or false if it can go that direction or not.
     */
    private boolean validTerrain(final Map<Direction, Terrain> theNeighbors, 
                                 final Direction theDirection) {
        boolean result = false;
        
        if (theNeighbors.get(theDirection) == Terrain.STREET 
             || theNeighbors.get(theDirection) == Terrain.LIGHT 
             || theNeighbors.get(theDirection) == Terrain.CROSSWALK) { 
            result = true;
        
        }
        return result;
    }          

}
