package com.example.fightbook.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResult> handle(BusinessException ex) {
    ErrorResult errorResult = new ErrorResult(LocalDateTime.now().toString(), ex.getMessage());
    return ResponseEntity.status(ex.getStatus()).body(errorResult);
  }


}
