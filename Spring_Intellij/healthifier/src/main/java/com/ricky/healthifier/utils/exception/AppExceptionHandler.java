package com.ricky.healthifier.utils.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleException(AppException ex) {

        CustomExceptionTemplate exceptionTemplate = new CustomExceptionTemplate(ex.getUiMessage(), ex.getExceptionLevel());

        return new ResponseEntity<>(exceptionTemplate, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
