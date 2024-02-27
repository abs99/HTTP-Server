package com.java.httpserver.Http;

public enum HttpStatusCode {
    /* Client Errors*/
    CLIENT_ERROR_BAD_REQUEST(400, "Bad Request"),

    CLIENT_ERROR_METHOD_NOT_ALLOWED(405,"Not allowed"),

    CLIENT_ERROR_URI_TOO_LONG(414,"Uri Too long"),

    /*Server Errors */
    SERVER_ERROR_500_INTERNAL(500,"Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501,"Method not implemented");


    public final int STATUS_CODE;
    public final String message;

    HttpStatusCode(int STATUS_CODE, String message) {
        this.STATUS_CODE = STATUS_CODE;
        this.message = message;
    }
}
