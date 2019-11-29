package com.app.money.api.service.exception;

/**
 * Classe responsável por tratar excessões genéricas em tempo de execução.
 * Excessão do tipo não checada
 * @author eplima1
 *
 */
public class GenericException extends RuntimeException{

	
	private static final long serialVersionUID = 4943690205445505325L;
	
	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public GenericException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public GenericException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
