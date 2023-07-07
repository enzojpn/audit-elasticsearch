package br.gov.sp.audit.auditelasticsearch.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.boot.model.source.spi.JoinedSubclassEntitySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.audit.auditelasticsearch.model.Audit;
import br.gov.sp.audit.auditelasticsearch.model.AuditQuery;
import br.gov.sp.audit.auditelasticsearch.model.Pessoa;
import br.gov.sp.audit.auditelasticsearch.repository.PessoaRepository;
import br.gov.sp.audit.auditelasticsearch.service.AuditService;

@Controller
public class AuditController {
	@Autowired
	AuditService auditService;

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping("/audit")
	public String home() {
		return "audit";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
  
	@GetMapping("/load")
	public String load() {

		System.out.println("inic d load > " + LocalTime.now());
		for (int i = 0; i < 2000 ; i++) {

		Pessoa p1 = new Pessoa();

		Pessoa p2 = new Pessoa();
		Pessoa p3 = new Pessoa();
		Pessoa p4 = new Pessoa();

		double r = Math.random();
		p1.setNome("ak junior");
		p1.setDesc("number = " + r);
		
		p2.setNome("nak  medeiros");
		p2.setDesc("placa = " + r);
		
		p3.setNome("wilsom kondo");
		p3.setDesc("renavam = " + r);
		
		p4.setNome("nukem man");
		p4.setDesc("chassi= " + r); 

			pessoaRepository.save(p1);
			pessoaRepository.save(p2); 
			pessoaRepository.save(p3);
			pessoaRepository.save(p4);
		}
		System.out.println("fim do load > " + LocalTime.now());
		return "audit";
	}

	@PostMapping("/audit")
	public String create(Audit audit) {
		System.out.println("iss" + audit.getQuantidade() + "--" + audit.getTransacao() + "  - " + audit.getDataInicio()
				+ "  - " + audit.getDataFim());

		LocalDateTime dataIni = convertDateToLocalDateTime(audit.getDataInicio());
		LocalDateTime dataFim = convertDateToLocalDateTime(audit.getDataFim());

		auditService.verificaLimiteDeConsulta(dataIni, dataFim);

		return "/list";
	}

	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/list");

		return mv;
	}

	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zoneId);
	}

}
