package exceptions;

public class UnknownInputException extends Exception{

	public UnknownInputException(){
		super("Not a valid input");
	}
}
