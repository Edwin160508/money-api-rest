package com.app.money.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.money.api.model.Categoria;
import com.app.money.api.repository.CategoriaRepository;
import com.app.money.api.service.exception.CategoriaExistenteException;
import com.app.money.api.service.exception.CategoriaNaoEncontradaException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar(){		
		return categoriaRepository.findAll();
	}
	
	/**
	 * Método responsável por cadastrar categoria.
	 * Não permite cadastro de categoria com codigos repetidos  
	 * 
	 * @param categoria
	 * @return Categoria
	 */
	public Categoria cadastrar(Categoria categoria) {
		if(categoria.getCodigo() != null) {
			buscarPorId(categoria.getCodigo()).get();
		}
		
		return categoriaRepository.save(categoria);
	}
	
	/**
	 * 
	 * Método responsável por fazer 2 verificações 
	 * 1- Verificar se existe categoria na base de dados caso negativo 
	 * 	  lança exceção CategoriaNaoEncontradaException.
	 *  
	 * 2- Verificar se autor selecionado já existe na base caspo positivo
	 *    lança exceção AutorExistenteException.
	 * 
	 * @param id
	 * @return Optional<Categoria>
	 */
	public Optional<Categoria> buscarPorId(Long id) {
		 Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		 if(!categoriaEncontrada.isPresent()) 
			 throw new CategoriaNaoEncontradaException("Categoria não encontrado na base de dados.");
		 
		 if(categoriaEncontrada.isPresent())
			 throw new CategoriaExistenteException("Categoria já existe na base de dados.");
		 
		 return categoriaEncontrada;
	}
	
	/**
	 * 
	 * Método responsável por fazer 2 verificações 
	 * 1- Verificar se existe categoria na base de dados caso negativo 
	 * 	  lança exceção CategoriaNaoEncontradaException.
	 * 
	 * @param id
	 * @return Optional<Categoria>
	 */
	public Optional<Categoria> buscarCategoriaPorCodigo(Long id){
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		
		if(!categoriaEncontrada.isPresent()) 
			 throw new CategoriaNaoEncontradaException("Categoria não encontrado na base de dados.");
		
		return categoriaEncontrada;
	}
}
