package br.dev.detowhey.urlshortner.mapper;

import br.dev.detowhey.urlshortner.dto.response.ShortenerUrlResponseDTO;
import br.dev.detowhey.urlshortner.entity.UrlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {

    ShortenerUrlResponseDTO toDto(UrlEntity entity);
}
