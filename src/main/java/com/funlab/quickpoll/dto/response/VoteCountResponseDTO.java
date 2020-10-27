package com.funlab.quickpoll.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteCountResponseDTO {

	private Long optionId;
	private Long count;

	public void incrementCount() {
		this.count++;
	}

}
