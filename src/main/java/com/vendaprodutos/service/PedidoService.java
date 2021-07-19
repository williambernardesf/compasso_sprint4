package com.vendaprodutos.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendaprodutos.model.Pedido;
import com.vendaprodutos.model.Produto;
import com.vendaprodutos.model.exception.CartNotFoundException;
import com.vendaprodutos.repository.PedidoRepository;

@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
    }

    public Pedido addPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getPedidos(){
        return StreamSupport
                .stream(pedidoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Pedido getPedido(Long id){
        return pedidoRepository.findById(id).orElseThrow(() ->
                new CartNotFoundException(id));
    }

    public Pedido deletePedido(Long id){
    	Pedido pedido = getPedido(id);
        pedidoRepository.delete(pedido);
        return pedido;
    }

    @Transactional
    public Pedido editPedido(Long id, Pedido pedido){
    	Pedido pedidoToEdit = getPedido(id);
    	pedidoToEdit.setTotal(pedido.getTotal());
        return pedidoToEdit;
    }

    @Transactional
    public Pedido addProdutoToPedido(Long pedidoId, Long produtoId){
        Pedido pedido = getPedido(pedidoId);
        Produto produto = produtoService.getProduto(produtoId);
        pedido.addProduto(produto);
        return pedido;
    }

    @Transactional
    public Pedido removeProdutoFromPedido(Long pedidoId, Long produtoId){
    	Pedido pedido = getPedido(pedidoId);
        Produto produto = produtoService.getProduto(produtoId);
        pedido.removeProduto(produto);
        return pedido;
    }
}