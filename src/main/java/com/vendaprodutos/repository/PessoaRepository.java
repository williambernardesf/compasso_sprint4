package com.vendaprodutos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vendaprodutos.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
