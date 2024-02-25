package com.java.httpserver.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.java.httpserver.Exceptions.HttpConfigurationException;
import com.java.httpserver.Utillities.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private  static ConfigurationManager myConfigManager;

    private static Config myConfig;

    private ConfigurationManager() {
    }
    public static ConfigurationManager getInstance(){
        if(myConfigManager==null){
            myConfigManager = new ConfigurationManager();
        }
        return myConfigManager;

    }
    /*
    * Used to load Configuration File
    */
    public void loadConfiguration(String filePath) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();

        int i;
        while (true){
            try {
                if (((i=fileReader.read())==-1)) break;
            } catch (IOException e) {
                throw new HttpConfigurationException("Unable to read File",e);
            }
            sb.append((char) i);
        }
        JsonNode node = null;
        try {
            node = Json.parse(sb.toString());
            myConfig = Json.fromJson(node,Config.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
/*
*Get Current Configuration
*/
    public static  Config getCurrentConfiguration(){

        if(myConfig==null){
            throw new HttpConfigurationException("No Configuration found");
        }
        return  myConfig;
    }
}
