/*
 * TetrisMain.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-5
 */
package view;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import java.awt.EventQueue;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * This program runs Tetris by instantiating and starting 
 * the TetrisGUI.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version November 5, 2017 
 */

public final class TetrisMain {
    
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the Tetris GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        try {
            final Properties props = new Properties();
            props.put("logoString", "Tetris");
            HiFiLookAndFeel.setCurrentTheme(props);

            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            //TextureLookAndFeel.setTheme("Textile", "", "");
            //TextureLookAndFeel.setTheme("Textile", "", "");
            //com.jtattoo.plaf.hifi.HiFiLookAndFeel
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI().start();
            }
        });
    }

}