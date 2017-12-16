/*
 * Item.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-2
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This program will create Item objects to 
 * be instantiated by the driver class.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 12, 2017 
 */

public final class Item {
    
    /**
     * This stores the name of the item.
     */
    private final String myName;
    
    /**
     * This stores the price of the the item.
     */
    private final BigDecimal myPrice;
    
    /**
     * This stores the bulk quantity of the the item.
     */
    private int myBulkQuantity;
    
    /**
     * This stores the bulk price of the the item.
     */
    private BigDecimal myBulkPrice;
    
    /**
     * This constructs a Item with a specified name and price.
     * 
     * @param theName Stores the name of the Item.
     * @param thePrice Stores the price of the Item.
     */
 
    public Item(final String theName, final BigDecimal thePrice) {
        if ("".equals(theName) || thePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Must not input negative values"
                                                + " or empty item name.");
        } else {
            myName = Objects.requireNonNull(theName);
            myPrice = Objects.requireNonNull(thePrice);
            myBulkQuantity = 0;
            myBulkPrice = BigDecimal.ZERO;
        }
    }
    /**
     * This constructs a Item with a specified name, price, bulk quantity 
     * and bulk price.
     * 
     * @param theName Stores the name of the Item.
     * @param thePrice Stores the price of the Item. 
     * @param theBulkQuantity Stores the bulk quantity of the item.
     * @param theBulkPrice Stores the bulk price of the item.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity, 
                final BigDecimal theBulkPrice) {
        if ("".equals(theName) || thePrice.compareTo(BigDecimal.ZERO) < 0 
                   || theBulkQuantity < 0 || theBulkPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Must not input negative values.");
        } else {
            myName = Objects.requireNonNull(theName);
            myPrice = Objects.requireNonNull(thePrice);
            myBulkQuantity = Objects.requireNonNull(theBulkQuantity);
            myBulkPrice = theBulkPrice; 
        }
    }

    /**
     * This method allows for users to get the price of the item.
     * 
     * @return The price of the item.
     */
    public BigDecimal getPrice() {
        
        return myPrice;
    }
    
    /**
     * This method allows for users to get the bulk quantity of the item.
     * 
     * @return The bulk quantity of the item.
     */
    public int getBulkQuantity() {
        
        return myBulkQuantity;
    }
    
    /**
     * This method allows for users to get the bulk price of the item.
     * 
     * @return The bulk price of the item.
     */
    public BigDecimal getBulkPrice() {
        
        return myBulkPrice;
    }
    
    /**
     * This allows you to check if the item is a bulk quantity.
     * 
     * @return The boolean value if it is a bulk quantity.
     */
    public boolean isBulk() {
        boolean flag = false;
        if (myBulkQuantity > 0) {
            flag = true;
        }
        return flag;
    }
    
    /**
     * The String representation of this Item will be formatted in a 
     * organized fashion.
     */
    @Override
    public String toString() {
        final NumberFormat numberFormater = NumberFormat.getCurrencyInstance(Locale.US);
        final StringBuilder builder = new StringBuilder(128);
        builder.append(myName);
        builder.append(", ");
        builder.append(numberFormater.format(myPrice));
        if (myBulkQuantity >= 1) {
            builder.append(" (");
            builder.append(myBulkQuantity);
            builder.append(" for ");
            builder.append(numberFormater.format(myBulkPrice));
            builder.append(')');
        }
        return builder.toString();
    }
    
    /**
     * This will compare two item object to see if they are the 
     * same of if they are different and should not compare the equality.
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean result = true;
        //This compares the two objects to see if they are the same.
        if (this == theOther) {
            result = true;
          //This sees if it is null or if is the different class.
        } else if (theOther == null || this.getClass() != theOther.getClass()) {
            result = false;
        } else {
            result = myName.equals(((Item) theOther).myName) 
                     && myPrice.equals(((Item) theOther).myPrice)
                     && myBulkQuantity == ((Item) theOther).myBulkQuantity 
                     && myBulkPrice.equals(((Item) theOther).myBulkPrice); 
        }
        return result;
    }

    /**
     * This is hash code that will return a integer value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
    }

}
