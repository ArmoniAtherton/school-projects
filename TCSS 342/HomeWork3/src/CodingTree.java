/*
 * CodingTree.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-3
 * 
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Armoni
 * @version May 3, 2018 
 */

public class CodingTree {
	
	/** This will hold the nodes to build the Huffman tree. */
	private myPriorityQueue<Node> myPriorityQueue = new myPriorityQueue<Node>();
	
	/** This will hold unique characters along with there frequency. */
	private Map<Character, Integer> myCharacters = new HashMap<>();
	
	/** This will store my character and its binary code for it. */
	private Map<Character, String> myCodes =  new HashMap<>();
	
	/**
	 * This is a inner class that will allow you to create nodes allowing for the 
	 * construction of a Huffman tree.
	 * 
	 * @author Armoni
     * @version May 3, 2018 
	 */
	private class Node implements Comparable<Node>{
		
		/** This will store the right and left node. */
		private Node myLeft,  myRight;
		
		/** This will store char associated with the node. */
		private char myChar;
		
		/** This will hold the frequency of the character in the node. */
		private int myFrequency;
		
		/**
		 * This is the constructor for the Node that will allow for you to create
		 * a Node with no character in it.
		 * 
		 * @param theFrequency the occurrences of the specific character.
		 * @param theLeft the node that will be stored as the left node.
		 * @param theRight the node that will be stored as the right node.
		 */
		private Node(int theFrequency, Node theLeft, Node theRight) {
			myFrequency = theFrequency;
			myLeft = theLeft;
			myRight = theRight;
			
		}
		
		/**
		 * This is the constructor for the Node that will allow for you to create
		 * a Node with a character in it.
		 * 
		 * @param theChar
		 * @param theFrequency the occurrences of the specific character.
		 * @param theLeft the node that will be stored as the left node.
		 * @param theRight the node that will be stored as the right node.
		 */
		private Node(char theChar, int theFrequency, Node theLeft, Node theRight) {
			myChar = theChar;
			myFrequency = theFrequency;
			myLeft = theLeft;
			myRight = theRight;
		}
		
		/**
		 * This will allow for you to check if the current node is a leaf or not.
		 * 
		 * @return a boolean if the node is a leaf or not.
		 */
		private boolean isLeaf() {
			return (myRight == null && myLeft == null);
		}
		//This is implemented for when using the priority queue in java.
		/**
		 * This is implemented for the priority queue when organizing elements 
		 * in the priority queue.
		 */
		@Override
		public int compareTo(Node theNode) {
			if (theNode == null) {
				return 0;
			} else {
				return this.myFrequency - theNode.myFrequency;
			}
		}
		
		/**
		 * This will allow for you to print out the custom representation of the node.
		 */
		@Override
		public String toString() {
			return "Freq: " + myFrequency + ", Char: " + myChar + "\n";
			
		}
	}
	
	/**
	 *  This is the constructor for the Huffman coding tree class that will read in the string
	 *  and find the frequency of the characters in the file. Will call other private helper methods to 
	 *  build the huffman coding tree.
	 *  
	 * @param message This is the whole file to create a huffman tree on.
	 */
	public CodingTree(String message) {
		//This will go through the string and count the characters.
		for(int i = 0; i < message.length(); i++) {
			char currentChar = message.charAt(i);
			if(myCharacters.containsKey(currentChar)) {
				myCharacters.put(currentChar, myCharacters.get(currentChar).intValue() + 1);
			} else {
				myCharacters.put(currentChar, 1);
			}
		}
		//This will create all  the nodes with one character.
		createNodes();

		StringBuilder sb = new StringBuilder();
		buildHuffmanTree();
		Node myRoot = myPriorityQueue.poll();
		buildTreeCodes(myRoot, sb);
	}
	
	/**
	 * Private helper method that will make a node with the frequency and the character 
	 * and insert into a priority queue. 
	 */
	private void createNodes() {
		for(Entry<Character, Integer> entry : myCharacters.entrySet()) {  
			myPriorityQueue.add(new Node(entry.getKey(), entry.getValue(), null, null));
	    }
	}
	
	/**
	 * Private helper method that will take all the nodes and start to combine them
	 * therefore building the huffman tree until there is only one node left.
	 */
	private void buildHuffmanTree() { 
		while(myPriorityQueue.size() != 1) {
				Node firstTemp = myPriorityQueue.poll();
				Node secondTemp = myPriorityQueue.poll();
				int totalFrequency = firstTemp.myFrequency + secondTemp.myFrequency;
				myPriorityQueue.add(new Node(totalFrequency, firstTemp, secondTemp));
		}
	}
	
	/**
	 * Private helper method that will recursively go through the tree and will build the 
	 * code tables. 
	 * 
	 * @param theNode This is the new incoming node.
	 * @param theBinaryCode This is the binary code related to the specific character.
	 * @param theCodes the Hash map to append the Characters to there respective binary code.
	 */
	private void buildTreeCodes(Node theNode, StringBuilder theBinaryCode) {
		if (theNode.isLeaf()) {
			myCodes.put(theNode.myChar, theBinaryCode.toString());
			
		} else {
				buildTreeCodes(theNode.myLeft, theBinaryCode.append("0"));
				
				theBinaryCode.delete(theBinaryCode.length() - 1, theBinaryCode.length());
				buildTreeCodes(theNode.myRight, theBinaryCode.append("1"));
				theBinaryCode.delete(theBinaryCode.length() - 1, theBinaryCode.length());
		}
	}
	
	/**
	 * This method will take the output of the Huffman's encoding and produce the original
	 * text.
	 * 
	 * @param bits the decoded binary string to convert.
	 * @param codes the binary codes related to a char for decoding the binary strings.
	 * @return the decoded string that will represent the orginial text.
	 */
	public String decode(String bits, Map<Character, String> codes) {
		boolean flag = true;
		StringBuilder built = new StringBuilder();
		int length = bits.length();
		int pt1 = 0;
		for(int i = 0; i < length && flag; i++) {
			//This will check if it contains the binary value.
			if (codes.containsValue(bits.substring(pt1, i))) {
				String temp = bits.substring(pt1, i);
				//This will iterate over all the keys to find the corresponding binary value and map it to the key.
				for(Entry<Character, String> entry : codes.entrySet()) {   
					//Will find the key and add it to the StringBuilder. 
					 if (entry.getValue().toString().equals(temp) && flag) {
						 //Wont add if it comes across the end of file character.
						 if (entry.getKey().equals('۞')) {
							 flag = false;
							 break;
						//Else will keep building the file.
						 } else {
							 built.append(entry.getKey());
							 break;
						 }
					}	
				}
				//This will set the pointer along the string to a new spot along the string
				pt1 = i;
			}
		}
		return built.toString();	
	}
	
	/**
	 * This will allow for the code table to be accessed out side of this class.
	 * 
	 * @return the code of characters and there binary representation.
	 */
	public Map<Character, String> getMyCodes(){
		return myCodes;
	}

}