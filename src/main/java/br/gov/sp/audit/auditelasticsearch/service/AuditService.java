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

 
	
	public Long verificaLimiteDeConsulta(LocalDateTime inicio, LocalDateTime fim) {

		LocalDateTime dataIni = inicio;
		LocalDateTime dataFim = fim;

		Long quantidadeLinhas = consultarLinhas(dataIni, dataFim);
	 
		if (quantidadeLinhas > 10000) {
			
			 LocalDateTime meio = dataIni.plusSeconds(dataIni.until(dataFim, java.time.temporal.ChronoUnit.SECONDS) / 2);

			 System.out.println(meio + "  <<< << <<MEIO");
			 
			 Long quantidadeLinhasMetade1 = verificaLimiteDeConsulta(dataIni, meio);
			 Long quantidadeLinhasMetade2 = verificaLimiteDeConsulta(meio, dataFim);

	           quantidadeLinhas = quantidadeLinhasMetade1 + quantidadeLinhasMetade2;
		 
		} else {
			System.out.println("Limite OK!!!");
		 
		}
		return quantidadeLinhas;
	}

	private static Long consultarLinhas(LocalDateTime inicio, LocalDateTime fim) {

		int quantidadeLinhas =    (int) (Math.random() * 20000);
		System.out.println("quantidade de linhas>>" + quantidadeLinhas);
		return (long) quantidadeLinhas;
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
