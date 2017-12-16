/*
* Word.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 6
*/
import java.util.*;
/**
* This class takes information from the driver class and then
* sets up a Word object that can be stored in two different ways
* and finally stores the information so it can be used later.
*
* @author Armoni Atherton athera@uw.edu
* @version 29 May 2017
*/
public class Word implements Comparable<Word>{
  /**
  * Stores the normal form of the word / (String).
  */
  private String myNormalWord;
  /**
  * Stores the cannonical form of the word / (String).
  */
  private String myCanonicalWord;
  /**
   * This method sets up the constructors
   * correctly for the variables to be used throughout the
   * class.
   *
   * @param theNormalWord The incoming word / (String).
   */
  public Word(String theNormalWord) {
    //This sets the word to its normal form.
    myNormalWord = theNormalWord;
    //This stores the word into a cannonical string.
    myCanonicalWord = canonicalConverter(myNormalWord);
  }
  /**
   * This method orgainzes the incoming String into
   * alphabetical order or cannonical form.
   *
   * @param theNormalWord The incoming word / (String).
   * @return organizedString returns word to cannonical form.
   */
  public String canonicalConverter(String theNormalWord) {
    //This puts the word into character array.
    char[] charArray = theNormalWord.toCharArray();
    //Sorts the array from a to z.
    Arrays.sort(charArray);
    //Creates it back into a string.
    String organizedString = new String(charArray);
    //Sends back a string in the form from a to z.
    return organizedString;
  }
  /**
   * This method gets the cannoncial form of the String.
   *
   * @return myCanonicalWord sends back the cannoncial word.
   */
  public String getMyCanonical() {
    //Returns the cannonical word.
    return myCanonicalWord;
  }
  /**
   * This method gets the normal form of the String.
   *
   * @return myNormalWord sends back the normal form of the word.
   */
  public String getMyNormalWord() {
    //Returns the normal word.
    return myNormalWord;
  }
  /**
   * This method organizes words alphabeticaly in its cannoncial from
   * ascending to descending.
   *
   * @param theWord The incoming (Word) name.
   * @return The (int) number to orgainze the words.
   */
  public int compareTo(Word theWord) {
    return (myCanonicalWord).compareTo(theWord.getMyCanonical());
  }
  /**
   * This method prints the cannoncial form of the String.
   *
   * @return myCanonicalWord sends back the cannoncial form of the word.
   */
  public String toString() {
    return myCanonicalWord;
  }
}
