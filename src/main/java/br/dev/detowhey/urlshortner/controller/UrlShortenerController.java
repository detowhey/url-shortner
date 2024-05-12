package br.dev.detowhey.urlshortner.controller;

import br.dev.detowhey.urlshortner.dto.request.ShortenerUrlRequestDTO;
import br.dev.detowhey.urlshortner.dto.response.ShortenerUrlResponseDTO;
import br.dev.detowhey.urlshortner.entity.UrlEntity;
import br.dev.detowhey.urlshortner.exception.error.ErrorResponse;
import br.dev.detowhey.urlshortner.exception.error.StandardError;
import br.dev.detowhey.urlshortner.service.UrlService;
import br.dev.detowhey.urlshortner.util.MapObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Url shortener", description = "URL shortener methods")
@RestController
@RequestMapping(value = "/shortener", produces = MediaType.APPLICATION_JSON_VALUE)
public class UrlShortenerController {

    private final UrlService urlService;
    private final MapObject mapObject = MapObject.getInstance();

    @Autowired
    public UrlShortenerController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Operation(
            summary = "Create a shortener URL value",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create a new short URL",
                            content = @Content(schema = @Schema(implementation = ShortenerUrlResponseDTO.class))
                    )
            }
    )
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


    @Operation(
            summary = "Create a shortener URL value",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully search a short URL by ID",
                            content = @Content(schema = @Schema(implementation = ShortenerUrlResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found URL",
                            content = @Content(schema = @Schema(implementation = StandardError.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping(value = "{id}")
    public ResponseEntity<ShortenerUrlResponseDTO> getRedirectUrl(@PathVariable("id") String id) {
        var url = urlService.getRedirectUrl(id);
        var urlDto = mapObject.convertToType(url, ShortenerUrlResponseDTO.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));
        return ResponseEntity.ok().headers(headers).body(urlDto);
    }
}
