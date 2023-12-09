package com.server.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.server.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

   // resourse not found exception
   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandlerr(ResourceNotFoundException ex) {
      String message = ex.getMessage();
      ApiResponse apiResponse = new ApiResponse(message, false);
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
   }

   // path not found exception
   @ExceptionHandler(NoHandlerFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
      return new ResponseEntity<>("Path not found", HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(NoSuchElementException.class)
   public ResponseEntity<ApiResponse> handleNoSuchElementException(NoSuchElementException ex) {
      String message = ex.getMessage();
      ApiResponse apiResponse = new ApiResponse(message, false);
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
   }

}
