package com.java.httpserver.Http;

import com.java.httpserver.Exceptions.HttpParsingException;
import com.java.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private final static int SP = 0x20; //32
    private final static int CR = 0x0D; //13
    private final static int LF = 0x0A; //10

    private boolean isMethodParsed = false;
    private boolean isResourceTargrtParsed = false;
    public HttpRequest parseHttpRequest(InputStream inputStream) throws  HttpParsingException{
        LOGGER.info("parsing http request");
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();
        try {
            parseRequestline(reader,request);
        } catch (IOException e) {
           e.printStackTrace();
        }
        parseHeaders(reader,request);
        parseBody(reader,request);
        return request;
    }
    private void parseRequestline(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {

        StringBuilder dataBuffer = new StringBuilder();
        int _byte;
        while( (_byte = reader.read())>=0){

            if(_byte==CR){
                _byte=reader.read();
                if(_byte==LF){
                    LOGGER.debug("Request Line to Process : {}",dataBuffer.toString());
                    return;
                }
            }

            if(_byte==SP){
                if(!isMethodParsed) {
                    LOGGER.debug("Method Parsed : {}", dataBuffer.toString());
                    request.setMethod(dataBuffer.toString());
                    isMethodParsed = true;
                } else if (!isResourceTargrtParsed) {
                    LOGGER.debug("Resource Target Parsed : {}", dataBuffer.toString());
                    isResourceTargrtParsed = true;
                }

                dataBuffer.delete(0,dataBuffer.length());
            }else {
                dataBuffer.append((char) _byte);

                if(!isMethodParsed && dataBuffer.length()>HttpMethod.MAX_METHOD_LENGTH){
                        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                }
            }
        }
    }


    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
    }



}
