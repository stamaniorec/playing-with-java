
public class BuilderPattern {
	public static void main(String[] args) {
		Laptop cheaper = new CheaperLaptop();
		Laptop better = new BetterLaptop();
		System.out.println(cheaper);
		System.out.println(better);
		
		System.out.println("--- --- --- --- --- --- --- --- --- --- ");
		
		LaptopEnginner cheaperLaptopEnginner = 
				new LaptopEnginner(new CheaperLaptopBuilder());
		LaptopEnginner betterLaptopEngineer = 
				new LaptopEnginner(new BetterLaptopBuilder());
		
		cheaperLaptopEnginner.buildLaptop();
		betterLaptopEngineer.buildLaptop();
		Laptop cheaper2 = cheaperLaptopEnginner.getLaptop();
		Laptop better2 = betterLaptopEngineer.getLaptop();
		
		System.out.println("Cheaper laptop built. Printing: ");
		System.out.println(cheaper2);
		System.out.println("Better laptop built. Printing: ");
		System.out.println(better2);
		
	}
}

// --- LAPTOP COMPONENTS
class CPU { }
class RAM { }

// --- CPU's
class i3CPU extends CPU {}
class i5CPU extends CPU {}
class i7CPU extends CPU {}

// --- RAM's
class FourRAM extends RAM {}
class EightRAM extends RAM {}

// General Laptop class
// mark it abstract if the Builder pattern is not used
class Laptop {
	protected int price;
	protected CPU cpu;
	protected RAM ram;

	public String toString() {
		return "price: " + price + " ; cpu: " + cpu.getClass() + 
				" ; ram: " + ram.getClass();
	}
	
	public void setPrice(int price) { this.price = price; }
	public void setCPU(CPU cpu) { this.cpu = cpu; }
	public void setRAM(RAM ram) { this.ram = ram; }
}

interface LaptopBuilder {
	void buildPrice();
	void buildCPU();
	void buildRAM();
	Laptop getLaptop();
}

class CheaperLaptopBuilder implements LaptopBuilder {
	Laptop laptop;
	
	public CheaperLaptopBuilder() { laptop = new Laptop(); }
	
	public void buildPrice() { laptop.setPrice(500); }
	public void buildCPU() { laptop.setCPU(new i3CPU()); }
	public void buildRAM() { laptop.setRAM(new FourRAM()); }
	public Laptop getLaptop() { return laptop; }
}

class BetterLaptopBuilder implements LaptopBuilder {
	Laptop laptop;
	
	public BetterLaptopBuilder() { laptop = new Laptop(); }
	
	public void buildPrice() { laptop.setPrice(1000); }
	public void buildCPU() { laptop.setCPU(new i7CPU()); }
	public void buildRAM() { laptop.setRAM(new EightRAM()); }
	public Laptop getLaptop() { return laptop; }
}

class LaptopEnginner {
	private LaptopBuilder builder;
	
	public LaptopEnginner(LaptopBuilder builder) {
		this.builder = builder;
	}
	
	public Laptop getLaptop() {
		return builder.getLaptop();
	}
	
	public void buildLaptop() {
		builder.buildPrice();
		builder.buildCPU();
		builder.buildRAM();
	}
}

// Doing it the old-style way with subclasses

class CheaperLaptop extends Laptop {
	public CheaperLaptop() {
		price = 500;
		cpu = new i3CPU();
		ram = new FourRAM();
	}
}

class BetterLaptop extends Laptop {
	public BetterLaptop() {
		price = 1000;
		cpu = new i7CPU();
		ram = new EightRAM();
	}
}