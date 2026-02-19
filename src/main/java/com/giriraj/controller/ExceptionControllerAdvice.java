package com.giriraj.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.giriraj.dto.ErrorDTO;
import com.giriraj.exception.OwnerNotFoundException;
import com.giriraj.exception.PetNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(OwnerNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleOwnerNotFoundException(OwnerNotFoundException exception) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setError(HttpStatus.NOT_FOUND);
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setTimestamp(LocalDateTime.now());

		// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
	}

	@ExceptionHandler // (HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorDTO> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException exception) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setError(HttpStatus.METHOD_NOT_ALLOWED);
		errorDTO.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		errorDTO.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleSQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException exception) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setError(HttpStatus.CONFLICT);
		errorDTO.setStatus(HttpStatus.CONFLICT.value());
		errorDTO.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleException(Exception exception) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setError(HttpStatus.INTERNAL_SERVER_ERROR);
		errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDTO.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
	}

	@ExceptionHandler(PetNotFoundException.class)
	public ResponseEntity<ErrorDTO> handlePetNotFoundException(PetNotFoundException exception) {

		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setError(HttpStatus.NOT_FOUND);
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(errorDTO.getError()).body(errorDTO);
	}

}
