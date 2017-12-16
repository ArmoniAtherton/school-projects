/*
* Assign_2.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 2
*/

import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

/**
* This program will perform a basic addition, subtraction,
* and multiplication of two matrices from an input file
* to an output file.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 April 2017
*/

public class Assign_2 {
  /**
  * Driver method of correct matrices calcuations and outputing
  * to correct files.
  *
  * @param theArgs is used for command line input.
  */
   public static void main(String[] theArgs) {
      Scanner input = null;
      PrintStream output = null;
      String inFileName = "in2a.txt"; //Stores input file name.
      String outFileName = "out2a.txt"; //Stores output file name.
      boolean filesOk = false; //Boolean logic to dictate if you can
                            //Move on or not.
      try {
         input = new Scanner(new File(inFileName));
         output = new PrintStream(new File(outFileName));
      //Shows succesfully open the file and can move on to if statement.
         filesOk = true;
      }
      catch (FileNotFoundException e) {
         System.out.println("Cant open file - " + e);
      }
      if (filesOk) {
        //Calls the methods and stores them so they can be refrenced
        //later in the program.
         int [][] firstArray = readFileInput(input);
         int [][] secondArray = readFileInput(input);
         int [][] thirdArray = readFileInput(input);
         int [][] fourthArray = readFileInput(input);
         int [][] addSum = ArrayMath.addArrays(firstArray, secondArray);
         int [][] subSum = ArrayMath.minusArrays(firstArray, secondArray);
         int [][] multSum = ArrayMath.multiplyArray(thirdArray,
                                                    fourthArray);
         printOutputToFile(firstArray, secondArray, addSum, "+",
                          output, "A", "B");
         printOutputToFile(firstArray, secondArray, subSum, "-",
                          output, "A", "B");
         printOutputToFile(thirdArray, fourthArray, multSum, "X",
                          output, "C", "D");
      }
      if (input != null){ //Closes Scanner.
           input.close();
      }
   }
  /**
   * This method reads the input file and then converts the
   * numbers on the .txt file into to a 2d array in which is
   * then returned.
   *
   * @param theInput is a Scanner that contains the .txt file
   * to read from.
   * @return newArray contains a 2d array of integer numbers.
   */
   public static int[][] readFileInput(Scanner theInput) {
      int getRow = theInput.nextInt(); //Gets the row number.
      int getColumn = theInput.nextInt(); //Gets the column number.
      int [][] newArray = new int [getRow][getColumn]; //Creates new array.

      for (int r = 0; r < getRow; r++) {
         for (int c = 0; c < getColumn; c++) {
            newArray[r][c]= theInput.nextInt();
         }
      }
      return newArray; //Returns a 2D array.
   }
  /**
  * This method organizes the incoming 2D arrays. It then ouputs
  * to a file printing each array in a organized fashion from
  * left to right including operator symbols in between arrays.
  *
  * @param theAr1 Original 2D array from input file.
  * @param theAr2 Original 2D array from input file.
  * @param theAr3 Modified 2D array that has been multiplied,
  * added or subtracted.
  * @param theOper Holds the operator type to put between arrays.
  * @param theOutput Contains PrintStream to write to the file.
  * @param theLetter1 Holds the array letter for printing format.
  * @param theLetter Holds the array letter for printing format.
  */
   public static void printOutputToFile(int [][] theAr1, int [][] theAr2,
                                      int [][] theAr3, String theOper,
                                      PrintStream theOutput,
                                      String theLetter1,
                                      String theLetter2) {
    //This prints the title Above each set of matrices.
      theOutput.println("MATRIX " + theLetter1 + " PLUS MATRIX "
                      + theLetter2 + ": ");
      theOutput.println(); //Prints empty line
    //This goes through each row of the matrices
      for (int r = 0; r < theAr1.length || r < theAr2.length; r++) {
      //Iterates through the columns prints corresponding spaces.
         for (int c = 0; c < theAr1[0].length; c++) {
            if (r >= theAr1.length) {
              theOutput.print(String.format("%1$10s",""));
            }
            else {
            //Prints number index based off row and column of that matrix.
               theOutput.print(String.format("%1$10d", theAr1[r][c]));
            }
         }
         if (r == 1 ) {
          //If on the second row prints operator between matrices.
            theOutput.print(String.format("%1$10s", theOper));
         }
         else {
          //If not on second row print correct amount of empty space.
            theOutput.print(String.format("%1$10s",""));
         }
      //Iterates through the second matrice columns prints same line.
         for (int c2 = 0; c2 < theAr2[0].length; c2++) {
            if (r >= theAr2.length) {
               theOutput.print(String.format("%1$10s",""));
            }
            else {
            //Prints number index based off row and column of that matrix.
               theOutput.print(String.format("%1$10d", theAr2[r][c2]));
            }
         }
         if (r == 1) {
            theOutput.print(String.format("%1$10s", "="));
         }
         else {
            theOutput.print(String.format("%1$10s",""));
         }
      //Iterates through the third matrice columns prints same line.
         for (int c3 = 0; c3 < theAr3[0].length; c3++) {
            if (r >= theAr3.length) {
               theOutput.print(String.format("%1$10s",""));
            }
            else {
            //Prints number index based off row and column of that matrix.
               theOutput.print(String.format("%1$10d", theAr3[r][c3]));
            }
         }
         theOutput.println(); //Prints new line to start values on.
      }
      theOutput.println(); //Prints empty line between each matrix.
   }
}
