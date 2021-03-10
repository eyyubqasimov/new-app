package umb.carp.user;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	private final String email;
	
	public JwtResponse(String token, String email) {
		super();
		this.token = token;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public String getEmail() {
		return email;
	}

}