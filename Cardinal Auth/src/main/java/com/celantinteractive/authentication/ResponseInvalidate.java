/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.common.ResponseFrame;

/**
 *
 * @author Joshua
 */
public class ResponseInvalidate extends ResponseFrame {

    public ResponseInvalidate() {
        super(StatusCode.GENERAL_FAILURE, "");
    }
    
}
