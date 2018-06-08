/*
 * MyRecipe.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-1
 * 
 */
import java.util.ArrayList;

/**
 * This class will hold the recipe to all the ingredients to 
 * make a baron burger correctly including order.
 * 
 * @author Armoni
 *@version March 26, 2018 
 */
public enum MyRecipe {
	/** This ingredient is a Pickle. **/
	PICKLE("Pickle"),
	
	/** This ingredient is a Bun. **/
    TOP_BUN("Bun"),
    
	/** This ingredient is aMayonnaise. **/
    MAYONNAISE("Mayonnaise"),
    
	/** This ingredient is Baron-Sauce. **/
    BARON_SAUCE("Baron-Sauce"),
    
	/** This ingredient is Lettuce. **/
    LETTUCE("Lettuce"),
    
	/** This ingredient is a Tomato.  **/
    TOMATO("Tomato"),
    
	/** This ingredient is a Onion.**/
    ONIONS("Onions"),
    
	/** This ingredient is the top patty. **/
    BEEF3("Beef"),
    
	/** This ingredient is the second to top patty. **/
    BEEF2("Beef"),
    
	/** This ingredient is Pepperjack. **/
    PEPPERJACK("Pepperjack"),
    
	/** This ingredient is Mozzarella. **/
    MOZARELLA("Mozzarella"),
    
	/** This ingredient is Cheddar. **/
    CHEDDER("Cheddar"),
    
	/** This ingredient is the first patty to be placed. **/
    BEEF1("Beef"),
    
	/** This ingredient is Mushrooms. **/
    MUSHROOMS("Mushrooms"),
    
	/** This ingredient is Mustard. **/
    MUSTARD("Mustard"),
    
	/** This ingredient is Ketchup. **/
    KETCHUP("Ketchup"),
    
	/** This Ingredient is the bottom bun. **/
    BOTTOM_BUN("Bun");

	/** This is the field to hold the enums text. **/
    private String myText;

    /**
     * This will construct a enum along with 
     * the text associated with it.
     * 
     * @param text the current text of the enum.
     */
    MyRecipe(String text) {
        myText = text;
    }

    /**
     * This will return the text that is associated with 
     * the current enum.
     */
    public String toString() {
        return myText;
    }
    
    /**
     * This will allow for the enum to text to be changed to 
     * allow the patties names to be changed.
     * 
     * @param thePatty the incoming new patty type.
     */
    public static void changeEnumPatty(String thePatty) {
    		BEEF1.myText = thePatty;
    		BEEF2.myText = thePatty;
    		BEEF3.myText = thePatty;
    }
    
    /**
     * This will find the current enum to create based off
     * the current string the user sent in to find. 
     * 
     * @param theString the incoming string that corresponding to the correct enum.
     * @return temp the enum based off the string that was sent in.
     */
    public static MyRecipe findEnum(String theString) {
    		ArrayList<MyRecipe> Enums = new ArrayList<>();
    		Enums.add(MyRecipe.CHEDDER);
    		Enums.add(MyRecipe.MOZARELLA);
    		Enums.add(MyRecipe.PEPPERJACK);
    		Enums.add(MyRecipe.LETTUCE);
    		Enums.add(MyRecipe.TOMATO);
    		Enums.add(MyRecipe.ONIONS);
    		Enums.add(MyRecipe.PICKLE);
    		Enums.add(MyRecipe.MUSHROOMS);
    		Enums.add(MyRecipe.KETCHUP);
    		Enums.add(MyRecipe.MUSTARD);
    		Enums.add(MyRecipe.MAYONNAISE);
    		Enums.add(MyRecipe.BARON_SAUCE);
    		
    		MyRecipe temp = null;
    		for(int i = 0; i < Enums.size(); i++) {
    			if (Enums.get(i).myText.equals(theString)) {
    				temp = Enums.get(i);
    			}
    		}
		return temp;
    }
}