import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class Program {

	public static void main(String[] args) {
		try {
		BufferedReader r = new BufferedReader(new FileReader("input_file.csv"));
		String curLine;
		BufferedWriter w = new BufferedWriter(new FileWriter("input_file_result.csv", true));

		while((curLine = r.readLine()) != null) {
			
			String[] fields = curLine.split(",");
			System.out.print(fields[0] + " ");
			System.out.print(fields[1] + " ");
			int random;
			while(true) {
				random = (int) (Math.random() * 100);
				if(random < Integer.parseInt(fields[1])/2) {
					break;
				} 
			}
			System.out.println(random);
			
			String writeMeToTheFile = fields[0] + "," + fields[1] + "," + random + '\n';
			w.write(writeMeToTheFile);
		}

		r.close();
		w.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
