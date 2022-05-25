package com.baggio.pedidovendas.dspedidovendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.repository.CategoriaRepository;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;

	public Categoria find(Long id) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		return categoriaOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
