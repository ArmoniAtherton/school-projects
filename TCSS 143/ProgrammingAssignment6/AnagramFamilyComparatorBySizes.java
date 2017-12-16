/*
* AnagramFamilyComparatorBySizes.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 6
*/
import java.util.Comparator;
/**
* This class organizes anagramFamily lists based off there sizes and
* organizes them from largest to smallest order.
*
* @author Armoni Atherton athera@uw.edu
* @version 29 May 2017
*/
public class AnagramFamilyComparatorBySizes implements
             Comparator<AnagramFamily> {
  /**
   * This compares two AnagramFamily list and
   * sorts them into descending order.
   *
   * @param s1 Contains the first AnagramFamily list.
   * @param s2 Contains the second AnagramFamily list.
   * @return The (int) number to orgainze the AnagramFamily list sizes.
   */
  public int compare(AnagramFamily s1, AnagramFamily s2) {
    //Compares to family sizes and returns which one is bigger.
    return s2.getFamilySize() - s1.getFamilySize();
  }
}
