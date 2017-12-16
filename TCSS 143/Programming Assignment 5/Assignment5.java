/*
* Assignment5.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 5
*/
import java.util.*;
import java.io.*;
/**
* This program is used as a driver program to take input from a
* file and use that input to create shapes and find the area.
* If it can create the shape it will output in a organized fashion.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 May 2017
*/
public class Assignment5 {
  /**
   * Driver program to open files and then create shapes of many types
   * calls other classes.
   *
   * @param theArgs is used for command-line input arguments.
   *                Not used here.
   */
  public static void main(String[] theArgs) {
    Scanner input = null;
    PrintStream output = null;
    String fileIn = "in5.txt"; //Stores input file name.
    String fileOut = "out5.txt"; //Stores the output file name.
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
      //Creates a new linked list.
      List<Shape> myList = new LinkedList<Shape>();
      //Calls method to make a copied list.
      List<Shape> copyList = assignInput(input, myList);
      output.println("Original List[unsorted]: "); //Outputs to file.
      printInput(output, myList); //Prints the orignal list of shapes.
      Collections.sort(copyList); //Calls the compareTo and orgainzes list.
      output.println(); //Outputs a empty line.
      output.println("Copied List[sorted-Ascending Class first, " +
                     "descending area second]: "); //Outputs to file.
      printInput(output, copyList); //Outputs the copyList organized.
      output.println(); //Outputs a empty line.
      output.println("Original List[unsorted]: "); //Output to file.
      printInput(output, myList); //Outputs the orignial list again.
      }
  }
  /**
   * This method inputes data into a linked list and attempts to
   * make new shapes. If succesfully created will store into a
   * linked list and make a copy of it as a array list.
   *
   * @param theInput (Scanner) contains the input file.
   * @param theLinkedList (Shape) Contains a empty list to place values in.
   * @return The (Shape) a copy of shapes as an array list.
   */
  public static List<Shape> assignInput(Scanner theInput,
                                        List<Shape> theLinkedList) {
    //Creates a new list to copy shapes to for returning.
    List<Shape> newList = new ArrayList<Shape>();
    //Checks if it has next lines.
    while (theInput.hasNextLine()) {
      String line = theInput.nextLine(); //Stores current line.
      Scanner s = new Scanner(line); //Curent line to a scanner
      //Creates a array list to store shape values.
      ArrayList <Double> valuesList = new ArrayList<Double>();
      Boolean validInput = true; //If input is vaild or not.
      //read through the line to check if values are vaild.
      while (s.hasNext() && validInput) {
        //Checks if it is a number
        if (s.hasNextDouble()) {
          valuesList.add(s.nextDouble());
          //Checks if it is a integer.
        } else if (s.hasNextInt()) {
            valuesList.add( (double) s.nextInt());
          //If anything else sets that line input to not vaild.
        } else if (s.hasNext()) {
            validInput = false;
        }
      }
      //It will try to create a shape
      try {
        //Checks if less than or = to three and if line input was vaild.
        if (valuesList.size() <= 3 && validInput) {
          //If array size is one it makes a new circle in linked list.
          if (valuesList.size() == 1) {
            theLinkedList.add(new Circle(valuesList.get(0)));
            //If array size is two it makes a new rectangle in linked list.
          } else if (valuesList.size() == 2) {
              theLinkedList.add(new Rectangle(valuesList.get(0),
                                              valuesList.get(1)));
            //If array size is two it makes a new triangle in linked list.
          } else if (valuesList.size() == 3) {
              theLinkedList.add(new Triangle(valuesList.get(0),
                                             valuesList.get(1),
                                             valuesList.get(2)));
          }
      }
      //If it cant create the shape it will throw the exception.
    } catch (IllegalArgumentException e) {
        System.out.println(e);
      }
    } //End of while loop.
    //This makes a copy of shapes in linked list to new list.
    for (Shape element : theLinkedList) {
      Shape s = element.copyShape(); //Gets the copyied shape and stores.
      newList.add(s); } //Adds the copy of the shape to new list.
    return newList; //Returns the new list.
  }
  /**
   * This method goes through an array and prints out each shape
   * that is currently be held within it.
   *
   * @param theOutput (PrintStream) For outputing to a file.
   * @param theOutputList (Shape) holds the list of shapes to be printed.
   */
  public static void printInput(PrintStream theOutput,
                                List<Shape> theOutputList) {
    //iterates through each shape in the list.
    for (Shape element : theOutputList) {
      //Outputs to a file to display shape information.
      theOutput.println(element);
    }
  }
}
