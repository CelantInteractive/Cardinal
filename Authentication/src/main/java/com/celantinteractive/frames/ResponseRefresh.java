package main.java.com.celantinteractive.frames;

import java.io.Serializable;

public class ResponseRefresh extends ResponseFrame implements Serializable {

	private static final long serialVersionUID = -1876309879392883746L;
	private String accessToken;
	private String clientToken;

	public ResponseRefresh() {
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
}
