package Exceptions;

public class FileTooLargeException extends Exception{

	public FileTooLargeException() {
		super("File too large!");
		// TODO Auto-generated constructor stub
	}

	public FileTooLargeException(int size, int maxSize) {
		super(String.format("File is too large! Maximum value is %nMB, but your file is %nMB", maxSize, size) );
	}

	
}
