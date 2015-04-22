package main.java.com.celantinteractive.frames;

import java.io.Serializable;

public class ResponseFrame implements Serializable {

    public enum StatusCode {
        OK,
        GENERAL_FAILURE,
        INVALID_CREDENTIALS,
        STALE_SESSION;
    }

    /**
     *
     */
    private static final long serialVersionUID = -1127404576184110691L;
    private StatusCode statusCode;
    private String uniqueSupport;

    public ResponseFrame(StatusCode statusCode, String uniqueSupport) {
        this.statusCode = statusCode;
        this.uniqueSupport = uniqueSupport;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getUniqueSupport() {
        return uniqueSupport;
    }

    public void setUniqueSupport(String uniqueSupport) {
        this.uniqueSupport = uniqueSupport;
    }

}
