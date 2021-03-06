/*
 * DrawingPanel.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */
package draw;

import gui.PowerPaintGUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import shape.ShapeInformation;
import tools.DrawTool;
import tools.EraserTool;
import tools.LineTool;
import tools.PencilTool;

/**
 * This class sets up the drawing panel with 
 * mouse listeners.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class DrawingPanel extends JPanel { 
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -5980366470331898625L;
    
    /** The default Color for primary click. */
    private static final Color HUSKY_PURPLE = new Color(51, 0, 111);
    
    /** The default Color for secondary click. */
    private static final Color HUSKY_GOLD = new Color(232, 211, 162);
    
    /** The default Dimension for the size of the frame. */
    private static final Dimension DEFAULT_FRAME_SIZE = new Dimension(500, 300);
    
    /** The color for the primary click.  */
    private Color myPrimaryColor;
    
    /** The Color for secondary click. */
    private Color mySecondaryColor;
    
    /** The Color for the currently selected shape. */
    private Color myCurrentColor;
    
    /** The width for the shape. */
    private int myWidth;
    
    /** The Lists of current shapes that have been created. */
    private List<ShapeInformation> myShapes;
    
    /** The shape that is held for when dragging on screen. */
    private ShapeInformation myDraggingShape;
    
    /** The current shape that holds the information about it. */
    private ShapeInformation myCurrentShape;
    
    /** The current tool that is being created. */
    private DrawTool myCurrentTool;
    
    /**
     * This is the constructor to set up the drawing panel
     * will set up mouse listeners and preferred size.
     */
    public DrawingPanel() {
        super();
        setPreferredSize(DEFAULT_FRAME_SIZE);
        setBackground(Color.WHITE);
        setUpFields();
        setUpMouseListners();
    }
    
    /**
     * This sets up all the fields for the constructor 
     * so that the variables have been initialized.
     */
    private void setUpFields() {
        myPrimaryColor = HUSKY_PURPLE;
        mySecondaryColor = HUSKY_GOLD;
        myCurrentColor = HUSKY_PURPLE;
        myShapes = new ArrayList<ShapeInformation>();
        myDraggingShape = new ShapeInformation(null, null, null);
        myCurrentTool = new LineTool();
       
    }
    
    /**
     * This will paint all the shapes that have been drawn
     * will print them according to there size, width and 
     * color.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) { 
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (final ShapeInformation currentShapes : myShapes) {
            g2d.setPaint(currentShapes.getColor());
            g2d.setStroke(new BasicStroke(currentShapes.getWidth()));
            g2d.draw(currentShapes.getShape());   
        }
        if (myDraggingShape.getShape() != null) {
            g2d.setStroke(new BasicStroke(myDraggingShape.getWidth()));
            g2d.setPaint(myDraggingShape.getColor());
            g2d.draw(myDraggingShape.getShape());
        }
    }
   
    /**
     * This connects the mouse adapter to the drawing panel 
     * when creating a new drawing panel object.
     */
    private void setUpMouseListners() {
        final MouseInputAdapter connect = new MyMouseInputAdapter();
        addMouseListener(connect);
        addMouseMotionListener(connect);
    }
    
    /**
     * This will set the Primary color from the PowerPaintGUI.
     * 
     * @param theColor this will be the incoming primary color.
     */
    public void setPrimaryColor(final Color theColor) {
        myPrimaryColor = theColor;
    }
    
    /**
     * This will set the Secondary color from the PowerPaintGUI.
     * 
     * @param theColor this will be the incoming secondary color.
     */
    public void setSecondaryColor(final Color theColor) {
        mySecondaryColor = theColor;    
    }
    
    /**
     * This will set the width from the PowerPaintGUI.
     * 
     * @param theWidth this will be the incoming width.
     */
    public void setSliderWidth(final int theWidth) {
        myWidth = theWidth;
    }
    
    /**
     * This will set the tool from the PowerPaintGUI.
     * 
     * @param theTool the current tool selected.
     */
    public void setTool(final DrawTool theTool) {
        myCurrentTool = theTool;
    }
    
    /**
     * This will clear the shapes on the screen.
     */
    public void setClearList() {
        myShapes.clear();
        myDraggingShape.setShape(null);
        repaint();
    }
    
    /**
     * This is inner class that takes care of all the mouse listeners
     * to listen for specific mouse events that the user inputs.
     * 
     * @author Armoni Atherton athera@uw.edu
     * @version November 5, 2017 
     */
    public class MyMouseInputAdapter extends MouseInputAdapter {
        /**
         * This will listen if the mouse is pressed and check 
         * if it is primary click or secondary click.
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (!(myWidth == 0)) {
                myCurrentTool.setStartPoint(theEvent.getPoint());
                if (myCurrentTool instanceof EraserTool) {
                    myCurrentColor = Color.WHITE;
                    myCurrentShape = new ShapeInformation(myCurrentTool.getShape(), 
                                                          myCurrentColor, myWidth);
                } else {
                    firePropertyChange(PowerPaintGUI.CLEAR, false, true);
                    myCurrentShape = new ShapeInformation(myCurrentTool.getShape(), 
                                                          myCurrentColor, myWidth);
                }
                if (theEvent.getButton() == MouseEvent.BUTTON1) {
                    if (!(myCurrentTool instanceof EraserTool)) {
                        myCurrentColor = myPrimaryColor;
                        myCurrentShape.setColor(myPrimaryColor);
                    } 
                } else {
                    if (!(myCurrentTool instanceof EraserTool)) {
                        myCurrentColor = mySecondaryColor;
                        myCurrentShape.setColor(mySecondaryColor);
                    } 
                }
            }
            
        }
        
        /**
         * This will listen to if the mouse is released if so will assign
         * the shape to a list to be painted.
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (!(myWidth == 0)) {
                myCurrentTool.setEndPoint(theEvent.getPoint());
     
                myCurrentShape.setShape(myCurrentTool.getShape());
                myCurrentShape.setWidth(myWidth);
                myShapes.add(myCurrentShape);
                repaint();
            }
            
            if (myCurrentTool instanceof PencilTool) {
                ((PencilTool) myCurrentTool).resetTool();
            }
            
            if (myCurrentTool instanceof EraserTool) {
                ((EraserTool) myCurrentTool).resetTool();
            }
        }
        
        /*
         * This will check if the shape is being dragged and will
         * update the shape while also repainting it.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (!(myWidth == 0)) {
                myCurrentTool.setEndPoint(theEvent.getPoint());
                myDraggingShape.setShape(myCurrentTool.getShape());
                myDraggingShape.setColor(myCurrentColor);
                myDraggingShape.setWidth(myWidth);
              
                repaint();
            }
        }
        
        /**
         * This will check if the user enters the drawing screen and will
         * change the cursor if entered.
         */
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
    }
}