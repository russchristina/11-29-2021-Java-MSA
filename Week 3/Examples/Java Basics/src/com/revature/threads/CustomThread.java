package com.revature.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Thus far, we have worked on only one thread: a thread called "main". Recall that 
 * statements are executed in the order in which they are specified. That said, this is
 * true when we're discussing the execution of a single thread.
 * 
 * When you introduce additional threads (other lines of execution), however, your output
 * becomes less predictable.
 * 
 * So how do you create additional threads? You can:
 * 
 * 1) Extend the Thread Class
 * 2) Implement the Runnable interface (which the Thread class just does anyway)
 * 3) Concurrency API (Best Way)
 * 
 * Threads can exist in several different "states". These states are as follows:
 * 
 * 1) NEW: A thread that has been created but that has not been started.
 * 2) RUNNABLE: A thread that has actually been started or that is waiting on task.
 * 3) BLOCKED: A thread is waiting to access a synchronized/locked resource.
 * 4) WAITING: A thread is waiting for another thread to complete a task for no specific amount of time
 * 5) TIMED_WAITING: A thread waits for another thread to complete a task for
 * 		a specified amount of time
 * 6) TERMINATED: A thread has completed its execution
 * 
 * FUN FACT: The Garbage Collector is a thread that runs alongside main and any user-defined
 * threads. The Garbage Collector is a "low-priority" thread. This is also referred to as
 * a "daemon" thread. A daemon thread will not prevent the JVM exiting. */

public class CustomThread extends Thread {

	public static void main(String[] args) {
		// Creating an instance of our thread class.
		CustomThread thread2 = new CustomThread();
		RunnableThread runnable = new RunnableThread();
		thread2.start();	
		
		/*
		 * This is actually the recommended to create threads. This happens to create
		 * a thread pool of reusable threads that will be managed by Java.
		 * 
		 * NOTE: You don't actually to have know this, but it is useful AND it's a great
		 * way to practice your lambda expressions.
		 */
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		service.execute(
				() -> System.out.println("The Concurrency API is the best. Use it")
				);
		service.shutdown();
		
		/*
		 * Do NOT just call "run" thinking that you are starting your Runnable thread.
		 * You must pass a runnable to a thread constructor and then call start on that
		 * thread object.
		 */
		
		Thread thread3 = new Thread(runnable);
		thread3.start();
		
		/*
		 * You can use the "join" method to tell the currently executing thread to wait
		 * for the thread object on which "join" is called to finish executing.
//		 */
//		try {
//			thread2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/*
		 * You can make a thread sleep for a duration of time. This just means that the
		 * thread ceases execution for this amount of time.
		 */
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		for (int i = 0; i < 100; i++) {
			System.out.println("Printing from the main thread.");
		}

	}

	/*
	 * You must override the "run" method if you wish to assign tasks to your
	 * thread. This method determines which logic your thread should carry out.
	 * 
	 * Note that both threads are running at the same time (concurrently). That
	 * said, only one statement is executed at a time, though we can't predict which
	 * statement from which thread will be executed first.
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("Printing from my custom thread.");
		}
	}
}

/*
 * Our second way of creating a thread is to implement the Runnable interface. Note that this
 * way is preferred as Java does not support multiple inheritance; in essence, if you
 * extend the Thread class, you lose the ability to extend other classes.
 */
class RunnableThread implements Runnable{

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.println("Printing from my runnable thread.");
		}
		
	}
	
}
