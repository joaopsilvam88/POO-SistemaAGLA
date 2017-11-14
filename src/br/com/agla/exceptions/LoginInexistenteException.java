package exception;

public class LoginInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public LoginInexistenteException(String s) {
		super(s);
	}
}
