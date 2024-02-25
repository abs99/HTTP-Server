package com.java.httpserver.Utillities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public class Json {

    private static ObjectMapper myobjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper;
    }

    public static JsonNode parse(String srcString) throws IOException {
        return myobjectMapper.readTree(srcString);
    }

    public static <T> T fromJson(JsonNode node, Class<T> tClass) throws JsonProcessingException {
        return myobjectMapper.treeToValue(node,tClass);
    }

    public static JsonNode toJson(Object obj){
        return myobjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node,false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node,true);
    }

    private static String generateJson(Object obj, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myobjectMapper.writer();
        return objectWriter.writeValueAsString(obj);
    }
}
