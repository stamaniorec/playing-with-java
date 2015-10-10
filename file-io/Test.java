import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Test implements Serializable {

	int data1;
	int data2;

	public int getData1() {
		return data1;
	}

	public int getData2() {
		return data2;
	}

	public Test() {
		data1 = 5;
		data2 = 10;
	}

	public static void main(String[] args) {
		Test t = new Test();

		try {
			ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream("Test.ser"));
			os.writeObject(t);
			os.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
