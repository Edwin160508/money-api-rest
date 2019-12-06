package com.app.money.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.app.money.api.enums.MensagemEnum;
import com.app.money.api.model.Lancamento;
import com.app.money.api.model.Pessoa;
import com.app.money.api.repository.LancamentoRepository;
import com.app.money.api.repository.filter.LancamentoFilter;
import com.app.money.api.service.exception.CategoriaNaoEncontradaException;
import com.app.money.api.service.exception.EntidadeNaoEncontradaException;
import com.app.money.api.service.exception.GenericException;
import com.app.money.api.service.exception.PessoaInativaException;
import com.app.money.api.service.exception.PessoaNaoEncontradaException;
import com.app.money.api.utils.Constante;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	Logger logger = LoggerFactory.getLogger(LancamentoService.class);
	
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
			verificaCategoriaExisteEmBase(lancamento);
			verificaPessoaInexistenteOuInativa(lancamento);
			
			return lancamentoRepository.save(lancamento);
		}catch(EntidadeNaoEncontradaException en) {
			throw en;
		}catch(DataIntegrityViolationException dve) {
			throw dve;
		}catch(CategoriaNaoEncontradaException cne) {
			throw cne;
		}catch(PessoaNaoEncontradaException pne) {
			throw pne;
		}catch(PessoaInativaException pie){
			throw pie;
		}catch(Exception e) {
			logger.error(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_CADASTRAR.getMensagem() + Constante.ERROR + e);
			throw new GenericException(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_CADASTRAR.getMensagem());
		}
	}
	
	/**
	 * 
	 * Método responsável por validar se pessoa existe na base ou é inativo.
	 * 
	 * @param lancamento
	 */
	private void verificaPessoaInexistenteOuInativa(Lancamento lancamento) {
		Pessoa pessoa = pessoaService.buscarPessoaPorCodigo(lancamento.getPessoa().getCodigo()).get();
		if(pessoa.isInativo()) {
			throw new PessoaInativaException();
		}
	}
	
	/**
	 * 
	 * Método responsável por verificar
	 * 
	 * @param lancamento
	 */
	private void verificaCategoriaExisteEmBase(Lancamento lancamento) {
		categoriaService.buscarCategoriaPorCodigo(lancamento.getCategoria().getCodigo());
	}
	
	/**
	 * 
	 * Método resonsável por trazer lista de lancamentos passando filtro.
	 * 
	 * @param lancamentoFilter
	 * @return List<Lancamento>
	 */
	public List<Lancamento> pesquisarLancamentoFiltro(LancamentoFilter lancamentoFilter){
		return lancamentoRepository.filtrar(lancamentoFilter);
	}

	/**
	 * 
	 * Método responsável por fazer exclusão da entidade lancamento através do código passado.
	 * 
	 * @param codigo
	 */
	public void excluir(Long codigo) {
		try {
			Optional<Lancamento> lancamento = buscarLancamentoPorCodigo(codigo);
			lancamentoRepository.delete(lancamento.get());
		}catch(EntidadeNaoEncontradaException ene) {
			throw ene;
		}catch(Exception e) {
			logger.error(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_EXCLUIR.getMensagem()+ Constante.ERROR + e);
			throw new GenericException(MensagemEnum.EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_EXCLUIR.getMensagem());
		}
	}
}
