package br.dev.detowhey.urlshortner.service;

import br.dev.detowhey.urlshortner.dto.response.ShortenerUrlResponseDTO;
import br.dev.detowhey.urlshortner.entity.UrlEntity;
import br.dev.detowhey.urlshortner.exception.BusinessException;
import br.dev.detowhey.urlshortner.exception.DataBaseConnectionException;
import br.dev.detowhey.urlshortner.exception.NotFoundException;
import br.dev.detowhey.urlshortner.mapper.UrlMapper;
import br.dev.detowhey.urlshortner.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String MESSAGE_ERROR = "This URL is already registered";

    private final UrlMapper urlMapper;


    @Autowired
    public UrlService(UrlRepository urlRepository, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
    }

    public UrlEntity postCreateUrlShortener(String url, HttpServletRequest servletRequest) {
        try {

            if (urlRepository.findByUrl(url).isPresent()) {
                log.error(MESSAGE_ERROR);
                throw new BusinessException(MESSAGE_ERROR);
            }

            log.info("Create new short url");
            String uuid = UUID.randomUUID().toString();
            String shortUrl = servletRequest.getRequestURL().toString().replace("shortener", uuid);

            UrlEntity urlEntity = UrlEntity.builder()
                    .url(url)
                    .shortenerUrl(shortUrl)
                    .createdAt(LocalDateTime.now())
                    .build();

            return urlRepository.save(urlEntity);
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

    public ShortenerUrlResponseDTO toDto(UrlEntity urlEntity) {
        return urlMapper.toDto(urlEntity);
    }
}
