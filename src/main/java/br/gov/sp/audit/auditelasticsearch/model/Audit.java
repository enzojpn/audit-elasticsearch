package br.gov.sp.audit.auditelasticsearch.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


public class Audit {

	
	
	private Long id;
	private String trx;
	
	private Long quantidade;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataInicio;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataFim;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrx() {
		return trx;
	}
	public void setTrx(String trx) {
		this.trx = trx;
	}
	
	
	
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
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
	
	
	
	
	
}
