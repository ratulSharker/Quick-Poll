package com.funlab.quickpoll.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteCount {

	private Long optionId;
	private Long count;

	public void incrementCount() {
		this.count++;
	}

}
