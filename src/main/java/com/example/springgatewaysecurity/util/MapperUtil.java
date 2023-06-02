package com.example.springgatewaysecurity.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtil {

    ObjectMapper mapper = new ObjectMapper();

    public <T> T objectMapper(Object object, Class<T> contentClassType){

        return mapper.convertValue(object, contentClassType);
    }
}
