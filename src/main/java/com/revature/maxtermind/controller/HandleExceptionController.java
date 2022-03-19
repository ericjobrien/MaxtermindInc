package com.revature.maxtermind.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class HandleExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
