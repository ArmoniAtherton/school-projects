/*
* Shape.java
*
* TCSS 143 - Spring 2017
* Instructor: David Schuessler
* Assignment 5
*/

/**
* This program is the interface for all the programs written
* that implements the interface and gives them a skeleton blueprint
* of what methods the programs should have.
*
* @author Armoni Atherton athera@uw.edu
* @version 10 May 2017
*/
public interface Shape extends Comparable<Shape> {
  /**
   * Every program the implements interface must have this method
   * in which gets the calculate area.
   *
   * @return the calculate area of the shape.
   */
  public double calculateArea();
  /**
   * Every program the implements interface must have this method
   * in which it copys the shape.
   *
   * @return the correct demensions of the shape.
   */
  public Shape copyShape();
  /**
   * Every program the implements interface must have this method
   * in which gets the shape name.
   *
   * @return the requested name of the shape.
   */
  public String getName();
}
