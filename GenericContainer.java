public class GenericContainer<T> {

	private T[] arr;
	private int top;

	public GenericContainer() {
		arr = (T[]) new Object[10];
	}

	public void add(T val){
		arr[top++] = val;
	}

	public void printArray() {
		for(int i = 0; i < top; ++i) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {
		//Generic<Integer> gen = new Generic<Integer>();
		//gen.add(50);
		//gen.add(123);
		GenericContainer<String> gen = new GenericContainer<String>();
		gen.add("asdf");
		gen.add("fdasa");
		gen.printArray();
	}

}
