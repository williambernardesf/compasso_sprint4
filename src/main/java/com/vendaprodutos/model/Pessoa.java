package com.vendaprodutos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.vendaprodutos.model.dto.PessoaDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Pessoa")
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	@CPF
	private String cpf;

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@NotBlank
	private Double salario;

	@NotNull
	@NotBlank
	private String sexo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	private List<Endereco> enderecos = new ArrayList<>();
	
	public static Pessoa from(PessoaDto pessoaDto) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setSalario(pessoaDto.getSalario());
		pessoa.setSexo(pessoaDto.getSexo());
		return pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void addEndereco(Endereco endereco) {
		enderecos.add(endereco);
	}

	public void removeEndereco(Endereco endereco) {
		enderecos.remove(endereco);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
}