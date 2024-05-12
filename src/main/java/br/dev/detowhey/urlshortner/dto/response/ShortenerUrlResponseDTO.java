package br.dev.detowhey.urlshortner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortenerUrlResponseDTO {
    private String shortenerUrl;
}
