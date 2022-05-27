package com.baggio.pedidovendas.dspedidovendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baggio.pedidovendas.dspedidovendas.domain.Cliente;
import com.baggio.pedidovendas.dspedidovendas.dto.ClienteDTO;
import com.baggio.pedidovendas.dspedidovendas.repository.ClienteRepository;
import com.baggio.pedidovendas.dspedidovendas.service.exception.DataIntegrityException;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Long id) {
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		return clienteOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional(readOnly = true)
	public Page<Cliente> findPaged(Pageable pageable) {
		Page<Cliente> page = clienteRepository.findAll(pageable);
		return page;
	}

	@Transactional
	public Cliente create(Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		return cliente;
	}

	@Transactional
	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);		
		
		return clienteRepository.save(newCliente);
	}


	public void delete(Long id) {
		try {
			find(id);
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente porque possui pedidos");
		}
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
}
