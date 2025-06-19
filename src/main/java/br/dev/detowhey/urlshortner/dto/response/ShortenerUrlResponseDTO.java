package br.dev.detowhey.urlshortner.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Schema(name = "ShortUrl", description = "Url response object model")
public record ShortenerUrlResponseDTO(
        @Schema(description = "ID from register")
        String id,
        @Schema(description = "The shorten URL", example = "https://ex/4das321")
        String shortenerUrl,
        @Schema(description = "Created date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
        LocalDateTime createdAt
) {
}
