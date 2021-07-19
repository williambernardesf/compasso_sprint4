package com.vendaprodutos.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendaprodutos.model.Produto;
import com.vendaprodutos.model.exception.ItemNotFoundException;
import com.vendaprodutos.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto addProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> getProdutos(){
        return StreamSupport
                .stream(produtoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Produto getProduto(Long id){
        return produtoRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(id));
    }

    public Produto deleteProduto(Long id){
    	Produto produto = getProduto(id);
        produtoRepository.delete(produto);
        return produto;
    }

    @Transactional
    public Produto editProduto(Long id, Produto produto){
    	Produto produtoToEdit = getProduto(id);
    	produtoToEdit.setDescricao(produto.getDescricao());
    	produtoToEdit.setPrecoUnitario(produto.getPrecoUnitario());

        return produtoToEdit;
    }
}