import java.util.Scanner;


public class Logo {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int X = input.nextInt();
				
		int width = 2*X - 1 + 2*(X/2);
		int dotsAtEnds = X / 2;
		int dotsInBetween = width - 2 - 2*dotsAtEnds;
		
		// Print first line
		for(int i = 0; i < dotsAtEnds; ++i) {
			System.out.print(".");
		}
		System.out.print("*");
		
		for(int i = 0; i < dotsInBetween; ++i) {
			System.out.print(".");
		}
		System.out.print("*");
		for(int i = 0; i < dotsAtEnds; ++i) {
			System.out.print(".");
		}
		
		System.out.println();
		
		// Print the upper thingies
		
		dotsAtEnds--;
		int asterixOneLeft = dotsAtEnds;
		int asterixTwoLeft = dotsAtEnds + 2;
		int asterixOneRight = width - dotsAtEnds - 2;
		int asterixTwoRight = width - dotsAtEnds;
		
		for(int i = 0; i < X - 2; ++i) {			
			for(int j = 0; j < asterixOneLeft; ++j) {
				System.out.print(".");
			}
			if(asterixOneLeft >= 0)
				System.out.print("*");
			if(asterixOneLeft < -1)
				asterixOneLeft = -1;
			for(int j = 0; j < asterixTwoLeft - asterixOneLeft - 1; ++j) {
				System.out.print(".");
			}
			System.out.print("*");
			
			for(int j = 0; j < asterixOneRight - asterixTwoLeft - 2; ++j) {
				System.out.print(".");
			}
			System.out.print("*");
			if(asterixTwoRight > width + 1)
				asterixTwoRight = width + 1;
			for(int j = 0; j < asterixTwoRight - asterixOneRight - 1; ++j) {
				System.out.print(".");
			}
			if(asterixTwoRight <= width)
				System.out.print("*");
			
			for(int j = 0; j < dotsAtEnds; ++j) {
				System.out.print(".");
			}
			
			asterixOneLeft--;
			asterixTwoLeft++;
			asterixOneRight--;
			asterixTwoRight++;
			dotsAtEnds--;
			
			System.out.println();
		}
		
		for(int i = 0; i < asterixTwoLeft; ++i) {
			System.out.print(".");
		}
		System.out.print("*");
		for(int i = 0; i < asterixTwoLeft; ++i) {
			System.out.print(".");
		}
		
		System.out.println();
		
		// Print the rhombus thing
		
		int asterixLeft = asterixTwoLeft - 1;
		int asterixRight = asterixTwoLeft;
		
		for(int i = 0; i < 2*X - 3; ++i) {
			for(int j = 0; j < asterixLeft; ++j) {
				System.out.print(".");
			}
			System.out.print("*");
			for(int j = 0; j < asterixRight - asterixLeft; ++j) {
				System.out.print(".");
			}
			System.out.print("*");
			for(int j = 0; j < width - asterixRight - 2; ++j) {
				System.out.print(".");
			}
			
			if(i < (2*X - 3) / 2) {
				asterixLeft--;
				asterixRight++;
			} else {
				asterixLeft++;
				asterixRight--;
			}
			
			System.out.println();
			
			if(asterixLeft < X / 2) {
				asterixLeft = X / 2;
			}
			if(asterixRight > width - X / 2 - 2) {
				asterixRight = width - X / 2 - 2;
			}
		}
		
		for(int i = 0; i < asterixTwoLeft; ++i) {
			System.out.print(".");
		}
		System.out.print("*");
		for(int i = 0; i < asterixTwoLeft; ++i) {
			System.out.print(".");
		}
		
		input.close();

	}
	
}
