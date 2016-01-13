package exceptions;

@SuppressWarnings("serial")
public class RegisterOutOfBoundsException extends ArrayIndexOutOfBoundsException {
	
	public RegisterOutOfBoundsException(){
		super("Registru invalid.");
	}
	
	public RegisterOutOfBoundsException(int register_index){
		super("Registru # " + register_index + " invalid.");
	}
	
}
