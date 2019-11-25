package com.app.money.api.service.exception;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço CategoriaService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class CategoriaNaoEncontradaException extends RuntimeException{

	
	private static final long serialVersionUID = -3071450483710640204L;
	
	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public CategoriaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public CategoriaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
