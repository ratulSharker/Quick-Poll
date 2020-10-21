package com.funlab.quickpoll.exception;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.funlab.quickpoll.dto.ApiResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException rnfe, HttpServletRequest req) {
		ApiResponse<?> response = new ApiResponse<>();
		
		HashMap<String, Object> meta = new HashMap<>();
		meta.put("timestamp", new Date().getTime());
		meta.put("message", "Resource not found");
		meta.put("dev-msg", rnfe.getClass().getName());
		meta.put("path", req.getRequestURI());
		
		response.setMeta(meta);
		
		return new ResponseEntity(meta, HttpStatus.NOT_FOUND);	
	}
}
