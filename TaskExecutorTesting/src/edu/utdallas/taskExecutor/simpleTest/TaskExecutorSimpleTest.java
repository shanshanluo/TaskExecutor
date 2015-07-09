package edu.utdallas.taskExecutor.simpleTest;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.taskExecutorImpl.TaskExecutorImpl;

public class TaskExecutorSimpleTest
{
	public void runTest()
	{
		// Initialize the executor with 10 threads. 
		final TaskExecutor taskExecutor = new TaskExecutorImpl(10);
		
		// Inject 1000 tasks into the executor in a separate thread. 
		// Note use of anonymous inner-class. 
		Runnable inserter = new Runnable() {
			public void run()
			{
				for (int idx = 0; idx < 10000; idx++) {
					// Note that Threads are assigned names. 
					String name = "SimpleTask" + idx;
					Task myTask = new SimpleTestTask(name);
					taskExecutor.addTask(myTask);
					Thread.yield();
				}
			}
		};
		Thread insertThread = new Thread(inserter);
		insertThread.start();
	}

	public static void main(String args[])
	{
		TaskExecutorSimpleTest app = new TaskExecutorSimpleTest();
		app.runTest();
	}

}
