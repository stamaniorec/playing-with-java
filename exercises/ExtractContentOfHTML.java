import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ExtractContentOfHTML {

	//StringBuilder contentWithoutHTML;
	String contentWithoutHTML = "";
	
	public ExtractContentOfHTML(String pathName) {
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader(pathName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String curLine;
		boolean stopReading;
		
		try {
			while((curLine = r.readLine()) != null) {
				stopReading = false;
				String tmpStr = "";
				
				for(int i = 0; i < curLine.length(); ++i) {
					
					if(curLine.charAt(i)== '<') {
						stopReading = true;
					} else if(curLine.charAt(i) == '>') {
						stopReading = false;
					}
					
					if(!stopReading && curLine.charAt(i) != '>' && curLine.charAt(i) != '\t') {
						tmpStr += curLine.charAt(i);
					}
					
				}
				
				if(!tmpStr.isEmpty() && tmpStr != null) {
					contentWithoutHTML += tmpStr + "\n";
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printContentWithoutHTML() {
		System.out.println(contentWithoutHTML);
	}
	
	public static void main(String[] args) {
		ExtractContentOfHTML problem = new ExtractContentOfHTML(
				"/home/stamaniorec/workspace/Problems/res/sample_html.html");
		problem.printContentWithoutHTML();
	}
	
}
