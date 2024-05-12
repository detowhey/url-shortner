package br.dev.detowhey.urlshortner.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record StandardError(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-/MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
