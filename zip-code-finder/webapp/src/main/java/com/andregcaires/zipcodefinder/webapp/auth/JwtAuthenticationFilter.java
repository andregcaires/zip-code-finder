package com.andregcaires.zipcodefinder.webapp.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.andregcaires.zipcodefinder.webapp.dtos.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JwtUtil jwtUtil;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			var creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDto.class);

			var authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>());

			var auth = authenticationManager.authenticate(authToken);

			return auth;

		} catch (IOException e) {

			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication (HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain, 
			Authentication auth) {
		
		var username = ((UserDetailsImpl) auth.getPrincipal()).getUsername();
		var token = jwtUtil.generateToken(username);
		
		response.addHeader("Authorization", "Bearer "+token);		
	}
}
