package com.funlab.quickpoll.repositoy;

import org.springframework.data.repository.CrudRepository;

import com.funlab.quickpoll.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
