import java.util.HashSet;
import java.util.Set;


public class TwoNumsInArrayAddUpToN {

	int[] arr;
	int arrValueCap;
	
	public TwoNumsInArrayAddUpToN(int arrSize, int arrValueCap) {
		arr = new int[arrSize];
		this.arrValueCap = arrValueCap;
		fillArray(arr);
	}
	
	public void fillArray(int[] arr) {
		for(int i = 0; i < arr.length; ++i) {
			arr[i] = (int)(Math.random() * arrValueCap);
		}
	}
	
	public void solution(int n) {
		boolean found = false;
		for(int i = 0; i < arr.length; ++i) {
			for(int j = i + 1; j < arr.length; ++j) {
				if(arr[i] + arr[j] == n) {
					System.out.println(arr[i] + "," + arr[j]);
					found = true;
				}
			}
		}
		if(!found) {
			System.out.println("No two numbers in this array add up to " + n);
		}
	}
	
	public void betterSolution(int n) {
		Set<Integer> set = new HashSet<Integer>();
		boolean found = false;
		for(int i = 0; i < arr.length; ++i) {
			if(set.contains(arr[i])) {
				System.out.println((n - arr[i]) + "," + arr[i]);
				found = true;
			} else {
				set.add(n-arr[i]);
			}
		}
		if(!found) {
			System.out.println("No two numbers in this array add up to " + n);
		}
	}
	
	public void printArray() {
		for(int i = 0; i < arr.length; ++i) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		TwoNumsInArrayAddUpToN obj = new TwoNumsInArrayAddUpToN(10, 10);
		//obj.solution(10);
		obj.betterSolution(2);
		obj.printArray();
	}
	
}
