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

import com.vendaprodutos.model.Pedido;
import com.vendaprodutos.model.dto.PedidoDto;
import com.vendaprodutos.service.PedidoService;

@RestController
@RequestMapping("/protected/pedidos")
public class PedidoController {
	
	private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDto> addPedido(@RequestBody final PedidoDto pedidoDto){
        Pedido pedido = pedidoService.addPedido(Pedido.from(pedidoDto));
        return new ResponseEntity<>(PedidoDto.from(pedido), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> getPedidos(){
        List<Pedido> pedidos = pedidoService.getPedidos();
        List<PedidoDto> pedidosDto = pedidos.stream().map(PedidoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(pedidosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PedidoDto> getPedido(@PathVariable final Long id){
        Pedido pedido = pedidoService.getPedido(id);
        return new ResponseEntity<>(PedidoDto.from(pedido), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PedidoDto> deletePedido(@PathVariable final Long id){
        Pedido pedido = pedidoService.deletePedido(id);
        return new ResponseEntity<>(PedidoDto.from(pedido), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PedidoDto> editPedido(@PathVariable final Long id,
                                            @RequestBody final PedidoDto pedidoDto){
        Pedido pedido = pedidoService.editPedido(id, Pedido.from(pedidoDto));
        return new ResponseEntity<>(PedidoDto.from(pedido), HttpStatus.OK);
    }

    @PostMapping(value = "{pedidoId}/produtos/{produtoId}/add")
    public ResponseEntity<PedidoDto> addProdutoToPedido(@PathVariable final Long pedidoId,
                                                 @PathVariable final Long produtoId){
        Pedido pedido = pedidoService.addProdutoToPedido(pedidoId, produtoId);
        return new ResponseEntity<>(PedidoDto.from(pedido), HttpStatus.OK);
    }

    @DeleteMapping(value = "{pedidoId}/produtos/{produtoId}/remove")
    public ResponseEntity<PedidoDto> removeProdutoFromPedido(@PathVariable final Long pedidoId,
                                                 @PathVariable final Long produtoId){
    	Pedido pedido = pedidoService.removeProdutoFromPedido(pedidoId, produtoId);
        return new ResponseEntity<>(PedidoDto.from(pedido), HttpStatus.OK);
    }

}
