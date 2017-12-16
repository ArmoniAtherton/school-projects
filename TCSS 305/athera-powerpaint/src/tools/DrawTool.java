/*
 * DrawTool.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */

package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * This is a interface class that will layout
 * the minimum methods that will need to be implement 
 * of any classes that will implement from it.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public interface DrawTool {

    /**
     * Returns the Shape that this tools draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();

    /**
     * Sets the initial point for the Shape drawn by this tool.
     * 
     * @param thePoint the start point to set
     */
    void setStartPoint(Point thePoint);
        
    /**
     * Sets the end point for the Shape drawn by this tool.
     * 
     * @param thePoint the end point to set
     */
    void setEndPoint(Point thePoint);
}
