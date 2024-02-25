package com.java.httpserver.Http;

public abstract class HttpMessage {

    private String method;
    private String httpVersion;
    private String requestTarget;

    HttpMessage() {
    }

    public String getMethod() {
        return method;
    }

    void setMethod(String method) {
        this.method = method;
    }
}
