package exceptions;

public class InvalidUserException extends Exception {

	private static final long serialVersionUID = 8430290272859995878L;

	public InvalidUserException(String message) {
		super(message);
	}
	
}
