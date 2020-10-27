package com.funlab.quickpoll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.funlab.quickpoll.domain.User;
import com.funlab.quickpoll.repositoy.UserRepository;
import com.funlab.quickpoll.utils.QuickPollUserDetails;

@Service
public class QuickPollUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User with `%s` does not exists", username));
		}
		return new QuickPollUserDetails(user);
	}

}
