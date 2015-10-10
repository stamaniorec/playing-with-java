import java.util.Arrays;
import java.util.Scanner;


public class MitovEntryLevel2012 {
	
	/*
	 * Code written on 06.09.2014
	 * as an exercise
	 * 
	 * I am very grateful for the lack of tests (not)!
	 */

	public static void main(String[] args) {
		ten();
	}
	
	public static int one() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt(); 
		int y = s.nextInt(); 
		int sum = 0;
		
		for(int i = x; i <= y; ++i) {
			if(i % 2 != 0) {
				sum += i;
			}
		}
		
		s.close();
		return sum;
	}
	
	public static int two() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		int sum = 0;
		
		for(int i = x; i <= y; ++i) {
			if(i % 17 == 0) {
				sum += i;
			}
		}
		
		s.close();
		return sum;
	}
	
	public static void three() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		
		for(int i = x; i <= y; ++i) {
			boolean isPrime = true;
			for(int j = 2; j <= Math.sqrt(i); ++j) {
				if(i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime && i % 10 == 3) {
				System.out.println(i);
			}
		}
		
		s.close();
	}
	
	public static void four() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		
		if(x <= 0 && y >= 1) {
			System.out.println("0");
		}
		
		int a = 0;
		int b = 1;
		int fib;
		while(true) {
			fib = a + b;
			a = b;
			b = fib;
			
			if(fib >= x) {
				if(fib <= y)
					System.out.println(fib);
				else
					break;
			}
		}
		
		s.close();
	}
	
	public static void five() {
		Scanner s = new Scanner(System.in);
		@SuppressWarnings("unused")
		int x = s.nextInt(); // for whatever reason, the problem statement wants me to enter a number
		double[] arr = new double[10];
		for(int i = 0; i < arr.length; ++i) {
			arr[i] = Math.cos(Math.toRadians(i));
		}
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		Arrays.sort(arr);
		for(int i = 0; i < arr.length / 2; ++i) {
			double tmp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = tmp;
		}
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		s.close();
	}
	
	public static void six() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		int[] arr = new int[100];
		int k = 0;
		
		for(int i = 0; i < arr.length; ++i) {
			arr[i] = (int)(Math.random() * 100);
		}
		
		for(int i = 0; i < arr.length; ++i) {
			if(arr[i] % 10 == x) {
				int tmp = arr[i];
				arr[i] = arr[k];
				arr[k] = tmp;
				k++;
			}
		}
		
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		
		s.close();
	}
	
	public static void seven() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		double[] arr = new double[10];
		int curIndex = 0;
		int curValue = 0;
	
		while(curIndex < 10) {
			double curCos = Math.cos(curValue++);
			int thirdDigitAfterDecPoint = (int)(curCos * 1000) % 10;
			if(Math.abs(thirdDigitAfterDecPoint) == x) {
				arr[curIndex++] = curCos;
			}
		}
		
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void eight() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		int[] arr = new int[10];
	
		int a = 0;
		int b = 1;
		
		int curIndex = 0;
		
		while(curIndex < 10) {
			int fib = a + b;
			a = b;
			b = fib;
			
			if(fib % x == 0) {
				arr[curIndex++] = fib;
			}
		}
		
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void nine() {
		Scanner s = new Scanner(System.in);
		double x = s.nextDouble();
		
		int denominator = 1;
		boolean sign = true;
		double result = 0;
		
		while(4.0/denominator >= x) {
			if(sign) {
				result += 4.0/denominator;
			} else {
				result -= 4.0/denominator; 
			}
			denominator += 2;
			sign = !sign;
		}
		
		// that isn't pi, is it...
		System.out.println("Pi = " + result);
		
		s.close();
	}
	
	public static void ten() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		int y = s.nextInt();
		int w = s.nextInt();
		int h = s.nextInt();
		
		int count = 0;
		int[] coordinates = new int[10];
		int curIndex = 0;
		for(int i = 0; i <= x - w; i += w) {
			for(int j = 0; j <= y - h; j += h) {
				count++;
			}
			coordinates[curIndex++] = i;
		}
		
		// Not sure if the 0 should be included in the array
		// Maybe it shouldn't?
		// Thank all gods for the lack of examples and test cases. :)
		System.out.println("Number of small plates: " + count);
		if(curIndex < 10) {
			System.out.println("The wooden plate is not big enough to be cut into 10 pieces horizontally");
		}
		for(int i : coordinates) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void eleven() {
		// Since no tests have been provided for me 
		// I assume this program works correctly
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
	
		double[] arr = new double[10];
		
		for(int N = 0; N < 10; ++N) {
			double sumOfCosZ = 0;
			for(int z = N*x; z < (N+1)*x; ++z) {
				sumOfCosZ += Math.cos(z);
			}
			arr[N] = sumOfCosZ;
		}
		
		for(int i = 0; i < 10; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void twelve() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		int[] arr = new int[10];
		
		int N = 0;
		int curIndex = 0;
		while(curIndex < 10) {
			N++;
			if(N % x == 0) {
				int sum = 0;
				for(int i = 1; i <= N; ++i) {
					sum += i;
				}
				arr[curIndex++] = sum;
			}
		}
		
		for(int i = 0; i < 10; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void thirteen() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		int[] arr = new int[10];
		
		for(int N = 0; N < 10; ++N) {
			int sum = 0;
			for(int k = 0; k < 1000; ++k) {
				if((k % ((N+1)*x)) == 0) { // f-ing order of operators...
					sum += k;
				}
			}
			arr[N] = sum;
		}
		
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void fourteen() {
		Scanner s = new Scanner(System.in);
		int x;
		while(true) {
			x = s.nextInt();
			if(x <= 0 || x >= 10) {
				System.out.println("Sorry, the input must be in the range (0;10)");
			} else if(x % 2 == 0 || x == 5) {
				System.out.println("Sorry, but the idiot who wrote the problem statement");
				System.out.println("didn't figure out the fact that there are NO prime numbers");
				System.out.println("that are even apart from 2 and NO prime numbers that are ");
				System.out.println("multiples of 5!!!");
			} else {
				break;
			}
		}
		
		int[] arr = new int[10];
		
		int curIndex = 0;
		int num = 2;
		while(curIndex < 10) {
			boolean isPrime = true;
			for(int k = 2; k <= Math.sqrt(num); ++k) {
				if(num % k == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime && num % 10 == x) {
				arr[curIndex++] = num;
			}
			num++;
		}
		
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void fifteen() {
		Scanner s = new Scanner(System.in);
		int x;
		while(true) {
			x = s.nextInt();
			if(x > 0 && x < 10)
				break;
			System.out.println("Error! Input must be in the range (0;10)");
		}
		
		long[] arr = new long[10];
		
		long a = 0;
		long b = 1;
		int curIndex = 0;
		if(x == 0) {
			arr[curIndex++] = 0;
		} else if(x == 1) {
			arr[curIndex++] = 1;
		}
		
		while(curIndex < 10) {
			long fib = a + b;
			a = b;
			b = fib;
			
			if(fib % 10 == x) {
				arr[curIndex++] = fib;
			}
		}
		
		for(long i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		s.close();
	}
	
	public static void sixteen() {
		Scanner s = new Scanner(System.in);
		int x = s.nextInt();
		
		int[] arr = new int[10];
		int curIndex = 0;
		
		arr[curIndex++] = 1;
		for(int i = 2; i <= x; ++i) {
			if(x % i == 0 && i % 2 != 0) {
				arr[curIndex++] = i;
				if(curIndex > 9)
					break;
			}
		}
		
		if(curIndex < 10) {
			System.out.println("There are no 10 odd divisors.");
		} else {
			for(int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
			
			System.out.println("The prime numbers in this list are: ");
			for(int i : arr) {
				boolean isPrime = true;
				for(int j = 2; j <= Math.sqrt(i); ++j) {
					if(i % j == 0) {
						isPrime = false;
						break;
					}
				}
				if(isPrime) {
					System.out.print(i + " ");
				}
			}
		}
		
		s.close();
	}
	
	public static void seventeen() {
		Scanner s = new Scanner(System.in);
		double x = s.nextDouble();
		
		double sum = 0;
		int prevPrime = 1;
		int num = 2;
		while(true) {
			boolean isPrime = true;
			for(int i = 2; i <= Math.sqrt(num); ++i) {
				if(num % i == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				if(num == prevPrime + 2) {
					if(1.0/prevPrime + 1.0/num < x)
						break;
					
					sum += (1.0/prevPrime + 1.0/num);
				}
				prevPrime = num;
			}
			
			++num;
		}
		
		System.out.println("sum = " + sum);
		
		s.close();
	}
	
}
