package com.app.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.money.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
