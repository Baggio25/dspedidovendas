package com.baggio.pedidovendas.dspedidovendas.service.generic;

import java.util.List;

public interface GenericService<DTO> {

	List<DTO> findAll();

	DTO find(Long id);

	DTO create(DTO entity);

	DTO update(DTO entity);

	void deleteById(Long id);
}