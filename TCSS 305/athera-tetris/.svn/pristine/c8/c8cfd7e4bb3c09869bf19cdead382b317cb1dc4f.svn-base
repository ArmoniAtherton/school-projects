/*
 * TetrisGUI.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-6
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;

/**
 * This class sets the GUI so that user can interact with the program.
 * The frame and all of its components are set up and organized.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class TetrisGUI {
    /**
     * The default primary board keys for playing tetris blocks.
     */
    private static final BoardKeys PRIMARY_BOARD_KEYS = new BoardKeys(38, 40, 37, 39, 32);
    
    /**
     * The default secondary board keys for playing tetris blocks.
     */
    private static final BoardKeys SECONDARY_BOARD_KEYS = new BoardKeys(87, 83, 65, 68, 32);
    
    /**
     * This is the intial timer of the timer set the time between each tick.
     */
    private static final int TIMER_DEYLAY = 1000;
    
    /**
     * This allows the user to access to the resource package.
     */
    private static final String RESCOURCES = "/resources/";
    
    /**
     * The main frame to hold all the layouts in.
     */
    private final JFrame myFrame;
    
    /**
     * The standard Tetris game board that holds the blocks, pieces and logic.
     */
    private final Board myBoard;
    
    /**
     * The frame that displays the Tetris game board to the user.
     */
    private TetrisBoardDrawingPanel myTetrisBoard;
    
    /**
     * The frame that displays the next Tetris piece.
     */
    private TetrisPieceDrawingPanel myTetrisPiece;
    
    /**
     * The score panel to keep track of the score of the current game.
     */
    private ScoreBoard myScoreBoard;
    
    /**
     * The timer to keep track of when to move Tetris pieces.
     */
    private Timer myMoveTimer;
    
    /**
     * The key listener to keep track of what keys have been pressed.
     */
    private KeyInputAdapter myKeyListener;
    
    /**
     * The menu that will set up all the menu options available to the user.
     */
    private CreateToolMenu myMenuBar;
    
    /**
     * The main music that will be played during game.
     */
    private Clip myMainSoundClip;
    
    /**
     * The music that will play when game ends.
     */
    private Clip myGameOverSoundClip;
    
    /**
     * This constructs the main frame and all the 
     * properties added to it.
     */
    public TetrisGUI() {
        myFrame = new JFrame("Tetris");
        
        setUpMusic();
        setUpGameOver();
        myBoard = new Board();
        setUpKeyListeners();
        setUpFields();
        setUpFrame();
    }
    
    /**
     * This will set up the main music that will played 
     * when the game is in progress.
     */
    private void setUpMusic() {
        try {
            myMainSoundClip = AudioSystem.getClip();
            final AudioInputStream audioInput = AudioSystem.
                            getAudioInputStream(this.getClass().
                                                getResource(RESCOURCES + "tetrisDub.wav"));
            myMainSoundClip.open(audioInput);
        } catch (final LineUnavailableException | IOException 
                        | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * This will set up the game over music that will played 
     * when the game is in progress.
     */
    private void setUpGameOver() {
        try {
            myGameOverSoundClip = AudioSystem.getClip();
            final AudioInputStream audioInput2 = AudioSystem.
                            getAudioInputStream(this.getClass().
                                                getResource(RESCOURCES + "gameOver.wav"));
            myGameOverSoundClip.open(audioInput2);
        } catch (final LineUnavailableException | IOException 
                        | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Will organize the frame correctly to display it visually to 
     * the user. Will play multiple frames into one anther to allow 
     * for multiple panels to be displayed.
     */
    private void setUpFrame() {
        final JPanel secondFrame = new JPanel();
        final JPanel tetrisPanel = new JPanel();
        final JPanel previewPiecePanel = new JPanel();
        final JPanel scoreBoardPanel = new JPanel();

        secondFrame.setLayout(new BorderLayout());
        myFrame.add(tetrisPanel, BorderLayout.WEST);
        myFrame.add(secondFrame, BorderLayout.EAST);
        secondFrame.add(previewPiecePanel, BorderLayout.NORTH);
        secondFrame.add(scoreBoardPanel, BorderLayout.CENTER);
        tetrisPanel.add(myTetrisBoard);
        previewPiecePanel.add(myTetrisPiece);
        scoreBoardPanel.add(myScoreBoard);

        myMenuBar = new CreateToolMenu(myFrame, myBoard, myTetrisBoard, myMoveTimer,
                                       myKeyListener, myScoreBoard, myMainSoundClip, 
                                       myGameOverSoundClip);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        final Dimension frameDimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) ((frameDimension.getWidth() - myFrame.getWidth()) / 2);
        final int height = (int) ((frameDimension.getHeight() - myFrame.getHeight()) / 2);
        myFrame.setLocation(width, height);
        myFrame.setVisible(true);
        myFrame.setResizable(false);

    }
    
    /**
     * Will set up all the keys and place them into a list 
     * sends these keys to the key listeners. To allow the 
     * user to move Tetris pieces.
     */
    private void setUpKeyListeners() {
        // left
        final List<Integer> keysLeft = new ArrayList<Integer>();
        keysLeft.add(SECONDARY_BOARD_KEYS.getLeftKeys());
        keysLeft.add(PRIMARY_BOARD_KEYS.getLeftKeys());

        // right
        final List<Integer> keysRight = new ArrayList<Integer>();
        keysRight.add(SECONDARY_BOARD_KEYS.getRightKeys());
        keysRight.add(PRIMARY_BOARD_KEYS.getRightKeys());

        // down
        final List<Integer>  keysDown = new ArrayList<Integer>();
        keysDown.add(SECONDARY_BOARD_KEYS.getDownKeys());
        keysDown.add(PRIMARY_BOARD_KEYS.getDownKeys());

        // Up
        final List<Integer> keysUp = new ArrayList<Integer>();
        keysUp.add(SECONDARY_BOARD_KEYS.getUpKeys());
        keysUp.add(PRIMARY_BOARD_KEYS.getUpKeys());

        // Drop
        final List<Integer> keysDrop = new ArrayList<Integer>();
        keysDrop.add(SECONDARY_BOARD_KEYS.getDropKeys());
        //Set up key Listener.
        myKeyListener = new KeyInputAdapter(myBoard, keysLeft, keysRight, keysDown,
                                            keysUp, keysDrop);
       

    }
    
    /**
     * This is a constructor helper method to allow 
     * fields to be instantiated.
     */
    private void setUpFields() {
        myTetrisBoard = new TetrisBoardDrawingPanel(myFrame);
        myTetrisPiece = new TetrisPieceDrawingPanel();
        myMoveTimer = new Timer(TIMER_DEYLAY, new MoveListener());
        myScoreBoard = new ScoreBoard(myMoveTimer);
        
    }
    
    /**
     * This is called by the main will set up the observer to listen 
     * for changes in the program.
     */
    public void start() {
        final ImageIcon image = new ImageIcon(this.
                                              getClass().getResource("/images/tetris.jpg"));
        myFrame.setIconImage(image.getImage());
        myBoard.addObserver(myTetrisBoard);
        myBoard.addObserver(myTetrisPiece);
        myBoard.addObserver(myMenuBar);
        myBoard.addObserver(myScoreBoard);
    }
    
    /**
     * This is a inner class that is called by the timer to 
     * move a tetris piece.
     * 
     * @author Armoni Atherton  athera@uw.edu
     * @version December 7, 2017 
     *
     */
    private class MoveListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.step();

        } // end of MoveListener

    }
}
