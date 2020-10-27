package com.funlab.quickpoll.repositoy;

import com.funlab.quickpoll.entity.Vote;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

	
	@Query("select v from Vote v left join fetch v.option o where o.pollId = :pollId")
	// @Query("select v from Vote v left join fetch v.option o left join o.poll p where p.id = :pollId ")
	public Iterable<Vote> findByPoll(@Param("pollId") Long pollId);
}
