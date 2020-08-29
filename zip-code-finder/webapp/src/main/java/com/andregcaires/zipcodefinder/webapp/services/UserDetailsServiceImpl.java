package com.andregcaires.zipcodefinder.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.webapp.auth.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder _bCryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetailsImpl mockUser = null;
		
		if (!username.toLowerCase().equals("admin")) {
			throw new UsernameNotFoundException(username);
		}
		
		mockUser = new UserDetailsImpl("admin", _bCryptEncoder.encode("admin"));
		
		return mockUser;
	}

}
