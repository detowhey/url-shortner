package br.dev.detowhey.urlshortner.exception;

public class DataBaseConnectionException extends RuntimeException {

    public DataBaseConnectionException() {
        super("Connection with database is out");
    }
}
