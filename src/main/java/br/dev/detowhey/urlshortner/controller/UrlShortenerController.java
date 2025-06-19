package br.dev.detowhey.urlshortner.controller;

import br.dev.detowhey.urlshortner.configuration.annotations.ApiResponsesError;
import br.dev.detowhey.urlshortner.dto.request.ShortenerUrlRequestDTO;
import br.dev.detowhey.urlshortner.dto.response.ShortenerUrlResponseDTO;
import br.dev.detowhey.urlshortner.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Url shortener", description = "URL shortener methods")
@RestController
@RequestMapping(value = "/shortener", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponsesError
public class UrlShortenerController {

    private final UrlService urlService;

    @Autowired
    public UrlShortenerController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Operation(
            summary = "Create a shortener URL value",
            responses = @ApiResponse(
                    responseCode = "201",
                    description = "Successfully create a new short URL",
                    content = @Content(schema = @Schema(implementation = ShortenerUrlResponseDTO.class)))
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ShortenerUrlResponseDTO createShortenerUrl(
            @Validated
            @RequestBody
            ShortenerUrlRequestDTO shortenerUrlRequestDTO,
            HttpServletRequest servletRequest) {
        var urlEntity = urlService.postCreateUrlShortener(shortenerUrlRequestDTO.url(), servletRequest);
        return urlService.toDto(urlEntity);
    }


    @Operation(summary = "Return object information from URL",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Ok",
                    content = @Content(schema = @Schema(implementation = ShortenerUrlResponseDTO.class)))
    )
    @GetMapping(value = "{id}")
    public ShortenerUrlResponseDTO getRedirectUrl(@PathVariable("id") String id, HttpServletResponse response) {
        var urlEntity = urlService.getRedirectUrl(id);
        var urlDto = urlService.toDto(urlEntity);

        response.addHeader("Location", urlEntity.getUrl());
        return urlDto;
    }
}
