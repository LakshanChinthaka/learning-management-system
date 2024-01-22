package com.chinthaka.learningmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class HandleException extends RuntimeException{

    public HandleException(String message) {
        super(message);
    }
}
