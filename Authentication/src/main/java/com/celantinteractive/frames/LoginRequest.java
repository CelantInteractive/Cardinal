/**
 * Copyright (c) 2015 Celant Interactive Ltd. All rights reserved
 */
package main.java.com.celantinteractive.frames;

import java.io.Serializable;

public class LoginRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1303422757364982863L;

	private String username;
	private String password;
	private String clientToken;
	
	public LoginRequest() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}
}
