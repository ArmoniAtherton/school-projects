/*
 * SnapShopGUI.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-3
 */

package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * This program will create the graphical user interface
 * and will allow users to use filters and open files.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 1, 2017 
 */
public class SnapShopGUI {
    /**
     * This holds the amount of columns for the grid layout.
     */
    private static final int COLUMN = 1;
    
    /**
     * This holds the default rows for the grid layout.
     */
    private static final int ROW = 0;
    
    /**
     * The main frame of the entire layout.
     */
    private final JFrame myFrame;
    
    /**
     * Holds a list of buttons for the filters.
     */
    private final List<JButton> myButtons;
    
    /**
     * The current image that is stored.
     */
    private PixelImage myCurrentImage;
    
    /**
     * The Label that contains a image.
     */
    private final JLabel myIconLabel;
    
    /**
     * The JFile object to set open files.
     */
    private final JFileChooser myCurrentChooser;
    
    /**
     * The panel to hold the filters within the main frame.
     */
    private final JPanel myCurrentFilters;
    
    /**
     * The panel to hold the current directories 
     * such as open, close and save.
     */
    private final JPanel myCurrentDirectories;
    
    /**
     * Holds the button for the name that says open.
     */
    private final JButton myOpenFile;
    
    /**
     * Holds the button for the name that says saveAs.
     */
    private final JButton mySaveFileAs;
    
    /**
     * Holds the button for the name that says close.
     */
    private final JButton myCloseFile;
    
    /**
     * Hold the current directory of the user.
     */
    private File myCurrentDirectory;
   
    /**
     * This is the constructor that will set up all the panels for
     * displaying the on the main frame for the users to see and interact with.
     */
    public SnapShopGUI() {
        myFrame = new JFrame("TCSS 305 SnapShop");
        myButtons = new ArrayList<>();
        myIconLabel = new JLabel(new ImageIcon());
        myCurrentChooser = new JFileChooser();
        myCurrentFilters = new JPanel(new GridLayout(ROW, COLUMN));
        myCurrentDirectories = new JPanel(new GridLayout(ROW, COLUMN));
        myOpenFile = new JButton("Open...");
        mySaveFileAs = new JButton("Save As...");
        myCloseFile = new JButton("Close Image");
        myCurrentDirectory = new File(".");
    }
    
    /**
     * This is called by the main and sets up panels while also 
     * filling all the panels with correct information by different calls
     * to methods.
     */
    public void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //This sets up the layout how it will look
        setUpLayoutManger();
        setUpActionListnersUtility();
        setUpFilterObjects();
        myFrame.setMinimumSize(new Dimension(0, 0));
        myFrame.pack();
        myFrame.setMinimumSize(myFrame.getSize());
        myFrame.setVisible(true);
    }
    
    /**
     * This sets up the layout and places them inside the main
     * border for correct display to the user.
     */
    private void setUpLayoutManger() {
        //Creates all the layouts within the main one.
        final JPanel userOptions = new JPanel(new BorderLayout());
        final JPanel secondImageBorder = new JPanel(new BorderLayout());
        final JPanel thirdImageBorder = new JPanel(new BorderLayout());
        
        //This will hold all the layouts in correct ways
        //main frame
        myFrame.add(userOptions, BorderLayout.WEST);
        //main frame puts the image in center
        myFrame.add(secondImageBorder, BorderLayout.CENTER);
        secondImageBorder.add(thirdImageBorder, BorderLayout.NORTH);
        thirdImageBorder.add(myIconLabel, BorderLayout.WEST);
        //Second border Main Frame adds to north border .
        userOptions.add(myCurrentFilters, BorderLayout.NORTH);
        //Second border Main Frame adds to the south border.
        userOptions.add(myCurrentDirectories, BorderLayout.SOUTH);
    }
    
    /**
     * This will set up all the action listeners for 
     * open, close and save. Will call other methods to do 
     * correct things such as opening the file.
     */
    private void setUpActionListnersUtility() { 
        myOpenFile.addActionListener(e -> actionOpen());
        myCurrentDirectories.add(myOpenFile);
        
        mySaveFileAs.setEnabled(false);
        mySaveFileAs.addActionListener(e -> actionSaveFileAs());
        myCurrentDirectories.add(mySaveFileAs);
        
        myCloseFile.setEnabled(false);
        myCloseFile.addActionListener(e -> actionCloseFile());
        myCurrentDirectories.add(myCloseFile);
    }
    
    /**
     * This will set up all the filters so that they can be 
     * changed dynamically will create them into buttons and then
     * put the buttons into a grid layout.
     */
    private void setUpFilterObjects() {
        
        final List<Filter> filterObjects = new ArrayList<>();
        filterObjects.add(new EdgeDetectFilter());
        filterObjects.add(new EdgeHighlightFilter());
        filterObjects.add(new FlipHorizontalFilter());
        filterObjects.add(new FlipVerticalFilter());
        filterObjects.add(new GrayscaleFilter());
        filterObjects.add(new SharpenFilter());
        filterObjects.add(new SoftenFilter());
        
        //Iterates through all the filters adds them to a list
        for (final Filter filters : filterObjects) {
            myButtons.add(createButton(filters));
        }
        
        //Adds all the buttons to the gridLayout.
        for (final JButton button: myButtons) {
            button.setEnabled(false);
            myCurrentFilters.add(button);
        }
        
    }
    
    /**
     * This will create the filters make them into new button
     * objects while at the same time setting up a action 
     * listener for each of those buttons.
     * 
     * @param theFilterObject The incoming current filter.
     * @return the filter that has now been put into a button.
     */
    private JButton createButton(final Filter theFilterObject) {
        final JButton button = new JButton(theFilterObject.getDescription());
        //This will add a lambda action listener.
        button.addActionListener(e -> {
            theFilterObject.filter(myCurrentImage);
            myIconLabel.setIcon(new ImageIcon(myCurrentImage));
        });
        return button;
    }
    
    /**
     * This will open the file when the action listener of the 
     * JButton is activated. Will open to the current workspace 
     * directory.
     * 
     */
    private void actionOpen() {
        myCurrentChooser.setCurrentDirectory(myCurrentDirectory);
        final int result = myCurrentChooser.showOpenDialog(myFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            myCurrentDirectory = myCurrentChooser.getSelectedFile();
            try {
                myCurrentImage = PixelImage.load(myCurrentChooser.getSelectedFile());
                myIconLabel.setIcon(new ImageIcon(myCurrentImage));
                setButtonsVisible(true);
                setButtons(mySaveFileAs, myCloseFile, true);
                myFrame.setMinimumSize(new Dimension(0, 0));
                myFrame.pack();
                myFrame.setMinimumSize(myFrame.getSize());
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(myFrame, 
                                              "The selected file did not contain an image!",
                                              "Open Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * This will save the file when asked to do so by the user
     * in a certain spot specified by the user.
     * 
     */
    private void actionSaveFileAs() {
        myCurrentChooser.setCurrentDirectory(myCurrentDirectory);
        final int result = myCurrentChooser.showSaveDialog(myFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            myCurrentDirectory = myCurrentChooser.getSelectedFile();
            try {
                myCurrentImage.save(myCurrentChooser.getSelectedFile());
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(myFrame, 
                                             "Problem Occured When Attempting To Save",
                                             "Saving Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * This will close the file when asked to do so by the user.
     * Will return the GUI back to its original state with no picture
     * in the frame at all.
     * 
     */
    private void actionCloseFile() {
        
        myIconLabel.setIcon(new ImageIcon());
        setButtonsVisible(false);
        setButtons(mySaveFileAs, myCloseFile, false);
        myFrame.setMinimumSize(new Dimension(0, 0));
        myFrame.pack();
        myFrame.setMinimumSize(myFrame.getSize());
    }
    
    /**
     * This is used for enabling all filter buttons on or off
     * if a image is closed.
     * 
     * @param theValue The incoming value of true or false.
     */
    private void setButtonsVisible(final boolean theValue) {
        for (final JButton button: myButtons) {
            button.setEnabled(theValue);
        }
    }
    
    /**
     * This is used for enabling buttons such as save as and close.
     * Will activate if the user has closed a file or have opened one.
     * 
     * @param theSaveAs The incoming save button.
     * @param theFileClose the incoming close button.
     * @param theValue The incoming value of true or false.
     */
    private void setButtons(final JButton theSaveAs, final JButton theFileClose, 
                           final boolean theValue) {
        theSaveAs.setEnabled(theValue);
        theFileClose.setEnabled(theValue);
    }
}