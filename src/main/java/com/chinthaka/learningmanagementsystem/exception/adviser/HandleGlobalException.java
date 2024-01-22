package com.chinthaka.learningmanagementsystem.exception.adviser;

import com.chinthaka.learningmanagementsystem.exception.AlreadyExistException;
import com.chinthaka.learningmanagementsystem.exception.HandleException;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import com.chinthaka.learningmanagementsystem.utils.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleGlobalException {

    @ExceptionHandler(HandleException.class)
    public ResponseEntity<StandardResponse> handleHandleException(HandleException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500,"Error",e.getMessage())
                ,HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error",e.getMessage())
                ,HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<StandardResponse> handleAlreadyExistException(AlreadyExistException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400,"Error",e.getMessage())
                ,HttpStatus.BAD_REQUEST
        );
    }
}
