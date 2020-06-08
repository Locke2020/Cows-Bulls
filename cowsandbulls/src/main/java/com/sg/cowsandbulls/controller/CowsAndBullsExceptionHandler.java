/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cowsandbulls.controller;

import com.sg.cowsandbulls.controller.Error;
import com.sg.cowsandbulls.service.NumberIsNotUniqueException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author davidsteffes
 */
@ControllerAdvice
@RestController
public class CowsAndBullsExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final String CONSTRAINT_MESSAGE = "Your number is not unique, "
            + "so it was not recorded as a valid guess.";
    
    @ExceptionHandler(NumberIsNotUniqueException.class)
    public final ResponseEntity<Error> handleSqlException(
            NumberIsNotUniqueException ex,
            WebRequest request) {
        
        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
        
    }
    
}
