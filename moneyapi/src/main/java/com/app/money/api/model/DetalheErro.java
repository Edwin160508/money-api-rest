package com.app.money.api.model;

/**
 * 
 * Classe responsável por reresentar detalhe do erro ocorrido
 * de acordo com a exceção levantada na aplicação.
 * 
 * @author edwin
 *
 */
public class DetalheErro {

	private String titulo;
	
	private Long status;
	
	private Long dataHora;
	
	private String menssagemDesenvolvedor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getDataHora() {
		return dataHora;
	}

	public void setDataHora(Long dataHora) {
		this.dataHora = dataHora;
	}

	public String getMenssagemDesenvolvedor() {
		return menssagemDesenvolvedor;
	}

	public void setMenssagemDesenvolvedor(String menssagemDesenvolvedor) {
		this.menssagemDesenvolvedor = menssagemDesenvolvedor;
	}
}
