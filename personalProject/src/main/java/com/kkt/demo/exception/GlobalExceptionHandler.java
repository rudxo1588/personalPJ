package com.kkt.demo.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handelMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<>();
		result.put("reason", e.getAllErrors().get(0).getDefaultMessage());

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(BindException.class)
	protected ResponseEntity<Object> handBindException(BindException e, HttpServletRequest request) {
		String msg = e.getAllErrors().get(0).getDefaultMessage();
		Map<String, Object> result = new HashMap<>();
		result.put("reason", msg);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
