package com.app.money.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.money.api.enums.MensagemEnum;
import com.app.money.api.model.Pessoa;
import com.app.money.api.repository.PessoaRepository;
import com.app.money.api.service.exception.GenericException;
import com.app.money.api.service.exception.PessoaExistenteException;
import com.app.money.api.service.exception.PessoaNaoEncontradaException;
import com.app.money.api.utils.Constante;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	Logger logger = LoggerFactory.getLogger(PessoaService.class);
	
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	/**
	 * Método responsável por cadastrar pessoa.
	 * Não permite cadastro de pessoa com codigos repetidos  
	 * 
	 * @param categoria
	 * @return Categoria
	 */
	public Pessoa cadastrar(Pessoa pessoa) {
		if(pessoa.getCodigo() != null) {
			buscarPorId(pessoa.getCodigo()).get();
		}
		return pessoaRepository.save(pessoa);
	}
	
	/**
	 * 
	 * Método responsável por fazer 1 verificação 
	 * 1- Verificar se existe pessoa na base de dados caso negativo 
	 * 	  lança exceção CategoriaNaoEncontradaException.
	 *  
	 * 
	 * @param id
	 * @return Optional<Pessoa>
	 */
	public Optional<Pessoa> buscarPorId(Long id) {
		 Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		 		 
		 if(pessoaEncontrada.isPresent())
			 throw new PessoaExistenteException(MensagemEnum.EXCEPTION_PESSOA_JA_EXISTE_NA_BASE.getMensagem());
		 
		 return pessoaEncontrada;
	}
	
	/**
	 * 
	 * Método responsável por fazer 1 verificação 
	 * 1- Verificar se existe pessoa na base de dados caso negativo 
	 * 	  lança exceção CategoriaNaoEncontradaException.
	 * 
	 * @param id
	 * @return Optional<Pessoa>
	 */
	public Optional<Pessoa> buscarPessoaPorCodigo(Long id){
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		
		if(!pessoaEncontrada.isPresent()) 
			 throw new PessoaNaoEncontradaException(MensagemEnum.EXCEPTION_PESSOA_NAO_ENCONTRADA_NA_BASE.getMensagem());
		
		return pessoaEncontrada;
	}
	
	/**
	 * 
	 * Método responsável por fazer exclusão pessoa passando código
	 * como refêrencia.
	 * 
	 * @param codigo
	 */
	public void excluir(Long codigo) {
		try {
			Optional<Pessoa> buscarCategoriaPorCodigo = buscarPessoaPorCodigo(codigo);
			pessoaRepository.delete(buscarCategoriaPorCodigo.get());
		}catch(PessoaNaoEncontradaException pe) {
			throw pe;
		}catch(Exception e) {
			logger.error(MensagemEnum.EXCEPTION_PESSOA_NAO_FOI_POSSIVEL_EXCLUIR.getMensagem() + Constante.ERROR + e);
			throw new GenericException(MensagemEnum.EXCEPTION_PESSOA_NAO_FOI_POSSIVEL_EXCLUIR.getMensagem());
		}
	}
	/**
	 * 
	 * Método responsável por realizar atualização de informações referente a pessoa passando codigo
	 * 
	 * @param codigo
	 * @param pessoa
	 * @return pessoa
	 */
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		try {
			Optional<Pessoa> pessoaEncontradaBase = buscarPessoaPorCodigo(codigo);
			BeanUtils.copyProperties(pessoa, pessoaEncontradaBase.get(),"codigo");//copiando prorpriedades modificadas no cliente passando para objeto entidade.
			return pessoaRepository.save(pessoaEncontradaBase.get());
		}catch(PessoaNaoEncontradaException pne) {
			throw pne;
		}catch(Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
