package com.vendaprodutos.model.dto;

import com.vendaprodutos.model.Endereco;

import lombok.Data;

@Data
public class EnderecoDto {

	private Long id;
	private String pais;
	private String estado;
	private String cidade;
	private int cep;
	private String rua;

	public static EnderecoDto from(Endereco endereco) {
		EnderecoDto enderecoDto = new EnderecoDto();
		enderecoDto.setId(endereco.getId());
		enderecoDto.setPais(endereco.getPais());
		enderecoDto.setEstado(endereco.getEstado());
		enderecoDto.setCidade(endereco.getCidade());
		enderecoDto.setCep(endereco.getCep());
		enderecoDto.setRua(endereco.getRua());
		return enderecoDto;
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
}