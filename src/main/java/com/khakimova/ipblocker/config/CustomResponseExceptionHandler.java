package com.khakimova.ipblocker.config;

import com.khakimova.ipblocker.exception.NumberOfRequestsExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NumberOfRequestsExceededException.class)
    public ResponseEntity<Object> handleNumberOfRequestsExceededException(
            NumberOfRequestsExceededException exception, WebRequest request) {
        return new ResponseEntity<>("", BAD_GATEWAY);
    }
}