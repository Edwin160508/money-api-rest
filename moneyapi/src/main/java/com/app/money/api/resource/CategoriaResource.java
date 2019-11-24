package com.app.money.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.money.api.model.Categoria;
import com.app.money.api.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * EndPoint responsável por listar todas as categorias.
	 * @return List<Categoria>
	 */
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		return ResponseEntity.ok().body(categoriaService.listar());
	}
}
