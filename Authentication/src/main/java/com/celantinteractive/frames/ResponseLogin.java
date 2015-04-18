package main.java.com.celantinteractive.frames;

import java.io.Serializable;

public class ResponseLogin extends ResponseFrame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6004769023625219821L;
	private String UUID;
	private String username;
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

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
