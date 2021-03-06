package com.baggio.pedidovendas.dspedidovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.dto.CategoriaDTO;
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
	public Page<Categoria> findPaged(Pageable pageable) {
		Page<Categoria> page = categoriaRepository.findAll(pageable);
		return page;
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
		Categoria newCategoria = find(categoria.getId());
		updateData(newCategoria, categoria);
		return categoriaRepository.save(newCategoria);
	}

	public void delete(Long id) {
		try {
			find(id);
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria porque possui produtos");
		}
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}

	private void updateData(Categoria newCategoria, Categoria categoria) {
		newCategoria.setNome(categoria.getNome());
	}
	
}
