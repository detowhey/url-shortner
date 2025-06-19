package br.dev.detowhey.urlshortner.repository;

import br.dev.detowhey.urlshortner.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {

    Optional<UrlEntity> findByUrl(String url);
}
