package restaurant;

import java.util.Random;

public class Drink extends Order {
	
	
	public Drink(String name, int preparationTimeMS) {
		super(name, preparationTimeMS);
		
	}
	
	public Drink() {
		super();
	}

		// for a random drink
		public Drink getARandomDrink() {
			Random random = new Random();
			int i = random.nextInt(4);
			
			Drink drink = null;
			switch (i) {
			case 0:
				drink = new Drink("coke", 500);
				break;
			case 1:
				drink = new Drink("juice", 350);
				break;
			case 2:
				drink = new Drink("coffee", 250);
				break;
			case 3:
				drink = new Drink("water", 200);
				break;
				
			}
			return drink;
		}
	
	
	

}
