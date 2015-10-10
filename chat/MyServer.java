import java.net.*;
import java.io.*;

public class MyServer {

	private ServerSocket serverSocket;
	private final int portNum;
	private Socket clientSocket1;
	private Socket clientSocket2;
	private BufferedReader reader1;
	private BufferedReader reader2;
	private PrintWriter writer1;
	private PrintWriter writer2;

	public MyServer() {
		portNum = 5000;
		try {
			serverSocket = new ServerSocket(portNum);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		setUpSocketOne();
		setUpSocketTwo();

		setUpIO();

		Thread connectionOne = new Thread(new ReadOneWriteTwo());
		Thread connectionTwo = new Thread(new ReadTwoWriteOne());
		connectionOne.start();
		connectionTwo.start();
	} 

	private void setUpSocketOne() {
		try {
			clientSocket1 = new Socket();
			System.out.println("Waiting for connection one...");
			clientSocket1 = serverSocket.accept();
			System.out.println("Connection one successfully established.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpSocketTwo() {
		try {
			clientSocket2 = new Socket();
			System.out.println("Waiting for connection two...");
			clientSocket2 = serverSocket.accept();
			System.out.println("Connection two successfully established.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void setUpIO() {
		try {
			reader1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
			reader2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

			writer1 = new PrintWriter(clientSocket1.getOutputStream(), true);
			writer2 = new PrintWriter(clientSocket2.getOutputStream(), true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private class ReadOneWriteTwo implements Runnable {
		public void run() {
			while(true) {
				String line;
				try {
					while((line = reader1.readLine()) != null) {
						writer2.println(line);
						// System.out.println("ONE said: " + line);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class ReadTwoWriteOne implements Runnable {
		public void run() {
			while(true) {
				String line;
				try {
					while((line = reader2.readLine()) != null) {
						writer1.println(line);
						// System.out.println("TWO said: " + line);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new MyServer();
	}

}
