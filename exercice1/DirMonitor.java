package exercice1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

import exercice2.MyAction;

public class DirMonitor {
	private Path path;

	public DirMonitor(Path path) throws IOException {

		File file = path.toFile();

		if(!file.isDirectory() || !file.canRead()) {
			System.out.println("Error");
			throw new IOException("file not found");
		}
		this.path = path;

	}


	public void allFiles() {
		try {
			for (Path path : Files.newDirectoryStream(path)) {
				//File f = path.toFile();
				//System.out.println(path.getFileName());
				System.out.println(path.toFile().getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int sizeOfFiles() {
		int somme = 0;

		try {

			for (Path path : Files.newDirectoryStream(path)) {
				somme += (int) path.toFile().length();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}

		return somme;
	}


	public Path mostRecent() {
		FileTime lastTime = FileTime.fromMillis(0);
		Path recent = null;
		try {
			for(Path path : Files.newDirectoryStream(path)) {
				FileTime fileTime = Files.getLastModifiedTime(path);

				if(fileTime.compareTo(lastTime) > 0) {
					lastTime = fileTime;
					recent = path;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return recent;
	}


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

		public void printFileFilter(int size) {
			try {
				for(Path path : Files.newDirectoryStream(path, new DirectoryStream.Filter<Path>(){

					@Override
					public boolean accept(Path entry) throws IOException {
						if(Files.size(entry) >= size) {
							return true;
						}
						return false;
					}

				}));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		public void printFileFilterWithAction(int size) {
			try {
				applyAction(size, new MyAction() {

					@Override
					public void perform(Path p) throws IOException {
						// TODO Auto-generated method stub
						
					}

				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void applyAction(int size, MyAction action) throws IOException {
			for (Path path : Files.newDirectoryStream(path)) {
				action.perform(path);

			}
		}

	}


}
