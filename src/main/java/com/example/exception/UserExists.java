package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


public class UserExists extends RuntimeException{

    /* 
    public UserExists(String s){
        super(s);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String cannotCreateUser(Exception ex) {
        return ex.getMessage();
    }
    */
}
