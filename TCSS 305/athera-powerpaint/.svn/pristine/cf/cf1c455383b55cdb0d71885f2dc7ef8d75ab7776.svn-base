/*
 * CreateToolBar.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */

package gui;

import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * This class creates the tool bar to be added to the 
 * main frame. It uses the action that were created and assigns
 * as items to the menu.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class CreateToolBar {
    
    /** The tool bar to hold all the tools. */
    private final JToolBar myToolBar;
    
    /**
     * This will create the tools into menu items so that they will share the 
     * same actions.
     * 
     * @param theName the name of the menu.
     * @param theToolActions the list of tools connected by the same action.
     */
    public CreateToolBar(final String theName, final List<ToolAction> theToolActions) {
        myToolBar = new JToolBar(theName);
        final ButtonGroup group = new ButtonGroup();
        
        for (final ToolAction currentTool : theToolActions) {
            final JToggleButton item = new JToggleButton(currentTool);
            group.add(item);
            myToolBar.add(item);
        }
    }
    
    /**
     * This will give back the tool bar of tools when 
     * called upon.
     * 
     * @return the menu bar containing all the tools.
     */
    public JToolBar getToolBar() {
        return myToolBar;
    }
        
}
