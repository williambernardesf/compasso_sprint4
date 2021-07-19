package com.vendaprodutos.service;

import com.vendaprodutos.model.Endereco;
import com.vendaprodutos.model.Pessoa;
import com.vendaprodutos.model.exception.CartNotFoundException;
import com.vendaprodutos.model.exception.ItemIsAlreadyAssignedException;
import com.vendaprodutos.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoService enderecoService;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    public Pessoa addPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getPessoas(){
        return StreamSupport
                .stream(pessoaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Pessoa getPessoa(Long id){
        return pessoaRepository.findById(id).orElseThrow(() ->
                new CartNotFoundException(id));
    }

    public Pessoa deletePessoa(Long id){
        Pessoa pessoa = getPessoa(id);
        pessoaRepository.delete(pessoa);
        return pessoa;
    }

    @Transactional
    public Pessoa editPessoa(Long id, Pessoa pessoa){
        Pessoa pessoaToEdit = getPessoa(id);
        pessoaToEdit.setCpf(pessoa.getCpf());
        pessoaToEdit.setNome(pessoa.getNome());
        pessoaToEdit.setSalario(pessoa.getSalario());
        pessoaToEdit.setSexo(pessoa.getSexo());
        return pessoaToEdit;
    }

    @Transactional
    public Pessoa addEnderecoToPessoa(Long pessoaId, Long enderecoId){
        Pessoa pessoa = getPessoa(pessoaId);
        Endereco endereco = enderecoService.getEndereco(enderecoId);
        if(Objects.nonNull(endereco.getPessoa())){
            throw new ItemIsAlreadyAssignedException(enderecoId,
                    endereco.getPessoa().getId());
        }
        pessoa.addEndereco(endereco);
        endereco.setPessoa(pessoa);
        return pessoa;
    }

    @Transactional
    public Pessoa removeEnderecoFromPessoa(Long pessoaId, Long enderecoId){
        Pessoa pessoa = getPessoa(pessoaId);
        Endereco endereco = enderecoService.getEndereco(enderecoId);
        pessoa.removeEndereco(endereco);
        return pessoa;
    }
}
