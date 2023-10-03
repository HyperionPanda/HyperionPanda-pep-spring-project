package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

public class HandleException {

    int code;
    public HandleException (int errorCode){
        code = errorCode;
    }


    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleNotFound(RuntimeException ex) {
        return ex.getMessage();
    }
    
}
