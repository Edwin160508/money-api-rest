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
import com.app.money.api.service.exception.EntidadeNaoEncontradaException;
import com.app.money.api.service.exception.GenericException;
import com.app.money.api.utils.Constante;

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
			 throw new EntidadeNaoEncontradaException(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_ENCONTRADO_NA_BASE.getMensagem());
		
		return lancamentoEncontrado;
	}
	
	/**
	 * 
	 * Método responsável por realizar cadastro de uma entidade lancamento.
	 * 
	 * @param lancamento
	 * @return Lancamento
	 */
	public Lancamento cadastrar(Lancamento lancamento) {
		try {
			if(lancamento.getCodigo() != null) {
				buscarLancamentoPorCodigo(lancamento.getCodigo());
			}
			return lancamentoRepository.save(lancamento);
		}catch(EntidadeNaoEncontradaException en) {
			throw en;
		}catch(Exception e) {
			logger.error(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_CADASTRAR.getMensagem() + Constante.ERROR + e);
			throw new GenericException(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_CADASTRAR.getMensagem());
		}
	}
}
