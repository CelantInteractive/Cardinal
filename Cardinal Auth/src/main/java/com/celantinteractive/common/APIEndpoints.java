/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.celantinteractive.common;

/**
 *
 * @author Joshua
 */
public class APIEndpoints {
    
    public static class Categories {
        public static final String UPDATES = "/updates";
    }
    
    public static final String LOGIN = "/login";
    public static final String REFRESH = "/refresh";
    public static final String VALIDATE = "/validate";
    public static final String LOGOUT = "/logout";
    public static final String INVALIDATE = "/invalidate";
    
    public static final String GET_LATEST_VERSION = "/getLatestVersion";
    public static final String GET_INSTALLER = "/getInstaller";
}
