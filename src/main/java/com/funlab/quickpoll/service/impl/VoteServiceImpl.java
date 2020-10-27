package com.funlab.quickpoll.service.impl;

import com.funlab.quickpoll.entity.Vote;
import com.funlab.quickpoll.repositoy.VoteRepository;
import com.funlab.quickpoll.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public Iterable<Vote> findByPoll(Long pollId) {
        return voteRepository.findByPoll(pollId);
    }
    
}
