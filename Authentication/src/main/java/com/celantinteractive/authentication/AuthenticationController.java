/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.frames.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    IOrbitAuthDAO authTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "*/*", produces = "application/json")
    @ResponseBody
    public ResponseLogin login(@RequestBody LoginRequest loginReqest) {

        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);

        ResponseLogin response = logic.processLogin(loginReqest);

        return response;
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseRefresh refresh(@RequestBody RefreshRequest refreshRequest) {

        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);

        ResponseRefresh response = logic.processRefresh(refreshRequest);

        return response;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseFrame logout(@RequestBody LogoutRequest logoutRequest) {
        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);
        
        ResponseFrame response = logic.processLogin(null);
        
        return response;
    }
    
    @RequestMapping(value = "/invalidate", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseInvalidate logout(@RequestBody InvalidateRequest logoutRequest) {
        AuthenticationLogic logic = new AuthenticationLogic(authTemplate);
        
        ResponseInvalidate response = logic.processLogin(null);
        
        return response;
    }
}
