package br.com.hugodev.helpdesk.controller;

import br.com.hugodev.helpdesk.exception.BusinessException;
import br.com.hugodev.helpdesk.exception.NotFoundException;
import br.com.hugodev.helpdesk.exception.StandartError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<StandartError> NotFoundHandler(NotFoundException e){
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<StandartError> BusinessRuleHandler(BusinessException e){
        StandartError error = new StandartError(HttpStatus.CONFLICT.value(), e.getMessage(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
