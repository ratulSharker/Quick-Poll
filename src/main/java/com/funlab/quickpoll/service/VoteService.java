package com.funlab.quickpoll.service;

import com.funlab.quickpoll.entity.Vote;

public interface VoteService {
    
    public Vote save(Vote vote);
    public Iterable<Vote> findByPoll(Long pollId);
}