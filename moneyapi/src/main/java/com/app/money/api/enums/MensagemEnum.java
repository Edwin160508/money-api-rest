package com.app.money.api.enums;

public enum MensagemEnum {

	EXCEPTION_PROPRIEDADE_INVALIDA("0001","propriedade.invalida"),
	EXCEPTION_CATEGORIA_NAO_ENCONTRADA_NA_BASE("0002","exception.categoria.nao.encontrada"),	
	EXCEPTION_CATEGORIA_JA_EXISTE_NA_BASE("0003","exception.categoria.ja.existe.na.base"),
	EXCEPTION_PESSOA_NAO_ENCONTRADA_NA_BASE("0004","exception.pessoa.nao.encontrada"),
	EXCEPTION_PESSOA_JA_EXISTE_NA_BASE("0005","exception.pessoa.ja.existe.na.base"),
	EXCEPTION_PESSOA_NAO_FOI_POSSIVEL_EXCLUIR("0006","exception.pessoa.nao.foi.possivel.excluir"),
	EXCEPTION_CATEGORIA_NAO_FOI_POSSIVEL_EXCLUIR("0007","exception.categoria.nao.foi.possivel.excluir"),
	EXCEPTION_LANCAMENTO_NAO_ENCONTRADO_NA_BASE("0008","exception.lancamento.nao.encontrado"),
	EXCEPTION_PESSOA_NAO_FOI_POSSIVEL_ATUALIZAR("0009","exception.pessoa.nao.foi.possivel.atualizar"),
	EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_CADASTRAR("0010","exception.lancamento.nao.foi.possivel.cadastrar"),
	EXCEPTION_CATEGORIA_NAO_FOI_POSSIVEL_CADASTRAR("0011","exception.lancamento.nao.foi.possivel.cadastrar"),
	EXCEPTION_PESSOA_NAO_FOI_POSSIVEL_CADASTRAR("0012","exception.lancamento.nao.foi.possivel.cadastrar"),
	ERROR_OPERACAO_NAO_PERMITIDA("0013","error.operacao.nao.permitida"),
	EXCEPTION_LANCAMENTO_NAO_FOI_POSSIVEL_EXCLUIR("00014","exception.lancamento.nao.foi.possivel.excluir");
	
	
	
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
