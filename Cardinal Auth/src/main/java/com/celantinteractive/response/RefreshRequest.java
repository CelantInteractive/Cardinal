/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.response;

import java.io.Serializable;

public class RefreshRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8668205813566302595L;
	private String accessToken;
	private String clientToken;
	
	public RefreshRequest() {

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
