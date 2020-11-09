package com.letscode.java.springbank.exceptions;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontradoException extends RuntimeException {
    
    public RecursoNaoEncontradoException(Class clazz) {
        super(String.format("%s não encontrado", clazz.getSimpleName()));
    }
    
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

