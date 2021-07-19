package com.vendaprodutos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendaprodutos.model.Produto;
import com.vendaprodutos.model.dto.ProdutoDto;
import com.vendaprodutos.service.ProdutoService;

@RestController
@RequestMapping("/protected/produtos")
public class ProdutoController {
	
	private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> addProduto(@RequestBody final ProdutoDto produtoDto){
        Produto produto = produtoService.addProduto(Produto.from(produtoDto));
        return new ResponseEntity<>(ProdutoDto.from(produto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getProdutos(){
        List<Produto> produtos = produtoService.getProdutos();
        List<ProdutoDto> produtosDto = produtos.stream().map(ProdutoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(produtosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProdutoDto> getProduto(@PathVariable final Long id){
        Produto produtos = produtoService.getProduto(id);
        return new ResponseEntity<>(ProdutoDto.from(produtos), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ProdutoDto> deleteProduto(@PathVariable final Long id){
    	Produto produto = produtoService.deleteProduto(id);
        return new ResponseEntity<>(ProdutoDto.from(produto), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ProdutoDto> editProduto(@PathVariable final Long id,
                                            @RequestBody final ProdutoDto produtoDto){
    	Produto produto = produtoService.editProduto(id, Produto.from(produtoDto));
        return new ResponseEntity<>(ProdutoDto.from(produto), HttpStatus.OK);
    }
}