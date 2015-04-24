package main.java.com.celantinteractive.response;

import java.io.Serializable;
import main.java.com.celantinteractive.common.ResponseFrame;

public class ResponseLogin extends ResponseFrame implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6004769023625219821L;
    private String cardinalId;
    private String accessToken;
    private String clientToken;

    public ResponseLogin() {
        super(StatusCode.GENERAL_FAILURE, "");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getCardinalId() {
        return cardinalId;
    }

    public void setCardinalId(String cardinalId) {
        this.cardinalId = cardinalId;
    }
}
