import java.util.Scanner;
import java.util.Stack;


public class EasterMister {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		
		int height = 2 * n;
		// int width = 3 * n - 1; // was given in the problem description
		int widthOfDrawingArea = 3 * n + 1;
		int sizeOfTopAndBottom = n - 1;
		
		int dotNum = n + 1;
		Stack<Integer> dots = new Stack<Integer>(); 
		
		for(int i = 0; i < height; ++i) {
			if(i >= height / 2) {
				dotNum = dots.pop();
			}
			
			for(int j = 0; j < dotNum; ++j) {
				System.out.print(".");
			}
			if(i == 0 || i == height - 1) {
				for(int j = 0; j < sizeOfTopAndBottom; ++j) {
					System.out.print("*");
				}
			} else if(i == height / 2 - 1 || i == height / 2) {
				System.out.print("*");
				for(int j = 0; j < widthOfDrawingArea - 2*dotNum - 2; ++j) {
					if(i == height / 2 - 1) {
						if(j % 2 == 0) {
							System.out.print(".");
						} else {
							System.out.print("#");
						}
					} else if(i == height / 2) {
						if(j % 2 == 1) {
							System.out.print(".");
						} else {
							System.out.print("#");
						}
					}
					
				}
				System.out.print("*");
				
			} else {
				System.out.print("*");
				for(int j = 0; j < widthOfDrawingArea - 2*dotNum - 2; ++j) {
					System.out.print(".");
				}
				System.out.print("*");
			}
			for(int j = 0; j < dotNum; ++j) {
				System.out.print(".");
			}
			if(i < height / 2) {
				dots.push(dotNum);
				dotNum -= 2;
				if(dotNum < 1) {
					dotNum = 1;
				}
			}
			
			System.out.println();
			
		}
		
		input.close();
	}
	
}
