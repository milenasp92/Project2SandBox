package pageObj;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileTxt {
public static Map<String, String> readXPaths() {
		
		Map<String, String> xPaths = new HashMap<>();
		File txtFile= new File("xPaths.txt"); 
		Scanner in;
		
		try {
			in = new Scanner(txtFile);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String values[] = data.split("~");
				//	System.out.println(values[0] + " " + values[1]);
				xPaths.put(values[0], values[1]);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occured!");
			e.printStackTrace();
		}

		return xPaths;

	}

}
