package br.dev.detowhey.urlshortner.util;

import org.modelmapper.ModelMapper;

public class MapObject {

    private final ModelMapper modelMapper = new ModelMapper();
    private static MapObject instance;

    private MapObject() {
    }

    public static MapObject getInstance() {
        return instance == null ? instance = new MapObject() : instance;
    }

    public  <T> T convertToType(Object source, Class<T> resultClass) {
        return modelMapper.map(source,resultClass);
    }
}
