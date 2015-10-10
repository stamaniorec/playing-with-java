import java.util.Scanner;
import java.io.File;

public class ReadFile {
	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(new File("info.txt"));
		} catch(Exception e) {
			System.out.println("Could not load file.");
			e.printStackTrace();
		}
		
		String name;
		int age;
		String random; 

		while(input.hasNext()) {
			name = input.next();
			age = input.nextInt();
			random = input.next();

			System.out.printf("%s; %d; %s\n", name, age, random);	
			// System.out.printf("%s; %d\n", name, age);
		}

		input.close();
	}
}
