package com.app.money.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.money.api.enums.MensagemEnum;
import com.app.money.api.model.Pessoa;
import com.app.money.api.repository.PessoaRepository;
import com.app.money.api.service.exception.PessoaExistenteException;
import com.app.money.api.service.exception.PessoaNaoEncontradaException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
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
	public Optional<Pessoa> buscarCategoriaPorCodigo(Long id){
		Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
		
		if(!pessoaEncontrada.isPresent()) 
			 throw new PessoaNaoEncontradaException(MensagemEnum.EXCEPTION_PESSOA_NAO_ENCONTRADA_NA_BASE.getMensagem());
		
		return pessoaEncontrada;
	}
}
