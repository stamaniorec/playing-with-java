
public class CvsJavaTime {

	/*
	 * o to 10000
	 * 		- C -> 0.000053
	 * 		- Java -> 0.000105519
	 * 0 to 100000 
	 * 		- C -> 0.000508
	 * 		- Java -> 0.001003229
	 * 0 to 1000000
	 * 		- C -> 0.005086
	 * 		- Java -> 0.005223938
	 * 0 to 1000000000
	 * 		- C -> 1.858351
	 * 		- Java -> 0.003646084
	 */
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		
		for(int i = 0; i < 1000000000; ++i) {
			
		}
		
		System.out.println("DONE! Time: " + (double)(System.nanoTime() - startTime)/1000000000);
	}
	
}
