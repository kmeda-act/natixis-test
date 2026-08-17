package com.natixistest.playlist.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.natixistest.playlist.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
		ErrorResponseDTO errorResponseDto = new ErrorResponseDTO(
			request.getDescription(false),
			HttpStatus.NOT_FOUND,
			exception.getMessage(),
			LocalDateTime.now()
		);
		
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}
}
