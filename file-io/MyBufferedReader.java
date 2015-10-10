import java.io.*;

public class MyBufferedReader {

	public static void main(String[] args) {
		try {
			BufferedReader reader = 
				new BufferedReader(new FileReader("info.txt"));
			String line = null;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
