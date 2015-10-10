import java.util.Random;

public class ProblemDistributer {

	private int numOfStudents;
	private boolean[] problems;
	private int[] students;

	public ProblemDistributer() {
		numOfStudents = 28; // might as well make it static but whatever
		problems = new boolean[numOfStudents];
		students = new int[numOfStudents];
		for(int i = 0; i < problems.length; ++i) { 
			problems[i] = false;
		}
		for(int i : students) { 
			students[i] = -1; // not really needed but well
		}
	}

	public void doStuff() {
		Random rand = new Random();
		for(int i = 0; i < students.length; ++i) {
			while(true) {	
				int randNum = rand.nextInt(numOfStudents);
				if(problems[randNum] == false) {
					students[i] = randNum;
					problems[randNum] = true;
					break;
				}
			}
		}
	}

	public void printStuff() { 
		for(int i = 0; i < students.length; ++i){
			System.out.printf("%d : %d\n", i+1, students[i] + 1);
		}
	}

}