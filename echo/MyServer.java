import java.net.*;
import java.io.*;

public class MyServer {
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private BufferedReader reader;
	private PrintWriter writer;
	private static final int serverPort;

	static {
		serverPort = 5000;
	}

	public MyServer() {
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("Waiting for somebody to connect...");
			clientSocket = serverSocket.accept();
		
			System.out.println("Connection established.");
	
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new PrintWriter(clientSocket.getOutputStream(), true);

			String line;

			while((line = reader.readLine()) != null) {	
				System.out.println("Received input: " + line);
				writer.println("You said: " + line);
			}

			reader.close();
			writer.close();
			clientSocket.close();
			serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyServer serv = new MyServer();
	}

}
