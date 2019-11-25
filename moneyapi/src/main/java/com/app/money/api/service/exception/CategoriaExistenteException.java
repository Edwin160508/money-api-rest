package com.app.money.api.service.exception;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço CategoriaService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class CategoriaExistenteException extends RuntimeException{
	
	
	private static final long serialVersionUID = -8569840071522817509L;

	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public CategoriaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public CategoriaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
