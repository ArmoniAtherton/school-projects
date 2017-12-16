/*
 * KeyInputAdapter.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-6
 */
package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import model.Board;

/**
 * This class sets up the key listeners for the game
 * to listen for when a key is pressed.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class KeyInputAdapter extends KeyAdapter {

   /**
    * The standard Tetris game board that holds the blocks, pieces and logic.
    */
    private final Board myBoard;
    
    /**
     * This will hold all the left key values.
     */
    private final List<Integer> myKeysLeft;
    
    /**
     * This will hold all the right key values.
     */
    private final List<Integer> myKeysRight;
    
    /**
     * This will hold all the down key values.
     */
    private final List<Integer> myKeysDown;
    
    /**
     * This will hold all the up key values.
     */
    private final List<Integer> myKeysUp;
    
    /**
     * This will hold all the drop key values.
     */
    private final List<Integer> myKeysDrop;

    /**
     * This will construct the fields for this class
     * allowing access to them through out all the 
     * class.
     * 
     * @param theBoard Tetris board that holds the blocks, pieces and logic.
     * @param theKeysLeft the incoming keys that allow the user to move a piece left.
     * @param theKeysRight the incoming keys that allow the user to move a piece right.
     * @param theKeysDown the incoming keys that allow the user to move a piece down.
     * @param theKeysUp the incoming keys that allow the user to move a piece up.
     * @param theKeysDrop the incoming keys that allow the user to move a piece drop.
     */
    public KeyInputAdapter(final Board theBoard, 
                           final List<Integer> theKeysLeft, 
                           final List<Integer> theKeysRight,
                           final List<Integer> theKeysDown, 
                           final List<Integer> theKeysUp,
                           final List<Integer> theKeysDrop) {
        super();
        myBoard = theBoard;
        myKeysLeft = theKeysLeft;
        myKeysRight = theKeysRight;
        myKeysDown = theKeysDown;
        myKeysUp = theKeysUp;
        myKeysDrop = theKeysDrop;
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        if (myKeysLeft.contains(theEvent.getKeyCode())) {
            myBoard.left();
        } else if (myKeysRight.contains(theEvent.getKeyCode())) {
            myBoard.right();
        } else if (myKeysUp.contains(theEvent.getKeyCode())) {
            myBoard.rotateCW();
        } else if (myKeysDown.contains(theEvent.getKeyCode())) {
            myBoard.step();
        } else if (myKeysDrop.contains(theEvent.getKeyCode())) {
            myBoard.drop();
        }
    }
}
