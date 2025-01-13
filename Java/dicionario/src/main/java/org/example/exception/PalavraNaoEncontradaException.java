package org.example.exception;

public class PalavraNaoEncontradaException extends RuntimeException {

    public PalavraNaoEncontradaException(String message) {
        super(message);
    }

}