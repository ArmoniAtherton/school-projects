/*
 * SizeAction.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-6
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 * This class will set up a action associated
 * with each size.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class SizeAction extends AbstractAction {
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 7329947802528016108L;
    
    /**
     * This is will hold a dimension.
     */
    private final Dimension myDimension;
    
    /**
     * Will hold the block size.
     */
    private final int myBlockSize;
    
    /**
     * Will be a object of the Tetris board drawing panel.
     */
    private final TetrisBoardDrawingPanel myTetrisBoard;
    
    /**
     * The main frame to hold all the layouts in.
     */
    private final JFrame myFrame;
    
    /**
     * This will construct all the fields for this class so 
     * they can be used throughout this class.
     * 
     * @param theFrame the main frame.
     * @param theTetrisBoard the drawing board for the Tetris pieces.
     * @param theName the name associated with the size.
     * @param theEnabled boolean of whether enabled or not.
     * @param theDimension the incoming dimension.
     * @param theBlockSize the incoming block size.
     */
    public SizeAction(final JFrame theFrame, 
                      final TetrisBoardDrawingPanel theTetrisBoard, 
                      final String theName, final Boolean theEnabled, 
                      final Dimension theDimension, final int theBlockSize) {
        super(theName);
        myFrame = theFrame;
        myTetrisBoard = theTetrisBoard;
        myDimension = theDimension;
        myBlockSize = theBlockSize;
        putValue(SELECTED_KEY, theEnabled);
    }
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        //theEvent.
        if (!(myDimension.equals(myTetrisBoard.getSize()))) {
            myTetrisBoard.setDimension(myDimension);
            myTetrisBoard.setBlockSize(myBlockSize); 
            myFrame.setResizable(false);
            myFrame.setSize(myDimension);
            myFrame.revalidate();
            myFrame.pack();
        }
         
            
    }

}

