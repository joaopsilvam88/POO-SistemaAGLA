package exception;

public class ProfessorJaExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ProfessorJaExisteException(String s) {
		super(s);
	}
}
