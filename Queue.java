public class Queue {

	private int[] queue;
	private int capacity;
	private int head;
	private int tail;

	public Queue() {
		capacity = 2;
		queue = new int[capacity];
		head = tail = -1;
	}

	public void add(int val) {
		if(head == -1 && tail == -1) {
			head++;
			tail++;
			queue[head] = val;
		} else if(tail == capacity - 1) {
			int[] new_queue = new int[2*capacity];
			for(int i = 0; i < capacity; ++i) {
				new_queue[i] = queue[i];
			}
			capacity *= 2;
			queue = new_queue;
			queue[++tail] = val;
		} else {
			queue[++tail] = val;
		}
	}

	public void remove() {
		if(head == -1 && tail == -1) {
			System.out.println("Empty queue, cannot remove element.");
		} else {
			++head;
		}
	}

	public void print() {
		if((head == -1 && tail == -1) || (head == tail+1)) {
			System.out.println("Empty queue.");
			return;
		}
		for(int i = head; i <= tail; ++i) {
			System.out.print(queue[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Queue q = new Queue();
		q.print();
		q.add(5);
		q.add(3);
		q.add(9);
		q.add(10);
		q.add(11);
		q.print();
		q.remove();
		q.remove();
		q.remove();
		q.print();
		q.remove();
		q.add(15);
		q.print();
		q.remove();
		q.remove();
		q.print();
		q.add(13);
		q.print();
	}

}
