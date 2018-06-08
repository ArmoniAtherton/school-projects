/*
 * Burger.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-1
 * 
 */

/**
 * This class will make different types of burger's. Will
 * completely customize the burger depending on 
 * what the customer asks for.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version March 26, 2018 
 *
 */
public class Burger {
	
	/** This will hold the customer's burger order. **/
	MyStack<MyRecipe> myCustomerOrder;
	
	/** This will check how many patties that have been requested. **/
	int  myCheckPattyCnt;
	
	/**
	 * This will initialize the burger. Will create either 
	 * a baron burger or just a normal burger.
	 * 
	 * @param theWorks incoming burger type to be made.
	 */
	public Burger(boolean theWorks) {
		myCustomerOrder = new MyStack<>();
		MyRecipe.changeEnumPatty("Beef");
		myCheckPattyCnt = 0;
		if (theWorks) {
			//Creates a baron buger
			myCustomerOrder.push(MyRecipe.BOTTOM_BUN);
			myCustomerOrder.push(MyRecipe.KETCHUP);
			myCustomerOrder.push(MyRecipe.MUSTARD);
			myCustomerOrder.push(MyRecipe.MUSHROOMS);
			myCustomerOrder.push(MyRecipe.BEEF1);
			myCustomerOrder.push(MyRecipe.CHEDDER);
			myCustomerOrder.push(MyRecipe.MOZARELLA);
			myCustomerOrder.push(MyRecipe.PEPPERJACK);
			myCustomerOrder.push(MyRecipe.ONIONS);
			myCustomerOrder.push(MyRecipe.TOMATO);
			myCustomerOrder.push(MyRecipe.LETTUCE);
			myCustomerOrder.push(MyRecipe.BARON_SAUCE);
			myCustomerOrder.push(MyRecipe.MAYONNAISE);
			myCustomerOrder.push(MyRecipe.TOP_BUN);
			myCustomerOrder.push(MyRecipe.PICKLE);
			
		} else {
			
			myCustomerOrder.push(MyRecipe.BOTTOM_BUN);
			myCustomerOrder.push(MyRecipe.BEEF1);
			myCustomerOrder.push(MyRecipe.TOP_BUN);
		}
		
	}
	
	/**
	 * This will change the patty type of the current
	 * customer burger being made.
	 * 
	 * @param pattyType the incoming patty to switch too.
	 */
	public void changePatties(String pattyType) {
			if (pattyType.equals("Beef")) {
				MyRecipe.changeEnumPatty(pattyType);
			} else if (pattyType.equals("Chicken")) {
				MyRecipe.changeEnumPatty(pattyType);
			} else if (pattyType.equals("Veggie")) {
				MyRecipe.changeEnumPatty(pattyType);
			}
	}

	/**
	 * This will add a extra patty to the burger. Will 
	 * go through the stack and add it to the appropriate 
	 * spot in the recipe.
	 */
	public void addPatty() {
		MyStack<MyRecipe> temp = new MyStack<>();
		boolean stop = false;
		//This will add a triple patty.
		if (myCheckPattyCnt == 1) {
			while(myCustomerOrder.size() != 0) {
				MyRecipe tempPop = myCustomerOrder.pop();
				if (tempPop.ordinal() > MyRecipe.BEEF3.ordinal() && !stop ) {
					temp.push(MyRecipe.BEEF3);
					temp.push(tempPop);
					stop = true;
				} else {
					temp.push(tempPop);
				}
			}
//		This will make a double patty
		} else if (myCheckPattyCnt == 0) {
			
			myCheckPattyCnt++;
			while(myCustomerOrder.size() != 0) {
				MyRecipe tempPop = myCustomerOrder.pop();
				if (tempPop.ordinal() > MyRecipe.BEEF2.ordinal() && !stop ) {
					temp.push(MyRecipe.BEEF2);
					temp.push(tempPop);
					stop = true;
				} else {
					temp.push(tempPop);
				}
			}
		}
		
		while(temp.size() != 0) {
			myCustomerOrder.push(temp.pop());
		}
	}
	
	/**
	 * This will add a whole category of ingredients to 
	 * a burger in the correct location according to the 
	 * recipe.
	 * 
	 * @param type the current category to add.
	 */
	public void addCategory(String type) {
		MyStack<MyRecipe> temp = new MyStack<>();
		if (type.equals("Veggies")) {
			addIngredient("Pickle");
			addIngredient("Lettuce");
			addIngredient("Tomato");
			addIngredient("Onions");
			addIngredient("Mushrooms");
				
		} else if (type.equals("Sauce")) {
			addIngredient("Mayonnaise");
			addIngredient("Baron-Sauce");
			addIngredient("Mustard");
			addIngredient("Ketchup");
			
		} else if (type.equals("Cheese")) {
			addIngredient("Pepperjack");
			addIngredient("Mozzarella");
			addIngredient("Cheddar");
			
		}
		while(temp.size() != 0) {
			myCustomerOrder.push(temp.pop());
		}
	}
	
	/**
	 * This will remove a whole category of ingredients to 
	 * a burger and keep all other ingredients in the 
	 * correct location according to the recipe.
	 * 
	 * @param type the current category to remove.
	 */
	public void removeCategory(String type) {
		if (type.equals("Veggies")) {
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
			
		} else if (type.equals("Sauce")) {
			removeIngredient("Mayonnaise");
			removeIngredient("Baron-Sauce");
			removeIngredient("Mustard");
			removeIngredient("Ketchup");
			
		} else if (type.equals("Cheese")) {
			removeIngredient("Pepperjack");
			removeIngredient("Mozzarella");
			removeIngredient("Cheddar");
		}
	}
	
	/**
	 * This will add a ingredient to 
	 * a burger in the correct location according to the 
	 * recipe.
	 * 
	 * @param type the current ingredient to add.
	 */
	public void addIngredient(String type) {
		MyRecipe currentEnum = MyRecipe.findEnum(type);
		MyStack<MyRecipe> temp = new MyStack<>();
		boolean stop = false;
		
		while(myCustomerOrder.size() != 0) {
			MyRecipe tempPop = myCustomerOrder.pop();
			if (tempPop.ordinal() > currentEnum.ordinal() && !stop ) {
				temp.push(currentEnum);
				temp.push(tempPop);
				stop = true;
			} else {
				temp.push(tempPop);
			}
		}
		while(temp.size() != 0) {
			myCustomerOrder.push(temp.pop());
		}
	}
	
	/**
	 * This will remove a ingredient to 
	 * a burger and keep all other ingredients in the 
	 * correct location according to the recipe.
	 * 
	 * @param type the current ingredient to remove.
	 */
	public void removeIngredient(String type) {
		MyStack<MyRecipe> temp =new MyStack<>();
		MyRecipe item;
		while (myCustomerOrder.size() != 0) {
			item = myCustomerOrder.pop();
			if (!(item.toString().equals(type))) {
				temp.push(item);
			} 
		}
		while (temp.size() != 0) {
			myCustomerOrder.push(temp.pop());
		}
	}
	
	/**
	 * This will visually display the burger allowing to 
	 * see the current burger.
	 */
	public String toString() {
		return myCustomerOrder.toString();
	}
}