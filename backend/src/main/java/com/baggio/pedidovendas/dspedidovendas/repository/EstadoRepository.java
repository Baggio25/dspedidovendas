package com.baggio.pedidovendas.dspedidovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baggio.pedidovendas.dspedidovendas.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
