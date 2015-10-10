
public class MedianOfTwoSortedArrays {

	private int[] arr1;
	private int[] arr2;
	private int arrLength;
	
	public MedianOfTwoSortedArrays(int[] arr1, int[] arr2) {
		this.arr1 = arr1;
		this.arr2 = arr2;
		arrLength = arr1.length;
		// I assume the arrays are sorted and that they have the same length
		// as given in the problem statement
	}
	
	public double medianOfTheTwo() {
		int curIndex1 = 0;
		int curIndex2 = 0;
		
		int count = -1; // -1 so that I actually start counting nodes from 0
		int prevNum = 0;
		int addedLast = 0;
		
		boolean outOfBoundsFirst = false;
		boolean outOfBoundsSecond = false;
		
		// my "naive" approach was to build a list of the two arrays
		// then just use the definition for median and get the result
		
		// this solution "fakes" a list in a way
		// so I have no additional space complexity
		
		// Loop through the "imaginary" sorted list
		for(int i = 0; i < 2 * arrLength; ++i) {
			// If both arrays still have more elements
			if(!outOfBoundsFirst && !outOfBoundsSecond) {
				// Add to the "imaginary" list according to the
				// current values in the arrays
				if(arr1[curIndex1] >= arr2[curIndex2]) {
					addedLast = arr2[curIndex2++];
					++count;
				} else {
					addedLast = arr1[curIndex1++];
					++count;
				}
			} else if(outOfBoundsFirst) { // Else if only the second 
										// array has more elements left
				// simply add them to the "imaginary" list
				addedLast = arr2[curIndex2++];
				count++;
			} else if(outOfBoundsSecond) { // Same but with the other array
				addedLast = arr1[curIndex1++];
				count++;
			}
			
			
			// Make sure the indices don't go out of bounds
			if(curIndex1 > arrLength - 1) {
				outOfBoundsFirst = true;
			}
			if(curIndex2 > arrLength - 1) {
				outOfBoundsSecond = true;
			}
			
			// The "imaginary" list will always have an even number of elements
			// Because its length is 2 * the array lengths
			// Therefore the median is equal to the average of the middle 
			// two elements
			
			// If I'm at the first middle number
			if(count == (2*arrLength) / 2 - 1) {
				// store it in prevNum
				prevNum = addedLast;
			}
			// If I'm at the second middle number
			if(count == (2*arrLength) / 2) {
				// Print the two numbers for debug purposes
				System.out.println(addedLast + " " + prevNum);
				// And return their average
				return (double)(addedLast + prevNum)/2;
			}
		}
		return 0.00;
	}
	
	public static void main(String[] args) {
		System.out.println((new MedianOfTwoSortedArrays(new int[] { 2, 6, 10 }, 
				new int[] { 10, 11, 12 })).medianOfTheTwo());
		System.out.println();
		System.out.println((new MedianOfTwoSortedArrays(new int[] { 1, 4, 14, 15, 26 }, 
				new int[] { 2, 3, 16, 28, 42 })).medianOfTheTwo());
		System.out.println();
		System.out.println((new MedianOfTwoSortedArrays(new int[] { 1, 4, 14, 25, 26 }, 
				new int[] { 2, 3, 16, 28, 42 })).medianOfTheTwo());
		System.out.println();
		System.out.println((new MedianOfTwoSortedArrays(new int[] { 1, 2 }, 
				new int[] { 3, 4 })).medianOfTheTwo());
		System.out.println();
		System.out.println((new MedianOfTwoSortedArrays(new int[] { 1 }, 
				new int[] { 2 })).medianOfTheTwo());
	}
}
