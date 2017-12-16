/*
 * ShoppingCart.java
 * 
 * TCSS 305 - Fall 2017
 * Instructor: Charles Bryan
 * Assignment-2
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This program will create ShoppingCart object to 
 * be instantiated by the driver class and store ItemOrders.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version October 12, 2017 
 */
public class ShoppingCart {
    /**
     * This stores a shopping cart list of item orders. 
     */
    private final List<ItemOrder> myShoppingCart;
    /**
     * This stores if you have a membership.
     */
    private boolean myMembership;
    
    /**
     * This constructs a empty shopping cart in which will
     * hold the items within.
     */
    public ShoppingCart() {
        myShoppingCart = new ArrayList<ItemOrder>();

    }

    /**
     * This will add item orders to the shopping cart list and change the 
     * quantity of items as requested by the user.
     * 
     * @param theOrder A ItemOrder object given by the user.
     */
    public void add(final ItemOrder theOrder) {
        Objects.requireNonNull(theOrder);
        //Sets the index for the item order that needs to be replaced.
        int index = -1;
        for (int i = 0; i < myShoppingCart.size(); i++) {
            final ItemOrder currentItemOrder =  myShoppingCart.get(i);
            if (currentItemOrder.getItem().equals(theOrder.getItem())) {
                index = i;
            }
        } 
        //add to the myShopping cart due to item order not being in the shopping cart
        if (index == -1) {
            myShoppingCart.add(theOrder);
        } else {
            /*Remove it from the shopping cart at the index it was equal 
              and then update it with the new order. */
            myShoppingCart.remove(index);
            myShoppingCart.add(theOrder);
        }
    }
        
    
    /**
     * This will determine if you have a membership or if you do not 
     * have a membership.
     * 
     * @param theMembership Incoming membership of the customer.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
        
    }
    
    /**
     * This method will calculate the total price of the items in the shopping 
     * cart and will apply a discount if you have a membership. Including calculating 
     * a bulk quantity discount as well.
     * 
     * @return The total price of all the items in the cart.
     */
    public BigDecimal calculateTotal() {
        BigDecimal totalAmount = BigDecimal.ZERO; 
        for (int i = 0; i < myShoppingCart.size(); i++) {
            final ItemOrder currentOrder = myShoppingCart.get(i);
            final BigDecimal currentPrice = currentOrder.getItem().getPrice();
            final BigDecimal currentQuantity = BigDecimal.valueOf(currentOrder.getQuantity());
            final BigDecimal currentBulkPrice = currentOrder.getItem().getBulkPrice();
            final BigDecimal currentBulkQuantity = 
                            BigDecimal.valueOf(currentOrder.getItem().getBulkQuantity());
            
            BigDecimal bulkQuantityPrice = null;
            BigDecimal bulkPrice = null;
            BigDecimal bulkTotal = null;
            
            if (currentOrder.getItem().isBulk()) {
                bulkQuantityPrice = currentBulkPrice.
                                    multiply(currentQuantity.
                                    divide(currentBulkQuantity, RoundingMode.DOWN));
                bulkPrice = (currentQuantity.
                            remainder(currentBulkQuantity)).multiply(currentPrice);
                bulkTotal = bulkPrice.add(bulkQuantityPrice);
            }
            
            if (currentOrder.getItem().isBulk() && myMembership) {
                totalAmount = totalAmount.add(bulkTotal);
            } else {
                totalAmount = totalAmount.add(currentPrice.multiply(currentQuantity));
            }
        }
        return totalAmount;
    }
    
    /**
     * This will clear the current shopping cart to 
     * empty and remove all items orders in it.
     */
    public void clear() {
        myShoppingCart.clear();
    }
    
    /**
     * The String representation of the shopping cart will be formatted in a 
     * organized fashion.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append(myShoppingCart);
        return builder.toString();
    }

}
