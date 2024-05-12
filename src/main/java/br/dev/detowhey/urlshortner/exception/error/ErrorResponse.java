package br.dev.detowhey.urlshortner.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant timestamp,
        Integer status,
        String message,
        String objectName,
        List<ErrorObject> errors
) {
}
