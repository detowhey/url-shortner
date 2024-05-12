package br.dev.detowhey.urlshortner.service;

import br.dev.detowhey.urlshortner.entity.UrlEntity;
import br.dev.detowhey.urlshortner.exception.DataBaseConnectionException;
import br.dev.detowhey.urlshortner.exception.NotFoundException;
import br.dev.detowhey.urlshortner.repository.UrlRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlEntity postCreateUrlShortener(UrlEntity urlEntity) {
        try {
            log.info("Create new short url");

            String id;

            do {
                id = RandomStringUtils.randomAlphanumeric(5, 10);
            } while (urlRepository.existsById(id));

            return urlRepository.save(new UrlEntity(id, urlEntity.getFullUrl(), urlEntity.getLocalDateTime()));
        } catch (DataBaseConnectionException e) {
            log.error("Connection with database is out");
            throw new DataBaseConnectionException();
        }
    }

    public UrlEntity getRedirectUrl(String id) {
        log.info("Searching URL with ID: {}", id);

        return urlRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found URL with ID: ", id));
    }
}
