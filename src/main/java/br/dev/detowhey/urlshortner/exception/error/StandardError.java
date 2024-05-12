package br.dev.detowhey.urlshortner.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "StandardError", description = "Error Object for exceptions")
public record StandardError(
        @Schema(description = "The date of execution error handler", pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", example = "15/05/2024T19:23:18.254Z")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant timestamp,
        @Schema(description = "The status code from response", example = "401")
        Integer status,
        @Schema(description = "The error from request", example = "Bad request")
        String error,
        @Schema(description = "The message error from request", example = "Invalid url value")
        String message,
        @Schema(description = "The path of resource error", example = "/example")
        String path
) {
}
