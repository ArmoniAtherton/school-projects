/*
* Triangle.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 5
*/
import java.lang.Math;
/**
* This class Triangle extends from AbstractShape and then makes a
* new Triangle with only correct input. Next it makes correct calculations
* on it.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 May 2017
*/
public class Triangle extends AbstractShape {
  /**
  * Stores the side A of Triangle.
  */
  private double mySideA;
  /**
  * Stores the side B of Triangle.
  */
  private double mySideB;
  /**
  * Stores the side C of Triangle.
  */
  private double mySideC;
  /**
  * Stores the number of Triangles created.
  */
  private static int myID = 0;
  /**
   * This sets up the Triangle if no parameters passed.
   */
  public Triangle() {
    this(1.0, 1.0, 1.0);
  }
  /**
   * This method creates a Rectangle depending on if the Length and
   * the width are vaild input, throws exceptions if not. Proceeds to
   * find and set the area of the length and width.
   *
   * @param theSideA The incoming (double) side A of the Triangle.
   * @param theSideB The incoming (double) side B of the Triangle.
   * @param theSideC the incomcing (double) side C of the Triangle.
   */
  public Triangle (final double theSideA, final double theSideB,
                   final double theSideC ) {
   /* Calls parent class to set up the shape, keep tracks of how many
      shapes created. */
    super("Triangle", ++myID);
    //Throws exception if values are negative or zero.
    if (theSideA <= 0 || theSideB <= 0 || theSideC <= 0) {
      //Decrements the Triangle since a unvaild Triangle was inputed.
      myID--;
      throw new IllegalArgumentException("ERROR! Negative or 0 value(s) " +
                                         "can't be applied to a " +
                                         "Triangle.");
    }
    /* Throws exception if values are greater than or equal to
      sum of remaining sides. */
    if (theSideA + theSideB < theSideC || theSideB + theSideC < theSideA ||
        theSideA + theSideC < theSideB) {
      //Decrements the Triangle since a unvaild Triangle was inputed.
      myID--;
        throw new IllegalArgumentException("ERROR! Not a Triangle. " +
                                           "Longest side too long.");
      }
      //Sets the theSideA to the field within the class.
      mySideA = theSideA;
      //Sets the theSideB to the field within the class.
      mySideB = theSideB;
      //Sets the theSideC to the field within the class.
      mySideC = theSideC;
  }
  /**
   * This method makes it easier to set the Side A of the Triangle.
   * Uses a safety to check if user changed a the value of a already
   * created triangle to make sure the longest side isnt greater than
   * or equal to sum or remaining sides and not negative.
   *
   * @param theSideA The incoming (double) side A of the Triangle.
   */
  public void setSideA(final double theSideA) {
    //Throws exception if value is negative or zero when trying to set.
    if (theSideA <= 0) {
      throw new IllegalArgumentException("ERROR! Negative or 0 value(s) " +
                                         "can't be applied to a " +
                                         "Triangle set SideA.");
    }
    /* This is a safety for checking user inputed correct side length when
       using set methods. Throws exeception if wrong. */
    if (theSideA + mySideB < mySideC || mySideB + mySideC < theSideA ||
        theSideA + mySideC < mySideB) {
          throw new IllegalArgumentException("ERROR! Not a Triangle. " +
                                             "Longest side too long, " +
                                             "invaild side input when " +
                                             "using setSideA methods.");
        }
    //Sets the side length.
    mySideA = theSideA;
  }
  /**
   * This method makes it easier to set the Side B of the Triangle.
   * Uses a safety to check if user changed a the value of a already
   * created triangle to make sure the longest side isnt greater than
   * or equal to sum or remaining sides and not negative.
   *
   * @param theSideB The incoming (double) side B of the Triangle.
   */
  public void setSideB(final double theSideB) {
    //Throws exception if value is negative or zero when trying to set.
    if (theSideB <= 0) {
      throw new IllegalArgumentException("ERROR! Negative or 0 value(s) " +
                                         "can't be applied to a " +
                                         "Triangle set SideB.");
    }
    /* This is a safety for checking user inputed correct side length when
       using set methods. Throws exeception if wrong. */
    if (mySideA + theSideB < mySideC || theSideB + mySideC < mySideA ||
        mySideA + mySideC < theSideB) {
          throw new IllegalArgumentException("ERROR! Not a Triangle. " +
                                             "Longest side too long, " +
                                             "invaild side input when " +
                                             "using setSideB method.");
        }
    //Sets the side length.
    mySideB = theSideB;
  }
  /**
   * This method makes it easier to set the Side C of the Triangle.
   * Uses a safety to check if user changed a the value of a already
   * created triangle to make sure the longest side isnt greater than
   * or equal to sum or remaining sides and not negative.
   *
   * @param theSideC The incoming (double) side C of the Triangle.
   */
  public void setSideC(final double theSideC) {
    //Throws exception if value is negative or zero when trying to set.
    if (theSideC <= 0) {
      throw new IllegalArgumentException("ERROR! Negative or 0 value(s) " +
                                         "can't be applied to a " +
                                         "Triangle set SideC.");
    }
    /* This is a safety for checking user inputed correct side length when
       using set methods. Throws exeception if wrong. */
    if (mySideA + mySideB < theSideC || mySideB + theSideC < mySideA ||
        mySideA + theSideC < mySideB) {
          throw new IllegalArgumentException("ERROR! Not a Triangle. " +
                                             "Longest side too long, " +
                                             "invaild side input when " +
                                             "using setSideC method.");
        }
    //Sets the side length.
    mySideC = theSideC;
  }
  /**
   * This method calculates the area of the Triangle given the correct
   * side A, side B and side C.
   *
   * @return The (double) area of the Triangle.
   */
  public double calculateArea() {
    //Sums up all sides and divides by two.
    double p = (mySideA + mySideB +mySideC) / 2.0;
    //Returns the area of triangle with given formula.
    return (Math.sqrt((p * ((p - mySideA) * (p - mySideB) *
                           (p - mySideC)))));
  }
  /**
   * This method makes a defensive copy of Triangle that returns a
   * refrence to a new Triangle object.
   *
   * @return The (Shape) a reference to a new Triangle object.
   */
  public final Shape copyShape() {
    //Creates a new Triangle object.
    Triangle copyT = new Triangle();
    //Copies the current side A to the new Triangle side A.
    copyT.mySideA = this.mySideA;
    //Copies the current side B to the new Triangle side B.
    copyT.mySideB = this.mySideB;
    //Copies the current side B to the new Triangle side B.
    copyT.mySideC = this.mySideC;
    //Returns the reference of the new Triangle object.
    return copyT;
  }
  /**
   * This gives back the information of side A, Side B, Side C and area of
   * the Triangle as a string so it can be seen visually of current
   * information.
   *
   * @return Formated (String) of all current Triangle information.
   */
  public String toString() {
    /* Returns a fully formated Triangle information in a
       organized fashion. */
    return String.format("%1$s [SideA: %2$.2f, SideB: %3$.2f, SideC: " +
                         "%4$.2f] Area: %5$.2f", super.getName(), mySideA,
                         mySideB, mySideC, calculateArea());
  }
}
