package com.java.httpserver.Http;

public enum HttpMethod {

    GET,HEAD;
    public static final int MAX_METHOD_LENGTH ;

    static {
        int tlength=-1;
        for(HttpMethod method: HttpMethod.values()){
                if(method.name().length()>tlength){
                    tlength=method.name().length();
                }
        }
        MAX_METHOD_LENGTH=tlength;
    }
}
