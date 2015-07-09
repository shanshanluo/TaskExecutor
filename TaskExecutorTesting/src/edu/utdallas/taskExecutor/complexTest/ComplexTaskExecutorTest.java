package edu.utdallas.taskExecutor.complexTest;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.taskExecutorImpl.TaskExecutorImpl;

public class ComplexTaskExecutorTest
{
	public void runTest()
	{
		final TaskExecutor taskExecutor = new TaskExecutorImpl(10);
		Runnable inserter = new Runnable() {
			public void run()
			{
				for (int idx = 0; idx < 10000; idx++) {
					String name = "Task" + idx;
					Task myTask = new ComplexTestTask(name);
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
		ComplexTaskExecutorTest app = new ComplexTaskExecutorTest();
		app.runTest();
	}

}
