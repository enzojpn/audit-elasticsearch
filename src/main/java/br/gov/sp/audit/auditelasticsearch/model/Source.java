package br.gov.sp.audit.auditelasticsearch.model;

public class Source {

	private String arquivo;
	private String dataHoraFull;
	private String terminal;
	private String userId;
	private String transacao;
	private String tela;

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getDataHoraFull() {
		return dataHoraFull;
	}

	public void setDataHoraFull(String dataHoraFull) {
		this.dataHoraFull = dataHoraFull;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransacao() {
		return transacao;
	}

	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

}
