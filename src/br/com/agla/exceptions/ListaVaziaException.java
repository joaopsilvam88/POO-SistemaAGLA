package br.com.agla.exceptions;

public class ListaVaziaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ListaVaziaException(String s) {
		super(s);
	}
}
