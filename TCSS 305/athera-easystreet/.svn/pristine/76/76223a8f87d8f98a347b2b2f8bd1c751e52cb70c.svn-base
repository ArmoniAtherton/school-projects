/*
 * Car.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-3
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class will create a Car and give specific 
 * directions on terrain and traffic light rules.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public class Car extends AbstractVehicle implements Vehicle {
    
    /**
     * This is a constant used to hold the death time of the Car.
     */
    public static final int DEFAULT_CAR_DEATH_TIME = 10;
    
    /**
     * This constructs a Car in which will use inheritance 
     * to reduce code redundancy and in this class set up 
     * if it can pass the correct directions that are chosen.
     * 
     * @param theX the incoming x value of Car.
     * @param theY the incoming y value of Car.
     * @param theDir the incoming direction of the Car.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEFAULT_CAR_DEATH_TIME); 
        
    }
    
    /**
     * This will determine if what terrain types the Car
     * can travel on and if it has a light restrictions.
     * 
     * @return result of boolean if terrain is valid or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = true;
        if ((theTerrain == Terrain.CROSSWALK && theLight == Light.RED) 
             || (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW) 
             || (theTerrain == Terrain.LIGHT && theLight == Light.RED)) {
            result = false;
        } 
        return result;
    }
    
    /**
     * This will determine a correct direction for Car
     * to go and try all possible directions but if not valid
     * will reverse.
     * 
     * @return Direction that is valid for Car to go.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = null;
        final List<Direction> possibleDirections = new ArrayList<Direction>(); 
        
        if ((getDirection().reverse() != getDirection())  
             && validTerrain(theNeighbors, getDirection())) {
            possibleDirections.add(getDirection()); 
        }
        if ((getDirection().reverse() != getDirection()) 
             && validTerrain(theNeighbors, getDirection().left())) {
            possibleDirections.add(getDirection().left());
        }
        if ((getDirection().reverse() != getDirection()) 
             && validTerrain(theNeighbors, getDirection().right())) {
            possibleDirections.add(getDirection().right());
        }
        
        if (possibleDirections.isEmpty()) {
            result = getDirection().reverse();
        } else  {
            result = checkDirectionOrder(possibleDirections);

        }
        return result;
    }
    
    /**
     * This is a helper method used to determine if the 
     * Car has a valid terrain to travel on.
     * 
     * @param theNeighbors contains the list of terrain types and directions.
     * @param theDirection current direction such as left, right or strait ahead.
     * @return result of whether it can go on this terrain or not.
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
    
    /**
     * This is a helper method used to determine the 
     * Car preferred way to go if any. 
     * 
     * @param thePossibleDirections holds valid directions the Bicycle can go.
     * @return Direction of preferred way.
     */
    private Direction checkDirectionOrder(final List<Direction> thePossibleDirections) {
        Direction result = null;
        if (thePossibleDirections.contains(getDirection())) {
            result = getDirection();
        } else if (thePossibleDirections.contains(getDirection().left())) {
            result = getDirection().left();
        } else {
            result = getDirection().right();
        }
        return result;
    }

}
