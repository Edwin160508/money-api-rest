package com.app.money.api.service.exception;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço PessoaService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class PessoaExistenteException extends RuntimeException{
	
	private static final long serialVersionUID = 3335280901881472298L;

	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public PessoaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public PessoaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
