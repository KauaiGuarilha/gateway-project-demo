package com.demo.mspagamento.model.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Getter private String description;

    public ResourceNotFoundException(String message) {
        super(message);
        this.description = message;
    }
}
