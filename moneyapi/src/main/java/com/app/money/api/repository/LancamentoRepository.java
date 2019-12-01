package com.app.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.money.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
