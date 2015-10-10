import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class GreenBeltExam {

	public static void main(String[] args) {

		// NOTE: Yes, I'm aware this is incredibly procedural
		// but well, I was timing myself so I just went ahead
		// and did my thing, as you can see

		BufferedReader consoleInput = new BufferedReader(
			new InputStreamReader(System.in));
		
		String userInput = "";
		String chartType;
		int[] valuesForChart = new int[3];
		int curIndex = 0;
		int number;

		ArrayList<Chart> collection = new ArrayList<Chart>();

		try {
			while((userInput = consoleInput.readLine()) != null) {
			
				// input is in the format 
				// [number] for value1, [number] for value2, // no space
				// [number] for value3, [character]
				// the character is either p or b -> chart type 
				// break if only [number] is entered
	
				String[] fields = userInput.split(" ");
				curIndex = 0;		

				for(String curField : fields) {
					try {
						number = Integer.parseInt(curField);
						
						// if there is no exception thrown, 
						// then "number" is indeed a number

						valuesForChart[curIndex++] = number;	
					} catch(NumberFormatException e) {
			
					}	
				}	

				chartType = fields[fields.length-1];
			
				try {
					Integer.parseInt(chartType);

					// if only a number was entered
					break;
				} catch(NumberFormatException e) {

				} 

				if(chartType.equals("p")) {
					collection.add(new PieChart(valuesForChart[0], 
						valuesForChart[1], valuesForChart[2]));
				} else if(chartType.equals("b")) {
					collection.add(new BarChart(valuesForChart[0], 
						valuesForChart[1], valuesForChart[2]));
				}

			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		for(Chart c : collection) {
			if(c instanceof PieChart) {
				if(c.getV2() > c.getV1()) {
					((PieChart)c).firstName();
				}
			}
		}

		for(Chart c : collection) {
			if(c instanceof PieChart) {
				System.out.println((PieChart) c);
			} else if(c instanceof BarChart) {
				System.out.println((BarChart) c);
			}
		} 

	}

}

// NOTE: The classes/interfaces below should be in their separate
// files, but I kept all of them here just for convenience 

// I'm not sure what "class with common fields" is supposed to mean
// but I guess it refers to a superclass that the two chart types
// inherit from
// either way, I think this solution makes a lot of sense
abstract class Chart {

	protected int value1;
	protected int value2; 
	protected int value3;

	public Chart(int v1, int v2, int v3) {
		value1 = v1;
		value2 = v2;
		value3 = v3;
	}

	public int getV1() { return value1; }
	public int getV2() { return value2; }
	public int getV3() { return value3; }

}

interface FirstNameLastNameTaskNumber {
	void firstName();
}

class PieChart extends Chart implements FirstNameLastNameTaskNumber {
	public PieChart(int v1, int v2, int v3) {
		super(v1, v2, v3);
	}

	public void firstName() {
		value2 -= value1;
	}

	public String toString() {
		return String.format("Pie: %d %d %d", value1, value2, value3);
	}
}

class BarChart extends Chart {
	public BarChart(int v1, int v2, int v3) {
		super(v1, v2, v3);
	}

	public String toString() {
		return String.format("Bar: %d %d %d", value1, value2, value3);
	}
}
