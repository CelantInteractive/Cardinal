/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.celantinteractive.updates;

import main.java.com.celantinteractive.common.ResponseFrame;

/**
 *
 * @author Joshua
 */
public class ResponseUpdate extends ResponseFrame {

    private String latestVersion = "";

    /**
     * @return the latestVersion
     */
    public String getLatestVersion() {
        return latestVersion;
    }

    /**
     * @param latestVersion the latestVersion to set
     */
    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
