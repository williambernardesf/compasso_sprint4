package com.vendaprodutos.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vendaprodutos.model.Pedido;

public class PedidoDto {
	
	private Long id;
	private Double total;
	

	private List<ProdutoDto> produtosDto = new ArrayList<>();
	
	public static PedidoDto from(Pedido pedido) {
		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setId(pedido.getId());
		pedidoDto.setTotal(pedido.getTotal());
		pedidoDto.setProdutosDto(pedido.getProdutos().stream().map(ProdutoDto::from).collect(Collectors.toList()));
		return pedidoDto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<ProdutoDto> getProdutosDto() {
		return produtosDto;
	}
	public void setProdutosDto(List<ProdutoDto> produtosDto) {
		this.produtosDto = produtosDto;
	}
}