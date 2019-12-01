package com.app.money.api.service.exception;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço
 * Excessão do tipo não checada
 * 
 * @author edwin
 *
 */
public class EntidadeNaoEncontradaException extends RuntimeException{

	
	private static final long serialVersionUID = -3030532717325538282L;
	
	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public EntidadeNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
