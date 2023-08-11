package br.gov.sp.audit.auditelasticsearch.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.gov.sp.audit.auditelasticsearch.model.Audit;

@Service
public class AuditService {

	private static long quantidadeMock = 60000L; //quantidade a ser fatiado a cada busca (mock)
	
	public long verificaLimiteDeConsulta(LocalDateTime dataIni, LocalDateTime dataFim) {
 
		long quantidadeLinhas = consultarLinhas(dataIni, dataFim); //mock para consulta de linhas
	 
		if (quantidadeLinhas > 10000) {
			
			 LocalDateTime dataMediana = encontraDataMediana(dataIni, dataFim);

			 
			 long quantidadeLinhasMetade1 = verificaLimiteDeConsulta(dataIni, dataMediana);
			 long quantidadeLinhasMetade2 = verificaLimiteDeConsulta(dataMediana, dataFim);

	           quantidadeLinhas = quantidadeLinhasMetade1 + quantidadeLinhasMetade2;
		 
		} else {
			System.out.println("Limite OK!!!");
		 
		}
		return quantidadeLinhas;
	}

	private LocalDateTime encontraDataMediana(LocalDateTime dataIni, LocalDateTime dataFim) {
		LocalDateTime meio = dataIni.plusSeconds(dataIni.until(dataFim, java.time.temporal.ChronoUnit.SECONDS) / 2);
		return meio;
	}

	public long consultarLinhas(LocalDateTime inicio, LocalDateTime fim) {
		quantidadeMock /= 2;
		return quantidadeMock;
		
	}

	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zoneId);
	}
//
//	//TODO apagar este metodo apos teste
//	@Scheduled(fixedDelay   = 1000)
//	public void testeAgendamento() throws InterruptedException {
//		
//		System.out.println("Rodando -"+ ident +"- as " + LocalTime.now());
//		Thread.sleep(7000);
//		System.out.println("metodo -"+ ident+"- finalizado ");
//		ident++;
//		
//	}
}
