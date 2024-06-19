package com.matthew.instagram.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matthew.instagram.services.exception.DatabaseException;
import com.matthew.instagram.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest resquest){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError se = new StandardError(Instant.now(), status.value(), "Não tem esse id!", resquest.getRequestURI(), e.getMessage());
		return ResponseEntity.status(status).body(se);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DatabaseException e, HttpServletRequest resquest){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError se = new StandardError(Instant.now(), status.value(), "Não pode deletar", resquest.getRequestURI(), e.getMessage());
		return ResponseEntity.status(status).body(se);
	}
}
