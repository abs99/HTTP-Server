package com.java.httpserver.Http;

public enum HttpStatusCode {

    CLIENT_ERROR_BAD_REQUEST(400, "Bad Request");

    CLIENT_ERROR_NOT_F
    public final int STATUS_CODE;
    public final String message;

    HttpStatusCode(int STATUS_CODE, String message) {
        this.STATUS_CODE = STATUS_CODE;
        this.message = message;
    }
}
