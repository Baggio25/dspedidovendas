package com.baggio.pedidovendas.dspedidovendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.domain.Pedido;
import com.baggio.pedidovendas.dspedidovendas.repository.PedidoRepository;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Long id) {
		Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
		return pedidoOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
}
