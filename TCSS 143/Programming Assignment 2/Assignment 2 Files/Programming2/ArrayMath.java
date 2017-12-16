/*
* ArrayMath.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 2
*/

/**
* This program will perform a basic addition, subtraction,
* and multiplication of two matrices.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 April 2017
*/
public class ArrayMath {
  /**
   * This method takes takes two arrays containing integer numbers and
   * then sums up there totals using addition. Puts them into one
   * 2D array.
   *
   * @param theArrayA Contains 2D array of first group of numbers.
   * @param theArrayB Contains 2D array of second group of numbers.
   * @return additionSum is a 2d array which contains the sum of theArrayA
   * and theArrayB when added together.
   */
  public static int[][] addArrays(int [][] theArrayA, int [][] theArrayB) {
    int getRow = theArrayA.length; //Gets the row length.
    int getCol = theArrayB[0].length; //Gets the column lenght.
    //Creates a new 2D array to store new values in.
    int [][] additionSum = new int [getRow][getCol];
    for (int r = 0; r < getRow; r++) { //Iterates through the rows.
      for (int c = 0; c < getCol; c++) { //Iterates throught the columns
        //Adds numbers together at row index and column index
        //assigns to a new 2D array.
        additionSum[r][c] = theArrayA[r][c] + theArrayB[r][c];
      }
    }
    return additionSum; //2D array containing the sum of the two arrays.
  }
  /**
   * This method takes takes two arrays containing integer numbers and
   * then totals them up using subtraction. Next puts them into one
   * 2D array.
   *
   * @param theArrayA Contains 2D array of first group of numbers.
   * @param theArrayB Contains 2D array of second group of numbers.
   * @return subtractionSum is a 2d array which contains the total of
   *  theArrayA and theArrayB when minused together.
   */
  public static int[][] minusArrays(int [][] theArrayA,
                                    int [][] theArrayB) {
    int getRow = theArrayA.length; //Gets row lenght.
    int getCol = theArrayA[0].length; //Gets column lenght.
    //Creates a 2D array to store new values into.
    int [][] subtractionSum = new int [getRow][getCol];
    for (int r = 0; r < getRow; r++) { //Iterates through rows
      for (int c = 0; c < getCol; c++) { //iterates through columns
        //Subtracts numbers together at row index and column index
        //assigns to a new 2D array.
        subtractionSum[r][c] = theArrayA[r][c] - theArrayB[r][c];
      }
    }
    return subtractionSum; //2D array contains the total of both
                           //subtracted arrays.
  }
  /**
   * This method takes takes two arrays containing integer numbers and
   * then multiplies them together using multiplicaton. Next puts them
   * into one new 2D array.
   *
   * @param theArrayC Contains 2D array of third group of numbers.
   * @param theArrayD Contains 2D array of fourth group of numbers.
   * @return multiplicationSum is a 2d array which contains the total
   * of theArrayC and theArrayD when multiplied together.
   */
  public static int[][] multiplyArray(int [][] theArrayC,
                                      int [][] theArrayD) {
    int getRow = theArrayC.length; //Gets the row lenght.
    int getColumn1 = theArrayC[0].length; //Gets the first column lenght.
    int getColumn2 = theArrayD[0].length; //Gets the second column lenght.
    //Creates 2D array with the correct demensions to hold new values in.
    int [][] multiplicationSum = new int [getRow][getColumn2];
    for (int r = 0; r < getRow; r++) { //Iterates through the rows.
      for (int c = 0; c < getColumn2; c++) { //Iterates through columns.
        for (int j = 0; j < getColumn1; j++) { //Iterates through columns.
          //This multiplies the numbers and then sums them together.
          multiplicationSum[r][c] = multiplicationSum[r][c] +
          theArrayC[r][j] * theArrayD[j][c];
        }
      }
    }
    return multiplicationSum; //Returns a combined multiplied 2D array.
  }
}
