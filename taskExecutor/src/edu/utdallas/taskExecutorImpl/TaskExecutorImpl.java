package edu.utdallas.taskExecutorImpl;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.utdallas.taskExecutor.*;


public class TaskExecutorImpl implements TaskExecutor{
	private MyBlockingQueue<Task> mq=new MyBlockingQueue<>();
	private int maxThreads;
	final Lock lock=new ReentrantLock(); 
	public TaskExecutorImpl(){
		
	}
	public TaskExecutorImpl(int maxThreads){
		this.maxThreads=maxThreads;
		for(int i=0;i<this.maxThreads;i++){
			MyThread mt=new MyThread();
			Thread t=new Thread(mt);
			//t.setName(String.valueOf(i));
			t.start();
			Thread.yield();
		}
	}
	
	public void addTask(Task task){
		mq.put(task);
	}
	
	class MyThread implements Runnable{
		public void run(){
			System.out.println(Thread.currentThread().getName()+" is running");
			Task t;
			while(true){
				lock.lock();
				try{
					t=mq.take();
					if(t==null){
						Thread.yield();
						throw new NullPointerException("The queue is empty");
					}
					t.execute();
				}
				finally{
					lock.unlock();
				}
			}
		}
	}
}
