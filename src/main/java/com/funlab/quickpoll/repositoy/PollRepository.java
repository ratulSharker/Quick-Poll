package com.funlab.quickpoll.repositoy;

import com.funlab.quickpoll.entity.Poll;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}
