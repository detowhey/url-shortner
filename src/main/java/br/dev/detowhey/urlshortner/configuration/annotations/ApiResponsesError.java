package br.dev.detowhey.urlshortner.configuration.annotations;

import br.dev.detowhey.urlshortner.exception.error.ErrorResponse;
import br.dev.detowhey.urlshortner.exception.error.StandardError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = "404",
                description = "Not Found URL",
                content = @Content(schema = @Schema(implementation = StandardError.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Conflict",
                content = @Content(schema = @Schema(implementation = StandardError.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(schema = @Schema(implementation = StandardError.class))
        )
})
public @interface ApiResponsesError {
}
