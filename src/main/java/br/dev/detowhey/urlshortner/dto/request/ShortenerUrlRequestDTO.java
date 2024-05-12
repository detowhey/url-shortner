package br.dev.detowhey.urlshortner.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Url", description = "Url request object model")
public class ShortenerUrlRequestDTO {
    @NotBlank(message = "URL not blank value")
    @URL(message = "URL value is not valid")
    @Schema(description = "The URL value to shorten", example = "https://example.com")
    private String url;
}
