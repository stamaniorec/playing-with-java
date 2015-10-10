import java.util.Scanner;


public class FirTree {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		
		int dotsNum = n - 2;
		int width = 2*dotsNum + 1;
		
		for(int i = 0; i < n - 1; ++i) {
			for(int j = 0; j < dotsNum; ++j) {
				System.out.print(".");
			}
			for(int j = 0; j < width - 2*dotsNum; ++j) {
				System.out.print("*");
			}
			for(int j = 0; j < dotsNum; ++j) {
				System.out.print(".");
			}
			dotsNum--;
			System.out.println();
		}
		
		dotsNum = n - 2;
		
		for(int j = 0; j < dotsNum; ++j) {
			System.out.print(".");
		}
		System.out.print("*");
		for(int j = 0; j < dotsNum; ++j) {
			System.out.print(".");
		}
				
		input.close();
		
		System.out.println(System.currentTimeMillis() - startTime);
		
	}
	
	
	
}
