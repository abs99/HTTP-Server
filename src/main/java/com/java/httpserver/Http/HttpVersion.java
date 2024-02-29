package com.java.httpserver.Http;

import java.util.regex.Pattern;

public enum HttpVersion {

    HTTP_VERSION("HTTP/1.1",1,1);
    public final String LITERAL;
    public final int MAJOR;
    public final int MINOR;

    HttpVersion(String LITERAL, int MAJOR, int MINOR) {
        this.LITERAL = LITERAL;
        this.MAJOR = MAJOR;
        this.MINOR = MINOR;
    }



}
