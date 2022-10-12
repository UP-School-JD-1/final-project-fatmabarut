package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Waiter implements Runnable {
	private static final int cusCounter = 0;
	private int totalPreparationTime;
	public String waiterName;
	public static int waiterNum = 4;
	public int orderNum;
	
	
	public List<Waiter> waiterList = new ArrayList<>();
	
	public Waiter(String waiterName) {
		this.waiterName = waiterName;
		}
	
	public synchronized void waiterReceiveOrder(Waiter waiter) {
		System.out.println(" The waiter received order with the number " + orderNum);
	}
	public synchronized void waiterGiveOrder(Waiter waiter) {
		System.out.println(" The waiter gave order with the number " + orderNum + " to the chef ");
	}
	
	public synchronized void waiterTakePreparedOrder(Waiter waiter) {
		System.out.println(" The waiter took prepared order with the number " + orderNum + " from the chef ");
	}
	public synchronized void waiterGivePreparedOrder(Waiter waiter) {
		System.out.println(" The waiter gave prepared order with the number " + orderNum + " to the customer ");
	}
	
	@Override
	public void run() {
		try {
			
			this.waiterReceiveOrder(this);
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
	    synchronized (lock) {
	    	while (cusCounter >= waiterNum) {
	    	try {
	    		lock.wait();
	    	} catch (InterruptedException e) {
	    		e.printStackTrace();
	    	}
	    	}
	    	
	    synchronized(this) {
	    	try {
	    		this.wait();
	    		this.waiterGiveOrder(this);
	    		Thread.sleep(totalPreparationTime);
	    	} catch (InterruptedException e) {
	    		e.printStackTrace();
	    	}
	    		
	    	}
	    try {
	    	this.waiterTakePreparedOrder(this);
	    } catch (InterruptedException e) {
    		e.printStackTrace();
    	}
	    try {
	    	this.waiterGivePreparedOrder(this);
	    } catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    		synchronized(lock) {
    			lock.notify();
    		}
	}
}
}
