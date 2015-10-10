import java.util.Scanner;


public class NumOfRightTrianglesWithLegN {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int leg = input.nextInt();
		
		long start = System.nanoTime();
		long i = 2;
		while(true) {
			long hypotenuseSquared = i * i + leg * leg;
			//System.out.println(i + " " + leg + " " + Math.sqrt(hypotenuseSquared));
			if(isPerfectSquare(hypotenuseSquared)) {
				System.out.println(Math.min(leg, i) + ", " + 
						Math.max(leg, i) + ", " + (int)Math.sqrt(hypotenuseSquared));
			}
			if(i*i + leg*leg <= (i+1)*(i+1))
				break;
			
			i++;
		}
		
		input.close();
		System.out.println((double)(System.nanoTime() - start)/1000000000);
	}
	
	public static boolean isPerfectSquare(long hypSq) {
		double a = Math.sqrt(hypSq);
		int b = (int) a;
		return a == b;
	}
	
}
