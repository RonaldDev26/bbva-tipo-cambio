package com.bbva.ms.tipocambio.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bbva.ms.tipocambio.business.dto.ErrorDetalle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorDetalle> manejarAppException(AppException exception , WebRequest webRequest) {
		ErrorDetalle errorDetalle =  new ErrorDetalle(new Date()
				, exception.getMensaje()
				, webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalle,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
