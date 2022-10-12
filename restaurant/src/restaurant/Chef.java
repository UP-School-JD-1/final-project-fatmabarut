package restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chef implements Runnable{
	
	public String chefName;
	public int orderNum;
	public static int chefCapacity = 3;
	public int totalPreparationtime;
	
	public List<Order> orderList = new ArrayList<>();
	
	public Chef(String chefName, int orderNum) {
		this.chefName = chefName;
		this.orderNum = orderNum;
	}
	private static final Object lock = new Object();
	public void chefPrepareOrder(Order order) throws InterruptedException {
		synchronized(lock) {
			while(true) {
				if(orderList.size() > chefCapacity) {
					System.out.println(" The order with " + orderNum+ " is waiting. ");
					} else {
					System.out.println(" The order with " + orderNum+ " is started to prepare. ");
					orderList.add(order);
					lock.notify();
					}
				Thread.sleep(1000);
			}
		}
			
			
		}
	public synchronized void chefTakeOrder(Chef chef) {
		System.out.println(" The chef took the order with " + orderNum);
	}
	
	public synchronized void chefStartOrder(Chef chef) {
		System.out.println(" The chef started to prepare the order with " + orderNum);
	}
	
	public synchronized void chefFinishOrder(Chef chef) {
		System.out.println(" The chef finished to prepare the order with " + orderNum);
	}

	@Override
	public void run() {
		try {
			
				this.chefStartOrder(this);
				Thread.sleep(totalPreparationtime);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
		 }
		synchronized (lock) {
			while(orderList.size() >= chefCapacity) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			synchronized (Main.waitOrder);
			this.chefTakeOrder(this);
	    	Main.waitOrder.add(this);
	        Main.waitOrder.notifyAll();
		}
		if(customer != null) {
			synchronized (customer) {
				List<Order> order = customer.order;
				Map<String, Integer> foods = new HashMap<>();
				for (Order f: order) {
					int n = 0;
					if(foods.get(f.name) != null) {
						n = foods.get(f.name);
					}
					foods.put(f.name, n+1);
				}
				this.chefStartOrder(this);
				Thread.sleep(2000);
				this.chefFinishOrder(this);
				chef.notifyAll();
			}
		
	
          } catch (InterruptedException e) {
        	  e.printStackTrace();
          }
	}
	
		
	}
		
	}
	
	
	
	

