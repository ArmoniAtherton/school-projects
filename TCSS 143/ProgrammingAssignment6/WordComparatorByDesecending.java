/*
* WordComparatorByDesecending.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 6
*/
import java.util.Comparator;
/**
* This class organizes words descending so that you can organize list
* of words based on this critera.
*
* @author Armoni Atherton athera@uw.edu
* @version 29 May 2017
*/
public class WordComparatorByDesecending implements Comparator<Word> {
  /**
   * This compares two words sorts them descending reorganzing them
   * eventually to z to a.
   *
   * @param s1 Contains the first Word object.
   * @param s2 Contains the second Word object.
   * @return The (int) number to orgainze the word objects.
   */
  public int compare(Word s1, Word s2) {
    //Compares the second word object to the first word object.
    return s2.getMyNormalWord().compareToIgnoreCase(s1.getMyNormalWord());
  }
}
//change name
