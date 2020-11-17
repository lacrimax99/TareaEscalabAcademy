package com.escalab.academyappbackend.exception;

public class ExceptionsUtil extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExceptionsUtil(String mensaje) {
		super(mensaje);
	}

}
