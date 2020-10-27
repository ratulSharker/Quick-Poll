package com.funlab.quickpoll.service;

import java.util.Optional;

import com.funlab.quickpoll.entity.Poll;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PollService {
    
    // V1
    public Iterable<Poll> findAll();
    public Poll save(Poll poll);
    public void deleteById(Long id);
    public Optional<Poll> findById(Long id);


    // V2
    public Page<Poll> findAll(Pageable pageable);
}
