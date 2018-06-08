/*
 * Main.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-1
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * The main method will read in input from a file will parse a string and 
 * decided what ingredients to add to the burger while also calling other
 * classes to instantiate a Burger or a Baron Burger.
 * 
 * @author Armoni Atherton athera@uw.edu
 * @version March 26, 2018 
 *
 */
public class Main {
	
	 /**
	  * Static main method used to run the program and test the 
	  * program elements.
	  * 
	  * @param theArgs Command line arguments.
	 * @throws IOException 
	  */
	public static void main(String[] args) throws IOException {
		FileReader inFile = null;
		
		try {
			inFile = new FileReader("customer.txt");
			BufferedReader fileReader = new BufferedReader(inFile);
			String currentLine;
			int lineNumber = 0;
			//&& lineNumber < 100 add in while loop or not
			while ((currentLine = fileReader.readLine()) != null) {
				System.out.print("Processing Order "+ lineNumber++ + ": ");
				System.out.println(currentLine); // useful for debugging
				parseLine(currentLine);
				System.out.println();
			}
			fileReader.close();
		}  finally {}
//		testMyStack();
//		testBurger();
	}
	
	/**
	 * Parses a line of input from the file and outputs the correct
	 * burger will also customize the burger depending what the 
	 * customer has asked for.
	 * 
	 * @param line The incoming line of ingredients.
	 */
	public static void parseLine(String line) {
		String[] splitStrings = line.split(" ");
		Burger burger;
		boolean flag = false, flagChicken = false, flagVeggie = false;
		for (int i = 0; i < splitStrings.length; i++) {
			if (splitStrings[i].equals("Baron")) {
				flag = true;
			} 
			if (splitStrings[i].equals("Chicken")) {
				flagChicken = true;
			} 
			
			if (splitStrings[i].equals("Veggie")) {
				flagVeggie = true;
			} 
		}
		//Will create a baron burger or burger.
		burger = new Burger(flag);
		
		if (splitStrings[0].equals("Double")) {
			burger.addPatty();
		}
		
		if (splitStrings[0].equals("Triple")) {
			burger.addPatty();
			burger.addPatty();
		} 
		
		if (flagChicken) {
			burger.changePatties("Chicken");
		}
		
		if (flagVeggie) {
			burger.changePatties("Veggie");
		}
		
		int firstIndex = 0;
		int lastIndex = 0;
		for (int i = 0; i < splitStrings.length; i++) {
			if (splitStrings[i].equals("with")) {
				firstIndex = i;
			}
			if (splitStrings[i].equals("but")) {
				lastIndex = i;
			}
		}
		
		boolean removeFlag = false;
		//Check to make sure you find the with part.
		if (firstIndex != 0) {
			firstIndex++;
			if (splitStrings[firstIndex].equals("no")) {
				removeFlag = true;
			}
			//This will check if you found a but part.
			if (lastIndex == 0) {
				lastIndex = splitStrings.length;
			}
			//if removeFlag is true you will remove categories 
			for (int i = firstIndex; i < lastIndex && removeFlag; i++) {
				if (splitStrings[i].equals("Sauce") || splitStrings[i].equals("Cheese") 
						 || splitStrings[i].equals("Veggies")) {
						burger.removeCategory(splitStrings[i]);
					} else {
						burger.removeIngredient(splitStrings[i]);
					}
			}
			//If removeFlag is false you will add categories.
			for (int i = firstIndex; i < lastIndex && !removeFlag; i++) {
				if (splitStrings[i].equals("Sauce") || splitStrings[i].equals("Cheese") 
						 || splitStrings[i].equals("Veggies")) {
						burger.addCategory(splitStrings[i]);
					} else {
						burger.addIngredient(splitStrings[i]);
					}
			}
		}
		//Check if it found a "but".
		if (lastIndex != 0) {
			lastIndex++;
			//This will add the ingredient if removeFlag is true.
			for (int i = lastIndex; i < splitStrings.length && removeFlag; i++) {
				burger.addIngredient(splitStrings[i]);
			}
			//This will remove the ingredient if the removeFlag is false.
			for (int i = lastIndex; i < splitStrings.length && !removeFlag; i++) {
				burger.removeIngredient(splitStrings[i]);
			}
		}
		System.out.println(burger.toString());
	}
	
	/**
	 * This method will test my implemented stack.  Will test again all 
	 * the different methods and will see if it is working right.
	 */
	public static void testMyStack() {
		System.out.println("****TESTING MY STACK****");
		System.out.println("If Expected Value and Actual Value are same the test Passed!!");
		MyStack<Integer> numbers = new MyStack<>();
	    numbers.push(1000);
	    numbers.push(25);
	    numbers.push(145);
	    System.out.print("Testing Push - Expected [145, 25, 1000], Actually: ");
	    System.out.println(numbers.toString() + "\n");
	    numbers.pop();
	    numbers.pop();
	    numbers.pop();
	    System.out.print("Testing Pop - Expected [], Actually:");
	    System.out.println(numbers.toString() + "\n");

	    System.out.print("Testing isEmpty - Expected true, Actually: ");
	    System.out.println(numbers.isEmpty() + "\n");

	    numbers.push(100);
	    System.out.println("Adding Element...");
	    System.out.print("Testing isEmpty - Expected false, Actually: ");
	    System.out.println(numbers.isEmpty());
	    System.out.println("");
	    
	    System.out.print("Testing size - Expected 1, Actually: ");
	    System.out.println(numbers.size());
	    System.out.println("");
	    numbers.push(23);
	    System.out.print("Testing size - Expected 2, Actually:  ");
	    System.out.println(numbers.size());
	    System.out.println("");
	    
	    System.out.print("Testing peek - Expected 23, Actually: ");
	    System.out.println(numbers.peek());
	    System.out.println("");
	    
	    
	    System.out.println("Testing if myStack class is generic "
	    		                          + "If so should be able to make stack of type String.");
	    MyStack<String> strings = new MyStack<>();
	    strings.push("Hello");
	    System.out.print("Testing Generic: Should print [Hello]: ");
	    System.out.println(strings.toString());
	    System.out.println("");
	    System.out.println("****END OF TESTING MY STACK****\n");
	}
	
	/**
	 * This will test the burger  to make sure that all the methods
	 * will work correctly. 
	 */
	public static void testBurger() {
		System.out.println("****TESTING MY BURGER****");
		
		Burger testBaronBurger = new Burger(true);
		Burger testBurger = new Burger(false);
		System.out.print("Testing Constructor - Expected: [Pickle, Bun, Mayonnaise, Baron-Sauce, Lettuce, "
									+ "Tomato, Onions, Pepperjack, Mozzarella, Cheddar, "
									+ "Beef, Mushrooms, Mustard, Ketchup, Bun] \n\t\t\t\t\tActually: ");
		System.out.println(testBaronBurger.toString() + "\n");
		System.out.print("Testing Constructor - Expected: [Bun, Beef, Bun]  \n\t\t\t\t\tActually: ");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Change to Chicken");
		System.out.print("Testing Change Patties - Expected: [Bun, Chicken, Bun]  \n\t\t\t\t\tActually: ");
		testBurger.changePatties("Chicken");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.print("Testing Add Patties - Expected: [Bun, Chicken, Chicken, Bun] \n\t\t\t\t\tActually: ");
		testBurger.addPatty();
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Add Veggies");
		System.out.print("Testing Add Category - Expected: [Pickle, Bun, Lettuce, Tomato, Onions, Chicken, Chicken, Mushrooms, Bun]"
				+ "\n\t\t\t\t\t Actually: ");
		testBurger.addCategory("Veggies");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Add Sauce");
		System.out.print("Testing Add Category - Expected: [Pickle, Bun, Mayonnaise, Baron-Sauce, Lettuce, "
				+ "Tomato, Onions, Chicken, Chicken, Mushrooms, Mustard, Ketchup, Bun], \n\t\t\t\t\t Actually: ");
		testBurger.addCategory("Sauce");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Add Cheese");
		System.out.print("Testing Add Category - Expected: [Pickle, Bun, Mayonnaise, Baron-Sauce, Lettuce, "
				+ "Tomato, Onions, Chicken, Pepperjack, Mozzarella, Cheddar, Chicken, Mushrooms, Mustard, Ketchup, Bun]" + 
				"\n\t\t\t\t\t Actually: ");
		testBurger.addCategory("Cheese");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Remove Cheese");
		System.out.print("Testing Remove Category - Expected: [Pickle, Bun, Mayonnaise, Baron-Sauce, Lettuce, "
				+ "Tomato, Onions, Chicken, Chicken, Mushrooms, Mustard, Ketchup, Bun]\n\t\t\t\t\t\tActually: ");
		testBurger.removeCategory("Cheese");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Remove Sauce");
		System.out.print("Testing Remove Category - Expected: [Pickle, Bun, Lettuce, Tomato, Onions, Chicken, Chicken, Mushrooms, Bun]"
				+ "\n\t\t\t\t\t\tActually: ");
		testBurger.removeCategory("Sauce");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Remove Veggies");
		System.out.print("Testing Remove Category - Expected: [Bun, Chicken, Chicken, Bun]"
				+ "\n\t\t\t\t\t\tActually: ");
		testBurger.removeCategory("Veggies");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Add Cheddar");
		System.out.print("Testing Add Ingredient - Expected: [Bun, Chicken, Cheddar, Chicken, Bun]"
				+ "\n\t\t\t\t\t  Actually: ");
		testBurger.addIngredient("Cheddar");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("Remove Cheddar");
		System.out.print("Testing Remove Ingredient - Expected: [Bun, Chicken, Chicken, Bun]"
				+ "\n\t\t\t\t\t\t Actually: ");
		testBurger.removeIngredient("Cheddar");
		System.out.println(testBurger.toString() + "\n");
		
		System.out.println("****END OF TESTING MY BURGER****");
	}
}
