package com.app.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.money.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
