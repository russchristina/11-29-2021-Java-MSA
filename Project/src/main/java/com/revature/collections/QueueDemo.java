package com.revature.collections;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueDemo {

    /*
                            Iterable (I)
                                |
                           Collection (I)
                                |
                               /     \              \
                         List (I)   Set (I)         Queue (I)

     */
    public static void main(String[] args) {
        /*
        Queues are slightly different from List and Set in the following ways:
        - A Queue follows a FIFO (first in first out) policy. THis means that the first element that is added to the queue is the first to be removed.
        (Duplicates are okay in a queue)
        Notice that the method used for adding an element to a queue is the same method we used for adding to List and Set implementations.
         */
        Queue myQueue = new LinkedBlockingQueue<>();
        myQueue.add("Kymon");
        myQueue.add("Kristof");
        myQueue.add("Kelsy");
        myQueue.add("Kymon");

        //This is generally a better method of adding elements for many Queue implementations as opposed to .add();
        myQueue.offer("Fourth person");

        /*
        We can also remove elements. .remove(); takes no args. It removes the head of the queue (FIFO).
        there is a remove option that takes args, but the point of using queue is FIFO, so it wouldn't make sense
        to use a queue. Just use a List or Set
        Possible but not encouraged: myQueue.remove("Kymon");

        You can also use .poll() instead of .remove();. The only difference is that poll will not throw an exception
        if the queue is empty, but remove will throw a NoSuchElementException
         */
        myQueue.remove();
        myQueue.poll();
        /*

         */
        myQueue.contains("Kymon");
        /*
        You can peek at the head of the queue if you want to know which element is at the front of the queue without
        remove it
         */
        System.out.println(myQueue.peek());
    }
}
