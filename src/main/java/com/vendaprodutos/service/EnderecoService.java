package com.vendaprodutos.service;

import com.vendaprodutos.model.Endereco;
import com.vendaprodutos.model.exception.ItemNotFoundException;
import com.vendaprodutos.repository.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco addEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> getEnderecos(){
        return StreamSupport
                .stream(enderecoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Endereco getEndereco(Long id){
        return enderecoRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(id));
    }

    public Endereco deleteEndereco(Long id){
        Endereco endereco = getEndereco(id);
        enderecoRepository.delete(endereco);
        return endereco;
    }

    @Transactional
    public Endereco editEndereco(Long id, Endereco endereco){
        Endereco enderecoToEdit = getEndereco(id);
        enderecoToEdit.setPais(endereco.getPais());
        enderecoToEdit.setEstado(endereco.getEstado());
        enderecoToEdit.setCidade(endereco.getCidade());
        enderecoToEdit.setCep(endereco.getCep());
        enderecoToEdit.setRua(endereco.getRua());
        return enderecoToEdit;
    }
}
