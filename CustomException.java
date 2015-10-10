public class CustomException extends Exception {

	public CustomException() {
		super("Uh-huh, CustomException was thrown!");
	}

	public static void main(String[] args) {

		int x = 5;

		try {
			if(x < 10) {
				throw(new CustomException());
			}
			System.out.println("Everything went fine.");
		} catch(CustomException e) {
			System.out.println("Error: " + e.getMessage());
		} catch(Exception e) {
			System.out.println("Undefined exception.");
			e.printStackTrace();
		}

	}

}
