import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

public class Client {

	private Socket socket;
	private BufferedReader reader;
	private BufferedReader keyboardInput;
	private PrintWriter writer;

	public Client() {
		try {
			socket = new Socket("127.0.0.1", 5000);

			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);

			keyboardInput = new BufferedReader(new InputStreamReader(System.in));

			Thread input = new Thread(new InputFromSocket());
			Thread output = new Thread(new OutputToSocket());
			input.start();
			output.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private class OutputToSocket implements Runnable {
		public void run() {
			while(true) {
				String line;
				try {
					while((line = keyboardInput.readLine()) != null) {
						writer.println(line);
						// System.out.println("entered: " + line);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class InputFromSocket implements Runnable {
		public void run() {
			while(true) {
				String line;
				try {
					while((line = reader.readLine()) != null) {
						System.out.println(line);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new Client();
	}

}
