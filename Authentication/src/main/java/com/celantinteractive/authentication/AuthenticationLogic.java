/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.frames.RefreshRequest;
import main.java.com.celantinteractive.frames.ResponseLogin;
import main.java.com.celantinteractive.frames.ResponseRefresh;
import main.java.com.celantinteractive.frames.LoginRequest;
import java.util.UUID;
import main.java.com.celantinteractive.frames.ResponseFrame.StatusCode;

import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("unused")
public class AuthenticationLogic {

    IOrbitAuthDAO authTemplate = null;

    public AuthenticationLogic(IOrbitAuthDAO auth) {
        authTemplate = auth;
    }

    public ResponseLogin processLogin(LoginRequest loginRequest) {

        ResponseLogin response = new ResponseLogin();

        String userUUID = authTemplate.getUUIDFromUsername(loginRequest
                .getUsername());

        String userPassword = authTemplate.getPasswordFromUUID(userUUID);

        if (userPassword != null) {
            String userAccessToken = UUID.randomUUID().toString();
            String userClientToken = UUID.randomUUID().toString();

            if (!loginRequest.getClientToken().isEmpty()) {
                userClientToken = loginRequest.getClientToken();
            }

            if (BCrypt.checkpw(loginRequest.getPassword(), userPassword)) {
                authTemplate.createSession(userUUID, userAccessToken, userClientToken);
                response.setStatusCode(StatusCode.OK);
                response.setAccessToken(userAccessToken);
                response.setClientToken(userClientToken);
                response.setUsername(loginRequest.getUsername());
                response.setUUID(userUUID);
            } else {
                response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
            }
        } else {
            response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
        }

        return response;
    }

    public ResponseRefresh processRefresh(RefreshRequest refreshRequest) {

        ResponseRefresh response = new ResponseRefresh();

        String userAccessToken = UUID.randomUUID().toString();

        if (authTemplate.sessionIsValid(refreshRequest.getAccessToken())) {
            authTemplate.refreshSession(userAccessToken, refreshRequest.getAccessToken(), refreshRequest.getClientToken());
            response.setAccessToken(userAccessToken);
            response.setClientToken(refreshRequest.getClientToken());
            response.setStatusCode(StatusCode.OK);
        } else {
            response.setStatusCode(StatusCode.UNKNOWN_ACCESS_TOKEN);
        }

        return response;
    }

}
