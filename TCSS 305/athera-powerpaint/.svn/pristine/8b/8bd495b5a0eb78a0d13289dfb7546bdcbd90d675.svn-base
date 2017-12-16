/*
 * RectangeTool.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;


/**
 * This class will define how to make a Rectangle
 * and will call its parent class to organize 
 * the starting point and end point.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class RectangleTool extends AbstractDrawTool {
    
    /**
     * This will get the Rectangle tool.
     * 
     * @return Shape will return the given tool.
     */
    @Override
    public Shape getShape() {
        final Rectangle2D.Double currentRectangle = new Rectangle2D.Double();
        currentRectangle.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return currentRectangle;

    }
    
}
