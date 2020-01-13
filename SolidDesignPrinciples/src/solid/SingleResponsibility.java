package solid;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SingleResponsibility {

	public static void main(String[] args) throws Exception {
		LoadAndSave loadAndSave = new LoadAndSave();
		Journal journal = new Journal();
		
		loadAndSave.loadEnteries(journal, "loader.txt");
		journal.addEntry("Solid Principle");
		journal.addEntry("S Stands for Single Responsibility");
		System.out.println(journal);
		
		loadAndSave.saveEnteries(journal, "enteries", true);
	}

}

class Journal {
	private List<String> enteries = new ArrayList<>();
	private static int count = 0;
	
	public void addEntry(String entry) {
		enteries.add(""+ (++count) + ": " + entry);
	}
	
	public void removeEntry(int index) {
		enteries.remove(index);
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), enteries);
	}
	
}


class LoadAndSave {
	
	public void saveEnteries(Journal journal, String filename, boolean ovveride) throws Exception {
		if(ovveride || new File(filename).exists()) {
			try(PrintWriter print = new PrintWriter(filename)) {
				print.write(journal.toString());
			}
		}
		
		Runtime.getRuntime().exec("notepad.exe " + filename);
	}
	
	
	public void loadEnteries(Journal journal, String filename) throws Exception {
		if(new File(filename).exists()) {
			try(BufferedReader br = new BufferedReader(new FileReader(filename))){
				String line = br.readLine();
				while(line != null) {
					journal.addEntry(line);
					line = br.readLine();
				}
			}
		}
	}
}
