/*
 * ItemTest.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-2
 */

package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.Item;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the testing program that will check for errors in the Item.java class.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 12, 2017 
 */
public class ItemTest {
    /**
     * This is a Silly Putty Item used for testing 2 parameter constructor.
     */
    private Item myTestItem1;
    /**
     * This is a banana item used for testing 4 parameter constructor.
     */
    private Item myTestItem2;
    /**
     * This is a Orange Item used for testing 2 parameter constructor.
     */
    private Item myTestItem3;
    /**
     * This is a apple item used for testing 4 parameter constructor.
     */
    private Item myTestItem4;
    /**
     * This is Item orange object for object referencing.  
     */
    private Item myTestItem5;
    /**
     * This is a banana item used for equal method testing.
     */
    private Item myTestItem6;
    /**
     * This is a banana item used for equal method testing.
     */
    private Item myTestItem7;
    /**
     * This is a banana item used for equal method testing.
     */
    private Item myTestItem8;
    /**
     * This is a banana item used for equal method testing.
     */
    private Item myTestItem9;
    /**
     * This is a banana item used for equal method testing.
     */
    private Item myTestItem10;
    /**
     * This is a potate item used for equal method testing.
     */
    private Item myTestItem11;

    /**
     * 
     */
    @Before
    public void setUp() {
        //Tests the constructor that takes two parameters.
        myTestItem1 = new Item("Silly Putty", BigDecimal.valueOf(10));
        //Tests the constructor that takes four parameters.
        myTestItem2 = new Item("Bananas", BigDecimal.valueOf(5), 5, BigDecimal.valueOf(5));
        //Used for testing the equal method.
        myTestItem3 = new Item("Orange", BigDecimal.valueOf(6));
        //Used for testing the equal method when it is not equal.
        myTestItem4 = new Item("Apple", BigDecimal.valueOf(6), 6, BigDecimal.valueOf(6));
        //Used for testing the equal method. 
        myTestItem5 = myTestItem3;
        //Used for testing the equal method.
        myTestItem6 = new Item("Bananas", BigDecimal.valueOf(5), 5, BigDecimal.valueOf(5));
        //Used for testing the equal method.
        myTestItem7 = new Item("Bananas", BigDecimal.valueOf(6), 6, BigDecimal.valueOf(6));
        //Used for testing the equal method.
        myTestItem8 = new Item("Bananas", BigDecimal.valueOf(5), 6, BigDecimal.valueOf(6));
        //Used for testing the equal method.
        myTestItem9 = new Item("Bananas", BigDecimal.valueOf(5), 5, BigDecimal.valueOf(6));
        //Used for testing the equal method.
        myTestItem10 = new Item("Bananas", BigDecimal.valueOf(5), 5, BigDecimal.valueOf(5));
        //Used for testing equal method
        myTestItem11 = new Item("Potatoe", BigDecimal.valueOf(3), 1, BigDecimal.valueOf(1));
    }
    
    /**
     * This checks the first constructor and makes sure the name of the 
     * Item can not be set to null.
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor1ForNullName() {
        new Item(null, BigDecimal.valueOf(10));
    }
    
    /**
     * Test method for first constructor and makes sure the price of the 
     * Item can not be set to null.
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor1ForThePrice() {
        new Item("Bananas", null);
    }
    
    /**
     * Test method for first constructor and makes sure the price of the 
     * Item can not be a negative value.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor1ForThePriceNegative() {
        new Item("Bananas", BigDecimal.valueOf(-10));
    }
    
    /**
     * Test method for first constructor to make sure the name of the 
     * Item can not be passed a empty string/name.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor1ForTheNameEmpty() {
        new Item("", BigDecimal.valueOf(10));
    }
    
    /**
     * Test method for second constructor to make sure the name of the 
     * Item can not be set to null. 
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor2ForNullTheName() {
        new Item(null, BigDecimal.valueOf(5), 5, BigDecimal.valueOf(5));
    }
    
    /**
     * Test method for second constructor to make sure the price of the
     * Item can not be set to null.
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor2ForNullThePrice() {
        new Item("Bananas", null, 5, BigDecimal.valueOf(5));
    }
    
    /**
     * Test method for second constructor to make sure the bulk price of the 
     * Item can not be set to null.
     */
    @Test (expected = NullPointerException.class)
    public void testConstructor2ForNullTheBulkPrice() {
        new Item("Bananas", BigDecimal.valueOf(5), 5, null);
    }
    
    /**
     * Test method for second constructor to make sure the name of the 
     * Item can not be set to a empty String/name.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor2ForTheNameEmpty() {
        new Item("", BigDecimal.valueOf(5), 5, BigDecimal.valueOf(5));
    }
    
    /**
     * Test method for second constructor to make sure the the price of the 
     * Item can not be set to a negative number.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor2ForThePriceNegative() {
        new Item("Bananas", BigDecimal.valueOf(-5), 5, BigDecimal.valueOf(5));
    }

    /**
     * Test method for second constructor to make sure the bulk quantity of the 
     * Item can not be set to a negative quantity.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor2ForTheBulkQuantityNegative() {
        new Item("Bananas", BigDecimal.valueOf(5), -5, BigDecimal.valueOf(5));
    }
    
    /**
     * Test method for second constructor to make sure the bulk price of the 
     * Item can not be set to a negative price.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor2ForTheBulkPriceNegative() {
        new Item("Bananas", BigDecimal.valueOf(5), 5, BigDecimal.valueOf(-5));
    }

    /**
     * Test method for the hash code to make sure the hash code of the two objects are 
     * equal.
     */
    @Test
    public void testHashCode() {
        assertEquals(myTestItem6.hashCode(), myTestItem10.hashCode());
    }

    /**
     * Test method for get price to make sure the value of the getter
     * method is working and returning the correct price of the Item.
     */
    @Test
    public void testGetPrice() {
        assertEquals(BigDecimal.valueOf(5), myTestItem2.getPrice());
    }

    /**
     * Test method for get bulk quantity to make sure the value of the getter
     * method is working and returning the correct bulk quantity of the Item.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals(5, myTestItem2.getBulkQuantity());
    }

    /**
     * Test method for get bulk price to make sure the value of the getter
     * method is working and returning the correct bulk price of the Item.
     */
    @Test
    public void testGetBulkPrice1() {
        assertEquals(BigDecimal.valueOf(5), myTestItem2.getBulkPrice());
    }

    /**
     * Test method for get bulk price to make sure the value of the getter
     * method is working and returning the correct bulk price of the Item
     * when using a getter on the first constructor.
     */
    @Test
    public void testGetBulkPrice2() {
        assertEquals(BigDecimal.valueOf(0), myTestItem1.getBulkPrice());
    }
    
    /**
     * Test method for checking if the is Bulk is giving and assigning 
     * the correct boolean value.
     */
    @Test
    public void testIsBulk1() {
        assertTrue(myTestItem2.isBulk());
        //assertFalse(myTestItem1.isBulk());
    }

    /**
     * Test method for checking if the is Bulk is giving and assigning 
     * the correct boolean value.
     */
    @Test
    public void testIsBulk2() {
        assertFalse(myTestItem1.isBulk());
    }
    
    /**
     * Test method for testing the to String to make sure that it is outputting 
     * the correct values and format as expected.
     */
    @Test
    public void testToString1() {
        final NumberFormat numberFormater = NumberFormat.getCurrencyInstance(Locale.US);
        assertEquals("Bananas, " + numberFormater.format(BigDecimal.valueOf(5)) + " (5 for " 
                      + numberFormater.format(BigDecimal.valueOf(5)) + ")"
                      , myTestItem2.toString());
    }
    
    /**
     * Test method for testing the to String to make sure that it is outputting 
     * the correct values and format as expected.
     */
    @Test
    public void testToString2() {
        final NumberFormat numberFormater = NumberFormat.getCurrencyInstance(Locale.US);
        assertEquals("Silly Putty, " + numberFormater.
                     format(BigDecimal.valueOf(10)), myTestItem1.toString());
    }

    /**
     * Test method for checking if both objects reference same spots in memory
     * and that the equals method is working correctly.
     */
    @Test
    public void testEqualsObject1() {
        assertEquals(myTestItem3.equals(myTestItem5), true);
    }
    
    /**
     * Test method for checking if object submitted is null and returns the correct
     * boolean value.
     */
    @Test
    public void testEqualsObject2() {
        assertEquals(myTestItem3 == null, false);
    }

    /**
     * Test method for checking if the classes of the object are the same
     * or not.
     */
    @Test
    public void testEqualsObject20() {
        assertEquals(myTestItem3.equals(new Object()), false);
    }
    
    /**
     * Test method for checking if objects is not equal.
     */
    @Test
    public void testEqualsObject3() {
        assertEquals(myTestItem2.equals(myTestItem4), false);
    }
    
    /**
     * Test method for checking if objects is not equal specifically at 
     * the location theName. Values of the Item are F F F F.
     */
    @Test
    public void testEqualsObject4() {
        assertEquals(myTestItem6.equals(myTestItem11), false);
    }
    
    /**
     * Test method for checking if objects is not equal specifically at 
     * the location thePrice. Values of the Item are T F F F.
     */
    @Test
    public void testEqualsObject5() {
        assertEquals(myTestItem6.equals(myTestItem7), false);
    }
    
    /**
     * Test method for checking if objects is not equal specifically at 
     * the location theBulkQuantity. Values of the Item are T T F F.
     */
    @Test
    public void testEqualsObject6() {
        assertEquals(myTestItem6.equals(myTestItem8), false);
    }
    
    /**
     * Test method for checking if objects is not equal specifically at 
     * the location theBulkPrice. Values of the Item are T T T F.
     */
    @Test
    public void testEqualsObject7() {
        assertEquals(myTestItem6.equals(myTestItem9), false);
    }
    
    /**
     * Test method for checking two objects that are equal are 
     * correctly view as equal. Values of both Items are T T T T.
     */
    @Test
    public void testEqualsObject8() {
        assertEquals(myTestItem6.equals(myTestItem2), true);
    }
}
