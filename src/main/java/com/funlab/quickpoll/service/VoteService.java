package com.funlab.quickpoll.service;

import com.funlab.quickpoll.dto.VoteResult;
import com.funlab.quickpoll.entity.Vote;

public interface VoteService {
    
    public Vote save(Vote vote);
    public Iterable<Vote> findByPoll(Long pollId);

    public VoteResult computeResult(Long pollId);
}