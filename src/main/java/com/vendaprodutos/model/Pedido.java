package com.vendaprodutos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.vendaprodutos.model.dto.PedidoDto;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double total;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private List<Produto> produtos = new ArrayList<>();

	public static Pedido from(PedidoDto pedidoDto) {
		Pedido pedido = new Pedido();
		pedido.setTotal(pedidoDto.getTotal());
		return pedido;
	}
	
	public void addProduto(Produto produto) {
		produtos.add(produto);
	}

	public void removeProduto(Produto produto) {
		produtos.remove(produto);
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
