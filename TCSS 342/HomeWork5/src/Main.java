/*
 * Main.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-5
 * 
 */

/**
	 * The main method will create a maze object allowing for you to view the 
	 * maze in the console. Will allow for you to control while searching the entire
	 * maze and also include the unique solution.
	 *  
	 * @author Armoni Atherton athera@uw.edu
	 * @version May 30, 2018 
	 *
	 */
public class Main {
	/**
	 * Static main method used to run the program and test the 
	 * program elements.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		Maze firstMaze = new Maze(5, 5, true);
		firstMaze.display(); 
		
		Maze secondMaze = new Maze(30, 30, false);
		secondMaze.display(); 
//		
//		Maze thirdMaze = new Maze(9, 4, false);
//		thirdMaze.display(); 
//		
//		Maze fourMaze = new Maze(20, 15, false);
//		fourMaze.display(); 
	}

}
