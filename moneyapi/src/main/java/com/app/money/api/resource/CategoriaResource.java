package com.app.money.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.money.api.event.RecursoCriadoEvent;
import com.app.money.api.model.Categoria;
import com.app.money.api.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/**
	 * 
	 * EndPoint responsável por listar todas as categorias.
	 * 
	 * @return List<Categoria>
	 */
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		return ResponseEntity.ok().body(categoriaService.listar());
	}
	/**
	 * 
	 * EndiPoint responsável por cadastrar uma categoria
	 * e retornar location do objeto recem cadastrado no header
	 * 
	 * @param categoria
	 * @param response
	 * @return categoria
	 */
	@PostMapping
	public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
		Categoria categoriaSalva = categoriaService.cadastrar(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(categoriaSalva, response, categoria.getCodigo()));
		
		/** Criando location do objeto recem salvo na base de dados. **/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
