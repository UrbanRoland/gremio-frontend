package com.gremio.message.response;

import com.gremio.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {
    private String token;
	private String type = "Bearer";
	private String username;

	private User user;

	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities, User user) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
		this.user = user;
	}
}
