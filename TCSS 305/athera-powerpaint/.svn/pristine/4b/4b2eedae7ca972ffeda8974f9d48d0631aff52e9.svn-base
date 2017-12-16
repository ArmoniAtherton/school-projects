/*
 * PencilTool.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * This class will define how to make a pencil 
 * and will call its parent class to organize 
 * the starting point and end point.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class PencilTool extends AbstractDrawTool {
    
    /** This will hold the pencil information for creation. */
    private Path2D.Double myPencil;
    
    /**
     * This is the constructor that calls its parents class
     * and sets up the pencil object.
     */
    public PencilTool() {
        super();
        myPencil = new Path2D.Double(); 
    }
    
    /**
     * This will allow for the shape to be drawn while 
     * following the cursor.
     * 
     * @return Shape will return the given tool.
     */
    @Override
    public Shape getShape() {
        myPencil.moveTo(getStartPoint().getX(), getStartPoint().getY());
        myPencil.lineTo(getEndPoint().getX(), getEndPoint().getY());
        setStartPoint(getEndPoint());
    
        return myPencil;
    }
    
    /**
     * This will allow for the pencil tool to be reset to allow 
     * for more than one pencil object to be drawn.
     */
    public void resetTool() {
        myPencil = new Path2D.Double(); 
    }
}
