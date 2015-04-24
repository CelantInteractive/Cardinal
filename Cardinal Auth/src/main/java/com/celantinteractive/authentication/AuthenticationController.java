/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.response.ResponseRefresh;
import main.java.com.celantinteractive.common.ResponseFrame;
import main.java.com.celantinteractive.common.APIEndpoints;
import main.java.com.celantinteractive.response.ResponseLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    ICardinalAuthDAO authTemplate;

    @RequestMapping(value = APIEndpoints.LOGIN, method = RequestMethod.POST, consumes = "*/*", produces = "application/json")
    public ResponseLogin login(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "clientToken", required = false, defaultValue = "") String clientToken) {

        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);

        ResponseLogin response = logic.processLogin(email, password, clientToken);

        return response;
    }

    @RequestMapping(value = APIEndpoints.REFRESH, method = RequestMethod.POST, consumes = "*/*", produces = "application/json")
    public ResponseRefresh refresh(
            @RequestParam(value = "accessToken") String accessToken,
            @RequestParam(value = "clientToken") String clientToken) {

        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);

        ResponseRefresh response = logic.processRefresh(accessToken, clientToken);

        return response;
    }
    
    @RequestMapping(value = APIEndpoints.VALIDATE, method = RequestMethod.POST, consumes = "*/*", produces = "application/json")
    public ResponseFrame validate(
            @RequestParam(value = "accessToken") String accessToken) {
        
        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);
        
        ResponseFrame response = logic.processValidate(accessToken);
        
        return response;
    }

    @RequestMapping(value = APIEndpoints.LOGOUT, method = RequestMethod.POST, consumes = "*/*", produces = "application/json")
    public ResponseFrame logout(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        
        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);

        ResponseFrame response = logic.processLogout(email, password);

        return response;
    }

    @RequestMapping(value = APIEndpoints.INVALIDATE, method = RequestMethod.POST, consumes = "*/*", produces = "application/json")
    public ResponseFrame invalidate(
            @RequestParam(value = "accessToken") String accessToken,
            @RequestParam(value = "clientToken") String clientToken) {
    
        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);

        ResponseFrame response = logic.processInvalidate(accessToken, clientToken);

        return response;
    }
}
