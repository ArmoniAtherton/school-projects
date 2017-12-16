/*
 * TetrisBoardDrawingPanel.java
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
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Block;

/**
 * This class sets the Tetris board that allows the 
 * user to see the piece visually.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class TetrisBoardDrawingPanel extends JPanel implements Observer {
    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = 5374566811980230614L;
    
    /**
     * The block size associated with the current board.
     */
    private static final int DEFAULT_BLOCKSIZE = 30;
    
    /**
     * This is the panel height subtracted by one.
     */
    private static final int DEFAULT_PANEL_HEIGHT = 19;
    
    /**
     * This is the frame size of the panel.
     */
    private static final Dimension DEFAULT_FRAME_SIZE = new Dimension(300, 600);
    
    /**
     * This is the default color of the panel.
     */
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(92, 160, 148);
    
    /**
     * This is the color of the T block.
     */
    private static final Color DEFAULT_T_BLOCK_COLOR = new Color(58, 52, 143);
    
    /**
     * The main frame to hold all the layouts in.
     */
    private final JFrame myFrame;
    
    /**
     * This is the list of the all on the pieces on the board.
     */
    private List<Block[]> myListRow;
    
    /**
     * This is the dimension of the frame.
     */
    private Dimension myDimension;
    
    /**
     * This is the block size corresponding to the board size.
     */
    private int myBlockSize;
    
    /**
     * This is the color of the block being drawn.
     */
    private Color myBlockColor;
    
    /**
     * This is checks to enable grid or disable.
     */
    private boolean myGrid;
    
    /**
     * This enable or disables all blocks to dark mode.
     */
    private boolean myDarkModeEnabled;
    
    /**
     * This constructs all the fields of this class so 
     * that they can be used throughout this class.
     * 
     * @param theFrame the incoming value in which is the main frame.
     */
    public TetrisBoardDrawingPanel(final JFrame theFrame) {
        super();
        setUpFrame();
        myFrame = theFrame;
        myBlockSize = DEFAULT_BLOCKSIZE;
        myListRow = new ArrayList<Block[]>();
        myBlockColor = Color.DARK_GRAY;
        myGrid = false;
        myDarkModeEnabled = false;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    
    /**
     * This is a helper method for the constructor that will
     * help set up the fields.
     */
    private void setUpFrame() {
        myDimension = DEFAULT_FRAME_SIZE;
        setBackground(DEFAULT_BACKGROUND_COLOR);
        setPreferredSize(myDimension);
        
    }

    @Override
    public void paintComponent(final Graphics theGraphics) { 
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = myListRow.size() - 1; i >= 0; i--) {
            final Block[] column = myListRow.get(i);
            for (int j = 0; j < column.length; j++) {
                if (column[j] == null) { 
                    if (myGrid) {
                        g2d.setPaint(Color.BLACK);
                        g2d.drawRect(j * myBlockSize, (DEFAULT_PANEL_HEIGHT - i) * myBlockSize,
                                     myBlockSize , myBlockSize);
                    }
                } else {
                    setPreferedColor(column[j]);
                    g2d.setPaint(myBlockColor);
                    g2d.fillRect(j * myBlockSize, (DEFAULT_PANEL_HEIGHT - i) * myBlockSize,
                                 myBlockSize , myBlockSize);
                    g2d.setPaint(Color.BLACK);
                    g2d.drawRect(j * myBlockSize, (DEFAULT_PANEL_HEIGHT - i) * myBlockSize,
                                 myBlockSize , myBlockSize);
                }
            }
        }
    }
    
    /**
     * This will set the color of each individual block
     * to a different color.
     * 
     * @param theColumn the type of the current piece.
     */
    private void setPreferedColor(final Block theColumn) {
        if (myDarkModeEnabled) {
            myBlockColor = Color.DARK_GRAY;
            repaint();
        } else {
            if (theColumn == Block.I) {
                myBlockColor = Color.CYAN;
                
            } else if (theColumn == Block.J) {
                myBlockColor = Color.BLUE;
                
            } else if (theColumn == Block.L) {
                myBlockColor = Color.ORANGE;
                
            } else if (theColumn == Block.O) {
                myBlockColor = Color.YELLOW;
                
            } else if (theColumn == Block.S) {
                myBlockColor = Color.GREEN;
                
            } else if (theColumn == Block.T) {
                myBlockColor = DEFAULT_T_BLOCK_COLOR;
                
            } else if (theColumn == Block.Z) {
                myBlockColor = Color.RED;
            } 
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof List) {
            myListRow = (List<Block[]>) theArg;
            repaint();
                
        } else if (theArg instanceof Boolean) {
            myFrame.removeKeyListener(null);
            final ImageIcon icon = new ImageIcon(getClass().
                                                 getResource("/images/TetrisGameOver.jpg"));
            JOptionPane.
                showMessageDialog(null,
                                  "" 
                                  , null, JOptionPane.INFORMATION_MESSAGE, icon);    
        }
    }
            
    /**
     * This will set the dimension of the panel 
     * to the incoming size.
     * 
     * @param theDimension the incoming dimension to set the frame as.
     */
    public void setDimension(final Dimension theDimension) {
        if (myDimension.equals(getSize())) {
            myDimension = theDimension;
            setPreferredSize(myDimension);
        }     
    }
    
    /**
     * This will set the block size on the panel.
     * 
     * @param theBlockSize the incoming block size.
     */
    public void setBlockSize(final int theBlockSize) {
        myBlockSize = theBlockSize;
        
        
        
    }

    /**
     * This will enable or disable grid mode 
     * on or off.
     * 
     * @param theBoolean the incoming boolean value.
     */
    public void setGrid(final boolean theBoolean) {
        myGrid = theBoolean;
        repaint(); 
    }

    /**
     * This will enable or disable dark mode on the Tetris
     * board.
     * 
     * @param theDarkMode the incoming boolean value.
     */
    public void setDarkMode(final boolean theDarkMode) {
        myDarkModeEnabled = theDarkMode;
        
    }
}
