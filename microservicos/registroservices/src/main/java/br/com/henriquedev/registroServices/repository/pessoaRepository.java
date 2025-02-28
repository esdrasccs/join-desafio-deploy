package br.com.henriquedev.registroServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquedev.registroServices.model.PessoaModel;

public interface pessoaRepository extends JpaRepository<PessoaModel, Long>{}
