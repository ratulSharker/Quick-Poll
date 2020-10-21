package com.funlab.quickpoll.dto;

import java.util.Collection;

public class VoteResult {

	private Long totalVotes;
	private Collection<OptionCount> result;

	public Long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Collection<OptionCount> getResult() {
		return result;
	}

	public void setResult(Collection<OptionCount> result) {
		this.result = result;
	}

}
