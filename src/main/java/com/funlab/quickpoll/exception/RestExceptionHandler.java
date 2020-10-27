package com.funlab.quickpoll.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.funlab.quickpoll.dto.CustomApiResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException rnfe, HttpServletRequest req) {
		CustomApiResponse<?> response = new CustomApiResponse<>();

		Map<String, Object> meta = new HashMap<>();
		meta.put("timestamp", new Date().getTime());
		meta.put("message", messageSource.getMessage("ResourceNotFoundError", null, req.getLocale()));
		meta.put("dev-msg", rnfe.getClass().getName());
		meta.put("path", req.getRequestURI());

		response.setMeta(meta);

		return new ResponseEntity<CustomApiResponse<?>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomApiResponse<Void>> handleValidationError(MethodArgumentNotValidException manve,
			HttpServletRequest req) {
		CustomApiResponse<Void> response = new CustomApiResponse<>();

		Map<String, Object> meta = new HashMap<>();
		meta.put("timestamp", new Date().getTime());
		meta.put("path", req.getRequestURI());
		meta.put("message", messageSource.getMessage("ValidationError", null, req.getLocale()));

		StringBuilder stringBuilder = new StringBuilder();
		for (FieldError fieldError : manve.getBindingResult().getFieldErrors()) {
			stringBuilder.append("`" + fieldError.getField() + "` " + fieldError.getDefaultMessage() + ";");
		}
		meta.put("dev-msg", stringBuilder.toString());

		response.setMeta(meta);
		return new ResponseEntity<CustomApiResponse<Void>>(response, HttpStatus.BAD_REQUEST);
	}

	// Investigate why `UsernameNotFoundException` is being caught by the ControllerAdvice
//	@ExceptionHandler(UsernameNotFoundException.class)
//	public ResponseEntity<CustomApiResponse<Void>> handleUserNotFound(UsernameNotFoundException unfe,
//			HttpServletRequest req) {
//
//		CustomApiResponse<Void> response = new CustomApiResponse<>();
//
//		Map<String, Object> meta = new HashMap<String, Object>();
//		meta.put("timestamp", new Date().getTime());
//		meta.put("path", req.getRequestURI());
//		meta.put("message", unfe.getMessage());
//
//		response.setMeta(meta);
//
//		return new ResponseEntity<CustomApiResponse<Void>>(response, HttpStatus.BAD_REQUEST);
//	}
}
