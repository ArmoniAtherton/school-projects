/*
* Assignment6.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 6
*/
import java.util.*;
import java.io.*;
/**
* This program is used as a driver program to take input from a
* file and use that input to create words and make AnagramFamilies.
* If it can create the AnagramFamily it will output in a organized fashion.
*
* @author Armoni Atherton athera@uw.edu
* @version 29 May 2017
*/
public class Assignment6 {
  /**
   * Driver program to open files and then create shapes of many types
   * calls other classes.
   *
   * @param theArgs is used for command-line input arguments.
   *                Not used here.
   */
  public static void main(String[] theArgs) {
    //Starts the time for how long the program takes to run in nanoSeconds.
    long startTime = System.nanoTime();
    Scanner input = null;
    PrintStream output = null;
    String fileIn = "words.txt"; //Stores input file name.
    String fileOut = "out6.txt"; //Stores the output file name.
    Boolean test = false; //Checks if file was openend succesfully.
    try {
      input = new Scanner(new File(fileIn)); //Opens file with scanner.
      //Sets up file for output.
      output = new PrintStream(new File(fileOut));
      test = true; //Shows it opened files succesfully.

    } catch (FileNotFoundException e) {
      System.out.print("File not found " + e);
    }
    //Checks to make sure files were open succesfully.
    if (test) {
      //Holds a linked list of word objects.
      List<Word> originalList = assignInput(input);
      //Sorts the word objects to assending based off cannonical form.
      Collections.sort(originalList);
      //Stores a list of Anagram Families depending on word object form.
      List<AnagramFamily> anagramFamilyList = assignFamilies(originalList);
      //Organizes the anagramFamilyList list decending based off sizes.
      Collections.sort(anagramFamilyList,
                       new AnagramFamilyComparatorBySizes());
      //Prints to an output file.
      outputToFile(anagramFamilyList, output);
      long endTime = System.nanoTime(); //Stops timer for program.
      long totalTime = endTime - startTime; //Calculates total time.
      //Prints out to console the total time in seconds.
      System.out.println("Total Time To Run Program in Seconds: " +
                          totalTime / 1000000000.0);
      }
    }
    /**
     * This method takes a file and reads input in from it and Creates
     * word object into a linked list of words.
     *
     * @param theInput The incoming file of words.
     * @return tempList list of words from input file.
     */
    public static List<Word> assignInput(Scanner theInput) {
      //List to store the words into.
      List<Word> tempList = new LinkedList<Word>();
      //Goes through entire file and makes words until it reaches the end.
      while(theInput.hasNext()) {
        //Adds it to the list.
        tempList.add(new Word(theInput.next()));
      }
      //Full list of words based off input file.
      return tempList;
    }
    /**
     * This method takes a list of words and Creates a anagram family
     * based on if the cannonical words in the list are the same.
     *
     * @param theOriginalList The incoming list of words.
     * @return anagramList list of Anagram Families.
     */
    public static List<AnagramFamily> assignFamilies(List<Word>
                                                     theOriginalList) {
      //Stores the list of anagram Families.
      List<AnagramFamily> anagramList = new LinkedList<AnagramFamily>();
      //TempList to store the word objects.
      List<Word> tempList = new LinkedList<Word>();
      //Creates a list iterator.
      ListIterator<Word> itr = theOriginalList.listIterator();
      //Gets the first word in the list.
      Word firstWord = theOriginalList.get(0);
      //Iterates through file until it reaches the end.
      while(itr.hasNext()) {
        //Sets the second word to the next word object in the list.
        Word secondWord = itr.next();
        //Compares to see if they have same cannoncial form.
        if (firstWord.getMyCanonical().equals(
            secondWord.getMyCanonical())) {
          //Adds them to a list of words with same cannoncial form.
          tempList.add(secondWord);
        } else { //If not the same form.
            //Sorts the list based off normal form of words descending.
            Collections.sort(tempList, new WordComparatorByDesecending());
            //Finaly creates a AnagramFamily with words of same cannoncial.
            anagramList.add(new AnagramFamily(tempList));
            //Creates a new templist to store new word objects into.
            tempList = new LinkedList<Word>();
            //Back tracks the iterator so it doesnt skip over a value.
            secondWord = itr.previous();
        }
        //Sets the words to each other to make sure it compares correctly.
        firstWord = secondWord;
      }
      /* Same thing as above but gets the last tempList created since never
         reaches the else statement */
      Collections.sort(tempList, new WordComparatorByDesecending());
      anagramList.add(new AnagramFamily(tempList));
      tempList = new LinkedList<Word>();
      //Returns the anagram families.
      return anagramList;
    }
    /**
     * This method prints out the top 5 anagram familes, then prints out
     * all the familes that have the size of 8 and finally it prints
     * the last family in the list created.
     *
     * @param theFamilyList The incoming lists of anagram families.
     * @param theOutput Allows us to print to the output file.
     */
    public static void outputToFile(List<AnagramFamily> theFamilyList,
                                    PrintStream theOutput) {
      //Prints to output file.
      theOutput.println("The Top Five Familes: \n");
      //Prints the top 5 familes.
      for (int i = 0; i <= 4; i++ ) {
        //Gets the index of that anagram family and prints it.
        theOutput.println(theFamilyList.get(i));
      }
      //Prints to output file.
      theOutput.println("The Families of Eight: \n");
      //Sets up iterator to go through anagram list.
      Iterator<AnagramFamily> itr = theFamilyList.iterator();
      while (itr.hasNext()) {
        //Sets iterator to the current list.
        AnagramFamily currentList = itr.next();
        //Checks to see if current list is equal to 8.
        if (currentList.getFamilySize() == 8) {
          //Outputs to file if size is equal to eight.
          theOutput.println(currentList);
        }
      }
      //Prints to output file.
      theOutput.println("The Very Last Family in List: \n");
      //prints the very last family in list.
      theOutput.println(theFamilyList.get(theFamilyList.size() - 1));
    }
}
