package com.vendaprodutos.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vendaprodutos.model.Pessoa;

import lombok.Data;

@Data
public class PessoaDto {
	private Long id;
	private String cpf;
	private String nome;
	private Double salario;
	private String sexo;
	private List<EnderecoDto> enderecosDto = new ArrayList<>();

	public static PessoaDto from(Pessoa pessoa) {
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto.setId(pessoa.getId());
		pessoaDto.setCpf(pessoa.getCpf());
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setSalario(pessoa.getSalario());
		pessoaDto.setSexo(pessoa.getSexo());
		pessoaDto.setEnderecosDto(pessoa.getEnderecos().stream().map(EnderecoDto::from).collect(Collectors.toList()));
		return pessoaDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<EnderecoDto> getEnderecosDto() {
		return enderecosDto;
	}

	public void setEnderecosDto(List<EnderecoDto> itemsDto) {
		this.enderecosDto = itemsDto;
	}
}