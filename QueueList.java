public class QueueList {

	private Node head;
	private Node tail;

	public QueueList() {
		head = null;
		tail = null;
	}	

	public void add(int val) {
		if(head == null) {
			head = new Node(val);
			tail = head;
		} else {
			Node new_node = new Node(val);
			tail.next = new_node;
			tail = tail.next;
		}
	}

	public void remove() {
		if(head == null) {
			System.out.println("Empty queue, cannot remove element.");
		} else {
			head = head.next;
		}
	}

	public void print() {
		if(head == null) {
			System.out.println("Empty queue.");
		} else {
			Node temp = head;
			while(temp != null) {
				temp.print();
				temp = temp.next;
			}
			System.out.println();
		}
	}

	private class Node {
		int data;
		Node next;

		Node(int val) {
			data = val;
			next = null;
		}

		void print() {
			System.out.print(data + " --> ");
		}
	}

	public static void main(String[] args) {
		QueueList q = new QueueList();
		q.print();
		q.add(5);
		q.add(10);
		q.add(15);
		q.add(20);
		q.add(25);
		q.print();
		q.remove();
		q.remove();
		q.remove();
		q.print();
		q.remove();
		q.remove();
		q.print();
	}	

}
