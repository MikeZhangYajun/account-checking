package cst8284.lab7;

public class BadAccountInputException extends java.lang.RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadAccountInputException() {
		this("Bad input: value entered is incorrect");
	}

	public BadAccountInputException(String message) {
		super(message);
	}

}
