package edu.utdallas.taskExecutor.complexTest;

import edu.utdallas.taskExecutor.Task;

public class ComplexTestTask implements Task 
{
	private static long counter = 0;
	private synchronized static long incCounter()
	{
		return ++counter;
	}
	
	private String name;
	
	public ComplexTestTask(String name)
	{
		this.name = name;
	}
	
	@Override
    public void execute()
    {
		for(int idx = 0; idx < 10; idx++) {
			long val = incCounter();
			System.out.println("Thread: " + Thread.currentThread().getName() + " Task: " + name + " count: " + val);
			Thread.yield();
		}
    }
}

