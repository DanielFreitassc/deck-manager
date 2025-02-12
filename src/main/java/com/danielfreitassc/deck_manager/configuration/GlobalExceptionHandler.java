package com.danielfreitassc.deck_manager.configuration;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.deck_manager.dtos.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> errorResponse(ResponseStatusException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getReason());
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> errorResponse(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errorMessage);
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
}
