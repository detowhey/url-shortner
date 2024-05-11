package br.dev.detowhey.urlshortner.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Document
public class UrlEntity {

    @Id
    private String id;
    private String fullUrl;
    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime localDateTime;
}
