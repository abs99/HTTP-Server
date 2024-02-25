package com.java.httpserver.Http;

import com.java.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    public HttpRequest parseHttpRequest(InputStream inputStream){
        LOGGER.info("parsing http request");
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();
        parseline(reader,request);
        parseHeaders(reader,request);
        parseBody(reader,request);
        return request;
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
    }

    private void parseline(InputStreamReader reader, HttpRequest request) {
    }


}
