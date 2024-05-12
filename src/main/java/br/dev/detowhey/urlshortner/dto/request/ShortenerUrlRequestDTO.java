package br.dev.detowhey.urlshortner.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortenerUrlRequestDTO {
    @NotBlank(message = "URL not blank value")
    @URL(message = "URL value is not valid")
    private String url;
}
