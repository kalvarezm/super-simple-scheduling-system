package com.thinknear.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.thinknear.model.Error;
import com.thinknear.service.exception.CustomBadRequestException;
import com.thinknear.service.exception.CustomNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> exceptionHandler(Exception ex) {
		LOG.error(ex.getMessage(), ex);
		Error error = new Error();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<Error> badRequest(CustomNotFoundException ex) {
		LOG.error(ex.getMessage(), ex);
		Error error = new Error();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomBadRequestException.class)
	public ResponseEntity<Error> notFound(CustomBadRequestException ex) {
		LOG.error(ex.getMessage(), ex);
		Error error = new Error();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
