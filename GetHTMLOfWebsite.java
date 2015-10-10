import java.io.*;
import java.net.*;

public class GetHTMLOfWebsite {

	public GetHTMLOfWebsite() {
		try {
			URL url = new URL("http://not-a-ninja.hit.bg/");
			URLConnection con = url.openConnection();

			//InputStream is = con.getInputStream();
			//int c;
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while((line = reader.readLine()) != null) {//while((c = is.read()) != -1) {
				System.out.println(line); //System.out.print((char) c);
			}			
		} catch(Exception e) { System.out.println("Error!"); }	
	}

	public static void main(String[] args) {
		new GetHTMLOfWebsite();
	}
	
}
