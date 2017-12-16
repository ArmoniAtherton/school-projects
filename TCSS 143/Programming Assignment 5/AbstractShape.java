/*
* AbstractShape.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 5
*/

/**
* This abstract class implements from shape and then set up the
* shape information and correctly implements the compareTo method
* to compare shapes in the program.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 May 2017
*/
public abstract class AbstractShape implements Shape {
  /**
  * Stores the shape name.
  */
  private String myShapeName;
  /**
   * This method sets up the constructors
   * correctly for the variables to be used throughout the
   * class and some subclasses.
   *
   * @param theShape The incoming (String) name of the shape.
   * @param theShapeNumber The incoming (int) shape number.
   */
  public AbstractShape(final String theShape, final int theShapeNumber) {
    //This concatenate the shape with a number.
    myShapeName = theShape + theShapeNumber;
  }
  /**
   * This method organizes shapes alphabeticaly and after that it
   * organizes it by the area from ascending to descending.
   *
   * @param theShape The incoming (Shape) name of the shape.
   * @return The (int) number to orgainze the shapes area/classes.
   */
  public int compareTo(final Shape theShape) {
    int result = 0;
    //This checks if the shape names are the same.
    if (this.getClass().getName().compareTo(theShape.getClass().
        getName()) == 0) {
      //Organizes the shapes area from ascending to descending.
        result = (int)(100 * (theShape.calculateArea() -
                              this.calculateArea()));
    } else {
        //Organizes the shape names by alphabetical order.
        result = this.getClass().getName().compareTo(
                 theShape.getClass().getName());
    }
    //Returns if equal, greater than or less than.
    return result;
  }
  /**
   * This gives back the information of the current shape name.
   *
   * @return myShapeName (String) with the current name of the shape.
   */
  public String getName() {
    //Sends back the current shape name.
    return myShapeName;
  }
}
