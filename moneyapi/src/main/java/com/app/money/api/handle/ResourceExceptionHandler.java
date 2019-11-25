package com.app.money.api.handle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.money.api.model.DetalheErro;
import com.app.money.api.service.exception.CategoriaExistenteException;
import com.app.money.api.service.exception.CategoriaNaoEncontradaException;

/**
 * 
 * Classe Responsável por manipular excessões que ocorrer em todos os 
 * Resources existentes do projeto.
 * 
 * @author edwin
 *
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	
	/**
	 * 
	 * Exceção levantada quando uma categoria específca não foi encontrada na base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return detalheErro
	 */
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<DetalheErro> handlerCategoriaNaoEncontradaException(CategoriaNaoEncontradaException e, HttpServletRequest request){
		DetalheErro detalheErro = new DetalheErro();
		detalheErro.setStatus(404l);
		detalheErro.setTitulo("Categoria não encontrada");
		detalheErro.setMenssagemDesenvolvedor("https://erros.moneyapi.com/404");
		detalheErro.setDataHora(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}	
	
	/**
	 * 
	 * Exceção levantada quando tenta cadastrar categoria repetida na base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return detalheErro
	 */
	@ExceptionHandler(CategoriaExistenteException.class)
	public ResponseEntity<DetalheErro> handlerCategoriaExistenteException(CategoriaExistenteException e, HttpServletRequest request){
		DetalheErro detalheErro = new DetalheErro();
		detalheErro.setStatus(409l);
		detalheErro.setTitulo("Categoria já existe na base de dados.");
		detalheErro.setMenssagemDesenvolvedor("https://erros.moneyapi.com/409");
		detalheErro.setDataHora(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}
	
}
