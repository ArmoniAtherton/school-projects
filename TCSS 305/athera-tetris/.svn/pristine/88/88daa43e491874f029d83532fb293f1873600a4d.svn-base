/*
 * TetrisPieceDrawingPanel.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-6
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Block;
import model.TetrisPiece;

/**
 * This class sets the next Tetris piece that allows the 
 * user to see the next incoming piece.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class TetrisPieceDrawingPanel extends JPanel implements Observer {

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 5470725701102600253L;
    
    /**
     * This is the default panel size.
     */
    private static final Dimension DEFAULT_FRAME_SIZE = new Dimension(200, 200);
    
    /**
     * This is the default frame color that will be displayed on this panel.
     */
    private static final Color DEFAULT_FRAME_COLOR = new Color(92, 160, 148);
    
    /**
     * This is the color of the borders of the tetris pieces.
     */
    private static final Color RECTANGLE_BORDER_COLOR = new Color(92, 160, 148);
    
    /**
     * This is the defalut block size of the tetris piece.
     */
    private static final int DEFAULT_BLOCKSIZE = 30;
    
    /**
     * This is half size of the block size.
     */
    private static final int HALF_BLOCKSIZE = 15;
    
    /**
     * This is the value that will align the next piece to center correctly.
     */
    private static final int ADJUST_PIECE = 75;
    
    /**
     * This is the value that will align the next piece to center correctly.
     */
    private static final int ADJUST_PIECE_UP = 105;
    
    /**
     * This is the points of the current tetris piece.
     */
    private model.Point[] myTetrisPoints;
    
    /**
     * This is the width of the current tetris piece.
     */
    private int myWidth;
    
    /**
     * This is the current tetris piece.
     */
    private TetrisPiece myCurrentTetrisPiece;

    /**
     * This is the constructor that will set up all the fields
     * that allow them to be accesed throughout the class.
     */
    public TetrisPieceDrawingPanel() {
        super();
        setBackground(DEFAULT_FRAME_COLOR);
        setPreferredSize(DEFAULT_FRAME_SIZE);
        myWidth = 0;
        myTetrisPoints = new model.Point[] {};
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) { 
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int i = 0; i < myTetrisPoints.length; i++) {
            final model.Point currentPoint = myTetrisPoints[i];
            g2d.setColor(Color.BLACK);
            if (myCurrentTetrisPiece.getBlock() == Block.I) {
                g2d.fillRect(currentPoint.x() * DEFAULT_BLOCKSIZE 
                             + (getWidth() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2), 
                             currentPoint.y() * -DEFAULT_BLOCKSIZE 
                             + (getHeight() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             + ADJUST_PIECE_UP, DEFAULT_BLOCKSIZE, DEFAULT_BLOCKSIZE);
                g2d.setPaint(RECTANGLE_BORDER_COLOR);
                g2d.drawRect(currentPoint.x() * DEFAULT_BLOCKSIZE 
                             + (getWidth() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2),
                             currentPoint.y() * -DEFAULT_BLOCKSIZE 
                             + (getHeight() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             + ADJUST_PIECE_UP, DEFAULT_BLOCKSIZE, DEFAULT_BLOCKSIZE);         
                
            } else if (myCurrentTetrisPiece.getBlock() == Block.O) {
                g2d.fillRect(currentPoint.x() * DEFAULT_BLOCKSIZE 
                             + (getWidth() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             - HALF_BLOCKSIZE, currentPoint.y() * -DEFAULT_BLOCKSIZE 
                             + (getHeight() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             + ADJUST_PIECE, DEFAULT_BLOCKSIZE, DEFAULT_BLOCKSIZE);
                g2d.setPaint(RECTANGLE_BORDER_COLOR);
                g2d.drawRect(currentPoint.x() * DEFAULT_BLOCKSIZE 
                             + (getWidth() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             - HALF_BLOCKSIZE, currentPoint.y() * -DEFAULT_BLOCKSIZE 
                             + (getHeight() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             + ADJUST_PIECE, DEFAULT_BLOCKSIZE, DEFAULT_BLOCKSIZE);
                
            } else {
                g2d.fillRect(currentPoint.x() * DEFAULT_BLOCKSIZE 
                             + (getWidth() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2), 
                             currentPoint.y() * -DEFAULT_BLOCKSIZE 
                             + (getHeight() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             + ADJUST_PIECE, DEFAULT_BLOCKSIZE, DEFAULT_BLOCKSIZE);
                g2d.setPaint(RECTANGLE_BORDER_COLOR);
                g2d.drawRect(currentPoint.x() * DEFAULT_BLOCKSIZE 
                             + (getWidth() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2), 
                             currentPoint.y() * -DEFAULT_BLOCKSIZE 
                             + (getHeight() / 2 - (myWidth * DEFAULT_BLOCKSIZE) / 2) 
                             + ADJUST_PIECE,  DEFAULT_BLOCKSIZE, DEFAULT_BLOCKSIZE);
            }
        }
        
       
    }
    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            myWidth = ((TetrisPiece) theArg).getWidth();
            myCurrentTetrisPiece = (TetrisPiece) theArg;
            myTetrisPoints = ((TetrisPiece) theArg).getPoints();
            repaint();
        }
        
        
    }
}
