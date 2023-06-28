package br.gov.sp.audit.auditelasticsearch.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.gov.sp.audit.auditelasticsearch.model.AuditQuery;
import br.gov.sp.audit.auditelasticsearch.model.Boleando;
import br.gov.sp.audit.auditelasticsearch.model.DataHoraFull;
import br.gov.sp.audit.auditelasticsearch.model.Filter;
import br.gov.sp.audit.auditelasticsearch.model.Must;
import br.gov.sp.audit.auditelasticsearch.model.Pessoa;
import br.gov.sp.audit.auditelasticsearch.model.Query;
import br.gov.sp.audit.auditelasticsearch.model.Range;
import br.gov.sp.audit.auditelasticsearch.model.Tems;
import br.gov.sp.audit.auditelasticsearch.repository.PessoaRepository;

@RestController
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping("/pessoa")
	public Pessoa pessoas() {
		return pessoaRepository.findById(12312L).get();
	}

	@GetMapping("/pessoas")
	public List<Pessoa> pessoaList() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/query")
	public AuditQuery query() {

		AuditQuery auditQuery = new AuditQuery();

		auditQuery.setSize(10000L);

		Tems terms = new Tems();

		List<String> transacoes = new ArrayList<String>();
		transacoes.add("TCO1");
		terms.setTransacao(transacoes);

		Boleando bool = new Boleando();
		Must must = new Must();
		bool.setMust(must);
		must.setTerms(terms);

		Range range = new Range();
		DataHoraFull dataHoraFull = new DataHoraFull();

		System.out.println(LocalDateTime.now().toString() + "<<<<<<<<<<<<<<<<<TESTE");

		String dataOriginal = LocalDateTime.now().toString();
		DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
		LocalDateTime data = LocalDateTime.parse(dataOriginal, formatoEntrada);

		DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
		String dataFormatada = data.format(formatoSaida);

		System.out.println(dataFormatada + "<<<<<<<<<<<<<<<<<TESTE 2");
		dataHoraFull.setGte(dataFormatada);
		range.setDataHoraFull(dataHoraFull);
		Filter filter = new Filter();

		List<Range> ranges = new ArrayList<Range>();
		ranges.add(range);
		filter.setRanges(ranges);
		bool.setFilter(filter);

		Query q = new Query();
		q.setBool(bool);
		auditQuery.setQuery(q);

		return auditQuery;

	}
}
