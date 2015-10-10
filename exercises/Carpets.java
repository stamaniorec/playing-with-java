import java.util.Scanner;


public class Carpets {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int carpetSize = input.nextInt();
		
		int dotsNum = (carpetSize - 2) / 2; // two slashes
		
		for(int i = 0; i < carpetSize / 2; ++i) {
			for(int j = 0; j < dotsNum; ++j) {
				System.out.print(".");
			}
			for(int j = 0; j < (carpetSize - 2 * dotsNum) / 2; ++j) {
				if(j % 2 == 0) {
					System.out.print("/");
				} else {
					System.out.print(" ");
				}
			}
			for(int j = 0; j < (carpetSize - 2 * dotsNum) / 2; ++j) {
				if(j % 2 == 0) {
					if(i % 2 == 0)
						System.out.print("\\");
					else
						System.out.print(" ");
				} else {
					if(i % 2 == 1)
						System.out.print("\\");
					else
						System.out.print(" ");
				}
			}
			for(int j = 0; j < dotsNum; ++j) {
				System.out.print(".");
			}
			if(i < carpetSize / 2 - 1) {
				dotsNum--;
			} 
			System.out.println();
		}
		
		dotsNum = 0;
		
		for(int i = 0; i < carpetSize / 2; ++i) {
			for(int j = 0; j < dotsNum; ++j) {
				System.out.print(".");
			}
			char prevChar = 0;
			for(int j = 0; j < (carpetSize - 2*dotsNum) / 2; ++j) {
				if(j % 2 == 0) {
					System.out.print("\\");
					prevChar = '\\';
				} else {
					System.out.print(" ");
					prevChar = ' ';
				}
			}
			if(prevChar == '\\')
				prevChar = '/';
			for(int j = 0; j < (carpetSize - 2*dotsNum) / 2; ++j) {
				System.out.print(prevChar);
				if(prevChar == ' ')
					prevChar = '/';
				else if(prevChar == '/')
					prevChar = ' ';
			}
			for(int j = 0; j < dotsNum; ++j) {
				System.out.print(".");
			}
			dotsNum++;
			System.out.println();
		}
		
		input.close();
			
	}
	
}
