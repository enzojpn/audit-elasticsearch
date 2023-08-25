package br.gov.sp.audit.auditelasticsearch.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.gov.sp.audit.auditelasticsearch.model.Audit;

@Service
public class AuditService {
 
	
	private static Stack<Long> pilha = new Stack<Long>();
	
	public AuditService() {
		pilha.push(908L);
		pilha.push(907L);
		pilha.push(906L);
		pilha.push(905L);
		pilha.push(904L);
		pilha.push(903L);
		pilha.push(902L);
		pilha.push(901L);
		pilha.push(18040L);
		pilha.push(18030L);
		pilha.push(18020L);
		pilha.push(18010L);
		pilha.push(32020L);
		pilha.push(33910L);
		pilha.push(67000L);
		
	}
	
	
	public long verificaLimiteDeConsulta(LocalDateTime dataIni, LocalDateTime dataFim) {
 
		long quantidadeLinhas = consultarLinhas(dataIni, dataFim); //mock para consulta de linhas
	 
		if (quantidadeLinhas > 10000) {
			System.out.println("quantidades" + quantidadeLinhas);
			 LocalDateTime dataMediana = encontraDataMediana(dataIni, dataFim);

			 
			 long quantidadeLinhasMetade1 = verificaLimiteDeConsulta(dataIni, dataMediana);
			 long quantidadeLinhasMetade2 = verificaLimiteDeConsulta(dataMediana, dataFim);

	           quantidadeLinhas = quantidadeLinhasMetade1 + quantidadeLinhasMetade2;
		 
		} else {
			System.out.println("Limite OK!!!" + quantidadeLinhas);
			
		 
		}
		return quantidadeLinhas;
	}

	private LocalDateTime encontraDataMediana(LocalDateTime dataIni, LocalDateTime dataFim) {
		LocalDateTime meio = dataIni.plusSeconds(dataIni.until(dataFim, java.time.temporal.ChronoUnit.SECONDS) / 2);
		return meio;
	}

	public long consultarLinhas(LocalDateTime inicio, LocalDateTime fim) {
		 
		 //mock
		return pilha.pop();
		
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
