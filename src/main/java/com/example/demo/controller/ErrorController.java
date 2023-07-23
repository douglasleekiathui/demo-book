package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.error.DemoApplicationException;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, DemoApplicationException.class})
	protected ResponseEntity<Object> handleApplicationError(Exception e, WebRequest request) {
		return ResponseEntity.badRequest().body(e.getClass() + " : " + e.getMessage());
	}
	
	@ExceptionHandler
	protected ResponseEntity<Object> handleError(Exception e, WebRequest request) {
		return ResponseEntity.internalServerError().body(e.getClass() + " : " + e.getMessage());
	}
}
