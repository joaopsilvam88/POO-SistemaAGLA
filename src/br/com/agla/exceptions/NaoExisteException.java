package br.com.agla.exceptions;

public class NaoExisteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoExisteException(String msg){
		super(msg);
	}
}
