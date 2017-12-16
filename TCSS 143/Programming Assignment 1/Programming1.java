/*
* Programming1.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 1
*/

import java.util.Scanner;

/**
* Prompts the user to enter the population values of 5 towns next it
* draws a horizontal bar graph for each 1000 entered.
*
* @author Armoni Atherton athera@uw.edu
* @version 1 April 2017
*/

public class Programming1 {
/**
* Driver method of getting population and printing correct amount of stars.
*
* @param theArgs is used for command line input.
*/
  public static void main(String[] theArgs) {
    // Calls the method getPopulation and stores it as a variable.
    int town1Population = getPopulation(1);
    int town2Population = getPopulation(2);
    int town3Population = getPopulation(3);
    int town4Population = getPopulation(4);
    int town5Population = getPopulation(5);
    System.out.println();
    System.out.println("POPULATION GRAPH:");
    // Calls the method drawPopulationBar.
    drawPopulationBar(1, town1Population);
    drawPopulationBar(2, town2Population);
    drawPopulationBar(3, town3Population);
    drawPopulationBar(4, town4Population);
    drawPopulationBar(5, town5Population);

  }
  /**
  * This gets the user input of the town population.
  *
  * @param theTownNum contains the town number for correct sentence
  * structure.
  * @return returns the number the user entered.
  */
  public static int getPopulation(int theTownNum) {
    System.out.print("Enter the population of town " + theTownNum + ": ");
    Scanner inp = new Scanner(System.in); // Scanner-keyboard input.
    int input = inp.nextInt(); // Stores users input into input variable.
    return input; // Returns the number the user inputed.
  }
  /**
  * Determins how many stars to print based off users input.
  *
  * @param theTownNum contains the town number for correct sentence
  * structure.
  * @param theTownPop holds the number the user inputed.
  */
  public static void drawPopulationBar(int theTownNum, int theTownPop) {
   // Integer division to find how many times 1000 goes into users input.
   int newTownPop = theTownPop / 1000;
   System.out.print("Town " + theTownNum + ": "); // Print the town number.
   for (int i = 0; i < newTownPop; i++) {
     System.out.print("*"); // Prints a * for each 1000.
   }
   System.out.println(); // Starts a new line.
  }
}
