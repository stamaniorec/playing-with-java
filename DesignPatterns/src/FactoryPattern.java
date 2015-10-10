import java.util.Scanner;


public class FactoryPattern {
	
	Scanner consoleInput = new Scanner(System.in);
	String userInput = "";
	Course course = null;
	
	public FactoryPattern() {
		System.out.println("Do you want a Java (J) course or a C++ (CPP) course?");
		userInput = consoleInput.nextLine();
		
		//noFactory();
		//staticFactory();
		abstractFactory();
	}
	
	public void noFactory() {
		if(userInput.equals("J")) {
			course = new JavaCourse();
		} else if(userInput.equals("CPP")) {
			course = new CPPCourse();
		} else {
			System.out.println("Unknown course");
		}
		
		printCourse();
	}
	
	public void staticFactory() {
		course = StaticCourseFactory.createCourse(userInput);
		printCourse();
	}
	
	public void printCourse() {
		if(course == null) {
			System.out.println("Unknown course.");
		} else {
			System.out.println(course.getClass());
		}
	}
	
	public void abstractFactory() {
		System.out.println("Do you want a Corporate (C) course or an Online (O) course?");
		String secondInput = consoleInput.nextLine();
		AbstractCourseFactory factory = null;
		
		// time for a factory for the factory, eh?
		if(secondInput.equals("C")) {
			factory = new ConcreteCorporateCourseFactory();
		} else if(secondInput.equals("O")) {
			factory = new ConcreteOnlineCourseFactory();
		} 
		
		if(factory == null) {
			System.out.println("Invalid \"flavor\" of courses.");
		} else {
			course = factory.createCourse(userInput);
		}
		
		printCourse();
	}
	
	public static void main(String[] args) {
		new FactoryPattern();
	}
	
}

abstract class Course {
	
}

class JavaCourse extends Course {
	
}

class CPPCourse extends Course {
	
}

class StaticCourseFactory {
	public static Course createCourse(String courseType) {
		if(courseType.equals("J")) {
			return new JavaCourse();
		} else if(courseType.equals("CPP")){
			return new CPPCourse();
		} else {
			return null;
		}
	}
}

class CorporateJavaCourse extends JavaCourse {
	
}

class OnlineJavaCourse extends JavaCourse {
	
}

class CorporateCPPCourse extends CPPCourse {
	
}

class OnlineCPPCourse extends CPPCourse {
	
}

abstract class AbstractCourseFactory {
	public abstract Course createCourse(String courseType);
}

class ConcreteCorporateCourseFactory extends AbstractCourseFactory {
	public Course createCourse(String courseType) {
		if(courseType.equals("J")) {
			return new CorporateJavaCourse();
		} else if(courseType.equals("CPP")) {
			return new CorporateCPPCourse();
		} else {
			return null;
		}
	}
}

class ConcreteOnlineCourseFactory extends AbstractCourseFactory {
	public Course createCourse(String courseType) {
		if(courseType.equals("J")) {
			return new OnlineJavaCourse();
		} else if(courseType.equals("CPP")) {
			return new OnlineCPPCourse();
		} else {
			return null;
		}
	}
}
