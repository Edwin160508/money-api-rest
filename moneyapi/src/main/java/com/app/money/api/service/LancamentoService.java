package com.app.money.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.money.api.enums.MensagemEnum;
import com.app.money.api.model.Lancamento;
import com.app.money.api.repository.LancamentoRepository;
import com.app.money.api.service.exception.GenericException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	Logger logger = LoggerFactory.getLogger(LancamentoService.class);
	
	public List<Lancamento> listar(){
		return lancamentoRepository.findAll();
	}
	
	/**
	 * 
	 * Método responsável por fazer 1 verificação 
	 * 1- Verificar se existe lancamento na base de dados caso negativo 
	 * 	  lança exceção LancamentoNaoEncontradoException.
	 * 
	 * @param id
	 * @return Optional<Lancamento>
	 */
	public Optional<Lancamento> buscarLancamentoPorCodigo(Long codigo){
		Optional<Lancamento> lancamentoEncontrado = lancamentoRepository.findById(codigo);
		
		if(!lancamentoEncontrado.isPresent()) 
			 throw new GenericException(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_ENCONTRADO_NA_BASE.getMensagem());
		
		return lancamentoEncontrado;
	}
}
