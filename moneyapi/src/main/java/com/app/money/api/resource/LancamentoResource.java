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
import com.app.money.api.model.Lancamento;
import com.app.money.api.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listar(){
		return ResponseEntity.ok().body(lancamentoService.listar());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarLancamentoPorCodigo(@PathVariable Long codigo){
		Optional<Lancamento> lancamentoEncontrado = lancamentoService.buscarLancamentoPorCodigo(codigo);
		return ResponseEntity.ok().body(lancamentoEncontrado.get());
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> cadastrar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoSalvo = lancamentoService.cadastrar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
}
