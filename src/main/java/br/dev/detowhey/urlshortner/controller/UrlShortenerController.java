package br.dev.detowhey.urlshortner.controller;

import br.dev.detowhey.urlshortner.dto.request.ShortenerUrlRequestDTO;
import br.dev.detowhey.urlshortner.dto.response.ShortenerUrlResponseDTO;
import br.dev.detowhey.urlshortner.entity.UrlEntity;
import br.dev.detowhey.urlshortner.service.UrlService;
import br.dev.detowhey.urlshortner.util.MapObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/shortener", produces = MediaType.APPLICATION_JSON_VALUE)
public class UrlShortenerController {

    private final UrlService urlService;
    private final MapObject mapObject = MapObject.getInstance();

    @Autowired
    public UrlShortenerController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<ShortenerUrlResponseDTO> createShortenerUrl(
            @Validated
            @RequestBody
            ShortenerUrlRequestDTO shortenerUrlRequestDTO,
            HttpServletRequest servletRequest) {
        UrlEntity urlEntityRequest = mapObject.convertToType(shortenerUrlRequestDTO, UrlEntity.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(urlEntityRequest.getId())
                .toUri();

        UrlEntity urlEntity = urlService.postCreateUrlShortener(urlEntityRequest, servletRequest);
        ShortenerUrlResponseDTO urlShortenerResponse = mapObject.convertToType(urlEntity, ShortenerUrlResponseDTO.class);
        return ResponseEntity.created(uri).body(urlShortenerResponse);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ShortenerUrlResponseDTO> getRedirectUrl(@PathVariable("id") String id) {
        var url = urlService.getRedirectUrl(id);
        var urlDto = mapObject.convertToType(url, ShortenerUrlResponseDTO.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return ResponseEntity.ok().headers(headers).body(urlDto);
    }
}
