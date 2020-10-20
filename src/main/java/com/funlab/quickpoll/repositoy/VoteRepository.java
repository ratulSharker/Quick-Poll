package com.funlab.quickpoll.repositoy;

import org.springframework.data.repository.CrudRepository;

import com.funlab.quickpoll.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
