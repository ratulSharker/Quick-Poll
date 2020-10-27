package com.funlab.quickpoll.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.funlab.quickpoll.domain.User;

public class QuickPollUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;

	public QuickPollUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (user.isAdmin()) {
			authorities.addAll(AuthorityUtils.createAuthorityList("ADMIN"));
		} else {
			authorities.addAll(AuthorityUtils.createAuthorityList("USER"));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
