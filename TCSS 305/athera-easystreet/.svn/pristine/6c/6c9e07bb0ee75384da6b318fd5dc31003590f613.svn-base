/*
 * Bicycle.java
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
 * This class will create a Bicycle and give specific 
 * directions on terrain and traffic light rules.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public class Bicycle extends AbstractVehicle implements Vehicle {
    
    /**
     * This is a constant used to hold the death time of the Bicycle.
     */
    public static final int DEFAULT_BICYCLE_DEATH_TIME = 30;
    
    /**
     * This constructs a Bicycle in which will use inheritance 
     * to reduce code redundancy and in this class set up 
     * if it can pass the correct directions that are chosen.
     * 
     * @param theX the incoming x value of Bicycle.
     * @param theY the incoming y value of Bicycle.
     * @param theDir the incoming direction of the bicycle.
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEFAULT_BICYCLE_DEATH_TIME); 
    }
    /**
     * This will determine if what terrain types the Bicycle
     * can travel on and if it has a light restrictions.
     * 
     * @return result of boolean if terrain is valid or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = true;
        if ((theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN) 
             || (theTerrain == Terrain.LIGHT && theLight != Light.GREEN)) {
            result = false;
        }
        return result; 
    }
    
    /**
     * This will determine a correct direction for Bicycle
     * to go and try all possible directions but if not valid
     * will reverse.
     * 
     * @return Direction that is valid for Atv to go.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction resultDirection = null; 
        final Direction facingTowardsTrail = nextToTrail(theNeighbors); 
        if (theNeighbors.get(facingTowardsTrail) == Terrain.TRAIL) { 
            resultDirection = facingTowardsTrail;
        } else {
            resultDirection = vaildDirection(theNeighbors);
        }
        return resultDirection;
    }
    
    /**
     * This is a helper method used to determine if the 
     * Bicycle is next to a trail and if so will face it.
     * 
     * @param theNeighbors contains the list of terrain types and directions.
     * @return Direction of the Bicycle facing the trail.
     */
    private Direction nextToTrail(final Map<Direction, Terrain> theNeighbors) {
        Direction resultDirection = null;
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            resultDirection = getDirection();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            resultDirection = getDirection().right();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            resultDirection = getDirection().left();
        }
        return resultDirection;
    } 
    
    /**
     * This is a helper method used to determine if the 
     * Bicycle has a valid direction to go if so will put into a array if none 
     * will reverse.
     * 
     * @param theNeighbors contains the list of terrain types and directions.
     * @return Direction of the correct direction the Bicycle can go.
     */
    private Direction vaildDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction resultDirection = null; 
        final List<Direction> possibleDirections = new ArrayList<Direction>();
        if ((getDirection().reverse() != getDirection()) 
             && (validTerrain(theNeighbors, getDirection()))) { 
            possibleDirections.add(getDirection());
        }
        if ((getDirection().reverse() != getDirection())
             && (validTerrain(theNeighbors, getDirection().right()))) {
            possibleDirections.add(getDirection().right());
        }
        if ((getDirection().reverse() != getDirection()) 
             && (validTerrain(theNeighbors, getDirection().left()))) {
            possibleDirections.add(getDirection().left());
        }
        
        if (possibleDirections.isEmpty()) {
            resultDirection = getDirection().reverse();
        } else {
            resultDirection = checkDirectionOrder(possibleDirections);
        }
        return resultDirection;
        
    } 
    
    /**
    * This is a helper method used to determine if the 
    * Bicycle has a valid terrain to choose from.
    * 
    * @param theNeighbors contains the list of terrain types and directions.
    * @param theDirection current direction such as left, right or strait ahead.
    * @return result of true or false if it can go that direction or not.
    */
    private boolean validTerrain(final Map<Direction, Terrain> theNeighbors, 
                                 final Direction theDirection) {
        boolean result = false;
        if ((theNeighbors.get(theDirection) != Terrain.GRASS) 
            && (theNeighbors.get(theDirection) != Terrain.WALL)) { 
            result = true;
        
        }
        return result;
    }          
    
    /**
     * This is a helper method used to determine the 
     * Bicycles preferred way to go if any. 
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
