package com.app.money.api.handle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.money.api.enums.MensagemEnum;
import com.app.money.api.model.DetalheErro;
import com.app.money.api.service.exception.CategoriaExistenteException;
import com.app.money.api.service.exception.CategoriaNaoEncontradaException;
import com.app.money.api.service.exception.EntidadeNaoEncontradaException;
import com.app.money.api.service.exception.PessoaExistenteException;
import com.app.money.api.service.exception.PessoaInativaException;
import com.app.money.api.service.exception.PessoaNaoEncontradaException;

/**
 * 
 * Classe Responsável por manipular excessões que ocorrer em todos os 
 * Resources existentes do projeto.
 * 
 * @author edwin
 *
 */

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
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
		detalheErro.setTitulo(messageSource.getMessage(MensagemEnum.EXCEPTION_CATEGORIA_NAO_ENCONTRADA_NA_BASE.getMensagem(), null, LocaleContextHolder.getLocale()));
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
		detalheErro.setTitulo(messageSource.getMessage(MensagemEnum.EXCEPTION_CATEGORIA_JA_EXISTE_NA_BASE.getMensagem(), null, LocaleContextHolder.getLocale()));
		detalheErro.setMenssagemDesenvolvedor("https://erros.moneyapi.com/409");
		detalheErro.setDataHora(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detalheErro);
	}
	
	/**
	 * Método responsável por fazer Tratamento de proriedades inválidas passadas pelo JSON.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request){		
		DetalheErro detalheErro = new DetalheErro();
		detalheErro.setStatus(400l);
		detalheErro.setTitulo(messageSource.getMessage(MensagemEnum.EXCEPTION_PROPRIEDADE_INVALIDA.getMensagem(), null, LocaleContextHolder.getLocale()));
		detalheErro.setMenssagemDesenvolvedor(e.getCause().toString());
		detalheErro.setDataHora(System.currentTimeMillis());
		List<DetalheErro> erros = Arrays.asList(detalheErro);

		return handleExceptionInternal(e, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Método responsável por fazer tratamentos de parametros nulos
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<DetalheErro> erros = criaListaErros(ex.getBindingResult(), 400l);
		return handleExceptionInternal(ex, erros,headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<DetalheErro> criaListaErros(BindingResult bindingResult, Long httpStatus){
		List<DetalheErro> erros = new ArrayList<>();
		
		for(FieldError campoErro: bindingResult.getFieldErrors()) {
			erros.add(new DetalheErro(messageSource.getMessage(campoErro,LocaleContextHolder.getLocale()),httpStatus,System.currentTimeMillis(), campoErro.toString()));
		}
		return erros;
	}
	
	/**
	 * 
	 * Exceção levantada quando uma pessoa específca não foi encontrada na base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return detalheErro
	 */
	@ExceptionHandler(PessoaNaoEncontradaException.class)
	public ResponseEntity<DetalheErro> handlerPessoaNaoEncontradaException(PessoaNaoEncontradaException e, HttpServletRequest request){
		DetalheErro detalheErro = new DetalheErro();
		detalheErro.setStatus(404l);
		detalheErro.setTitulo(messageSource.getMessage(MensagemEnum.EXCEPTION_PESSOA_NAO_ENCONTRADA_NA_BASE.getMensagem(), null, LocaleContextHolder.getLocale()));
		detalheErro.setMenssagemDesenvolvedor("https://erros.moneyapi.com/404");
		detalheErro.setDataHora(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}	
	
	/**
	 * 
	 * Exceção levantada quando tenta cadastrar pessoa repetida na base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return detalheErro
	 */
	@ExceptionHandler(PessoaExistenteException.class)
	public ResponseEntity<DetalheErro> handlerCategoriaExistenteException(PessoaExistenteException e, HttpServletRequest request){
		DetalheErro detalheErro = new DetalheErro();
		detalheErro.setStatus(409l);
		detalheErro.setTitulo(messageSource.getMessage(MensagemEnum.EXCEPTION_PESSOA_JA_EXISTE_NA_BASE.getMensagem(), null, LocaleContextHolder.getLocale()));
		detalheErro.setMenssagemDesenvolvedor("https://erros.moneyapi.com/409");
		detalheErro.setDataHora(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detalheErro);
	}
	
	/**
	 * 
	 * Exceção levantada quando ocorre erro de integridade de dados.
	 * Ex cadastrar lancamento passando 'codigoCatgoria' inexistente em base.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, 
			WebRequest request) {
		String mensagemUsuario = messageSource.getMessage(MensagemEnum.ERROR_OPERACAO_NAO_PERMITIDA.getMensagem(), null,
				LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor =  ExceptionUtils.getRootCauseMessage(ex);

		List<DetalheErro> erros = Arrays.asList(new DetalheErro(mensagemUsuario,400l, System.currentTimeMillis(),mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	/**
	 * 
	 * Exceção levantada quando tenta cadastrar lancamento com pessoa com cadastro inativo
	 * 
	 * @param p
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ PessoaInativaException.class })
	public ResponseEntity<Object> handlePessoaInativaException(PessoaInativaException p, WebRequest request){
		String mensagemUsuario = messageSource.getMessage(MensagemEnum.ERROR_OPERACAO_NAO_PERMITIDA.getMensagem(), null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor =  ExceptionUtils.getRootCauseMessage(p);
		List<DetalheErro> erros = Arrays.asList(new DetalheErro(mensagemUsuario,400l, System.currentTimeMillis(),mensagemDesenvolvedor));
		return handleExceptionInternal(p, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Exceção levandada quando tenta consultar entidade inexistente na base
	 * @param enf
	 * @param request
	 * @return
	 */
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException enf, WebRequest request){
		String mensagemUsuario = messageSource.getMessage(MensagemEnum.ERROR_OPERACAO_NAO_PERMITIDA.getMensagem(), null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(enf);
		List<DetalheErro> erros = Arrays.asList(new DetalheErro(mensagemUsuario, 400l, System.currentTimeMillis(), mensagemDesenvolvedor));
		return handleExceptionInternal(enf, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EntidadeNaoEncontradaException.class})
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ene, WebRequest request){
		String mensagemUsuario = messageSource.getMessage(ene.getMessage(), null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ene);
		List<DetalheErro> erros = Arrays.asList(new DetalheErro(mensagemUsuario, 400l, System.currentTimeMillis(), mensagemDesenvolvedor));
		return handleExceptionInternal(ene, erros, new HttpHeaders(),HttpStatus.BAD_REQUEST, request);
	}
}
