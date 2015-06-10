/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.resources;

import java.util.ResourceBundle;

/**
 * Singleton provider access to message.properties file
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class MessageManager {

    /**
     * ResourceBundle for message.properties file
     */
    private static final ResourceBundle resourceBundle
            = ResourceBundle.getBundle("resources.message");

    /**
     * default constructor
     */
    private MessageManager() {
    }

    /**
     * @param key
     * @return specified by key String from message.properties file
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
