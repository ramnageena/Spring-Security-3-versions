package com.expense_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    //it will work when no item is available
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ExpenseNotFoundException e) {
        String message = e.getMessage();
        APIResponse response = new APIResponse(HttpStatus.NOT_FOUND.value(), message, new Date());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //it will work when we will use type mismatch while getting the item
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = e.getMessage();
        APIResponse response = new APIResponse(HttpStatus.BAD_REQUEST.value(), message, new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    //General Handler exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGeneralException(Exception e) {
        String message = e.getMessage();
        APIResponse response = new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, new Date());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //it will work with the Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errorMessage = e.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        response.put("message", errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
