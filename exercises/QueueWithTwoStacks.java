import java.util.Iterator;
import java.util.Stack;


public class QueueWithTwoStacks {

	private Stack<Integer> inbox;
	private Stack<Integer> outbox;
	
	public QueueWithTwoStacks() {
		inbox = new Stack<Integer>();
		outbox = new Stack<Integer>();
		
		enqueue(1);
		enqueue(2);
		enqueue(3);
		printQueue();
		dequeue();
		printQueue();
		enqueue(4);
		enqueue(5);
		printQueue();
	}
	
	private void enqueue(int value) {
		inbox.push(value);
	}
	
	private int dequeue() {
		if(outbox.isEmpty()) {
			while(!inbox.isEmpty()) {
				outbox.push(inbox.pop());
			}
		} 
		if(!outbox.isEmpty())
			return outbox.pop();
		else {
			System.out.println("Queue is empty!");
			return -1;
		}
	}
	
	private void printQueue() {
		Iterator<Integer> itr = outbox.iterator();
		while(itr.hasNext()) {
			int cur = itr.next();
			System.out.print(cur + " ");
		}
		//System.out.print("inbox");
		itr = inbox.iterator();
		while(itr.hasNext()) {
			int cur = itr.next();
			System.out.print(cur +" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		new QueueWithTwoStacks();
	}
	
}
