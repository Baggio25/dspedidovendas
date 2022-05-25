package com.baggio.pedidovendas.dspedidovendas.dto;

import java.io.Serializable;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
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
	
}
