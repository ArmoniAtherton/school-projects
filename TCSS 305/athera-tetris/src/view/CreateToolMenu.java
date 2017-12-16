/*
 * CreateToolMenu.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-6
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import model.Board;

/**
 * This class sets the tool menu so that user can interact and customize
 * the game to how they want.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class CreateToolMenu implements Observer {
    /**
     * The large default frame size of the panel.
     */
    private static final Dimension FRAME_SIZE_LARGE = new Dimension(300, 600);
    
    /**
     * The medium default frame size of the panel.
     */
    private static final Dimension FRAME_SIZE_MEDIUM = new Dimension(200, 400);
    
    /**
     * The small default frame size of the panel.
     */
    private static final Dimension FRAME_SIZE_SMALL = new Dimension(100, 200);
    
    /**
     * The large block size of the Tetris pieces.
     */
    private static final int BLOCK_SIZE_LARGE = 30;
    
    /**
     * The medium block size of the Tetris pieces.
     */
    private static final int BLOCK_SIZE_MEDIUM = 20;
    
    /**
     * The small block size of the Tetris pieces.
     */
    private static final int BLOCK_SIZE_SMALL = 10;
    
    /**
     * This is the panel that draws the Tetris Board.
     */
    private final TetrisBoardDrawingPanel myTetrisBoard;
    
    /**
     * The timer to keep track of when to move Tetris pieces.
     */
    private final Timer myMoveTimer;
    
    /**
     * The main frame to hold all the layouts in.
     */
    private final JFrame myFrame;
    
    /**
     * The standard Tetris game board that holds the blocks, pieces and logic.
     */
    private final Board myBoard;
    
    /**
     * The key listener to keep track of what keys have been pressed.
     */
    private final KeyInputAdapter myKeyListener;
    
    
    /**
     * The score panel that keeps track of the current score of the game.
     */
    private final ScoreBoard myScoreBoard;
    
    /**
     * The main music that will be played during game.
     */
    private final Clip myGameOverSoundClip;
    
    /**
     * The music that will play when game ends.
     */
    private final Clip myMainClip;
    
    /**
     * The list containing all the different actions of different sizes.
     */
    private List<SizeAction> mySizeActions;
    
    /**
     * This is will add a new game menu while also allowing you to start a new game.
     */
    private JMenuItem myNewGameMenu;
    
    /**
     * This is will add a end game menu while also allowing you to end a game.
     */
    private JMenuItem myEndGameMenu;
    
    /**
     * This is will add a start/pause game menu while also allowing you to 
     * pause or start a game.
     */
    private JMenuItem myStartAndPauseMenu;
    
    /**
     * This is will check if the game is muted or not.
     */
    private boolean myMuteCheck;
    
    /**
     * This will check if the grid is enabled.
     */
    private boolean myGridEnabledCheck;
    
    /**
     * This will check if the game is pause or not.
     */
    private boolean myPauseEnableCheck;
    
    /**
     * This will check if dark mode is enabled.
     */
    private boolean myDarkModeEnabled;
   
    /**
     * This will construct the tool menu to be displayed 
     * on the main frame. Will set up all the JMenu items.
     * 
     * @param theFrame the main frame from the gui.
     * @param theBoard the board that holds lists of the Tetris pieces.
     * @param theTetrisBoard the board that draws the Tetris game board.
     * @param theMoveTimer the timer to move the tetris pieces.
     * @param theKeyListener the key listener to listen for keys pressed.
     * @param theScoreBoard the board that keeps track of the score.
     * @param theMainClip the main sound that will be played during game.
     * @param theGameOverSoundClip the sound that will be played when game is over.
     */
    public CreateToolMenu(final JFrame theFrame, 
                          final Board theBoard, 
                          final TetrisBoardDrawingPanel theTetrisBoard, 
                          final Timer theMoveTimer,
                          final KeyInputAdapter theKeyListener, 
                          final ScoreBoard theScoreBoard, 
                          final Clip theMainClip, 
                          final Clip theGameOverSoundClip) {
        myFrame = theFrame;
        myBoard = theBoard;
        myTetrisBoard = theTetrisBoard;
        myMoveTimer = theMoveTimer;
        myKeyListener = theKeyListener;
        myScoreBoard = theScoreBoard;
        myMainClip = theMainClip;
        myGameOverSoundClip = theGameOverSoundClip;
        
        setUpFields();
        setUpToolMenu();
    }
    
    /**
     * This is a helper method called by the constructor
     * to set up fields.
     */
    private void setUpFields() {
        mySizeActions = new ArrayList<>();
        myMuteCheck = true;
        myGridEnabledCheck = true;
        myPauseEnableCheck = true;
        myDarkModeEnabled = true;
        
    }
    /**
     * This method will set up all menu bar items. Will create and 
     * instantiated new items while setting it to the main frame.
     * 
     */
    private void setUpToolMenu() {
        //Creates menu bar.
        final JMenuBar bar = new JMenuBar();
        final JMenu game = new JMenu("Game");
        game.add(sizeMenu());
        game.add(newGameMenu());
        game.add(endGameMenu());
        game.add(startMenu());
   
        final JMenu options = new JMenu("Options");
        options.add(createGrid());
        options.add(darkMode());
        options.add(muteMusic());
        
        final JMenu help = new JMenu("Help");
        help.add(aboutMenu());
        help.add(controlsMenu());
        help.add(scoreMenu());
        help.add(citationsMenu());
        
        bar.add(game);
        bar.add(options);
        bar.add(help);
        
        myFrame.setJMenuBar(bar);
        
    }

    /**
     * This will create multiple size actions allowing the frame to 
     * be changed with 3 different sizes.
     * 
     * @return the adjust size panel.
     */
    private JMenu sizeMenu() {
        mySizeActions.add(new SizeAction(myFrame, myTetrisBoard, 
                                         "Large", true, 
                                         FRAME_SIZE_LARGE, BLOCK_SIZE_LARGE));
        mySizeActions.add(new SizeAction(myFrame, myTetrisBoard,
                                         "Medium", false, 
                                         FRAME_SIZE_MEDIUM, BLOCK_SIZE_MEDIUM));
        mySizeActions.add(new SizeAction(myFrame, myTetrisBoard,
                                         "Small", false, 
                                         FRAME_SIZE_SMALL, BLOCK_SIZE_SMALL));

        return setUpPanel("Adjust Size");
    }
    
    /**
     * This will set up all the size menu items and will 
     * put them into a radio group.
     * 
     * @param theName of each JMenu.
     * @return the JMenu with the action attached to it.
     */
    private JMenu setUpPanel(final String theName) {
        final JMenu name = new JMenu(theName);
        name.addSeparator();
        final ButtonGroup group = new ButtonGroup();
        
        for (final SizeAction currentSize : mySizeActions) {
            final JRadioButton item = new JRadioButton(currentSize);
            group.add(item);
            name.add(item);
        }       
        return name;
        
    }
    
    /**
     * This will create a new game menu that allows the 
     * user to start a new Tetris game if a game is not in
     * progress.
     * 
     * @return the new game menu to be added to the options bar.
     */
    private JMenuItem newGameMenu() {
        myNewGameMenu = new JMenuItem("New Game");
        myNewGameMenu.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.newGame();
                if (myMoveTimer.isRunning()) {
                    myFrame.removeKeyListener(myKeyListener);
                    myMoveTimer.stop();
                }
                myFrame.addKeyListener(myKeyListener);
                myMoveTimer.start();
                myScoreBoard.setResetValues();
  
                myNewGameMenu.setEnabled(false);
                myStartAndPauseMenu.setEnabled(true);
                myEndGameMenu.setEnabled(true);
                myMainClip.loop(Clip.LOOP_CONTINUOUSLY);
                myMainClip.start();
            }
        });
        return myNewGameMenu;
    } 
    
    /**
     * This will create a end game menu that allows the 
     * user to stop a Tetris game that is in 
     * progress.
     * 
     * @return the end game menu to be added to the options bar.
     */
    private JMenuItem endGameMenu() {
        myEndGameMenu = new JMenuItem("End Game");
        myEndGameMenu.setEnabled(false);
        myEndGameMenu.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myMoveTimer.stop();
                myFrame.removeKeyListener(myKeyListener);
                
                myEndGameMenu.setEnabled(false);
                myNewGameMenu.setEnabled(true);
                myStartAndPauseMenu.setEnabled(false);
                //myPauseMenu.setEnabled(false);
           
                JOptionPane.showMessageDialog(null,
                                              "\tGame Over!" 
                                              + "\n Under Game Tab Click New "
                                              + "Game To Try Again!",
                                              "Game Over", JOptionPane.PLAIN_MESSAGE);      
            }
        });
        return myEndGameMenu;
    }
    
    /**
     * This will create a start game menu that allows the 
     * user to start a Tetris game when no game is in 
     * progress.
     *  
     * @return the start menu to be added to the options bar.
     */
    private JMenuItem startMenu() {
        myStartAndPauseMenu = new JMenuItem("Pause");
        myStartAndPauseMenu.setEnabled(false);
        myStartAndPauseMenu.setAccelerator(KeyStroke.getKeyStroke('p'));
        
        myStartAndPauseMenu.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myPauseEnableCheck) {
                    myMoveTimer.stop();
                    myFrame.removeKeyListener(myKeyListener);
                    myPauseEnableCheck = false;
                    
                } else {
                    myMoveTimer.start();
                    myFrame.addKeyListener(myKeyListener);
                    myPauseEnableCheck = true;
                }
            }
        });
        return myStartAndPauseMenu;
    }
    
    /**
     * This will create a create grid menu that allows the 
     * user to turn on a grid on the game board that will allow 
     * the user to active and de-activate. 
     *   
     * @return the grid menu to be added to the options bar.
     */
    private JMenuItem createGrid() {
        final JMenuItem grid = new JMenuItem("Grid");
        
        grid.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myGridEnabledCheck) {
                    myTetrisBoard.setGrid(true);
                    myGridEnabledCheck = false;
                } else {
                    myTetrisBoard.setGrid(false);
                    myGridEnabledCheck = true;
                }
               
                
            }
        });
        
        return grid;
    }
    
    /**
      * This will create a create dark menu that allows the 
     * user to turn on a dark mode on the game board that will allow 
     * the user to active and de-activate dark mode.  
     * 
     * @return the dark mode menu to be added to the options bar.
     */
    private JMenuItem darkMode() {
        final JMenuItem darkMode = new JMenuItem("Dark Mode");
        darkMode.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myDarkModeEnabled) {
                    myTetrisBoard.setDarkMode(true);
                    myDarkModeEnabled = false;
                } else {
                    myTetrisBoard.setDarkMode(false);
                    myDarkModeEnabled = true;
                }
                
            }
        });
        
        return darkMode;
    }

    /**
     * This will create a create mute menu that allows the 
     * user to mute the music on the game board that will allow 
     * the user to active and de-activate the music.  
     * 
     * @return the music pause menu to be added to the options bar.
     */
    private JMenuItem muteMusic() {
        final JMenuItem mute = new JMenuItem("Mute: OFF");
        
        mute.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (myMuteCheck) {
                    myMainClip.stop();
                    mute.setText("Music: ON");
                    myMuteCheck = false;
                } else {
                    myMuteCheck = true;
                    myMainClip.start();
                    mute.setText("Music: OFF");
                }
                
                
            }
        });
        return mute;
    }
   
    /**
     * This will create a about menu that allows the 
     * user to look at information about the creator of the game.
     * 
     * @return the options menu to be added to the tool bar.
     */
    private JMenuItem aboutMenu() {
        final JMenuItem about = new JMenuItem("About...");
        about.addActionListener(new ActionListener() {
            
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.
                            showMessageDialog(null,
                            "Armoni Atherton\nAutumn 2017\nTCSS 305 Assignment 6",
                            "Tetris", JOptionPane.PLAIN_MESSAGE);    
            }
        });
        return about;
    }
    
    /**
     * This will create a control menu that allows the 
     * user to look at information about the controls of the game.
     * 
     * @return the controls menu to be added to the tool bar.
     */
    private JMenuItem controlsMenu() {
        final JMenuItem controls =  new JMenuItem("Controls...");
        controls.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final String builtString = buildDirectionString();
                JOptionPane.
                    showMessageDialog(null,
                                      builtString,
                                      "Tetris Controls", JOptionPane.PLAIN_MESSAGE);    
            }
        });
        return controls;
    }
    
    /**
     * This will create a score menu that allows the 
     * user to look at information about the scoring of the game.
     * 
     * @return the scores information menu to be added to the tool bar.
     */
    private JMenuItem scoreMenu() {
        final JMenuItem score =  new JMenuItem("Score...");
        score.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.
                    showMessageDialog(null,
                                      "The Level is equal to n,\n"
                                      + "1 Row Cleared: 40 * (n)\n2 Row Cleared: 100 * (n)\n"
                                      + "3 Row Cleared: 300 * (n)\n4 Row Cleared: 1200 * (n)\n"
                                      + "Every time a piece freezes in place" 
                                      + " 4 points are added to the score.",
                                      "Tetris Score", JOptionPane.PLAIN_MESSAGE);    
            }
        });
        return score;
    }
    
    /**
     * This will create a citation menu that allows the 
     * user to look at resources that have been used to make the game.
     * 
     * @return the citation information menu to be added to the tool bar.
     */
    private JMenuItem citationsMenu() {
        final JMenuItem citations =  new JMenuItem("Citations...");
        citations.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.
                    showMessageDialog(null,
                                      "Music Used:\n"
                                      + "https://www.youtube.com/watch?v=E8FQBjVlERk"
                                      + "\nhttps://www.youtube.com/watch?v=vJaAy3jmEx8\n"
                                      + "\nImages Used:\n"
                                      + "https://i2.wp.com/retrogamingmagazine.com/wp-content"
                                      + "/uploads/2016/06/Tetris_Elektronite_"
                                      + "Mattel_Intellivision."
                                      + "jpg?fit=480%2C300&resize=350%2C200\n"
                                      + "https://i.ytimg.com/vi/qDDqX9Zo5aE/"
                                      + "maxresdefault.jpg\n\nTheme Used:\n"
                                      + "http://www.jtattoo.net/",
                                      "Citations", JOptionPane.PLAIN_MESSAGE);    
            }
        });
        return citations;
    }
    /**
     * This will create a string about the controls of the 
     * game. Will store it as a string.
     * 
     * @return the completed and built string about the controls of the game.
     */
    private String buildDirectionString() {
        return String.format("Move Left:\nLeft arrow and 'a' and \"A\"\n\n"
                             + "Move Right:\nRight arrow and 'd' and \"D\"\n\n"
                             + "Rotate:\nUp arrow and 'w' and \"W\"\n\n"
                             + "Move Down:\nDown arrow and 's' and \"S\"\n\n"
                             + "Drop:\nSpace");
    }
    
    /**
     * This will update the class every timer the game is over. Will
     * stop the music and will disable buttons when the game is over.
     */
    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof Boolean) {
            myMainClip.stop();
            try {
                myGameOverSoundClip.close();
                myGameOverSoundClip.open(
                                         AudioSystem.getAudioInputStream(
                                         this.getClass().
                                         getResource("/resources/gameOver.wav")));
            } catch (final LineUnavailableException 
                            | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
            myGameOverSoundClip.start();
            myNewGameMenu.setEnabled(true);
            myStartAndPauseMenu.setEnabled(false);
            myEndGameMenu.setEnabled(false);
        }
    }
}
