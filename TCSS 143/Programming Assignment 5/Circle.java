/*
* Circle.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 5
*/
import java.lang.Math;
/**
* This class Circle extends from AbstractShape and then makes a
* new circle with only correct input. Next it makes correct calculations
* on it.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 May 2017
*/
public class Circle extends AbstractShape {
  /**
  * Stores the radius of specific circle.
  */
  private double myRadius;
  /**
  * Stores the number of circles created.
  */
  private static int myID = 0;
  /**
   * This sets up the circle if no parameters passed.
   */
  public Circle() {
    this(1.0);
  }
  /**
   * This method creates a circle depending on if theRadius is
   * vaild input throws exceptions if not. Proceeds to find and
   * set the area of the circle.
   *
   * @param theRadius The incoming (double) radius of the circle.
   */
  public Circle(final double theRadius) {
    /* Calls parent class to set up the shape, keep tracks of how many
       shapes created. */
    super("Circle", ++myID);
    //Throws exception if value is negative or zero.
    if (theRadius <= 0.0) {
      myID--; //Decrements the circle since a unvaild circle was inputed.
      throw new IllegalArgumentException("ERROR! Negative or 0 " +
                                         "value can't be applied to a " +
                                         "Circle radius.");
    }
    //Sets the radius to the field within the class.
    myRadius = theRadius;
  }
  /**
   * This method makes it easier to set the radius of the circle.
   * Implements a safety to check if you change a value of a already
   * created circle to verify it can not be negative.
   *
   * @param theRadius The incoming (double) radius of the circle.
   */
  public void setRadius(final double theRadius) {
    //Throws exception if value is negative or zero when trying to set.
    if (theRadius <= 0.0) {
      throw new IllegalArgumentException("ERROR! Negative or 0 " +
                                         "value can't be applied to a " +
                                         "Circle set radius.");
    }
    //Sets the radius if number is positive.
    myRadius = theRadius;
  }
  /**
   * This method calculates the area of the circle given the correct
   * radius.
   *
   * @return The (double) area of the circle.
   */
  public double calculateArea() {
    return (double)(Math.PI * (myRadius * myRadius));
  }
  /**
   * This method makes a defensive copy of Circle that returns a
   * refrence to a new Circle object.
   *
   * @return The (Shape) a reference to a new Circle object.
   */
  public final Shape copyShape() {
    //Creates a new circle object.
    Circle newC = new Circle();
    //Copies the current radius to the new Circles radius.
    newC.myRadius = myRadius;
    //Returns the reference of the new Circle object.
    return newC;
  }
  /**
   * This gives back the information of radius and area of
   * the Circle as a string so it can be seen visually of current
   * information.
   *
   * @return Formated (String) of all current Circle information.
   */
  public String toString() {
    //returns a fully formated circle information in a organized fashion.
    return String.format("%1$s [Radius: %2$.2f] Area: %3$.2f",
                         super.getName(), myRadius, calculateArea());
  }
}
