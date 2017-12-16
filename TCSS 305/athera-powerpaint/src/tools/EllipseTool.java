/*
 * EllipseTool.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * This class will define how to make a Ellipse
 * and will call its parent class to organize 
 * the starting point and end point.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class EllipseTool extends AbstractDrawTool {
    
    /**
     * This will get the Ellipse tool.
     * 
     * @return Shape will return the given tool.
     */
    @Override
    public Shape getShape() {
        final Ellipse2D.Double currentEllipse = new Ellipse2D.Double();
        currentEllipse.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return currentEllipse;

    }
}
