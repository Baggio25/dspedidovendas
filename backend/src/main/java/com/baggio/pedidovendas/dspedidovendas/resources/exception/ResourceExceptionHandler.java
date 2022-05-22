package com.baggio.pedidovendas.dspedidovendas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.baggio.pedidovendas.dspedidovendas.resources.exception.error.StandardError;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	private static Integer STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.value();

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(STATUS_NOT_FOUND, e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(STATUS_NOT_FOUND).body(err);
	}

}
