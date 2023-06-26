package br.gov.sp.audit.auditelasticsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.audit.auditelasticsearch.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
