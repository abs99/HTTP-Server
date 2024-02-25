package com.java.httpserver.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable {

    private int port;
    private String webRoot;
    private final ServerSocket serverSocket;
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListener.class);


    public ServerListener(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()){
                Socket socket = this.serverSocket.accept();
                LOGGER.info("Connection accepted " + socket.getInetAddress());
                HttpConnectionWorker worker = new HttpConnectionWorker(socket);
                Thread workerThread = new Thread(worker);
                workerThread.start();
            }


            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace(); //TO HANDLE LATER
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) { }
            }
        }
    }
}
