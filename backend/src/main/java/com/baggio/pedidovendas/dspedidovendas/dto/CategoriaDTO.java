package com.baggio.pedidovendas.dspedidovendas.dto;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;

public class CategoriaDTO {

	private Long id;
	private String nome;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
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