package com.br.joaovictor.picpaysimplificado.infrastructure.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.joaovictor.picpaysimplificado.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

   @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity treatingDuplicateEntity(DataIntegrityViolationException exception) {
      ExceptionDTO exceptionDTO = new ExceptionDTO("Usuario já cadastrado!", "400");
      return ResponseEntity.badRequest().body(exceptionDTO);
   }

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity treating404(EntityNotFoundException exception) {
      return ResponseEntity.notFound().build();
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity treatingGeneralException(Exception exception) {
      ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
      return ResponseEntity.internalServerError().body(exceptionDTO);
   }
}
