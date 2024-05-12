package br.dev.detowhey.urlshortner.exception.error;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Error", description = "Object error")
public record ErrorObject(
        @Schema(description = "The message error from request", example = "Invalid url value")
        String message,
        @Schema(description = "The field property from request", example = "url")
        String field,
        @Schema(description = "The parameter from field", example = "example123", implementation = String.class)
        Object parameter
) {
}
