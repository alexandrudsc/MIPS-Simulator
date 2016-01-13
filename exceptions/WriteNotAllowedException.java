package exceptions;

@SuppressWarnings("serial")
public class WriteNotAllowedException extends Exception {
	
	public WriteNotAllowedException(){
		super("Registrul nu poate fi scris");
	}
	
	public WriteNotAllowedException(int register_index){
		super("Registrul nu poate fi scris # " + register_index);
	}
	

}
