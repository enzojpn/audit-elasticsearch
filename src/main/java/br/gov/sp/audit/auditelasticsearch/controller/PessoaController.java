package br.gov.sp.audit.auditelasticsearch.controller;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import br.gov.sp.audit.auditelasticsearch.model.Source;
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

	@GetMapping("/resto")
	public String resto() {

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "https://random-data-api.com/api/v2/banks";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		System.out.println(response.getBody());

		return response.getBody();
	}

	
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile() {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8084/teste";
		ResponseEntity<String> jsonResponse = restTemplate.getForEntity(fooResourceUrl, String.class);
		System.out.println(jsonResponse.getBody());
		
		
		byte[] jsonData = jsonResponse.getBody().getBytes(StandardCharsets.UTF_8);
		ByteArrayResource resource = new ByteArrayResource(jsonData);

		
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.txt");
	    return ResponseEntity.ok()
	            .headers(headers)
	            .body(resource);
	}

	@GetMapping("/rest-audit")
	public String restAudit() throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8084/teste";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		System.out.println(response.getBody());

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response.getBody());

		if (jsonNode.isArray()) {
		  JsonNode firstObject = jsonNode.get(0);
		  JsonNode sourceNode = firstObject.get("source");
		  
		  if (sourceNode != null) {
		    Source sourceObject = objectMapper.treeToValue(sourceNode, Source.class);
		    // Faça algo com o objeto "sourceObject"
		    System.out.println("Source >>>" + sourceObject.getTela() + sourceObject.getDataHoraFull());
		  }
		}
  
		return response.getBody();
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
		filter.setRange(ranges);
		bool.setFilter(filter);

		Query q = new Query();
		q.setBool(bool);
		auditQuery.setQuery(q);

		return auditQuery;

	}
}
