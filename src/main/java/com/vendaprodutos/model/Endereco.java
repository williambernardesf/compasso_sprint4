package com.vendaprodutos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;
import com.vendaprodutos.model.dto.EnderecoDto;

import lombok.Data;

@Data
@Entity
@Table(name = "Endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	private String pais;

	@NotNull
	@NotBlank
	private String estado;

	@NotNull
	@NotBlank
	private String cidade;

	@NotNull
	@NotBlank
	private int cep;

	@NotNull
	@NotBlank
	private String rua;

	@ManyToOne
	private Pessoa pessoa;

	public static Endereco from(EnderecoDto enderecoDto) {
		Endereco endereco = new Endereco();
		endereco.setPais(enderecoDto.getPais());
		endereco.setEstado(enderecoDto.getEstado());
		endereco.setCidade(enderecoDto.getCidade());
		endereco.setCep(enderecoDto.getCep());
		endereco.setRua(enderecoDto.getRua());
		return endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}