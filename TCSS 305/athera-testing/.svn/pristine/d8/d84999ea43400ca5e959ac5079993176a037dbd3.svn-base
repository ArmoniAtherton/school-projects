/*
 * CircleTest.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-1
 */
package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import shapes.Circle;


/**
 * This is the testing program that will check for errors in the Circle.java class.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 5, 2017 
 */
public class CircleTest {
    /**
     * Stores a margin of error for comparing two doubles.
     */
    private static final double TOLARANCE = .000001;
    
    /**
     * A default circle to be used when no parameters passed.
     */
    private Circle myCircleDefault;
    
    /**
     * A circle to be used with specific values for testing methods.
     */
    private Circle myCircleTesting;
    
    /**
     * A circle to be used with specific values for testing the constructor.
     */
    private Circle myCircleTesting2;
    
    /**
     * Will store a color for a circle.
     */
    private Color myColorTest;
    
    /**
     * Will store a radius of a circle.
     */
    private double myRadiusTest;
    
    /**
     * Will store a point for the circle.
     */
    private Point myPointTest;

    /**
     * This sets up the class fields and creates instances of objects to make sure 
     * our constructor is working and so that we can use these objects to test them 
     * in other methods too.
     * 
     */
    @Before
     public void setUp() {
        myRadiusTest = 5.0;
        myPointTest = new Point(5, 5); //Creates a point.
        myColorTest = new Color(5, 5, 5); //Creates a Color.
        myCircleDefault = new Circle(); //Creates default circle object.
        myCircleTesting = new Circle(myRadiusTest, myPointTest, myColorTest);
        myCircleTesting2 = new Circle(myRadiusTest, myPointTest, myColorTest);
    }
    
    /**
     * This checks the constructor to make sure that it doesn't allow you to set zero 
     * as a radius value when instantiating a circle object.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor1() {
        myCircleTesting2 = new Circle(0, myPointTest, myColorTest);
    }
    
    /**
     * This checks the constructor to make sure that it doesn't allow you to set a negative 
     * radius value when instantiating a circle object.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor2() {
        myCircleTesting2 = new Circle(-5, myPointTest, myColorTest);
    }
    
    /**
     * This checks the constructor to make sure that it doesn't allow you to set a null
     * point value when instantiating a circle object.
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor3() {
        myCircleTesting2 = new Circle(myRadiusTest, null, myColorTest);
    }
    
    /**
     * This checks the constructor to make sure that it doesn't allow you to set a null
     * color value when instantiating a circle object.
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor4() {
        myCircleTesting2 = new Circle(myRadiusTest, myPointTest, null);
    }

    /**
     * Test method that checks the default values when creating a circle with no parameters
     * to see if they match up correctly.
     */
    @Test
    public void testCircle() {
        assertEquals("Circle [radius=1.00, center=java.awt.Point[x=0,y=0], "
                     + "color=java.awt.Color[r=0,g=0,b=0]]", myCircleDefault.toString());
        
    }

    /**
     * Test method that checks if the set radius method changes the value correctly
     * to the value expected.
     */
    @Test
    public void testSetRadius1() {
        myCircleTesting.setRadius(5.0);
        assertEquals(myRadiusTest, myCircleTesting.getRadius(), 5.0);
    }
   
    /**
     * Test method that checks to make sure that it doesn't allow you to set
     * the value to be set to a illegal value. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetRadius2() {
        myCircleTesting.setRadius(0);
        
    }
    
    /**
     * Test method that checks to make sure that it doesn't allow you to set
     * the value to be set to a illegal value. 
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetRadius3() {
        myCircleTesting.setRadius(-25);
    }
    
    /**
     * Test method that checks the "set center" method to see if it works correctly
     * and that the value of it is correct.
     */
    @Test 
    public void testSetCenter1() {
        myPointTest = new Point(2, 2);
        myCircleTesting.setCenter(myPointTest);
        assertEquals(myPointTest, myCircleTesting.getCenter());
        
    }
    
    /**
     * Test method for checking the setCenter to correctly not allow the value 
     * to be set as null.
     */
    @Test (expected = NullPointerException.class)
    public void testSetCenter2() {
        myPointTest = null;
        myCircleTesting2.setCenter(myPointTest);
        assertEquals(myPointTest, myCircleTesting2.getCenter());
        
    }
    
    /**
     * Test method for checking the color of the circle can be changed and
     * that the value it correctly/accurately changed.
     */
    @Test
    public void testSetColor1() {
        myColorTest = new Color(4, 4, 4);
        myCircleTesting.setColor(myColorTest);
        assertEquals(myColorTest, myCircleTesting.getColor());
    }
    
    /**
     * Test method for checking and not allowing null to be set as a color.
     */
    @Test (expected = NullPointerException.class)
    public void testSetColor2() {
      //This was a bug I fixed this by making theColor require a non null value.
        myCircleTesting.setColor(null);
        assertEquals(myColorTest, myCircleTesting.getColor());
    }
    
    /**
     * Test method for getting the radius of the circle created makes sure that 
     * it returns the correct value.
     */
    @Test
    public void testGetRadius() {
        assertEquals(myRadiusTest, myCircleTesting.getRadius(), TOLARANCE);
    }

    /**
     * Test method for getting the center of the circle created make sure that 
     * it returns the correct value.
     */
    @Test
    public void testGetCenter() {
        assertEquals(myPointTest, myCircleTesting.getCenter());
    }

    /**
     * Test method for getting the color of the circle created make sure that 
     * it returns the correct color.
     */
    @Test
    public void testGetColor() {
        assertEquals(myColorTest, myCircleTesting.getColor());
    }

    /**
     * Test method for checking the circles calculated diameter and verifying it
     * was correct and return the value as expected.
     */
    @Test
    public void testCalculateDiameter() {
        //This diameter formula was wrong in the Circle.java class but now fixed.
        assertEquals(myCircleTesting.getRadius() * 2,
                     myCircleTesting.calculateDiameter(), TOLARANCE);
    }

    /**
     * Test method for checking the circles calculated circumference and verifying it
     * was correct and return the value as expected.
     */
    @Test
    public void testCalculateCircumference() {
        assertEquals(Math.PI * myCircleTesting.calculateDiameter(), 
                     myCircleTesting.calculateCircumference(), TOLARANCE);
    }

    /**
     * Test method for checking the circles calculated area and verifying it
     * was correct and return the value as expected.
     */
    @Test
    public void testCalculateArea() {
        assertEquals(myCircleTesting.getRadius() * myCircleTesting.getRadius() * Math.PI,
                     myCircleTesting.calculateArea(), TOLARANCE);
    }

    /**
     * Test method for checking the to string of a circle object and verifying it 
     * would output the correct format/value.
     */
    @Test
    public void testToString() {
        assertEquals("Circle [radius=1.00, center=java.awt.Point[x=0,y=0],"
                     + " color=java.awt.Color[r=0,g=0,b=0]]", myCircleDefault.toString());
    }

}
