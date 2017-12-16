/*
 * CreateToolMenu.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */

package gui;

import com.sun.glass.events.KeyEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * This class creates the tool menu to be added to the 
 * main frame. It uses the action that were created and assigns
 * as items to the menu.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */
public class CreateToolMenu {
    
    /** The tool menu to hold all the tools. */
    private final JMenu myToolBar;
    
    /**
     * This will create the tools into menu items so that they will share the 
     * same actions.
     * 
     * @param theName the name of the menu.
     * @param theToolActions the list of tools connected by the same action.
     */
    public CreateToolMenu(final String theName, final List<ToolAction> theToolActions) {
        myToolBar = new JMenu(theName);
        myToolBar.setMnemonic(KeyEvent.VK_T);
        final ButtonGroup group = new ButtonGroup();
        
        for (final ToolAction currentTool : theToolActions) {
            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(currentTool);
            group.add(item);
            myToolBar.add(item);
        }       
    }
    
    /**
     * This will give back the tool menu of tools when 
     * called upon.
     * 
     * @return the tool menu containing all the tools.
     */
    public JMenu getToolMenu() {
        return myToolBar;
    }
}
