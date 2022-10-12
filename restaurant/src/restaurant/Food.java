package restaurant;

import java.util.Random;

public class Food extends Order {
	
	public Food() {
		super();
	}

	public Food(String name, int preparationTimeMS) {
		super(name, preparationTimeMS);
		
	}
	
	// for a random food
	public Food getARandomFood() {
		Random random = new Random();
		int i = random.nextInt(5);
		
		Food food = null;
		switch (i) {
		case 0:
			food = new Food("burger", 500);
			break;
		case 1:
			food = new Food("pizza", 350);
			break;
		case 2:
			food = new Food("pasta", 250);
			break;
		case 3:
			food = new Food("fries", 200);
			break;
		case 4:
			food = new Food("fish", 300);
			break;
			
		}
		return food;
	}
	

}
