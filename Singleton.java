public class Singleton {

	private static Singleton singleton = new Singleton();
	private int data = 5;

	private Singleton() {}

	public static Singleton getInstance() {
		return singleton;
	}

	public void setData(int value) {
		data = value;
	}

	public void printData() {
		System.out.println(data);
	}

	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		singleton.printData();
		singleton.setData(50);
		singleton.printData();
	}

}
