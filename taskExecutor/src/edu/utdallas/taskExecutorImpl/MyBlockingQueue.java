package edu.utdallas.taskExecutorImpl;
import java.util.concurrent.locks.*;


public class MyBlockingQueue<T> {
	final Lock lock=new ReentrantLock();  
	final Condition notEmpty=lock.newCondition();
	final Condition notFull=lock.newCondition();
	private int capacity;
	private int count=0;
	private java.util.Queue<T> myQueue=new java.util.LinkedList<>();
	
	public MyBlockingQueue(){
		capacity=100;
	}
	
	public MyBlockingQueue(int max){
		capacity=max;		
	}
	
	public void put(T t) {
		if(t==null)
			throw new NullPointerException();
		lock.lock();
		try{
			while(count==capacity){
				notFull.await();			//write
			}
			myQueue.add(t);
			++count;
			notEmpty.signal();
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}
	
	public T take(){
		lock.lock();
		T temp=null;
		try{
			while(count==0){
				notEmpty.await();		//read
			}
			temp=myQueue.poll();
			--count;
			notFull.signal();			
		}
		catch(InterruptedException ie){
			ie.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		return temp;
	}
	
	public boolean isFull(){
		return count==capacity;
	}
	
	public boolean isEmpty(){
		return count==0;
	}
}
