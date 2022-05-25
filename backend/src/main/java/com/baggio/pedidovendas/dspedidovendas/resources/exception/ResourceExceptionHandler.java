package com.baggio.pedidovendas.dspedidovendas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.baggio.pedidovendas.dspedidovendas.resources.exception.error.StandardError;
import com.baggio.pedidovendas.dspedidovendas.resources.exception.error.ValidationError;
import com.baggio.pedidovendas.dspedidovendas.service.exception.DataIntegrityException;
import com.baggio.pedidovendas.dspedidovendas.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	private static Integer STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.value();
	private static Integer STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(STATUS_NOT_FOUND, e.getMessage());
		return ResponseEntity.status(STATUS_NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityFound(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError(STATUS_BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(STATUS_BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(STATUS_BAD_REQUEST, "Erro de validação: ");
		
		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			err.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(STATUS_BAD_REQUEST).body(err);
	}
	
}
