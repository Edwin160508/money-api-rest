package com.app.money.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.app.money.api.model.Lancamento;
import com.app.money.api.model.Lancamento_;
import com.app.money.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * 
	 * Método responsável por listar lancamentos já filtrada.
	 * 
	 * @param lancamentoFilter
	 * @return List<Lancamento>
	 */
	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable page) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//Clausula Where onde o filtro será aplicado.
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, page);
		return new PageImpl<>(query.getResultList(), page, total(lancamentoFilter)); //query.getResultList();
	}

	/**
	 * 
	 * Método responsável por criar restrições retornando uma lista de predicate
	 * 
	 * @param lancamentoFilter
	 * @param builder
	 * @param root
	 * @return Predicate[]
	 */
	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		
			List<Predicate> predicates = new ArrayList<>();
		
			if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
				predicates.add(builder.like(builder.lower(root.get(Lancamento_.DESCRICAO)), "%"+lancamentoFilter.getDescricao().toLowerCase()+"%"));
			}
			if(lancamentoFilter.getDataVencimentoDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.DATA_VENCIMENTO), lancamentoFilter.getDataVencimentoDe()));
			}
			if(lancamentoFilter.getDataVencimentoAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.DATA_VENCIMENTO), lancamentoFilter.getDataVencimentoAte()));
			}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	/**
	 * 
	 * Método responsável por criar configuração da paginação dos lancamentos
	 * 
	 * @param query
	 * @param page
	 */
	private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable page) {
		int paginaAtual = page.getPageNumber();
		int totalRegistroPorPagina = page.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
		
	}
	
	/**
	 * 
	 * Método responsa´vel por contar todos os registros de lancamento
	 * 
	 * @param lancamentoFilter
	 * @return Long
	 */
	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter,builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
