package com.vendaprodutos.controller;

import com.vendaprodutos.model.Pessoa;
import com.vendaprodutos.model.dto.EnderecoDto;
import com.vendaprodutos.model.dto.PessoaDto;
import com.vendaprodutos.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/protected/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDto> addPessoa(@RequestBody final PessoaDto pessoaDto){
        Pessoa pessoa = pessoaService.addPessoa(Pessoa.from(pessoaDto));
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> getPessoas(){
        List<Pessoa> pessoas = pessoaService.getPessoas();
        List<PessoaDto> pessoasDto = pessoas.stream().map(PessoaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(pessoasDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PessoaDto> getPessoa(@PathVariable final Long id){
        Pessoa pessoas = pessoaService.getPessoa(id);
        return new ResponseEntity<>(PessoaDto.from(pessoas), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PessoaDto> deletePessoa(@PathVariable final Long id){
        Pessoa pessoa = pessoaService.deletePessoa(id);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PessoaDto> editPessoa(@PathVariable final Long id,
                                            @RequestBody final PessoaDto pessoaDto){
        Pessoa pessoa = pessoaService.editPessoa(id, Pessoa.from(pessoaDto));
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @PostMapping(value = "{pessoaId}/enderecos/{enderecoId}/add")
    public ResponseEntity<PessoaDto> addEnderecoToPessoa(@PathVariable final Long pessoaId,
                                                 @PathVariable final Long enderecoId){
        Pessoa pessoa = pessoaService.addEnderecoToPessoa(pessoaId, enderecoId);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @DeleteMapping(value = "{pessoaId}/enderecos/{enderecoId}/remove")
    public ResponseEntity<PessoaDto> removeEnderecoFromPessoa(@PathVariable final Long pessoaId,
                                                 @PathVariable final Long enderecoId){
        Pessoa pessoa = pessoaService.removeEnderecoFromPessoa(pessoaId, enderecoId);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }
}
