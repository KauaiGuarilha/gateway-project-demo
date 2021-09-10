package com.demo.msrepository.model.exceptions.handles;

import com.demo.msrepository.model.exceptions.ResourceNotFoundException;
import com.demo.msrepository.model.exceptions.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class RepositoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ResponseException> badRequestHandlerException(Exception e, WebRequest webRequest){
        ResponseException responseException = new ResponseException(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ResponseException>(responseException, HttpStatus.BAD_REQUEST);
    }
}
