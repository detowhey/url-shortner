package br.dev.detowhey.urlshortner.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(name = "Errors", description = "Error response to invalid fields")
public record ErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
        @Schema(description = "The date of execution error handler", pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", example = "15/05/2024T19:23:18.254Z")
        Instant timestamp,
        @Schema(description = "The status code from response", example = "401")
        Integer status,
        @Schema(description = "The message error from request", example = "Invalid url value")
        String message,
        @Schema(description = "The property from request is invalid", example = "url")
        String objectName,
        @ArraySchema(arraySchema = @Schema(description = "Array object with errors descriptions", implementation = ErrorObject.class))
        List<ErrorObject> errors
) {
}
