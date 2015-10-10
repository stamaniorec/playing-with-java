
public class DecoratorPattern {

	public static void main(String[] args) {
		
		Pizza margarita = new Margarita();
		System.out.println(margarita.getDescription() + 
				" " + margarita.getPrice());
		
		Pizza smallMargarita = new Small(new Margarita());
		System.out.println(smallMargarita.getDescription() + 
				" " + smallMargarita.getPrice());
		
		Pizza bigMargaritaExtraCheese = 
				new WithExtraCheese(new Big(new Margarita()));
		System.out.println(bigMargaritaExtraCheese.getDescription() + 
				" " + bigMargaritaExtraCheese.getPrice());
		
	}
	
}

abstract class Pizza {
	
	protected int size;
	protected double price;
	protected String description;
	
	public int getSize() {
		return size;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
}

class Margarita extends Pizza {
	
	public Margarita() {
		size = 10;
		price = 10.00;
		description = "Margarita - a very nice pizza.";
	}
	
}

abstract class Decorator extends Pizza {
	protected Pizza pizza;
	
	Decorator(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public abstract double getPrice();
	public abstract int getSize();
	public abstract String getDescription();
}


class Small extends Decorator {
	
	public Small(Pizza pizza) {
		super(pizza);
	}
	
	public double getPrice() {
		return pizza.getPrice() - 3.00;
	}
	
	public int getSize() {
		return pizza.getSize() - 3;
	}
	
	public String getDescription() {
		return pizza.getDescription() + ", small";
	}

}

class Big extends Decorator {
	
	public Big(Pizza pizza) {
		super(pizza);
	}
	
	public double getPrice() {
		return pizza.getPrice() + 3.00;
	}
	
	public int getSize() {
		return pizza.getSize() + 3;
	}
	
	public String getDescription() {
		return pizza.getDescription() + ", big";
	}

}

class WithExtraCheese extends Decorator {
	
	public WithExtraCheese(Pizza pizza) {
		super(pizza);
	}
	
	public double getPrice() {
		return pizza.getPrice() + 1.00;
	}
	
	public int getSize() {
		return pizza.getSize();
	}
	
	public String getDescription() {
		return pizza.getDescription() + ", with extra cheese";
	}

}