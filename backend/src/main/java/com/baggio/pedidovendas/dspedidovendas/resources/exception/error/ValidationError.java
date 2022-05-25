package com.baggio.pedidovendas.dspedidovendas.resources.exception.error;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> fieldMessageList = new ArrayList<FieldMessage>();

	public ValidationError(Integer status, String msg) {
		super(status, msg);
	}

	public List<FieldMessage> getErrors() {
		return fieldMessageList;
	}

	public void addError(String fieldName, String message) {
		fieldMessageList.add(new FieldMessage(fieldName, message));
	}
}
