/*
 * ToolAction.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */
package gui;

import draw.DrawingPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import tools.DrawTool;

/**
 * This class sets up the tool actions and assigns to 
 * a mnemonic, a boolean value and a image to be displayed
 * and shared.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class ToolAction extends AbstractAction {
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 7329947802528016108L;
    
    /**  The drawing panel to allow access to the class. */
    private final DrawingPanel myDrawingPanel;
    
    /**  The current tool to be created. */
    private final DrawTool myCurrentTool;

    /**
     * This is the constructor to set up all the values. Will
     * set up the image to each tool along with if it is enabled or not and 
     * finally will set the mnemonic of each tool.
     * 
     * @param theName the name of the current tool being created.
     * @param theEnabled if the tool is selected or not.
     * @param theMnemonic the mnemonic key to be set for the tool.
     * @param theImage the image to be displayed for the tool.
     * @param theCurrentTool the tool that is being created.
     * @param theDrawingPanel the drawing panel class to allow access.
     */
    ToolAction(final String theName, final Boolean theEnabled, 
                       final Integer theMnemonic, final ImageIcon theImage, 
                       final DrawTool theCurrentTool, final DrawingPanel theDrawingPanel) {
        super(theName);
        myCurrentTool = theCurrentTool;
        myDrawingPanel = theDrawingPanel;
        putValue(SELECTED_KEY, theEnabled);
        putValue(LARGE_ICON_KEY, theImage);
        putValue(MNEMONIC_KEY, theMnemonic);
    }

    /**
     * This is will set the current tool.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.setTool(myCurrentTool);
            
    }

}