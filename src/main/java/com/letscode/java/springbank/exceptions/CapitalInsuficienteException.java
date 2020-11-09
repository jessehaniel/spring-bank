package com.letscode.java.springbank.exceptions;

public class CapitalInsuficienteException extends RuntimeException {
    
    public CapitalInsuficienteException() {
        super("O cliente não possui capital suficiente para adicionar o produto à sua carteira");
    }
}
