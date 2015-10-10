import java.util.Scanner;

public class MyThreads {

	private class ThreadOne implements Runnable {
		private Scanner input;
		public void run() {
			while(true) {
				System.out.println("Waiting for input");
				input = new Scanner(System.in);
				String inputStr;
				while((inputStr = input.nextLine()) != null) {
					System.out.println("You said: " + inputStr);
				}
			}
		}
	}

	private class ThreadTwo implements Runnable {
		public void run() {
			while(true) {
				System.out.println("SPAM!");
				try {
					Thread.sleep(3000);
				} catch(Exception e) {}
			}
		}
	}

	public MyThreads() {
		try {
			Thread t1 = new Thread(new ThreadOne());
			Thread t2 = new Thread(new ThreadTwo());
			t1.start();	
			t2.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MyThreads();
	}

}
