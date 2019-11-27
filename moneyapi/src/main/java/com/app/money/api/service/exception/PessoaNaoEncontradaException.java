package com.app.money.api.service.exception;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço PessoaService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class PessoaNaoEncontradaException extends RuntimeException{

	
	private static final long serialVersionUID = -9060999083572961747L;

	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public PessoaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
