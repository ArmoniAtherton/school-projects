/*
 * Maze.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-5
 * 
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *  This class will  generate a 2­dimensional n by m maze with no cycles.
 * 
 * @author Armoni
 * @version May 30, 2018 
 */
public class Maze {
	/**
	 * This will be used to simulate each block in the maze allowing for you to create 
	 * walls around the current coordinate.
	 * 
	 * @author Armoni
	 * @version May 30, 2018 
	 */
	public class Node {
		/** This will hold the X coordinate location in the maze. */
		private int myX;
		
		/** This will hold the Y coordinate location in the maze. */
		private int myY;
		
		/** This will represent the North wall of the current node. */
		private boolean myNorth;
		
		/** This will represent the South wall of the current node. */
		private boolean mySouth;
		
		/** This will represent the East wall of the current node. */
		private boolean myEast;
		
		/** This will represent the West wall of the current node. */
		private boolean myWest;
		
		/** This will check if the node has been visted or not. */
		private boolean myIsVisted;
		
		/** This will allow the node to be set to the end node in the maze. */
		private boolean myEnd;
		
		/**Will hold all the neighbors of the current node. */
		ArrayList<Node> myNeighbors = new ArrayList<>();
		
		/**
		 * This will instantiate a node with a x and y coordinate
		 * relative to the location in the maze.
		 * 
		 * @param theX the row coordinate.
		 * @param theY the Column coordinate.
		 */
		public Node(int theX, int theY) {
			myX = theX;
			myY = theY;
			//Set all walls to true off instantiation.
			myNorth = true;
			mySouth = true;
			myEast = true;
			myWest = true;
			
			myIsVisted = false;

			myEnd = false;
		}
		
		/**
		 * The string representation of the node displaying its x coordinate 
		 * and the y coordinate.
		 */
		public String toString() {
			return "("+ myX + ", " + myY + ")";
		}
	}
	
	/** This will keep track of the total number of nodes in the maze. */
	private int myTotalNodes;
	
	/** Holds the amount of rows the maze will hold.*/
	private int myRows;
	
	/** Holds the amount of columns the maze will hold.*/
	private int myColumns;
	
	/** Check if you want to show each iteration of the maze being dug out. */
	private boolean myDebug;
	
	/** Will represent the current maze. */
	private Node [][] myMaze;
	
	/** This will be the solution  to the maze that is more friendly to print. */
	private String[][] myMazeSolution;
	
	/** This will hold the current maze location used to search whole maze. */
	private Stack<Node> myMazeLocation;
	
	/** This will hold the correct solution of nodes to print to recreate solution tree. */
	private Stack<Node> myMazeAnswerKey;
	
	/**
	 * This is the constructor allowing you to set up the maze with the specified
	 * size and displaying options.
	 * 
	 * @param width the column / width size of the maze.
	 * @param depth the row / depth size of the maze.
	 * @param debug boolean to show each iteration of making the maze.
	 */
	public Maze(int width, int depth, boolean debug) {
		myColumns = width; 
		myRows = depth;
		myDebug = debug;
		myTotalNodes = myRows * myColumns;
		myMaze = new Node[myRows][myColumns];
		//Will allow to create a maze to display to the console.
		myMazeSolution = new String[(myRows * 2 )+ 1][(myColumns * 2 )+ 1]; 
		myMazeLocation = new Stack<>();
		myMazeAnswerKey = new Stack<>();
		
		//This will set up the maze correctly.
		createMaze();
		
		//This will intialize the maze solution matrix.
		setUpSolutionMaze();
		
		//Will display the maze before anything as been edited.
		if (myDebug) {
			debug();
		}
		//Will carve out the maze. 
		generateMaze();
		
		//Will show finished version of the maze.
		if (myDebug) {
			debug();
		}
	}

	/**
	 * This will create the maze  placing in a default
	 * node into each spot into the maze. 
	 */
	private void createMaze() {
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myColumns; c++) {
				myMaze[r][c] = new Node(r, c); 
			}
		}

		myMaze[0][0].myNorth = false;
		//Set the exit to the maze.
		myMaze[myRows - 1][myColumns - 1].myEnd = true;
		myMaze[myRows - 1][myColumns - 1].mySouth = false;
		
		//Will find all neighbors of each node.
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myColumns; c++) {
				getNeighbors(myMaze[r][c], r, c);
			}
		}
	}
	
	/**
	 * This will find all the neighbors of the current node being sent in.
	 * Will check all the cases of the node is along a wall.
	 * 
	 * @param theNode the incoming node.
	 * @param r the current row.
	 * @param c the current column.
	 */
	private void getNeighbors(Node theNode, int r, int c) {
		//This will check if row is zero.
		if (theNode.myX == 0) {
			//Check top left corner.
			if (theNode.myY - 1 != -1) {
				//Will get the west node if not on top left corner.
				myMaze[r][c].myNeighbors.add(myMaze[r][ c - 1]);
			}
			//Check South
			myMaze[r][c].myNeighbors.add(myMaze[r + 1][c]);
					
			//Check top right corner
			if (theNode.myY + 1 != myColumns) {
				//will get the east node if not on the top right corner.
				myMaze[r][c].myNeighbors.add(myMaze[r][ c + 1]);
				
			}
			//This will check if colum is zero
		} else if (theNode.myY == 0) {
			//Check North
			myMaze[r][c].myNeighbors.add(myMaze[r - 1][ c]);
			
			//Check East
			myMaze[r][c].myNeighbors.add(myMaze[r][c + 1]);
			
			//This will check the bottom left corner.
			if (theNode.myX + 1 != myRows) {
				//Will get the south node if not in bottom left corner.
				myMaze[r][c].myNeighbors.add(myMaze[r + 1][ c]);
			}
			
			
			//This will check if last elements in the row are zero.
		} else if (theNode.myX == myRows - 1) {
			//Check West
			myMaze[r][c].myNeighbors.add(myMaze[r][c - 1]);
			
			//Check North
			myMaze[r][c].myNeighbors.add(myMaze[r - 1][c]);
			
			//Check East
			if (theNode.myY + 1 != myColumns) {
				//This get the east node if not in bottom right corner
				myMaze[r][c].myNeighbors.add(myMaze[r][c + 1]);
			}
			
			//This will check if the last elements in column are zero.
		} else if (theNode.myY == myColumns -1) {
			//Check north
			myMaze[r][c].myNeighbors.add(myMaze[r - 1][c]);
			
			//Check West
			myMaze[r][c].myNeighbors.add(myMaze[r][c - 1]);
			
			//Check South
			myMaze[r][c].myNeighbors.add(myMaze[r + 1][c]);
		} else {
			//Check North
			myMaze[r][c].myNeighbors.add(myMaze[r - 1][c]);
			
			//Check East
			myMaze[r][c].myNeighbors.add(myMaze[r][c + 1]);
			
			//Check South
			myMaze[r][c].myNeighbors.add(myMaze[r + 1][c]);
			
			//Check West
			myMaze[r][c].myNeighbors.add(myMaze[r][c - 1]);
		}
		
	}
	
	/**
	 * This will generate a maze will go through and dig out the 
	 * entire maze not allowing any cycles to be generated.
	 */
	private void generateMaze() {
		boolean flag = false;
		Random randomDirection = new Random();
		ArrayList<Node> temp;
		//Mark the starting position as true;
		myMaze[0][0].myIsVisted = true;
		//Start with the first node.
		Node currentNode = myMaze[0][0];
		int vistedNode = 1;
		//This will make sure it visits all the nodes.
		while (vistedNode < myTotalNodes) {
			if (myDebug) {
				debug();
			}
			//set boolean when found the end will create solution to maze.
			if ((!flag)) {
				myMazeAnswerKey.push(currentNode);
				if (currentNode.myEnd == true) {
					flag = true;
				}
			}
			//This will get valid neighbors.
			temp = checkNotVistedNeighbors(currentNode.myNeighbors);
			//If there is at least one pick at random one of them.
			if(temp.size() != 0) {
				int index = randomDirection.nextInt(temp.size());
				//knock down the wall between them.
				knockDownWall(currentNode, temp.get(index));
				//Push the current location on to the stack.
				myMazeLocation.push(currentNode);
				currentNode = temp.get(index);
				currentNode.myIsVisted = true;
				vistedNode++;
				
			} else {
				if (!myMazeLocation.isEmpty()) {
					//Will back track back one step in the maze
					currentNode = myMazeLocation.pop();
				}
				//Back track for the solution.
				if (!flag) {
					if (!myMazeAnswerKey.isEmpty()) {
					//Will back track back two steps in the maze or solution will show extra +/visted areas
					myMazeAnswerKey.pop();
					myMazeAnswerKey.pop();
					}	
				}	
		}
		}
		//This will check if the last spot in the maze if did set the very end point will set it.
		if (myMazeAnswerKey.peek().myX != myRows -1 || myMazeAnswerKey.peek().myY != myColumns -1) {
			myMazeAnswerKey.add(myMaze[myRows -1][myColumns -1]);
			}
		}
	
	/**
	 * This will check if all the neighbors of the current node have been visited or not.
	 * 
	 * @param theNeighbors of the current node/list that has been sent in.
	 * @return  will return a list of unvisited nodes.
	 */
	public ArrayList<Node> checkNotVistedNeighbors(ArrayList<Node> theNeighbors) {
		ArrayList<Node> vaildNeighbors = new ArrayList<>();
		for (int i = 0; i < theNeighbors.size(); i++) {
			if (theNeighbors.get(i).myIsVisted == false) {
				vaildNeighbors.add(theNeighbors.get(i));
			}
		}
		return vaildNeighbors;
	}
	
	/**
	 * This will knock down the wall between the shared walls between two nodes.
	 * 
	 * @param theCurrentNode the current node of the wall to be knocked down.
	 * @param theNextNode the neighbor node of the wall to be knocked down.
	 */
	public void knockDownWall(Node theCurrentNode, Node theNextNode) {
		//If current x value is bigger than next x value path moved up/north.
		if (theCurrentNode.myX > theNextNode.myX) {
			//North
			theCurrentNode.myNorth = false;
			theNextNode.mySouth = false;
			
			//If current x value is smaller than next x value path moved down/south.
		} else if (theCurrentNode.myX < theNextNode.myX) {
			//South
			theCurrentNode.mySouth = false;
			theNextNode.myNorth = false;
			
			//If current  y valus is bigger than next y value path moved left/west.
		} else if (theCurrentNode.myY > theNextNode.myY) {
			//West
			theCurrentNode.myWest = false;
			theNextNode.myEast = false;
			//Only direction left is to go right/east.
		} else {
			//East
			theCurrentNode.myEast = false;
			theNextNode.myWest = false;
		}
	}

	/**
	 * This will print out the maze and will show each iteration of the maze being 
	 * create by showing the breaking down of the walls.
	 */
	public void debug() {
		System.out.println("- Debug - ");
		int rowSize = (myRows * 2) + 1;
		int columnSize = (myColumns * 2) + 1;
		
		for (int r = 0; r < myRows; r++ ) {
			for (int c = 0; c < myColumns; c++ ) {
				int tempX = 2 * r + 1;
				int tempY = 2 * c + 1;
				//Check North
				if (myMaze[r][c].myNorth == false) {
					//Will check top right corner.
					if (r == 0 && c == 0) {
						myMazeSolution[tempX - 1][tempY] = "  ";
					} else {
						myMazeSolution[tempX][tempY] = "+ ";
					}
				} else {
					myMazeSolution[tempX - 1][tempY] = "X ";
				}
				
				//Check South
				if (myMaze[r][c].mySouth == false) {
					//This will make sure the exit doesn't display a '+' until actually reached
					if (r == myRows -1  && c == myColumns - 1 && !myMaze[r][c].myIsVisted)  {
						myMazeSolution[tempX + 1][tempY] = "  ";
						myMazeSolution[tempX][tempY] = "  ";
					} else {
						myMazeSolution[tempX + 1][tempY] = "  ";
						myMazeSolution[tempX][tempY] = "+ ";
					}
				} else {
					myMazeSolution[tempX + 1][tempY] = "X ";
				}
				
				//Check West
				if (myMaze[r][c].myWest == false) {
					myMazeSolution[tempX][tempY - 1] = "  ";
					myMazeSolution[tempX][tempY] = "+ ";
				} else {
					myMazeSolution[tempX][tempY - 1] = "X ";
				}
				//Check East
				if (myMaze[r][c].myEast == false) {
					myMazeSolution[tempX][tempY + 1] = " ";
					myMazeSolution[tempX][tempY] = "+ ";
				} else {
					myMazeSolution[tempX][tempY + 1] = "X";
				}
				
			}	
		}
		//This will print out the maze to the console.
		for (int r = 0; r < rowSize; r++ ) {
			for (int c = 0; c < columnSize; c++ ) {
				System.out.print(myMazeSolution[r][c]);
			}
			System.out.println();
		}
		
		System.out.println("");
	}
	
	/**
	 * This will print the maze and the unique solution to solve the maze.
	 */
	public void display() {
		System.out.println("- Display - Unique Solution ");
		Stack<Node> temp = new Stack<>();
		while(myMazeAnswerKey.size() != 0) {
			temp.add(myMazeAnswerKey.pop());
		}
		ArrayList<Node> coordinates = new ArrayList<>();
		while (temp.size() != 0) {
			Node currentNode = temp.pop();
			coordinates.add(currentNode);
		}
		
		int rowSize = (myRows * 2) + 1;
		int columnSize = (myColumns * 2) + 1;
		
		for (int r = 0; r < myRows; r++ ) {
			for (int c = 0; c < myColumns; c++ ) {
				int tempX = 2 * r + 1;
				int tempY = 2 * c + 1;
				//Check North
				if (myMaze[r][c].myNorth == false) {
					//Will check top right corner.
					if (r == 0 && c == 0) {
						myMazeSolution[tempX - 1][tempY] = "  ";
					} else {
						if (checkCoordinates(coordinates, r, c)) {
							myMazeSolution[tempX][tempY] = "+ ";
						} else {
							myMazeSolution[tempX][tempY] = "  ";
						}
					}
				} else {
					myMazeSolution[tempX - 1][tempY] = "X ";
				}
				
				//Check South
				if (myMaze[r][c].mySouth == false) {
					if (checkCoordinates(coordinates, r, c)) {
						myMazeSolution[tempX + 1][tempY] = "  ";
						myMazeSolution[tempX][tempY] = "+ ";
					} else {
						myMazeSolution[tempX + 1][tempY] = "  ";
						myMazeSolution[tempX][tempY] = "  ";
					}
				} else {
					myMazeSolution[tempX + 1][tempY] = "X ";
				}
				
				//Check West
				if (myMaze[r][c].myWest == false) {
					if (checkCoordinates(coordinates, r, c)) {
						myMazeSolution[tempX][tempY - 1] = "  ";
						myMazeSolution[tempX][tempY] = "+ ";
					} else {
						myMazeSolution[tempX][tempY - 1] = "  ";
						myMazeSolution[tempX][tempY] = "  ";
					}
				} else {
					myMazeSolution[tempX][tempY - 1] = "X ";
				}
				
				//Check East
				if (myMaze[r][c].myEast == false) {
					if (checkCoordinates(coordinates, r, c)) {
						myMazeSolution[tempX][tempY + 1] = " ";
						myMazeSolution[tempX][tempY] = "+ ";
					} else {
						myMazeSolution[tempX][tempY + 1] = " ";
						myMazeSolution[tempX][tempY] = "  ";
					}
				} else {
					myMazeSolution[tempX][tempY + 1] = "X";
				}
				
			}	
		}
		
		//Will print the final soultion to the maze.
		for (int r = 0; r < rowSize; r++ ) {
			for (int c = 0; c < columnSize; c++ ) {
				System.out.print(myMazeSolution[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * This will allow for you to check only specific coordinates in the maze. Allowing 
	 * you to only print the solution and not other points.
	 * 
	 * @param coordinates the list of coordinates to check.
	 * @param theRow the current row.
	 * @param theColumn the current column.
	 * @return will return true if the coordinates match the row and column.
	 */
	private boolean checkCoordinates(ArrayList<Node> coordinates, int theRow, int theColumn) {
		boolean flag = false;
		for (int i = 0; i < coordinates.size(); i++) {
			if (coordinates.get(i).myX == theRow && coordinates.get(i).myY == theColumn) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * This will be used to set up the solution maze to default 
	 * values stopping any null pointer exceptions.
	 */
	private void setUpSolutionMaze() {
		int rowSize = (myRows * 2) + 1;
		int columnSize = (myColumns * 2) + 1;
		
		for (int r = 0; r < rowSize; r++ ) {
			for (int c = 0; c < columnSize; c++ ) {
				//Checks if a odd node in place you can't reach within the maze.
				if ((r % 2 == 0 && c % 2 == 0)  || r == 0 || c == 0 || r == rowSize - 1 || c == columnSize - 1) {
					//Check the top left
					if (r == 0) {
						myMazeSolution[r][c] = "X ";
					} else {
						myMazeSolution[r][c] = "X ";
					}
				} else {
					myMazeSolution[r][c] = "  ";
				}
			}
			myMazeSolution[0][1] = "  ";
			myMazeSolution[rowSize -1 ][columnSize - 2] = "  ";
		}
	}
}
