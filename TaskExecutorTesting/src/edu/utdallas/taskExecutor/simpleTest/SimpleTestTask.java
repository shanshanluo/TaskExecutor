package edu.utdallas.taskExecutor.simpleTest;

import edu.utdallas.taskExecutor.Task;

public class SimpleTestTask implements Task 
{
	private String name;
	
	public SimpleTestTask(String name)
	{
		this.name = name;
	}
	
	@Override
    public void execute()
    {
		// Note that the printed message includes the Thread's unique name. 
		System.out.println("Hello From Thread: " + Thread.currentThread().getName() + " Task: " + name);
    }
}

