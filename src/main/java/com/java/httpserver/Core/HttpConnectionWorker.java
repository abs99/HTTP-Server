package com.java.httpserver.Core;

import com.java.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorker implements Runnable{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    private Socket socket;

    public HttpConnectionWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream in = null;
        OutputStream out = null;
        try{
            in = socket.getInputStream();
            out = socket.getOutputStream();
            String html = "<html><head><title>Simple Java HTTP Server</title></head>" +
                    "<body><h1>Simple Java Server </h1></body></html>";
            final String CLRF = "\n\r";
            String response = "HTTP/1.1 200 OK" + CLRF+
                    "Content-Length :"+ html.getBytes().length + CLRF +
                    CLRF + html + CLRF
                    +CLRF;

            out.write(response.getBytes());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Connection processing finished");
    }
}
