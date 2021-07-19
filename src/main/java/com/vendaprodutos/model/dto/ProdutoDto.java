package com.vendaprodutos.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.vendaprodutos.model.Produto;

public class ProdutoDto {

	private Long id;
	private String descricao;
	private Double precoUnitario;

	public static ProdutoDto from(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setId(produto.getId());
		produtoDto.setDescricao(produto.getDescricao());
		produtoDto.setPrecoUnitario(produto.getPrecoUnitario());
		return produtoDto;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
}