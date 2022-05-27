package com.baggio.pedidovendas.dspedidovendas.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baggio.pedidovendas.dspedidovendas.domain.Cliente;
import com.baggio.pedidovendas.dspedidovendas.dto.ClienteDTO;
import com.baggio.pedidovendas.dspedidovendas.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Long id) {
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(Pageable pageable) {
		Page<Cliente> clientePage = clienteService.findPaged(pageable);
		Page<ClienteDTO> clientePageDTO = clientePage.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok(clientePageDTO);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.fromDTO(clienteDTO); 
		cliente = clienteService.create(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
		Cliente cliente = clienteService.fromDTO(clienteDTO);	
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
