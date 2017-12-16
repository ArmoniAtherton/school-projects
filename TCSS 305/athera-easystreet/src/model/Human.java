/*
 * Human.java
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
 * This class will create a Human and give specific 
 * directions on terrain and traffic light rules.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public class Human extends AbstractVehicle implements Vehicle {
    /**
     * This is a constant used to hold the death time of the Human.
     */
    public static final int DEFAULT_HUMAN_DEATH_TIME = 50;
  
    /**
     * This constructs a Human in which will use inheritance 
     * to reduce code redundancy and in this class set up 
     * if it can pass the correct directions that are chosen.
     * 
     * @param theX the incoming x value of Human.
     * @param theY the incoming y value of Human.
     * @param theDir the incoming direction of the Human.
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEFAULT_HUMAN_DEATH_TIME);
        
    }
    
    /**
     * This will determine if what terrain types the Human
     * can travel on and if it has a light restrictions.
     * 
     * @return result of boolean if terrain is valid or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false; 
        if (theTerrain == Terrain.GRASS 
            || (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN)) {
            result = true;
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
        Direction resultDirection = null; 
        final Direction facingTowardsCrossWalk = nextToCrossWalk(theNeighbors); 
        if (theNeighbors.get(facingTowardsCrossWalk) == Terrain.CROSSWALK) { 
            resultDirection = facingTowardsCrossWalk;
        } else {
            resultDirection = vaildDirection(theNeighbors);
        }
        return resultDirection;
    }  
    
    /**
     * This is a helper method used to determine if the 
     * Human is next to a cross walk and if so will face it.
     * 
     * @param theNeighbors contains the list of terrain types and directions.
     * @return Direction of the Human facing the cross walk. 
     */   
    private Direction nextToCrossWalk(final Map<Direction, Terrain> theNeighbors) {
        Direction resultDirection = null;
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            resultDirection = getDirection();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            resultDirection = getDirection().right();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            resultDirection = getDirection().left();
        }
        return resultDirection; 
    } 
    
    /**
     * This is a helper method used to determine if the 
     * Human has a valid direction to go if so will put into a array if none 
     * will reverse.
     * 
     * @param theNeighbors contains the list of terrain types and directions.
     * @return Direction of the correct direction the Human can go.
     */
    private Direction vaildDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction resultDirection = null; 
        final List<Direction> possibleDirections = new ArrayList<Direction>();
        if ((getDirection().reverse() != getDirection()) 
             && (theNeighbors.get(getDirection()) == Terrain.GRASS)) {
            possibleDirections.add(getDirection());
        }
        if ((getDirection().reverse() != getDirection()) 
             && (theNeighbors.get(getDirection().right()) == Terrain.GRASS)) {
            possibleDirections.add(getDirection().right());
        }
        if ((getDirection().reverse() != getDirection()) 
             && (theNeighbors.get(getDirection().left()) == Terrain.GRASS)) {
            possibleDirections.add(getDirection().left());
        }
        
        if (possibleDirections.isEmpty()) {
            resultDirection = getDirection().reverse();
        } else {
            final Random random = new Random();
            resultDirection = possibleDirections.
                              get(random.nextInt(possibleDirections.size()));
        }
        return resultDirection;
        
    } 
}
