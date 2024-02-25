package com.java.httpserver.Exceptions;

import com.java.httpserver.Http.HttpStatusCode;

public class HttpParsingException extends Exception{

        HttpStatusCode httpStatusCode;

    public HttpParsingException(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatusCode getErrorCode(){
        return this.httpStatusCode;
    }
}
