package com.baggio.pedidovendas.dspedidovendas.service.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.repository.CategoriaRepository;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;

	@Transactional(readOnly = true)
	public Categoria find(Long id) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		Categoria categoria = categoriaOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

		return categoria;
	}

	@Transactional
	public Categoria create(Categoria categoria) {
		categoria = categoriaRepository.save(categoria);
		return categoria;
	}

	@Transactional
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		categoria = categoriaRepository.save(categoria);
		return categoria;
	}

}
