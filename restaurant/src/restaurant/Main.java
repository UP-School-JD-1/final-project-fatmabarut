package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static List<Customer> waitOrder;

	public static void main(String[] args) {
		
		
		// Random 
		
		Food food = new Food();
		Drink drink = new Drink();
		List<Customer> customerList = new ArrayList<>();
		List<Thread> tCustomerList = new ArrayList<>();
		
		    for(int i=1; i<=14 ; i++) {
		    	Customer customer = new Customer(i, food.getARandomFood(), food.getARandomFood(), drink.getARandomDrink());
		    	customerList.add(customer);
		    	Thread tCustomer = new Thread(customer);
		    	tCustomerList.add(tCustomer);
		    	tCustomer.start();
		    	
		    }
            
		    Thread tChef = new Chef (Chef chef);
		    tChef.start();
		    
		    Thread tWaiter = new Waiter ();
		    tWaiter.start();
	
	}
	

}
