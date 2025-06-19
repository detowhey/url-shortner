package br.dev.detowhey.urlshortner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "urls")
public class UrlEntity {

    @Id
    @Field(targetType = FieldType.STRING)
    private String id;
    private String url;
    private String shortenerUrl;
    @Indexed(expireAfter = "0")
    private LocalDateTime createdAt;
}
