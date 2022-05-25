package com.baggio.pedidovendas.dspedidovendas.service.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.dto.CategoriaDTO;
import com.baggio.pedidovendas.dspedidovendas.repository.CategoriaRepository;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	public CategoriaRepository categoriaRepository;

	@Override
	public List<CategoriaDTO> findAll() {
		return null;
	}

	@Override
	public CategoriaDTO find(Long id) {
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
		Categoria categoria = categoriaOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaDTO.class.getName()));
		
		return new CategoriaDTO(categoria);
	}

	@Override
	public CategoriaDTO create(CategoriaDTO entity) {
		Categoria categoria = dtoToEntity(entity);
		categoria = categoriaRepository.save(categoria);
		return new CategoriaDTO(categoria);
	}

	@Override
	public CategoriaDTO update(CategoriaDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	private Categoria dtoToEntity(CategoriaDTO entity) {
		Categoria categoria = new Categoria(null, entity.getNome());
		return categoria;
	}
}
