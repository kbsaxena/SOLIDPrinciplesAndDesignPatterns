package solid;

public class InterfaceSegregation {

	public InterfaceSegregation() {
		
	}

}

class Document {}

interface Machine {
	void print(Document d);
	void fax(Document d);
	void scan(Document d);
}

class NewPrinter implements Machine{

	@Override
	public void print(Document d) { /*Implementation*/ }

	@Override
	public void fax(Document d) { /*Implementation*/ }

	@Override
	public void scan(Document d) { /*Implementation*/ }
	
}

class OldPrinter implements Machine{

	@Override
	public void print(Document d) { /*Implementation*/ }

	@Override
	public void fax(Document d) {/* No Implementation...ERROR */}

	@Override
	public void scan(Document d) { /* No Implementation...ERROR */ }
}


//New code
interface Printer {
	void print(Document d);
}

interface Scanner {
	void scan(Document d);
}

class NormalPrinter implements Printer{

	@Override
	public void print(Document d) { }
	
}

class XeroxMachine implements Printer, Scanner{

	@Override
	public void scan(Document d) { }

	@Override
	public void print(Document d) { }
	
}

//Another Way

interface MultiFunctionDevice extends Printer, Scanner {}

class MultiFunctionMachine implements MultiFunctionDevice{

	private Printer print;
	private Scanner scan;
	
	@Override
	public void print(Document d) {
		print.print(d);
	}

	@Override
	public void scan(Document d) {
		scan.scan(d);
	}
	
}
