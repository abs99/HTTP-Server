package com.java.httpserver.Http;

import com.java.httpserver.Exceptions.HttpParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest extends HttpMessage{
    HttpRequest(){

    }
    private HttpMethod method;
    private String httpVersion;
    private String requestTarget;

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);


    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(String method) throws HttpParsingException {

        for(HttpMethod httpMethod : HttpMethod.values()){
            LOGGER.debug(httpMethod.name() );
            if(method.equals(httpMethod.name())){
                this.method=httpMethod;
                return;
            }
        }
        throw new HttpParsingException(
                HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED
        );

    }

    public void setRequestTarget(String requestTarget) throws HttpParsingException {
        if(requestTarget==null || requestTarget.isEmpty()) throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_BAD_REQUEST);
        this.requestTarget = requestTarget;
    }

    public String getRequestTarget() {
        return requestTarget;
    }
}
