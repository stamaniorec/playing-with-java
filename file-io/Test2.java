import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Test2 {

	public static void main(String[] args) {
		try {
			ObjectInputStream is = new ObjectInputStream(
				new FileInputStream("Test.ser"));
			Test t = (Test) is.readObject();
			System.out.println(t.getData1());
			System.out.println(t.getData2());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
