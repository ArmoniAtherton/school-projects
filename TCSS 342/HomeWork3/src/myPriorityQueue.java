/*
 * myPriorityQueue.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-3
 * 
 */
import java.util.ArrayList;

/**
 * This is a very basic implementation of a priority queue that will be 
 * used by the Coding tree class.
 * 
 * @author Armoni
 * @version May 3, 2018 
 */
public class myPriorityQueue<Type> {
	
	/** This will simulate the priority queue. */
	ArrayList<Type> myQueue;

	/**
	 * This will initialize the priority queue.
	 */
	public <T> myPriorityQueue() {
		myQueue = new ArrayList<Type>();
	}
	
	/**
	 * This will add a element to a priority queue. Will not allow for you to add a null 
	 * element similar to the java implementation.
	 * 
	 * @param theItem the incoming item to be added to the queue.
	 */
	public void add(Type theItem) {
		if (theItem == null) {
			throw new NullPointerException("Can not pass in a null object!");
		} else {
			myQueue.add(theItem);
		}
	}
	
	/**
	 * This will allow for you to remove the element with the highest 
	 * priority out of the queue. 
	 * 
	 * @return The item to be removed. if empty will return null.
	 */
	public Type poll() {
		if(myQueue.size() == 0) {
			return null;
		} 
		myQueue.sort(null);
		return myQueue.remove(0);
	}
	
	/**
	 * This will return the current size of the priority queue.
	 * 
	 * @return the size of the priority queue.
	 */
	public int size() {
		return myQueue.size();
	}
}
