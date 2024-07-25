package com.lbg.hackathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleResourceException(ResourceNotFoundException ex, WebRequest req) {

        CustomErrorMessage msg = new CustomErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(msg, msg.getStatus());

    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorMessage> handleCustomException(CustomException ex, WebRequest req) {

        CustomErrorMessage msg = new CustomErrorMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<CustomErrorMessage>(msg, msg.getStatus());

    }

}
