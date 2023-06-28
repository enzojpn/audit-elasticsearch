package br.gov.sp.audit.auditelasticsearch.model;

public class Boleando {
	
	private Must must;

	private Filter filter;
	
	
	public Must getMust() {
		return must;
	}

	public void setMust(Must must) {
		this.must = must;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	
}
