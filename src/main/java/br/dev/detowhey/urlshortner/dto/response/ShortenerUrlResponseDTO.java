package br.dev.detowhey.urlshortner.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ShortUrl", description = "Url request object model")
public class ShortenerUrlResponseDTO {
    @Schema(description = "The shorten URL", example = "https://ex/4das321")
    private String shortenerUrl;
}
