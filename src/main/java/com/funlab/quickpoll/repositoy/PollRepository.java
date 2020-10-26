package com.funlab.quickpoll.repositoy;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.funlab.quickpoll.domain.Poll;

@Repository
public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}
