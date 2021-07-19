package com.vendaprodutos.controller;

import com.vendaprodutos.model.Endereco;
import com.vendaprodutos.model.dto.EnderecoDto;
import com.vendaprodutos.service.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/protected/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> addEndereco(@RequestBody final EnderecoDto enderecoDto){
        Endereco endereco = enderecoService.addEndereco(Endereco.from(enderecoDto));
        return new ResponseEntity<>(EnderecoDto.from(endereco), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> getEnderecos(){
        List<Endereco> enderecos = enderecoService.getEnderecos();
        List<EnderecoDto> enderecosDto = enderecos.stream().map(EnderecoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(enderecosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EnderecoDto> getEndereco(@PathVariable final Long id){
        Endereco endereco = enderecoService.getEndereco(id);
        return new ResponseEntity<>(EnderecoDto.from(endereco), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EnderecoDto> deleteEndereco(@PathVariable final Long id){
        Endereco endereco = enderecoService.deleteEndereco(id);
        return new ResponseEntity<>(EnderecoDto.from(endereco), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EnderecoDto> editEndereco(@PathVariable final Long id,
                                            @RequestBody final EnderecoDto enderecoDto){
        Endereco editedEndereco = enderecoService.editEndereco(id, Endereco.from(enderecoDto));
        return new ResponseEntity<>(EnderecoDto.from(editedEndereco), HttpStatus.OK);
    }
}
