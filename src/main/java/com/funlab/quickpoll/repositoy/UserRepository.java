package com.funlab.quickpoll.repositoy;

import org.springframework.data.repository.CrudRepository;

import com.funlab.quickpoll.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);	
}
