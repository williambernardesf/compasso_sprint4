package com.vendaprodutos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vendaprodutos.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
}
