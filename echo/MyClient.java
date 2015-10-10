import java.net.*;
import java.io.*;
import java.util.Scanner;

public class MyClient {

	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private static final int portNum;

	static {
		portNum = 5000;
	}

	public MyClient() {
		try {
			socket = new Socket("127.0.0.1", portNum);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			Scanner scanner = new Scanner(System.in);
			String input;	
			while(true) {
				input = scanner.nextLine();
				if(input.equals("-1")) {
					break;
				}
				writer.println(input);
				System.out.println(reader.readLine());
			}

			reader.close();
			writer.close();
			scanner.close();
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyClient client = new MyClient();
	}

}
