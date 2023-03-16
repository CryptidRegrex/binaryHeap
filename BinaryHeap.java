package defPac;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import defPac.UnderflowException;


	// BinaryHeap class
	//
	// CONSTRUCTION: with optional capacity (that defaults to 100)
//	               or an array containing initial items
	//
	// ******************PUBLIC OPERATIONS*********************
	// void insert( x )       --> Insert x
	// Comparable deleteMin( )--> Return and remove smallest item
	// Comparable findMin( )  --> Return smallest item
	// boolean isEmpty( )     --> Return true if empty; else false
	// void makeEmpty( )      --> Remove all items
	// ******************ERRORS********************************
	// Throws UnderflowException as appropriate

	/**
	 * Implements a binary heap.
	 * Note that all "matching" is based on the compareTo method.
	 * @author Mark Allen Weiss
	 */
//BinaryHeap class
//
//CONSTRUCTION: with optional capacity (that defaults to 100)
//            or an array containing initial items
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//Comparable deleteMin( )--> Return and remove smallest item
//Comparable findMin( )  --> Return smallest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//******************ERRORS********************************
//Throws UnderflowException as appropriate



/**
* Implements a binary heap.
* Note that all "matching" is based on the compareTo method.
* @author Mark Allen Weiss
*/
public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
 	private static final int DEFAULT_CAPACITY = 10;

 	private int currentSize;      // Number of elements in heap
 	private AnyType [] array; // The heap array
 	private AnyType [] arrayTwo;
 	int index;
 	public static Integer[] n1;
	
	int d = 3;
 /**
  * Construct the binary heap.
  */
	public BinaryHeap( )
	{
		this( DEFAULT_CAPACITY );
 	}

 /**
  * Construct the binary heap.
  * @param capacity the capacity of the binary heap.
  */
	public BinaryHeap( int capacity )
	{
		currentSize = 0;
     	array = (AnyType[]) new Comparable[ capacity + 1 ];
 	}
 
 /**
  * Construct the binary heap given an array of items.
  */
	public BinaryHeap( AnyType [ ] items, int a )
	{
		d = a;
		currentSize = items.length;
		array = (AnyType[]) new Comparable[ ( currentSize + d ) * 11 / 10 ];
     
		int i = 1;
		for( AnyType item : items )
			array[ i++ ] = item;
		buildHeap( );
	}

 
	/**
	 * Algorithm to find values at certain places
	 * @param i integer
	 * @return our parent index
	 */
	private int parent(int i)
	{
		return ((i - 2) / d) + 1;
	}
 
	/**
	 * Algorithm to find children
	 * @param i integer
	 * @param a our constant to iterate through children from parent
	 * @return our value of child index
	 */
	private int child(int i, int a)
	{
		return d * (i - 1) + (a + 1);
	}
 
 /**
  * Insert into the priority queue, maintaining heap order.
  * Duplicates are allowed.
  * @param x the item to insert.
  */
 	public void insert( AnyType x )
 	{
 		int total = n1.length;
 		//Enlarge array with all values sorted
 		if( currentSize == array.length - 1 )
 			enlargeArray( array.length * d + 1 );
 		//Enlarge array with all values stored in n1
 		if( total == n1.length)
 		{
 			enlargeArrayN1( n1.length + 1);
 			
 	 		n1[total] = (Integer) x;
 		}

 		
 		
 		// Percolate up
 		int hole = ++currentSize;
 		//Compare multiple components using parent function
 		while (hole > 1 && x.compareTo(array[ parent(hole) ] ) < 0 )
 		{
 			array[hole] = array[parent(hole)];
 			hole = parent(hole);
 		}
 		array[ hole ] = x;
 		
 	}

 	/**
 	 * Enlarges array for N1
 	 * @param newSize size of new array
 	 */
 	private void enlargeArrayN1(int newSize)
 	{
		Integer [] old = n1;
 		n1 = new Integer[ newSize ];
 		for( int i = 0; i < old.length; i++ )
 			n1[ i ] = old[ i ];   
 	}

 	/**
 	 * Enlarges array to store new values
 	 * @param newSize size of new array
 	 */
 	private void enlargeArray( int newSize )
 	{
 		AnyType [] old = array;
 		array = (AnyType []) new Comparable[ newSize ];
 		for( int i = 0; i < old.length; i++ )
 			array[ i ] = old[ i ];        
 	}
 
 /**
  * Find the smallest item in the priority queue.
  * @return the smallest item, or throw an UnderflowException if empty.
  */
 	public AnyType findMin( )
 	{
 		if( isEmpty( ) )
 			throw new UnderflowException( );
 		return array[ 1 ];
 	}

 /**
  * Remove the smallest item from the priority queue.
  * @return the smallest item, or throw an UnderflowException if empty.
  */
 	public AnyType deleteMin( )
 	{
 		if( isEmpty( ) )
 			throw new UnderflowException( );
 		
 		AnyType minItem = findMin( );
 		array[ 1 ] = array[ currentSize-- ];
 		percolateDown( 1 );

 		return minItem;
 	}

 /**
  * Establish heap order property from an arbitrary
  * arrangement of items. Runs in linear time.
  */
 	private void buildHeap( )
 	{
 		for( int i = parent(currentSize); i > 0; i-- )
 			percolateDown( i );
 	}
 	
 	/**
 	 * Used to setup 
 	 * @param n2
 	 * @param a
 	 */
 	public void buildTwo(Integer[] n2, int a)
 	{
		this.d = a;
		currentSize = n2.length;
		arrayTwo = (AnyType[]) new Comparable[ ( currentSize + d ) * 11 / 10 ];
		
		int i = 1;
		for( Integer item : n2 )
			arrayTwo[ i++ ] = (AnyType) item;
		buildHeapTwo();
 	}
 	
 	/**
 	 * Builds second heap for new d-value
 	 */
 	public void buildHeapTwo() 
 	{

		for( int i = parent(currentSize); i > 0; i-- )
 			percolateDown( i );
	}


 /**
  * Test if the priority queue is logically empty.
  * @return true if empty, false otherwise.
  */
 	public boolean isEmpty( )
 	{
 		return currentSize == 0;
 	}

 /**
  * Make the priority queue logically empty.
  */
 	public void makeEmpty( )
 	{
 		currentSize = 0;
 	}

 	/**
 	 * Prints all values from the array
 	 */
 	public void print( )
 	{
 		for( int i = 0; i < currentSize; i++ )
 			System.out.printf("%d ",  array[i+1]);
 		System.out.println();

 	}


 /**
  * Internal method to percolate down in the heap.
  * @param hole the index at which the percolate begins.
  */
 	private void percolateDown( int hole )
 	{

 		
 		int child;
 		AnyType tmp = array[ hole ];
 		


 		//This will use findMinChild to determin min child and percolate down
 		for( ; hole * d <= currentSize; hole = findMinChild(hole) )
 		{
 			child = findMinChild(hole);
 			if( array[ child ].compareTo( tmp ) < 0 ) //Compares child to temp value from the array[hole] value
 				array[ hole ] = array[ child ];
 			else
 				break;
 		}
 		array[ hole ] = tmp;
 	}
 	
 	/**
 	 * Private method to find the minimum child in any d-heap array.
 	 * @param hole 
 	 * @return
 	 */
 	private int findMinChild(int hole)
 	{
 		//Take the child at the first index of the hold
 		int min = child(hole, 1);

 		//Search for values starting at 2
 		for (int i = 2; i <= d; i++)
 		{
 			int indexChild = child(hole, i);
 			if (array[indexChild] == null) //If our array is null or at the end break
 			{
 				break;
 			}
 			if (array[ min ].compareTo(array[indexChild]) > 0 ) //If our array finds a smaller child set min to new index
 			{
 				min = indexChild;
 			}
 		}
 		return min; //return our min value at index
 	}

     // Test program


	    public static <T> void main( String [ ] args )
	    {

	    	//Array to store old values
	    	Integer[] n2 = new Integer[1000];

	    	//To test our while function
	    	boolean test = true;
	    	
	    	//Takes input from the user for the elements
	    	System.out.printf("Enter heap elements with spaces in between each: ");
	    	Scanner input = new Scanner(System.in);
	    	String v = input.nextLine();
	    	
	    	//Splits the values out into parts
	    	String[] parts = v.split(" ");
	    	n1 = new Integer[parts.length];

	    	//Inputs values to the new array
	    	for(int n = 0; n < parts.length; n++) {
	    	   n1[n] = Integer.parseInt(parts[n]);
	    	}
	    	
	    	
	    	//Asks user for initial d-value
	    	System.out.printf("Enter d: ");
	    	int delim = input.nextInt(); 
	    	BinaryHeap<Integer> h = new BinaryHeap<Integer>( n1, delim);

	    	System.out.printf("Output: Heap (d=%d): ", delim);
	    	h.print();
	    	
	    	//This while loop will continue to ask for user input until a 4 is selected
	    	while(test)
	    	{
	    		System.out.println("Press 1) for insert, 2) for deleteMin, 3) for buildHeap with new d value,\r\n" + 
	    				"4) to quit");
	    		System.out.print("Enter Choice: ");
	    		int value = input.nextInt();
	    		
	    		if (value == 1) //If user selects 1 ask to insert a value
	    		{
	    			System.out.print("Enter element to insert: ");
	    			value = input.nextInt();
	    			h.insert(value);
	    			System.out.printf("Output: Heap (d=%d): ", delim);
	    	    	h.print();
	    		}
	    		else if (value == 2) //If user selects 2 then delete the minimum value
	    		{
	    			h.deleteMin();
	    			System.out.printf("Output: Heap (d=%d): ", delim);
	    	    	h.print();
	    		}
	    		else if (value == 3) //If user selects 3 then change the d value
	    		{
	    			System.out.print("Enter d-value: ");
	    			delim = input.nextInt();
	    			n2 = n1;
	    			BinaryHeap<Integer> g = new BinaryHeap<Integer>( n2, delim);
	    			System.out.printf("Output: Heap (d=%d): ", delim);
	    			g.print();

	    			
	    		}
	    		else if (value == 4) //Program is terminated
	    		{
	    			System.out.print("Program Terminated");
	    			break;
	    		}

	    		
	    	}

	    	
	    	

	    }
	}


