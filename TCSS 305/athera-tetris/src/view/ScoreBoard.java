/*
 * ScoreBoard.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-6
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.TetrisPiece;

/**
 * This class will keep track of the score 
 * of the game that is in progress.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version December 7, 2017 
 */
public class ScoreBoard extends JPanel implements Observer {

    /**
     * A generated serial version UID for object Serialization.
     */
    private static final long serialVersionUID = -7182802873385440142L;
    
    /**
     * This is the default size of this panel.
     */
    private static final Dimension DEFAULT_FRAME_SIZE = new Dimension(200, 250);
    
    /**
     * This is the main font size.
     */
    private static final int MAIN_FONT_SIZE = 20;
    
    /**
     * This is the secondary font size.
     */
    private static final int SECONDARY_FONT_SIZE = 15;
    
    /**
     * This is the amount of rows cleared until you level up.
     */
    private static final int UNTIL_NEXT_LEVEL = 5;
    
    /**
     * This is the intial deylay of the timer.
     */
    private static final int INTIAL_TIMER_DEYLAY = 1000;
    
    /**
     * This is how fast the timer will be speed up when leveled up.
     */
    private static final int SPEED_UP_TIMER = 40;
    
    /**
     * This is how much you score will be multiplied by for one level cleared.
     */
    private static final int SCORE_ONE_LINE = 40;
    
    /**
     * This is how much you score will be multiplied by for two level cleared.
     */
    private static final int SCORE_TWO_LINE = 100;
    
    /**
     * This is how much you score will be multiplied by for three level cleared.
     */
    private static final int SCORE_THREE_LINE = 300;
    
    /**
     * This is how much you score will be multiplied by for four level cleared.
     */
    private static final int SCORE_FOUR_LINE = 1200;
    
    /**
     * This is how much your score will be for a piece frozen sucesfully.
     */
    private static final int SCORE_FOR_SINGLE_PIECE = 4;
    
    /**
     * This is the index of the array.
     */
    private static final int INDEX_THREE = 3;
    
    /**
     * This is the label for top score.
     */
    private static final String TOP_SCORE = "Top Score: ";
    
    /**
     * This is the default font used for all the JLabels.
     */
    private static final String DEFAULT_FONT = "Arial";
    
    /**
     * This is the grid layout to place all scoring information in.
     */
    private final JPanel myGridLayout;
    
    /**
     * This is the timer for moving pieces.
     */
    private final Timer myMoveTimer;
    
    /**
     * This is the current score of the game.
     */
    private int myCurrentScore;
    
    /**
     * This is the total lines cleared during the games.
     */
    private int myTotalLinesCleared;
    
    /**
     * This keeps track of the current level in the game.
     */
    private int myKeepTrackofLevel;
    
    /**
     * This keeps track of the current level in the game.
     */
    private int myCurrentLevel;
    
    /**
     * This is the delay for the timer.
     */
    private int myTimerDelay;
    
    /**
     * This keeps track of the top score in the current game.
     */
    private int myCurrentTopScore;
    
    /**
     * This will store the score.
     */
    private JLabel myScoreLabel;
    
    /**
     * This will store the lines cleared.
     */
    private JLabel myLinesClearedLabel;
    
    /**
     * This will store the level.
     */
    private JLabel myLevelLabel;
    
    /**
     * This will store the next level.
     */
    private JLabel myNextLevelLabel;
    
    /**
     * This will store the top score.
     */
    private JLabel myTopScoreLabel;
    
    /**
     * This will construct the incoming values and will
     * set up all the fields in this class so that they 
     * can be assesable.
     * 
     * @param theMoveTimer the timer for moving pieces.
     */
    public ScoreBoard(final Timer theMoveTimer) {
        super();
        setPreferredSize(DEFAULT_FRAME_SIZE);
        myMoveTimer = theMoveTimer;
        myGridLayout = new JPanel();
        myGridLayout.setLayout(new GridLayout(0, 1));
        setUpFields();
        setUpLayout();
        
    }
    
    /**
     * This is a helper method for the constructor that will
     * help set up the fields.
     */
    private void setUpFields() {
        myCurrentScore = 0;
        myTotalLinesCleared = 0;
        myKeepTrackofLevel = 1;
        myCurrentLevel = 1;
        myTimerDelay = INTIAL_TIMER_DEYLAY;
        myCurrentTopScore = 0;
    }
    
    /**
     * This will set up the layout of all the labels
     * for displaying the score information.
     */
    private void setUpLayout() {
        
        createJLabels("Current Score: ");
        myScoreLabel = new JLabel(Integer.toString(myCurrentScore));
        myScoreLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, 
                                      SECONDARY_FONT_SIZE));
        myGridLayout.add(myScoreLabel);
        
        createJLabels("Total Lines Cleared: ");
        myLinesClearedLabel = new JLabel(Integer.toString(myTotalLinesCleared));
        myLinesClearedLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, SECONDARY_FONT_SIZE));
        myGridLayout.add(myLinesClearedLabel);
        
        createJLabels("Level: ");
        myLevelLabel = new JLabel(Integer.toString((myTotalLinesCleared 
                        / UNTIL_NEXT_LEVEL) + 1));
        myLevelLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, SECONDARY_FONT_SIZE));
        myGridLayout.add(myLevelLabel);
        
        createJLabels("Until Next Level: ");
        myNextLevelLabel = new JLabel(Integer.toString(UNTIL_NEXT_LEVEL 
                                      - (myTotalLinesCleared % UNTIL_NEXT_LEVEL)));
        myNextLevelLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, SECONDARY_FONT_SIZE));
        myGridLayout.add(myNextLevelLabel);
        
        myTopScoreLabel = new JLabel(TOP_SCORE 
        + myCurrentTopScore);
        myTopScoreLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, MAIN_FONT_SIZE));
        myGridLayout.add(myTopScoreLabel);
        
        //createJLabels("Until Next Level  Lines");

     
        add(myGridLayout);
    }
    
    /**
     * This will create the labels and add them to a 
     * grid layout.
     * 
     * @param theName the name of the label.
     */
    private void createJLabels(final String theName) {
        final JLabel currentLabel = new JLabel(theName);
        currentLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, MAIN_FONT_SIZE));
        myGridLayout.add(currentLabel);
        
    }
    
    /**
     * This will update the score of the current game.
     * 
     * @param theScore the amount to add to the score.
     */
    public void updateScore(final int theScore) {
        myCurrentScore += theScore;
        myScoreLabel.setText(Integer.toString(myCurrentScore)); 
        if (myCurrentScore >= myCurrentTopScore) {
            myCurrentTopScore = myCurrentScore;
            myTopScoreLabel.setText(TOP_SCORE + myCurrentTopScore);
        }
    }
    
    /**
     * This is will update the total lines cleared.
     * 
     * @param theRowsCleared how many rows have been cleared.
     */
    public void updateTotalLinesCleared(final int theRowsCleared) {
        myTotalLinesCleared += theRowsCleared;
        myLinesClearedLabel.setText(Integer.toString(myTotalLinesCleared));
    }
    
    /**
     * This will update the current level of the game that is 
     * being played.
     */
    public void updateCurrentLevel() {
        myLevelLabel.setText(Integer.toString((myTotalLinesCleared / UNTIL_NEXT_LEVEL) + 1));
        
        if (!(((myTotalLinesCleared / UNTIL_NEXT_LEVEL) + 1) == myKeepTrackofLevel)) {
            myKeepTrackofLevel = (myTotalLinesCleared / UNTIL_NEXT_LEVEL) + 1;
            myTimerDelay -= SPEED_UP_TIMER;
            myMoveTimer.setDelay(myTimerDelay);
        }
    }
    
    /**
     * This will update the amount of rows until you move onto the next
     * level.
     */
    public void updateUntilNextLevel() {
        myNextLevelLabel.setText(Integer.toString(UNTIL_NEXT_LEVEL 
                                 - (myTotalLinesCleared % UNTIL_NEXT_LEVEL))); 
    }
    
    @Override
    public void update(final Observable theO, final Object theArg) {
        if (theArg instanceof TetrisPiece) {
            updateScore(SCORE_FOR_SINGLE_PIECE);
        } else if (theArg instanceof Integer[]) {
            final Integer[] array = (Integer[]) theArg;
            if (array.length == 1) {
                updateScore(SCORE_ONE_LINE * myCurrentLevel);
            } else if (array.length == 2) {
                updateScore(SCORE_TWO_LINE * myCurrentLevel);
            } else if (array.length == INDEX_THREE) {
                updateScore(SCORE_THREE_LINE * myCurrentLevel);
            } else {
                updateScore(SCORE_FOUR_LINE * myCurrentLevel);
            }
            
            updateTotalLinesCleared(array.length);
            updateCurrentLevel();
            updateUntilNextLevel();
            
        }
        
    }
    
    /**
     * This will reset all the score values when
     * a new game is activated.
     */
    public void setResetValues() {
        myCurrentScore = 0;
        myTotalLinesCleared = 0;
        myKeepTrackofLevel = 1;
        myCurrentLevel = 1;
        myTimerDelay = INTIAL_TIMER_DEYLAY;
        myMoveTimer.setDelay(myTimerDelay);
        
        //Resets all the Jlabels.
        myScoreLabel.setText(Integer.toString(myCurrentScore));  
        myLinesClearedLabel.setText(Integer.toString(myTotalLinesCleared));
        myLevelLabel.setText(Integer.toString((myTotalLinesCleared / UNTIL_NEXT_LEVEL) + 1));
        myNextLevelLabel.setText(Integer.toString(UNTIL_NEXT_LEVEL 
                                                  - (myTotalLinesCleared 
                                                                  % UNTIL_NEXT_LEVEL))); 
    }

}
