package solid;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SingleResponsibility {

	public static void main(String[] args) throws Exception {
		Journal journal = new Journal();
		journal.addEntry("Solid Principle");
		journal.addEntry("S Stands for Single Responsibility");
		System.out.println(journal);
		
		new LoadAndSave().saveEnteries(journal, "enteries", true);
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
			
		}
		
		Runtime.getRuntime().exec("notepad.exe " + filename);
	}
}
