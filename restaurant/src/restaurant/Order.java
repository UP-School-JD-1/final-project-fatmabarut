package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Order {
	public static Object waitOrder;
	public String name;
	public int preparationTimeMS;
	
	public Order(String name, int preparationTimeMS) {
		this.name = name;
		this.preparationTimeMS = preparationTimeMS;
	}

	public List<Order> orderList = new ArrayList<>();
	
	public synchronized void waitOrder(Order order) {
		System.out.println(" The wait orders are "+ orderList);
		
	}
	public Order() {
		// TODO Auto-generated constructor stub
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPreparationTimeMS() {
		return preparationTimeMS;
	}

	public void setPreparationTimeMS(int preparationTimeMS) {
		this.preparationTimeMS = preparationTimeMS;
	}

	@Override
	public String toString() {
		return "Order [name=" + name + ", preparationTimeMS=" + preparationTimeMS + "]";
	}

	
}
