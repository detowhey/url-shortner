package br.dev.detowhey.urlshortner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String id;
    private String url;
    private String shortenerUrl;
    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime localDateTime;
}
