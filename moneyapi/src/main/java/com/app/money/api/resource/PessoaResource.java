package com.app.money.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.money.api.event.RecursoCriadoEvent;
import com.app.money.api.model.Pessoa;
import com.app.money.api.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/**
	 * 
	 * EndPoint responsável por listar todas as pessoas.
	 * 
	 * @return List<Pessoa>
	 */
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar(){
		return ResponseEntity.ok().body(pessoaService.listar());
	}
	/**
	 * 
	 * EndiPoint responsável por cadastrar uma pessoa
	 * e retornar location do objeto recem cadastrado no header
	 * 
	 * @param pessoa
	 * @param response
	 * @return Pessoa
	 */
	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalva = pessoaService.cadastrar(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	/**
	 * 
	 * EndPoint responsável por localizar uma pessoa através do código.
	 * 
	 * @param codigo
	 * @return Pessoa
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPessoaPorCodigo(@PathVariable Long codigo){
		Optional<Pessoa> pessoaEncontrada = pessoaService.buscarCategoriaPorCodigo(codigo);
		return ResponseEntity.ok().body(pessoaEncontrada.get());
		
	}
	
	@DeleteMapping
	public ResponseEntity<Object> excluir(@PathVariable Long codigo){
		pessoaService.excluir(codigo);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
