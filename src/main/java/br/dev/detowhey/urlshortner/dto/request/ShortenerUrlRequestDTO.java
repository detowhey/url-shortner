package br.dev.detowhey.urlshortner.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Schema(name = "Url", description = "Url request object model")
public record ShortenerUrlRequestDTO(
        @NotBlank(message = "URL not blank value")
        @URL(message = "URL value is not valid")
        @Schema(description = "The URL value to shorten", example = "https://example.com")
        @URL(message = "Value is not URL")
        String url
) {
}

