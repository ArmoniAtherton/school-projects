/*
* Rectangle.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 5
*/

/**
* This class Rectangle extends from AbstractShape and then makes a
* new Rectangle with only correct input. Next it makes correct calculations
* on it.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 May 2017
*/
public class Rectangle extends AbstractShape {
  /**
  * Stores the length of Rectangle.
  */
  private double myLength;
  /**
  * Stores the width of Rectangle.
  */
  private double myWidth;
  /**
  * Stores the number of Rectangles created.
  */
  private static int myID = 0;
  /**
   * This sets up the Rectangle if no parameters passed.
   */
  public Rectangle() {
    this(1.0, 1.0);
  }
  /**
   * This method creates a Rectangle depending on if the Length and
   * the width are vaild input, throws exceptions if not. Proceeds to
   * find and set the area of the length and width.
   *
   * @param theLength The incoming (double) length of the Rectangle.
   * @param theWidth The incoming (double) width of the Rectangle.
   */
  public Rectangle (final double theLength, final double theWidth ) {
    /* Calls parent class to set up the shape, keep tracks of how many
       shapes created. */
    super("Rectangle", ++myID);
    //Throws exception if values are negative or zero.
    if (theLength <= 0.0 || theWidth <= 0.0) {
      //Decrements the Rectangle since a unvaild Rectangle was inputed.
      myID--;
      throw new IllegalArgumentException("ERROR! Negative or 0 value(s) " +
                                         "can't be applied to a " +
                                         "Rectangle.");
    }
    //Sets the length to the field within the class.
    myLength = theLength;
    //Sets the width to the field within the class.
    myWidth = theWidth;
  }
  /**
   * This method makes it easier to set the length of the Rectangle.
   * Implements a safety to check if you change the lenght of a already
   * created Rectangle to verify its new length can not be negative.
   *
   * @param theLength The incoming (double) length of the Rectangle.
   */
  public void setLength(final double theLength) {
    //Throws exception if value is negative or zero when trying to set.
    if (theLength <= 0.0) {
      throw new IllegalArgumentException("ERROR! Negative or 0 value " +
                                         "can't be applied to a " +
                                         "Rectangle set length.");
    }
    //Sets the length.
    myLength = theLength;

  }
  /**
   * This method makes it easier to set the width of the Rectangle.
   * Implements a safety to check if you change the width of a already
   * created Rectangle to verify its new width can not be negative.
   *
   * @param theWidth The incoming (double) width of the Rectangle.
   */
  public void setWidth(final double theWidth) {
    //Throws exception if value is negative or zero when trying to set.
    if (theWidth <= 0.0) {
      throw new IllegalArgumentException("ERROR! Negative or 0 value " +
                                         "can't be applied to a " +
                                         "Rectangle set width.");
    }
    //Sets the width.
    myWidth = theWidth;
  }
  /**
   * This method calculates the area of the Rectangle given the correct
   * length and width.
   *
   * @return The (double) area of the Rectangle.
   */
  public double calculateArea() {
    //Sends back the calculated area of the Rectangle.
    return myWidth * myLength;
  }
  /**
   * This method makes a defensive copy of Rectangle that returns a
   * refrence to a new Rectangle object.
   *
   * @return The (Shape) a reference to a new Rectangle object.
   */
  public final Shape copyShape() {
    //Creates a new Rectangle object.
    Rectangle copyR = new Rectangle();
    //Copies the current length to the new Rectangle length.
    copyR.myLength = this.myLength;
    //Copies the current width to the new width length.
    copyR.myWidth = this.myWidth;
    //Returns the reference of the new Rectangle object.
    return copyR;
  }
  /**
   * This gives back the information of length, width and area of
   * the Rectangle as a string so it can be seen visually of current
   * information.
   *
   * @return Formated (String) of all current Rectangle information.
   */
  public String toString() {
    /* Returns a fully formated Rectangle information in a
       organized fashion. */
    return String.format("%1$s [Length: %2$.2f, Width: %3$.2f] Area: " +
                          "%4$.2f", super.getName(), myLength, myWidth,
                          calculateArea());
  }
}
