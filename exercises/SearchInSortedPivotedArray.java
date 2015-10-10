import java.util.Arrays;


public class SearchInSortedPivotedArray {

	private int[] arr;
	
	public SearchInSortedPivotedArray() {
		// example: 3 4 5 1 2
		arr = new int[] { 5, 6, 7, 8, 9, 10, 1, 2, 3, 4 };
		
		int value = 4;
		int pivotIndex = findPivotIndex(0, arr.length - 1);
		int index = search(pivotIndex, value);
		if(index == -1) {
			System.out.println("No such value found.");
		} else {
			System.out.println("Index of " + value + " : " + index);
		}
	}
	
	private int search(int pivotIndex, int value) {
		int[] firstPart = Arrays.copyOfRange(arr, 0, pivotIndex);
		int inFirstPart = binarySearch(firstPart, 0, firstPart.length-1, value);
		if(inFirstPart != -1) {
			return inFirstPart;
		}
		int[] secondPart = Arrays.copyOfRange(arr, pivotIndex, arr.length);
		int inSecondPart = binarySearch(secondPart, 0, secondPart.length-1, value);
		if(inSecondPart != -1) {
			return pivotIndex + inSecondPart;
		}
		
		return -1;
	}
	
	private int binarySearch(int[] arr, int start, int end, int value) {
		if(start > end) 
			return -1;
		
		int mid = (start + end) / 2;
		if(arr[mid] == value) {
			return mid;
		} else if(arr[mid] < value) {
			return binarySearch(arr, mid+1, end, value);
		} else {
			return binarySearch(arr, start, mid-1, value);
		}
	}
	
	private int findPivotIndex(int start, int end) {
		if(start > end) {
			return -1;
		}
		
		int mid = (start + end) / 2;
		if(mid > 0) {
			if(arr[mid] < arr[mid-1]) {
				return mid;
			} else {
				int ret = findPivotIndex(start, mid-1);
				if(ret != -1) {
					return ret;
				}
				ret = findPivotIndex(mid+1, end);
				if(ret != -1) {
					return ret;
				}
				
			}
		} 
		
		return -1;
	}
	
	public static void main(String[] args) {
		new SearchInSortedPivotedArray();
	}
	
}
