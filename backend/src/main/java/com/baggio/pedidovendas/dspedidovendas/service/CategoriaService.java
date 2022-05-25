package com.baggio.pedidovendas.dspedidovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.repository.CategoriaRepository;
import com.baggio.pedidovendas.dspedidovendas.service.exception.DataIntegrityException;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

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

	public void delete(Long id) {
		try {
			find(id);
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria porque possui produtos");
		}
	}

}
