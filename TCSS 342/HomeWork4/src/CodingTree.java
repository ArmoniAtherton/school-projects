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
import java.util.PriorityQueue;

/**
 *  This class will create a Huffman tree and will go through the given 
 *  incoming text and will compress the file.
 * 
 * @author Armoni
 * @version May 3, 2018 
 */
public class CodingTree {
	
	/* This is will be the default size for the HashTable. */
	private static final int HASHTABLE_SIZE = 32768;
	
	/** This will hold the nodes to build the Huffman tree. */
	private PriorityQueue<Node> myPriorityQueue = new PriorityQueue<Node>();
	
	/** This will hold unique Strings along with Binary Code. */
	private MyHashTable<String, String> myCodes = new MyHashTable<>(HASHTABLE_SIZE);

	/** This will hold unique Strings along with there frequency. */
	private MyHashTable<String, Integer> myWords = new MyHashTable<>(HASHTABLE_SIZE);
	
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
		private String myString;
		
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
		 * @param theString
		 * @param theFrequency the occurrences of the specific character.
		 * @param theLeft the node that will be stored as the left node.
		 * @param theRight the node that will be stored as the right node.
		 */
		private Node(String theString, int theFrequency, Node theLeft, Node theRight) {
			myString = theString;
			myFrequency = theFrequency;
			myLeft = theLeft;
			myRight = theRight;
		}
		
		/**
		 * This will allow for you to check if the current node is a leaf or not.
		 * @return a boolean if the node is a leaf or not.
		 */
		private boolean isLeaf() {
			return (myRight == null && myLeft == null);
		}
		
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
			return "Freq: " + myFrequency + ", Char: " + myString + "\n";
			
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

	StringBuilder temp = new StringBuilder();
	char [] charMessage = message.toCharArray();
	int size = charMessage.length;
	
	for (int i = 0; i < size; i++) {
		if (containsLetter(charMessage[i])) {
			temp.append(charMessage[i]);
			i++;
			//This will check if next character is invalid if so will output the string.
			if (!containsLetter(charMessage[i])) {
				//This will check if it is already in hash map.
				if (myWords.containsKey(temp.toString())) {
					myWords.put(temp.toString(), myWords.get(temp.toString()) + 1);
				//This will add word if not in list.
				} else {
					myWords.put(temp.toString(), 1);
				}
				temp.delete(0, temp.length());
			}
			i--;
		 //This will make a word with the invalid characters.
		} else {
			temp.append(charMessage[i]);
			if (myWords.containsKey(temp.toString())) {
				myWords.put(temp.toString(), myWords.get(temp.toString()) + 1);
			//This will add word if not in list.
			} else {
				myWords.put(temp.toString(), 1);
			}
			temp.delete(0, temp.length());
		}
	}
//	 myWords.stats();
	//This will create all  the nodes with one Word/String.
	createNodes();
	
	StringBuilder sb = new StringBuilder();
	buildHuffmanTree();
	Node myRoot = myPriorityQueue.poll();
	buildTreeCodes(myRoot, sb);	
	}
	
	/**
	 * This method will check if the current character is a legal character to create a 
	 * word.
	 * 
	 * @param theLetter the incoming letter.
	 * @return if the letter is a vaild letter or not.
	 */
	private boolean containsLetter(char theLetter) {
		boolean flag = false; 
		if ((theLetter >= 'a' && theLetter <= 'z')  || (theLetter >= 'A' && theLetter <= 'Z') 
			|| (theLetter >= '0' && theLetter <= '9') || theLetter == '\'' || theLetter == '-' ) {
			flag = true;
		}
		return flag;
		
	}
	/**
	 * Private helper method that will make a node with the frequency and the String 
	 * and insert into a priority queue. 
	 */
	private void createNodes() {
		for(MyHashTable<String, Integer>.TableNode entry : myWords.entrySet()) {  
			if (entry.getKey() != null && entry.getValue() != null) {
			myPriorityQueue.add(new Node(entry.getKey(), entry.getValue(), null, null));
			}
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
	 * @param theCodes the Hash map to append the Strings to there respective binary code.
	 */
	private void buildTreeCodes(Node theNode, StringBuilder theBinaryCode) {
		if (theNode.isLeaf()) {
			myCodes.put(theNode.myString, theBinaryCode.toString());
			
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
	 * @return the decoded string that will represent the original text.
	 */
	public String decode(String bits, MyHashTable<String, String> theCodes) {
	HashMap<String, String> codes = new HashMap<>();
	for(MyHashTable<String, String>.TableNode entry : theCodes.entrySet()) {  
		if (entry.getKey() != null && entry.getValue() != null) {
			codes.put(entry.getValue(), entry.getKey());
		}
	}
	boolean flag = true;
	StringBuilder built = new StringBuilder();
	int length = bits.length();
	int pt1 = 0;
	for(int i = 0; i < length && flag; i++) {
		//This will check if it contains the binary value.
		if (codes.get(bits.substring(pt1, i)) != null) {
			String temp = bits.substring(pt1, i);
			//This will check if end of file.
			if (codes.get(temp).equals("۞")) {
				flag = false;
				//Keep appending the string.
			} else {
				built.append(codes.get(temp));
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
	 * @return the code of Strings and there binary representation.
	 */
	public MyHashTable<String, String> getMyCodes(){
		return myCodes;
	}
}