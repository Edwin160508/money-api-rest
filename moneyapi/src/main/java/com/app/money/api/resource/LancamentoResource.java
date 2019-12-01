package com.app.money.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.money.api.model.Lancamento;
import com.app.money.api.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listar(){
		return ResponseEntity.ok().body(lancamentoService.listar());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarLancamentoPorCodigo(@PathVariable Long codigo){
		Optional<Lancamento> lancamentoEncontrado = lancamentoService.buscarLancamentoPorCodigo(codigo);
		return ResponseEntity.ok().body(lancamentoEncontrado.get());
	}
}
