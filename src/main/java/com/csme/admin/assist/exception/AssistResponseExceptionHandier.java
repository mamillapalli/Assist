package com.csme.admin.assist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

@ControllerAdvice
@RestController
@Slf4j
public class AssistResponseExceptionHandier extends ResponseEntityExceptionHandler {

/*    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstantViolationException(final ConstraintViolationException ex) {
        log.info("in custom exception handler class");
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }
        AssistExceptionResponse assistExceptionResponse = new AssistExceptionResponse(new Date(), ex.getMessage(), message.toString());
        return new ResponseEntity(assistExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("method argument not valid exception");
        AssistExceptionResponse assistExceptionResponse = new AssistExceptionResponse(new Date(), ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity(assistExceptionResponse,HttpStatus.BAD_REQUEST);
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }






/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {

        AssistExceptionResponse assistExceptionResponse = new AssistExceptionResponse(new Date(), ex.getMessage(), ex.getAllErrors().toString());
        return new ResponseEntity(assistExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/




/*   @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex,  WebRequest request) {

       log.info("in custom exception handler class");

        AssistExceptionResponse assistExceptionResponse = new AssistExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity(assistExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

/*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,  WebRequest request) {

        log.info("in custom exception handler class");

        AssistExceptionResponse assistExceptionResponse = new AssistExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity(assistExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/


}

