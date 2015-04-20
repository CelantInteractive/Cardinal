/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.frames;

import java.io.Serializable;

/**
 *
 * @author josh-harris
 */
public class LogoutRequest implements Serializable {
    
    private String username;
    private String password;

    public LogoutRequest() {

    }

    public String getAccessToken() {
        return username;
    }

    public void setAccessToken(String username) {
        this.username = username;
    }

    public String getClientToken() {
        return password;
    }

    public void setClientToken(String password) {
        this.password = password;
    }
}
