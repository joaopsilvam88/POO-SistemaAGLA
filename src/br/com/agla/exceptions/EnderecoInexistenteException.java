package br.com.agla.exceptions;

public class EnderecoInexistenteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public EnderecoInexistenteException(String s) {
		super(s);
	}
}
