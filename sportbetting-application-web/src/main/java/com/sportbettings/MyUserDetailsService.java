package com.sportbettings;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportsbettings.ISportsBettingService;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private ISportsBettingService service;

	@Override
	public UserDetails loadUserByUsername(String username) {
		com.sportsbettings.domain.User user = service.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user");
		}
		return buildUserForAuthentication(user);
	}


	private User buildUserForAuthentication(com.sportsbettings.domain.User user) {
		return new User(user.getEmail(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
	}

}
