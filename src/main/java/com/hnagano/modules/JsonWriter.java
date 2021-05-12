/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hnagano.modules;

/**
 *
 * @author Bryan
 */
public class JsonWriter {
    String jsonMessage = "";
    
    public void AddMessage(String title, String message, boolean isString){
        if (isString) {
            if (jsonMessage.equals("")) {
                jsonMessage += "{\""+title+"\":\""+message+"\"";
            } else {
                jsonMessage += ",\""+title+"\":\""+message+"\"";
            }
        }
        
        else {
            if (jsonMessage.equals("")) {
                jsonMessage += "{\""+title+"\":"+message+"";
            } else {
                jsonMessage += ",\""+title+"\":"+message+"";
            }
        }
    }
    
    public String getMessage(){
        return jsonMessage+"}";
    }
}
