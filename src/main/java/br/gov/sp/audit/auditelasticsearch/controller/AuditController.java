package br.gov.sp.audit.auditelasticsearch.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.audit.auditelasticsearch.model.Audit;
import br.gov.sp.audit.auditelasticsearch.service.AuditService;

@Controller
public class AuditController {
	@Autowired
	AuditService auditService;

	@GetMapping("/audit")
	public String home() {
		return "audit";
	}

	@PostMapping("/audit")
	public String create(Audit audit) {
		System.out.println("iss" + audit.getQuantidade() + "--" + audit.getTrx() + "  - " + audit.getDataInicio()
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
