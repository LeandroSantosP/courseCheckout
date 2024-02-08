package com.courseapi.infra.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExectionsHandler extends ResponseEntityExceptionHandler {

    private record ErrorFormat(String type, HttpStatus erroCode, String message) {
    }

    @ExceptionHandler(CustomError.class)
    private ResponseEntity<ErrorFormat> handlerCustomError(CustomError ex) {
        ErrorFormat errorFormat = new ErrorFormat(ex.getType(), ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorFormat);
    }

}
