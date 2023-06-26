package br.gov.sp.audit.auditelasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.audit.auditelasticsearch.model.Pessoa;
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

}
