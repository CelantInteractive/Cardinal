/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.frames.RefreshRequest;
import main.java.com.celantinteractive.frames.ResponseRefresh;
import main.java.com.celantinteractive.frames.LoginRequest;
import java.util.UUID;
import main.java.com.celantinteractive.frames.ResponseFrame.StatusCode;

import org.springframework.security.crypto.bcrypt.BCrypt;

@SuppressWarnings("unused")
public class AuthenticationLogic {

    ICardinalAuthDAO authTemplate = null;

    public AuthenticationLogic(ICardinalAuthDAO auth) {
        authTemplate = auth;
    }

    public ResponseLogin processLogin(String email, String password, String clientToken) {

        ResponseLogin response = new ResponseLogin();

        String userPassword = authTemplate.getPasswordFromEmail(email);

        if (userPassword != null) {
            String userAccessToken = UUID.randomUUID().toString();
            String userClientToken = UUID.randomUUID().toString();

            if (!clientToken.isEmpty()) {
                userClientToken = clientToken;
            }

            if (BCrypt.checkpw(password, userPassword)) {
                authTemplate.createSession(email, userAccessToken, userClientToken);
                response.setStatusCode(StatusCode.OK);
                response.setAccessToken(userAccessToken);
                response.setClientToken(userClientToken);
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
