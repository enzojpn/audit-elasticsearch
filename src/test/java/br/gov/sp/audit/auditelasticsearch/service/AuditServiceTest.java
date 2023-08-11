package br.gov.sp.audit.auditelasticsearch.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AuditServiceTest {

	@Test
	public void TestaConsultarLinhas() {
		
	 AuditService auditService = new AuditService();
	 LocalDateTime ld1 = LocalDateTime.now().minusHours(4);
	 
	 LocalDateTime ld2 = LocalDateTime.now();
	 auditService.consultarLinhas(  ld1,   ld2);
	 
		
		
	}
	
}
