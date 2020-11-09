package com.letscode.java.springbank.exceptions;

import org.springframework.http.HttpStatus;

public class RecursoJaExisteException extends RuntimeException {
    
    public RecursoJaExisteException(Class clazz) {
        super(String.format("%s já existe", clazz.getSimpleName()));
    }
    
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}

