package br.com.agla.exceptions;

public class TurmaInexistenteExeption extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TurmaInexistenteExeption(String s) {
		super(s);
	}
}
