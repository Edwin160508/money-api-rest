package com.app.money.api.repository.lancamento;

import java.util.List;

import com.app.money.api.model.Lancamento;
import com.app.money.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
	
}
