package br.gov.sp.audit.auditelasticsearch.model;

public class AuditQuery {

		private AuditQueryResponse response;
	
		private Long size;
		private Query query;
		public Long getSize() {
			return size;
		}
		public void setSize(Long size) {
			this.size = size;
		}
		public Query getQuery() {
			return query;
		}
		public void setQuery(Query query) {
			this.query = query;
		}
	
	
	 
	
}
