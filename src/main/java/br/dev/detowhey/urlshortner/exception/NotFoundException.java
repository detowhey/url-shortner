package br.dev.detowhey.urlshortner.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String id) {
        super(message + id);
    }
}
