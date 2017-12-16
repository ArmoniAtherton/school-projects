/*
 * AbstractVehicle.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-3
 */
package model;

import java.util.Locale;
import java.util.Map;

/**
 * This program will create Item Order objects to 
 * be instantiated by the driver class.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 25, 2017 
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /**
     * Holds the current x value of a vehicle.
     */
    private int myX;
    
    /**
     * Holds the current y value of a vehicle.
     */
    private int myY;
    
    /**
     * Holds the current direction of a vehicle.
     */
    private Direction myDir;
    
    /**
     * Holds the current death time for the vehicle.
     */
    private final int myDeathTime;
    
    /**
     * Holds the current amount of time for revival of a  vehicle.
     */
    private int myReviveTimer;
    
    /**
     * Holds the unmodified x value of a vehicle.
     */
    private final int myOriginalX;
    
    /**
     * Holds the unmodified y value of a vehicle.
     */
    private final int myOriginalY;
    
    /**
     * Holds the unmodified Direction of a vehicle.
     */
    private final Direction myOriginalDir;
    
    /**
     * Holds the unmodified revive timer of a vehicle.
     */
    private final int myOriginalReviveTimer;
    
    /**
     * This constructs a abstract vehicle that will be used by all vehicles to 
     * save code redundancy.
     *  
     * @param theX the incoming x value of the vehicle.
     * @param theY the incoming y value of the vehicle.
     * @param theDir the incoming Direction of the vehicle.
     * @param theDeathTime The incoming time a car stays dead.
     */
    protected AbstractVehicle(final int theX, final int theY, 
                           final Direction theDir, final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDir = theDir;
        myDeathTime = theDeathTime;
        myReviveTimer = 0;
        myOriginalX = theX;
        myOriginalY = theY;
        myOriginalDir = theDir;
        myOriginalReviveTimer = 0;
           
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && myDeathTime > theOther.getDeathTime()) {
            myReviveTimer = myDeathTime;    
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getImageFileName() {
        final String className = getClass().getSimpleName().toLowerCase(Locale.US);
        final StringBuilder builder = new StringBuilder(64);
        if (isAlive()) {
            builder.append(className);
            builder.append(".gif");
        } else {
            builder.append(className);
            builder.append("_dead.gif");
        }
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return myDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return myY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        boolean flag = false;
        if (myReviveTimer == 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void poke() {
        if (!isAlive()) {
            if (myReviveTimer == 0) {
                setDirection(Direction.random());
            } else {
                myReviveTimer--;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        myX = myOriginalX;
        myY = myOriginalY;
        myDir = myOriginalDir; 
        myReviveTimer = myOriginalReviveTimer;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final int theX) {
        myX = theX;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final int theY) {
        myY = theY;

    }
    
    /**
     * This used for showing a string representation of 
     * each vehicle to show the the name for making debugging
     * easier.
     * 
     * @return the class name of the vehicle.
     */
    @Override
    public String toString() {
        final String className = getClass().getSimpleName().toLowerCase(Locale.US);
        final StringBuilder builder = new StringBuilder(64);
        builder.append(className);
        return builder.toString();
    }

}
