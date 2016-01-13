package exceptions;

@SuppressWarnings("serial")
public class MemoryOutOfBoundsException extends ArrayIndexOutOfBoundsException {
	
	public MemoryOutOfBoundsException(){
		super("Adresa de memorie invalida");
	}

}
