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
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//Clausula Where onde o filtro será aplicado.
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
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

}
