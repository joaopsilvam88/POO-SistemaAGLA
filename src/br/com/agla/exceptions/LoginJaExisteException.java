package br.com.agla.exceptions;

public class LoginJaExisteException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	
	public LoginJaExisteException(String s) {
		super(s);
	}
}
