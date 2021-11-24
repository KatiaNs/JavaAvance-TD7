package exercice1;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		String path = ".";

		try {
			
			DirMonitor monitor = new DirMonitor(Paths.get(path));
			
			monitor.allFiles();
			System.out.println("-----------------------");

			System.out.println(monitor.sizeOfFiles());
			System.out.println("-----------------------");
			
			System.out.println(monitor.mostRecent());
			System.out.println("-----------------------");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
