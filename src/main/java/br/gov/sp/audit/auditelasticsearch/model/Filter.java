package br.gov.sp.audit.auditelasticsearch.model;

import java.util.List;

public class Filter {
	
	List<Range> ranges;

	public List<Range> getRanges() {
		return ranges;
	}

	public void setRanges(List<Range> ranges) {
		this.ranges = ranges;
	}
	
	
}
