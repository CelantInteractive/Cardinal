/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.frames.ResponseLogin;
import main.java.com.celantinteractive.frames.RefreshRequest;
import main.java.com.celantinteractive.frames.ResponseRefresh;
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
                if(authTemplate.isValidClientToken(clientToken)) {
                    userClientToken = clientToken;
                }
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

    public ResponseRefresh processRefresh(String clientToken, String accessToken) {

        ResponseRefresh response = new ResponseRefresh();

        String userAccessToken = UUID.randomUUID().toString();

        if (authTemplate.sessionIsRecent(clientToken, accessToken)) {
            authTemplate.refreshSession(userAccessToken, accessToken, clientToken);
            response.setAccessToken(userAccessToken);
            response.setClientToken(clientToken);
            response.setStatusCode(StatusCode.OK);
        } else {
            response.setStatusCode(StatusCode.STALE_SESSION);
        }

        return response;
    }

}
