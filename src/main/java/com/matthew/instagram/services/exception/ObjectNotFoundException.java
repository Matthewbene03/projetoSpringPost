package com.matthew.instagram.services.exception;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg, Integer id) {
		super(msg + " - ID: " + id);
	}

}
