package exercice2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrefixFilter implements DirectoryStream.Filter<Path>{
	private int size;
	
	public PrefixFilter(int size) {
		this.size = size;
	}

	@Override
	public boolean accept(Path entry) throws IOException {
		if(Files.size(entry) >= this.size) {
			return true;
		}
		
		return false;
	}
	
	
	

}
