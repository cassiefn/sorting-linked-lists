/*
 * Authors: Cassie Noble
 * Date: 7/18/18
 * Overview: Implement three sorting algorithms within linked lists: Insertion Sort, Merge Sort, and Quick Sort.
 * Run an experiment to compare the running time of the three algorithms on various input sizes.
 */

import java.util.Random;


public class LinkedList {
	private int counter = 0;	// counter to keep track of list size
	private Node head;			// head of list
	private Node tail;			// end of list
	Random rand = new Random();		// use random class to generate random numbers
	
	// default constructor for empty LinkedList
	LinkedList(){
	}
	
	// special constructor for LinkedList to generate n random numbers
	LinkedList(int n) {
		
		// create list of length n of random nodes 
		for (int i = 0; i < n; i++) {
			int data = rand.nextInt();		// generate random integer
			Node current = new Node(data);	// create node with data of that random integer
			insert(current);				// use insert method to add it to the list
		}
	}
	
	// get method for counter
	public int getCounter() {
		return counter;
	}
	
	// get method for head
	public Node getHead() {
		return head;
	}
	
	// get method for tail
	public Node getTail() {
		return tail;
	}
	
	// set method for counter
	public void setCounter(int num) {
		counter = num;
	}
	
	// set method for tail
	public void setTail(Node newTail) {
		tail = newTail;
	}
	
	// method to insert elements at end of the list
	public void insert(Node current) {
		
		// first check if list is empty
		if (head == null) {
			head = current;		// if this is the first element, make it the head
			tail = head;		// tail is also head in this case
			counter++;			// increment counter
		}
		
		else {
			tail.setNextNode(current);	// set previous tail to point to new node
			tail = current;				// now tail is added node
			counter++;					// increment counter
		}
	}
	
	// method to remove elements from beginning of the list
	public void remove() {
		
		// check if list is already empty
		if (head == null) {
			System.out.println("You can't remove items from a list that's already empty!");
		}
		
		// otherwise reset head as next element in list
		else {
			head = head.getNextNode();
			System.out.println("The first element of the list has been removed.");
			counter--;	// decrement counter
		}
	}
	
	// method to print the list
	public void printList() {
		Node current = head;	// start printing from the head
		
		// check if list is empty
		if (head == null) {
			System.out.println("You must first create a list before you can print the list!");
		}
		
		// otherwise print list
		else {
			System.out.print("| ");
			
			// loop until end of list is reached
			while(current != null) {
				System.out.print(current + " | ");
				current = current.getNextNode();	// move to next node	
			}
			System.out.println();
		}
	}
	
	// method to get ith node in the list
	public Node getNode(int i) {
		Node current = head;	// start searching at head
		int count = 0;			// start count at 0
		
		// keep looping until we reach the ith node
		while(count < i) {
			current = current.getNextNode();	
			count += 1;
		}
		return current;		// return the node
	}

	// method to swap two nodes in list
	public void swapData(int first, int second) {
		int temp = getNode(second).getData();				// store the data for the second node
		getNode(second).setData(getNode(first).getData());	// set second node data to first node data
		getNode(first).setData(temp);						// set first node data to previous second node data
	}
}
