package com.condueet.pension.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BvnDoesNotMatchException.class)
    public Map<String, Object> handleObjectInvalid(BvnDoesNotMatchException ex) {

        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", false);
        errorMap.put("code", HttpStatus.BAD_REQUEST.value());
        errorMap.put("message", ex.getMessage());

        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GenericException.class)
    public Map<String, Object> handleObjectInvalid(GenericException ex) {

        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", false);
        errorMap.put("code", HttpStatus.BAD_REQUEST.value());
        errorMap.put("message", ex.getMessage());

        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NINDoesNotMatchException.class)
    public Map<String, Object> handleObjectInvalid(NINDoesNotMatchException ex){
        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", false);
        errorMap.put("code", HttpStatus.BAD_REQUEST.value());
        errorMap.put("message", ex.getMessage());

        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserExistsException.class)
    public Map<String, Object> handleExistingUser(UserExistsException ex) {

        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", false);
        errorMap.put("code", HttpStatus.BAD_REQUEST.value());
        errorMap.put("message", ex.getMessage());

        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueEnteredException.class)
    public Map<String, Object> handleExistingUser(InvalidValueEnteredException ex) {

        Map<String, Object> errorMap = new HashMap<>();

        errorMap.put("status", false);
        errorMap.put("code", HttpStatus.BAD_REQUEST.value());
        errorMap.put("message", ex.getMessage());

        return errorMap;
    }
}
