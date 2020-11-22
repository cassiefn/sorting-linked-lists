/*
 * Authors: Cassie Noble
 * Date: 7/18/18
 * Overview: Implement three sorting algorithms within linked lists: Insertion Sort, Merge Sort, and Quick Sort.
 * Run an experiment to compare the running time of the three algorithms on various input sizes.
 */

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);		// open scanner
		boolean notQuit = true;						// initialize not quit equal to false
		LinkedList myList = new LinkedList();		// initialize a linked list
		
		// initialize start and end time for run time experiment
		long startTime;	
		long endTime;
		
		// stay in loop as long as user does not quit
		while (notQuit) {
			printMenu();									// print menu options
			int menuChoice = input.nextInt();				// get user menu choice
						
			// Choice 1. Create a new list
			if (menuChoice == 1) {
				// get input for number of elements in list
				System.out.print("Enter number of elements you would like in your list > ");
				int numElements = input.nextInt();
				
				myList = new LinkedList(numElements);		// create a new list
				System.out.println("A new list has been created.");
			}
			
			// Choice 2. Print the list
			else if (menuChoice == 2) {
				myList.printList();
				
			}
			
			// Choice 3. Remove an element from the list
			else if (menuChoice == 3) {
				myList.remove();
			}
			
			// Choice 4. Insert an element to the list
			else if (menuChoice == 4) {
				// get user input for an integer to add
				System.out.print("Please choose an integer to add to the list > ");
				int num = input.nextInt();		
				Node data = new Node(num);		// create new node with data based on user input
				myList.insert(data);			// insert node in list
				System.out.println(num + " has been added to the end of the list.");
				
			}
			
			// Choice 5. Perform Insertion Sort
			else if (menuChoice == 5) {
				startTime = System.currentTimeMillis();		// start time of operation
				myList = insertionSort(myList);
				endTime = System.currentTimeMillis();		// end time of operation
				System.out.println("Sorting time: " + (endTime - startTime));
			}
			
			// Choice 6. Perform Merge Sort
			else if (menuChoice == 6) {
				startTime = System.currentTimeMillis();		// start time of operation
				myList = mergeSort(myList);
				endTime = System.currentTimeMillis();		// end time of operation
				System.out.println("Sorting time: " + (endTime - startTime));
			}
			
			// Choice 7. Perform Quick Sort
			else if (menuChoice == 7) {
				startTime = System.currentTimeMillis();		// start time of operation
				myList = quickSort(myList);
				endTime = System.currentTimeMillis();		// end time of operation
				System.out.println("Sorting time: " + (endTime - startTime));
			}
			
			// Choice 8. Quit
			else if (menuChoice == 8) {
				System.out.println();
				System.out.println("Goodbye!");
				notQuit = false;				// now while loop will not continue
			}
			
			// if choice is out of bounds display this
			else {
				System.out.println();
				System.out.println("Invalid Choice. Please enter integers between 0 and 8.");
				System.out.println();
			}
		}
		input.close();		// close scanner when while loop exits
	}
	
	
	// menu to display to user
	public static void printMenu() {
	
		System.out.println();
		System.out.println("Please choose from one of the following:");
		System.out.println("1. Create a new list.");
		System.out.println("2. Print the list.");
		System.out.println("3. Remove an element from the list.");
		System.out.println("4. Insert an element to the list.");
		System.out.println("5. Perform Insertion Sort.");
		System.out.println("6. Perform Merge Sort.");
		System.out.println("7. Perform Quick Sort.");
		System.out.println("8. Quit");
		System.out.println();
		System.out.print("Menu choice > ");

	}
	
	
	// perform insertion sort
	public static LinkedList insertionSort(LinkedList list) {
		int n = list.getCounter();			// get number of nodes in list
		
		// loop through list starting at index 1
		for (int i = 1; i < n; i++) {
			int current = list.getNode(i).getData();	// set variable to data for node at current index
			
			// keeping looping through list until j reaches the end or until the previous data is less than the current
			for (int j = i; j > 0 && list.getNode(j - 1).getData() > current; j--) {
				// swap positions if current node data is less than previous node data
				list.swapData(j, j - 1);	
			}
		}
		return list;		// return sorted list
	}
	
	
	// perform merge sort
	public static LinkedList mergeSort(LinkedList list) {
		// initialize new linked lists
		LinkedList firstHalf = new LinkedList();		
		LinkedList secondHalf = new LinkedList();		
		
		int n = list.getCounter();			// get number of nodes in list
		int middle = n/2;					// get middle of list
		Node current = list.getHead();
		int i = 0;
		
		// base case: number of nodes is 0 or 1
		if (n < 2) {
			return list;
		}
		
		while (current != null) {
			Node currentCopy = new Node(current.getData());	// copy current data to new node
			
			if (i < middle) {
				firstHalf.insert(currentCopy);					// insert the copy in new list				
			}
			else {
				secondHalf.insert(currentCopy);					// insert the copy in new list
			}
			current = current.getNextNode();
			i++;
		}
		
		/*
		// create new list for first half
		for (int i = 0; i < middle; i++) {
			Node current = list.getNode(i);					// get current node
			Node currentCopy = new Node(current.getData());	// copy current data to new node
			firstHalf.insert(currentCopy);					// insert the copy in new list
		}
		
		// create new list for second half
		for (int i = middle; i < n; i++) {			
			Node current = list.getNode(i);					// get current node				
			Node currentCopy = new Node(current.getData());	// copy current data to new node
			secondHalf.insert(currentCopy);					// insert the copy in new list
		}
		*/
		
		// recursive calls on new first and second halves of lists
		firstHalf = mergeSort(firstHalf);
		secondHalf = mergeSort(secondHalf);
		
		// return merged first and second halves
		return merge(firstHalf, secondHalf);
	}
	
	
	// merge two lists in sorted order
	public static LinkedList merge(LinkedList firstList, LinkedList secondList) {
		LinkedList sortedList = new LinkedList();		// initialize new linked list
		
		// get total number of nodes for both lists
		int firstCount = firstList.getCounter();		
		int secondCount = secondList.getCounter();
		
		// start both indices at 0
		int i = 0;
		int j = 0;
		
		Node current1 = firstList.getHead();
		Node current2 = secondList.getHead();
		
		//System.out.println("firstCount: " + firstCount);
		//System.out.println("secondCount: " + secondCount);
		
		// keep looping until we reach the end of both lists
		while (i < firstCount || j < secondCount) {
			//System.out.println("i" + i);
			//System.out.println("j" + j);
			// if we've reached the end of the first list, then add everything remaining in the second list to the sorted list
			if (i == firstCount) {
				//Node current = secondList.getNode(j);			
				Node currentCopy = new Node(current2.getData());	// copy data
				sortedList.insert(currentCopy);						// insert copied data	
				current2 = current2.getNextNode();
				j++;												// increment j
			}
			
			// if we've reached the end of the second list, then add everything remaining in the first list to the sorted list
			else if (j == secondCount) {
				//Node current = firstList.getNode(i);			
				Node currentCopy = new Node(current1.getData());	// copy data
				sortedList.insert(currentCopy);						// insert copied data
				current1 = current1.getNextNode();
				i++;												// increment i
			}
			
			// if the current index data in the 1st list is smaller than the 2nd, then add the 1st list data to the sorted list
			else if (current1.getData() < current2.getData()) {
				Node currentCopy = new Node(current1.getData());	// copy data
				sortedList.insert(currentCopy);						// insert copied data
				current1 = current1.getNextNode();
				i++;												// increment i											
			}
			
			// otherwise current index data in the 2nd list is smaller than (or equal to) the 1st, so add the 2nd list data to the sorted list
			else {
				Node currentCopy = new Node(current2.getData());	// copy data
				sortedList.insert(currentCopy);						// insert copied data	
				current2 = current2.getNextNode();
				j++;												// increment j
			}
		}
		return sortedList;
	}
	
	
	// perform quick sort
	public static LinkedList quickSort(LinkedList list) {
		int n = list.getCounter();			// get number of nodes in list
		
		// base case: number of nodes is 0 or 1
		if (n < 2) {
			return list;
		}
		
		else {
			int pivot = list.getNode(0).getData();		// pivot is first index
			Node current = list.getNode(1);				// current is second index
			
			// initialize two new lists
			LinkedList small = new LinkedList();
			LinkedList big = new LinkedList();

			// keep looping until we reach the end of the list
			while(current != null) {
				Node currentCopy = new Node(current.getData());		// copy data
				
				// if smaller than pivot add to small list
				if (current.getData() < pivot) {
					small.insert(currentCopy);
				}
				// otherwise data is greater than or equal to pivot so add to big list
				else {
					big.insert(currentCopy);
				}
				// move current to next index at end of loop
				current = current.getNextNode();
			}
			
			// recursive call on small and big lists
			small = quickSort(small);
			big = quickSort(big);
			
			// return combined small and big lists with pivot in middle
			return combine(small, pivot, big);
		}
	}
	
	
	// combine two lists with pivot
	public static LinkedList combine(LinkedList list1, int pivot, LinkedList list2) {
		Node pivotNode = new Node(pivot);		// create node for pivot
		LinkedList combinedList;				// declare new linked list
		
		// if small list is empty, combine pivot with big list
		if (list1.getCounter() == 0) {
			combinedList = new LinkedList();
			combinedList.insert(pivotNode);							// add pivot to combined list
			combinedList.getTail().setNextNode(list2.getHead());	// link combined list to big list head
			combinedList.setTail(list2.getTail());					// set tail of combined list to tail of big list
			combinedList.setCounter(list2.getCounter() + 1);		// correct counter for combined list
		}
		
		// if big list is empty, insert pivot at the end of small list
		else if (list2.getCounter() == 0) {
			list1.insert(pivotNode);		// insert pivot at end of small list
			combinedList = list1;			// combined list is now the same as small list
		}
		
		// otherwise none are empty so combine small, then pivot, then big
		else {
			list1.insert(pivotNode);									// add pivot to end of small list
			list1.getTail().setNextNode(list2.getHead());				// link small list to big list head
			list1.setTail(list2.getTail());								// set tail of small list to tail of big list
			list1.setCounter(list1.getCounter() + list2.getCounter());	// correct counter for small list
			combinedList = list1;										// combined list is now the same as small list
		}
		return combinedList;
	}
}
