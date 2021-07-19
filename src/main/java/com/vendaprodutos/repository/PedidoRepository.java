package com.vendaprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendaprodutos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}