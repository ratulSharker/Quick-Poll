package com.funlab.quickpoll.service.impl;

import java.util.Optional;

import com.funlab.quickpoll.domain.Poll;
import com.funlab.quickpoll.repositoy.PollRepository;
import com.funlab.quickpoll.service.PollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Iterable<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll save(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public void deleteById(Long id) {
        pollRepository.deleteById(id);
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return pollRepository.findById(id);
    }

    @Override
    public Page<Poll> findAll(Pageable pageable) {
        return pollRepository.findAll(pageable);
    }
    
}
