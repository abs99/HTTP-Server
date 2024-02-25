package com.java.httpserver;

import com.java.httpserver.Configuration.Config;
import com.java.httpserver.Configuration.ConfigurationManager;
import com.java.httpserver.Core.ServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/*
* Driver Class for HTTP Server
 */
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {
        LOGGER.info("Server running");

        ConfigurationManager.getInstance().loadConfiguration("src/main/resources/httpconfig.json");
        Config myConfig = ConfigurationManager.getInstance().getCurrentConfiguration();

        try {
            ServerListener serverListenerTh = new ServerListener(myConfig.getPort(),myConfig.getWebRoot());
            Thread serverThread = new Thread(serverListenerTh);
            LOGGER.info("Launching listener server thread");
            serverThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}