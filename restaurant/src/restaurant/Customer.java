package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable {
	public int id;
	public Food foodOrder1;
	public Food foodOrder2;
	public Drink drinkOrder;
	public int totalPreparationTime;
	
	
	public static int maxCus = 10;
	
	public List<Customer> customerList = new ArrayList<>();
	
	public Customer (int id, Food foodOrder1, Food foodOrder2, Drink drinkOrder) {
		this.id = id;
		this.foodOrder1 = foodOrder1;
		this.foodOrder2 = foodOrder2;
		this.drinkOrder = drinkOrder;
	
	
	if(foodOrder1.getPreparationTimeMS() > foodOrder2.getPreparationTimeMS()) 
	{
		totalPreparationTime = foodOrder1.getPreparationTimeMS();
		
	} 
	else {
		totalPreparationTime = foodOrder2.getPreparationTimeMS();
    }
	
	}
	
	//customer methods
	
	private static final Object lock = new Object();
	private static final int cusCounter = 0;
	public void customerArriveRestaurant(Customer customer) throws InterruptedException {
		synchronized(lock) {
			while(customerList.size() >= 10) {
				if(customerList.size() < maxCus) {
					System.out.println(" The customer " + id + " is waiting. ");
				} else {
					System.out.println(" The customer " + id + " entered to the restaurant. ");
					customerList.add(customer);
					lock.notify();
				}
			Thread.sleep(1000);
			}
		}
		
	}
	
	public synchronized void customerEnterRestaurant(Customer customer) {
		System.out.println(" The customer " + id + " entered restaurant ");
	}
	
	public synchronized void customerGiveOrder(Customer customer) {
		System.out.println(" The customer " + id + " ordered " + foodOrder1 + "," + foodOrder2 + " and " + drinkOrder);
	}
	
	public synchronized void customerReceiveOrder(Customer customer) {
		System.out.println(" The customer " + id + " received order " + foodOrder1 + "," + foodOrder2 + " and " + drinkOrder);
	}
	
	public synchronized void customerLeaveRestaurant(Customer customer) throws InterruptedException {
		synchronized(lock) {
			System.out.println(" The customer " + id + " is leaving from the restaurant. ");
			while(customerList.size() == 0) {
				if(customerList.size() == 0) {
					lock.wait();
				} else {
					customerList.remove(customerList.size() - 1);
					lock.notify();
				}
				Thread.sleep(1000);
			}
			
		}
	
	}
	
	
	
	@Override
	public void run() {
		
		try {
			
				this.customerArriveRestaurant(this);
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		    synchronized (lock) {
		    	while (cusCounter >= maxCus) {
		    	try {
		    		lock.wait();
		    	} catch (InterruptedException e) {
		    		e.printStackTrace();
		    	}
		    	}
		    	cusCounter++;
		    	
		    	this.customerEnterRestaurant(this);
		    	
		    	synchronized (Order.waitOrder);
		    	this.customerGiveOrder(this);
		    	Order.waitOrder.add(this);
		    	Order.waitOrder.notifyAll();
		    }
		    synchronized(this) {
		    	try {
		    		this.wait();
		    		this.customerReceiveOrder(this);
		    		Thread.sleep(1000);
		    	} catch (InterruptedException e) {
		    		e.printStackTrace();
		    	}
		    		
		    	}
		    try {
		    	this.customerLeaveRestaurant(this);
		    } catch (InterruptedException e) {
	    		e.printStackTrace();
	    	}
	    		synchronized(lock) {
	    			cusCounter--;
	    			lock.notify();
	    		}
	    		}
	}
	    	
	

