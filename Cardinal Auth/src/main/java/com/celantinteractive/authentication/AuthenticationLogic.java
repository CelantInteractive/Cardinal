/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.authentication;

import main.java.com.celantinteractive.response.ResponseLogin;
import main.java.com.celantinteractive.response.ResponseRefresh;
import java.util.UUID;
import main.java.com.celantinteractive.common.ResponseFrame;
import main.java.com.celantinteractive.common.ResponseFrame.StatusCode;

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

        if (userPassword != null && !userPassword.isEmpty()) {
            String userAccessToken = UUID.randomUUID().toString();
            String userClientToken = UUID.randomUUID().toString();

            if (!clientToken.isEmpty()) {
                if (clientToken.matches("[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}")) {
                    userClientToken = clientToken;
                }
            }

            if (BCrypt.checkpw(password, userPassword)) {
                String cardinalId = authTemplate.getCardinalIdFromEmail(email);
                if (!cardinalId.isEmpty()) {
                    authTemplate.createSession(email, userAccessToken, userClientToken);
                    response.setStatusCode(StatusCode.OK);
                    response.setCardinalId(cardinalId);
                    response.setAccessToken(userAccessToken);
                    response.setClientToken(userClientToken);
                } else {
                    response.setStatusCode(StatusCode.GENERAL_FAILURE);
                }
            } else {
                response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
            }
        } else {
            response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
        }

        return response;
    }

    public ResponseRefresh processRefresh(String accessToken, String clientToken) {

        ResponseRefresh response = new ResponseRefresh();

        String userAccessToken = UUID.randomUUID().toString();

        if (authTemplate.authenticateSession(accessToken, clientToken)) {
            authTemplate.refreshSession(userAccessToken, accessToken, clientToken);
            response.setAccessToken(userAccessToken);
            response.setClientToken(clientToken);
            response.setStatusCode(StatusCode.OK);
        } else {
            response.setStatusCode(StatusCode.STALE_SESSION);
        }

        return response;
    }

    public ResponseFrame processValidate(String accessToken) {

        ResponseFrame response = new ResponseFrame();

        if (authTemplate.sessionIsRecent(accessToken)) {
            response.setStatusCode(StatusCode.OK);
        } else {
            response.setStatusCode(StatusCode.STALE_SESSION);
        }

        return response;
    }

    public ResponseFrame processLogout(String email, String password) {

        ResponseFrame response = new ResponseFrame();

        String userPassword = authTemplate.getPasswordFromEmail(email);

        if (userPassword != null) {
            if (BCrypt.checkpw(password, userPassword)) {
                authTemplate.invalidateSessionByEmail(email);
                response.setStatusCode(StatusCode.OK);
            } else {
                response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
            }
        } else {
            response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
        }

        return response;
    }

    public ResponseFrame processInvalidate(String accessToken, String clientToken) {

        ResponseFrame response = new ResponseFrame();

        if (authTemplate.authenticateSession(accessToken, clientToken)) {
            authTemplate.invalidateSessionByPair(accessToken, clientToken);
            response.setStatusCode(StatusCode.OK);
        } else {
            response.setStatusCode(StatusCode.INVALID_CREDENTIALS);
        }

        return response;
    }

}
