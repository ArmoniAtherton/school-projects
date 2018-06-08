/*
 * MyStack.java
 * 
 * TCSS 342 - Spring 2018
 * Armoni Atherton
 * Instructor: Paulo Barreto
 * Assignment-1
 * 
 */

/**
 * This class will make the data structure that will
 * simulate a generic stack allowing for the user to create a 
 * stack based off this linked list.
 * 
 * @author Armoni
 *@version March 26, 2018 
 */
public class MyStack <Type>{
	
	/**
	 * This will create a inner class that will 
	 * create a node allowing for the linked list to link
	 * to one anther.
	 * 
	 * @author Armoni Atherton
	 */
	private class Node {
		/** This will hold the data within the certain node. */
		private Type myData;
		
		/** This will hold the link to the next node. */
		private Node  myNext;
		
		/**
		 * This will  construct a node allowing for 
		 * storing of information within this current 
		 * node. 
		 * 
		 * @param theData the generic data coming in.
		 * @param theNext the linke to the next node.
		 */
		public Node(Type theData, Node theNext) {
			myData = theData;
			myNext = theNext;
		}
	}
	
	/** This will store the head of the stack. **/
	private  Node myHead;
	
	/**
	 * This will initialize the stack and set it initially 
	 * to be set to null.
	 */
	public MyStack() {
		myHead = null;
	}
	
	/**
	 * This will check if the stack if empty or not.
	 * 
	 * @return theBoolean if the stack is empty.
	 */
	public boolean isEmpty() {
		return (myHead == null);
		
	}
	
	/**
	 * This will push a item onto the stack pushing the next
	 * element back.
	 * 
	 * @param item the current item to push.
	 */
	public void push(Type item) {
		Node temp = new Node(item, myHead);
		myHead = temp;
	}
	
	/**
	 * This will pop off the first element in the stack allowing
	 * you to view information.
	 * 
	 * @return the popped off stack item.
	 */
	public Type pop() {
		Node temp = myHead;
		myHead = myHead.myNext;
		return temp.myData;
		
	}
	
	/**
	 * This will allow you to look at the data of the last 
	 * item that was added to the stack without having to remove 
	 * the node link.
	 * 
	 * @return will return the current data of the top item.
	 */
	public Type peek() {
		return myHead.myData;
		
	}
	
	/**
	 * This will get the current size of the stack.
	 * 
	 * @return the size of the stack.
	 */
	public int size() {
		int cnt = 0;
		Node currentNode = myHead;
		while (currentNode != null) {
			cnt++;
			currentNode = currentNode.myNext;
		}
		return cnt;
		
	}
	
	/**
	 * This will return a visual representation of the stack
	 * allowing the user to see the current data within the 
	 * stack.
	 */
	public String toString() {
		StringBuilder builtString = new StringBuilder();
		builtString.append("[");
		Node currentNode = myHead;
		
		while (currentNode != null) {
			builtString.append(currentNode.myData.toString());
			currentNode = currentNode.myNext;
			if (currentNode != null) {
				builtString.append(", ");
			}
		}
		builtString.append("]");
		return builtString.toString();
		
	}
}