/*
 * LineTool.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * This class will define how to make a line
 * and will call its parent class to organize 
 * the starting point and end point.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class LineTool extends AbstractDrawTool {
    
    /**
     * This will get the line tool.
     * 
     * @return Shape will return the given tool.
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}
