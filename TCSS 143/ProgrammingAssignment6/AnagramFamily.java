/*
* AnagramFamily.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 6
*/
import java.util.*;
/**
* This class takes information from the driver class and then
* sets up a AnagramFamily object that stores a group of words based off
* their cannonical form and finally stores the information so it
* can be used later.
*
* @author Armoni Atherton athera@uw.edu
* @version 29 May 2017
*/
public class AnagramFamily {
  /**
  * Stores a list of words with the same cannonical form.
  */
  private List<Word> myFamilyList;
  /**
  * Stores the size of the current anagram family..
  */
  private int myFamilySize;
  /**
   * This method sets up the constructors
   * correctly for the variables to be used throughout the
   * class.
   *
   * @param theFamilyList The incoming list of words.
   */
  public AnagramFamily(List<Word> theFamilyList) {
    //Sets the up the family list.
    myFamilyList = theFamilyList;
    //Sets up the size of the family list.
    myFamilySize = myFamilyList.size();
  }
  /**
   * This method gets the family size of the current anagram family.
   *
   * @return myFamilySize the family size of anagram family.
   */
  public int getFamilySize() {
    //Current size of the anagaram family.
    return myFamilySize;
  }
  /**
   * This gives back the information of the family size, cannonical form
   * of the list and the current contents of the anagram family list
   * so it can be seen visually when printed out.
   *
   * @return Formated (String) of all current AnagramFamily information.
   */
  public String toString() {
    String newList = ""; //New variable to hold information in.
    //Creates a iterator over the objects of words of current anagramList.
    Iterator<Word> itr = myFamilyList.iterator();
    if (itr.hasNext()) {
      //Concatenates the word objects into the string variable.
      newList += itr.next().getMyNormalWord();
    }
    //Will go through all elements in list if more than one.
    while (itr.hasNext()) {
      //Concatenates the word objects into the string variable with commas.
      newList += ", " + itr.next().getMyNormalWord();;
    }
    //Sends back all the information of the anagram Family list.
    return String.format("Number of Words in Anagram Family: %1$d \n" +
            "Canonical form of the Anagram: %2$s \nMembers of family: " +
            "%3$s \n", myFamilySize, myFamilyList.get(0), newList);
  }
}
