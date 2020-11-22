/*
 * Authors: Cassie Noble
 * Date: 7/18/18
 * Overview: Implement three sorting algorithms within linked lists: Insertion Sort, Merge Sort, and Quick Sort.
 * Run an experiment to compare the running time of the three algorithms on various input sizes.
 */

public class Node {
	private int data;		// data for node	
	private Node next;		// next node to point to
	
	// constructor for Node
	Node(int element){
		data = element;
	}
	
	// method to get data
	public int getData() {
		return data;
	}
	
	// method to get next node
	public Node getNextNode() {
		return next;
	}
	
	// method to set data
	public void setData(int d) {
		data = d;
	}
	
	// method to set next node
	public void setNextNode(Node n) {
		next = n;
	}
	
	// method to print node
	public String toString() {
		return Integer.toString(data);
	}

}
