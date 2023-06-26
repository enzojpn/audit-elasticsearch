package br.gov.sp.audit.auditelasticsearch.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


public class Audit {
 
	
	private Long id;

	private String indice;
	
	private String userId;
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataInicio;
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataFim;
	private String tipo;
	private String transacao;
	private String terminal;

	//fragmento
	private String tela;
 
	private Long quantidade =0L ;
 
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public String getIndice() {
		return indice;
	}
 
	public void setIndice(String indice) {
		this.indice = indice;
	}
 

	public String getUserId() {
		return userId;
	} 
	public void setUserId(String userId) {
		this.userId = userId;
	}
 
	public Date getDataInicio() {
		return dataInicio;
	}
 
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
 
	public Date getDataFim() {
		return dataFim;
	}
 
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
 
	public String getTipo() {
		return tipo;
	}
 
	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 
	public String getTransacao() {
		return transacao;
	}
 
	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}
 

	public String getTerminal() {
		return terminal;
	}
 
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
 
	public String getTela() {
		return tela;
	}
 
	public void setTela(String tela) {
		this.tela = tela;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
}
