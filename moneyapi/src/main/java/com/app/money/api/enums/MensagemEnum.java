package com.app.money.api.enums;

public enum MensagemEnum {

	EXCEPTION_PROPRIEDADE_INVALIDA("0001","propriedade.invalida"),
	EXCEPTION_CATEGORIA_NAO_ENCONTRADA_NA_BASE("0002","exception.categoria.nao.encontrada"),	
	EXCEPTION_CATEGORIA_JA_EXISTE_NA_BASE("0003","exception.categoria.ja.existe.na.base");
	public String status;
	public String mensagem;
	
	private MensagemEnum(String status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}
	
	public String getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}
}
