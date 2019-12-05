package com.app.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.money.api.model.Lancamento;
import com.app.money.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
