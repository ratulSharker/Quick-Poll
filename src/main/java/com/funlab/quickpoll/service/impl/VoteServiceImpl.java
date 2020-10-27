package com.funlab.quickpoll.service.impl;

import java.util.HashMap;

import com.funlab.quickpoll.dto.response.VoteCountResponseDTO;
import com.funlab.quickpoll.dto.response.VoteResultResponseDTO;
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

    @Override
    public VoteResultResponseDTO computeResult(Long pollId) {
        VoteResultResponseDTO result = new VoteResultResponseDTO();

		Iterable<Vote> votes = voteRepository.findByPoll(pollId);

		Long totalCount = 0L;
		final HashMap<Long, VoteCountResponseDTO> optionIdWiseVoteCount = new HashMap<>();

		for (Vote vote : votes) {
			totalCount++;
			VoteCountResponseDTO optionCount = optionIdWiseVoteCount.get(vote.getOption().getId());

			if (optionCount == null) {
				optionCount = new VoteCountResponseDTO();

				optionCount.setCount(1L);
				optionCount.setOptionId(vote.getOption().getId());

				optionIdWiseVoteCount.put(vote.getOption().getId(), optionCount);
			} else {
				optionCount.incrementCount();
			}
		}

		result.setTotalVotes(totalCount);
        result.setResult(optionIdWiseVoteCount.values());
        
        return result;
    }
    
}
