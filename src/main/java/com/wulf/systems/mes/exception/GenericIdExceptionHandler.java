package com.wulf.systems.mes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class GenericIdExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<?> handleGenericIdException(GenericIdException ex, WebRequest request){
        GenericIdExceptionResponse response = new GenericIdExceptionResponse(ex.getMessage());
        return new ResponseEntity<GenericIdExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
