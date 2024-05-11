package br.dev.detowhey.urlshortner.repository;

import br.dev.detowhey.urlshortner.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
