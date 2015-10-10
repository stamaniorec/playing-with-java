
public class Test {
	
	public static void main(String[] args) {
		int number = 115;
		
		long start = System.nanoTime();
		System.out.println("Is " + number + " even? " + (number % 2 == 0));
		System.out.println((double)(System.nanoTime() - start) / 1000000000);
		
		start = System.nanoTime();
		System.out.println("Is " + number + " even? " + ((number & 1) == 0));
		System.out.println((double)(System.nanoTime() - start) / 1000000000);
	}
	
}
