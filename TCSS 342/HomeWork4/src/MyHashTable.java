/*
 * MyHashTable.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-3
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 *  This class will create a hash table allows for any generic types to store into the 
 *  HashTable. Will allow for multiple methods to add, contains key or get.
 * 
 * @author Armoni
 * @version May 18, 2018 
 *
 * @param <K> the generic key value.
 * @param <V> the generic value.
 */
public class MyHashTable<K, V> {
	
	/**
	 * This is a inner class that will allow for the simulation of a 
	 * map by allowing the object to hold a key and value pair.
	 * 
	 * @author Armoni
	 */
	public class TableNode {
		
		/** This will store the key value. */
		private K myKey;
		
		/** This will store the value. */
		private V myValue;
		
		/**
		 * This is the constructor that will allow for setting the 
		 * key and value pair.
		 * 
		 * @param theKey the key value to be set.
		 * @param theValue the value related to the key.
		 */
		public TableNode(K theKey, V theValue) {
			myKey = theKey;
			myValue = theValue;
		}
		
		/**
		 * This will be the default constructor for creating a node 
		 * will set the key and value both to null values.
		 */
		public TableNode() {
			myKey = null;
			myValue = null;
		}
		
		/**
		 * This will get the key value that is stored in the node.
		 * 
		 * @return the current key.
		 */
		public K getKey() {
			return myKey;
			
		}
		
		/**
		 * This will get the current value that is stored in the node.
		 * 
		 * @return the  value in the node.
		 */
		public V getValue() {
			return myValue;
			
		}
		
		/**
		 * This will print out the node which will print out the 
		 * Key and the value pair. Allows for seeing the contents 
		 * in the node.
		 */
		public String toString() {
			return " (" + myKey + ", " + myValue + ")";
		}
	}
	
	/** This will be used for the maintaining the hash table.*/
	private ArrayList<TableNode> myHashTable;
	
	/** This will be store the probe history  throughout this program. */
	private ArrayList<Integer> myHistogram;
	
	/** This will hold the standard size of the hashtable. */
	private int mySize;
	
	/** This will keep track of the total entries in the hash table. */
	private int myTotalEntries;
	
	/** This will hold the maximum number of probes. */
	private int myMaxProb;
	
	/**
	 * This will be the constructor that allows for you to construct a new
	 * hash table that will allow for you to set the correct size.
	 * 
	 * @param capacity the size of the hashtable.
	 */
	MyHashTable(int capacity) {
		mySize = capacity;
		myHashTable = new ArrayList<TableNode>(mySize);
		myHistogram = new ArrayList<>(Collections.nCopies(1000, 0));
		myTotalEntries = 0;
		myMaxProb = 0;
		
		int i = 0;
		//This will instantiate the list with a empty linked list.
		while(i < mySize) {
			myHashTable.add(new TableNode());
			i++;
		}
	}
	
	/**
	 * This will put a new entry into the hash map and will hash the value.
	 * If their is already a element in the current spot it will linear prob until
	 * finding a spot to insert.
	 * 
	 * @param searchKey the specified key put into the HashTable.
	 * @param newValue the specified value to put in the HashTable.
	 */
	public void put(K searchKey, V newValue) {
		int  tempMaxProb = 0;
		int index = hash(searchKey);
		int pCnt = 0;
		int totalSize = myHashTable.size();
		boolean isFound = false;
		//This for loop will update key associated with the value. If not same at hash value will search the whole list.
		while (pCnt < totalSize && !isFound) {
			if (searchKey.equals(myHashTable.get(index).myKey)) {
				myHashTable.get(index).myValue = newValue;
				isFound = true;
			} else {
				tempMaxProb++;
				index =( index + 1) % mySize;
				pCnt++;
				//This will check if a empty spot if so we just stop searching due to linear probing mechanics.
				if (myHashTable.get(index).myKey == null) {
					break;
				}
			}
		}
		//Will add in the new node if not found.
		if(!isFound) {
			myTotalEntries++;
			boolean flag = true;
			int cnt = hash(searchKey); //was index...
			pCnt = 0;
			while(pCnt < totalSize && flag) {
			//This will see if there is any data in the node.
				if (myHashTable.get(cnt).myKey == null) {
					myHashTable.get(cnt).myKey = searchKey;
					myHashTable.get(cnt).myValue = newValue;
					flag = false;
					//This will add to histogram.
					int n = myHistogram.get(pCnt);
					myHistogram.set(pCnt, ++n);
				//If data in node we want to increment to next node. 
				} else {
					//If at the last index of the array. restart to begining of array.
					cnt = (cnt + 1) % mySize;
					pCnt++;
				}
			}
		}
		//This will find the max linear prob.
		if (tempMaxProb > myMaxProb) {
			myMaxProb = tempMaxProb;
		}
	}
	
	/**
	 * This will look the the search key item and will linear prob to find 
	 * if not found the first time will send back to the user the value.
	 * 
	 * @param searchKey the search key entry that is being looked for.
	 * @return the value that is associated with the key.
	 */
	public V get(K searchKey) {
		int index = hash(searchKey);
		int totalSize = myHashTable.size();
		boolean isFound = false;
		int pCnt = 0;
		
		// This will go through and get the key if not found will linear prob.
		while(pCnt < totalSize && !isFound) {
			if (searchKey.equals(myHashTable.get(index).myKey)) {
				//This will break the loop.
				isFound = true;
			} else {
				index =( index + 1) % mySize;
				pCnt++;
			}
		}
		
		if (isFound) {
			return myHashTable.get(index).myValue;
		} else {
			return null;
		}
	}
	
	/**
	 * This will search through the entire Hash Table and look for the current sent in 
	 * search key to see if it is in the Hash Table.
	 * 
	 * @param searchKey the key that is being looked for.
	 * @return this will send back if it was found or not.
	 */
	public boolean containsKey(K searchKey) {
		int index = hash(searchKey);
		int totalSize = myHashTable.size();
		boolean isFound = false;
		int pCnt = 0;
		// This will go through and try to find  the key if not found will linear prob.
		while(pCnt < totalSize && !isFound) {
			if (searchKey.equals(myHashTable.get(index).myKey)) {
				isFound = true;
//				System.out.println("Found");
			} else if (myHashTable.get(index).myKey == null) {
					return false;
			} else {
				index =( index + 1) % mySize;
				pCnt++;
			}
		}
		
		return isFound;
	}
	
	/**
	 * This will display all the stats on the current Hash Table. Will display to the user the number of entries,
	 * number of buckets, histogram of probes and the total fill percentage of the table along with more information
	 * on the table.
	 */
	public void stats() {
		System.out.println("Hash Table Stats \n================");
		System.out.println("Number of Entries: " + myTotalEntries);
		System.out.println("Number of buckets: " + mySize);
		System.out.println("Histogram of Probes: ");
	
	    histogram();
	    
		
		double percentage =((double)myTotalEntries / (double)mySize) * 100;
		System.out.println("Fill percentage: " + percentage);
		System.out.println("Max Linear Prob: " + myMaxProb);
		System.out.println("Average Linear Prob: " + averageLinearProb());
		System.out.println("================");
		
	}
	
	/**
	 * This will create the histogram table that will show the 
	 * history of probes  grouping  total probe numbers together.
	 */
	private void histogram() {
//		System.out.println(myHistogram.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		for (int i = 0; i < myMaxProb; i++ ) {
			//ATTEMPT to format it correctly.
			if (i != 0 && i % 40 == 0) {
				sb.append("\n");
			} 
			//This will 
			if (i == myMaxProb - 1) {
				sb.append(myHistogram.get(i));
			} else {
				sb.append(myHistogram.get(i) + ", ");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	/**
	 * This will find the average number between all the 
	 * linear probes.
	 * 
	 * @return will show the average between how many times it takes to probes.
	 */
	public double averageLinearProb() {
		double average = 0;
		for (int i = 0; i < myMaxProb; i++ ) {
			average += myHistogram.get(i) * i;
		}
		return (average/ myTotalEntries) ;
	}
	
	/**
	 * This will get the hash value from the generic key value using the built in hash function
	 * implemented by java.
	 * 
	 * @param key the incoming generic key.
	 * @return This will hashed value of the key.
	 */
	private int hash(K key) {
		return (Math.abs(key.hashCode())) % mySize;

	}
	
	/**
	 * This will allow for the visual representation of the HashTable. Allowing 
	 * the user to see the contents current stored.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int cnt = 0;
		while(cnt < mySize) {
			if (myHashTable.get(cnt).myKey != null) {
				if ( cnt == mySize -1) {
					sb.append(myHashTable.get(cnt).toString());
				} else {
					sb.append(myHashTable.get(cnt).toString() +",");
				}
			} 
			cnt++;
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * This will allow for the user to access the HashTable 
	 * out side of this class.
	 * @return the HashtTable contents.
	 */
	public ArrayList<TableNode> entrySet() {
		return myHashTable;
		
	}
}
