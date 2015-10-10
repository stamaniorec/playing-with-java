import java.io.*;

public class MyBufferedWriter {
	public static void main(String[] args) {
		try {
			BufferedWriter writer = 
			new BufferedWriter(new FileWriter("kurappu.txt"));
			
			writer.write("hehe\n");
			writer.close();
		} catch(Exception e) {

		}
	}
}
