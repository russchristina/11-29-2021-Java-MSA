package com.revature.collections;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueDemo {

	/*
	 * 					Iterable (I)
	 * 						|
	 * 					Collection (I)
	 * 						|
	 * 				/			\			\
	 * 			List (I)	   Set (I)     Queue (I)
	 */
	
	public static void main(String...args){
		
		/*
		 * Queue is slightly different from List and Set in the following ways:
		 * 
		 * - A Queue follows a FIFO (first in first out) policy. This means that the first element
		 * 		that is added to the queue is the first to be removed. This implies that the order
		 * 		is maintained.
		 * - A Queue does not support access to its elements by index.
		 * 
		 */
		
			Queue myQueue = new LinkedBlockingQueue();
			
			/*
			 * Notice that the method used for adding an element to a queue is the same method we
			 * used for adding to List and Set implementations.
			 */
			myQueue.add("Christina");
			myQueue.add("Anthony");
			myQueue.add("Brandon");
			myQueue.add("Christina");
			
			/*
			 * This is generally a better method of adding elements for many Queue implementations.
			 */
			myQueue.offer("fifth person");
			
			/*
			 * Of course, we can also remove elements from our Queue. Notice that "remove" doesn't
			 * require that you pass an index as an argument as it assumed that you want to remove
			 * from the head of the Queue.
			 * 
			 * Also note that you can "peek" at the head of the queue if wish to see which element
			 * is at the front of the queue without removing it.
			 */
			
			System.out.println(myQueue.peek());
			myQueue.remove();
			/*
			 * We can remove from any point in a Queue, but it is discouraged unless you really need to
			 * do so.
			 */
//			myQueue.remove("Brandon");
			
			/*
			 * "poll" will also remove the head of the Queue, BUT it will not throw an exception if
			 * the Queue is empty - unlike the "remove" method.
			 */
			myQueue.poll();
			
			System.out.println(myQueue);
			
		}
}
