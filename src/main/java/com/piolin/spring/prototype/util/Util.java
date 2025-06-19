package com.piolin.spring.prototype.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    ObjectMapper objMapper = new ObjectMapper();

    public String convertObject2Json(Object obj){
        String result = null;
        try {
            result = objMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            LOG.info("JsonProcessingException when converting to JSON String: {}", ex.getMessage());
        }
        return result;
    }

    public Object convertJson2Object(String json, Object obj){
        Object result = null;
        try {
            result = objMapper.readValue(json, obj.getClass());
        } catch (JsonProcessingException ex) {
            LOG.info("JsonProcessingException when converting to specific Object: {}", ex.getMessage());
        }
        return result;
    }
}