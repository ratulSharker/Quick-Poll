package com.funlab.quickpoll.dto;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteResult {

	private Long totalVotes;
	private Collection<VoteCount> result;

}
