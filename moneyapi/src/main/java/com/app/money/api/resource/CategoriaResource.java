package com.app.money.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	/**
	 * 
	 * EndPoint responsável por localizar uma categoria através do código.
	 * 
	 * @param codigo
	 * @return categoria
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarCategoriaPorCodigo(@PathVariable Long codigo){
		Optional<Categoria> categoriaEncontrada = categoriaService.buscarCategoriaPorCodigo(codigo);
		return ResponseEntity.ok().body(categoriaEncontrada.get());
		
	}
}
