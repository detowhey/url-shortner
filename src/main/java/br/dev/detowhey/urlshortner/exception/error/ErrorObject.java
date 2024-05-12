package br.dev.detowhey.urlshortner.exception.error;

public record ErrorObject(
        String message,
        String field,
        Object parameter
) {
}
